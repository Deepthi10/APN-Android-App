package org.apnplace;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apnplace.fragment.TopFreeFragment;
import org.apnplace.utility.QuizDetails;
import org.apnplace.utility.QuizrootDetails;
import org.apnplace.utility.ScorDetails;

import java.util.List;

/**
 * Created by dlakshmi on 10/12/2016.
 */

public class QuizAdapter  extends BaseAdapter {
    private TopFreeFragment context;
    List<QuizrootDetails>quizroot;
    //ScorDetails scoredetails;


    public QuizAdapter(TopFreeFragment context, List<QuizrootDetails> quizroot) {
        this.context=context;
        this.quizroot=quizroot;
        //this.scoredetails=scoredetails;
    }


    @Override
    public int getCount() {
        return quizroot.size();
    }

    @Override
    public QuizrootDetails getItem(int position) {
        return quizroot.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        QuizrootDetails quizdata=quizroot.get(position);
        /*String quizscore=scoredetails.getRecent_score();
        String quizfinalscore=scoredetails.getFinal_score();
        String quizattempt=scoredetails.getAttempt_count();*/
        convertView= context.getActivity().getLayoutInflater().inflate(R.layout.quizrow,parent,false);
        TextView tv1=(TextView)convertView.findViewById(R.id.quiztv1);
        TextView tv2=(TextView)convertView.findViewById(R.id.quiztv2);
/*        TextView tv3=(TextView)convertView.findViewById(R.id.recent_score);
        TextView tv4=(TextView)convertView.findViewById(R.id.final_score);
        TextView tv5=(TextView)convertView.findViewById(R.id.attempt);*/
        tv1.setText(quizdata.getQuestion_id());
        tv2.setText(quizdata.getQuestion());
        /*tv3.setText(quizscore);
        tv4.setText(quizfinalscore);
        tv5.setText(quizattempt);*/

        RadioGroup radioGroup=(RadioGroup)convertView.findViewById(R.id.qtnqnwlist);
        final int quesid= Integer.parseInt(quizdata.getQuestion_id());
        for(QuizDetails option:quizdata.getAnswer_opt()){
            final int optid= Integer.parseInt(option.getOpt_id());
            RadioButton rb=new RadioButton(context.getActivity());
            rb.setText(option.getAnswer_option());
            rb.setTextColor(Color.BLACK);
            if( !(TopFreeFragment.questionMap.get(quesid)==null) && TopFreeFragment.questionMap.get(quesid).equals(optid) )
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
                    TopFreeFragment.mapQuesAns(quesid,checkedId);
                    System.out.println("get the map qtn and ans---------:= " + TopFreeFragment.questionMap);
                }
            });
            radioGroup.addView(rb);
        }
        return convertView;
    }
}
