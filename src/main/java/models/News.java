package models;

import org.sql2o.*;
import java.util.List;

public class News {
    private int id;
    private String content;
    private int usernameId;
    private int idDepartment;

    public News(String content, int idDepartment, int usernameId) {
        this.content = content;
        this.idDepartment = idDepartment;
        this.usernameId = usernameId;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUsernameId() {
        return usernameId;
    }

    public void setUsernameId(int usernameId) {
        this.usernameId = usernameId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void save(News news) {
        String sql = "INSERT INTO news (content, iddepartment, usernameid) VALUES (:content, :idDepartment, :usernameId);";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(news)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public static List<News> getAll(){
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM news")
                    .executeAndFetch(News.class);
        }
    }


}