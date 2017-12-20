package br.edu.unievangelica.domain.mantenedora;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tauan.camargo on 19/12/2017.
 */
@Repository
public interface MantenedoraRepository extends JpaRepository<Mantenedora, Long>{
}
