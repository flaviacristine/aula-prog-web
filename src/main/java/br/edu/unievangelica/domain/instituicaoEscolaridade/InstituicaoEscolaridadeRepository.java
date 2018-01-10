package br.edu.unievangelica.domain.instituicaoEscolaridade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituicaoEscolaridadeRepository extends JpaRepository<InstituicaoEscolaridade, Long> {
}
