package ru.yolkin.autoparts.feignClient;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yolkin.autoparts.model.entity.autodoc.Sparepart;

public interface AbstractPartPriceClient {

  default Sparepart getPart(
      @RequestParam(value = "isrecross") boolean isRecross,
      @RequestHeader("Authorization") String authorization
  ) {
    throw new NotImplementedException();
  }

  default Sparepart getAmortZad(
    @RequestHeader("Authorization") String authorization
  ) {
    throw new NotImplementedException();
  }
}
