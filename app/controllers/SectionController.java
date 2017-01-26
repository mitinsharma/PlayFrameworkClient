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

        String res = post("http://localhost:9000/saveSection", Json.toJson(section).toString());

        return ok(res);
    }

    public Result getSections() {

        String resSections = this.get("http://localhost:9000/getAllSections");
        Logger.info("here are my sections: \n{}\n", resSections);

        return ok(resSections);
    }

    public Result getMySections(Integer uid) {

        String resSections = this.get("http://localhost:9000/getMySections/"+uid);
        Logger.info("here are my sections: \n{}\n", resSections);

        return ok(resSections);
    }

    //enrollMySelf
    public Result enrollMySelf(Long studentId, Long sectionId) {

        String res = this.get("http://localhost:9000/enrollMySelf/"+studentId+"/"+sectionId);

        return ok(""+res);
    }

    //denrollMySelf
    public Result denrollMySelf(Long studentId, Long sectionId) {

        String res = this.get("http://localhost:9000/denrollMySelf/"+studentId+"/"+sectionId);

        return ok(""+res);
    }
}
