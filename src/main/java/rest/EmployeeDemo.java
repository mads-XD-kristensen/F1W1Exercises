/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author madsa
 */
@Path("employees")
public class EmployeeDemo {

    @Context
    private UriInfo context;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static EmployeeFacade ef = EmployeeFacade.getFacadeExample(emf);
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    List<Employee> employees = null;
    List<EmployeeDTO> employeeDTOs = new ArrayList<>();

    /**
     * Creates a new instance of EmployeeDemo
     */
    public EmployeeDemo() {
    }

    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllEmployees() {
        employees = ef.getAllEmployees();

        for (Employee employee : employees) {
            EmployeeDTO dtoEmployee = new EmployeeDTO(employee);
            employeeDTOs.add(dtoEmployee);
        }

        return gson.toJson(employeeDTOs);

    }

    @Path("highestpaid")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeHighestSalary() {
        employees = ef.getEmployeesWithHighestSalary();
        for (Employee employee : employees) {
            EmployeeDTO dtoEmployee = new EmployeeDTO(employee);
            employeeDTOs.add(dtoEmployee);
        }
        return gson.toJson(employeeDTOs);
    }

    @Path("employeebyid/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeByID(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("select e FROM Employee e WHERE e.id = :id");
            query.setParameter("id", id);
            Employee emp = (Employee) query.getSingleResult();
            EmployeeDTO empDTO = new EmployeeDTO(emp);
            return gson.toJson(empDTO);
        } finally {
            em.close();
        }
    }

    @Path("name/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeByName(@PathParam("name") String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("select e FROM Employee e WHERE e.name = :name");
            query.setParameter("name", name);
            employees = (List<Employee>) query.getResultList();
            for (Employee employee : employees) {
                EmployeeDTO dtoEmployee = new EmployeeDTO(employee);
                employeeDTOs.add(dtoEmployee);
            }
            
            return gson.toJson(employeeDTOs);
        } finally {
            em.close();
        }
    }
}
