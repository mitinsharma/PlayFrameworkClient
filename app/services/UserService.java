package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian.gormanly on 9/26/16.
 */
public class UserService extends FdfCommonServices {

    public User save(User user) {
        List<FdfEntity<User>> existingUser = getUserByUsername(user.username);
        if(existingUser != null && existingUser.size() > 0) {
            user.id = existingUser.get(0).entityId;
        }
        return this.save(user, User.class);
    }

    public void deleteUser(long userId) {
        this.setDeleteFlag(User.class, userId, -1, -1);
    }

    public void undeleteUser(long userId) {
        this.removeDeleteFlag(User.class, userId, -1, -1);
    }

    public List<User> getAllUsers() {
        return this.getAllCurrent(User.class);
    }

    public List<FdfEntity<User>> getAllUsersWithHistory() {
        return this.getAll(User.class);
    }

    public List<FdfEntity<User>> getUserByUsername(String username) {
        return getEntitiesByValueForPassedField(User.class, "username", username);
    }


}
