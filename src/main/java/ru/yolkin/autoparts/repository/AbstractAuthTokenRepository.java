package ru.yolkin.autoparts.repository;

import org.springframework.data.repository.CrudRepository;
import ru.yolkin.autoparts.model.auth.AbstractToken;

public interface AbstractAuthTokenRepository<T extends  AbstractToken> extends CrudRepository<T, Long> {
}
