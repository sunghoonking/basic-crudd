package com.example.basiccrud.service;


import com.example.basiccrud.domain.User;
import com.example.basiccrud.dto.SignupRequestDto;
import com.example.basiccrud.dto.UserDto;
import com.example.basiccrud.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();

        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }


        // 패스워드 인코딩
        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, password);
        userRepository.save(user);

        return user;
    }

    // 닉네임 중복 체크
    public String checkUser(UserDto userDto){
        String username = userDto.getUsername();
        String message;
        Optional<User> name = userRepository.findByUsername(username);
        if(name.isPresent()){
            message = "중복되는 닉네임입니다. 다시 입력해주세요.";
        }else {
            message = "사용할 수 있는 닉네임입니다.";
        }
        return message;
    }

}
