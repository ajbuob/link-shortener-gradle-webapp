package com.abuob.linkshortener.web;

public class CreateLinkRequest {

    private String link;

    public CreateLinkRequest() {
    }

    public CreateLinkRequest(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
