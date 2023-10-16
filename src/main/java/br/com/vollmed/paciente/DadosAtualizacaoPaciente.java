package br.com.vollmed.paciente;

import br.com.vollmed.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(

  @NotNull
  Long id,
  
  String nome, 
  String email, 
  String telefone, 
  String cpf, 
  DadosEndereco endereco) {
}
