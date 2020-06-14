package ba.wave.wavebackend.dataLoader;

import ba.wave.wavebackend.model.Talent;
import ba.wave.wavebackend.model.user.Role;
import ba.wave.wavebackend.model.user.RoleName;
import ba.wave.wavebackend.model.user.User;
import ba.wave.wavebackend.repository.IntonationRepository;
import ba.wave.wavebackend.repository.TalentRepository;
import ba.wave.wavebackend.repository.VoiceTypeRepository;
import ba.wave.wavebackend.service.RoleService;
import ba.wave.wavebackend.service.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;
    private IntonationRepository intonationRepository;
    private VoiceTypeRepository voiceTypeRepository;
    private TalentRepository talentRepository;

    public UsersLoader(RoleService roleService, UserService userService, PasswordEncoder passwordEncoder,
                       IntonationRepository intonationRepository, VoiceTypeRepository voiceTypeRepository, TalentRepository talentRepository) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.intonationRepository = intonationRepository;
        this.voiceTypeRepository = voiceTypeRepository;
        this.talentRepository = talentRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        if (dataLoaded) return;
        Set<Role> adminRoles = Collections.singleton(createRoleIfNotFound(RoleName.ROLE_ADMIN));
        Set<Role> userRoles = Collections.singleton(createRoleIfNotFound(RoleName.ROLE_USER));
        Set<Role> talentRole = Collections.singleton(createRoleIfNotFound(RoleName.ROLE_TALENT));

        createUserIfNotFound("admin", "$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi",
                "admin", "admin", "admin@admin.com",
                true, new Date(1514764800000L), adminRoles);
        createUserIfNotFound("user", "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC",
                "user", "user", "enabled@user.com",
                true, new Date(1514764800000L), userRoles);
        createUserIfNotFound("disabled", "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC",
                "user", "user", "disabled@user.com",
                false, new Date(1514764800000L), userRoles);

        createTalents(talentRole);
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


    @Transactional
    void createTalents(Set<Role> talentRole) {
        for (int i = 0; i < 7; i++) {
            Talent talent = new Talent();
            talent.setFirstName("Dummy" +i);
            talent.setLastName("Dummy"+ i);
            talent.setUsername("Dummy" + i);
            talent.setPassword(passwordEncoder.encode("pass"+i));
            talent.setEmail("email@email " + i +".com");
            talent.setEnabled(true);
            talent.setLastPasswordResetDate(new Date());
            talent.setRoles(talentRole);
            talent.setIntonations(Collections.singleton(intonationRepository.getOne(1)));
            talent.setVoiceTypes(Collections.singleton(voiceTypeRepository.getOne(1)));
            talentRepository.save(talent);
        }
    }

}

