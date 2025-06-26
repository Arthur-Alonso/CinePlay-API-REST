package cine.play.api.sessao;
import cine.play.api.sessao.enums.TipoExibicao;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Schema(name = "DadosCadastroSessao", description = "Dados para cadastro de uma nova sessão")
public record DadosCadastroSessao(

        @NotNull
        @Schema(description = "Data da sessão", example = "2025-07-01")
        LocalDate data,

        @NotNull
        @Schema(description = "Horário de início da sessão", example = "19:30:00")
        LocalTime horarioInicio,

        @NotBlank
        @Schema(description = "Nome ou número da sala", example = "Sala 3")
        String sala,

        @NotNull
        @DecimalMin("0.00")
        @Schema(description = "Preço da sessão", example = "25.00")
        BigDecimal preco,

        @NotNull
        @Schema(description = "Tipo de exibição da sessão", example = "D2")
        TipoExibicao tipoExibicao,

        @NotNull
        @Min(1)
        @Schema(description = "Quantidade total de vagas na sessão", example = "100")
        Integer vagasTotais,

        @NotNull
        @Schema(description = "ID do filme associado à sessão", example = "1")
        Long idFilme

) {}
