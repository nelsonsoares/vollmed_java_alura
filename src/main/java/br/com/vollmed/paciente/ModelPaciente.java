package br.com.vollmed.paciente;

import br.com.vollmed.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="Paciente")
@Table(name="pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class ModelPaciente {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String nome;
  private String email;
  private String telefone;
  private String cpf;
  
  @Embedded
  private Endereco endereco;

  private Boolean ativo;
  
  public ModelPaciente(DadosCadastroPaciente dados) {
    this.nome = dados.nome();
    this.email = dados.email();
    this.telefone = dados.telefone();
    this.cpf = dados.cpf();
    this.endereco = new Endereco(dados.endereco());
    this.ativo = true;
  }

  public void atualizarDadosPaciente(DadosAtualizacaoPaciente dados) {
    if(dados.nome() != null){
      this.nome = dados.nome();
    }

    if(dados.email() != null){
      this.email = dados.email();
    }

    if(dados.telefone() != null){
      this.telefone = dados.telefone();
    }

    if(dados.cpf() != null){
      this.cpf = dados.cpf();
    }

    if(dados.endereco() != null){
      this.endereco.atualizarEndereco(dados.endereco());
    }
  }

  public void ativarDesativar(Boolean valor) {
    this.ativo = valor;
  }
}
