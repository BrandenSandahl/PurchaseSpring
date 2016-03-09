package com.theironyard;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by branden on 3/9/16 at 12:55.
 */
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    List<Purchase> findByCustomer(Customer customer);


}
