package com.ysnacrk.grpcserver.repository;


import com.ysnacrk.grpcserver.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProductRepository {
    public void save(Product product){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("product");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public Product findById(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("product");
        EntityManager em = emf.createEntityManager();
        return em.find(Product.class, id);
    }
}
