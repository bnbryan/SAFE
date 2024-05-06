package team.ybj.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ybj.dto.CustomerBasic;
import team.ybj.exception.NoDataException;
import team.ybj.mappers.AddressMapper;
import team.ybj.mappers.CustomerMapper;
import team.ybj.pojo.YbjAddress;
import team.ybj.pojo.YbjCustomer;
import team.ybj.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    CustomerMapper customerMapper;
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public CustomerBasic findCustomerByEmail(String email) {

        YbjCustomer customer = customerMapper.getCustomerByEmail(email);
        YbjAddress address = addressMapper.getAddressByAdid(customer.getCid());
        if (customer == null) {
            throw new NoDataException("Can't find customer by email: " + email);
        } else {
            return new CustomerBasic(customer.getCid(), customer.getClname(), customer.getCfname(), customer.getCemail(), address);
        }
    }
}
