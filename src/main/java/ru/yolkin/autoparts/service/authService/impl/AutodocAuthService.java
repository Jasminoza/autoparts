package ru.yolkin.autoparts.service.authService.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yolkin.autoparts.config.autodoc.AutodocAuthProperties;
import ru.yolkin.autoparts.feignClient.autodoc.AutodocAuthClient;
import ru.yolkin.autoparts.model.auth.AbstractRefreshTokensResponse;
import ru.yolkin.autoparts.model.auth.AbstractToken;
import ru.yolkin.autoparts.model.auth.TokenType;
import ru.yolkin.autoparts.model.auth.TokensIds;
import ru.yolkin.autoparts.model.auth.autodoc.AutodocAuthRequest;
import ru.yolkin.autoparts.model.auth.autodoc.AutodocAuthResponse;
import ru.yolkin.autoparts.model.auth.autodoc.AutodocToken;
import ru.yolkin.autoparts.repository.authToken.AutodocAuthTokenRepository;
import ru.yolkin.autoparts.service.authService.AbstractAuthService;

@Service
@Slf4j
public class AutodocAuthService extends AbstractAuthService {
  @Autowired
  public AutodocAuthService(
    AutodocAuthClient authClient,
    AutodocAuthProperties properties,
    AutodocAuthTokenRepository<AutodocToken> authRepo
  ) {
    super(authClient, properties, authRepo);
  }

  @Override
  public AutodocAuthResponse getAuthResponse() {
    AutodocAuthRequest request = new AutodocAuthRequest();
    request.setUsername(properties.getUsername());
    request.setPassword(properties.getPassword());
    request.setGrantType("password");
    request.setRememberClient(false);
    log.info("AutodocAuthRequest: " + request);
    return ((AutodocAuthClient) authClient).getAuthResponse(request);
  }

  @Override
  public AbstractRefreshTokensResponse getRefreshTokensResponse() {
    //TODO::
    return null;
  }

  @Override
  public void initToken() {
    log.info("IN AutodocAuthService.initToken():");
    Optional<AbstractToken> autodocAccessTokenOptional = authRepo.findById(TokensIds.AUTODOC_ACCESS_ID);
    if (autodocAccessTokenOptional.isEmpty()) {
      String message = "Autodoc access token doesn't present in database. Fix migration scripts.";
      log.error(message);
      throw new NotImplementedException(message);
    } else {
      accessToken = autodocAccessTokenOptional.get();
    }

    Optional<AbstractToken> autodocRefreshTokenOptional = authRepo.findById(TokensIds.AUTODOC_REFRESH_ID);
    if (autodocRefreshTokenOptional.isEmpty()) {
      String message = "Autodoc refresh token doesn't present in database. Fix migration scripts.";
      log.error(message);
      throw new NotImplementedException(message);
    } else {
      refreshToken = autodocRefreshTokenOptional.get();
    }

    if (accessTokenIsExpired(autodocAccessTokenOptional.get())) {
      log.info("Start refreshing autodoc access token.");
      getBothTokens();
      log.info("Finish refreshing autodoc access token.");
    }

    log.info("OUT AutodocAuthService.initToken():");
  }

  private boolean accessTokenIsExpired(AbstractToken accessToken) {
    return LocalDateTime.now().isAfter(accessToken.getValidUntil());
  }

  private void getBothTokens() {
    AutodocAuthResponse authResponse = getAuthResponse();

    accessToken = new AutodocToken(
      TokensIds.AUTODOC_ACCESS_ID,
      "WWW.AUTODOC,RU",
      authResponse.getAccessToken(),
      LocalDateTime.now().plusSeconds(authResponse.getExpiresIn()),
      TokenType.ACCESS
    );

    logAccessToken();

    refreshToken = new AutodocToken(
      TokensIds.AUTODOC_REFRESH_ID,
      "WWW.AUTODOC,RU",
      authResponse.getRefreshToken(),
      LocalDateTime.now().plusYears(1L),
      TokenType.REFRESH
    );

    logRefreshToken();
  }

  private void logAccessToken() {
    if (accessToken.getToken() != null) {
      log.info("Access token has been set. Saving to repository.");
      authRepo.deleteById(TokensIds.AUTODOC_ACCESS_ID);
      AutodocToken savedAccessToken = (AutodocToken) authRepo.save(accessToken);
      log.info("Saved access token: " + savedAccessToken);
    } else {
      log.error("Can't set access token, it is null.");
    }
  }

  private void logRefreshToken() {
    if (refreshToken.getToken() != null) {
      log.info("Refresh token has been set. Saving to repository.");
      authRepo.deleteById(TokensIds.AUTODOC_REFRESH_ID);
      AutodocToken savedRefreshToken = (AutodocToken) authRepo.save(refreshToken);
      log.info("Saved refresh token: " + savedRefreshToken);
    } else {
      log.error("Can't set refresh token, it is null.");
    }
  }
}
