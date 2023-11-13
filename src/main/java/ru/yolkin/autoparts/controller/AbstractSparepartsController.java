package ru.yolkin.autoparts.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yolkin.autoparts.feignClient.AbstractPartPriceClient;
import ru.yolkin.autoparts.model.entity.autodoc.Sparepart;
import ru.yolkin.autoparts.service.authService.AbstractAuthService;

@RestController
@RequestMapping("/spareparts")
@AllArgsConstructor
@Slf4j
public abstract class AbstractSparepartsController {

  public final AbstractPartPriceClient partPriceClient;
  public final AbstractAuthService authService;

  @ResponseBody
  @GetMapping("/first")
  public Sparepart getFirstPart() {
    throw new NotImplementedException();
  }
}
