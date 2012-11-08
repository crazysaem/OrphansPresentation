package dao;

import entity.Lecture;
import entity.Student;

public interface StudentDao extends GenericDao<Student> {

	void insertStudents (Student[] students);

	Student getStudent (Long id);
	
	void storeExam(Student student, Lecture lecture);
	
}
