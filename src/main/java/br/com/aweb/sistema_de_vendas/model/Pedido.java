package br.com.aweb.sistema_de_vendas.model;

import br.com.aweb.sistema_de_vendas.model.enums.StatusPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome é obrigatório")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotNull(message = "A data do pedido é obrigatória")
    @Column(nullable = false, name = "data_pedido")
    private LocalDateTime dataPedido;

    @NotNull(message = "O valor total do pedido é obrigatório")
    @Column(nullable = false, name = "valor_total")
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private StatusPedido status = StatusPedido.ATIVO;

    @NotNull(message = "Cliente é obrigatório")
    @ManyToOne
    @JoinColumn(name = "cliente_id",nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    @Version
    private Long version;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }
}
