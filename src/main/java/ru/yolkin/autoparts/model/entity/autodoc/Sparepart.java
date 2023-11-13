package ru.yolkin.autoparts.model.entity.autodoc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sparepart {

  @JsonProperty("partNumber")
  private String partNumber;

  @JsonProperty("displayPartNumber")
  private String displayPartNumber;

  @JsonProperty("name")
  private String name;

  private Manufacturer manufacturer;

  @JsonProperty("minimalPrice")
  private BigDecimal minimalPrice;

  @JsonProperty("minimalDeliveryDays")
  private Integer minimalDeliveryDays;
}
