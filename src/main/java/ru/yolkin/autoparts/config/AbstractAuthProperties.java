package ru.yolkin.autoparts.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractAuthProperties {
  String username;
  String password;
}
