package net.jamesspurlin.homebrewingeventscalendar.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
public class Event extends AbstractEntity {

    private static final String START_DATE_FORMAT_PATTERN = "MM/dd/yyyy";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT
            = new SimpleDateFormat(START_DATE_FORMAT_PATTERN);

    @NotNull
    @DateTimeFormat(pattern = START_DATE_FORMAT_PATTERN)
    private Date startDate;

    @NotNull
    private String title;

    @NotNull
    private String location;

    @NotNull
    @DateTimeFormat(pattern = START_DATE_FORMAT_PATTERN)
    private Date registrationDeadline;

    @NotNull
    private String registrationLink;

    public Event() {}

    public Event(@NotNull Date startDate,
                 @NotNull String title,
                 @NotNull String location,
                 @NotNull Date registrationDeadline,
                 @NotNull String registrationLink) {

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
