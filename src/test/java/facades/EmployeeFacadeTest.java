package facades;

import entities.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeFacadeTest {

//    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("pu");
//    private static final EmployeeFacade FE = EmployeeFacade.getFacadeExample(ENF);
//    List<Employee> exp = new ArrayList();
//
//    public EmployeeFacadeTest() {
//    }
//
//    @BeforeAll
//    public static void setUpClass() {
////        Add code to setup entities for test before running any test methods
//
//        FE.createEmployee("Test", "Test Address", 10000);
//        FE.createEmployee("Test", "Address Test", 10001);
//    }
//
//    @AfterAll
//    public static void tearDownClass() {
////        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
//        EntityManager em = ENF.createEntityManager();
//        em.getTransaction().begin();
//        Query query = em.createQuery("DELETE FROM Employee e WHERE e.name = :Test");
//        String Test = "Test";
//        query.setParameter("Test", Test);
//        query.executeUpdate();
//        em.getTransaction().commit();
//        em.close();
//    }
//
//    @BeforeEach
//    public void setUp() {
////        Put the test database in a proper state before each test is run
//        
//    }
//
//    @AfterEach
//    public void tearDown() {
////        Remove any data after each test was run
//         
//   }
//
//    /**
//     * Test a method here.
//     */
//    //@Test
//    public void testSomeMethod() {
//        fail("The test case is a prototype.");
////        assertTrue(true);
//    }
//
//    @Test
//    public void testGetEmployeesByName() {
//        List<Employee> rList = FE.getEmployeesByName("Test");
//        String expName = "Test";
//        
//
//    }
//
//    @Test
//    public void testGetEmployeeByID() {
////        Employee testEmployee = EmployeeFacade.getFacadeExample(ENF).getEmployeeById(1);
//        Employee expEmployee = new Employee("Test", "Test Address", 10000);
//    //    assertEquals(testEmployee.getName(), expEmployee.getName());
//    }
}
