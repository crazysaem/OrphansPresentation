/*
 * Lecture.java
 *
 * Created on 24. M�rz 2007, 19:43
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * Entity class Lecture
 * @author  admin
 */
@Entity
@Table(name="lecture")
public class Lecture implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "lecture")
    private List<Exam> exams;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GlobalSequence")
    @SequenceGenerator(name="GlobalSequence", schema="UNI", sequenceName="GlobalSequence", allocationSize=1)
    private Long id;
    
    private String title;
    
    @Version
    private Long version;
    
    @ManyToOne
    private Professor professor;
    
    @ManyToMany (mappedBy="lectures")
    private Collection<Student> students;
    
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="dependency", 
    joinColumns={@JoinColumn(name="predecessor_id")},
    inverseJoinColumns={@JoinColumn(name="successor_id")})
    private Collection<Lecture> successors;

    @Transient
    private Collection<Lecture> predecessors;
    
    /** Creates a new instance of Lecture */
    public Lecture() {
    }

    /**
	 * Gets the id of this Lecture.
	 * @return  the id
	 * @uml.property  name="id"
	 */
    public Long getId() {
        return this.id;
    }

    /**
	 * Sets the id of this Lecture to the specified value.
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
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this Lecture.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Lecture object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lecture)) {
            return false;
        }
        Lecture other = (Lecture)object;
        if (this.getId() != other.getId() && (this.getId() == null || !this.getId().equals(other.getId()))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
    	String str = "Lecture[id=" + getId() + " title=" + getTitle() + "]\nSuccessors:";
    	for (Lecture l: successors) {
    		str=str+l.getId()+" ";
    	}
        return str;
    }

    /**
	 * @return  the title
	 * @uml.property  name="title"
	 */
    public String getTitle() {
        return title;
    }

    /**
	 * @param title  the title to set
	 * @uml.property  name="title"
	 */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
	 * @return  the students
	 * @uml.property  name="students"
	 */
    public Collection<Student> getStudents() {
        return students;
    }

    /**
	 * @param students  the students to set
	 * @uml.property  name="students"
	 */
    public void setStudents(Collection<Student> students) {
        this.students = students;
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
	 * @return  the successors
	 * @uml.property  name="successors"
	 */
    public Collection<Lecture> getSuccessors() {
        return successors;
    }

    /**
	 * @param successors  the successors to set
	 * @uml.property  name="successors"
	 */
    public void setSuccessors(Collection<Lecture> successors) {
        this.successors = successors;
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

	public Collection<Lecture> getPredecessors() {
		return predecessors;
	}

	public void setPredecessors(Collection<Lecture> predecessors) {
		this.predecessors = predecessors;
	}
    
}
