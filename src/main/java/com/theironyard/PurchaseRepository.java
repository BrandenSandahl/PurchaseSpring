package com.theironyard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by branden on 3/9/16 at 12:55.
 */
public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Integer> {
    List<Purchase> findByCustomer(Customer customer);
    Page<Purchase> findByCategory(Pageable pageable, Category category);
    Page<Purchase> findAll(Pageable pageable);


}
