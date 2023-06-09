package com.shotFormLetter.sFL.domain.user.service;

import com.shotFormLetter.sFL.ExceptionHandler.DataNotFoundException;
import com.shotFormLetter.sFL.domain.user.domain.entity.SiteUser;
import com.shotFormLetter.sFL.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SiteUser create(String username, String user_nickname, String password) {
        SiteUser siteUser = new SiteUser();
        siteUser.setUser_nickname(user_nickname);
        siteUser.setUsername(username);
        siteUser.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(siteUser);
        return siteUser;
    }

    @Override
    public SiteUser getUser(String username) {
        Optional<SiteUser> user=this.userRepository.findByUsername(username);
        if(user.isPresent()){
            return user.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }
}
