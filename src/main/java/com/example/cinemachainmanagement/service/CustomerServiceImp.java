package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.DTO.CustomerDTO;
import com.example.cinemachainmanagement.Mapper.Mappers;
import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer authenticateCustomer(String email, String password) {
        // Lấy thông tin khách hàng từ cơ sở dữ liệu dựa trên email
        Customer customer = customerRepository.findByEmail(email).orElse(null);

        // Kiểm tra xem khách hàng có tồn tại không
        if (customer != null) {
            // So sánh mật khẩu đã nhập với mật khẩu trong cơ sở dữ liệu
            if (password.equals(customer.getPassHash())) {
                return customer; // Trả về thông tin khách hàng nếu mật khẩu trùng khớp
            }
        }
        return null; // Trả về null nếu không tìm thấy hoặc mật khẩu không đúng
    }

    @Override
    public boolean registerCustomer(CustomerDTO customerDTO) {
        try {
            Customer customer_exist = customerRepository.findByEmail(customerDTO.getEmail()).orElse(null);
            if(customer_exist==null){
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
}
