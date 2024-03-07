package com.jakub.hrm.security;

import com.jakub.hrm.model.HrUser;
import com.jakub.hrm.repo.HrUserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtr sprawdzający przy każdym żądaniu, czy zalogowany użytkownik musi zmienić hasło.
 * Jeśli tak, użytkownik jest przekierowywany na stronę zmiany hasła. Wyjątkiem są ścieżki,
 * które są dozwolone do dostępu bez zmiany hasła, jak np. zasoby statyczne czy strona logowania.
 */
@Component
public class ChangePasswordFilter extends OncePerRequestFilter {

    @Autowired
    private HrUserRepo hrUserRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean changePasswordPageRequest = request.getRequestURI().equals("/resetpassword") ||
                request.getRequestURI().startsWith("/assets") ||
                request.getRequestURI().startsWith("/applicationform") ||
                request.getRequestURI().startsWith("/saveApplicationForm") ||
                request.getRequestURI().startsWith("/displayjoboffer") ||
                request.getRequestURI().startsWith("/savenewpassword") ||
                request.getRequestURI().equals("/logout");

        if (authentication != null && authentication.isAuthenticated() && !changePasswordPageRequest) {
            String username = ((UserDetails)authentication.getPrincipal()).getUsername();
            HrUser user = hrUserRepo.getByIdentification_Login(username);

            if (user.isPasswordChangeRequired()) {
                response.sendRedirect("/resetpassword");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
