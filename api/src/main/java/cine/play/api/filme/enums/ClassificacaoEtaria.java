package cine.play.api.filme.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Classificação etária do filme")
public enum ClassificacaoEtaria {

    @Schema(description = "Livre para todos os públicos")
    LIVRE("L"),

    @Schema(description = "Não recomendado para menores de 10 anos")
    DEZ("10"),

    @Schema(description = "Não recomendado para menores de 12 anos")
    DOZE("12"),

    @Schema(description = "Não recomendado para menores de 14 anos")
    QUATORZE("14"),

    @Schema(description = "Não recomendado para menores de 16 anos")
    DEZESSEIS("16"),

    @Schema(description = "Não recomendado para menores de 18 anos")
    DEZOITO("18");

    private final String codigo;

    ClassificacaoEtaria(String codigo) {
        this.codigo = codigo;
    }

    @JsonValue
    public String getCodigo() {
        return codigo;
    }

    @JsonCreator
    public static ClassificacaoEtaria fromCodigo(String codigo) {
        for (ClassificacaoEtaria c : values()) {
            if (c.codigo.equalsIgnoreCase(codigo)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Código inválido de classificação etária: " + codigo);
    }
}