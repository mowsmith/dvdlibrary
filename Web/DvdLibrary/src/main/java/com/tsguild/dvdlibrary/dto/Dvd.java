package com.tsguild.dvdlibrary.dto;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Morgan Smith
 */
public class Dvd {

    @NotBlank(message = "Title can not be empty.")
    @Length(min = 2, max = 50, message = "Title must between 2 and 50 characters in length.")
    private String title;
    private String rating;
    @NotNull(message = "Rating can not be empty.")
    private MpaaRating mpaaRating;
    @Length(min = 2, max = 50, message = "Director must between 2 and 50 characters in length.")
    @NotBlank(message = "Director can not be empty.")
    private String director;
    @Length(min = 2, max = 50, message = "Studio must between 2 and 50 characters in length.")
    @NotBlank(message = "Studio can not be empty.")
    private String studio;
    @Length(min = 2, max = 200, message = "User Note must between 2 and 200 characters in length.")
    private String userNote;
    private int id;
    private String date;
    @NotNull(message = "Release date can not be empty.")
    private Calendar releaseDate;

    public Dvd() {
    }

    public Dvd(String title, MpaaRating mpaaRating, String director, String studio, String userNote, Calendar releaseDate) {
        this.title = title;
        this.mpaaRating = mpaaRating;
        this.director = director;
        this.studio = studio;
        this.userNote = userNote;
        this.releaseDate = releaseDate;
    }

    public Dvd(String title, MpaaRating mpaaRating, String director, String studio, String userNote, int id, Calendar releaseDate) {
        this.title = title;
        this.mpaaRating = mpaaRating;
        this.director = director;
        this.studio = studio;
        this.userNote = userNote;
        this.id = id;
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    public void setRating(String rating) {

        this.rating = rating;

        switch (rating) {
            case "G":
                mpaaRating = MpaaRating.G;
                break;
            case "PG":
                mpaaRating = MpaaRating.PG;
                break;
            case "PG13":
                mpaaRating = MpaaRating.PG13;
                break;
            case "R":
                mpaaRating = MpaaRating.R;
                break;
            case "NC17":
                mpaaRating = MpaaRating.NC17;
                break;
            case "NR":
                mpaaRating = MpaaRating.NR;
                break;
            default:
                mpaaRating = null;
        }
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getReleaseDate() {
        return releaseDate;
    }

    public void setDate(String date) {

        this.date = date;

        String[] stringDateArr = date.split("-");
        int[] dateArr = new int[3];

        try {
            dateArr[0] = Integer.parseInt(stringDateArr[0]);
            dateArr[1] = Integer.parseInt(stringDateArr[1]);
            dateArr[2] = Integer.parseInt(stringDateArr[2]);

            releaseDate = new GregorianCalendar(dateArr[0], dateArr[1] - 1, dateArr[2]);
        } catch (NumberFormatException numberFormatException) {
            releaseDate = null;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.title);
        hash = 67 * hash + Objects.hashCode(this.mpaaRating);
        hash = 67 * hash + Objects.hashCode(this.director);
        hash = 67 * hash + Objects.hashCode(this.studio);
        hash = 67 * hash + Objects.hashCode(this.userNote);
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.releaseDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dvd other = (Dvd) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.userNote, other.userNote)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.mpaaRating != other.mpaaRating) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        return true;
    }

}
