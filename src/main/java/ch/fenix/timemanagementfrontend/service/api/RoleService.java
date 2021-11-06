package ch.fenix.timemanagementfrontend.service.api;

import ch.fenix.timemanagementfrontend.models.Role;
import ch.fenix.timemanagementfrontend.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final TokenService tokenService;
    private final WebClient webClient;

    public List<Role> getRoles() {
        try {
            return webClient.get().uri("localhost:8080/role").header("Authorization", tokenService.getToken())
                    .retrieve().onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new Exception()))
                    .bodyToMono(new ParameterizedTypeReference<List<Role>>(){}).block();
        }catch (Exception e) {
            return null;
        }
    }

    public boolean setRoleToUser(long userId, long roleId) {
        try {
            Map<String, Long> body = new HashMap<>();
            body.put("userId", userId);
            body.put("roleId", roleId);
            webClient.post().uri("localhost:8080/role").contentType(MediaType.APPLICATION_JSON).bodyValue(body)
                    .header("Authorization", tokenService.getToken()).retrieve()
                    .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new Exception()))
                    .bodyToMono(Role.class).block();
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
