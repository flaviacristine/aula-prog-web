package br.edu.unievangelica.domain.municipio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio,Long>{

    @Query("SELECT m FROM Municipio m WHERE provincia.id =:provincia_id ORDER BY nome ASC")
    public List<Municipio> listarPorProvincia(@Param("provincia_id") long provincia_id);

}
