package br.edu.unievangelica.domain.banco;

import br.edu.unievangelica.domain.emolumento.Emolumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {

    public List<Banco> findBancoByUnidadeId(long id);
}
