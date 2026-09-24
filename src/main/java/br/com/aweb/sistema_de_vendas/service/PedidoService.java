package br.com.aweb.sistema_de_vendas.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import br.com.aweb.sistema_de_vendas.model.Cliente;
import br.com.aweb.sistema_de_vendas.model.ItemPedido;
import br.com.aweb.sistema_de_vendas.model.Pedido;
import br.com.aweb.sistema_de_vendas.model.Produto;
import br.com.aweb.sistema_de_vendas.model.enums.StatusPedido;
import br.com.aweb.sistema_de_vendas.repository.PedidoRepository;
import br.com.aweb.sistema_de_vendas.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Transactional
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    // CREATE - Criar novo pedido
    @Transactional
    public Pedido criarPedido(Cliente cliente) {
        Pedido pedido = new Pedido(cliente);
        return pedidoRepository.save(pedido);
    }

    // Lista todos os pedidos
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    // Calcular valor total do pedido
    private void calcularValorTotal(Pedido pedido) {
        BigDecimal total = BigDecimal.ZERO;

        for (ItemPedido item : pedido.getItens()) {
            BigDecimal valorItem = item.getPrecoUnitario()
                    .multiply(BigDecimal.valueOf(item.getQuantidade()));
            total = total.add(valorItem);
        }

        pedido.setValorTotal(total);
    }

    // Adicionar ITEM ao pedido
    @Transactional
    public void adicionarItem(Long pedidoId, Long produtoId, Integer quantidade) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(pedidoId);
        Optional<Produto> optionalProduto = produtoRepository.findById(produtoId);

        if (!optionalPedido.isPresent()) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }

        if (!optionalPedido.isPresent()) {
            throw new IllegalArgumentException("Produto não encontrado");
        }

        Pedido pedido = optionalPedido.get();
        Produto produto = optionalProduto.get();

        if (pedido.getStatus() != StatusPedido.CANCELADO) {
            throw new IllegalStateException("Não é possível adicionar itens a um pedido cancelado");
        }

        if (produto.getQuantidadeEmEstoque() < quantidade) {
            throw new IllegalStateException("Estoque insuficiente para o produto: " + produto.getNome());
        }

        ItemPedido item = new ItemPedido(produto, quantidade);
        item.setPedido(pedido);

        pedido.getItens().add(item);

        produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - quantidade);

        calcularValorTotal(pedido);

        pedidoRepository.save(pedido);
        produtoRepository.save(produto);
    }

}
