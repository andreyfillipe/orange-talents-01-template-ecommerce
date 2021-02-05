package br.com.zup.mercadolivre.pagamento;

import br.com.zup.mercadolivre.compra.Compra;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String pagamentoId;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamento status;
    @NotNull
    @Column(nullable = false)
    private LocalDateTime dataPagamento = LocalDateTime.now();
    @NotNull
    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    @Deprecated
    public Pagamento() {
    }

    public Pagamento(@NotBlank String pagamentoId, @NotNull StatusPagamento status, @NotNull Compra compra) {
        this.pagamentoId = pagamentoId;
        this.status = status;
        this.compra = compra;
    }

    public boolean concluidoComSucesso() {
        return this.status.equals(StatusPagamento.SUCESSO);
    }
}
