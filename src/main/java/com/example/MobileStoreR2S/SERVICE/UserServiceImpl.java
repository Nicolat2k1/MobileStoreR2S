package com.example.MobileStoreR2S.SERVICE;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.MobileStoreR2S.DTO.LoginDTO;
import com.example.MobileStoreR2S.DTO.RegisterDTO;
import com.example.MobileStoreR2S.DTO.UserDTO;
import com.example.MobileStoreR2S.ENTITY.Role;
import com.example.MobileStoreR2S.ENTITY.User;
import com.example.MobileStoreR2S.EXCEPTION.NotFoundException;
import com.example.MobileStoreR2S.EXCEPTION.UserNotFoundException;
import com.example.MobileStoreR2S.MAPPER.UserMP;
import com.example.MobileStoreR2S.REPOSITORY.RoleRPS;
import com.example.MobileStoreR2S.REPOSITORY.UserRPS;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserSV {
    private final UserRPS userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMP userMapper;
    private final RoleRPS roleRepository;

    public UserServiceImpl(UserRPS userRepository, PasswordEncoder passwordEncoder, UserMP userMapper,
                           RoleRPS roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    // Tao User
    public RegisterDTO saveUser(RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new UserNotFoundException("Tên người dùng đã tồn tại.");
        }

        Role role = roleRepository.findById(registerDTO.getRole())
                .orElseThrow(() -> new NotFoundException(registerDTO.getRole()));

        User user = userMapper.toEntity(registerDTO);
        user.setPassword((passwordEncoder.encode(user.getPassword())));
        user.setRole(role);
        return userMapper.toDTO(userRepository.save(user));
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserDTO> getAllUser() throws NotFoundException {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("Không có người dùng nào.");
        }
        return users.stream().map(userMapper::toUserDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long id){
        if (!userRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public Object login(LoginDTO any) {
        return null;
    }
}
