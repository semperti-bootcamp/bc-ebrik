package com.semperti.trial.journals.repository;

import com.semperti.trial.journals.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
