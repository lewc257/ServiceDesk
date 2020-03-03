package com.project.servicedesk.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.project.servicedesk.entity.Product;
import com.project.servicedesk.entity.Producttype;

@Stateless
@LocalBean
public class ProductServiceBean {
	
	@PersistenceContext(name = "ServiceDesk")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public List<Product> findAllProducts() {
		return em.createNamedQuery("Product.findAll", Product.class).getResultList();
	}
	
	public List<String> findProductTypeNames(){
		String strQuery = "SELECT p.productType FROM Producttype p";
		return em.createQuery(strQuery, String.class).getResultList();
	}
	
	public List<String> findAllProductNames(){
		String strQuery = "SELECT p.productName FROM Product p";
		return em.createQuery(strQuery, String.class).getResultList();
	}

	public List<Product> findProductsByType(String productTypeName){
		return (List<Product>) em.createNamedQuery("Product.findByType",Product.class)
				.setParameter("productType",productTypeName).getResultList();
	}
	
	public List<String> findProductNamesByType(String productTypeName){
		String strQuery = "SELECT p.productName FROM Product p WHERE p.producttype.productType = '" + productTypeName + "'";
		return em.createQuery(strQuery, String.class).getResultList();
	}

	public List<Producttype> findProductTypes(){
		return em.createNamedQuery("Producttype.findAll",Producttype.class).getResultList();
	}
	
	public Product findProductByName(String productName) throws Exception{
		Product p = (Product) em.createNamedQuery("Product.findByName",Product.class)
				.setParameter("productName",productName).getSingleResult();
		return p;
	}
}
