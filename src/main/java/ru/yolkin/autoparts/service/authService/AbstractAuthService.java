package ru.yolkin.autoparts.service.authService;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yolkin.autoparts.config.AbstractAuthProperties;
import ru.yolkin.autoparts.feignClient.AbstractAuthFeignClient;
import ru.yolkin.autoparts.model.auth.AbstractAuthResponse;
import ru.yolkin.autoparts.model.auth.AbstractToken;
import ru.yolkin.autoparts.repository.AbstractAuthTokenRepository;

@Service
@AllArgsConstructor
@Slf4j
public abstract class AbstractAuthService implements AuthService {
  public AbstractAuthFeignClient authClient;
  public AbstractAuthProperties properties;
  public AbstractAuthTokenRepository authRepo;
  public static AbstractToken accessToken;
  public static AbstractToken refreshToken;

  public abstract void initToken();

  public void updateToken(AbstractAuthResponse response) {
    log.info("IN updateToken(): ");

    if (accessToken == null) {
      initToken();
    }

    if (tokenNeedToBeUpdated()) {
      log.info("The token needs to be updated.");
      updateCurrentToken(response);
    } else {
      log.info("The token does not need to be updated.");
    }

    log.info("OUT updateAutodocToken()");
  }

  private void updateCurrentToken(AbstractAuthResponse response) {
    accessToken.setToken(response.getAccessToken());
    accessToken.setValidUntil(LocalDateTime.now().plusSeconds(response.getExpiresIn()));
  }

  private boolean tokenNeedToBeUpdated() {
    return tokenIsNull()
        || tokenIsBlankOrEmpty()
        || tokenIsExpired();
  }

  private boolean tokenIsNull() {
    if (accessToken.getToken() == null) {
      log.info("autodocTokenString is null.");
      return true;
    }

    return false;
  }

  private boolean tokenIsBlankOrEmpty() {
    String autodocTokenString = accessToken.getToken();
    if (autodocTokenString.isBlank()) {
      log.info("autodocTokenString is blank.");
      return true;
    }

    if (autodocTokenString.isEmpty()) {
      log.info("autodocTokenString is empty.");
      return true;
    }

    return false;
  }

  private boolean tokenIsExpired() {
    LocalDateTime validUntil = accessToken.getValidUntil();

    if (validUntil == null) {
      log.info("Token's 'validUntil' field is null.");
      return true;
    }

    if (LocalDateTime.now().isAfter(accessToken.getValidUntil())) {
      log.info("Token's validity time has been expired.");
      return true;
    }

    return false;
  }
}
