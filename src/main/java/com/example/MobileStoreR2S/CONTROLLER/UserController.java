package com.example.MobileStoreR2S.CONTROLLER;

import com.example.MobileStoreR2S.CONFIG.TokenProvider;
import com.example.MobileStoreR2S.DTO.LoginDTO;
import com.example.MobileStoreR2S.DTO.RegisterDTO;
import com.example.MobileStoreR2S.DTO.ResponseLoginDTO;
import com.example.MobileStoreR2S.DTO.UserDTO;
import com.example.MobileStoreR2S.EXCEPTION.UserNotFoundException;
import com.example.MobileStoreR2S.SERVICE.UserSV;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/user")

public class UserController {
    private final UserSV userService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserSV userService,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterDTO> register(@RequestBody RegisterDTO registerDTO) {
        RegisterDTO user = userService.saveUser(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
                            loginDTO.getPassword()));

            if (authentication.isAuthenticated()) {
                ResponseLoginDTO responseLoginDTO = new ResponseLoginDTO();
                responseLoginDTO.setUsername(loginDTO.getUsername());
                responseLoginDTO.setToken("Bearer " + TokenProvider.generateToken(loginDTO.getUsername()));

                List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                        .toList();
                responseLoginDTO.setRole(roles.get(0));
                return new ResponseEntity<>(responseLoginDTO, HttpStatus.OK);
            }
        } catch (AuthenticationException e) {
            throw new UserNotFoundException("Sai tên đăng nhập hoặc mật khẩu");
        }
        return null;
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
