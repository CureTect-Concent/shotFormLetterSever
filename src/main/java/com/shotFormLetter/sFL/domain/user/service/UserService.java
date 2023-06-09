package com.shotFormLetter.sFL.domain.user.service;

import com.shotFormLetter.sFL.domain.user.domain.entity.SiteUser;

public interface UserService {
    SiteUser create(String username, String user_nickname, String password);
    SiteUser getUser(String username);
}
