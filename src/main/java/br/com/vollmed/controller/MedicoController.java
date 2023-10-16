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

import br.com.vollmed.medico.DadosAtualizacaoMedico;
import br.com.vollmed.medico.DadosCadastroMedico;
import br.com.vollmed.medico.DadosListagemMedico;
import br.com.vollmed.medico.ModelMedico;
import jakarta.validation.Valid;
import br.com.vollmed.medico.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

  @Autowired
  private MedicoRepository repository;
  
  @PostMapping
  @Transactional
  public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
    repository.save(new ModelMedico(dados));
  }

  @GetMapping
  public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable page){
    return repository.findAllByAtivoTrue(page).map(DadosListagemMedico::new);
  }

  @PutMapping
  @Transactional
  public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
    var medicos = repository.getReferenceById(dados.id());
    medicos.atualizarInformacoesMedico(dados);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public void excluir(@PathVariable Long id){
    var medicos = repository.getReferenceById(id);
    medicos.ativarDesativar(false);
  }
}
