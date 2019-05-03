package com.andersonfonseka.caffeine.dominio;

public class Estado {
	
	private String valor;
	
	private String descricao;

	public Estado(String valor, String descricao) {
		super();
		this.valor = valor;
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}
	

}
