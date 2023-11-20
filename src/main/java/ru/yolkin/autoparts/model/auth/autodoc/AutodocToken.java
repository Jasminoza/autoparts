package ru.yolkin.autoparts.model.auth.autodoc;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import ru.yolkin.autoparts.model.auth.AbstractToken;
import ru.yolkin.autoparts.model.auth.TokenType;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "AUTODOC_TOKEN")
@NoArgsConstructor
@ToString
public class AutodocToken extends AbstractToken {
  public AutodocToken(Long id, String site, String token, LocalDateTime validUntil, TokenType tokenType) {
    super(id, site, token, validUntil, tokenType);
  }
}
