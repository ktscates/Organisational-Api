package models;
import org.sql2o.*;

public class User {

    private int id;
    private String username;
    private int idDepartment;
    private String role;
    private String position;

    public User(String username, int idDepartment, String role, String position) {
        this.username = username;
        this.idDepartment = idDepartment;
        this.role = role;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String userame) {
        this.username = username;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {

        this.position = position;
    }

    public void save(User user) {
        String sql = "INSERT INTO users (username, idDepartment, role, position) VALUES (:username, :idDepartment, :role, :position)";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
