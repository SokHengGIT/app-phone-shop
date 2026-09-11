package com.setec.appphoneshop.services;

import com.setec.appphoneshop.models.Role;
import com.setec.appphoneshop.repositories.RoleRepository;
import com.setec.appphoneshop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByName(String name){
        return this.roleRepository.findByName(name);
    }
    @Transactional
    public void createRole(Role role) {
        this.roleRepository.save(role);

    }

    public List<Role> getRoleAll() {
        return this.roleRepository.findAll();
    }

}



