package org.apnplace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.apnplace.utility.QuestionDetails;

import java.util.List;

/**
 * Created by Kevin Racheal on 5/13/2016.
 */
public class QuestAdapter extends BaseAdapter {
    private Context context;
    private List<QuestionDetails> quest;

    public QuestAdapter(Context questionsPosted, List<QuestionDetails> quest) {
    this.quest=quest;
        this.context=questionsPosted;
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
        QuestionDetails data=quest.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.qlayout, null);

        TextView topic=(TextView)rowView.findViewById(R.id.questiontopic);
        TextView  q_id=(TextView)rowView.findViewById(R.id.question_id);
        TextView quest=(TextView)rowView.findViewById(R.id.question);
        TextView  q_by=(TextView)rowView.findViewById(R.id.question_by);
        TextView q_to=(TextView)rowView.findViewById(R.id.quest_to);
        TextView  qdate=(TextView)rowView.findViewById(R.id.qdate);
        TextView uname=(TextView) rowView.findViewById(R.id.qusername);

        topic.setText(data.getTopic());
        q_id.setText(data.getQuestion_id());
        quest.setText(data.getQuestion());
        q_by.setText(data.getQuestion_by());
        q_to.setText(data.getQuestion_to());
        qdate.setText(data.getDate());
        uname.setText(data.getUser_name());

        return rowView;
    }
}
