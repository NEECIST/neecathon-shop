package com.joaomariajaneiro.neechathon.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.joaomariajaneiro.neechathon.dao.SimpleTeam;
import com.joaomariajaneiro.neechathon.model.Team;
import com.joaomariajaneiro.neechathon.model.User;
import com.joaomariajaneiro.neechathon.repository.ProjectRepository;
import com.joaomariajaneiro.neechathon.repository.TeamRepository;
import com.joaomariajaneiro.neechathon.repository.UserRepository;
import com.joaomariajaneiro.neechathon.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/teams")
@RestController
public class TeamController {

    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Team> getAllTeams(@RequestHeader Map<String, String> headers) {
        User user;
        try {
            user = userService.getUserFromToken(headers);
        } catch (Exception e) {
            return ImmutableList.of();
        }

        if (user == null) {
            return ImmutableList.of();
        }

        if (user.getTeam().isAdmin()) {
            return teamRepository.findAll();
        } else {
            return ImmutableList.of();
        }

    }

    @RequestMapping(value = "/{teamName}", method = RequestMethod.GET)
    public @ResponseBody
    Team getTeam(@PathVariable String teamName) {
        return teamRepository.findByName(teamName);
    }


    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<SimpleTeam> getTeamsSimple() {
        Iterable<SimpleTeam> teams = new ArrayList<>();
        int i = 0;
        int j = 1;
        for (Team team : teamRepository.findAll()) {
            i = 0;
            j = 1;
            SimpleTeam simpleTeam = new SimpleTeam();
            for (User user : team.getUsers()) {
                if (j == 1)
                    simpleTeam.setEmail1(user.getEmail())
                            .setGitHub1(user.getGitHub())
                            .setLinkedIn1(user.getLinkedIn())
                            .setPassword1(user.getPassword())
                            .setUsername1(user.getUsername());
                if (j == 2)
                    simpleTeam.setEmail2(user.getEmail())
                            .setGitHub2(user.getGitHub())
                            .setLinkedIn2(user.getLinkedIn())
                            .setPassword2(user.getPassword())
                            .setUsername2(user.getUsername());
                if (j == 3)
                    simpleTeam.setEmail3(user.getEmail())
                            .setGitHub3(user.getGitHub())
                            .setLinkedIn3(user.getLinkedIn())
                            .setPassword3(user.getPassword())
                            .setUsername3(user.getUsername());
                if (j == 4)
                    simpleTeam.setEmail4(user.getEmail())
                            .setGitHub4(user.getGitHub())
                            .setLinkedIn4(user.getLinkedIn())
                            .setPassword4(user.getPassword())
                            .setUsername4(user.getUsername());
                j++;
            }
            simpleTeam.setName(team.getName());
            simpleTeam.setProject_name(team.getProject().getTitle());
            simpleTeam.setCash(team.getCash());
            ((ArrayList<SimpleTeam>) teams).add(simpleTeam);
        }
        return teams;
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public Team getMyTransactions(@RequestHeader Map<String, String> headers) {
        User user;
        try {
            user = userService.getUserFromToken(headers);
        } catch (Exception e) {
            return null;
        }

        if (user == null) {
            return null;
        }

        try {
            return user.getTeam();
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateTeam(@RequestHeader Map<String, String> headers,
                             @RequestBody String payload) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(payload);

        User user;
        try {
            user = userService.getUserFromToken(headers);
        } catch (Exception e) {
            return "Bad credentials";
        }

        if (user == null) {
            return "Bad credentials";
        }

        if (user.getTeam().isAdmin()) {
            try {
                Team team = teamRepository.findByName(jsonNode.get("name").asText());
                team.setCash(jsonNode.get("cash").asLong());
                teamRepository.save(team);
                return "Team updated successfully";
            } catch (Exception e) {
                return "There was an error updating the team, does this team exist?";
            }

        } else {
            return "There was an error updating the team";
        }

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createTeam(@RequestBody String payload) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(payload);

        String name = "";
        try {
            name = jsonNode.get("name").asText();
        } catch (Exception e) {
            return "You need to provide a name";
        }

        List<User> users = new ArrayList<>();

        try {
            String user1Name = jsonNode.get("user1").asText();
            users.add(0, userRepository.findByUsername(user1Name));
        } catch (Exception e) {
            System.out.println("NO USER");
        }


        try {
            String user1Name = jsonNode.get("user2").asText();
            users.add(1, userRepository.findByUsername(user1Name));
        } catch (Exception e) {
            System.out.println("NO USER");
        }
        try {
            String user1Name = jsonNode.get("user3").asText();
            users.add(2, userRepository.findByUsername(user1Name));
        } catch (Exception e) {
            System.out.println("NO USER");
        }
        try {
            String user1Name = jsonNode.get("user4").asText();
            users.add(3, userRepository.findByUsername(user1Name));
        } catch (Exception e) {
            System.out.println("NO USER");
        }

        Team team = new Team();
        team.setName(name).setUsers(users);
        teamRepository.save(team);

        for (User user : users) {
            user.setTeam(team);
        }

        userRepository.saveAll(users);

        return "Team created with success";

    }

}
