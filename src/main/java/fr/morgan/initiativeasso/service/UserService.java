package fr.morgan.initiativeasso.service;

import fr.morgan.initiativeasso.model.Parrain;
import fr.morgan.initiativeasso.model.Porteur;
import fr.morgan.initiativeasso.model.SalarieAsso;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.UserDto;
import fr.morgan.initiativeasso.model.enums.UserRole;

import java.util.Optional;

public interface UserService {

        void preInscriptionUser(UserDto user, UserRole role);
        Optional<User> findByEmail(String email);
//        void preInscrireUser(Porteur porteur);
//        void preInscrireUser(Parrain parrain);
//        void preInscrireUser(SalarieAsso salarieAsso);
}
