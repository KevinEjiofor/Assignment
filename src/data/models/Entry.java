package data.models;

import java.time.LocalDateTime;

public class Entry {

    private int id;

    private String title;

    private String body;

    private String ownerName;

    private LocalDateTime updatedTimeAndDate = LocalDateTime.now();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public LocalDateTime getUpdatedTimeAndDate() {
        return updatedTimeAndDate;
    }

    public void setUpdatedTimeAndDate(LocalDateTime updatedTimeAndDate) {
        this.updatedTimeAndDate = updatedTimeAndDate;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", updatedTimeAndDate=" + updatedTimeAndDate +
                '}';
    }
}

