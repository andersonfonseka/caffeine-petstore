package com.andersonfonseka.caffeine.dominio;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	
	private Integer id;
	
	private Usuario usuario;
	
	private List<ItemProduto> items = new ArrayList<ItemProduto>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ItemProduto> getItems() {
		return items;
	}

	public void setItems(List<ItemProduto> items) {
		this.items = items;
	}
	
}
