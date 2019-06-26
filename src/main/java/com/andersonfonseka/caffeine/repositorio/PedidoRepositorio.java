package com.andersonfonseka.caffeine.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;

import com.andersonfonseka.caffeine.dominio.Pedido;

@Singleton
public class PedidoRepositorio {
	
	private static int contador = 1;
	
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	
	public PedidoRepositorio() {}{}
	
	public void adicionar(Pedido pedido) {
		pedido.setId(contador);
		this.pedidos.add(pedido);
		contador++;
	}
	
	public Pedido obterPedido(String id) {
		
		Pedido produtoResultado = null;
		
		Optional<Pedido> produtoBusca = pedidos.stream().filter(x -> x.getId().equals(Integer.valueOf(id))).findFirst();
		
		if (produtoBusca.isPresent()) {
			produtoResultado = produtoBusca.get();
		}
		
		return produtoResultado;
	}
	
	public List<Pedido> getPedidos() {
		return this.pedidos;
	}
	
}
