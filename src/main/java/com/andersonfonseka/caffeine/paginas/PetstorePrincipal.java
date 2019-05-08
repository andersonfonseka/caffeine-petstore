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
import com.andersonfonseka.caffeine.repositorio.ProdutoRepositorio;

public class PetstorePrincipal extends PetstorePagina {

	private static final long serialVersionUID = 1L;

	@Inject
	ProdutoRepositorio produtoRepositorio;

	IFormulario formulario;

	IConteiner conteiner;

	@PostConstruct
	public void post() {
		super.post();

		setTitulo("Principal");

		formulario = getComponenteFabrica().criarFormulario();
		conteiner = getComponenteFabrica().criarConteiner(1);

		IBotao botao = getComponenteFabrica().criarBotao("Adicionar ao carrinho", new AcaoAbs(this) {
			@Override
			public IResposta execute() {
				return null;
			}
		}, true);

		for (Produto prod : produtoRepositorio.getProdutos()) {

			ICard card = getComponenteFabrica().criarCard(prod.getImagem(), prod.getDescricao(), prod.getObservacoes(),
					botao);
			conteiner.adicionar(0, card);

		}

		formulario.adicionar(conteiner);
		adicionar(formulario);

	}

}
