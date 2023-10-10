package data.models;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Diary {
    private int id;

    private String username;

    private String password;

    private boolean isLock;

    private List<Entry> entries = new ArrayList<>();

    private LocalDateTime timeAndDate ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(boolean isLock) {
        this.isLock = isLock;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public LocalDateTime getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(LocalDateTime timeAndDate) {
        this.timeAndDate = timeAndDate;
    }


}
