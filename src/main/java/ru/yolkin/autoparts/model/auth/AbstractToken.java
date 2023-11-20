package ru.yolkin.autoparts.model.auth;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tokens")
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorColumn(name = "site", discriminatorType = DiscriminatorType.STRING)
public class AbstractToken {
  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "site", insertable = false, updatable = false)
  private String site;

  @Column(name = "token")
  private String token;

  @Column(name = "valid_until")
  private LocalDateTime validUntil;

  @Column(name = "token_type")
  @Enumerated(EnumType.STRING)
  private TokenType tokenType;
}
