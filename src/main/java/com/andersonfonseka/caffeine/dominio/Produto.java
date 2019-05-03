package com.andersonfonseka.caffeine.dominio;

public class Produto {
	
	private Integer id;
	
	private String descricao;
	
	private double valor;
	
	private Categoria categoria;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getCategoria() {
		return categoria.getDescricao();
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
