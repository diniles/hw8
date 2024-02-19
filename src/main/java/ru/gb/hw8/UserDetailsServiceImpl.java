package ru.gb.hw8;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("User with name '" + name + "' not found"));
        return UserDetailsImpl.build(user);
    }
}
