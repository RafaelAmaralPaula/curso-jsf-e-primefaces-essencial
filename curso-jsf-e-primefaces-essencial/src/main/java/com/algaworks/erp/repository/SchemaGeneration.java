package com.algaworks.erp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.algaworks.erp.model.Empresa;

public class SchemaGeneration {
	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("AlgaWorks-PU");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		
		List<Empresa> lista = entityManager.createQuery("from Empresa",Empresa.class)
					.getResultList();
		
		System.out.println(lista);
		
		
		entityManager.close();
		entityManagerFactory.close();
		
	}
}
