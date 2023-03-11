package org.viktoriianikitiuk.artoffreedom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import  org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.CacheControl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.viktoriianikitiuk.artoffreedom.dao.UserRepository;
import org.viktoriianikitiuk.artoffreedom.model.Role;
import org.viktoriianikitiuk.artoffreedom.model.User;
import org.viktoriianikitiuk.artoffreedom.service.RoleService;

import java.util.Arrays;

@SpringBootApplication
public class ArtOfFreedomApplication implements CommandLineRunner, WebMvcConfigurer {

	@Autowired
	RoleService roleService;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ArtOfFreedomApplication.class, args);
	}

	public void run(String... args) throws Exception {
//		initRoles();
//		initUsers();
	}

	private void initRoles()
	{
		roleService.saveRole(new Role("ROLE_ADMIN"));
		roleService.saveRole(new Role("ROLE_USER"));
	}

	private void initUsers()
	{
		//Login 'admin@admin.com'
		//Password 'password'
		userRepository.save(new User(
				"admin", "$2a$11$G7zdm26P0xjZ2rDGhJ.znu883hGEMIkK3dRWqkIy3FlhPrG9oDK2y",
				"admin@admin.com", "123 some street", "Viktoriia Nikitiuk"));

		User user = userRepository.findUserByUserName("admin");
		user.setRoles(Arrays.asList(roleService.findRoleByRoleName("ROLE_ADMIN")));

		userRepository.save(user);


	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// images
		registry.addResourceHandler("/images/upload/**")
				.addResourceLocations("file:" + System.getProperty("user.dir") + "/src/main/resources/static/images/upload/")
				.setCacheControl(CacheControl.noCache());
	}

}
