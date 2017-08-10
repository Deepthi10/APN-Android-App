package org.apnplace.utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dlakshmi on 10/19/2016.
 */

public class QuizrootDetailstwo implements Serializable {

    String question_id,question;
    List<QuizDetailstwo> answer_opt = new ArrayList<QuizDetailstwo>();

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

    public List<QuizDetailstwo> getAnswer_opt() {
        return answer_opt;
    }

    public void setAnswer_opt(List<QuizDetailstwo> answer_opt) {
        this.answer_opt = answer_opt;
    }
}
