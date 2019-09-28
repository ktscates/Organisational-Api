package models;
import org.sql2o.*;
import org.sql2o.Sql2oException;

import java.util.List;

public class Department {
    private int id;
    private String nameOfDepartment;
    private String detail;
    private int numberEmployees;

    public Department(String nameOfDepartment, String detail, int numberEmployees) {
        this.nameOfDepartment = nameOfDepartment;
        this.detail = detail;
        this.numberEmployees = numberEmployees;
    }

    public String getNameOfDepartment() {
        return nameOfDepartment;
    }

    public void setDNameOfDepartment(String nameOfDepartment) {
        this.nameOfDepartment = nameOfDepartment;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getNumberEmployees() {
        return numberEmployees;
    }

    public void setNumberEmployees(int numberEmployees) {
        this.numberEmployees = numberEmployees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void save(Department department) {
        String sql = "INSERT INTO departs (nameofdepartment, detail, numberemployees) VALUES (:nameOfDepartment, :detail, :numberEmployees);";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(department)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public static List<Department> getAll(){
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM departs")
                    .executeAndFetch(Department.class);
        }
    }
}