package org.apnplace.model;

import org.apnplace.utility.QuizDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dlakshmi on 10/12/2016.
 */
public class QuizResponse  {

    public String question_id,question;
    List<QuizDetails> answer_opt = new ArrayList<QuizDetails>();

    public List<QuizDetails> getAnswer_opt() {
        return answer_opt;
    }

    public void setAnswer_opt(List<QuizDetails> answer_opt) {
        this.answer_opt = answer_opt;
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
}
