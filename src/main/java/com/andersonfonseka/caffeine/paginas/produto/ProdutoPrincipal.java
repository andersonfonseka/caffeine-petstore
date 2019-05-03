package com.andersonfonseka.caffeine.paginas.produto;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.andersonfonseka.caffeine.IBotao;
import com.andersonfonseka.caffeine.IConteiner;
import com.andersonfonseka.caffeine.IFormulario;
import com.andersonfonseka.caffeine.IResposta;
import com.andersonfonseka.caffeine.ITabela;
import com.andersonfonseka.caffeine.componentes.acao.AcaoAbs;
import com.andersonfonseka.caffeine.componentes.impl.basicos.Pagina;
import com.andersonfonseka.caffeine.paginas.acesso.AcessoPrincipal;
import com.andersonfonseka.caffeine.repositorio.ProdutoRepositorio;

@RequestScoped
public class ProdutoPrincipal extends Pagina {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoRepositorio produtoRepositorio;

	private IFormulario form;

	public ProdutoPrincipal() {
	}

	@PostConstruct
	public void post() {

		setTitulo("Produtos");

		form = getComponenteFabrica().criarFormulario();

		final IConteiner conteiner = getComponenteFabrica().criarConteiner(2);

		ITabela tabela = getComponenteFabrica().criarTabela("tblProdutos");

		tabela.adicionaColuna(getComponenteFabrica().criarTabelaColuna("#", "getId", true))
		.adicionaColuna(getComponenteFabrica().criarTabelaColuna("Categoria", "getCategoria"))
		.adicionaColuna(getComponenteFabrica().criarTabelaColuna("Descricao", "getDescricao"))
		.adicionaColuna(getComponenteFabrica().criarTabelaColuna("Valor", "getValor"));


		tabela.setDados(produtoRepositorio.getProdutos());

		IBotao btnNovo = getComponenteFabrica().criarBotao("Novo", new AcaoAbs(form) {
			public IResposta execute() {

				IResposta pageResponse = getComponenteFabrica().criarResposta();
				pageResponse.setPageUrl(ProdutoFormulario.class);

				return pageResponse;
			}
		}, false);
		
		IBotao btnEditar = getComponenteFabrica().criarBotao("Editar", new AcaoAbs(form) {
			public IResposta execute() {

				IResposta pageResponse = getComponenteFabrica().criarResposta();
				pageResponse.setPageUrl(ProdutoFormulario.class);

				return pageResponse;
			}
		}, false);


		conteiner.
			adicionar(0, tabela);
		
		IConteiner conteinerBotoes = getComponenteFabrica().criarConteiner(1);
		conteinerBotoes.setOrientacao(IConteiner.HORIZONTAL);
		
		conteinerBotoes.adicionar(0, btnNovo).
						adicionar(0, btnEditar).
						adicionar(0, getComponenteFabrica().criarBotaoCancelar(AcessoPrincipal.class));
		
		form.adicionar(conteiner);
		form.adicionar(conteinerBotoes);

		adicionar(form);

	}

	@Override
	public void aoCarregar(Map<String, Object> parametros) {
		super.aoCarregar(parametros);
	}

}