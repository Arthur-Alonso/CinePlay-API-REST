package cine.play.api.filme;

import cine.play.api.sessao.Sessao;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "Dados de exibição combinando informações do filme com a sessão")
public record DadosBuscaFilme(

        @Schema(description = "Título do filme", example = "Vingadores: Ultimato")
        String titulo,

        @Schema(description = "Duração do filme em minutos", example = "181")
        Integer duracao,

        @Schema(description = "Classificação etária do filme (ex: LIVRE, MAIOR_14)", example = "MAIOR_14")
        String classificacaoEtaria,

        @Schema(description = "Gênero do filme", example = "AÇÃO")
        String genero,

        @Schema(description = "Data da sessão", example = "2025-07-10")
        LocalDate data,

        @Schema(description = "Horário de início da sessão", example = "19:00")
        LocalTime horarioInicio,

        @Schema(description = "Número de vagas disponíveis na sessão", example = "42")
        Integer vagasDisponiveis

) {
    public DadosBuscaFilme(Filme filme, Sessao sessao) {
        this(
                filme.getTitulo(),
                filme.getDuracao(),
                filme.getClassificacaoEtaria().getCodigo(),
                filme.getGenero().getDescricao(),
                sessao.getData(),
                sessao.getHorarioInicio(),
                sessao.getVagasDisponiveis()
        );
    }
}