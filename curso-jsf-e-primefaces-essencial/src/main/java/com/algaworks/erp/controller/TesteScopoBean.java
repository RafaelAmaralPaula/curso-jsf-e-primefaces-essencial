package com.algaworks.erp.controller;

import java.io.Serializable;

//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
//import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
//@ViewScoped
//@RequestScoped
@SessionScoped
//@ApplicationScoped
public class TesteScopoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Integer NUMERO = 0;

	public TesteScopoBean() {
		NUMERO++;
	}
	
	
	public Integer getNumero() {
		return NUMERO;
	}

}
