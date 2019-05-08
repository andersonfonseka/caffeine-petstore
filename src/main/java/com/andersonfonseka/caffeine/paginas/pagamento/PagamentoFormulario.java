package com.andersonfonseka.caffeine.paginas.pagamento;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.andersonfonseka.caffeine.IBotao;
import com.andersonfonseka.caffeine.IConteiner;
import com.andersonfonseka.caffeine.IEndereco;
import com.andersonfonseka.caffeine.IEntradaTexto;
import com.andersonfonseka.caffeine.IFormulario;
import com.andersonfonseka.caffeine.IResposta;
import com.andersonfonseka.caffeine.ISelecao;
import com.andersonfonseka.caffeine.ITipoValor;
import com.andersonfonseka.caffeine.componentes.ConteinerEnum;
import com.andersonfonseka.caffeine.componentes.acao.AcaoAbs;
import com.andersonfonseka.caffeine.componentes.impl.basicos.Pagina;
import com.andersonfonseka.caffeine.dominio.Cidade;
import com.andersonfonseka.caffeine.dominio.Cliente;
import com.andersonfonseka.caffeine.dominio.Endereco;
import com.andersonfonseka.caffeine.dominio.Estado;
import com.andersonfonseka.caffeine.repositorio.ClienteRepositorio;

@RequestScoped
public class PagamentoFormulario extends Pagina {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteRepositorio clienteRepositorio;

	IFormulario form;

	IConteiner conteiner;
	
	IConteiner conteinerBotoes;
	
	IEntradaTexto txtFirstName;

	IEntradaTexto txtLastName;

	IEndereco compEndereco;
	
	ITipoValor compContatos;

	@PostConstruct
	public void post() {

		setTitulo("Pagamento");
		setSubTitulo("Cadastro");

		form = getComponenteFabrica().criarFormulario();

		conteiner = getComponenteFabrica().criarConteiner(3);

		conteinerBotoes = getComponenteFabrica().criarConteiner(1);
		conteinerBotoes.setOrientacao(ConteinerEnum.HORIZONTAL);

		criarComponentesBasicos();
		criarComponenteEndereco();
		criarComponenteContatos();
		criarBotaoAplicar();
		
		form.adicionar(conteiner);
		form.adicionar(compEndereco);
		form.adicionar(compContatos);
		form.adicionar(conteinerBotoes);

		adicionar(form);
	}

	private void criarBotaoAplicar() {

		IBotao btnAplicar = getComponenteFabrica().criarBotao("Enviar", new AcaoAbs(form) {
			public IResposta execute() {

				IResposta pageResponse = getComponenteFabrica().criarResposta();
				pageResponse.setPageUrl(PagamentoFormulario.class);

				Cliente pessoa = new Cliente();
				pessoa.setPrimeiroNome(txtFirstName.getValor());


				pessoa.setUltimoNome(txtLastName.getValor());

				Endereco endereco = new Endereco();
				endereco.setLogradouro(compEndereco.getLogradouro().getValor());
				endereco.setNumero(compEndereco.getNumero().getValor());
				endereco.setComplemento(compEndereco.getComplemento().getValor());
				endereco.setBairro(compEndereco.getBairro().getValor());
				endereco.setEstado(new Estado(compEndereco.getEstado().getSelecionado().getValor(),
						compEndereco.getEstado().getSelecionado().getRotulo()));
				endereco.setCidade(new Cidade(compEndereco.getCidade().getSelecionado().getValor(),
						compEndereco.getCidade().getSelecionado().getRotulo()));

				pessoa.setEndereco(endereco);

				clienteRepositorio.adicionar(pessoa);

				pageResponse.setPageUrl(PagamentoPrincipal.class);

				return pageResponse;
			}
		}, false);

		conteinerBotoes.adicionar(0, btnAplicar).adicionar(0,
				getComponenteFabrica().criarBotaoCancelar(PagamentoPrincipal.class));

		
	}

	private void criarComponenteEndereco() {
		compEndereco = getComponenteFabrica().criarEndereco(this);
	}
	
	private void criarComponentesBasicos() {

		txtFirstName = getComponenteFabrica().criarEntradaTexto("Primeiro nome", true);
		txtLastName = getComponenteFabrica().criarEntradaTexto("Ultimo nome", true);

		conteiner.adicionar(0, txtFirstName)
				 .adicionar(0, txtLastName);

	}
	
	private void criarComponenteContatos() {

		ISelecao selecaoTipo = getComponenteFabrica().criarSelecao("Tipo", false);
		selecaoTipo.adicionar(getComponenteFabrica().criarOpcaoSelecao("1", "Fone Movel"));
		selecaoTipo.adicionar(getComponenteFabrica().criarOpcaoSelecao("2", "Fone Fixo"));
		selecaoTipo.adicionar(getComponenteFabrica().criarOpcaoSelecao("3", "E-mail"));
		selecaoTipo.adicionar(getComponenteFabrica().criarOpcaoSelecao("4", "Facebook"));
		selecaoTipo.adicionar(getComponenteFabrica().criarOpcaoSelecao("5", "Linkedin"));
		selecaoTipo.adicionar(getComponenteFabrica().criarOpcaoSelecao("6", "Instagram"));

		compContatos = getComponenteFabrica().criarTipoValor(this, selecaoTipo);
	}

	@Override
	public void aoCarregar(Map<String, Object> parametros) {
		super.aoCarregar(parametros);
	}

}