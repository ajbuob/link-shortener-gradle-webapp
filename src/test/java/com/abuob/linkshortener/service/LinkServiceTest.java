package com.abuob.linkshortener.service;

import com.abuob.linkshortener.pojo.Link;
import com.abuob.linkshortener.repository.LinkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LinkServiceTest {

    private LinkRepository linkRepository = mock(LinkRepository.class);
    private LinkService linkService = new LinkServiceImpl("http://some-domain.com/", linkRepository);

    @BeforeEach
    public void setup() {
        //Same object returned when passed as a parameter
        when(linkRepository.save(any()))
                .thenAnswer((Answer<Mono<Link>>) invocationOnMock -> Mono.just((Link) invocationOnMock.getArguments()[0]));
    }

    @Test
    public void shortensLink() {
        StepVerifier.create(linkService.shortenLink("http://spring.io"))
                .expectNextMatches(result -> result != null && result.length() > 0
                        && result.startsWith("http://some-domain.com/"))
                .expectComplete()
                .verify();
    }
}
