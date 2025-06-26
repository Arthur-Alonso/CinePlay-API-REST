package cine.play.api.filme;

import cine.play.api.filme.enums.*;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados necessários para cadastrar um novo filme")
public record DadosCadastroFilmes(

        @NotBlank
        @Schema(description = "Título do filme", example = "O Senhor dos Anéis: A Sociedade do Anel")
        String titulo,

        @NotNull @Min(1)
        @Schema(description = "Duração do filme em minutos", example = "178")
        Integer duracao,

        @NotBlank @Size(max = 2000)
        @Schema(description = "Sinopse do filme (máximo 2000 caracteres)", example = "Um grupo de heróis parte em uma jornada para destruir o Um Anel e derrotar Sauron.")
        String sinopse,

        @NotNull @Min(1900)
        @Schema(description = "Ano de lançamento do filme", example = "2001")
        Integer anoLancamento,

        @NotNull
        @Schema(description = "Gênero do filme", example = "AVENTURA")
        Genero genero,

        @NotNull
        @Schema(description = "Classificação etária do filme", example = "MAIOR_12")
        ClassificacaoEtaria classificacaoEtaria,

        @NotNull
        @Schema(description = "Status de exibição do filme", example = "EM_CARTAZ")
        StatusExibicao statusExibicao

) {}