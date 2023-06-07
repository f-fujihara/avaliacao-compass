package br.com.ffujihara.avaliacao.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ffujihara.avaliacao.model.Voto;
import br.com.ffujihara.avaliacao.repository.VotoRepository;

@RestController
@RequestMapping("/votos")
public class VotoController {

    private final VotoRepository votoRepository;

    @Autowired
    public VotoController(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    @GetMapping
    public List<Voto> getAllVotos() {
        return votoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voto> getVotoById(@PathVariable Long id) {
        return votoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/votos")
    public ResponseEntity<Object> votar(@RequestBody Voto voto) {
        if (voto.getAssociado().getVotos().stream().anyMatch(v -> v.getPauta().equals(voto.getPauta()))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O associado já votou nesta pauta.");
        }

        LocalDateTime fimSessao = voto.getPauta().getInicioSessao().plusMinutes(voto.getPauta().getTempoSessao());
        if (fimSessao.isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A sessão da pauta já foi encerrada.");
        }

        Voto novoVoto = votoRepository.save(voto);
        return ResponseEntity.ok(novoVoto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voto> updateVoto(@PathVariable Long id, @RequestBody Voto votoAtualizado) {
        return votoRepository.findById(id)
                .map(voto -> {
                    voto.setAssociado(votoAtualizado.getAssociado());
                    voto.setPauta(votoAtualizado.getPauta());
                    voto.setVoto(votoAtualizado.getVoto());
                    Voto votoAtualizadoEntity = votoRepository.save(voto);
                    return ResponseEntity.ok(votoAtualizadoEntity);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}