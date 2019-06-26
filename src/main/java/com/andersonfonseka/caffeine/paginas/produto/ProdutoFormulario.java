package com.andersonfonseka.caffeine.paginas.produto;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.andersonfonseka.caffeine.IBotao;
import com.andersonfonseka.caffeine.IConteiner;
import com.andersonfonseka.caffeine.IEntradaArquivo;
import com.andersonfonseka.caffeine.IEntradaEditorTexto;
import com.andersonfonseka.caffeine.IEntradaNumero;
import com.andersonfonseka.caffeine.IEntradaTexto;
import com.andersonfonseka.caffeine.IFormulario;
import com.andersonfonseka.caffeine.IOpcaoSelecao;
import com.andersonfonseka.caffeine.IResposta;
import com.andersonfonseka.caffeine.ISelecao;
import com.andersonfonseka.caffeine.componentes.ConteinerEnum;
import com.andersonfonseka.caffeine.componentes.acao.AcaoAbs;
import com.andersonfonseka.caffeine.componentes.impl.basicos.Pagina;
import com.andersonfonseka.caffeine.dominio.Categoria;
import com.andersonfonseka.caffeine.dominio.Produto;
import com.andersonfonseka.caffeine.repositorio.CategoriaRepositorio;
import com.andersonfonseka.caffeine.repositorio.ProdutoRepositorio;

@RequestScoped
public class ProdutoFormulario extends Pagina {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaRepositorio categoriaRepositorio;

	@Inject
	private ProdutoRepositorio produtoRepositorio;
	
	IFormulario form;

	IConteiner conteiner;
	
	IConteiner conteinerBotoes;
	
	ISelecao selCategorias;
	
	IEntradaTexto txtDescricao;
	
	IEntradaNumero txtValor;
	
	IEntradaEditorTexto txtObservacoes;
	
	IEntradaArquivo imgAnimal;

	@PostConstruct
	public void post() {

		setTitulo("Produto");
		setSubTitulo("Cadastro");

		form = getComponenteFabrica().criarFormulario();

		conteiner = getComponenteFabrica().criarConteiner(5);
		
		conteinerBotoes = getComponenteFabrica().criarConteiner(1);
		conteinerBotoes.setOrientacao(ConteinerEnum.HORIZONTAL);
		
		criarComponentesBasicos();
		criarBotaoAplicar();
		
		form.adicionar(conteiner);
		form.adicionar(conteinerBotoes);
		
		adicionar(form);

	}

	private void criarBotaoAplicar() {

		IBotao btnAplicar = getComponenteFabrica().criarBotao("Enviar", new AcaoAbs(form) {
			public IResposta execute() {

				IResposta pageResponse = getComponenteFabrica().criarResposta();
				pageResponse.setPageUrl(ProdutoFormulario.class);

				Produto produto = new Produto();

				IOpcaoSelecao opcaoSelecao = selCategorias.getSelecionado();
				Categoria categoria = new Categoria();
				categoria.setId(Integer.valueOf(opcaoSelecao.getValor()));
				categoria.setDescricao(opcaoSelecao.getRotulo());
				
				produto.setCategoria(categoria);
				produto.setDescricao(txtDescricao.getValor());
				produto.setObservacoes(txtObservacoes.getValor());
				produto.setImagem(imgAnimal.getEntradaOculta().getValor());
				produto.setValor(Double.valueOf(txtValor.getValor()));

				produtoRepositorio.adicionar(produto);

				pageResponse.setPageUrl(ProdutoPrincipal.class);

				return pageResponse;
			}
		}, false);

		conteinerBotoes.adicionar(0, btnAplicar).adicionar(0,
				getComponenteFabrica().criarBotaoCancelar(ProdutoPrincipal.class));

		
	}
	
	private void criarComponentesBasicos() {
		
		selCategorias = getComponenteFabrica().criarSelecao("Categorias", true);
		
		for(Categoria categoria: categoriaRepositorio.getCategorias()) {
			selCategorias.adicionar(getComponenteFabrica().criarOpcaoSelecao(categoria.getId().toString(), categoria.getDescricao()));	
		}
		
		txtValor = getComponenteFabrica().criarEntradaNumero("Valor", true);
		txtDescricao = getComponenteFabrica().criarEntradaTexto("Descricao", true);
		txtObservacoes = getComponenteFabrica().criarEntradaEditorTexto("Observacoes", true, 40);
		imgAnimal = getComponenteFabrica().criarEntradaArquivo("Imagem do animal", false);
		
		conteiner.adicionar(0, selCategorias);
		conteiner.adicionar(1, txtDescricao);
		conteiner.adicionar(2, txtValor);
		conteiner.adicionar(3, txtObservacoes);
		conteiner.adicionar(4, imgAnimal);

	}
	

	@Override
	public void aoCarregar(Map<String, Object> parametros) {
		super.aoCarregar(parametros);
	}

}