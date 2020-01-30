package br.com.sistema_eventos.controller;


import br.com.sistema_eventos.model.eventosModel;
import br.com.sistema_eventos.service.eventosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class eventoController {

    @Autowired
    private eventosService service;

    @GetMapping
    public String mostrarHome() {
        return "index";
    }

    @PostMapping("/cadastrar")
    public String cadastrarEventos(eventosModel eventos) {
        service.cadastrarEvento(eventos);
        return "index";
    }

    @GetMapping("/eventos")
    public ModelAndView listarEventos(String nome) {
        ModelAndView pagina = new ModelAndView("listarEventos");
        Iterable<eventosModel> eventos = service.listarEventos(nome);
        pagina.addObject("eventos", eventos);
        return pagina;
    }

    @GetMapping("/buscar")
    public String retornarEvento() {
        return "buscaEvento";
    }

    @GetMapping("evento")
    public String listarEvento(@RequestParam("nome") String nome, Model model) {
        eventosModel eventos = service.listarEvento(nome);
        if (eventos != null) {
            model.addAttribute("eventos", eventos);
            return "listaEvento";

        } else {
            model.addAttribute("msg", "O evento " + nome + " não foi encontrado!! procure novamente");
            return "buscarEvento";
        }
    }

    @GetMapping("evento/{nome}")
    public String listaEventosPorPagina(@PathVariable("nome") String nome, Model model) {
        eventosModel eventos = service.listarEvento(nome);
        if (nome != null) {
            model.addAttribute("eventos", eventos);
            return "listaEvento";

        } else {
            model.addAttribute("msg", "O evento " + nome + " não foi encontrado!! procure novamente");
            return "buscarEvento";
        }
    }
}