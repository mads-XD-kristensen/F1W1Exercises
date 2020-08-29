package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private EmployeeFacade() {}
    
    public List<Employee> getAllEmployees(){
        EntityManager em = getEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT e FROM Employee e",Employee.class);
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Employee getEmployeeById(Long id){
        EntityManager em = getEntityManager();
        int ID = (int) (long) id;
        try {
            Employee employee = em.find(Employee.class, ID);
            return employee;
        } finally {
            em.close();
        }
    }
    
    public List<Employee> getEmployeesByName(String name){
        EntityManager em = getEntityManager();
        try {
            List<Employee> empList = (List<Employee>) em.find(Employee.class, name);
            return empList;
        } finally {
            em.close();
        }
    }
    
    public List<Employee> getEmployeesWithHighestSalary(){
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("Select e FROM Employee e WHERE e.salary=(SELECT MAX(e.salary) from Employee e )");
            List<Employee> highestSalaryEmployees = query.getResultList();
            return highestSalaryEmployees;
        } finally {
            em.close();
        }
    }
    
    public Employee createEmployee (String name, String address, int salary){
        Employee employee = new Employee(name, address, salary);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        } finally {
            em.close();
        }
    }
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
