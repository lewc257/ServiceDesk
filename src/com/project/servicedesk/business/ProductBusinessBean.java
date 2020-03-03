package com.project.servicedesk.business;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.project.servicedesk.entity.Faulttype;
import com.project.servicedesk.entity.Product;
import com.project.servicedesk.entity.Producttype;
import com.project.servicedesk.service.CustomerServiceBean;
import com.project.servicedesk.service.ProductServiceBean;


@Named(value = "productBusinessBean")
@SessionScoped
public class ProductBusinessBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	List<Product> productList;
	List<Producttype> productTypeList;
	List<Product> productsByType;
	List<String> productNamesByType;
	List<String> productTypeNames;
	
	@EJB
	ProductServiceBean productServiceBean;
	
	public List<String> getProductTypeNames(){
		this.productTypeNames = productServiceBean.findProductTypeNames();
		return this.productTypeNames;
	}
	
	public List<Product> getProductList() {
		this.productList = productServiceBean.findAllProducts();
		return this.productList;
	}

	public List<Producttype> getProductTypes(){
		this.productTypeList= productServiceBean.findProductTypes();
		return this.productTypeList;
	}

	public List<String> getProductsByType(String productTypeName){
		return productServiceBean.findProductNamesByType(productTypeName);
	}
}