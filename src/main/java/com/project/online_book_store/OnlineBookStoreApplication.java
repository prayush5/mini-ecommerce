package com.project.online_book_store;

import com.project.online_book_store.entity.Role;
import com.project.online_book_store.entity.User;
import com.project.online_book_store.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


@SpringBootApplication
public class OnlineBookStoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBookStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner initSuperAdmin(UserRepository userRepository,
											PasswordEncoder passwordEncoder) {
		return args -> {
			String superAdminEmail = "admin@admin.com";
			String superAdminPassword = "supersecure123";

			Optional<User> userOptional = userRepository.findByEmail(superAdminEmail);
			if (userOptional.isEmpty()) {
				User superAdmin = new User();
				superAdmin.setUsername("Admin");
				superAdmin.setEmail(superAdminEmail);
				superAdmin.setPassword(passwordEncoder.encode(superAdminPassword));
				superAdmin.setRole(Role.ADMIN);
				userRepository.save(superAdmin);
				System.out.println("✅ Super Admin created.");
			} else {
				System.out.println("ℹ️ Super Admin already exists.");
			}
		};
	}


	@Override
	public void run(String... args) throws Exception {

	}
}
