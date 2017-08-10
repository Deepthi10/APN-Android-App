package org.apnplace;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Kevin Racheal on 2/18/2016.
 */
class Adapter extends BaseAdapter {
    String [] result;
    Context context;
    int [] imageId;
    //String[] detail;

//This is the adapter for home page
    public
    Adapter(HomeActivity mainActivity, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        imageId=prgmImages;
        //detail=pgmDetail;
    }
    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

  /*  public class Holder
    {
       *//* TextView tv;
        ImageView img;*//*
        Button btn1;
    }*/
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
      //  Holder holder=new Holder();
        View rowView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.imagedetail, null);
      /*  holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView);
        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);*/

        ImageView img1=(ImageView)rowView.findViewById(R.id.imageView);
        TextView tv1=(TextView)rowView.findViewById(R.id.textView1);
        img1.setImageResource(imageId[position]);
        tv1.setText(result[position]);



        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_LONG).show();
                if (result[position].equals(result[0])) {
                    Intent intent = new Intent(context, OrientationActivity.class);
                    context.startActivity(intent);
                } else if (result[position].equals(result[1])) {
                    Intent intent = new Intent(context, PgmInfoActivity.class);
                    context.startActivity(intent);
                } else if (result[position].equals(result[2])) {
                    Intent intent = new Intent(context, CustomCalendarActivity.class);
                    context.startActivity(intent);
                } else if (result[position].equals(result[3])) {
                    Intent intent = new Intent(context, PrecepHandbook.class);
                    context.startActivity(intent);
                } else if (result[position].equals(result[4])) {
                    Intent intent = new Intent(context, ModuleActivity.class);
                    context.startActivity(intent);
                } else if (result[position].equals(result[5])) {
                    Intent intent = new Intent(context, ResourceActivity.class);
                    context.startActivity(intent);
                } else if (result[position].equals(result[6])) {
                 Intent intent = new Intent(context, DataActivity.class);
                    context.startActivity(intent);
                } else if (result[position].equals(result[7])) {
                    AlertDialog.Builder alert=new AlertDialog.Builder(context);
                    alert.setTitle("Message!");
                    alert.setMessage("This tab is not yet available.");
                    alert.setPositiveButton("Ok",new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();
                    /*Intent intent = new Intent(context, TeleHealthActivity.class);
                    context.startActivity(intent);*/
                } else if (result[position].equals(result[8])) {
                    Intent intent = new Intent(context, CardViewActivity.class);
                    context.startActivity(intent);
                }

            }
        });

        return rowView;
    }
}
