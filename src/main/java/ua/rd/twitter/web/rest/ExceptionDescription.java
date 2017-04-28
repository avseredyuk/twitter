package ua.rd.twitter.web.rest;

/**
 * Created by Anton_Serediuk on 4/28/2017.
 */
public class ExceptionDescription {
    public final String name;
    public final String eMessage;

    public ExceptionDescription(String name, String eMessage) {
        this.name = name;
        this.eMessage = eMessage;
    }
}
