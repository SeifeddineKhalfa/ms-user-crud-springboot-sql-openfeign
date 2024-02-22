package com.esprit.msuser.service.classes;

import com.esprit.msuser.persistance.dtos.UserDto;
import com.esprit.msuser.persistance.entities.User;
import com.esprit.msuser.persistance.repositories.UserRepository;
import com.esprit.msuser.service.interfaces.ITeamServiceFeignClient;
import com.esprit.msuser.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.util.*;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ITeamServiceFeignClient iTeamServiceFeignClient;

    @Override
    public User createUser(User user)
    {
        return userRepository.save(user);
    }

//    @Override
//    public User getUserById(Long userId) {
//        Optional<User> optionalUser = userRepository.findById(userId);
//        return optionalUser.get();
//    }


    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        Assert.notNull(user, "No user found with this id: "+id);

        UserDto userDto = UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .username(user.getUsername())
                .teamDto(iTeamServiceFeignClient.getTeamById(user.getTeamId()))
                .build();

        return userDto;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public User assignTeamToUser(Long userId, String teamId) {
        User user = userRepository.findById(userId).get();
        user.setTeamId(teamId);
        return userRepository.save(user);
    }

    public List<User> getUsersByTeamId(String teamId) {
            return userRepository.findByTeamId(teamId);
    }

}
