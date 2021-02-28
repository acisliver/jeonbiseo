package com.example.demo.config.auth;

import com.example.demo.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//security가 로그인 요청을 가로채서 수행하면 userDetails타입의 오브젝트를 고유 security 세션저장소에 저장
@Getter //BoardApiController에서 user 정보를 넘겨주기 위해 getter가 필요
public class PrincipalDetail implements UserDetails {

    private final User user;

    public PrincipalDetail(User user){
        this.user=user;
    }

    //권한
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors=new ArrayList<GrantedAuthority>();
        collectors.add(()->{
            //GrantedAuthority의 getAuthority함수를 사용한 것.
            return "ROLE_"+user.getRole(); //spring에서 역할에 대해 리턴을 받을 때 'ROLE_'를 꼭 넣어야함(규칙)
        });
        return collectors;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    //계정이 만료되지 않았는지 리턴(true:만료되지 않음)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨 있는지 리턴(true:만료되지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호가 만료 되었는지 리턴(true:만료되지 않음)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 활성화가 되었는지 확인(true:만료되지 않음)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
