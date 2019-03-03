package grsl.com.locmessapp.Models;

public class Messages {

    private String location;
    private String message;
    private String whoCanSee;
    private String dateTime;
    private int seenCount;
    private int replyCount;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getWhoCanSee() {
        return whoCanSee;
    }

    public void setWhoCanSee(String whoCanSee) {
        this.whoCanSee = whoCanSee;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getSeenCount() {
        return seenCount;
    }

    public void setSeenCount(int seenCount) {
        this.seenCount = seenCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public Messages(String location, String message, String whoCanSee, String dateTime, int seenCount, int replyCount) {
        this.location = location;
        this.message = message;
        this.whoCanSee = whoCanSee;
        this.dateTime = dateTime;
        this.seenCount = seenCount;
        this.replyCount = replyCount;
    }

    public Messages(){

    }
}
