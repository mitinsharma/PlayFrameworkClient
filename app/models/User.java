package models;

import com.fdflib.model.state.CommonState;

/**
 * Created by brian.gormanly on 9/26/16.
 */
public class User extends CommonState {

    public String firstName;
    public String lastName;
    public String username;
    public String email;
    public String password;

    public User() {
        super();

    }
}
