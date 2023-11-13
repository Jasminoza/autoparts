package ru.yolkin.autoparts.model.auth;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class AbstractToken {
  private String token;
  private LocalDateTime validUntil;
}
