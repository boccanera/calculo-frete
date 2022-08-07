package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Cep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

}

