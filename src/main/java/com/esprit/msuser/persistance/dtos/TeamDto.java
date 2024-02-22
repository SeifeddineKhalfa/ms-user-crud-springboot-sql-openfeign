package com.esprit.msuser.persistance.dtos;

import lombok.Builder;

@Builder
public record TeamDto(String name, String department) {
}
