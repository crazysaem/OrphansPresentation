package dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import entity.Exam;
import entity.Lecture;
import entity.Student;

public class StudentDaoImpl extends GenericDaoImpl<Student> implements StudentDao{

	@Override
	@Transactional
	public void insertStudents(final Student[] students) {
		for (int i=0; i<students.length;i++) {
			insert(students[i]);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Student getStudent(Long id) {
		List<Student> students = 
			getJpaTemplate().find
			("select distinct s from Student s left outer join fetch s.lectures where s.id="+id);
		if (students.size()>0) {
			Student student = students.get(0);
			return student;
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public void storeExam(Student student, Lecture lecture) {
		Exam exam = new Exam();
		exam.setStudent(student);
		exam.setLecture(lecture);
		getJpaTemplate().persist(exam);
		getJpaTemplate().flush();
	}

	@Override
	protected Class<Student> getEntityClass() {
		return Student.class;
	}


}
