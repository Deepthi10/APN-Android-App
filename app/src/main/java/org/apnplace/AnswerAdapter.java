package org.apnplace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.apnplace.utility.AnswerDetails;

import java.util.List;

/**
 * Created by Kevin Racheal on 5/31/2016.
 */
public class AnswerAdapter extends BaseAdapter {
    private Context context;
    private List<AnswerDetails> quest;

    public AnswerAdapter(GetResponseQActivity getResponseQActivity, List<AnswerDetails> quest) {
    this.context=getResponseQActivity;
    this.quest=quest;
    }

    @Override
    public int getCount() {
        return quest.size();
    }

    @Override
    public Object getItem(int position) {
        return quest.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AnswerDetails answerdata=quest.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.answers, null);

        TextView ansid=(TextView)rowView.findViewById(R.id.qanswerid);
        TextView answer=(TextView)rowView.findViewById(R.id.answer);
        TextView ans_by=(TextView)rowView.findViewById(R.id.qanswerby);
        TextView qid=(TextView)rowView.findViewById(R.id.questionans_id);
        TextView quest=(TextView)rowView.findViewById(R.id.questionanswer);
        TextView quest_by=(TextView)rowView.findViewById(R.id.questionans_by);
        TextView quesrid=(TextView)rowView.findViewById(R.id.qna_userid);
        TextView qusername=(TextView)rowView.findViewById(R.id.qna_username);

        ansid.setText(answerdata.getAnswer_id());
        answer.setText(answerdata.getAnswer());
        ans_by.setText(answerdata.getAnswer_by());
        qid.setText(answerdata.getQuestion_id());
        quest.setText(answerdata.getQuestion());
        quest_by.setText(answerdata.getQuestion_by());
        quesrid.setText(answerdata.getUser_id());
        qusername.setText(answerdata.getUser_name());

        return rowView;
    }
}
