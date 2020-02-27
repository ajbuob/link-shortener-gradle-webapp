package com.abuob.linkshortener.repository;

import com.abuob.linkshortener.pojo.Link;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@SpringBootTest

public class RedisLinkRepositoryTest {

    @Autowired
    private RedisLinkRepository redisLinkRepository;

    @Test
    public void returnsSameLinkAsArgument() {
        Link link = new Link("http://spring.io", "385AO2a");
        StepVerifier.create(redisLinkRepository.save(link))
                .expectNext(link)
                .verifyComplete();
    }

    @Test
    public void savesInRedis() {
        Link link = new Link("http://spring.io", "385AO2a");
        StepVerifier.create(redisLinkRepository.save(link)
                .flatMap(__ -> redisLinkRepository.findByKey(link.getKey())))
                .expectNext(link)
                .verifyComplete();
    }
}