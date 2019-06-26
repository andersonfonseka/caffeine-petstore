package com.andersonfonseka.caffeine.paginas.categoria;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.andersonfonseka.caffeine.IBotao;
import com.andersonfonseka.caffeine.IConteiner;
import com.andersonfonseka.caffeine.IEntradaEditorTexto;
import com.andersonfonseka.caffeine.IEntradaTexto;
import com.andersonfonseka.caffeine.IFormulario;
import com.andersonfonseka.caffeine.IResposta;
import com.andersonfonseka.caffeine.componentes.ConteinerEnum;
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
	
	IEntradaEditorTexto txtObservacoes;

	@PostConstruct
	public void post() {

		setTitulo("Categorias");
		setSubTitulo("Cadastro");

		form = getComponenteFabrica().criarFormulario();

		conteiner = getComponenteFabrica().criarConteiner(4);

		conteinerBotoes = getComponenteFabrica().criarConteiner(1);
		conteinerBotoes.setOrientacao(ConteinerEnum.HORIZONTAL);

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
				categoria.setObservacoes(txtObservacoes.getValor());

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
		
		txtObservacoes = getComponenteFabrica().criarEntradaEditorTexto("Observacoes", false, 40);
		
		conteiner.adicionar(0, txtDescricao);
		conteiner.adicionar(1, txtObservacoes);
	}
	

	@Override
	public void aoCarregar(Map<String, Object> parametros) {
		super.aoCarregar(parametros);
	}

}