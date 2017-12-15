package br.edu.unievangelica.domain.arquivo;

import br.edu.unievangelica.domain.unidade.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
}
