package com.esprit.msuser.service.interfaces;

import com.esprit.msuser.persistance.dtos.UserDto;
import com.esprit.msuser.persistance.entities.User;


import java.util.List;


public interface IUserService {

    User createUser(User user);

    //User getUserById(Long userId);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long userId);

    User getUserByEmail(String email);

    User assignTeamToUser(Long userId, String teamId);

    List<User> getUsersByTeamId(String teamId);

    UserDto getUserById(Long id);

}
