package br.edu.unievangelica.domain.emolumento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmolumentoRepository extends JpaRepository<Emolumento, Long> {
}
