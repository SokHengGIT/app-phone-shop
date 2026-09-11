package com.setec.appphoneshop.repositories;

import com.setec.appphoneshop.models.Role;
import com.setec.appphoneshop.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {


    Page<Role> findByNameContaining(String keyword, Pageable pageable);

    Role findByName(String name);
}