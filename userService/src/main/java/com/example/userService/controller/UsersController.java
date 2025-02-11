package com.example.userService.controller;

import com.example.userService.dto.UserDto;
import com.example.userService.entity.UserEntity;
import com.example.userService.service.UserServiceImpl;
import com.example.userService.vo.RequestUser;
import com.example.userService.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/user-service")
public class UsersController {
    private final UserServiceImpl usersService;
    private final Environment env;

    @GetMapping("/health-check")
    public String status(HttpServletRequest request) {
        return String.format("It's Working in User Service on Port"
                + ", \nport(local.server.port)=" + env.getProperty("local.server.port")
                + ", \nport(server.port)=" + env.getProperty("server.port")
                + ", \nwith token secret=" + env.getProperty("token.secret")
                + ", \nwith test refresh=" + env.getProperty("test.refresh")
                + ", \nwith token time=" + env.getProperty("local.expiration_time"));
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(usersService.createUser(userDto), ResponseUser.class));
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<UserEntity> userList = usersService.getUserByAll();

        List<ResponseUser> result = new ArrayList<>();
        userList.forEach(v -> result.add(new ModelMapper().map(v, ResponseUser.class)));

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {
        UserDto userDto = usersService.getUserByUserId(userId);
        ResponseUser returnValue = new ModelMapper().map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
