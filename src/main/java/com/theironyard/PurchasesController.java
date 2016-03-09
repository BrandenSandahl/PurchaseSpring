package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
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
//       purchaseRepository.deleteAll();
//        customerRepository.deleteAll();
//        categoryRepository.deleteAll();
        if (customerRepository.count() < 1) {

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
                purchase.setCustomer(customerRepository.findOne(Integer.valueOf(lineSplit[0])));
                Category categoryInDb = categoryRepository.findByCategory(lineSplit[4]);

                if (categoryInDb == null) {
                    categoryInDb = new Category(lineSplit[4]);
                    categoryRepository.save(categoryInDb);
                }
                purchase.setCategory(categoryInDb);
                purchaseRepository.save(purchase);
            }
            s.close();
        }
    }


    @RequestMapping(name = "/", method = RequestMethod.GET)
    public String home(Model model) {

        model.addAttribute("purchases", purchaseRepository.findAll());
        return "home";
    }

}