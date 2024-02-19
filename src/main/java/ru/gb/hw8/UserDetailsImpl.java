package ru.gb.hw8;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private Long id;
    private String name;
    private String password;
    private Collection<? extends GrantedAuthority> role;

    public UserDetailsImpl(Long id, String name, String password, Collection<? extends GrantedAuthority> role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(user.getRole()));
        return new UserDetailsImpl(
                user.getId(),
                user.getName(),
                user.getPassword(),
                authorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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
