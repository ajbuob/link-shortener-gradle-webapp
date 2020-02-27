package com.abuob.linkshortener.web;

public class CreateLinkResponse {

    private String shortenedLink;

    public CreateLinkResponse() {
    }

    public CreateLinkResponse(String shortenedLink) {
        this.shortenedLink = shortenedLink;
    }

    public String getShortenedLink() {
        return shortenedLink;
    }

    public void setShortenedLink(String shortenedLink) {
        this.shortenedLink = shortenedLink;
    }
}
