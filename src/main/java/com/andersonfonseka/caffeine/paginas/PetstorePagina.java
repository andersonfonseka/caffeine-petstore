package com.andersonfonseka.caffeine.paginas;

import com.andersonfonseka.caffeine.IMenu;
import com.andersonfonseka.caffeine.IResposta;
import com.andersonfonseka.caffeine.componentes.acao.AcaoAbs;
import com.andersonfonseka.caffeine.componentes.impl.basicos.Pagina;
import com.andersonfonseka.caffeine.paginas.categoria.CategoriaPrincipal;
import com.andersonfonseka.caffeine.paginas.pagamento.PagamentoPrincipal;
import com.andersonfonseka.caffeine.paginas.produto.ProdutoPrincipal;

public class PetstorePagina extends Pagina {

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
		
		menu.adicionar(getComponenteFabrica().criarMenuItem("Pagamentos", new AcaoAbs(this) {
			public IResposta execute() {
				IResposta pageResponse = getComponenteFabrica().criarResposta();
				pageResponse.setPageUrl(PagamentoPrincipal.class);

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

		setMenu(menu);
	}

}
