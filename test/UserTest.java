import org.junit.Test;
import play.libs.Json;


/**
 * Created by brian.gormanly on 11/9/16.
 */


public class UserTest extends GenericTest {



    @Test
    public void testGetAllUsers() {
        String resUsers = this.get("http://localhost:9000/getAllUsers");
        System.out.println(resUsers);

    }

    @Test
    public void testGetUserByUsername() {
        String username = "chevyssman";
        String resUSers = this.get("http://localhost:9000/getUserByUsername/" + username);
        System.out.println("testing!!! " + resUSers);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.email = "my@email.com";
        user.firstName = "Bob";
        user.lastName = "Johes";
        user.username = "user";

        System.out.println(Json.toJson(user).toString());

        this.post("http://localhost:9000/saveUser", Json.toJson(user).toString());

    }





}
