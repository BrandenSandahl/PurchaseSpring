package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by branden on 3/9/16 at 12:58.
 */
@Controller
public class PurchasesController {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    CategoryRepository categoryRepository;


    @PostConstruct
    public void init() throws FileNotFoundException {
        if (customerRepository.count() < 1) { //if nothing is in the table

            File f = new File("customers.csv");
            Scanner s = new Scanner(f);
            s.nextLine();
            while (s.hasNext()) {
                String[] lineSplit = s.nextLine().split(",");
                Customer customer = new Customer(lineSplit[0], lineSplit[1]);
                customerRepository.save(customer);
            }
            s.close();
        }


        if (purchaseRepository.count() < 1) {
            File f = new File("purchases.csv");
            Scanner s = new Scanner(f);
            s.nextLine();
            while (s.hasNext()) {
                String[] lineSplit = s.nextLine().split(",");
                Purchase purchase = new Purchase(lineSplit[1], lineSplit[2], Integer.valueOf(lineSplit[3]));
                purchase.setCustomer(customerRepository.findOne(Integer.valueOf(lineSplit[0]))); //set the id to a customer

                Category categoryInDb = categoryRepository.findByCategory(lineSplit[4]); //see if we have a category in DB already

                if (categoryInDb == null) { //if the category has not yet been created
                    categoryInDb = new Category(lineSplit[4].toLowerCase()); //set the category
                    categoryRepository.save(categoryInDb);
                }
                purchase.setCategory(categoryInDb); //connect category to the category table
                purchaseRepository.save(purchase);
            }
            s.close();
        }
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String category, Integer page) {
        model.addAttribute("categories", categoryRepository.findAll()); //this is to list out all categories

        page = (page == null) ? 0 : page;
        Page<Purchase> p; //this is like a list kind of
        PageRequest pr = new PageRequest(page, 5); //this is the subset we want to request
        Category categoryObject = categoryRepository.findByCategory(category);

        if (category != null && !category.equals("back")) {

            p = purchaseRepository.findByCategory(pr, categoryObject);
        } else {
            p = purchaseRepository.findAll(pr); //lists out all records in purchase table with relations.
        }

        model.addAttribute("category",category );
        model.addAttribute("purchases",p);
        model.addAttribute("nextPage", page + 1 );
        model.addAttribute("showNext", p.hasNext());
        model.addAttribute("previousPage", page - 1);
        model.addAttribute("showPrevious", p.hasPrevious());
        model.addAttribute("totalPages", p.getTotalPages());
        return "home";
    }

}