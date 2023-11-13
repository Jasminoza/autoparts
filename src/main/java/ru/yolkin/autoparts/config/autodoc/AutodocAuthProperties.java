package ru.yolkin.autoparts.config.autodoc;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.yolkin.autoparts.config.AbstractAuthProperties;

@Configuration
@ConfigurationProperties(prefix = "autodoc-auth")
public class AutodocAuthProperties extends AbstractAuthProperties {

}
