package com.luv2code.myapp.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.luv2code.myapp.dto.response.CepResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class ViaCepService {

    private final RestTemplate restTemplate;

    public CepResponse consultaCep(String cep) {

        cep = cep.trim();

        if(cep.contains("-")){
            cep = cep.replace("-", "");
        }
        
        if (cep.matches(".*[^0-9].*")){
            throw new IllegalArgumentException("CEP inválido: deve conter apenas números.");
        }

        if(cep.length() !=8){
            throw new IllegalArgumentException("CEP inválido: deve conter exatamente 8 dígitos.");
        }

        String url = "https://viacep.com.br/ws/" + cep + "/json";
        ResponseEntity<CepResponse> response = restTemplate.getForEntity(url, CepResponse.class);return response.getBody();
        
    }
}
