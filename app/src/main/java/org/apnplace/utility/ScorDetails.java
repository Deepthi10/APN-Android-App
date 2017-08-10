package org.apnplace.utility;

import java.io.Serializable;

/**
 * Created by dlakshmi on 3/29/2017.
 */

public class ScorDetails implements Serializable{
  public String recent_score,final_score,attempt_count;

    public String getRecent_score() {
        return recent_score;
    }

    public void setRecent_score(String recent_score) {
        this.recent_score = recent_score;
    }

    public String getFinal_score() {
        return final_score;
    }

    public void setFinal_score(String final_score) {
        this.final_score = final_score;
    }

    public String getAttempt_count() {
        return attempt_count;
    }

    public void setAttempt_count(String attempt_count) {
        this.attempt_count = attempt_count;
    }
}
