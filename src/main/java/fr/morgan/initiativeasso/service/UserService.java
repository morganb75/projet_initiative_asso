package fr.morgan.initiativeasso.service;

import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.UserDto;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

        void preInscriptionUser(UserDto user);
        Optional<User> findByEmail(String email);

        @Override
        UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
