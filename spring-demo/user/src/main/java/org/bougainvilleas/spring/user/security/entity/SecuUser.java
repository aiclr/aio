package org.bougainvilleas.spring.user.security.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.bougainvilleas.spring.commons.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class SecuUser implements UserDetails {

    private static final long serialVersionUID=1L;

    //当前登陆用户
    private transient User currentUserInfo;
    //当前权限
    private List<String> permissionValueList;


    public SecuUser(User currentUserInfo) {
        if (currentUserInfo != null) {
            this.currentUserInfo = currentUserInfo;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        for(String permissionValue:permissionValueList){
            if(StringUtils.isEmpty(permissionValue)) continue;
            SimpleGrantedAuthority authority=new SimpleGrantedAuthority(permissionValue);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return currentUserInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return currentUserInfo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
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
