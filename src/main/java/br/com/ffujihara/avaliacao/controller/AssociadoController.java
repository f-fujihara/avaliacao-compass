package br.com.ffujihara.avaliacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ffujihara.avaliacao.model.Associado;
import br.com.ffujihara.avaliacao.repository.AssociadoRepository;

@RestController
@RequestMapping("/associados")
public class AssociadoController {
    @Autowired
    private AssociadoRepository associadoRepository;

    @GetMapping
    public List<Associado> getAllAssociados() {
        return associadoRepository.findAll();
    }

    @PostMapping
    public Associado createAssociado(@RequestBody Associado associado) {
        return associadoRepository.save(associado);
    }
}