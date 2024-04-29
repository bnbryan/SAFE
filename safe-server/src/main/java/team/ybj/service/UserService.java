package team.ybj.service;

import team.ybj.dto.CustomerBasic;
import team.ybj.pojo.YbjCustomer;

public interface UserService {
    CustomerBasic findCustomerByEmail(String email);
}
