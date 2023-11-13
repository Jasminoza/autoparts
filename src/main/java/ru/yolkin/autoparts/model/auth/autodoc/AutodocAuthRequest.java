package ru.yolkin.autoparts.model.auth.autodoc;

import feign.form.FormProperty;
import lombok.Getter;
import lombok.Setter;
import ru.yolkin.autoparts.model.auth.AbstractAuthRequest;

@Getter
@Setter
public class AutodocAuthRequest extends AbstractAuthRequest {
  @FormProperty("username")
  public String userNameOrEmailAddress;

  @FormProperty("password")
  public String password;

  @FormProperty("grant_type")
  public String grantType;

  public AutodocAuthRequest() {
    super();
  }
}
