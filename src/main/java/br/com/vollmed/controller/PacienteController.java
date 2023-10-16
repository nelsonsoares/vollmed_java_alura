package br.com.vollmed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vollmed.paciente.DadosAtualizacaoPaciente;
import br.com.vollmed.paciente.DadosCadastroPaciente;
import br.com.vollmed.paciente.DadosListagemPaciente;
import br.com.vollmed.paciente.ModelPaciente;
import br.com.vollmed.paciente.PacienteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

  @Autowired
  private PacienteRepository repository;

  @PostMapping
  @Transactional
  public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
    repository.save(new ModelPaciente(dados));
  }

  @GetMapping
  public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable page) {
    return repository.findAllByAtivoTrue(page).map(DadosListagemPaciente::new);
  }

  @PutMapping
  @Transactional
  public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados){
    var paciente = repository.getReferenceById(dados.id());
    paciente.atualizarDadosPaciente(dados);
  }

  @DeleteMapping("/{id}")
  public void excluir(@PathVariable Long id){
    var paciente = repository.getReferenceById(id);
    paciente.ativarDesativar(false);
  }
}
