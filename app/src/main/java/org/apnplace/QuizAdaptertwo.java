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

import org.apnplace.fragment.TopFreeFragment;
import org.apnplace.fragment.TopFreeFragment2;
import org.apnplace.utility.QuizDetails;
import org.apnplace.utility.QuizDetailstwo;
import org.apnplace.utility.QuizrootDetModtwo;
import org.apnplace.utility.QuizrootDetails;
import org.apnplace.utility.QuizrootDetailstwo;

import java.util.List;

/**
 * Created by dlakshmi on 10/18/2016.
 */

public class QuizAdaptertwo extends BaseAdapter {
    private TopFreeFragment2 context;
    List<QuizrootDetails> quizroot;


    public QuizAdaptertwo(TopFreeFragment2 context, List<QuizrootDetails> quizroot) {
       this.context=context;
        this.quizroot=quizroot;
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
        QuizrootDetails qdata=quizroot.get(position);
        convertView= context.getActivity().getLayoutInflater().inflate(R.layout.quizrowtwo,parent,false);
        TextView tv1=(TextView)convertView.findViewById(R.id.quiztv11);
        TextView tv2=(TextView)convertView.findViewById(R.id.quiztv22);
       // ListView qlist=(ListView) convertView.findViewById(R.id.qtnqnwlist2);
        tv1.setText(qdata.getQuestion_id());
        tv2.setText(qdata.getQuestion());
       // List<QuizDetailstwo>answer_opt= qdata.getAnswer_opt();
        //qlist.setAdapter(qListAdaptertwo);
        RadioGroup radioGroup=(RadioGroup)convertView.findViewById(R.id.qtnqnwlist2);
        final int quesid= Integer.parseInt(qdata.getQuestion_id());
        for(QuizDetails option:qdata.getAnswer_opt()){
            final int optid= Integer.parseInt(option.getOpt_id());
            RadioButton rb=new RadioButton(context.getActivity());
            rb.setText(option.getAnswer_option());
            rb.setTextColor(Color.BLACK);
            if( !(TopFreeFragment2.questionMap.get(quesid)==null) && TopFreeFragment2.questionMap.get(quesid).equals(optid) )
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
                    TopFreeFragment2.mapQuesAns(quesid,checkedId);
                    System.out.println("get the map qtn and ans---------:= " + TopFreeFragment2.questionMap);
                }
            });
            radioGroup.addView(rb);
        }
        return convertView;
    }
}
