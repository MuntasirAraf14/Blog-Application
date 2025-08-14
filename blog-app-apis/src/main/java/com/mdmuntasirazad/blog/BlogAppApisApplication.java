package com.mdmuntasirazad.blog;

import com.mdmuntasirazad.blog.config.AppConstants;
import com.mdmuntasirazad.blog.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement; 
@SpringBootApplication
@EnableTransactionManagement // <-- THIS IS THE CRITICAL FIX
public class BlogAppApisApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // This is for debugging, can be removed
        System.out.println("Test password encoding for 'xyz': " + this.passwordEncoder.encode("xyz"));

        try {
            // Using the recommended role name for clarity
            roleRepo.insertIgnoreRole(AppConstants.ADMIN_USER, "ROLE_ADMIN");
            roleRepo.insertIgnoreRole(AppConstants.STUDENT_USER, "ROLE_STUDENT");
            System.out.println("Admin and Student roles initialized successfully.");
        } catch (Exception e) {
            System.err.println("Error initializing roles: " + e.getMessage());
            e.printStackTrace();
        }
    }
}