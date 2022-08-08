package com.example.demo.model;

import com.example.demo.ViaCepClient;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Encomenda {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private String nome;
    private String nomeDestinatario;
    private String cepOrigem;
    private String cepDestino;
    private double altura;
    private double comprimento;
    private double largura;
    private double peso;

    public int calculoDias(String cep, String cep1) {
        Cep enderecoOrigem = ViaCepClient.findCep(cep);
        Cep enderecoDestino = ViaCepClient.findCep(cep1);
        String dddOrigem = enderecoOrigem.getDdd();
        String dddDestino = enderecoDestino.getDdd();
        String estadoOrigem = enderecoOrigem.getUf();
        String estadoDestino = enderecoDestino.getUf();

        int entregaPrevistaEmDias;
        double valorFrete = peso * 15;
        if (dddOrigem.equals(dddDestino)) {

            entregaPrevistaEmDias = 2;
        } else if (estadoOrigem.equals(estadoDestino)) {

            entregaPrevistaEmDias = 3;
        } else entregaPrevistaEmDias = 5;
        return entregaPrevistaEmDias;

    }

    public double calculoFrete(int dias) {
        double medidas = (altura + comprimento + largura) * 1.5;
        double valorFrete = peso * 15 + medidas;
        if (dias <= 2) {
            valorFrete *= 0.50;
            if (dias <= 3) {
                valorFrete *= 0.75;
            }
        }
        return valorFrete;
    }

}

