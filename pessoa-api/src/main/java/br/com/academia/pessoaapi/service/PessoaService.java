package br.com.academia.pessoaapi.service;

import br.com.academia.pessoaapi.entity.Pessoa;
import br.com.academia.pessoaapi.exception.BadRequestException;
import br.com.academia.pessoaapi.model.TipoPessoa;
import br.com.academia.pessoaapi.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    public List<Pessoa> listarTodos(){
        return pessoaRepository.buscarTodos();
    };

    public Pessoa buscarPorId(Long id){
        return pessoaRepository.findById(id).orElseThrow(()-> new BadRequestException("Registro de pessoa não encontrado"));
    }

    public Pessoa salvarPessoa(Pessoa pessoa){

        if(pessoa.getTipoPessoa().equals("A")){
            pessoa.setTipoPessoa(TipoPessoa.ALUNO.getTipoPessoa());
        }else if (pessoa.getTipoPessoa().equals("P")){
            pessoa.setTipoPessoa(TipoPessoa.PERSONAL.getTipoPessoa());
        }else if (pessoa.getTipoPessoa().equals("R")){
            pessoa.setTipoPessoa(TipoPessoa.RECEPCIONISTA.getTipoPessoa());
        }else{
            throw new BadRequestException("O campo só aceita 'A', 'P' e 'R'.");
        }

        //Datas de hoje
        Date datehoje = new Date();
        LocalDate localDateHoje = LocalDate.now();
        LocalDate localDateDataNasicmento = pessoa.getDataNascimento().toInstant().atZone(ZoneId.systemDefault() ).toLocalDate();

        //Gerador de matrícula do sistema
        pessoa.setDataCadastro(datehoje);
        char letra = pessoa.getTipoPessoa().charAt(0);
        Random gerador = new Random();
        pessoa.setMatricula(letra + "" + localDateHoje.getYear() + "" + gerador.nextInt(5000) + 1);

        //Validações
        List<Pessoa> existe = pessoaRepository.findByCpf(pessoa.getCpf());
        if (existe.size() > 0)
            throw new BadRequestException("Já existe um cadastro com esse cpf!");

        if (pessoa.getDiaPagamento() > 31)
            throw new BadRequestException("O dia de pagamento só pode ser até dia 31.");

        int idadePessoa = localDateHoje.getYear() - localDateDataNasicmento.getYear();
        if(idadePessoa < 18){
            throw new BadRequestException("Não é possivel cadastrar um menor de idade no sistema.");
        }

        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizarPessoa(Long id, Pessoa pessoa){

        Pessoa findPessoa = buscarPorId(id);
        findPessoa.setCpf(pessoa.getCpf());
        findPessoa.setEmail(pessoa.getEmail());
        findPessoa.setDataCancelamento(pessoa.getDataCancelamento());
        findPessoa.setNome(pessoa.getNome());
        findPessoa.setSobrenome(pessoa.getSobrenome());
        findPessoa.setDataNascimento(pessoa.getDataNascimento());
        findPessoa.setDiaPagamento(pessoa.getDiaPagamento());
        findPessoa.setEndereco(pessoa.getEndereco());
        findPessoa.setTelefone(pessoa.getTelefone());

        if(pessoa.getTipoPessoa().equals("A")){
            findPessoa.setTipoPessoa(TipoPessoa.ALUNO.getTipoPessoa());
        }else if (pessoa.getTipoPessoa().equals("P")){
            findPessoa.setTipoPessoa(TipoPessoa.PERSONAL.getTipoPessoa());
        }else if (pessoa.getTipoPessoa().equals("R")){
            findPessoa.setTipoPessoa(TipoPessoa.RECEPCIONISTA.getTipoPessoa());
        }else{
            throw new BadRequestException("O campo só aceita 'A', 'P' e 'R'.");
        }

        //Datas de hoje
        Date datehoje = new Date();
        LocalDate localDateHoje = LocalDate.now();
        LocalDate localDateDataNasicmento = pessoa.getDataNascimento().toInstant().atZone(ZoneId.systemDefault() ).toLocalDate();

        //Validações
        List<Pessoa> existe = pessoaRepository.findByCpf(findPessoa.getCpf());
        if (existe.size() > 1)
            throw new BadRequestException("Já existe um cadastro com esse cpf!");

        if (pessoa.getDiaPagamento() > 31)
            throw new BadRequestException("O dia de pagamento só pode ser até dia 31.");

        int idadePessoa = localDateHoje.getYear() - localDateDataNasicmento.getYear();
        if(idadePessoa < 18){
            throw new BadRequestException("Não é possivel cadastrar um menor de idade no sistema.");
        }

        return pessoaRepository.save(findPessoa);
    }

    public Pessoa desligarPessoa(Long id){

        Pessoa pessoa = buscarPorId(id);
        if(pessoa.getTipoPessoa() == TipoPessoa.ALUNO.getTipoPessoa()){
            pessoa.setDataCancelamento(new Date());
        }else{
            pessoa.setDataDesligamento(new Date());
        }
        return pessoaRepository.save(pessoa);
    }
    
    public void deletePessoa(String cpf){
        List<Pessoa> pessoa = pessoaRepository.findByCpf(cpf);
        pessoaRepository.delete(pessoa.get(0));
    }

    public void deleteAll (){
        pessoaRepository.deleteAll();
    }

}
