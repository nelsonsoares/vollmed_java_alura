package br.com.vollmed.medico;

import br.com.vollmed.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
  
  @NotNull
  Long id, 

  String nome, 
  String telefone, 
  DadosEndereco endereco) {
}
