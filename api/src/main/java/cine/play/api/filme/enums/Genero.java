package cine.play.api.filme.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Gênero do filme")
public enum Genero {

    @Schema(description = "Filme de ação")
    ACAO("ação"),

    @Schema(description = "Filme de comédia")
    COMEDIA("comédia"),

    @Schema(description = "Filme de drama")
    DRAMA("drama"),

    @Schema(description = "Filme de ficção científica")
    FICCAO("ficção"),

    @Schema(description = "Filme de terror")
    TERROR("terror");

    private final String descricao;

    Genero(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static Genero fromDescricao(String descricao) {
        for (Genero g : values()) {
            if (g.descricao.equalsIgnoreCase(descricao)) {
                return g;
            }
        }
        throw new IllegalArgumentException("Gênero inválido: " + descricao);
    }
}