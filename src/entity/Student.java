/*
 * Student.java
 *
 * Created on 24. M�rz 2007, 19:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Entity class Student
 * @author  admin
 */
@Entity
@Table(name="student")
public class Student implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "student")
    private List<Exam> exams;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GlobalSequence")
    @SequenceGenerator(name="GlobalSequence", schema="UNI", sequenceName="GlobalSequence", allocationSize=1)
    private Long id;
    
    @Column(unique=true)
    private String name;
    
    private int semester;

    @Version
    private Long version;
    
    @ManyToMany
    @JoinTable(name="participation",
    joinColumns={@JoinColumn(name="student_id")},
    inverseJoinColumns={@JoinColumn(name="lecture_id")})
    private Collection<Lecture> lectures;

    /** Creates a new instance of Student */
    public Student() {
    }

    /**
	 * Gets the id of this Student.
	 * @return  the id
	 * @uml.property  name="id"
	 */
    public Long getId() {
        return this.id;
    }

    /**
	 * Sets the id of this Student to the specified value.
	 * @param id  the new id
	 * @uml.property  name="id"
	 */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this Student.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Student object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student)object;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "Student[id=" + getId() + " name=" + getName() + " version=" +getVersion() +"]";
    }

    /**
	 * @return  the name
	 * @uml.property  name="name"
	 */
    public String getName() {
        return name;
    }

    /**
	 * @param name  the name to set
	 * @uml.property  name="name"
	 */
    public void setName(String name) {
        this.name = name;
    }

    /**
	 * @return  the semester
	 * @uml.property  name="semester"
	 */
    public int getSemester() {
        return semester;
    }

    /**
	 * @param semester  the semester to set
	 * @uml.property  name="semester"
	 */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
	 * @return  the lectures
	 * @uml.property  name="lectures"
	 */
    public Collection<Lecture> getLectures() {
        return lectures;
    }

    /**
	 * @param lectures  the lectures to set
	 * @uml.property  name="lectures"
	 */
    public void setLectures(Collection<Lecture> lectures) {
        this.lectures = lectures;
    }

	/**
	 * @return  the exams
	 * @uml.property  name="exams"
	 */
	public List<Exam> getExams() {
		return exams;
	}

	/**
	 * @param exams  the exams to set
	 * @uml.property  name="exams"
	 */
	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

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
    
}
