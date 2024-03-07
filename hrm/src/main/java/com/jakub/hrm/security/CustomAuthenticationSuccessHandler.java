package com.jakub.hrm.security;

import com.jakub.hrm.model.HrUser;
import com.jakub.hrm.repo.HrUserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Handler sukcesu autentykacji, który decyduje o dalszym przekierowaniu użytkownika
 * po pomyślnym zalogowaniu. Jeśli użytkownik musi zmienić hasło, jest przekierowywany
 * na stronę zmiany hasła. W przeciwnym razie przechodzi na główną stronę/dashboard.
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private HrUserRepo hrUserRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = authentication.getPrincipal().toString();
        HrUser user = hrUserRepo.getByIdentification_Login(username);

        if (user.isPasswordChangeRequired()) {
            response.sendRedirect("/resetpassword");
        } else {
            response.sendRedirect("/dashboard");
        }
    }
}
