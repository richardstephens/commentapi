package dev.richst.commentapi.restmodel;

public class Page {

    private int id;
    private String url;

    public Page(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
