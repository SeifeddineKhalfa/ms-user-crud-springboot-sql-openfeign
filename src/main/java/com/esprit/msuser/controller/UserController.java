package com.esprit.msuser.controller;

import com.esprit.msuser.persistance.dtos.UserDto;
import com.esprit.msuser.persistance.entities.User;
import com.esprit.msuser.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    // build create User REST API
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // build get user by id REST API
    // http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    // Build Get All Users REST API
    // http://localhost:..../api/users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Build Update User REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/users/1
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody User user){
        user.setId(userId);
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Build Delete User REST API
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
    }

    @GetMapping("/assignTeam/{userId}/{teamId}")
    public boolean assignTeamToUser(@PathVariable("userId") Long userId, @PathVariable("teamId") String teamId){
        User updatedUser = userService.assignTeamToUser(userId, teamId);
        return updatedUser != null;
    }


    @GetMapping("/teamUsers/{teamId}")
    public ResponseEntity<List<User>> getUsersByTeamId(@PathVariable("teamId") String teamId)
    {
        List<User> users = userService.getUsersByTeamId(teamId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // public  getUserTeamInfos




}
