package ru.yolkin.autoparts.telegram;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.yolkin.autoparts.Utils.TelegramUtils;
import ru.yolkin.autoparts.config.TelegramBotProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.yolkin.autoparts.Utils.TelegramBotCommands.HELP;
import static ru.yolkin.autoparts.Utils.TelegramBotCommands.START;

@Component
@Slf4j
public class AutopartsBot extends TelegramLongPollingBot {
  @Autowired
  private TelegramBotProperties config;
  private static final String HELP_TEXT = getHelpText();
  private static final String START_TEXT = "Hello from bot";

  public AutopartsBot(TelegramBotProperties config) {
    this.config = config;
    List<BotCommand> listOfCommands = new ArrayList<>();
    listOfCommands.add(new BotCommand(START, "get a welcome message"));
    listOfCommands.add(new BotCommand(HELP, "info how to use this bot"));
    try {
      this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
    } catch (TelegramApiException e) {
      log.error(Arrays.toString(e.getStackTrace()));
    }
  }

  @Override
  public String getBotUsername() {
    return config.getBotName();
  }

  @Override
  public String getBotToken() {
    return config.getBotToken();
  }

  @Override
  public void onUpdateReceived(Update update) {

    // We check if the update has a message and the message has text
    if (update.hasMessage() && update.getMessage().hasText()) {
      // Set variables
      String messageText = update.getMessage().getText();
      long chatId = update.getMessage().getChatId();

      switch (messageText) {
        case START -> sendMessage(START_TEXT, chatId);
        case HELP -> sendMessage(HELP_TEXT, chatId);

        default -> sendMessage("Command not found", chatId);
      }
    }
  }

  private void sendMessage(String textToSend, long chatId) {
    try {
      SendMessage message = new SendMessage(); // Create a message object object
      message.setChatId(String.valueOf(chatId));
      message.setText(textToSend);
      message.enableMarkdown(true);
      execute(message); // Sending our message object to user
    } catch (TelegramApiException e) {
      log.error(Arrays.toString(e.getStackTrace()));
    }
  }

  @NotNull
  private static String getHelpText() {
    return """
      Чтобы юзать мегабота - отправь /findPart.
          я пока ниче не умею, но ты все равно отправляй че нить.
            пупупуууу;
      """
      + TelegramUtils.getJavaCode
      ("""
              public class HelloWorld {
                  public static void main(String[] args) {
                      System.out.println("Hello, World!");
                  }
              }
        """);
  }
}


