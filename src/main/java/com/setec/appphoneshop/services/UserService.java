package com.setec.appphoneshop.services;

import com.setec.appphoneshop.models.AppUser;
import com.setec.appphoneshop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void createUser(AppUser user){
        this.userRepository.save(user);
    }

    @Transactional
    public AppUser findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public Page<AppUser> paginated(int pageNo, String keyword) {
        Pageable pageable = PageRequest.of(pageNo - 1, 10);
        if(keyword == null || keyword.isEmpty()){
            return this.userRepository.findAll(pageable);
        }
        return this.userRepository.findByUsernameContainingIgnoreCase(keyword, pageable);
    }

    public AppUser findById(int id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        this.userRepository.deleteById(id);
    }
}