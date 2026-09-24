package br.com.aweb.sistema_de_vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aweb.sistema_de_vendas.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
