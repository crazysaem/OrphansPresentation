package dao;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.Assistant;
import entity.Employee;
import entity.Professor;

public class EmployeeDaoTest {

	private static ApplicationContext appContext;
	private static EmployeeDao employeeDao;
	private static Connection jdbcConnection = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		appContext = new ClassPathXmlApplicationContext( "db.xml" );
	    employeeDao = (EmployeeDao) appContext.getBean("employeeDao");
		jdbcConnection = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe", "uni","uni");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		jdbcConnection.close();
	}

	@Before
	public void setUp() throws Exception {
		Statement statement = jdbcConnection.createStatement();
		statement.executeUpdate("drop sequence globalsequence");
		statement.executeUpdate("create sequence globalsequence");
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection,"UNI");
		IDataSet dataSet = new FlatXmlDataSet(new File("uni.xml"));
		DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsert1() {
		Professor professor = new Professor();
		professor.setName("New Professor");
		professor.setRank("C2");
		professor.setRoom("Central");
		employeeDao.insert(professor);
		assertTrue(professor.getId()==1L);
		assertTrue(professor.getVersion()==1L);
	}

	@Test
	public void testInsert2() {
		Assistant assistant = new Assistant();
		assistant.setName("New Assistant");
		assistant.setSubject("Subject");
		assistant.setProfessor(null);
		employeeDao.insert(assistant);
		assertTrue(assistant.getId()==1L);
		assertTrue(assistant.getVersion()==1L);
	}

	@Test
	public void testUpdate() {
		Employee employee = employeeDao.findById(2125L);
		((Professor)employee).setRoom("Room 1");
		employee = employeeDao.update(employee);
		assertTrue(employee.getVersion()==2L);
	}

	@Test
	public void testFindById() {
		Employee employee = employeeDao.findById(2125L);
		assertTrue(employee instanceof Professor);
		employee = employeeDao.findById(3002L);
		assertTrue(employee instanceof Assistant);
		employee = employeeDao.findById(1000L);
		assertTrue(employee == null);
	}

	@Test
	public void testFindAll() {
		List<Employee> employees = employeeDao.findAll();
		assertTrue(employees.size()==13);
	}

}
