package br.com.ffujihara.avaliacao.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ffujihara.avaliacao.model.Pauta;
import br.com.ffujihara.avaliacao.repository.PautaRepository;

@RestController
@RequestMapping("/pautas")
public class PautaController {
    @Autowired
    private PautaRepository pautaRepository;

    @GetMapping
    public List<Pauta> getAllPautas() {
        return pautaRepository.findAll();
    }

    @PostMapping
    public Pauta createPauta(@RequestBody Pauta pauta) {
        if (pauta.getTempoSessao() == null || pauta.getTempoSessao() < 1) {
            pauta.setTempoSessao(1L);
        }
        pauta.setEmSessão(false);
        pauta.setInicioSessao(null);
        return pautaRepository.save(pauta);
    }
    
    @PutMapping("/{id}/inicio-sessao")
    public Pauta iniciarSessao(@PathVariable("id") Long id) {
        Pauta pauta = pautaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pauta não encontrada com o ID: " + id));

        if (pauta.getEmSessão()) {
            throw new RuntimeException("A sessão da pauta já está em andamento.");
        }

        LocalDateTime inicioSessao = LocalDateTime.now();
        LocalDateTime fimSessao = inicioSessao.plusMinutes(pauta.getTempoSessao());

        if (fimSessao.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("O horário de término da sessão já passou.");
        }

        pauta.setEmSessão(true);
        pauta.setInicioSessao(inicioSessao);

        return pautaRepository.save(pauta);
    }
}