package com.example.cinemachainmanagement.service.impl;

import com.example.cinemachainmanagement.DTO.CustomerDTO;
import com.example.cinemachainmanagement.Mapper.Mappers;
import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.enums.EMessage;
import com.example.cinemachainmanagement.repositories.CustomerRepository;
import com.example.cinemachainmanagement.service.AccountService;
import com.example.cinemachainmanagement.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImp.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public Customer authenticateCustomer(String email, String password) {
        Customer customer = customerRepository.findByEmail(email).orElse(null);
        if (customer != null) {
            String passHash = accountService.hashPassword(password);
            if (passHash.equals(customer.getPassHash())) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public boolean registerCustomer(CustomerDTO customerDTO) {
        try {
            Customer customer_exist = customerRepository.findByEmail(customerDTO.getEmail()).orElse(null);
            if(customer_exist==null){
                String passHash = accountService.hashPassword(customerDTO.getPassHash());
                customerDTO.setPassHash(passHash);
                customerDTO.setAccountBalance(0);
                Customer customer = Mappers.convertToEntity(customerDTO, Customer.class);
                customerRepository.save(customer);
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }

    @Override
    public CustomerDTO getCustomerById(Long customerDTO_id){
        Customer customer = customerRepository.findById(customerDTO_id).orElse(null);
        if(customer!=null){
            return Mappers.convertToDto(customer, CustomerDTO.class);
        }
        return null;

    }

    @Override
    public EMessage changePassword(String email, String oldPassword, String newPassword) {
        try{
            String oldPasswordHash = accountService.hashPassword(oldPassword);
            String newPasswordHash = accountService.hashPassword(newPassword);
            Customer customer = customerRepository.findByEmail(email).orElse(null);
            if(customer == null)
                return EMessage.CUSTOMER_NOT_EXIST;
            if(customer.getPassHash().equals(oldPasswordHash)){
                customer.setPassHash(newPasswordHash);
                customerRepository.save(customer);
                return EMessage.CHANGE_PASS_WORD_SUCCESS;
            }
            return EMessage.OLD_PASS_NOT_MATCH;
        }catch(Exception e){
            logger.error("Lỗi khi đổi mật khẩu cho khách hàng: " + email + ". Lỗi: " + e.getMessage());
            return EMessage.CHANGE_PASS_WORD_NOT_SUCCESS;
        }
    }
}
