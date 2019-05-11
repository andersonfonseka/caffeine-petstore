package com.andersonfonseka.caffeine.paginas.compra;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.andersonfonseka.caffeine.IBotao;
import com.andersonfonseka.caffeine.IConteiner;
import com.andersonfonseka.caffeine.IFormulario;
import com.andersonfonseka.caffeine.IResposta;
import com.andersonfonseka.caffeine.ITabela;
import com.andersonfonseka.caffeine.componentes.ConteinerEnum;
import com.andersonfonseka.caffeine.componentes.acao.AcaoAbs;
import com.andersonfonseka.caffeine.paginas.PetstorePagina;
import com.andersonfonseka.caffeine.paginas.produto.ProdutoFormulario;
import com.andersonfonseka.caffeine.repositorio.CarrinhoRepositorio;

public class CarrinhoPrinicipal extends PetstorePagina {
	
	@Inject
	private CarrinhoRepositorio carrinhoRepositorio;

	private IFormulario form;

	@PostConstruct
	public void post() {
		
		super.post();

		setTitulo("Meu carrinho");

		form = getComponenteFabrica().criarFormulario();

		final IConteiner conteiner = getComponenteFabrica().criarConteiner(2);

		ITabela tabela = getComponenteFabrica().criarTabela("tblProdutos");

		tabela.adicionaColuna(getComponenteFabrica().criarTabelaColuna("#", "getId", true))
		.adicionaColuna(getComponenteFabrica().criarTabelaColuna("Descricao", "getDescricao"));

		tabela.setDados(carrinhoRepositorio.getProdutos());

		IBotao btnNovo = getComponenteFabrica().criarBotao("Finalizar compra", new AcaoAbs(form) {
			public IResposta execute() {

				IResposta pageResponse = getComponenteFabrica().criarResposta();
				pageResponse.setPageUrl(ProdutoFormulario.class);

				return pageResponse;
			}
		}, false);

		conteiner.
			adicionar(0, tabela);
		
		IConteiner conteinerBotoes = getComponenteFabrica().criarConteiner(1);
		conteinerBotoes.setOrientacao(ConteinerEnum.HORIZONTAL);
		
		conteinerBotoes.adicionar(0, btnNovo);
		
		form.adicionar(conteiner);
		form.adicionar(conteinerBotoes);

		adicionar(form);

	}

	@Override
	public void aoCarregar(Map<String, Object> parametros) {
		super.aoCarregar(parametros);
	}

	
}
