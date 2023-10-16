package br.com.vollmed.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<ModelMedico, Long>{

  Page<ModelMedico> findAllByAtivoTrue(Pageable page);
  
}
