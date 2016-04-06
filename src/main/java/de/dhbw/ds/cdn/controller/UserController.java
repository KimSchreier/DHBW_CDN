package de.dhbw.ds.cdn.controller;

import de.dhbw.ds.cdn.data.User;
import de.dhbw.ds.cdn.repositries.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static java.util.UUID.randomUUID;

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

	@RequestMapping("/user")
	@ResponseBody
	public User getUserById(UUID id){
		User user = userRepository.findById(id);
		return user;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/users")
    @ResponseBody
	public User setUser(@RequestBody User user){
		user.setId(randomUUID());
        userRepository.save(user);
        return user;
	}

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public User getUserById(@RequestBody User user) throws Exception {
        User userDB = userRepository.findByLogin(user.getLogin());
        if(!StringUtils.equals(userDB.getPassword(),user.getPassword())){
            throw new Exception("User not found");
        }
        return userDB;
    }

}
