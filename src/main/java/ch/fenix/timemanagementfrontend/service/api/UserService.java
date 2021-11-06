package ch.fenix.timemanagementfrontend.service.api;

import ch.fenix.timemanagementfrontend.models.TokenResponse;
import ch.fenix.timemanagementfrontend.models.User;
import ch.fenix.timemanagementfrontend.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final WebClient webClient;
    private final TokenService tokenService;

    public boolean deleteUser(long id) {
        try {
            webClient.delete().uri("localhost:8080/users/" + id).header("Authorization", tokenService.getToken())
                .retrieve().onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new Exception()))
                .bodyToMono(String.class).block();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean login(String username, String password) {
        try {
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("username", username);
            body.add("password", password);
            String accessToken = webClient.post().uri("localhost:8080/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).body(BodyInserters.fromFormData(body))
                .retrieve().onStatus(HttpStatus::is4xxClientError, r -> Mono.error(new Exception()))
                .bodyToMono(TokenResponse.class).block().getToken();
            tokenService.setToken(accessToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User createUser(String username, String password) {
        try {
            HashMap<String, String> body = new HashMap<>();
            body.put("username", username);
            body.put("password", password);
            return webClient.post().uri("localhost:8080/users").contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body).header("Authorization", tokenService.getToken()).retrieve()
                .onStatus(HttpStatus::is4xxClientError, r -> Mono.error(new Exception()))
                .bodyToMono(User.class).block();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User editUser(String password) {
        HashMap<String, String> body = new HashMap<>();
        if (password != null && password.length() != 0) {
            body.put("password", password);
        }
        try {
            return webClient.put().uri("localhost:8080/users").contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body).header("Authorization", tokenService.getToken()).retrieve()
                .onStatus(HttpStatus::is4xxClientError, r -> Mono.error(new Exception()))
                .bodyToMono(User.class).block();
        } catch (Exception e) {
            return null;
        }
    }

    public User editUser(String username, String password, long id) {
        HashMap<String, String> body = new HashMap<>();
        if (username != null && username.length() != 0) {
            body.put("username", username);
        }
        if (password != null && password.length() != 0) {
            body.put("password", password);
        }
        try {
            return webClient.put().uri("localhost:8080/users/"+ id).contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(body).header("Authorization", tokenService.getToken()).retrieve()
                    .onStatus(HttpStatus::is4xxClientError, r -> Mono.error(new Exception()))
                    .bodyToMono(User.class).block();
        } catch (Exception e) {
            return null;
        }
    }

    public User getUser() {
        return webClient.get().uri("localhost:8080/users").header("Authorization", tokenService.getToken())
            .retrieve().bodyToMono(User.class).block();
    }

    public List<User> getAllUsers() {
        try {
            return webClient.get().uri("localhost:8080/users/all").header("Authorization", tokenService.getToken())
                    .retrieve().onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new Exception()))
                    .bodyToMono(new ParameterizedTypeReference<List<User>>(){}).block();
        } catch (Exception e) {
            return null;
        }
    }
}
