package com.algaworks.erp.util;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transacional
@Priority(Interceptor.Priority.APPLICATION)
public class TransacionalInterceptor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction entityManagerTransaction = manager.getTransaction();
		boolean criado = false;
		
		try {
			if(!entityManagerTransaction.isActive()) {
				entityManagerTransaction.begin();
				entityManagerTransaction.rollback();
				
				entityManagerTransaction.begin();
				
				criado = true;
				
			}
			
			return context.proceed();
			
		}catch (Exception e) {
			if(entityManagerTransaction != null && criado) {
				entityManagerTransaction.rollback();
			}
			
			throw e;
			
		} finally {
			if(entityManagerTransaction != null && entityManagerTransaction.isActive() && criado) {
				entityManagerTransaction.commit();
			}
		}	
		
	}
	
	
}
