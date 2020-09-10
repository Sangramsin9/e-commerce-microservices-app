package com.ecom.app.accountservice;

import com.ecom.app.accountservice.model.Role;
import com.ecom.app.accountservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountServiceApplication implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		Role existing = roleRepository.findByName("ROLE_ADMIN");
		if (existing!=null)
			roleAdmin.setId(existing.getId());
		roleRepository.save(roleAdmin);

		Role roleCust = new Role();
		roleCust.setName("ROLE_CUSTOMER");
		existing = roleRepository.findByName("ROLE_CUSTOMER");
		if (existing!=null)
			roleCust.setId(existing.getId());
		roleRepository.save(roleCust);

		Role roleSeller = new Role();
		roleSeller.setName("ROLE_SELLER");
		existing = roleRepository.findByName("ROLE_SELLER");
		if (existing!=null)
			roleSeller.setId(existing.getId());
		roleRepository.save(roleSeller);
	}
}
