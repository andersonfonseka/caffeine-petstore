package com.andersonfonseka.caffeine.paginas.categoria;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.andersonfonseka.caffeine.IBotao;
import com.andersonfonseka.caffeine.IConteiner;
import com.andersonfonseka.caffeine.IFormulario;
import com.andersonfonseka.caffeine.IResposta;
import com.andersonfonseka.caffeine.ITabela;
import com.andersonfonseka.caffeine.componentes.ConteinerEnum;
import com.andersonfonseka.caffeine.componentes.acao.AcaoAbs;
import com.andersonfonseka.caffeine.dominio.Categoria;
import com.andersonfonseka.caffeine.dominio.Produto;
import com.andersonfonseka.caffeine.paginas.PetstorePagina;
import com.andersonfonseka.caffeine.paginas.compra.CarrinhoPrinicipal;
import com.andersonfonseka.caffeine.repositorio.CategoriaRepositorio;

@RequestScoped
public class CategoriaPrincipal extends PetstorePagina {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaRepositorio categoriaRepositorio;

	private IFormulario form;

	public CategoriaPrincipal() {
	}

	@PostConstruct
	public void post() {

		super.post();
		
		setTitulo("Categorias");

		form = getComponenteFabrica().criarFormulario();

		final IConteiner conteiner = getComponenteFabrica().criarConteiner(2);

		ITabela tabela = getComponenteFabrica().criarTabela("tblCategorias");

		tabela.adicionaColuna(getComponenteFabrica().criarTabelaColuna("#", "getId", true))
				.adicionaColuna(getComponenteFabrica().criarTabelaColuna("Descricao", "getDescricao"))
				.adicionaColuna(getComponenteFabrica().criarTabelaColuna("Observacoes", "getObservacoes"));

		tabela.setDados(categoriaRepositorio.getCategorias());

		IBotao btnNovo = getComponenteFabrica().criarBotao("Novo", new AcaoAbs(form) {
			public IResposta execute() {

				IResposta pageResponse = getComponenteFabrica().criarResposta();
				pageResponse.setPageUrl(CategoriaFormulario.class);

				return pageResponse;
			}
		}, false);
		
		IBotao btnEditar = getComponenteFabrica().criarBotao("Editar", new AcaoAbs(form) {
			public IResposta execute() {

				IResposta pageResponse = getComponenteFabrica().criarResposta();
				pageResponse.setPageUrl(CategoriaFormulario.class);

				return pageResponse;
			}
		}, false);
		
		IBotao btnRemover = getComponenteFabrica().criarBotao("Remover", new AcaoAbs(form) {
			public IResposta execute() {
				
				IResposta pageResponse = getComponenteFabrica().criarResposta();
				pageResponse.setPageUrl(CategoriaPrincipal.class);

				return pageResponse;
			}
		}, false);


		conteiner.
			adicionar(0, tabela);
		
		IConteiner conteinerBotoes = getComponenteFabrica().criarConteiner(1);
		conteinerBotoes.setOrientacao(ConteinerEnum.HORIZONTAL);
		
		conteinerBotoes.adicionar(0, btnNovo).
						adicionar(0, btnEditar).
						adicionar(0, btnRemover);
		
		form.adicionar(conteiner);
		form.adicionar(conteinerBotoes);

		adicionar(form);

	}

	@Override
	public void aoCarregar(Map<String, Object> parametros) {
		super.aoCarregar(parametros);
		
		if (parametros.containsKey("tblCategorias")) {
			Categoria categoria = new Categoria();
			categoria.setId(Integer.valueOf(parametros.get("tblCategorias").toString()));
			categoriaRepositorio.remover(categoria);
		}
	}

}