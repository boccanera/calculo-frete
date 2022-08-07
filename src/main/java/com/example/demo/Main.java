package com.example.demo;

import com.example.demo.model.Cep;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


//Consulte o CEP através do webservice 'viacep.com.br' e retorne os valores do frete conforme as regras de negócio:
// Se mesmo DDD, ofereça um desconto no valor do frete de 50%
// Se mesmo Estado, ofereça um desconto no valor do frete de 25%


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        Scanner scan = new Scanner(System.in);

        //Poderia perguntar o DDD também, mas podemos dar um get no dado específico, com possiblidade
        // de simplesmente identificar todos os dados, pois podemos usar no futuro.
        System.out.print("Insira seu CEP:");
        String cepOrigem = scan.next();
        System.out.print("Insira o CEP de destino:");
        String cepDestino = scan.next();
        System.out.print("Qual peso do pacote em KG?");
        double peso = scan.nextDouble();

        Cep enderecoOrigem = ViaCepClient.findCep(cepOrigem);
        Cep enderecoDestino = ViaCepClient.findCep(cepDestino);

        System.out.println(enderecoOrigem);
        System.out.println(enderecoDestino);


        // Acredito que poderiamos usar um array, mas ia complicar ainda mais, por isso vamos utilizar POO

        //Deixar no padrao 02021-020
//      String n = CEPUtils.mascararCep(cepOrigem);

        String dddOrigem = enderecoOrigem.getDdd();
        String dddDestino = enderecoDestino.getDdd();
        String estadoOrigem = enderecoOrigem.getUf();
        String estadoDestino = enderecoDestino.getUf();







        //Regras do negócio.
        int entregaPrevistaEmDias;
        double valorFrete = peso * 15;
        if (dddOrigem.equals(dddDestino)) {
            valorFrete *= 0.50;
            entregaPrevistaEmDias = 1;
        } else if (estadoOrigem.equals(estadoDestino)) {
            valorFrete *= 0.75;
            entregaPrevistaEmDias = 3;
        } else entregaPrevistaEmDias = 10;
        System.out.println("A entrega prevista e de ate: " + entregaPrevistaEmDias +
                " dias e o valor do frete e de R$: " + valorFrete);
    }
}
