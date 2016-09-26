package init;

import com.fdflib.persistence.database.DatabaseUtil;
import com.fdflib.service.FdfServices;
import com.fdflib.util.FdfSettings;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Role;
import models.User;
import models.UserRole;
import play.Logger;
import play.api.Environment;
import services.RoleService;
import services.UserService;
import tyrex.security.container.helper.RolesRealm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian.gormanly on 9/26/16.
 */
@Singleton
public class DbInit {

    @Inject
    public DbInit(Environment environment) {
        Logger.info("Brian's Application start");

        // initialize 4DFLib
        // Create a array that will hold the classes that make up our 4df data model
        List<Class> myModel = new ArrayList<>();

        // add model objects
        myModel.add(User.class);
        myModel.add(Role.class);
        myModel.add(UserRole.class);

        // get the 4dflib settings singleton
        FdfSettings fdfSettings = FdfSettings.getInstance();
        fdfSettings.PERSISTENCE = DatabaseUtil.DatabaseType.MYSQL;
        fdfSettings.DB_PROTOCOL = DatabaseUtil.DatabaseProtocol.JDBC_MYSQL;
        fdfSettings.DB_ENCODING = DatabaseUtil.DatabaseEncoding.UTF8;
        fdfSettings.DB_HOST = "localhost";
        fdfSettings.DB_NAME = "lab4";
        fdfSettings.DB_ROOT_USER = "root";
        fdfSettings.DB_ROOT_PASSWORD = "";
        fdfSettings.DB_USER = "myUser";
        fdfSettings.DB_PASSWORD = "myUserPassword";

        // call the initialization of library!
        FdfServices.initializeFdfDataModel(myModel);

        serviceTest();


    }

    private void serviceTest() {

        User user1 = new User();
        user1.firstName = "Brian";
        user1.lastName = "Gormanly";
        user1.username = "chevyssman";
        user1.password = "12345";
        user1.email = "brian.gormanly@marist.edu";

        UserService us = new UserService();
        user1 = us.save(user1);

        Role userRole = new Role();
        userRole.name = "user";
        userRole.description = "this is a user";

        Role studentRole = new Role();
        studentRole.name = "student";
        studentRole.description = "this is a student";

        Role facultyRole = new Role();
        facultyRole.name = "faculty";
        facultyRole.description = "this is a faculty";

        RoleService rs = new RoleService();
        rs.save(userRole);
        rs.save(studentRole);
        rs.save(facultyRole);

        List<Role> briansRoles = new ArrayList<>();
        briansRoles.add(userRole);
        briansRoles.add(facultyRole);

        rs.saveRolesForUser(user1.id, briansRoles);

        // Logger output
        List<Role> allRoles = rs.getAllRoles();
        for(Role role: allRoles) {
            Logger.info("roles: " + role.name);
        }

        List<User> allUsers = us.getAllUsers();
        for(User user: allUsers) {
            Logger.info("user : " + user.username);
            List<Role> allUserRoles = rs.getRolesForUser(user.id);
            for(Role uRole: allUserRoles) {
                Logger.info("UserRole: " + uRole.name);
            }
        }


    }
}
