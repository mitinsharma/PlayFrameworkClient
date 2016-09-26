package models;

import com.fdflib.model.state.CommonState;

/**
 * Created by brian.gormanly on 9/26/16.
 */
public class UserRole extends CommonState {

    public long userId;
    public long roleId;

    public UserRole() {
        super();

    }
}
