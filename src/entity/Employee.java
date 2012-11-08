/*
 * Employee.java
 *
 * Created on 25. M�rz 2007, 16:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Entity class Employee
 * @author  admin
 */
@Entity
@Table(name="employee")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING)
public class Employee implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private Long id;
    
    private String name;
    
    @Version
    private Long version;
    
    /** Creates a new instance of Employee */
    public Employee() {
    }

    /**
	 * Gets the id of this Employee.
	 * @return  the id
	 * @uml.property  name="id"
	 */
    public Long getId() {
        return this.id;
    }

    /**
	 * Sets the id of this Employee to the specified value.
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
     * Determines whether another object is equal to this Employee.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Employee object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee)object;
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
        return "Employee[id=" + getId() + " name=" + getName() + "]";
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
