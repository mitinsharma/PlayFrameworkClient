package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fdflib.model.entity.FdfEntity;
import com.google.gson.Gson;
import models.User;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import services.UserService;

import java.util.List;

/**
 * Created by brian.gormanly on 9/26/16.
 */
public class UserController extends Controller {

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
}
