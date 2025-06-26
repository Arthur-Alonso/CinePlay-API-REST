package cine.play.api.sessao;

import cine.play.api.filme.Filme;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record DadosListagemSessao(
        Long id,
        LocalDate data,
        LocalTime horarioInicio,
        String sala,
        BigDecimal preco,
        String tipoExibicao,
        Integer vagasDisponiveis,
        String tituloFilme
) {
    public DadosListagemSessao(Sessao sessao) {
        this(
                sessao.getId(),
                sessao.getData(),
                sessao.getHorarioInicio(),
                sessao.getSala(),
                sessao.getPreco(),
                sessao.getTipoExibicao().getDescricao(),
                sessao.getVagasDisponiveis(),
                sessao.getFilme().getTitulo()
        );
    }
}