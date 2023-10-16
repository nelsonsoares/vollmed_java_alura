package br.com.vollmed.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<ModelPaciente, Long> {

  Page<ModelPaciente> findAllByAtivoTrue(Pageable page);
  
}
