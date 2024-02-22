package com.esprit.msuser.persistance.dtos;

import com.esprit.msuser.persistance.entities.User;
import lombok.Builder;

@Builder
public record UserDto(String firstName, String lastName, String email,
                      String username, TeamDto teamDto) {





}
