package br.com.aweb.sistema_de_vendas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O produto é obrigatório")
    @ManyToOne
    @JoinColumn(name = "produto_id",nullable = false)
    private Produto produto;

    @NotNull(message = "Quantidade é obrigatória")
    private Integer quantidade;

    @NotNull(message = "O preço é obrigatório")
    private BigDecimal precoUnitario;

    // Construtor personalizado
    public ItemPedido(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();
    }
}
