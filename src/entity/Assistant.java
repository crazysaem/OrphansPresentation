/*
 * Assistant.java
 *
 * Created on 25. M�rz 2007, 16:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity class Assistant
 * @author  admin
 */
@Entity
@Table(name="assistant")
@DiscriminatorValue("Assistant")
public class Assistant extends Employee implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String subject;
    
    @ManyToOne
    private Professor professor;

    /** Creates a new instance of Assistant */
    public Assistant() {
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
     * Determines whether another object is equal to this Assistant.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Assistant object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assistant)) {
            return false;
        }
        Assistant other = (Assistant)object;
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
        return "Assistant: "+super.toString();
    }

	/**
	 * @return  the knowledgeArea
	 * @uml.property  name="knowledgeArea"
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param knowledgeArea  the knowledgeArea to set
	 * @uml.property  name="knowledgeArea"
	 */
	public void setSubject(String knowledgeArea) {
		this.subject = knowledgeArea;
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
    
}
