package com.example.demo.utils;



import com.example.demo.exception.ViaCepException;
import com.example.demo.exception.ViaCepFormatException;

import java.util.Objects;

public class CEPUtils {

    public static void validaCep(String cep){
        if (Objects.isNull(cep) || cep.isEmpty() || cep.isBlank()) throw new ViaCepException("O cep informado não pode ser nulo ou vazio");
        if (cep.length() > 8) throw new ViaCepFormatException("CEP fora do formato");
        if (cep.length() < 8) throw new ViaCepException("CEP faltando numeros");
    }

    public static String removeMascaraCep(String cep){
        try {
            validaCep(cep);
            return cep;
        } catch (ViaCepFormatException e){
            return cep.replace("-", "");
        }
    }
    public static String mascararCep(String cep){
        try {
            validaCep(cep);
            return cep.substring(0, 5) + "-" + cep.substring(5);
        } catch (ViaCepFormatException e){
            throw new ViaCepException("Cep ja formatado ou fora do padrao");
        }
    }

}
