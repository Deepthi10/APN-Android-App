package org.apnplace.model;

import org.apnplace.utility.QuizrootDetModtwo;
import org.apnplace.utility.QuizrootDetails;
import org.apnplace.utility.QuizrootDetailstwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dlakshmi on 10/18/2016.
 */

public class QuizrootResModtwo {

    List<QuizrootDetailstwo> quizroot=new ArrayList<QuizrootDetailstwo>();

    public List<QuizrootDetailstwo> getQuizroot() {
        return quizroot;
    }
    public void setQuizroot(List<QuizrootDetailstwo> quizroot) {
        this.quizroot = quizroot;
    }
}
