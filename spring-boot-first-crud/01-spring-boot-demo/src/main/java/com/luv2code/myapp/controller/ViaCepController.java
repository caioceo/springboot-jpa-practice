package com.luv2code.myapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.myapp.dto.response.CepResponse;
import com.luv2code.myapp.services.ViaCepService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/cep")
public class ViaCepController {

    private final ViaCepService viaCepService;

    public ViaCepController(ViaCepService viaCepService){
        this.viaCepService = viaCepService;
    }
    
    @GetMapping("/{cep}")
    public CepResponse consultaCep(@PathVariable String cep){
        return viaCepService.consultaCep(cep);
    }
}
