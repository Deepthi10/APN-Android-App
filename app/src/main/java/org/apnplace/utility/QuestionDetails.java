package org.apnplace.utility;

import java.io.Serializable;

/**
 * Created by Kevin Racheal on 5/13/2016.
 */
public class QuestionDetails  implements Serializable {

    public String topic,question_id,question,question_by,question_to,date,user_name;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion_by() {
        return question_by;
    }

    public void setQuestion_by(String question_by) {
        this.question_by = question_by;
    }

    public String getQuestion_to() {
        return question_to;
    }

    public void setQuestion_to(String question_to) {
        this.question_to = question_to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
