package br.com.academia.pessoaapi.repository;

import br.com.academia.pessoaapi.entity.Pessoa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

    @Query(value = "SELECT * FROM PESSOA WHERE DATA_CANCELAMENTO IS NULL ORDER BY NOME ASC ", nativeQuery = true)
    List<Pessoa> buscarTodos();

    @Query(value = "SELECT * FROM PESSOA WHERE CPF = ?1" , nativeQuery = true)
    List<Pessoa> findByCpf(String text);
}