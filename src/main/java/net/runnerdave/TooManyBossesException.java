package net.runnerdave;

/**
 * Exception for the case when the company has more than one CEO.
 *
 * Created by runnerdave on 4/06/17.
 */
public class TooManyBossesException extends Exception {
    public TooManyBossesException(String message) {
        super(message);
    }
}
