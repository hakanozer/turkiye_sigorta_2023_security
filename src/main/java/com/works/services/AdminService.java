package com.works.services;

import com.works.entities.Admin;
import com.works.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    final AdminRepository adminRepository;
    final TinkEncDec tinkEncDec;
    final HttpServletRequest request;

    public void regiser() {
        Admin admin = new Admin();
        admin.setEmail("veli@mail.com");
        String cipherText = tinkEncDec.encrypt("12345");
        admin.setPassword(cipherText);
        adminRepository.save(admin);
    }

    public boolean login( Admin admin ) {
        Optional<Admin> optionalAdmin = adminRepository.findByEmailEqualsIgnoreCase(admin.getEmail());
        if ( optionalAdmin.isPresent() ) {
            Admin adm = optionalAdmin.get();
            String dbPass = tinkEncDec.decrypt( adm.getPassword() );
            if ( dbPass.equals(admin.getPassword()) ) {
                request.getSession().setAttribute("admin", adm);
                return true;
            }
        }
        return false;
    }

}
