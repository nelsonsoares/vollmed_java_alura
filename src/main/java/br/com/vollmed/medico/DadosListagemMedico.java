package br.com.vollmed.medico;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

  public DadosListagemMedico(ModelMedico medico){
    this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
  }
  
}
