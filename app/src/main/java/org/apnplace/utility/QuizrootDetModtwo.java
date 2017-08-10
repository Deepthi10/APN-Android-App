package org.apnplace.utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dlakshmi on 10/18/2016.
 */

public class QuizrootDetModtwo implements Serializable {
    String question_id,question;
    List<QuizDetails> answer_opt = new ArrayList<QuizDetails>();

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

    public List<QuizDetails> getAnswer_opt() {
        return answer_opt;
    }

    public void setAnswer_opt(List<QuizDetails> answer_opt) {
        this.answer_opt = answer_opt;
    }
}
