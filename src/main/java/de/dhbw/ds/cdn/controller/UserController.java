package de.dhbw.ds.cdn.controller;

import de.dhbw.ds.cdn.data.User;
import de.dhbw.ds.cdn.repositries.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class UserController {

	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping("/users")
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, path = "/users")
    @ResponseBody
	public User setUser(@RequestBody User user){
        userRepository.save(user);
        return user;
	}

}
