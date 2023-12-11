package ru.yolkin.autoparts.Utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TelegramUtils {
  public String getJavaCode(String code) {
    return "```java" + code + "```";
  }
}
