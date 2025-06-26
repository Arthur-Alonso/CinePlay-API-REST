package cine.play.api.service;

import cine.play.api.sessao.*;
import cine.play.api.repository.SessaoRepository;
import cine.play.api.filme.Filme;
import cine.play.api.repository.FilmeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Transactional
    public void cadastrarSessao(DadosCadastroSessao dados) {
        Filme filme = filmeRepository.findById(dados.idFilme())
                .orElseThrow(() -> new IllegalArgumentException("Filme não encontrado com ID: " + dados.idFilme()));
        Sessao sessao = new Sessao(dados, filme);
        sessaoRepository.save(sessao);
    }

    @Transactional
    public void excluirSessao(Long id) {
        Sessao sessao = sessaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        sessao.excluir();
    }

    @Transactional
    public void reativarSessao(Long id) {
        Sessao sessao = sessaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        if (sessao.getAtivo()) {
            throw new RuntimeException("A sessão já está ativa.");
        }

        sessao.reativar();
    }

    public List<DadosListagemSessao> listarSessoes() {
        return sessaoRepository.findAllByAtivoTrue().stream()
                .map(DadosListagemSessao::new)
                .toList();
    }

    public List<DadosBuscaSessao> buscarPorData(LocalDate data) {
        return sessaoRepository.findByDataAndAtivoTrue(data).stream().map(DadosBuscaSessao::new).toList();
    }

    @Transactional
    public Sessao comprarVagas(Long idSessao, int quantidade) {
        Sessao sessao = sessaoRepository.findById(idSessao).orElseThrow(() -> new RuntimeException("Sessão não encontrada"));
        if (sessao.getVagasDisponiveis() < quantidade) {
            throw new RuntimeException("Vagas insuficientes");
        }
        sessao.setVagasDisponiveis(sessao.getVagasDisponiveis() - quantidade);
        return sessao;
    }

    @Transactional
    public String atualizarSessao(DadosAtualizacaoSessao dados) {
        Sessao sessao = sessaoRepository.findById(dados.id())
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        return sessao.atualizarInformacoes(dados);
    }

}