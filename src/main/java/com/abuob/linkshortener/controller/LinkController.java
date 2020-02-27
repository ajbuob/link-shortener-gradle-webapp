package com.abuob.linkshortener.controller;

import com.abuob.linkshortener.service.LinkService;
import com.abuob.linkshortener.web.CreateLinkRequest;
import com.abuob.linkshortener.web.CreateLinkResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/link")
    Mono<CreateLinkResponse> create(@RequestBody CreateLinkRequest request) {
        return linkService.shortenLink(request.getLink())
                .map(CreateLinkResponse::new);
    }

    @GetMapping("/{key}")
    Mono<ResponseEntity<Object>> getLink(@PathVariable String key) {
        return linkService.getOriginalLink(key)
                .map(link -> ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                        .header("Location", link.getOriginalLink())
                        .build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
