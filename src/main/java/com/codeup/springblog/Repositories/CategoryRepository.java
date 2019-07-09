package com.codeup.springblog.Repositories;

import com.codeup.springblog.Models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
