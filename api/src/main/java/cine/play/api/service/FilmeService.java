package cine.play.api.service;
import cine.play.api.filme.*;
import cine.play.api.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository repository;

    @Transactional
    public void cadastrarFilme(DadosCadastroFilmes dados) {
        Filme filme = new Filme(dados);
        repository.save(filme);
    }

    @Transactional
    public void excluirFilme(Long id) {
        Filme filme = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        filme.excluir();
    }

    @Transactional
    public void reativarFilme(Long id) {
        Filme filme = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        if (filme.getAtivo()) {
            throw new RuntimeException("Filme já está ativo");
        }

        filme.reativar();
    }

    public List<DadosListagemFilme> listar() {
        return repository.findAllByAtivoTrue().stream()
                .map(DadosListagemFilme::new)
                .toList();
    }

    public List<DadosListagemFilme> buscarPorTitulo(String titulo) {
        return repository.findByTituloContainingIgnoreCaseAndAtivoTrue(titulo).stream()
                .map(DadosListagemFilme::new)
                .toList();
    }

    @Transactional
    public void atualizarFilme(DadosAtualizacaoFilme dados) {
        Filme filme = repository.getReferenceById(dados.id());
        filme.atualizarInformacoes(dados);
    }
}
