package br.edu.unievangelica.domain.cursoTurno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoTurnoRepository extends JpaRepository<CursoTurno, Long> {
}
