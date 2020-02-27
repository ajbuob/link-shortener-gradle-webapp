package com.abuob.linkshortener.service;

import com.abuob.linkshortener.pojo.Link;
import reactor.core.publisher.Mono;

public interface LinkService {

    Mono<String> shortenLink(String link);

    Mono<Link> getOriginalLink(String key);
}
