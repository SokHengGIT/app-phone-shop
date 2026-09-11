package com.setec.appphoneshop.dataseeder;

import com.setec.appphoneshop.model.User;
import com.setec.appphoneshop.models.Role;
import com.setec.appphoneshop.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.count() == 0) {
            Role role1 = new Role();
            role1.setName("ADMIN");
            roleRepository.save(role1);
            Role role2 = new Role();
            role2.setName("User");
            roleRepository.save(role2);
        }
    }
}
