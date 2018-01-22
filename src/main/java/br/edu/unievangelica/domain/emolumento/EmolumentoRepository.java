package br.edu.unievangelica.domain.emolumento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmolumentoRepository extends JpaRepository<Emolumento, Long> {

    public List<Emolumento> findEmolumentosByUnidadeId(long id);
}
