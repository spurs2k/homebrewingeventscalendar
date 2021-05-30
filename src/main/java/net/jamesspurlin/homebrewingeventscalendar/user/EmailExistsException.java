package net.jamesspurlin.homebrewingeventscalendar.user;

public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }

}