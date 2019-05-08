package com.andersonfonseka.caffeine.paginas.acesso;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.andersonfonseka.caffeine.IBotao;
import com.andersonfonseka.caffeine.IConteiner;
import com.andersonfonseka.caffeine.IFormulario;
import com.andersonfonseka.caffeine.IMenu;
import com.andersonfonseka.caffeine.IResposta;
import com.andersonfonseka.caffeine.componentes.ConteinerEnum;
import com.andersonfonseka.caffeine.componentes.acao.AcaoAbs;
import com.andersonfonseka.caffeine.componentes.impl.basicos.Pagina;
import com.andersonfonseka.caffeine.paginas.PetstorePrincipal;
import com.andersonfonseka.caffeine.paginas.categoria.CategoriaFormulario;
import com.andersonfonseka.caffeine.paginas.categoria.CategoriaPrincipal;
import com.andersonfonseka.caffeine.repositorio.AcessoRepositorio;

@RequestScoped
public class AcessoPrincipal extends Pagina {
	
	private IFormulario formulario;

	@Inject
	private AcessoRepositorio acessoRepositorio;
	
	IConteiner conteinerBotoes;
	
	private static final long serialVersionUID = 1L;

	public AcessoPrincipal() {}

	@PostConstruct
	public void post() {
		
		setTitulo("Acesso");
		
		Map<String, String> map = acessoRepositorio.getMapaUsuarios();
		
		formulario = getComponenteFabrica().criarFormulario();
		formulario.adicionar(getComponenteFabrica().criarAcesso(this, map, PetstorePrincipal.class));
		
		conteinerBotoes = getComponenteFabrica().criarConteiner(1);
		conteinerBotoes.setOrientacao(ConteinerEnum.HORIZONTAL);

		criarBotaoNovoAcesso();

		
		formulario.adicionar(conteinerBotoes);
		
		adicionar(formulario);
	}

	@Override
	public void aoCarregar(Map<String, Object> parametros) {
		super.aoCarregar(parametros);
	}
	
	private void criarBotaoNovoAcesso() {

		IBotao btnAplicar = getComponenteFabrica().criarBotao("Novo acesso", new AcaoAbs(formulario) {
			public IResposta execute() {
				IResposta pageResponse = getComponenteFabrica().criarResposta();
				pageResponse.setPageUrl(AcessoFormulario.class);
				return pageResponse;
			}
		}, true);

		conteinerBotoes.adicionar(0, btnAplicar);
		
	}


}
