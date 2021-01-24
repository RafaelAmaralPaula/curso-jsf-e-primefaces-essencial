package com.algaworks.erp.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.model.RamoAtividade;
import com.algaworks.erp.model.TipoEmpresa;
import com.algaworks.erp.service.CadastroEmpresaService;

public class CamadaPersistencia {
	
	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("AlgaWorks-PU");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		CadastroEmpresaService cadastroEmpresaService = new CadastroEmpresaService();
		
		
		entityManager.getTransaction().begin();
		
		// DECLARANDO REPOSITÓRIOS
		
		RamoAtividades ramoAtividades = new RamoAtividades(entityManager);
		Empresas empresas = new Empresas(entityManager);
		
		//BUSCANDO INFORMAÇÕES
		
		List<RamoAtividade> listaDeRamoAtividades = ramoAtividades.pesquisar("");
		List<Empresa> listaDeEmpresas = empresas.pesquisar("");
		System.out.println(listaDeEmpresas);
		
		
		//CRIANDO UMA EMPRESA
		Empresa empresa = new Empresa();
		empresa.setNomeFantasia("João da silva");
		empresa.setCnpj("41.952.519/0001-57");
		empresa.setRazaoSocial("João da silva teste");
		empresa.setTipo(TipoEmpresa.MEI);
		empresa.setDataFundacao(new Date());
		empresa.setRamoAtividade(listaDeRamoAtividades.get(0));
		
		//SALVANDO EMPRESA
		cadastroEmpresaService.salvar(empresa);
		
		entityManager.getTransaction().commit();
		
		//VERIFICANDO SE INSERÇÃO FUNCIONOU
		List<Empresa> listaDeEmpresas2 = empresas.pesquisar("");
		System.out.println(listaDeEmpresas2);
		
		
		entityManager.close();
		entityManagerFactory.close();
		
	}
	
}
