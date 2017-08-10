package org.apnplace.model;

import org.apnplace.utility.QuizrootDetails;
import org.apnplace.utility.ScorDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dlakshmi on 10/17/2016.
 */

public class QuizrootResponse {

    List<QuizrootDetails> quizroot=new ArrayList<QuizrootDetails>();
    ScorDetails scoredetails=new ScorDetails();


    public List<QuizrootDetails> getQuizroot() {
        return quizroot;
    }

    public void setQuizroot(List<QuizrootDetails> quizroot) {
        this.quizroot = quizroot;
    }

    public ScorDetails getScoredetails() {
        return scoredetails;
    }

    public void setScoredetails(ScorDetails scoredetails) {
        this.scoredetails = scoredetails;
    }
}
