package com.jakub.hrm.security;

import com.jakub.hrm.model.HrUser;
import com.jakub.hrm.repo.HrUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Serwis do ładowania danych użytkownika po nazwie użytkownika (loginie).
 * Wykorzystywany przez Spring Security do pobierania informacji o użytkowniku
 * podczas procesu autentykacji. Dostarcza obiekt UserDetails zawierający informacje
 * o zalogowanym użytkowniku, w tym jego uprawnieniach.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private HrUserRepo hrUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HrUser user = hrUserRepo.getByIdentification_Login(username);

        List<GrantedAuthority> authorities = new ArrayList<>();

        return new User(user.getIdentification().getLogin(), user.getIdentification().getPwd(), authorities);
    }
}
