package com.ysnacrk.grpcserver.repository;


import com.ysnacrk.grpcserver.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProductRepository {
    public void printProductList(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("product");
        EntityManager em = emf.createEntityManager();
        List<Product> products = em.createQuery("Select p from Product p", Product.class).getResultList();
        System.out.println(products);
    }
}
