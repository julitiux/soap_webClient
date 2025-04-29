package com.soap_webClient.controller;

import com.soap_webClient.service.SoapWebClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/webClient")
public class SoapWebClientController {

  private final SoapWebClient soapWebClient;

  public SoapWebClientController(SoapWebClient soapWebClient) {
    this.soapWebClient = soapWebClient;
  }

  @GetMapping("/add")
  public Mono<String> sumar(@RequestParam int a, @RequestParam int b) {
    return soapWebClient.add(a, b);
  }
}
