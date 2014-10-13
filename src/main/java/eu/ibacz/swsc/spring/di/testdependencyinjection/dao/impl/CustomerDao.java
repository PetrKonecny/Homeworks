/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ibacz.swsc.spring.di.testdependencyinjection.dao.impl;

import eu.ibacz.swsc.spring.di.testdependencyinjection.dto.Customer;
import java.util.List;

/**
 *
 * @author Petr
 */
public interface CustomerDao {
     
    public List<Customer> findAll();
    public void save(Customer customer);
}
