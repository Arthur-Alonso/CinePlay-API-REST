package cine.play.api.sessao;
import cine.play.api.sessao.enums.TipoExibicao;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Schema(name = "DadosAtualizacaoSessao", description = "Dados para atualizar uma sessão")
public record DadosAtualizacaoSessao(

        @NotNull
        @Schema(description = "ID da sessão", example = "1")
        Long id,

        @Schema(description = "Data da sessão", example = "2025-07-01")
        LocalDate data,

        @Schema(description = "Horário de início da sessão", example = "19:30:00")
        LocalTime horarioInicio,

        @Schema(description = "Nome ou número da sala", example = "Sala 3")
        String sala,

        @DecimalMin("0.00")
        @Schema(description = "Preço da sessão", example = "25.00")
        BigDecimal preco,

        @Schema(description = "Tipo de exibição da sessão", example = "D2")
        TipoExibicao tipoExibicao,

        @Min(1)
        @Schema(description = "Total de vagas na sessão", example = "100")
        Integer vagasTotais,

        @Min(1)
        @Schema(description = "Quantidade de vagas disponíveis", example = "50")
        Integer vagasDisponiveis

) {}