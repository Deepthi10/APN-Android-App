package org.apnplace;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.apnplace.fragment.Feedback1;

/**
 * Created by dlakshmi on 10/22/2016.
 */

public class FeedbackAdapter extends BaseAdapter {
    String[] Feedbackqtnlist;
    Feedback1 context;
    String[] Radiooptions;

    public FeedbackAdapter(Feedback1 context, String[] Radiooptions, String[] feedbackqtnlist) {
        this.context=context;
        this.Feedbackqtnlist=feedbackqtnlist;
        this.Radiooptions=Radiooptions;

    }

    @Override
    public int getCount() {
        return Feedbackqtnlist.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView=context.getActivity().getLayoutInflater().inflate(R.layout.feedback,parent,false);
        TextView tv1=(TextView)convertView.findViewById(R.id.feedtv1);
        RadioGroup radioGroup=(RadioGroup)convertView.findViewById(R.id.feedrd1);
       // tv1.setText();

        return convertView;
    }
}
