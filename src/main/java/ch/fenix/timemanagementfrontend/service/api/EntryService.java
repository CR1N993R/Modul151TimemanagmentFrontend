package ch.fenix.timemanagementfrontend.service.api;

import ch.fenix.timemanagementfrontend.models.Entry;
import ch.fenix.timemanagementfrontend.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntryService {
    private final TokenService tokenService;
    private final WebClient webClient;

    public List<Entry> getEntries() {
        return webClient.get().uri("localhost:8080/entries").header("Authorization", tokenService.getToken())
            .retrieve().bodyToMono(new ParameterizedTypeReference<List<Entry>>() {
            }).block();
    }

    public boolean deleteEntry(long id) {
        try {
            webClient.delete().uri("localhost:8080/entries/" + id).header("Authorization", tokenService.getToken())
                .retrieve().onStatus(HttpStatus::is4xxClientError, r -> Mono.error(new Exception()))
                .bodyToMono(String.class).block();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Entry createEntry(LocalDateTime checkIn, LocalDateTime checkOut, long categoryId) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("checkIn", checkIn);
        body.put("checkOut", checkOut);
        body.put("categoryId", categoryId + "");
        return webClient.post().uri("localhost:8080/entries").contentType(MediaType.APPLICATION_JSON)
            .bodyValue(body).header("Authorization", tokenService.getToken())
            .retrieve().bodyToMono(Entry.class).block();
    }

    public Entry editEntry(LocalDateTime checkIn, LocalDateTime checkOut, long categoryId, long id) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("checkIn", checkIn);
        body.put("checkOut", checkOut);
        body.put("categoryId", categoryId + "");
        try {
            return webClient.put().uri("localhost:8080/entries/" + id).contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body).header("Authorization", tokenService.getToken())
                .retrieve().onStatus(HttpStatus::is4xxClientError, r -> Mono.error(new Exception()))
                .bodyToMono(Entry.class).block();
        } catch (Exception e) {
            return null;
        }
    }
}
