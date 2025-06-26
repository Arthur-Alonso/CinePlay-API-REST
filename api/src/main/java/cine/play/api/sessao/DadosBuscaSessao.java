package cine.play.api.sessao;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Schema(name = "DadosBuscaSessao", description = "Dados para exibição das sessões buscadas")
public record DadosBuscaSessao(

        @Schema(description = "Data da sessão", example = "2025-07-01")
        LocalDate data,

        @Schema(description = "Horário de início da sessão", example = "19:30:00")
        LocalTime horarioInicio,

        @Schema(description = "Preço da sessão", example = "25.00")
        BigDecimal preco,

        @Schema(description = "Quantidade de vagas disponíveis", example = "50")
        Integer vagasDisponiveis,

        @Schema(description = "Título do filme associado", example = "Matrix")
        String tituloFilme,

        @Schema(description = "Duração do filme em minutos", example = "136")
        Integer duracaoFilme

) {
    public DadosBuscaSessao(Sessao sessao) {
        this(
                sessao.getData(),
                sessao.getHorarioInicio(),
                sessao.getPreco(),
                sessao.getVagasDisponiveis(),
                sessao.getFilme().getTitulo(),
                sessao.getFilme().getDuracao()
        );
    }
}