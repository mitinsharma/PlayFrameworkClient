package controllers;


import models.Section;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;


import views.html.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by brian.gormanly on 9/26/16.
 */
public class SectionController extends GenericController {


    public Result section() {

        String resSections = this.get("http://localhost:9000/getAllSections");
        Logger.info("here are my sections: \n{}\n", resSections);

        Section[] sectionArr = gson.fromJson(resSections, Section[].class);


        List<Section> sections = Arrays.asList(sectionArr);

        return ok(section.render(sections));
    }

    public Result saveSection(String name, int sectionNumber, boolean isPublished) {
        Logger.info("{}", name);
        Logger.info("{}", sectionNumber);
        Logger.info("{}", isPublished);

        Section section = new Section();
        section.name = name;
        section.sectionNumber = sectionNumber;
        section.published = isPublished;

        post("http://localhost:9000/saveSection", Json.toJson(section).toString());

        return ok();
    }

    /*
    public Result getAllUsers() {
        UserService us = new UserService();
        List<User> allUsers = us.getAllUsers();
        return ok(Json.toJson(allUsers));
    }

    public Result getUserByUsername(String username) {
        UserService us = new UserService();
        List<FdfEntity<User>> users = us.getUserByUsername(username);

        return ok(Json.toJson(users));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result saveUser() {
        JsonNode json = request().body().asJson();

        System.out.println("controller:  " + json);

        Gson gson = new Gson();

        User me = gson.fromJson(json.toString(), User.class);
        UserService us = new UserService();
        us.save(me);

        return ok("success!");
    }
    */
}
