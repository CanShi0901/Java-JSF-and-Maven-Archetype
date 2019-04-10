/**
 * File: StudentDAO.java
 * Author (original): Mike Norman, course materials CST8277 (19W)
 *
 * Modified Data: Feb 2019
 * Author: Can Shi 040-806-036
 * 
 * Description: StudentDAO interface class
 * @author Can Shi
 */
package com.algonquincollege.cst8277.daos;

import java.util.List;

import com.algonquincollege.cst8277.models.Student;

public interface StudentDAO {
    
    /**
     * creates a new student record in the database
     * @param student
     * @author Can Shi
     */
    public void createStudent(Student student);
    
    /**
     * reads a student's record from the database
     * @param id
     * @return a student instance
     * @author Can Shi
     */
    public Student readStudent(int id);
    
    /**
     * updates a student's record from the database
     * @param student
     * @return Student
     * @author Can Shi
     */
    public Student updateStudent(Student student);
    
    /**
     * deletes a student's record from the database
     * @param student
     */
    public void deleteStudent(Student student);
    
    /**
     * gets a lsit of all students from the database
     * @return a list of student elements
     * @author Can Shi
     */
    public List<Student> getAllStudents();

}
