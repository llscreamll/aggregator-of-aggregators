package com.project.service.taxi.service;

import com.project.service.taxi.auth.AuthRequest;
import com.project.service.taxi.auth.AuthResponse;
import com.project.service.taxi.dto.UserRequestDTO;
import com.project.service.taxi.entity.User;
import com.project.service.taxi.exception.LoginAndPasswordException;
import com.project.service.taxi.exception.LoginBeBusyException;
import com.project.service.taxi.exception.UserNotFoundException;
import com.project.service.taxi.mapper.UserMapper;
import com.project.service.taxi.repository.UserRepository;
import com.project.service.taxi.security.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTTokenProvider jwtProvider;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JWTTokenProvider jwtProvider) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public AuthResponse saveUser(UserRequestDTO userRequestDTO) {
        boolean checkLoginToDB = userRepository.existsByLogin(userRequestDTO.getLogin());
        if (checkLoginToDB) {
            throw new LoginBeBusyException();
        }
        User user = UserMapper.fromUserRequestDTOToUser(userRequestDTO);
        String token = jwtProvider.generateToken(user.getLogin());
        user.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword()));
        userRepository.save(user);
        return new AuthResponse(user.getId(), token);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(LoginAndPasswordException::new);
    }

    public AuthResponse checkLoginAndPassword(AuthRequest authRequest) {
        User users = findByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword());
        String token = jwtProvider.generateToken(users.getLogin());
        return new AuthResponse(users.getId(), token);
    }

    public User findByLoginAndPassword(String login, String password) {
        User users = userRepository.findByLogin(login).orElseThrow(UserNotFoundException::new);
        if (users != null) {
            if (bCryptPasswordEncoder.matches(password, users.getPassword())) {
                return users;
            }
        }
        throw new LoginAndPasswordException();
    }


}
