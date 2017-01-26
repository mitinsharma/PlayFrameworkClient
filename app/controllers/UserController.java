package controllers;


import models.Section;
import models.User;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;


import views.html.*;

import java.util.Arrays;
import java.util.List;
/**
 * Created by Mitin on 11/30/2016.
 */
public class UserController extends GenericController {

    public Result home()
    {
        return ok(login.render());
    }
    public Result dashboard()
    {
        return ok(dashboard.render());
    }

    public Result addSection()
    {
        return ok(addsection.render());
    }

    public Result dropSection()
    {
        return ok(dropsection.render());
    }

    public Result login(String username,String pass) {

        String user = this.get("http://localhost:9000/login/"+username);
        return ok(user);

    }


}
