    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entitiy.Animal;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author madsa
 */
@Path("animals_db")
public class AnimalFromDB {

    @Context
    private UriInfo context;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    /**
     * Creates a new instance of AnimalFromDB
     */
    public AnimalFromDB() {
    }

    @Path("animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }

    @Path("animalbyid/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByID(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("select e FROM Animal e WHERE e.id = :id");
            query.setParameter("id", id);
            Animal resultAnimal = (Animal) query.getSingleResult();
            return new Gson().toJson(resultAnimal);
        } finally {
            em.close();
        }
    }

    @Path("animalbytype/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByType(@PathParam("type") String type) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("select e from Animal e where e.type = :type", Animal.class);
            query.setParameter("type", type);
            List<Animal> resultAnimal = query.getResultList();
            return new Gson().toJson(resultAnimal);
        } finally {
            em.close();
        }
    }

    @Path("animalrandom")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomAimal() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("select e from Animal e", Animal.class);
            List<Animal> animalList = query.getResultList();
             if (animalList.isEmpty()) {
                return null;
            }
            Random r = new Random();
            return new Gson().toJson(animalList.get(r.nextInt(animalList.size()+1)));
           
        } finally {
            em.close();
        }

    }
}
