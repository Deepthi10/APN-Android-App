package org.apnplace.model;

import org.apnplace.utility.ScorDetails;
import org.apnplace.utility.ScoreMsgDet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dlakshmi on 3/30/2017.
 */

public class ScoreResponse {

      List<ScorDetails> scoredetails=new ArrayList<ScorDetails>();


    public List<ScorDetails> getScoredetails() {
        return scoredetails;
    }

    public void setScoredetails(List<ScorDetails> scoredetails) {
        this.scoredetails = scoredetails;
    }
}
