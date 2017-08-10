package org.apnplace.utility;

import java.io.Serializable;

/**
 * Created by Kevin Racheal on 4/22/2016.
 */
public class CalDetails implements Serializable {
    public String id,title,start,end,allDay,endNew,eventDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getAllDay() {
        return allDay;
    }

    public void setAllDay(String allDay) {
        this.allDay = allDay;
    }

    public String getEndNew() {
        return endNew;
    }

    public void setEndNew(String endNew) {
        this.endNew = endNew;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }
}
