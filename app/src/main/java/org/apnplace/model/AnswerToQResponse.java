package org.apnplace.model;

import android.content.Context;

import org.apnplace.utility.AnswerDetails;
import org.apnplace.utility.AnswerToQDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin Racheal on 6/1/2016.
 */
public class AnswerToQResponse {

    List<AnswerToQDetails> quest=new ArrayList<AnswerToQDetails>();

    public List<AnswerToQDetails> getQuest() {
        return quest;
    }

    public void setQuest(List<AnswerToQDetails> quest) {
        this.quest = quest;
    }
}
