package ba.wave.wavebackend.dataLoader;

import ba.wave.wavebackend.model.user.*;
import ba.wave.wavebackend.service.RoleService;
import ba.wave.wavebackend.service.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Component
public class UsersLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean dataLoaded = false;
    private RoleService roleService;
    private UserService userService;

    public UsersLoader(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        if (dataLoaded) return;
        Set<Role> adminRoles = Collections.singleton(createRoleIfNotFound(RoleName.ROLE_ADMIN));
        Set<Role> userRoles = Collections.singleton(createRoleIfNotFound(RoleName.ROLE_USER));
        createUserIfNotFound("admin", "$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi",
                "admin", "admin", "admin@admin.com",
                true, new Date(1514764800000L), adminRoles);
        createUserIfNotFound("user", "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC",
                "user", "user", "enabled@user.com",
                true, new Date(1514764800000L), userRoles);
        createUserIfNotFound("disabled", "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC",
                "user", "user", "disabled@user.com",
                false, new Date(1514764800000L), userRoles);
        dataLoaded = true;
    }

    @Transactional
    Role createRoleIfNotFound(RoleName name) {
        Role existingRole = roleService.findByName(name);
        if (existingRole != null) return existingRole;
        Role role = new Role(name);
        roleService.save(role);
        return role;
    }

    @Transactional
    User createUserIfNotFound(String userName, String password, String firstName,
                              String lastName, String email, boolean enabled,
                              Date lastPasswordResetDate, Set<Role> roles) {
        User existingUser = userService.findByEmail(email);
        if (existingUser != null) return existingUser;
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setEnabled(enabled);
        user.setLastPasswordResetDate(lastPasswordResetDate);
        user.setRoles(roles);
        userService.save(user);
        return user;
    }

}

