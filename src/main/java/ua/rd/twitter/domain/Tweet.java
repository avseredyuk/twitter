package ua.rd.twitter.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="tweets")
public class Tweet {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idd;

    @Column(name="text")
    private String text;

    @Column(name="retweet_count")
    private Integer retweetsCount = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reply_to")
    private Tweet replyTo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "retweet_of")
    private Tweet retweetOf;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name="likes",
        joinColumns = @JoinColumn( name="tweet_id"),
        inverseJoinColumns = @JoinColumn( name="user_id")
    )
    private List<User> likes = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="mentions",
            joinColumns = @JoinColumn( name="tweet_id"),
            inverseJoinColumns = @JoinColumn( name="user_id")
    )
    private List<User> mentionedUsers = new ArrayList<>();

    public Tweet() {
    }

    public Tweet(Long idd) {
        this.idd = idd;
    }

    public Tweet(User user, String text, Long idd) {
        this.user = user;
        this.text = text;
        this.idd = idd;
    }

    public List<User> getLikes() {
        return likes;
    }

//    public void like(User user) {
//        if (!likes.contains(user)) {
//            likes.add(user);
//        }
//    }

    public void setMentionedUsers(List<User> users) {
        List<User> filteredUsers = users.stream().filter(u -> !u.equals(this.user))
                .collect(Collectors.toList());
        mentionedUsers.addAll(filteredUsers);
    }


//    public int getLikesCount() {
//        return likes.size();
//    }

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

    public Long getIdd() {
        return idd;
    }

    public void setIdd(Long idd) {
        this.idd = idd;
    }

    public List<User> getMentionedUsers() {
        return new ArrayList<>(mentionedUsers);
    }

    public Tweet getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Tweet replyTo) {
        this.replyTo = replyTo;
    }

    public Tweet getRetweetOf() {
        return retweetOf;
    }

    public void setRetweetOf(Tweet retweetOf) {
        this.retweetOf = retweetOf;
    }

    public Integer getRetweetsCount() {
        return retweetsCount;
    }

    public void setRetweetsCount(Integer retweetsCount) {
        this.retweetsCount = retweetsCount;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "user=" + user +
                ", text='" + text + '\'' +
                ", likes=" + likes +
                ", RT count=" + retweetsCount +
                ", mentionedUsers=" + mentionedUsers +
                ", idd=" + idd +
                '}';
    }
}
