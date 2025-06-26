package cine.play.api.sessao.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Tipo de exibição da sessão")
public enum TipoExibicao {

    @Schema(description = "Exibição em 2D")
    D2("2D"),

    @Schema(description = "Exibição em 3D")
    D3("3D"),

    @Schema(description = "Exibição IMAX")
    IMAX("IMAX");

    private final String descricao;

    TipoExibicao(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TipoExibicao fromDescricao(String descricao) {
        for (TipoExibicao tipo : values()) {
            if (tipo.descricao.equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de exibição inválido: " + descricao);
    }
}