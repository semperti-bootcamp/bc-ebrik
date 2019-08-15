package com.semperti.trial.journals.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semperti.trial.journals.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginName(String loginName);

}
