import models.Department;
import models.News;
import models.User;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class App {

    public static void main(String[] args) {

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "departform.hbs");
        },new HandlebarsTemplateEngine());

        get("/departform", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "departform.hbs");
        },new HandlebarsTemplateEngine());

        post("/departments", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String nameOfDepartment = request.queryParams("nameOfDepartment");
            String detail = request.queryParams("detail");
            int numberEmployees = Integer.parseInt(request.queryParams("numberEmployees"));
            Department newDepartment = new Department(nameOfDepartment, detail, numberEmployees);
            newDepartment.save(newDepartment);
            List<Department> departments = Department.getAll();
            model.put("departments", departments);
            return new ModelAndView(model, "viewdepartments.hbs");
        }, new HandlebarsTemplateEngine());

        get("/users", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "users.hbs");
        },new HandlebarsTemplateEngine());

        post("/users", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String username = request.queryParams("username");
            int idDepartment = Integer.parseInt(request.queryParams("idDepartment"));
            String role = request.queryParams("role");
            String position = request.queryParams("position");
            User newUser = new User(username, idDepartment, role, position);
            newUser.save(newUser);
            List<User> users = User.getAll();
            model.put("users", users);
            return new ModelAndView(model, "users.hbs");
        }, new HandlebarsTemplateEngine());

        get("/news", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "news.hbs");
        },new HandlebarsTemplateEngine());

        post("/news", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String content = request.queryParams("content");
            int idDepartment = Integer.parseInt(request.queryParams("idDepartment"));
            News newNews = new News(content, idDepartment);
            newNews.save(newNews);
            List<News> news = News.getAll();
            model.put("news", news);
            return new ModelAndView(model, "news.hbs");
        }, new HandlebarsTemplateEngine());

    }

}
