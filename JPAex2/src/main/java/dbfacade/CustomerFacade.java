/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author madsa
 */
public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = em.find(Customer.class, id);
            return customer;
        } finally {
            em.close();
        }
    }

    public List<Customer> findByLastName(String name) {
        EntityManager em = emf.createEntityManager();
        List<Customer> customers = new ArrayList<>();

        try {
            Query query = em.createQuery("Select e FROM Customer e WHERE e.lastName = :lastName");
            query.setParameter("lastName", name);
            customers = query.getResultList();
            return customers;
        } finally {
            em.close();
        }
    }

    public int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("Select customer from Customer customer", Customer.class);
            int numberOfCust = query.getResultList().size();
            return numberOfCust;
        } finally {
            em.close();
        }
    }

    public List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("Select customer from Customer customer", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Customer addCustomer(String fName, String lName){
        Customer customer = new Customer(fName, lName);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        }finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        
        //add customer
        Customer c1 = facade.addCustomer("Test", "Madsen");
        System.out.println("Tilføjede ny customer" + c1.toString());
        
        //find by id
        System.out.print("finding by id: ");
        System.out.println(facade.findByID(c1.getId()).toString());
        
        //find by last name
        System.out.print("finding by last name :");
        System.out.println(facade.findByLastName("Madsen"));
        
        //number of customers
        System.out.print("number of customers :");
        System.out.println(facade.getNumberOfCustomers());
        
        //all customers
        System.out.print("finding all customers :");
        System.out.println(facade.allCustomers());
        
        
    }
}
