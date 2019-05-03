package com.andersonfonseka.caffeine.paginas.pagamento;

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
import com.andersonfonseka.caffeine.repositorio.ClienteRepositorio;

@RequestScoped
public class PagamentoPrincipal extends Pagina {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteRepositorio clienteRepositorio;

	private IFormulario form;

	public PagamentoPrincipal() {
	}

	@PostConstruct
	public void post() {

		setTitulo("Pagamentos");

		form = getComponenteFabrica().criarFormulario();

		final IConteiner conteiner = getComponenteFabrica().criarConteiner(2);

		ITabela tabela = getComponenteFabrica().criarTabela("tblPagamentos");

		tabela.adicionaColuna(getComponenteFabrica().criarTabelaColuna("#", "getId", true))
				.adicionaColuna(getComponenteFabrica().criarTabelaColuna("Primeiro nome", "getPrimeiroNome"))
				.adicionaColuna(getComponenteFabrica().criarTabelaColuna("Ultimo nome", "getUltimoNome"));

		tabela.setDados(clienteRepositorio.getPessoas());

		IBotao btnNovo = getComponenteFabrica().criarBotao("Novo", new AcaoAbs(form) {
			public IResposta execute() {

				IResposta pageResponse = getComponenteFabrica().criarResposta();
				pageResponse.setPageUrl(PagamentoFormulario.class);

				return pageResponse;
			}
		}, false);
		
		IBotao btnEditar = getComponenteFabrica().criarBotao("Editar", new AcaoAbs(form) {
			public IResposta execute() {

				IResposta pageResponse = getComponenteFabrica().criarResposta();
				pageResponse.setPageUrl(PagamentoFormulario.class);

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