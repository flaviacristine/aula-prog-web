package br.edu.unievangelica.domain.planoPagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoPagamentoRepository extends JpaRepository<PlanoPagamento, Long> {
}
