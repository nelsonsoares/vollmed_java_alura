package br.com.vollmed.medico;

import br.com.vollmed.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ModelMedico {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String email;
  private String telefone;
  private String crm;
  
  @Enumerated(EnumType.STRING)
  private Especialidade especialidade;
  
  @Embedded
  private Endereco endereco;

  private Boolean ativo;
  
  public ModelMedico(DadosCadastroMedico dados) {
    this.nome = dados.nome();
    this.email = dados.email();
    this.telefone = dados.telefone();
    this.crm = dados.crm();
    this.especialidade = dados.especialidade();
    this.endereco = new Endereco(dados.endereco());
    this.ativo = true;
  }

  public void atualizarInformacoesMedico(DadosAtualizacaoMedico dados) {
    if(dados.nome() != null){
      this.nome = dados.nome();
    }

    if(dados.telefone() != null){
      this.telefone = dados.telefone();
    }

    if(dados.endereco() != null){
      this.endereco.atualizarEndereco(dados.endereco());
    }
  }

  public void ativarDesativar(Boolean valor) {
    this.ativo = valor;
  }
}