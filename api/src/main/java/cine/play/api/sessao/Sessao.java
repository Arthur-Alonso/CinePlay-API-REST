package cine.play.api.sessao;
import cine.play.api.filme.Filme;
import cine.play.api.sessao.enums.TipoExibicao;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "sessoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    private LocalTime horarioInicio;

    private String sala;

    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private TipoExibicao tipoExibicao;

    private Integer vagasDisponiveis;

    private Integer vagasTotais;

    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "filme_id")
    private Filme filme;

    public Sessao(DadosCadastroSessao dados, Filme filme) {
        this.ativo= true;
        this.data = dados.data();
        this.horarioInicio = dados.horarioInicio();
        this.sala = dados.sala();
        this.preco = dados.preco();
        this.tipoExibicao = dados.tipoExibicao();
        this.vagasTotais = dados.vagasTotais();
        this.vagasDisponiveis = dados.vagasTotais();
        this.filme = filme;
    }

    public String atualizarInformacoes(DadosAtualizacaoSessao dados) {
        boolean temVendas = this.vagasTotais > this.vagasDisponiveis;

        if (temVendas) {
            int ingressosVendidos = this.vagasTotais - this.vagasDisponiveis;

            if (dados.data() != null || dados.horarioInicio() != null || dados.preco() != null || dados.vagasTotais() != null) {
                throw new RuntimeException("Essa sessão já possui ingressos vendidos. Só é permitido alterar a sala e o tipo de exibição.");
            }

            if (dados.sala() != null) this.sala = dados.sala();
            if (dados.tipoExibicao() != null) this.tipoExibicao = dados.tipoExibicao();

            return "Alterado sala e tipo de exibição, mas já existiam " + ingressosVendidos + " ingressos vendidos.";

        } else {
            if (dados.data() != null) this.data = dados.data();
            if (dados.horarioInicio() != null) this.horarioInicio = dados.horarioInicio();
            if (dados.preco() != null) this.preco = dados.preco();
            if (dados.sala() != null) this.sala = dados.sala();
            if (dados.tipoExibicao() != null) this.tipoExibicao = dados.tipoExibicao();
            if (dados.vagasTotais() != null) {
                this.vagasTotais = dados.vagasTotais();
                this.vagasDisponiveis = dados.vagasTotais();
            }

            return "Sessão atualizada com sucesso.";
        }
    }

    public void excluir() {
        this.ativo = false;
    }

    public void reativar() {
        this.ativo = true;
    }
}