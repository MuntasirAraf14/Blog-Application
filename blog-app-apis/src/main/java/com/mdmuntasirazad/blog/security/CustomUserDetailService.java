package com.mdmuntasirazad.blog.security;

import com.mdmuntasirazad.blog.entities.User;
import com.mdmuntasirazad.blog.exceptions.ResourceNotFoundException;
import com.mdmuntasirazad.blog.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // <-- IMPORT THIS

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    // --- THIS IS THE FIX ---
    @Override
    @Transactional // This keeps the session open so that the lazy-loaded 'roles' collection can be accessed.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Loading user from database by username (email)
        User user = this.userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", username));

        // When user.getAuthorities() is called later by Spring Security,
        // the session will still be active, and the roles will be loaded successfully.
        return user;
    }
}