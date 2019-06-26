package com.andersonfonseka.caffeine.paginas;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.andersonfonseka.caffeine.IBotao;
import com.andersonfonseka.caffeine.ICard;
import com.andersonfonseka.caffeine.IConteiner;
import com.andersonfonseka.caffeine.IEntradaNumero;
import com.andersonfonseka.caffeine.IEntradaOculta;
import com.andersonfonseka.caffeine.IFormulario;
import com.andersonfonseka.caffeine.IResposta;
import com.andersonfonseka.caffeine.IRotulo;
import com.andersonfonseka.caffeine.componentes.acao.AcaoAbs;
import com.andersonfonseka.caffeine.dominio.ItemProduto;
import com.andersonfonseka.caffeine.dominio.Produto;
import com.andersonfonseka.caffeine.repositorio.ProdutoRepositorio;

public class PetstorePrincipal extends PetstorePagina {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoRepositorio produtoRepositorio;

	IFormulario formulario;

	IConteiner conteiner;

	@PostConstruct
	public void post() {
		
		super.post();

		setTitulo("Principal");

		formulario = getComponenteFabrica().criarFormulario();
		conteiner = getComponenteFabrica().criarConteiner(1);

		for (Produto prod : getProdutoRepositorio().getProdutos()) {

			ICard card = getComponenteFabrica().criarCard(prod.getImagem(), prod.getDescricao(), prod.getObservacoes());
			
			IEntradaOculta txProdutoId = getComponenteFabrica().criarEntradaOculta(prod.getId().toString());

			IEntradaNumero txtQuantidade = getComponenteFabrica().criarEntradaNumero("Quantidade", true);
			
			IRotulo lblValor = getComponenteFabrica().criarRotulo("R$ " + prod.getValor());
			
			IBotao botao = getComponenteFabrica().criarBotao("Adicionar ao carrinho", new AcaoAbs(card) {
				@Override
				public IResposta execute() {

					ICard cardBotao = (ICard) getSource();
					
					Produto produto = produtoRepositorio.obterProduto(txProdutoId.getValor());
					
					ItemProduto itemProduto = new ItemProduto();
					itemProduto.setProduto(produto);
					itemProduto.setQuantidade(Integer.valueOf(txtQuantidade.getValor()));
					
					getCarrinhoRepositorio().adicionar(itemProduto);

					IResposta resposta = getComponenteFabrica().criarResposta();
					resposta.setPageUrl(PetstorePrincipal.class);

					return resposta;
				}
			}, true);
			
			
			card.adicionar(txProdutoId);
			card.adicionar(lblValor);
			card.adicionar(txtQuantidade);
			card.setBotao(botao);

			conteiner.adicionar(0, card);
		}

		formulario.adicionar(conteiner);
		adicionar(formulario);

	}

}
