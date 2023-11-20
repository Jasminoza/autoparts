package ru.yolkin.autoparts.model.auth.autodoc;

import feign.form.FormProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yolkin.autoparts.model.auth.AbstractAuthRequest;

@Getter
@Setter
@ToString
public class AutodocAuthRequest extends AbstractAuthRequest {
  @FormProperty("username")
  public String username;

  @FormProperty("password")
  public String password;

  @FormProperty("rememberClient")
  public boolean rememberClient;

  @FormProperty("grant_type")
  public String grantType;

  public AutodocAuthRequest() {
    super();
  }
}
