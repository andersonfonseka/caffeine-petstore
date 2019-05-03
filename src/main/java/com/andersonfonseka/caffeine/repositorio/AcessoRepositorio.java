package com.andersonfonseka.caffeine.repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Singleton;

import com.andersonfonseka.caffeine.dominio.Usuario;

@Singleton
public class AcessoRepositorio {
	
	private static int contador = 1;
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public AcessoRepositorio() {}{}
	
	public void adicionar(Usuario usuario) {
		usuario.setId(contador);
		this.usuarios.add(usuario);
		contador++;
	}
	
	public Usuario obterUsuario(String id) {
		
		Usuario usuarioResultado = null;
		
		Optional<Usuario> usuarioBusca = usuarios.stream().filter(x -> x.getId().equals(Integer.valueOf(id))).findFirst();
		
		if (usuarioBusca.isPresent()) {
			usuarioResultado = usuarioBusca.get();
		}
		
		return usuarioResultado;
	}
	

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public Map<String, String> getMapaUsuarios(){
		
		Map<String, String> map = new HashMap<String, String>();
		
		for(Usuario usuario: getUsuarios()) {
			map.put(usuario.getEmail(), usuario.getSenha());
		}
		
		return map;
	}
	
}
