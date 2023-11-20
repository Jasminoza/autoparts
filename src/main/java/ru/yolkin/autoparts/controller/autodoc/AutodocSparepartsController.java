package ru.yolkin.autoparts.controller.autodoc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yolkin.autoparts.controller.AbstractSparepartsController;
import ru.yolkin.autoparts.feignClient.autodoc.AutodocPartPriceClient;
import ru.yolkin.autoparts.model.auth.AbstractAuthResponse;
import ru.yolkin.autoparts.model.entity.autodoc.Sparepart;
import ru.yolkin.autoparts.service.authService.impl.AutodocAuthService;

@RestController
@RequestMapping("/spareparts")
@Slf4j
@ConditionalOnProperty(value = "autoparts.sites.autodoc.enabled", havingValue = "true")
public class AutodocSparepartsController extends AbstractSparepartsController {
  @Autowired
  AutodocSparepartsController(AutodocPartPriceClient priceClient, AutodocAuthService authService) {
    super(priceClient, authService);
  }

  @ResponseBody
  @GetMapping("/first")
  public Sparepart getFirstPart() {
    AbstractAuthResponse authResponse = authService.getAuthResponse();
    authService.updateToken(authResponse);
    return partPriceClient.getPart(false, "Bearer " + authResponse.getAccessToken());
  }

  @ResponseBody
  @GetMapping("check")
  public String getCheck() {
    return "AutodocSparepartsController enabled";
  }

  @ResponseBody
  @GetMapping("/amortZad")
  public Sparepart getAmortZad() {
    AbstractAuthResponse authResponse = authService.getAuthResponse();
    authService.updateToken(authResponse);
    return partPriceClient.getAmortZad("Bearer " + authResponse.getAccessToken());
  }
}
