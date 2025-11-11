package com.example.demo_sp_th_2025.controller;

import com.example.demo_sp_th_2025.DAO.ClienteDAO;
import com.example.demo_sp_th_2025.modelo.Cliente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller

public class DemothController {
    private final StringHttpMessageConverter stringHttpMessageConverter;

    @Autowired
    private ClienteDAO clienteDAO;

    public DemothController(StringHttpMessageConverter stringHttpMessageConverter) {
        this.stringHttpMessageConverter = stringHttpMessageConverter;
    }

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

    @GetMapping("/demoth3")
    public String demothHttpSession(Model model, HttpSession session){

        String mensajeAsesion = "Lo grabé en demoth3";
        String mensajeModel = "Este solo lo ve plantilla demoth3";


        model.addAttribute("mensajeModel", mensajeModel);

        //Atribute en sesion son visble en todos los endspoits
        session.setAttribute("mensajeAsesion", mensajeAsesion);
        return "demoth3";
    }

    @GetMapping("/demoth3_2")
    public String demothHttpSession2(){




        return "demoth3";
    }

    @GetMapping("/demoth5")

    public String demoth5(Model model){

        Cliente cliente = Cliente.builder()
                .id(101)
                .nombre("Cervantes")
                .build();

        model.addAttribute("cliente", cliente);
        return "demoth5";

    }

    // FORMULARIOS

    @GetMapping("/demoth/crear")
    public String demothCrear(Model model){
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "demoth-crear";
    }

    @PostMapping("/demoth/crear")
    public String demothCrearPost(@ModelAttribute Cliente cliente) {

        clienteDAO.create(cliente);

        return "redirect:/demoth/listar";

    }

    @GetMapping("/demoth/listar")
    public String demothListar(Model model){
        List<Cliente> list = clienteDAO.getAll();
        model.addAttribute("clientes", list);
        return "demoth-listar";
    }


    // FIN FORMULARIOS

}
