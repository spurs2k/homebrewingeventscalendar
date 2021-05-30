package net.jamesspurlin.homebrewingeventscalendar.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
public class Event extends AbstractEntity {

    private static final String START_DATE_FORMAT_PATTERN = "MM/dd/yyyy";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT
            = new SimpleDateFormat(START_DATE_FORMAT_PATTERN);

    @NotNull(message = "Please enter a valid date")
    @DateTimeFormat(pattern = START_DATE_FORMAT_PATTERN)
    private Date startDate;

    @NotBlank(message = "Title of event is required")
    private String title;

    @NotBlank(message = "Location is required. Please enter city and state")
    private String location;

    @NotNull(message = "Please enter a valid date")
    @DateTimeFormat(pattern = START_DATE_FORMAT_PATTERN)
    private Date registrationDeadline;

    @NotBlank(message = "Please enter the registration link for the event.")
    private String registrationLink;

    public Event() {}

    public Event(@NotNull Date startDate,
                 @NotBlank String title,
                 @NotBlank String location,
                 @NotNull Date registrationDeadline,
                 @NotBlank String registrationLink) {

        if (startDate == null)
            throw new IllegalArgumentException("Start date may not be null");

        if (title == null || title.length() == 0)
            throw new IllegalArgumentException("Title may not be blank");

        if (location == null || location.length() == 0)
            throw new IllegalArgumentException("Location may not be blank");

        if (registrationDeadline == null)
            throw new IllegalArgumentException("Registration deadline date may not be null");

        if (registrationLink == null || registrationLink.length() == 0)
            throw new IllegalArgumentException("Registration link may not be blank");


        this.startDate = startDate;
        this.title = title;
        this.location = location;
        this.registrationDeadline = registrationDeadline;
        this.registrationLink = registrationLink;
    }

    public String getFormattedStartDate() {
        return Event.SIMPLE_DATE_FORMAT.format(startDate);
    }

    public String getFormattedRegistrationDeadline() {
        return Event.SIMPLE_DATE_FORMAT.format(registrationDeadline);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(Date registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public String getRegistrationLink() {
        return registrationLink;
    }

    public void setRegistrationLink(String registrationLink) {
        this.registrationLink = registrationLink;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", registrationDeadline'" + registrationDeadline + '\'' +
                ", registrationLink'" + registrationLink + '\'' +
                ", startDate=" + startDate +
                '}';
    }

}
