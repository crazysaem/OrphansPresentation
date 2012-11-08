/*
 * Exam.java
 *
 * Created on 25. M�rz 2007, 16:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entity class Exam
 * @author  admin
 */
@Entity
@Table(name="exam")
public class Exam implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
    private Professor professor;
    
    @Id
    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;
    
    @Id
    @ManyToOne
    @JoinColumn(name="lecture_id")
    private Lecture lecture;

    @Column(nullable=true, precision=2, scale=1)
    private BigDecimal grade;
    
    @Transient
    private Long version;
    
    /**
	 * @return  the version
	 * @uml.property  name="version"
	 */
    public Long getVersion() {
		return version;
	}


	/**
	 * @param version  the version to set
	 * @uml.property  name="version"
	 */
	public void setVersion(Long version) {
		this.version = version;
	}


	/** Creates a new instance of Exam */
    public Exam() {
    }


    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        //hash += (this.getStudentId() != null ? this.getStudentId().hashCode() : 0);
        hash += (this.getStudent().getId() != null ? this.getStudent().getId().hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this Exam.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Exam object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exam)) {
            return false;
        }
        Exam other = (Exam)object;
        //if (this.getStudentId() != other.getStudentId() && (this.getStudentId() == null || !this.getStudentId().equals(other.getStudentId()))) return false;
        if (this.getStudent().getId() != other.getStudent().getId() && (this.getStudent().getId() == null || !this.getStudent().getId().equals(other.getStudent().getId()))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "entity.Exam[id=" + getStudent().getId() + "]";
    }

    /**
	 * @return  the professor
	 * @uml.property  name="professor"
	 */
    public Professor getProfessor() {
        return professor;
    }

    /**
	 * @param professor  the professor to set
	 * @uml.property  name="professor"
	 */
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    /**
	 * @return  the student
	 * @uml.property  name="student"
	 */
    public Student getStudent() {
        return student;
    }

    /**
	 * @param student  the student to set
	 * @uml.property  name="student"
	 */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
	 * @return  the lecture
	 * @uml.property  name="lecture"
	 */
    public Lecture getLecture() {
        return lecture;
    }

    /**
	 * @param lecture  the lecture to set
	 * @uml.property  name="lecture"
	 */
    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    /**
	 * @return  the grade
	 * @uml.property  name="grade"
	 */
    public BigDecimal getGrade() {
        return grade;
    }

    /**
	 * @param mark  the grade to set
	 * @uml.property  name="grade"
	 */
    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }
  
}
