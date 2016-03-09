package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by branden on 3/9/16 at 12:55.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findByName(String name);
}


