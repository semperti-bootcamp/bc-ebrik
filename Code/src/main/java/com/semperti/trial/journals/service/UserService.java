package com.semperti.trial.journals.service;

import java.util.Optional;

import com.semperti.trial.journals.model.User;

public interface UserService {

    Optional<User> getUserByLoginName(String loginName);

    void subscribe(User user, Long categoryId);

    User findById(Long id);

}