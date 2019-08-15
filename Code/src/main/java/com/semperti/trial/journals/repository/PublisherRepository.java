package com.semperti.trial.journals.repository;

import com.semperti.trial.journals.model.Publisher;
import com.semperti.trial.journals.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Optional<Publisher> findByUser(User user);

}
