package org.apnplace;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import org.apnplace.fragment.TopFreeFragment3;
import org.apnplace.fragment.TopFreeFragment4;
import org.apnplace.utility.QuizDetails;
import org.apnplace.utility.QuizrootDetails;

import java.util.List;

/**
 * Created by dlakshmi on 10/19/2016.
 */

public class QuizAdapterfour extends BaseAdapter {
    private TopFreeFragment4 context;
    List<QuizrootDetails> quizroot;

    public QuizAdapterfour(TopFreeFragment4 context, List<QuizrootDetails> quizroot) {
    this.context=context;
        this.quizroot=quizroot;
    }

    @Override
    public int getCount() {
        return quizroot.size();
    }

    @Override
    public Object getItem(int position) {
        return quizroot.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        QuizrootDetails qdata=quizroot.get(position);
        convertView= context.getActivity().getLayoutInflater().inflate(R.layout.quizrowfour,parent,false);
        TextView tv1=(TextView)convertView.findViewById(R.id.quiztv1111);
        TextView tv2=(TextView)convertView.findViewById(R.id.quiztv2222);
       // ListView qlist=(ListView) convertView.findViewById(R.id.qtnqnwlist4);
        tv1.setText(qdata.getQuestion_id());
        tv2.setText(qdata.getQuestion());
        RadioGroup radioGroup=(RadioGroup)convertView.findViewById(R.id.qtnqnwlist4);
        final int quesid= Integer.parseInt(qdata.getQuestion_id());
        for(QuizDetails option:qdata.getAnswer_opt()){
            final int optid= Integer.parseInt(option.getOpt_id());
            RadioButton rb=new RadioButton(context.getActivity());
            rb.setText(option.getAnswer_option());
            rb.setTextColor(Color.BLACK);
            if( !(TopFreeFragment4.questionMap.get(quesid)==null) && TopFreeFragment4.questionMap.get(quesid).equals(optid) )
            {

                rb.setChecked(true);

            }
            ColorStateList colorStateList = new ColorStateList(
                    new int[][]{

                            new int[]{-android.R.attr.state_enabled}, //disabled
                            new int[]{android.R.attr.state_enabled} //enabled
                    },
                    new int[] {

                            Color.BLACK //disabled
                            ,Color.BLUE //enabled

                    }
            );
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                rb.setButtonTintList(colorStateList);
            }

            rb.setId(Integer.parseInt(option.getOpt_id()));
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    System.out.println("get the radiobutton checkedid---------:= " + checkedId);
                    System.out.println("get the radiobutton qtnid---------:= " + quesid);
                    // Toast.makeText(getClass(),"get the optid"+optid,Toast.LENGTH_LONG).show();
                    TopFreeFragment4.mapQuesAns(quesid,checkedId);
                    System.out.println("get the map qtn and ans---------:= " + TopFreeFragment4.questionMap);
                }
            });
            radioGroup.addView(rb);
        }

        return convertView;
    }
}
