package com.algaworks.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.erp.model.Empresa;

public class Empresas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Empresas() {

	}

	public Empresas(EntityManager manager) {
		this.manager = manager;
	}
	
	public Empresa buscarPeloId(Long id) {
		return manager.find(Empresa.class, id);
	}

	public List<Empresa> pesquisar(String nome){
		
		String jpql = "select e from Empresa e where razaoSocial like :razaoSocial";
		
		TypedQuery<Empresa> typedQuery = manager
				.createQuery(jpql , Empresa.class);
		typedQuery.setParameter("razaoSocial", nome + "%");
		
		
		return typedQuery.getResultList();
	}
	
	
	public List<Empresa> listar(){
		
		return manager.createQuery("from Empresa", Empresa.class).getResultList();
	}
	
	
	public Empresa criar(Empresa empresa) {
		return manager.merge(empresa);
	}
	
	public void remover(Empresa empresa) {
		empresa = buscarPeloId(empresa.getId());
		manager.remove(empresa);
	}

}
