package dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
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
import org.springframework.dao.OptimisticLockingFailureException;

import entity.Lecture;
import entity.Student;

public class StudentDaoTest {

	private static ApplicationContext appContext;
	private static StudentDao studentDao;
	private static LectureDao lectureDao;
	private static Connection jdbcConnection;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	      try {
	    	  //Class.forName("com.mysql.jdbc.Driver");
	    	  appContext = new ClassPathXmlApplicationContext( "db.xml" );
		      studentDao = (StudentDao) appContext.getBean("studentDao");
		      lectureDao = (LectureDao) appContext.getBean("lectureDao");
		      jdbcConnection = DriverManager.getConnection(
					  "jdbc:oracle:thin:@localhost:1521:XE", "uni", "uni");
					  //"jdbc:mysql://localhost/uni","uni","uni");
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		  jdbcConnection.setAutoCommit(false);
	      IDatabaseConnection connection = new DatabaseConnection(jdbcConnection,"UNI");
	      IDataSet dataSet = new FlatXmlDataSet(new File("uni.xml"));
	      DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
	      jdbcConnection.commit();
	      jdbcConnection.close();
	}

	@Before
	public void setUp() throws Exception {
     	  
		  jdbcConnection.setAutoCommit(false);
	      IDatabaseConnection connection = new DatabaseConnection(jdbcConnection,"UNI");
	      IDataSet dataSet = new FlatXmlDataSet(new File("uni.xml"));
	      DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
	      jdbcConnection.commit();
		  
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testFindById() {
		Student student = studentDao.findById(24002L);
		assertTrue(student.getName().equals("Xenokrates"));
	}

	@Test
	public void testFindAll() {
		List<Student> students = studentDao.findAll();
		assertTrue(students.size()==8);
	}

	@Test
	public void testInsert() {
		Student student = new Student();
		student.setName("Test");
		student.setSemester(1);
		student.setVersion(null);
		student = studentDao.insert(student);
		assertTrue(student.getId()!=null);
		assertTrue(student.getVersion()==1L);
	}

	@Test
	public void testInsertStudents1() {
		Student[] students = new Student[2];
		students[0] = new Student();
		students[0].setName("Student 1");
		students[0].setSemester(1);
		students[1] = new Student();
		students[1].setName("Student 2");
		students[1].setSemester(1);
		studentDao.insertStudents(students);
		List<Student> studentlist = studentDao.findAll();
		assertTrue(studentlist.size()==10);
		
	}

	@Test
	public void testInsertStudents2() {
		Student[] students = new Student[2];
		students[0] = new Student();
		students[0].setName("Student 1");
		students[0].setSemester(1);
		students[1] = new Student();
		students[1].setName("Student 1");
		students[1].setSemester(1);
		try {
			studentDao.insertStudents(students);
		} catch(Exception e) {
		}
		List<Student> studentlist = studentDao.findAll();
		System.out.println("Size"+studentlist.size());
		assertTrue(studentlist.size()==8);
		
	}

	@Test
	public void testUpdate1() {
		Student student = studentDao.findById(24002L);
		student.setName("Update");
		student = studentDao.update(student);
		assertTrue(student.getName().equals("Update"));
		assertTrue(student.getVersion()==2L);
	}

	@Test
	public void testUpdate2() {
		Student student1 = studentDao.findById(29555L);
		Student student2 = studentDao.findById(29555L);
		student1.setName("Update1");
		student1.setSemester(1);
		student1=studentDao.update(student1);
		try {
			student2=studentDao.update(student2);
			fail();
		} catch (OptimisticLockingFailureException e) {
		}
	}

	@Test
	public void testGetStudent() {
		Student student = studentDao.getStudent(29555L);
		assertTrue(student.getLectures().size()==2);
	}
	
	@Test
	public void testStoreExam() {
		Student student = studentDao.getStudent(29555L);
		Lecture lecture = lectureDao.findById(5259L);
		studentDao.storeExam(student, lecture);
	}

}
