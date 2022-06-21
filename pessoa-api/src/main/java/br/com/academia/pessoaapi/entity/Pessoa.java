package br.com.academia.pessoaapi.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.academia.pessoaapi.util.validation.CpfValid;
import br.com.academia.pessoaapi.util.validation.EmailValid;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "PESSOA")
public class Pessoa {

    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column(name =  "ID")
    Long id;

    @NotNull(message = "O campo é obrigatório")
    @NotEmpty(message = "O campo não pode ficar vazio")
    @Column(name =  "NOME", nullable = false)
    String nome;

    @NotNull (message = "O campo é obrigatório")
    @NotEmpty(message = "O campo não pode ficar vazio")
    @Column(name =  "SOBRENOME", nullable = false)
    String sobrenome;

    @NotNull(message = "O campo é obrigatório")
    @NotEmpty(message = "O campo não pode ficar vazio")
    @CpfValid
    @Column(name =  "CPF", nullable = false)
    String cpf;

    @Column(name =  "DATA_NASCIMENTO", nullable = false)
    Date dataNascimento;

    @Column(name =  "MATRICULA", nullable = false)
    String matricula;

    @Column(name =  "DIA_PAGAMENTO", nullable = false)
    Integer diaPagamento;

    @Column(name =  "DATA_CADASTRO", nullable = false)
    Date dataCadastro;

    @Column(name =  "DATA_DESLIGAMENTO", nullable = false)
    Date dataDesligamento;

    @Column(name =  "DATA_CANCELAMENTO")
    Date dataCancelamento;

    @Column(name =  "ENDERECO")
    String endereco;

    @NotNull(message = "O campo é obrigatório")
    @NotEmpty(message = "O campo não pode ficar vazio")
    @EmailValid
    @Column(name =  "EMAIL", nullable = false)
    String email;

    @Column(name =  "TELEFONE")
    String telefone;

    @NotNull(message = "O campo é obrigatório")
    @NotEmpty(message = "O campo não pode ficar vazio")
    @Column(name =  "TIPO_PESSOA", nullable = false)
    String tipoPessoa;

}
