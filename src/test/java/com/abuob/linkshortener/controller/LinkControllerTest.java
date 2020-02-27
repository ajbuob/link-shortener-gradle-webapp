package com.abuob.linkshortener.controller;

import com.abuob.linkshortener.pojo.Link;
import com.abuob.linkshortener.service.LinkService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = LinkController.class)
public class LinkControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private LinkService linkService;


    @Test
    public void shortensLink() {
        when(linkService.shortenLink("https://spring.io")).thenReturn(Mono.just("http://localhost:8080/385AO2a"));
        webTestClient.post()
                .uri("/link")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"link\":\"https://spring.io\"}")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.shortenedLink")
                .value(val -> assertThat(val).isEqualTo("http://localhost:8080/385AO2a"));
    }

    @Test
    public void redirectsToOriginalLink() {
        when(linkService.getOriginalLink("385AO2a"))
                .thenReturn(Mono.just(new Link("http://spring.io", "385AO2a")));
        webTestClient.get()
                .uri("/385AO2a")
                .exchange()
                .expectStatus()
                .isPermanentRedirect()
                .expectHeader()
                .value("Location", location -> assertThat(location).isEqualTo("http://spring.io"));
    }
}