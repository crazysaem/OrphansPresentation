package dao;

import java.util.List;

import org.junit.Test;
import org.springframework.dao.OptimisticLockingFailureException;

import entity.Lecture;
import entity.Student;

public class StudentDaoTests extends AbstractDaoTests {

	private StudentDao studentDao;
	private LectureDao lectureDao;
	
	public void setLectureDao(LectureDao lectureDao) {
		this.lectureDao = lectureDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void testFindById() {
		Student student = studentDao.findById(24002L);
		assertTrue(student.getName().equals("Xenokrates"));
	}

	public void testFindAll() {
		List<Student> students = studentDao.findAll();
		assertTrue(students.size()==8);
	}

	public void testGetStudent() {
		Student student = studentDao.getStudent(29555L);
		assertTrue(student.getLectures().size()==2);
	}

	public void testInsert() {
		Student student = new Student();
		student.setName("Test");
		student.setSemester(1);
		student = studentDao.insert(student);
		List<Student> studentlist = studentDao.findAll();
		assertTrue(studentlist.size()==9);
		assertTrue(student.getVersion()==1L);
	}

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
			//sharedEntityManager.flush();
			fail();
		} catch (Exception e) {
		}
	}

	public void testUpdate1() {
		Student student=studentDao.findById(24002L);
		student.setName("Update");
		student.setSemester(1);
		student = studentDao.update(student);
		//sharedEntityManager.flush();
	}

	public void testUpdate2() {
		Student student1 = studentDao.findById(24002L);
		Student student2 = studentDao.findById(24002L);
		sharedEntityManager.clear();
		student1.setName("Update1");
		student1.setSemester(1);
		student1=studentDao.update(student1);
		//sharedEntityManager.flush();
		student2.setName("Update2");
		try {
			student2=studentDao.update(student2);
			//sharedEntityManager.flush();
			fail();
		} catch (OptimisticLockingFailureException e) {
		}
	}
	
	@Test
	public void testStoreExam() {
		Student student = studentDao.findById(29555L);
		Lecture lecture = lectureDao.findById(5259L);
		studentDao.storeExam(student, lecture);
	}



}
