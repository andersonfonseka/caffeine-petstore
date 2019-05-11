package com.andersonfonseka.caffeine.paginas;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.andersonfonseka.caffeine.IBotao;
import com.andersonfonseka.caffeine.ICard;
import com.andersonfonseka.caffeine.IConteiner;
import com.andersonfonseka.caffeine.IFormulario;
import com.andersonfonseka.caffeine.IResposta;
import com.andersonfonseka.caffeine.componentes.acao.AcaoAbs;
import com.andersonfonseka.caffeine.dominio.Produto;
import com.andersonfonseka.caffeine.repositorio.CarrinhoRepositorio;
import com.andersonfonseka.caffeine.repositorio.ProdutoRepositorio;

public class PetstorePrincipal extends PetstorePagina {

	private static final long serialVersionUID = 1L;

	@Inject
	ProdutoRepositorio produtoRepositorio;

	@Inject
	CarrinhoRepositorio carrinhoRepositorio;

	IFormulario formulario;

	IConteiner conteiner;

	@PostConstruct
	public void post() {
		super.post();

		setTitulo("Principal");

		formulario = getComponenteFabrica().criarFormulario();
		conteiner = getComponenteFabrica().criarConteiner(1);

		for (Produto prod : produtoRepositorio.getProdutos()) {

			ICard card = getComponenteFabrica().criarCard(prod.getImagem(), prod.getDescricao(), prod.getObservacoes());

			IBotao botao = getComponenteFabrica().criarBotao("Adicionar ao carrinho", new AcaoAbs(card) {
				@Override
				public IResposta execute() {

					ICard cardBotao = (ICard) getSource();

					Produto produto = new Produto();
					produto.setDescricao(cardBotao.getTitulo());
					produto.setObservacoes(cardBotao.getTexto());

					carrinhoRepositorio.adicionar(produto);

					IResposta resposta = getComponenteFabrica().criarResposta();
					resposta.setPageUrl(PetstorePrincipal.class);

					return resposta;
				}
			}, true);
			
			card.setBotao(botao);

			conteiner.adicionar(0, card);

		}

		formulario.adicionar(conteiner);
		adicionar(formulario);

	}

}
