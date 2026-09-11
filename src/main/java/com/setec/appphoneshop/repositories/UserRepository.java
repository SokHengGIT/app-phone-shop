package com.setec.appphoneshop.repositories;

import com.setec.appphoneshop.models.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {

    Page<AppUser> findByUsernameContainingIgnoreCase(String keyword, Pageable pageable);

    AppUser findByUsername(String username);
}