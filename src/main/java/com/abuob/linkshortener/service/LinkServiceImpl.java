package com.abuob.linkshortener.service;

import com.abuob.linkshortener.pojo.Link;
import com.abuob.linkshortener.repository.LinkRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LinkServiceImpl implements LinkService {

    private final String baseUrl;
    private final LinkRepository linkRepository;

    public LinkServiceImpl(@Value("${app.baseUrl}") String baseUrl, LinkRepository linkRepository) {
        this.baseUrl = baseUrl;
        this.linkRepository = linkRepository;
    }

    public Mono<String> shortenLink(String link) {
        String randomKey = RandomStringUtils.randomAlphabetic(6);
        return linkRepository.save(new Link(link, randomKey))
                .map(result -> baseUrl + result.getKey());
    }

    public Mono<Link> getOriginalLink(String key) {
        return linkRepository.findByKey(key);
    }
}
