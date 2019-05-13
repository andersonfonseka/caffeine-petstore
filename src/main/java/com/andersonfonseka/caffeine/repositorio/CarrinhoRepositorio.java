package com.andersonfonseka.caffeine.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;

import com.andersonfonseka.caffeine.dominio.Produto;

@Singleton
public class CarrinhoRepositorio {
	
	private static int contador = 1;
	
	private List<Produto> produtos = new ArrayList<Produto>();
	
	public CarrinhoRepositorio() {}{}
	
	public void adicionar(Produto produto) {
		produto.setId(contador);
		this.produtos.add(produto);
		contador++;
	}
	
	public Produto obterProduto(String id) {
		
		Produto produtoResultado = null;
		
		Optional<Produto> produtoBusca = produtos.stream().filter(x -> x.getId().equals(Integer.valueOf(id))).findFirst();
		
		if (produtoBusca.isPresent()) {
			produtoResultado = produtoBusca.get();
		}
		
		return produtoResultado;
	}
	
	public void remover(Produto produto) {
		
		Integer pos = this.produtos.indexOf(produto);
		
		if (pos != -1) {
			this.produtos.remove(produto);	
		}
		
	}

	public List<Produto> getProdutos() {
		return this.produtos;
	}
	
	public int  getTotalProdutos() {
		return this.produtos.size();
	}
	
	
}
