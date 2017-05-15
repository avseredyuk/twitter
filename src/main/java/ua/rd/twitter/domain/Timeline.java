package ua.rd.twitter.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="timelines")
public class Timeline {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval=true)
    @JoinTable(
            name = "timeline_tweets",
            joinColumns = @JoinColumn(name = "timeline_id"),
            inverseJoinColumns = @JoinColumn(name = "tweet_id")
    )
    private List<Tweet> tweets = new ArrayList<>();


    public Timeline() {
    }

    public Timeline(List<Tweet> tweets, User user, Long id) {
        this.tweets.addAll(tweets);
        this.user = user;
        this.id = id;
    }

    public void put(Tweet tweet) {
        tweets.add(tweet);
    }

//    public void putAll(Iterable<Tweet> tweetsSource) {
//        tweetsSource.forEach(this::put);
//    }

    public List<Tweet> getTweets() {
        return new ArrayList<>(tweets);
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void remove(Tweet tweet) {
        tweets.remove(tweet);
    }

    @Override
    public String toString() {
        return "Timeline{" +
                "id=" + id +
                ", user=" + user +
                ", tweets=" + tweets +
                '}';
    }
}
