package cine.play.api.filme.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Status atual de exibição do filme")
public enum StatusExibicao {

    @Schema(description = "Filme atualmente em cartaz")
    EM_CARTAZ("Em Cartaz"),

    @Schema(description = "Filme que será exibido em breve")
    EM_BREVE("Em Breve"),

    @Schema(description = "Filme que já saiu de cartaz")
    FORA_DE_CARTAZ("Fora de Cartaz"),

    @Schema(description = "Exibição do filme foi suspensa")
    SUSPENSO("Suspenso");

    private final String descricao;

    StatusExibicao(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static StatusExibicao fromDescricao(String descricao) {
        for (StatusExibicao status : values()) {
            if (status.descricao.equalsIgnoreCase(descricao)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status de exibição inválido: " + descricao);
    }
}