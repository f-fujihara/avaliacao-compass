package br.com.ffujihara.avaliacao.repository;

import br.com.ffujihara.avaliacao.model.Associado;
import br.com.ffujihara.avaliacao.model.Pauta;
import br.com.ffujihara.avaliacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    List<Voto> findByPauta(Pauta pauta);

    boolean existsByPautaAndAssociado_Id(Pauta pauta, Long associadoId);
    boolean existsByAssociado(Associado associado);
}