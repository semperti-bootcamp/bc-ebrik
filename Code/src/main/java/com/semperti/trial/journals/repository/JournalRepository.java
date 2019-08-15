package com.semperti.trial.journals.repository;

import com.semperti.trial.journals.model.Journal;
import com.semperti.trial.journals.model.Publisher;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface JournalRepository extends CrudRepository<Journal, Long> {

    Collection<Journal> findByPublisher(Publisher publisher);

    List<Journal> findByCategoryIdIn(List<Long> ids);

}
