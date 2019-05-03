package com.andersonfonseka.caffeine.paginas.categoria;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.andersonfonseka.caffeine.IBotao;
import com.andersonfonseka.caffeine.IConteiner;
import com.andersonfonseka.caffeine.IEntradaTexto;
import com.andersonfonseka.caffeine.IFormulario;
import com.andersonfonseka.caffeine.IResposta;
import com.andersonfonseka.caffeine.componentes.acao.AcaoAbs;
import com.andersonfonseka.caffeine.componentes.impl.basicos.Pagina;
import com.andersonfonseka.caffeine.dominio.Categoria;
import com.andersonfonseka.caffeine.repositorio.CategoriaRepositorio;

@RequestScoped
public class CategoriaFormulario extends Pagina {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaRepositorio categoriaRepositorio;

	IFormulario form;

	IConteiner conteiner;
	
	IConteiner conteinerBotoes;
	
	IEntradaTexto txtDescricao;

	@PostConstruct
	public void post() {

		setTitulo("Categoria");

		form = getComponenteFabrica().criarFormulario();

		conteiner = getComponenteFabrica().criarConteiner(3);

		conteinerBotoes = getComponenteFabrica().criarConteiner(1);
		conteinerBotoes.setOrientacao(IConteiner.HORIZONTAL);

		criarComponentesBasicos();
		criarBotaoAplicar();
		
		form.adicionar(conteiner);
		form.adicionar(conteinerBotoes);

		adicionar(form);
	}

	private void criarBotaoAplicar() {

		IBotao btnAplicar = getComponenteFabrica().criarBotao("Enviar", new AcaoAbs(form) {
			public IResposta execute() {

				IResposta pageResponse = getComponenteFabrica().criarResposta();
				pageResponse.setPageUrl(CategoriaFormulario.class);

				Categoria categoria = new Categoria();
				categoria.setDescricao(txtDescricao.getValor());


				categoriaRepositorio.adicionar(categoria);

				pageResponse.setPageUrl(CategoriaPrincipal.class);

				return pageResponse;
			}
		}, false);

		conteinerBotoes.adicionar(0, btnAplicar).adicionar(0,
				getComponenteFabrica().criarBotaoCancelar(CategoriaPrincipal.class));

		
	}
	
	private void criarComponentesBasicos() {
		txtDescricao = getComponenteFabrica().criarEntradaTexto("Descricao", true);
		conteiner.adicionar(0, txtDescricao);
	}
	

	@Override
	public void aoCarregar(Map<String, Object> parametros) {
		super.aoCarregar(parametros);
	}

}