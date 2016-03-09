package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by branden on 3/9/16 at 16:55.
 */
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category findByCategory(String name);
}
