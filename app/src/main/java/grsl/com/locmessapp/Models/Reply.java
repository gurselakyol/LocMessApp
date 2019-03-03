package grsl.com.locmessapp.Models;

public class Reply {

    private String userId;
    private String dateTime;
    private String replyText;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public Reply(String userId, String dateTime, String replyText) {
        this.userId = userId;
        this.dateTime = dateTime;
        this.replyText = replyText;
    }

    public Reply(){

    }
}
