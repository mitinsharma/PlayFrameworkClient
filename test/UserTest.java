import com.fdflib.model.state.FdfTenant;
import com.google.gson.*;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import models.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import play.libs.Json;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.stream.Collectors;


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
