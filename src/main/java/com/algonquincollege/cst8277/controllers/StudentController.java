/**
 * File: StudentController.java
 * Author (original): Mike Norman, course materials CST8277 (19W)
 *
 * Modified Data: Feb 2019
 * Author: Can Shi 040-806-036
 * 
 * Description:  Controller class that the view is connected with. Through this class, view is connected with the database.
 * @author Can Shi
 */

package com.algonquincollege.cst8277.controllers;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.algonquincollege.cst8277.daos.StudentDAO;
import com.algonquincollege.cst8277.models.Student;

@Named("studentController")
@ApplicationScoped
public class StudentController implements Serializable {
    /**
     * Explicit set serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * instance of StudentDAO
     * @author Can Shi
     */
    private StudentDAO studentDAO;
    /**
     * List of student objects
     * @author Can Shi
     */
    private List<Student> students;
    /**
     * a student object to hold data
     * @author Can Shi
     */
    private Student student = new Student();
    /**
     * an integer to hold id temporarily
     * @author Can Shi
     */
    private int id;

    public StudentController() {}

    @Inject
    public StudentController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
    
    /**
     * loads all students to the list-student page
     * @author Can Shi
     */
    @PostConstruct
    public void init() {
        this.students = studentDAO.getAllStudents();
    }

    /**
     * passes value to be added to database from view to studentDAO
     * @param student
     * @return page list-student.xhtml
     * @author Can Shi
     */
    public String createStudent(Student student) {
        cleanStudent(student);
        studentDAO.createStudent(student);
        init();
        return "list-student";
    }

    /**
     * passes value to be deleted to database from view to studentDAO
     * @param student
     * @return page list-student.xhtml
     * @author Can Shi
     */
    public String deleteStudent(Student student) {
        studentDAO.deleteStudent(student);
        init();
        return "list-student";
    }

    /**
     * passes value to be read to database from view to studentDAO
     * @return page display-single-student.xhtml
     * @author Can Shi
     */

    public String readStudent() {
        this.student = studentDAO.readStudent(id);
        return "display-single-student";
    }

    /**
     * passes request to display all students from view to studentDAO
     * @author Can Shi
     */
    public void getAllStudents(){
        this.students = studentDAO.getAllStudents();
    }

    /**
     * From studentDAO, gets the student to be edited and pass its info to the edit-student page
     * @return page list-student.xhtml
     * @author Can Shi
     */
    public String loadStudent() {
        this.student = studentDAO.readStudent(id);
        return "edit-student";
    }

    /**
     * passes value to be edited to database from view to studentDAO
     * @return page list-student.xhtml
     * @author Can Shi
     */
    public String updateStudent() {
        this.student = studentDAO.updateStudent(student);
        init();
        return "list-student";
    }

    /**
     * trims the name to be added
     * @param student
     * @author Can Shi
     */
    private void cleanStudent(Student student) {
        if (student.getFirstName() != null) {
            student.setFirstName(student.getFirstName().trim());
        }
        if (student.getLastName() != null) {
            student.setLastName(student.getLastName().trim());
        }
    }
        
    /**
     * below are all getters and setters for the fields
     * @author Can Shi
     */
    public StudentDAO getStudentDAO() {
        return studentDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    public List<Student> getStudents(){
        return students;
    }

    public Student getStudent() {
        return student;
    }

}
