package ru.yolkin.autoparts.model.auth.autodoc;

import java.time.LocalDateTime;
import ru.yolkin.autoparts.model.auth.AbstractToken;

public class AutodocToken extends AbstractToken {

  public AutodocToken(String token, LocalDateTime validUntil) {
    super(token, validUntil);
  }
}
