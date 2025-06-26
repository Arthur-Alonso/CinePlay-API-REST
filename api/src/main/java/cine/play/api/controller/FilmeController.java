package cine.play.api.controller;

import cine.play.api.filme.DadosAtualizacaoFilme;
import cine.play.api.filme.DadosBuscaFilme;
import cine.play.api.filme.DadosCadastroFilmes;
import cine.play.api.filme.DadosListagemFilme;
import cine.play.api.service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/filmes")
@Tag(name = "Filmes", description = "Endpoints para gerenciamento de filmes")
public class FilmeController {

    @Autowired
    private FilmeService service;

    @PostMapping
    @Operation(summary = "Cadastrar um novo filme", responses = {
            @ApiResponse(responseCode = "200", description = "Filme cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<String> cadastrar(@RequestBody @Valid DadosCadastroFilmes dados) {
        service.cadastrarFilme(dados);
        return ResponseEntity.ok("Filme cadastrado com sucesso!");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desativar um filme (exclusão lógica)", responses = {
            @ApiResponse(responseCode = "200", description = "Filme desativada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrada", content = @Content)
    })
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        service.excluirFilme(id);
        return ResponseEntity.ok("Filme desativado com sucesso!");
    }

    @PutMapping("/{id}/reativar")
    @Operation(summary = "Reativar um filme previamente desativada", responses = {
            @ApiResponse(responseCode = "200", description = "Filme reativada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content)
    })
    public ResponseEntity<String> reativar(@PathVariable Long id) {
        service.reativarFilme(id);
        return ResponseEntity.ok("Filme reativado com sucesso!");
    }

    @GetMapping
    @Operation(summary = "Listar filmes", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de filmes retornada com sucesso")
    })
    public ResponseEntity<List<DadosListagemFilme>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar filme por titulo", responses = {
            @ApiResponse(responseCode = "200", description = "Filme encontradas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Filme não encontrada", content = @Content)
    })
    public ResponseEntity<List<DadosListagemFilme>> buscar(@RequestParam String titulo) {
        return ResponseEntity.ok(service.buscarPorTitulo(titulo));
    }

    @PutMapping
    @Operation(summary = "Atualizar informações do filme", responses = {
            @ApiResponse(responseCode = "200", description = "Filme atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content)
    })
    public ResponseEntity<String> atualizar(@RequestBody @Valid DadosAtualizacaoFilme dados) {
        service.atualizarFilme(dados);
        return ResponseEntity.ok("Filme atualizado com sucesso!");
    }
}