package cine.play.api.filme;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados na listagem de filmes")
public record DadosListagemFilme(

        @Schema(description = "ID do filme", example = "1")
        Long id,

        @Schema(description = "Título do filme", example = "Interstellar")
        String titulo,

        @Schema(description = "Duração do filme em minutos", example = "169")
        Integer duracao,

        @Schema(description = "Gênero do filme", example = "FICÇÃO CIENTÍFICA")
        String genero,

        @Schema(description = "Classificação etária", example = "MAIOR_12")
        String classificacaoEtaria,

        @Schema(description = "Sinopse do filme", example = "Uma equipe de astronautas viaja por um buraco de minhoca em busca de um novo lar para a humanidade.")
        String sinopse,

        @Schema(description = "Ano de lançamento do filme", example = "2014")
        Integer anoLancamento,

        @Schema(description = "Status de exibição", example = "EM_CARTAZ")
        String statusExibicao

) {
    public DadosListagemFilme(Filme filme) {
        this(
                filme.getId(),
                filme.getTitulo(),
                filme.getDuracao(),
                filme.getGenero().getDescricao(),
                filme.getClassificacaoEtaria().getCodigo(),
                filme.getSinopse(),
                filme.getAnoLancamento(),
                filme.getStatusExibicao().getDescricao()
        );
    }
}