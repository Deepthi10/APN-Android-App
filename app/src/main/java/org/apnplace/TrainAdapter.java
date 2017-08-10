package org.apnplace;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Kevin Racheal on 3/1/2016.
 */
public class TrainAdapter extends BaseAdapter {

    String[] TrainRes;
    Context context;
    String[] Traindetail1;
    private static LayoutInflater inflater = null;
    int [] TimageId;
    public TrainAdapter(TrainingActivity trainingActivity, String[] modNameList, String[] modDetail, int[] modImages) {
        TrainRes=modNameList;
        context=trainingActivity;
        Traindetail1=modDetail;
        TimageId=modImages;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return TrainRes.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class THolder{
        TextView Traintv1;
        TextView Traintv2;
        ImageView Traini;
        GridView Trgv;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

       /* THolder th=new THolder();
        View Tview= inflater.inflate(R.layout.activity_training,null);
        th.Traintv1= (TextView) Tview.findViewById(R.id.trainM1);
        th.Traini= (ImageView) Tview.findViewById(R.id.imageTrain);
         th.Traintv1.setText(TrainRes[position]);
        //th.Traintv2.setText(Traindetail1[position]);
        th.Traini.setImageResource(TimageId[position]);

        Tview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Youclicked" + TrainRes[position], Toast.LENGTH_LONG).show();
                if (TrainRes[position].equals(TrainRes[0])) {
                    Intent intent = new Intent(context, TabBar.class);
                    context.startActivity(intent);
                } else if (TrainRes[position].equals(TrainRes[1])) {
                    Intent in= new Intent(context, TabBarModule2.class);
                    context.startActivity(in);
                }
                else if(TrainRes[position].equals(TrainRes[2])){
                    Intent inthree= new Intent(context,TabBarModule3.class);
                    context.startActivity(inthree);
                }
                else if(TrainRes[position].equals(TrainRes[3])){
                    Intent infour= new Intent(context,WebModuleActivity4.class);
                    context.startActivity(infour);
                }

            }
        });*/

        return null;
    }
}
