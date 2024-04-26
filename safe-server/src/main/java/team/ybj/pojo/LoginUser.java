package team.ybj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Collection;
import java.util.List;

/**
 * LoginUser中存放数据库中根据username查询出的信息
 * 它的作用是作为UserDetails接口的实现类在springSecurity中与用户传入的登录信息进行比对
 *
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class LoginUser implements UserDetails {

    YbjCustomer customer;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return customer.getCpassword();
    }

    @Override
    public String getUsername() {
        return customer.getCemail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return customer.getCvalid().equals("1");
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
