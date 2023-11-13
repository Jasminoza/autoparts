package ru.yolkin.autoparts.feignClient.autodoc;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import ru.yolkin.autoparts.feignClient.AbstractAuthFeignClient;
import ru.yolkin.autoparts.model.auth.autodoc.AutodocAuthRequest;
import ru.yolkin.autoparts.model.auth.autodoc.AutodocAuthResponse;

@FeignClient(value = "autodocAuth", url = "https://auth.autodoc.ru")
public interface AutodocAuthClient extends AbstractAuthFeignClient {
  @PostMapping(value = "/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
  @Headers("Content-Type: application/x-www-form-urlencoded")
  AutodocAuthResponse getAuthResponse(AutodocAuthRequest tokenRequest);
}
