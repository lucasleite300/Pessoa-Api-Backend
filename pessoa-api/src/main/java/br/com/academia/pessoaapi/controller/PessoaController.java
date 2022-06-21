package br.com.academia.pessoaapi.controller;

import br.com.academia.pessoaapi.entity.Pessoa;
import br.com.academia.pessoaapi.repository.PessoaRepository;
import br.com.academia.pessoaapi.service.PessoaService;
import br.com.academia.pessoaapi.util.validation.CpfValid;
import br.com.academia.pessoaapi.util.validation.EmailValid;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@Validated
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("api/pessoa")
public class PessoaController {

    private final PessoaRepository pessoaRepository;
    private final PessoaService pessoaService;

    @ApiOperation(value = "Lista todas as pessoas")
    @GetMapping
    public ResponseEntity<Iterable<Pessoa>> listarTodos() {
        return new ResponseEntity<>(pessoaService.listarTodos(), HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.buscarPorId(id));
    }

    @ApiOperation(value = "Salva uma nova pessoa")
    @PostMapping
    public ResponseEntity<Pessoa> salvarNovo(@RequestBody @Valid Pessoa pessoa) throws ParseException {
        return ResponseEntity.ok(pessoaService.salvarPessoa(pessoa));
    }

    @ApiOperation(value = "Editar uma nova pessoa")
    @PostMapping("/{id}")
    public ResponseEntity<Pessoa> alterar(@PathVariable Long id, @RequestBody @Valid Pessoa jsonPessoa){
        return ResponseEntity.ok(pessoaService.atualizarPessoa(id,jsonPessoa));
    }

    @ApiOperation(value = "Excluir todos os registros cadastrados")
    @DeleteMapping("/excluir-todos")
    public void deletarRegistros() {
        pessoaService.deleteAll();
    }

    @ApiOperation(value = "Excluir uma pessoa selecionada por CPF")
    @DeleteMapping("/{id}")
    public void deletarPorCpf(@PathVariable @CpfValid String cpf) {
        pessoaService.deletePessoa(cpf);
    }

    @ApiOperation(value = "Deligar/cancelar uma pessoa")
    @PostMapping("/desligar/{id}")
    public ResponseEntity<Pessoa> desligarPessoa(@PathVariable Long id){
        return ResponseEntity.ok(pessoaService.desligarPessoa(id));
    }

}
