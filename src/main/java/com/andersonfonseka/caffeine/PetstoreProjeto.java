package com.andersonfonseka.caffeine;

import java.util.Map;

import javax.enterprise.context.SessionScoped;

import com.andersonfonseka.caffeine.componentes.impl.basicos.Projeto;
import com.andersonfonseka.caffeine.paginas.acesso.AcessoPrincipal;

@SessionScoped
public class PetstoreProjeto extends Projeto {
	
	private static final long serialVersionUID = 1L;

	public PetstoreProjeto() {
		setTitulo("Caffeine Petstore");
		setPaginaInicial(AcessoPrincipal.class);
	}

	@Override
	public void aoCarregar(Map<String, Object> parametros) {}
}
