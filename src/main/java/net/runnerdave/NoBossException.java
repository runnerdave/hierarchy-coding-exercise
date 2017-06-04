package net.runnerdave;

/**
 * Exception for the case when the company has no CEO.
 *
 * Created by runnerdave on 4/06/17.
 */
public class NoBossException extends Exception {
    public NoBossException(String message) {
        super(message);
    }
}
