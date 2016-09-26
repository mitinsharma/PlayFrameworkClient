package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.Role;
import models.UserRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by brian.gormanly on 9/26/16.
 */
public class RoleService extends FdfCommonServices {
    public Role save(Role role) {
        List<FdfEntity<Role>> existingRole = getRoleByName(role.name);
        if(existingRole != null && existingRole.size() > 0) {
            role.id = existingRole.get(0).entityId;
        }
        return this.save(role, Role.class);
    }

    public void deleteRole(long roleId) {
        this.setDeleteFlag(Role.class, roleId, -1, -1);
    }

    public void undeleteRole(long roleId) {
        this.removeDeleteFlag(Role.class, roleId, -1, -1);
    }

    public List<Role> getAllRoles() {
        return this.getAllCurrent(Role.class);
    }

    public List<FdfEntity<Role>> getAllRolesWithHistory() {
        return this.getAll(Role.class);
    }

    public List<FdfEntity<Role>> getRoleByName(String name) {
        return getEntitiesByValueForPassedField(Role.class, "name", name);


    }

    public void saveRolesForUser(long userId, List<Role> roles) {

        if(userId > -1 && roles != null) {

            for(Role role: roles) {

                //check to see if the UserRole already exists
                UserRole existingUserRole = getUserRole(userId, role.id);

                UserRole ur = new UserRole();

                if(existingUserRole != null) {
                    ur.id = existingUserRole.id;
                }
                ur.userId = userId;
                ur.roleId = role.id;

                this.save(UserRole.class, ur);
            }
        }
    }

    public void deleteRoleForUser(long userId, long roleId) {

        if(userId > -1 && roleId > -1) {
            UserRole userRole = getUserRole(userId, roleId);

            this.setDeleteFlag(UserRole.class, userRole.id, -1, -1);
        }

    }

    public void undeleteRoleForUser(long userId, long roleId) {

        if(userId > -1 && roleId > -1) {
            List<UserRole> allUserRoleAudit = auditAllCurrent(UserRole.class);
            UserRole deletedUserRole = null;
            for(UserRole userRole: allUserRoleAudit) {
                if(userRole.userId == userId && userRole.roleId == roleId) {
                    deletedUserRole = userRole;
                }
            }

            if(deletedUserRole != null) {
                this.removeDeleteFlag(UserRole.class, deletedUserRole.id, -1, -1);
            }
        }

    }

    public UserRole getUserRole(long userId, long roleId) {
        UserRole returnUserRole = null;
        if(userId > -1 && roleId > -1) {
            HashMap<String, String> fieldsAndValues = new HashMap<>();
            fieldsAndValues.put("userId", Long.toString(userId));
            fieldsAndValues.put("roleId", Long.toString(roleId));

            List<FdfEntity<UserRole>> returnedUserRoles =
                    getEntitiesByValuesForPassedFields(UserRole.class, fieldsAndValues);

            if(returnedUserRoles != null && returnedUserRoles.size() > 0 && returnedUserRoles.get(0).current != null) {
                returnUserRole = returnedUserRoles.get(0).current;
            }
        }
        return returnUserRole;
    }

    public List<Role> getRolesForUser(long userId) {
        List<Role> returnRoles = new ArrayList<>();

        if(userId > -1) {
            List<FdfEntity<UserRole>> userRoles = getEntitiesByValueForPassedField(UserRole.class, "userId",
                    Long.toString(userId));

            for(FdfEntity<UserRole> ur : userRoles) {
                if(ur != null && ur.current != null) {
                    Role role = getEntityCurrentById(Role.class, ur.current.roleId);
                    returnRoles.add(role);
                }
            }
        }

        return returnRoles;
    }
}
