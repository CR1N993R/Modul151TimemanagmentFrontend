package ch.fenix.timemanagementfrontend.service.api;

import ch.fenix.timemanagementfrontend.models.Category;
import ch.fenix.timemanagementfrontend.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final TokenService tokenService;
    private final WebClient webClient;

    public Category createCategory(Category category) {
        return webClient.post().uri("localhost:8080/categories").bodyValue(category).header("Authorization", tokenService
                .getToken()).retrieve().bodyToMono(Category.class).block();
    }

    public boolean deleteCategory(long id) {
        try {
            webClient.delete().uri("localhost:8080/categories/" + id).header("Authorization", tokenService.getToken())
                    .retrieve().onStatus(HttpStatus::is4xxClientError, r -> Mono.error(new Exception(r.rawStatusCode() + "")))
                    .bodyToMono(String.class).block();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Category> getCategories() {
        return webClient.get().uri("localhost:8080/categories").header("Authorization", tokenService.getToken())
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<Category>>() {}).block();
    }
}
