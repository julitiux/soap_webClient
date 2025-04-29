package com.soap_webClient.service;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class SoapWebClient {

  private final WebClient webClient;

  public SoapWebClient(WebClient.Builder webClient) {
    this.webClient = webClient
      .baseUrl("http://www.dneonline.com/calculator.asmx")
      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_XML_VALUE)
      .build();
  }

  public Mono<String> add(int a, int b) {
    String soapXml = String.format(
      """
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tem="http://tempuri.org/">
           <soapenv:Header/>
           <soapenv:Body>
              <tem:Add>
                 <tem:intA>%d</tem:intA>
                 <tem:intB>%d</tem:intB>
              </tem:Add>
           </soapenv:Body>
        </soapenv:Envelope>
        """, a, b);

    return webClient.post()
      .header("SOAPAction", "http://tempuri.org/Add")
      .bodyValue(soapXml)
      .retrieve()
      .bodyToMono(String.class);
  }
}
