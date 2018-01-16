package br.edu.unievangelica.domain.frequenciaPadrao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrequenciaPadraoRepository extends JpaRepository<FrequenciaPadrao, Long>{
}
