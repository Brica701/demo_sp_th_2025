package com.example.demo_sp_th_2025.controller;

import com.example.demo_sp_th_2025.modelo.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DemothController {

    //Sin service, solo actuar sobre plantillas html

    //EndPoints

    @GetMapping("/demoth1")
    public String demoth1(Model model){

        model.addAttribute("parrafo2", "En un lugar de la mancha");

        return "demoth1";
    }

    @GetMapping("/demoth2")
    public String demoth2ThBlock(Model model){

        Cliente cliente = Cliente.builder()
                .nombre("Don Miguel de Cervantes")
                .build();

        Cliente cliente2 = Cliente.builder()
                .nombre("Lope de Vega")
                .build();

        List<Cliente> list = List.of(cliente, cliente2);

        model.addAttribute("escritores", list);

    return "demoth2";
    }
}
