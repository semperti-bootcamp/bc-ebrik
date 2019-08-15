package com.semperti.trial.journals.service;

import com.semperti.trial.journals.model.Journal;
import com.semperti.trial.journals.model.Publisher;
import com.semperti.trial.journals.model.User;

import java.util.List;

public interface JournalService {

	List<Journal> listAll(User user);

	List<Journal> publisherList(Publisher publisher);

	Journal publish(Publisher publisher, Journal journal, Long categoryId);

	void unPublish(Publisher publisher, Long journalId);
}
