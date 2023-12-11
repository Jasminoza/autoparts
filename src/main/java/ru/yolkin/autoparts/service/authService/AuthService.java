package ru.yolkin.autoparts.service.authService;

import ru.yolkin.autoparts.model.auth.AbstractAuthResponse;
import ru.yolkin.autoparts.model.auth.AbstractRefreshTokensResponse;

public interface AuthService {
  AbstractAuthResponse getAuthResponse();
  AbstractRefreshTokensResponse getRefreshTokensResponse();
}
