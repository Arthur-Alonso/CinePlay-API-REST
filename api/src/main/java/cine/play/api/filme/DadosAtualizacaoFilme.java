package cine.play.api.filme;

import jakarta.validation.constraints.*;
import cine.play.api.filme.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados utilizados para atualizar um filme existente")
public record DadosAtualizacaoFilme(

        @NotNull
        @Schema(description = "ID do filme a ser atualizado", example = "1")
        Long id,

        @Schema(description = "Novo título do filme", example = "Matrix Reloaded")
        String titulo,

        @Min(1)
        @Schema(description = "Duração do filme em minutos", example = "136")
        Integer duracao,

        @Size(max = 2000)
        @Schema(description = "Sinopse do filme (máximo 2000 caracteres)", example = "Neo descobre mais segredos da Matrix enquanto luta contra um novo inimigo.")
        String sinopse,

        @Schema(description = "Gênero do filme", example = "ACAO")
        Genero genero,

        @Schema(description = "Classificação etária do filme", example = "MAIOR_14")
        ClassificacaoEtaria classificacaoEtaria,

        @Schema(description = "Status atual de exibição do filme", example = "EM_CARTAZ")
        StatusExibicao statusExibicao

) {}
