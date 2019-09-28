package models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return numberEmployees == that.numberEmployees &&
                Objects.equals(nameOfDepartment, that.nameOfDepartment) &&
                Objects.equals(detail, that.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfDepartment, detail, numberEmployees);
    }
}