package com.luv2code.myapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.luv2code.myapp.dto.response.CepResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/cep")
public class ViaCepController {
    
    @GetMapping("/{cep}")
    public CepResponse consultaCep(@PathVariable String cep){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/" + cep + "/json";
        ResponseEntity<CepResponse> response = restTemplate.getForEntity(url, CepResponse.class);
        return response.getBody();
    }
}
