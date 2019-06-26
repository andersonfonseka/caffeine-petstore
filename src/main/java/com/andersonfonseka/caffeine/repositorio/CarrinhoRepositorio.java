package com.andersonfonseka.caffeine.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;

import com.andersonfonseka.caffeine.dominio.ItemProduto;
import com.andersonfonseka.caffeine.dominio.Produto;

@Singleton
public class CarrinhoRepositorio {
	
	private static int contador = 1;
	
	private List<ItemProduto> produtos = new ArrayList<ItemProduto>();
	
	public CarrinhoRepositorio() {}{}
	
	public void adicionar(ItemProduto produto) {
		produto.setId(contador);
		this.produtos.add(produto);
		contador++;
	}
	
	public ItemProduto obterProduto(String id) {
		
		ItemProduto produtoResultado = null;
		
		Optional<ItemProduto> produtoBusca = produtos.stream().filter(x -> x.getId().equals(Integer.valueOf(id))).findFirst();
		
		if (produtoBusca.isPresent()) {
			produtoResultado = produtoBusca.get();
		}
		
		return produtoResultado;
	}
	
	public void remover(ItemProduto produto) {
		
		Integer pos = this.produtos.indexOf(produto);
		
		if (pos != -1) {
			this.produtos.remove(produto);	
		}
		
	}

	public List<ItemProduto> getProdutos() {
		return this.produtos;
	}
	
	public int  getTotalProdutos() {
		return this.produtos.size();
	}
	
	
}
