package ru.yolkin.autoparts.model.entity.autodoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Manufacturer {
  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("popularityIndex")
  private Integer popularityIndex;
}
