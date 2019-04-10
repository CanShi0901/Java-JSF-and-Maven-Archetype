/**
 * File: Student.java
 * Author (original): Mike Norman, course materials CST8277 (19W)
 *
 * Modified Data: Feb 2019
 * Author: Can Shi 040-806-036
 * 
 * Description:  Model class which represents a row from the database.
 * @author Can Shi
 */

package com.algonquincollege.cst8277.models;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Student {
    
    /**
     * Explicit set serialVersionUID
     * @author Can Shi
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * id field
     * @author Can Shi
     */
    protected int id;
    /**
     * first name field
     * @author Can Shi
     */
    protected String firstName;
    /**
     * last name field
     * @author Can Shi
     */
    protected String lastName;
    
    
    /**
     * below are all setters and getters for the fields
     * @author Can Shi
     */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + id;
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (id != other.id)
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        return true;
    }
    
    


}
