package ru.yolkin.autoparts.model.auth.autodoc;

import feign.form.FormProperty;
import lombok.ToString;
import ru.yolkin.autoparts.model.auth.AbstractAuthResponse;

@ToString
public class AutodocAuthResponse extends AbstractAuthResponse {
  @FormProperty("grant_type")
  public String grantType;

  public AutodocAuthResponse(String scope, String tokenType, String accessToken, int expiresIn, String refreshToken) {
    super(scope, tokenType, accessToken, expiresIn, refreshToken);
  }
}
