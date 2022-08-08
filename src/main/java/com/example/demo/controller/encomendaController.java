package com.example.demo.controller;


import com.example.demo.ViaCepClient;
import com.example.demo.model.Cep;
import com.example.demo.model.Encomenda;
import com.example.demo.repository.EncomendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class encomendaController {


    private EncomendaRepository er;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String salvar(Encomenda encomenda) {
        er.save(encomenda);
        Long id = encomenda.getId();
        return "redirect:/consultaRealizada/" + id;
    }

    @RequestMapping(value = "/consultaRealizada/{id}", method = RequestMethod.GET)
    public ModelAndView resultadoCalculo(@PathVariable("id") long id) {
        Encomenda encomenda = er.findById(id);
        ModelAndView mv = new ModelAndView("/resultadoCalculo");
        mv.addObject("encomenda", encomenda);

        int dias = encomenda.calculoDias(encomenda.getCepOrigem(), encomenda.getCepDestino());
        double valorFrete1 = encomenda.calculoFrete(dias);
        DecimalFormat duasCasasDecimais = new DecimalFormat("#.00");
        String valorFrete = duasCasasDecimais.format(valorFrete1);
        mv.addObject("encomenda1", dias);
        mv.addObject("encomenda2", valorFrete);
        return mv;
    }
}

//@GetMapping
//    public List<Cep> getAll() {
//        return (List<Cep>) cepRepository.findAll();
//    }
//}
//
//    @PostMapping
//    public Cep salvar(Cep cep) {
//        return cepRepository.save(cep);
//    }
//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String form() {
//        return "index";
//    }
//
//    @PostMapping
//    public String calculoFrete(Cep cep, Cep cep1) {
//        String cepOrigem = cep.getCepOrigem();
//        String cepDestino = cep.getCepDestino();
//        Cep enderecoOrigem = ViaCepClient.findCep(cepOrigem);
//        Cep enderecoDestino = ViaCepClient.findCep(cepDestino);
//        String dddOrigem = enderecoOrigem.getDdd();
//        String dddDestino = enderecoDestino.getDdd();
//        String estadoOrigem = enderecoOrigem.getUf();
//        String estadoDestino = enderecoDestino.getUf();
//
//        int peso = 0;
//        int entregaPrevistaEmDias;
//        double valorFrete = peso * 15;
//        if (dddOrigem.equals(dddDestino)) {
//            valorFrete *= 0.50;
//            entregaPrevistaEmDias = 1;
//        } else if (estadoOrigem.equals(estadoDestino)) {
//            valorFrete *= 0.75;
//            entregaPrevistaEmDias = 3;
//        } else entregaPrevistaEmDias = 10;
//        String mensagem = "A entrega prevista e de ate: " + entregaPrevistaEmDias +
//                " dias e o valor do frete e de R$: " + valorFrete;
//        return "index";
//
//    }
//}


/*    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
    public String form() {
        return "evento/formEvento";
    }

    @RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
    public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/cadastrarEvento";
        }

        er.save(evento);
        attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");
        return "redirect:/cadastrarEvento";
    }

    @RequestMapping("/eventos")
    public ModelAndView listaEventos(){
        ModelAndView mv = new ModelAndView("listaEventos");
        Iterable<Evento> eventos = er.findAll();
        mv.addObject("eventos", eventos);
        return mv;
    }
*/
