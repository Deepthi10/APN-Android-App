package org.apnplace.model;

import org.apnplace.utility.QuestionDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin Racheal on 5/13/2016.
 */
public class QuestionResponse {
    List<QuestionDetails>quest=new ArrayList<QuestionDetails>();

    public List<QuestionDetails> getQuest() {
        return quest;
    }

    public void setQuest(List<QuestionDetails> quest) {
        this.quest = quest;
    }
}
