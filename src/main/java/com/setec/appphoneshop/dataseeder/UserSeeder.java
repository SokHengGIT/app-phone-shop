package com.setec.appphoneshop.dataseeder;

import com.setec.appphoneshop.models.AppUser;
import com.setec.appphoneshop.models.Role;
import com.setec.appphoneshop.repositories.UserRepository;
import com.setec.appphoneshop.services.RoleService;
import com.setec.appphoneshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.UserDatabase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserService userService;

    private final PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    };

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0) {
            AppUser user = new AppUser();
            user.setUsername("admin");
            user.setPassword(passwordEncoder().encode("123@"));
            user.setEnabled(true);
            Role role = this.roleService.findByName("ADMIN");
            if(role==null){
                role=new Role();
                role.setName("ADMIN");
                this.roleService.createRole(role);
            }
            user.setRoles(List.of(role));
            this.userService.createUser(user);
            System.out.println("User created");
        }
    }
}
