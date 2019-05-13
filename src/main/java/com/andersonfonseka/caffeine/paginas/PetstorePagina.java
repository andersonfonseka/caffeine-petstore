package com.andersonfonseka.caffeine.paginas;

import javax.inject.Inject;

import com.andersonfonseka.caffeine.IMenu;
import com.andersonfonseka.caffeine.IResposta;
import com.andersonfonseka.caffeine.componentes.acao.AcaoAbs;
import com.andersonfonseka.caffeine.componentes.impl.basicos.Pagina;
import com.andersonfonseka.caffeine.paginas.categoria.CategoriaPrincipal;
import com.andersonfonseka.caffeine.paginas.compra.CarrinhoPrinicipal;
import com.andersonfonseka.caffeine.paginas.produto.ProdutoPrincipal;
import com.andersonfonseka.caffeine.repositorio.CarrinhoRepositorio;
import com.andersonfonseka.caffeine.repositorio.ProdutoRepositorio;

public class PetstorePagina extends Pagina {
	
	@Inject
	private ProdutoRepositorio produtoRepositorio;

	@Inject
	private CarrinhoRepositorio carrinhoRepositorio;

	private static final long serialVersionUID = 1L;

	@Override
	public void post() {

		IMenu menu = getComponenteFabrica().criarMenu("Menu");
		
		menu.adicionar(getComponenteFabrica().criarMenuItem("Principal", new AcaoAbs(this) {
			public IResposta execute() {
				IResposta pageResponse = getComponenteFabrica().criarResposta();
				pageResponse.setPageUrl(PetstorePrincipal.class);

				return pageResponse;
			}
		}));
		
		menu.adicionar(getComponenteFabrica().criarMenuItem("Categorias", new AcaoAbs(this) {
			public IResposta execute() {
				IResposta pageResponse = getComponenteFabrica().criarResposta();
				pageResponse.setPageUrl(CategoriaPrincipal.class);

				return pageResponse;
			}
		}));
		
		menu.adicionar(getComponenteFabrica().criarMenuItem("Produtos", new AcaoAbs(this) {
			public IResposta execute() {
				IResposta pageResponse = getComponenteFabrica().criarResposta();
				pageResponse.setPageUrl(ProdutoPrincipal.class);

				return pageResponse;
			}
		}));
		
		menu.adicionar(getComponenteFabrica().criarMenuItem("Meu carrinho (" + carrinhoRepositorio.getTotalProdutos() + ")", new AcaoAbs(this) {
			public IResposta execute() {
				IResposta pageResponse = getComponenteFabrica().criarResposta();
				pageResponse.setPageUrl(CarrinhoPrinicipal.class);

				return pageResponse;
			}
		}));


		setMenu(menu);
	}

	public ProdutoRepositorio getProdutoRepositorio() {
		return produtoRepositorio;
	}

	public CarrinhoRepositorio getCarrinhoRepositorio() {
		return carrinhoRepositorio;
	}
	
}
