package ua.rd.twitter.domain;


public class Tweet {
    private User user;
     private String text;

    public Tweet() {
    }

    public Tweet(User user, String text) {
        this.user = user;
        this.text = text;
    }    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Tweet{" + "text=" + text + '}';
    }
    
    
}
