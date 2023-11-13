package ru.yolkin.autoparts.service.authService.impl;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yolkin.autoparts.config.autodoc.AutodocAuthProperties;
import ru.yolkin.autoparts.feignClient.autodoc.AutodocAuthClient;
import ru.yolkin.autoparts.model.auth.autodoc.AutodocAuthRequest;
import ru.yolkin.autoparts.model.auth.autodoc.AutodocAuthResponse;
import ru.yolkin.autoparts.model.auth.autodoc.AutodocToken;
import ru.yolkin.autoparts.service.authService.AbstractAuthService;

@Service
@Slf4j
public class AutodocAuthService extends AbstractAuthService {

  @Autowired
  public AutodocAuthService(AutodocAuthClient authClient, AutodocAuthProperties properties) {
    super(authClient, properties);
  }

  @Override
  public AutodocAuthResponse getAuthResponse() {
    AutodocAuthRequest request = new AutodocAuthRequest();
    request.setUserNameOrEmailAddress(properties.getUsername());
    request.setPassword(properties.getPassword());
    request.setGrantType("password");
    return ((AutodocAuthClient) authClient).getAuthResponse(request);
  }

  @Override
  public void initToken() {
    AutodocAuthResponse authResponse = getAuthResponse();
    token = new AutodocToken(authResponse.getAccessToken(), LocalDateTime.now().plusSeconds(authResponse.getExpiresIn()));
  }
}
