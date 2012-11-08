/*
 * Professor.java
 *
 * Created on 25. M�rz 2007, 16:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity class Professor
 * @author  admin
 */
@Entity
@Table(name="professor")
@DiscriminatorValue("Professor")
public class Professor extends Employee implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "professor")
    private List<Exam> exams;

    @OneToMany(mappedBy = "professor")
    private List<Lecture> lectures;

    @OneToMany(mappedBy = "professor")
    private List<Assistant> assistants;

    private String rank;
    private String room;
    
    /** Creates a new instance of Professor */
    public Professor() {
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
     * Determines whether another object is equal to this Professor.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Professor object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Professor)) {
            return false;
        }
        Professor other = (Professor)object;
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
        return "Professor " + super.toString();
    }

    /**
	 * @return  the rank
	 * @uml.property  name="rank"
	 */
    public String getRank() {
        return rank;
    }

    /**
	 * @param rank  the rank to set
	 * @uml.property  name="rank"
	 */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
	 * @return  the room
	 * @uml.property  name="room"
	 */
    public String getRoom() {
        return room;
    }

    /**
	 * @param room  the room to set
	 * @uml.property  name="room"
	 */
    public void setRoom(String room) {
        this.room = room;
    }

	/**
	 * @return  the assistants
	 * @uml.property  name="assistants"
	 */
	public List<Assistant> getAssistants() {
		return assistants;
	}

	/**
	 * @param assistants  the assistants to set
	 * @uml.property  name="assistants"
	 */
	public void setAssistants(List<Assistant> assistants) {
		this.assistants = assistants;
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
	 * @return  the lectures
	 * @uml.property  name="lectures"
	 */
	public List<Lecture> getLectures() {
		return lectures;
	}

	/**
	 * @param lectures  the lectures to set
	 * @uml.property  name="lectures"
	 */
	public void setLectures(List<Lecture> lectures) {
		this.lectures = lectures;
	}
    
}
