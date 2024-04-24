package team.ybj.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.ybj.mappers.AdminMapper;
import team.ybj.mappers.CustomerMapper;
import team.ybj.pojo.LoginAdmin;
import team.ybj.pojo.LoginUser;
import team.ybj.pojo.YbjAdmin;
import team.ybj.pojo.YbjCustomer;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    CustomerMapper customerMapper;
    @Resource
    AdminMapper adminMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // get admin details
        YbjAdmin admin = adminMapper.findAdminByUsername(username);
        if (admin != null) {
            return new LoginAdmin(admin);
        }

        // get customer details
        YbjCustomer customer = customerMapper.getValidCustomerByEmail(username);
        if (customer == null) {
            throw new RuntimeException("failed to find customer by username: " + username);
        }

        // TODO: get corresponding authentication


        return new LoginUser(customer);
    }
}
