package ba.wave.wavebackend.controller;


import ba.wave.wavebackend.service.RoleService;
import ba.wave.wavebackend.service.UserService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    /*@GetMapping("/")
    public ResponseEntity<String> getAllUsers() {
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/register")
    public ResponseEntity<@Valid ?> registerUser(@Valid @RequestBody User user,
                                                 UriComponentsBuilder uriBuilder) {

//        Set<Role> roles =
//                user.getRoles().stream().map(role -> {
//                    if (role.getId() == null)
//                        return roleService.save(role);
//                    return roleService.getOne(role.getId());
//                }).collect(Collectors.toSet());

//        user.setRoles(roles);
        userService.save(user);
        return ResponseEntity.created(uriBuilder.path("/users/{id}").buildAndExpand(user.getId().toString()).toUri()).body("");
    }*/

}

