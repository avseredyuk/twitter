package ua.rd.twitter.exception;

/**
 * Created by Anton_Serediuk on 4/28/2017.
 */
public class NoSuchTweetException extends RuntimeException {
    private Long id;

    public NoSuchTweetException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
