package com.abuob.linkshortener.pojo;

import java.util.Objects;

public class Link {

    private String originalLink;
    private String key;

    public Link(String originalLink, String key) {
        this.originalLink = originalLink;
        this.key = key;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(key, link.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
