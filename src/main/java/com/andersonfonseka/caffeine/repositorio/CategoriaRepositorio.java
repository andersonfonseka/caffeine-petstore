package com.andersonfonseka.caffeine.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;

import com.andersonfonseka.caffeine.dominio.Categoria;

@Singleton
public class CategoriaRepositorio {
	
	private static int contador = 1;
	
	private List<Categoria> categorias = new ArrayList<Categoria>();
	
	public CategoriaRepositorio() {}{}
	
	public void adicionar(Categoria categoria) {
		categoria.setId(contador);
		this.categorias.add(categoria);
		contador++;
	}
	
	public Categoria obterCategoria(String id) {
		
		Categoria categoriaResultado = null;
		
		Optional<Categoria> categoriaBusca = categorias.stream().filter(x -> x.getId().equals(Integer.valueOf(id))).findFirst();
		
		if (categoriaBusca.isPresent()) {
			categoriaResultado = categoriaBusca.get();
		}
		
		return categoriaResultado;
	}
	

	public List<Categoria> getCategorias() {
		return this.categorias;
	}
	
	
}
