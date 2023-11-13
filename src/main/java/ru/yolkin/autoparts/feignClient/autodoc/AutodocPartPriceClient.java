package ru.yolkin.autoparts.feignClient.autodoc;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yolkin.autoparts.feignClient.AbstractPartPriceClient;
import ru.yolkin.autoparts.model.entity.autodoc.Sparepart;

@FeignClient(value = "autodoc", url = "https://webapi.autodoc.ru/api")
@Headers("Content-Type: application/json")
public interface AutodocPartPriceClient extends AbstractPartPriceClient {

  @Override
  @GetMapping(value = "/spareparts/511/11127583138/2")
  Sparepart getPart(
      @RequestParam(value = "isrecross") boolean isRecross,
      @RequestHeader("Authorization") String authorization
  );
}
