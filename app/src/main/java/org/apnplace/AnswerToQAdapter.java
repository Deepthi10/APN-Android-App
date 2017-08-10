package org.apnplace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.apnplace.utility.AnswerToQDetails;

import java.util.List;

/**
 * Created by Kevin Racheal on 6/1/2016.
 */
public class AnswerToQAdapter extends BaseAdapter {
    private Context context;
    private List<AnswerToQDetails> quest;

    public AnswerToQAdapter(GetNextResponseQActivity getResponseQActivity, List<AnswerToQDetails> quest) {
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

          AnswerToQDetails answerTodata=quest.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowViewR = inflater.inflate(R.layout.answer_toq, null);

        TextView ransid=(TextView)rowViewR.findViewById(R.id.response_answerid);
        TextView ranswer=(TextView)rowViewR.findViewById(R.id.response_answer);
        TextView ruid=(TextView)rowViewR.findViewById(R.id.responseu_id);
        TextView runame=(TextView)rowViewR.findViewById(R.id.reponse_uname);

        ransid.setText(answerTodata.getAnswer_id());
        ranswer.setText(answerTodata.getAnswer());
        ruid.setText(answerTodata.getUser_id());
        runame.setText(answerTodata.getUser_name());
        return rowViewR;
    }
}
