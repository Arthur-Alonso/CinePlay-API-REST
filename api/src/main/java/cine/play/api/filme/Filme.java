package cine.play.api.filme;

import cine.play.api.filme.enums.*;
import cine.play.api.sessao.Sessao;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "filmes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private Integer duracao;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Enumerated(EnumType.STRING)
    private ClassificacaoEtaria classificacaoEtaria;

    @Column(length = 2000)
    private String sinopse;

    private Integer anoLancamento;

    @Enumerated(EnumType.STRING)
    private StatusExibicao statusExibicao;

    private Boolean ativo;

    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sessao> sessoes = new ArrayList<>();

    public Filme(DadosCadastroFilmes dados) {
        this.ativo = true;
        this.titulo = dados.titulo();
        this.duracao = dados.duracao();
        this.genero = dados.genero();
        this.classificacaoEtaria = dados.classificacaoEtaria();
        this.sinopse = dados.sinopse();
        this.anoLancamento = dados.anoLancamento();
        this.statusExibicao = dados.statusExibicao();
    }

    public void atualizarInformacoes(DadosAtualizacaoFilme dados) {
        if (dados.titulo() != null) this.setTitulo(dados.titulo());
        if (dados.duracao() != null) this.setDuracao(dados.duracao());
        if (dados.genero() != null) this.setGenero(dados.genero());
        if (dados.classificacaoEtaria() != null) this.setClassificacaoEtaria(dados.classificacaoEtaria());
        if (dados.sinopse() != null) this.setSinopse(dados.sinopse());
        if (dados.statusExibicao() != null) this.setStatusExibicao(dados.statusExibicao());
    }

    public void excluir() {
        this.ativo = false;
    }

    public void reativar() {
        this.ativo = true;
    }
}