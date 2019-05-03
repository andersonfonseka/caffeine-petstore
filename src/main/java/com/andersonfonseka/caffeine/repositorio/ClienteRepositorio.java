package com.andersonfonseka.caffeine.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;

import com.andersonfonseka.caffeine.dominio.Cliente;

@Singleton
public class ClienteRepositorio {
	
	private static int contador = 1;
	
	private List<Cliente> pessoas = new ArrayList<Cliente>();
	
	public ClienteRepositorio() {}{}
	
	public void adicionar(Cliente pessoa) {
		pessoa.setId(contador);
		this.pessoas.add(pessoa);
		contador++;
	}
	
	public Cliente obterPessoa(String id) {
		
		Cliente pessoaResultado = null;
		
		Optional<Cliente> pessoaBusca = pessoas.stream().filter(x -> x.getId().equals(Integer.valueOf(id))).findFirst();
		
		if (pessoaBusca.isPresent()) {
			pessoaResultado = pessoaBusca.get();
		}
		
		return pessoaResultado;
	}
	

	public List<Cliente> getPessoas() {
		return pessoas;
	}
	
}
