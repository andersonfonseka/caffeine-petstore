package com.andersonfonseka.caffeine.paginas.acesso;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.andersonfonseka.caffeine.IBotao;
import com.andersonfonseka.caffeine.IConteiner;
import com.andersonfonseka.caffeine.IEntradaEmail;
import com.andersonfonseka.caffeine.IEntradaSenha;
import com.andersonfonseka.caffeine.IFormulario;
import com.andersonfonseka.caffeine.IResposta;
import com.andersonfonseka.caffeine.componentes.acao.AcaoAbs;
import com.andersonfonseka.caffeine.componentes.impl.basicos.Pagina;
import com.andersonfonseka.caffeine.dominio.Usuario;
import com.andersonfonseka.caffeine.repositorio.AcessoRepositorio;
import com.andersonfonseka.caffeine.util.MensagemUtil;

@RequestScoped
public class AcessoFormulario extends Pagina {

	private static final long serialVersionUID = 1L;
	
	private MensagemUtil mensagemUtil = new MensagemUtil();

	@Inject
	private AcessoRepositorio acessoRepositorio;

	IFormulario formulario;

	IConteiner conteiner;
	
	IConteiner conteinerBotoes;
	
	IEntradaEmail txtEmail;

	IEntradaSenha txtSenha;

	IEntradaSenha txtConfirmacaoSenha;

	@PostConstruct
	public void post() {

		setTitulo("Acesso - Cadastro");

		formulario = getComponenteFabrica().criarFormulario();

		conteiner = getComponenteFabrica().criarConteiner(3);

		conteinerBotoes = getComponenteFabrica().criarConteiner(1);
		conteinerBotoes.setOrientacao(IConteiner.HORIZONTAL);

		criarComponentesBasicos();
		criarBotaoAplicar();
		
		formulario.adicionar(conteiner);
		formulario.adicionar(conteinerBotoes);

		adicionar(formulario);
	}

	private void criarBotaoAplicar() {

		IBotao btnAplicar = getComponenteFabrica().criarBotao("Enviar", new AcaoAbs(formulario) {
			public IResposta execute() {

				IResposta pageResponse = getComponenteFabrica().criarResposta();
				
				if(txtSenha.getValor().equals(txtConfirmacaoSenha.getValor())) {

					Usuario usuario = new Usuario();
					usuario.setEmail(txtEmail.getValor());
					usuario.setSenha(txtSenha.getValor());
					acessoRepositorio.adicionar(usuario);	
					pageResponse.setPageUrl(AcessoPrincipal.class);
				
				} else {

					pageResponse.adicionar(mensagemUtil.getMensagemPropriedades("INVALIDACCESS", txtEmail.getValor()));
					pageResponse.setPageUrl(AcessoFormulario.class);
				}

				return pageResponse;
			}
		}, false);

		conteinerBotoes.adicionar(0, btnAplicar).adicionar(0,
				getComponenteFabrica().criarBotaoCancelar(AcessoPrincipal.class));

		
	}

	
	private void criarComponentesBasicos() {

		txtEmail = getComponenteFabrica().criarEntradaEmail("E-mail", true);
		txtSenha = getComponenteFabrica().criarEntradaSenha("Senha", true);
		txtConfirmacaoSenha = getComponenteFabrica().criarEntradaSenha("Confirmacao de senha", true);

		conteiner.adicionar(0, txtEmail);
		conteiner.adicionar(1, txtSenha);
		conteiner.adicionar(2, txtConfirmacaoSenha);
		
	}
	

	@Override
	public void aoCarregar(Map<String, Object> parametros) {
		super.aoCarregar(parametros);
	}

}