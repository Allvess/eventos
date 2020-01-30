package br.com.sistema_eventos.service;

import br.com.sistema_eventos.model.eventosModel;
import br.com.sistema_eventos.repository.eventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class eventosService {

    @Autowired
    private eventosRepository repository;

    public eventosModel cadastrarEvento(eventosModel evento) {
        evento.setAtivo(Boolean.TRUE);
        evento.setDataCriacao(LocalDate.now());
        return repository.save(evento);
    }

    public Iterable<eventosModel> listarEventos(String nome) {
        Iterable<eventosModel> eventos = repository.findAll();
        return eventos;
    }

    public eventosModel listarEvento(String nome) {
        Optional<eventosModel> evento = repository.findByNome(nome);
        if (evento.isPresent()) {
            return evento.get();
        }
        return null;
    }
}
