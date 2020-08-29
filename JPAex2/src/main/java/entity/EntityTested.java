/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author madsa
 */
public class EntityTested {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        List<Customer> customers = new ArrayList<>();
        Customer c1 = new Customer("Mads", "Kristensen");
        Customer c2 = new Customer("Mads", "Madsen");
        Customer c3 = new Customer("Poul", "Poulsen");
        Customer c4 = new Customer("Henrik", "Henriksen");
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
        customers.add(c4);
        
        em.getTransaction().begin();
        for (Customer customer : customers) {
            em.persist(customer);
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
