package br.com.ffujihara.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ffujihara.avaliacao.model.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

}
