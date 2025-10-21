package com.code.healthcare.appointment_system.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import com.code.healthcare.appointment_system.repository.UserRepository;
import com.code.healthcare.appointment_system.model.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return CustomUserDetails.create(user);
    }

    public CustomUserDetails loadUserById(Long id) {
        User user = userRepo.findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return CustomUserDetails.create(user);
    }
}
