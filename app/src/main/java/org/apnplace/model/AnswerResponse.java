package org.apnplace.model;

import org.apnplace.utility.AnswerDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin Racheal on 5/31/2016.
 */
public class AnswerResponse {

    List<AnswerDetails> quest= new ArrayList<AnswerDetails>();

    public List<AnswerDetails> getQuest() {
        return quest;
    }

    public void setQuest(List<AnswerDetails> quest) {
        this.quest = quest;
    }
}
