package br.com.zup.mercadolivre.compra;

import br.com.zup.mercadolivre.pagamento.Pagamento;
import br.com.zup.mercadolivre.pagamento.PagamentoPagSeguroRequest;
import br.com.zup.mercadolivre.pagamento.PagamentoRequest;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String transacaoId = UUID.randomUUID().toString();
    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer quantidade;
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CompraStatus status = CompraStatus.INICIADA;
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CompraTipoPagamento tipoPagamento;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @NotNull
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<Pagamento> pagamentos = new ArrayList<>();

    @Deprecated
    public Compra(){
    }

    public Compra(@NotNull @Positive Integer quantidade,
                  @NotNull CompraTipoPagamento tipoPagamento,
                  @NotNull Produto produto,
                  @NotNull Usuario usuario) {
        this.quantidade = quantidade;
        this.tipoPagamento = tipoPagamento;
        this.produto = produto;
        this.usuario = usuario;
    }

    public String getTransacaoId() {
        return transacaoId;
    }

    public CompraStatus getStatus() {
        return status;
    }

    public CompraTipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void finalizarPagamento(PagamentoRequest request) {
        Pagamento pagamento = request.toPagamento(this);
        Assert.isTrue(!this.pagamentos.contains(pagamento), "Já existe um pagamento com essa transação");
        Assert.isTrue(pagamentosComSucesso().isEmpty(), "Essa compra já foi concluída com sucesso");

        this.pagamentos.add(pagamento);
    }

    public boolean finalizadaComSucesso() {
        return !pagamentosComSucesso().isEmpty();
    }

    private List<Pagamento> pagamentosComSucesso() {
        List<Pagamento> pagamentosComSucesso = this.pagamentos.stream().filter(Pagamento::concluidoComSucesso).collect(Collectors.toList());
        Assert.isTrue(pagamentosComSucesso.size() <= 1, "Possui mais de um pagamento com sucesso");
        return pagamentosComSucesso;
    }
}
