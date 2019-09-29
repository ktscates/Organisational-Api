import models.Department;
import models.News;
import models.User;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "welcome.hbs");
        },new HandlebarsTemplateEngine());

        get("/welcome", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "departform.hbs");
        },new HandlebarsTemplateEngine());

        get("/departform", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "departform.hbs");
        },new HandlebarsTemplateEngine());

        get("/viewdepartments", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("departments", Department.getAll());
            return new ModelAndView(model, "viewdepartments.hbs");
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

        get("/userform", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("departments", Department.getAll());
            return new ModelAndView(model, "userform.hbs");
        },new HandlebarsTemplateEngine());

        get("/viewusers", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("departments", Department.getAll());
            model.put("users", User.getAll());
            return new ModelAndView(model, "viewusers.hbs");
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
            return new ModelAndView(model, "viewusers.hbs");
        }, new HandlebarsTemplateEngine());

        get("/newsform", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("departments", Department.getAll());
            model.put("users", User.getAll());
            return new ModelAndView(model, "newsform.hbs");
        },new HandlebarsTemplateEngine());

        get("/viewnews", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("departments", Department.getAll());
            model.put("users", User.getAll());
            model.put("news", News.getAll());
            return new ModelAndView(model, "viewnews.hbs");
        },new HandlebarsTemplateEngine());

        post("/news", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String content = request.queryParams("content");
            int idDepartment = Integer.parseInt(request.queryParams("idDepartment"));
            int usernameId = Integer.parseInt(request.queryParams("usernameId"));
            News newNews = new News(content, idDepartment, usernameId);
            newNews.save(newNews);
            List<News> news = News.getAll();
            model.put("news", news);
            return new ModelAndView(model, "viewnews.hbs");
        }, new HandlebarsTemplateEngine());

    }

}
