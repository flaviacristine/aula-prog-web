package br.edu.unievangelica.domain.avaliacaoPadrao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoPadraoRepository extends JpaRepository<AvaliacaoPadrao, Long> {
}
