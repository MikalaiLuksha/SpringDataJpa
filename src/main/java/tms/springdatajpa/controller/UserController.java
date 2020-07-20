package tms.springdatajpa.controller;


import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tms.springdatajpa.entity.ApiResponse;
import tms.springdatajpa.entity.user.User;
import tms.springdatajpa.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Data
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;
    private final List<UUID> uuidList;

    @PostMapping(path = "/createWithList")
    public ResponseEntity<ApiResponse> createUser (@Valid @RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.CREATED.value(), "user", "successful operation"), HttpStatus.CREATED);
    }

    @GetMapping(path = "/login")
    public ResponseEntity<UUID> authUser(@RequestParam("password") String password, @RequestParam("userName") String userName){
        boolean b = userService.authUser(password, userName);
        if (b){
            UUID uuid = UUID.randomUUID();
            uuidList.add(uuid);
            return new ResponseEntity<>(uuid, HttpStatus.CREATED);
        }
        else
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<User> findUser(@RequestHeader("token") String token, @PathVariable(name = "username") String userName) {
        if (uuidList.contains(UUID.fromString(token))) {
            User user = userService.getUser(userName);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
