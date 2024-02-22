package com.esprit.msuser.service.interfaces;


import com.esprit.msuser.persistance.dtos.TeamDto;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@EnableFeignClients
@FeignClient(value = "MS-TEAM/team" ,url = "http://localhost:8085/teamApi/")
public interface ITeamServiceFeignClient {

    @GetMapping("teams/{id}")
    TeamDto getTeamById(@PathVariable String id);

}
