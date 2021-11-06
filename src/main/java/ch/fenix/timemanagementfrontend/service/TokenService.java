package ch.fenix.timemanagementfrontend.service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private static String token;

    public void setToken(String token) {
        TokenService.token = "Bearer " + token;
    }

    public void clearToken() {
        TokenService.token = "";
    }

    public String getToken() {
        return TokenService.token;
    }
}
