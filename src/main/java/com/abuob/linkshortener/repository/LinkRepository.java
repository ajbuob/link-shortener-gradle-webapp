package com.abuob.linkshortener.repository;


import com.abuob.linkshortener.pojo.Link;
import reactor.core.publisher.Mono;

public interface LinkRepository {

    Mono<Link> save(Link link);

    Mono<Link> findByKey(String key);
}

