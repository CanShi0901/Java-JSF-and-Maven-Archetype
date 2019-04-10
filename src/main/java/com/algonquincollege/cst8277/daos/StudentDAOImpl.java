/**
 * File: StudentDAOImpl.java
 * Author (original): Mike Norman, course materials CST8277 (19W)
 *
 * Modified Data: Feb 2019
 * Author: Can Shi 040-806-036
 * 
 * Description:  DAO class that impletements the StudentDAO, used to manipulate data from the database
 * @author Can Shi
 */

package com.algonquincollege.cst8277.daos;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.algonquincollege.cst8277.models.Student;

@Named
@ApplicationScoped
public class StudentDAOImpl implements Serializable, StudentDAO {
    /**
     * explicit set serialVersionUID 
     */
    private static final long serialVersionUID = 1L;

    /**
     * SQL queries to get all rows, get a single row, update a row, create a new row, and delete a row
     * @author Can Shi
     */
    private static final String GET_ALL_STUDENT = "SELECT * FROM student";
    private static final String GET_STUDENT = "SELECT * FROM student WHERE id = ?";
    private static final String UPDATE_STUDENT = "UPDATE student SET first_name = ?, last_name = ? WHERE id = ?";
    private static final String CREATE_STUDENT = "INSERT INTO student (first_name, last_name) VALUES (?, ?)";
    private static final String DELETE_STUDENT = "DELETE FROM student WHERE id = ?";

    /**
     * a logger
     * @author Can Shi
     */
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * a conection object
     * @author Can Shi
     */
    private Connection con;

    /**
     * a datasource object
     * @author Can Shi
     */
    @Resource(name = "jdbc/student")
    protected DataSource studentDb;

    /**
     * a list of student objects
     * @author Can Shi
     */
    protected List<Student> students;
    /**
     * a preparedStatement
     * @author Can Shi
     */
    protected PreparedStatement pstmt;
    /**
     * a resultSet
     * @author Can Shi
     */
    protected ResultSet rs;

    public StudentDAOImpl () {
    }

    /** 
     * @see com.algonquincollege.cst8277.daos.StudentDAO#createStudent(com.algonquincollege.cst8277.models.Student)
     * connects to the database and query to create a new record with firstname and lastname passed from controller
     * @author Can Shi
     * @param Student
     */
    @Override
    public void createStudent(Student student) {
        logger.info("creating a student");
        try {
            con = studentDb.getConnection();
            pstmt = con.prepareStatement(CREATE_STUDENT);
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.executeUpdate();

            con.close();
            rs.close();
            pstmt.close();
        }
        catch (SQLException e) {
            logger.error("Error accessing Student database", e);
        }
    }

    /** 
     * @see com.algonquincollege.cst8277.daos.StudentDAO#readStudent(int)
     * connects to the database and query to get a row of record with provided id
     * @author Can Shi
     * @return Student
     * @param int id
     */
    @Override
    public Student readStudent(int id) {
        logger.info("Finding a student");
        Student student = new Student();
        try {
            con = studentDb.getConnection();
            pstmt = con.prepareStatement(GET_STUDENT);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            rs.next();
            student.setId(id);
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));

            con.close();
            rs.close();
            pstmt.close();
        }
        catch (SQLException e) {
            logger.error("Error accessing Student database", e);
        }
        return student;
    }

    /**
     * @see com.algonquincollege.cst8277.daos.StudentDAO#updateStudent(com.algonquincollege.cst8277.models.Student)
     * connects to the database and update a row of records
     * @author Can Shi
     * @return Student
     * @param Student
     */
    @Override
    public Student updateStudent(Student student) {
        logger.info("Editing a student");
        try {
            con = studentDb.getConnection();
            pstmt = con.prepareStatement(UPDATE_STUDENT);
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setInt(3, student.getId());
            pstmt.executeUpdate();

            con.close();
            rs.close();
            pstmt.close();
        }
        catch (SQLException e) {
            logger.error("Error accessing Student database", e);
        }
        return student;
    }

    /**
     * @see com.algonquincollege.cst8277.daos.StudentDAO#deleteStudent(com.algonquincollege.cst8277.models.Student)
     * connects to the database and delete a record based on the id of a record
     * @param Student
     * @author Can Shi
     */
    @Override
    public void deleteStudent(Student student) {
        logger.info("deleting a student");
        try {
            con = studentDb.getConnection();
            pstmt = con.prepareStatement(DELETE_STUDENT);
            pstmt.setInt(1, student.getId());
            pstmt.executeUpdate();

            con.close();
            rs.close();
            pstmt.close();
        }
        catch (SQLException e) {
            logger.error("Error accessing Student database", e);
        }
    }

    /**
     * @see com.algonquincollege.cst8277.daos.StudentDAO#getAllStudents()
     * connects to the database and gets all records and return a list of student objects
     * @return List<Student>
     * @author Can Shi
     */
    @Override
    public List<Student> getAllStudents() {
        logger.info("retrieving list of students");
        List<Student> students = new ArrayList<>();
        try {
            con = studentDb.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STUDENT);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Student newStudent = new Student();
                newStudent.setId(rs.getInt("id"));
                newStudent.setFirstName(rs.getString("first_name"));
                newStudent.setLastName(rs.getString("last_name"));
                students.add(newStudent);
            }
            con.close();
            rs.close();
            pstmt.close();
        }
        catch (SQLException e) {
            logger.error("Error accessing Student database", e);
        }
        return students;
    }


}
