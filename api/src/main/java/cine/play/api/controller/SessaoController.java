package cine.play.api.controller;

import cine.play.api.sessao.*;
import cine.play.api.service.SessaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sessoes")
@Tag(name = "Sessões", description = "Endpoints para gerenciamento de sessões de filmes")
public class SessaoController {

    @Autowired
    private SessaoService service;

    @PostMapping
    @Operation(summary = "Cadastrar uma nova sessão", responses = {
            @ApiResponse(responseCode = "200", description = "Sessão cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<String> cadastrar(@RequestBody @Valid DadosCadastroSessao dados) {
        service.cadastrarSessao(dados);
        return ResponseEntity.ok("Sessão cadastrada com sucesso!");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desativar uma sessão (exclusão lógica)", responses = {
            @ApiResponse(responseCode = "200", description = "Sessão desativada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sessão não encontrada", content = @Content)
    })
    public ResponseEntity<String> desativar(@PathVariable Long id) {
        service.excluirSessao(id);
        return ResponseEntity.ok("Sessão desativada com sucesso!");
    }

    @PutMapping("/{id}/reativar")
    @Operation(summary = "Reativar uma sessão previamente desativada", responses = {
            @ApiResponse(responseCode = "200", description = "Sessão reativada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sessão não encontrada", content = @Content)
    })
    public ResponseEntity<String> reativarSessao(@PathVariable Long id) {
        service.reativarSessao(id);
        return ResponseEntity.ok("Sessão reativada com sucesso!");
    }

    @GetMapping
    @Operation(summary = "Listar sessões", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de sessões retornada com sucesso")
    })
    public ResponseEntity<List<DadosListagemSessao>> listar() {
        return ResponseEntity.ok(service.listarSessoes());
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar sessões por data", responses = {
            @ApiResponse(responseCode = "200", description = "Sessões encontradas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Formato de data inválido", content = @Content)
    })
    public ResponseEntity<List<DadosBuscaSessao>> buscarPorData(
            @Parameter(description = "Data no formato ISO (yyyy-MM-dd)")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<DadosBuscaSessao> sessoes = service.buscarPorData(data);
        return ResponseEntity.ok(sessoes);
    }

    @PutMapping("/{id}/comprar")
    @Operation(summary = "Comprar vagas para uma sessão", responses = {
            @ApiResponse(responseCode = "200", description = "Compra realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sessão não encontrada", content = @Content)
    })
    public ResponseEntity<String> comprarVagas(
            @PathVariable Long id,
            @Parameter(description = "Quantidade de vagas a serem compradas")
            @RequestParam int quantidade) {

        Sessao sessao = service.comprarVagas(id, quantidade);

        String mensagem = String.format(
                "Compra realizada com sucesso! Filme '%s' na data %s às %s na %s. Quantidade de vagas compradas: %d.",
                sessao.getFilme().getTitulo(),
                sessao.getData(),
                sessao.getHorarioInicio(),
                sessao.getSala(),
                quantidade
        );

        return ResponseEntity.ok(mensagem);
    }

    @PutMapping
    @Operation(summary = "Atualizar informações de uma sessão", responses = {
            @ApiResponse(responseCode = "200", description = "Sessão atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Sessão não encontrada", content = @Content)
    })
    public ResponseEntity<String> atualizar(@RequestBody @Valid DadosAtualizacaoSessao dados) {
        String mensagem = service.atualizarSessao(dados);
        return ResponseEntity.ok(mensagem);
    }

}
