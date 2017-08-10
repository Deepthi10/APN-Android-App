package org.apnplace;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Kevin Racheal on 2/22/2016.
 */
public class ListAdapter extends BaseAdapter {
    String[] result;
    Context context;
    String[] detail;
    private static LayoutInflater inflater = null;


    public ListAdapter(HomeListActivity mainActivity, String[] listName, String[] listDetail) {
        result = listName;
        context = mainActivity;
        detail = listDetail;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

        @Override
        public int getCount () {
            return result.length;
        }

        @Override
        public Object getItem ( int position){
            return position;
        }

        @Override
        public long getItemId ( int position){
            return position;
        }

        public class Holder {
            TextView tv;
            TextView tv1;

        }
        @Override
        public View getView ( final int position, View convertView, ViewGroup parent){
            Holder holder = new Holder();
            View rowView1;

            rowView1 = inflater.inflate(R.layout.listdetail, null);
            holder.tv = (TextView) rowView1.findViewById(R.id.textView11);
            holder.tv1 = (TextView) rowView1.findViewById(R.id.textView22);
            holder.tv.setText(result[position]);
            holder.tv1.setText(detail[position]);

            rowView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   // Toast.makeText(context, "You clicked--" + result[position], Toast.LENGTH_LONG).show();

                    if (result[position].equals(result[0])) {
                        //Intent intent = new Intent(context, InviteActivity.class);
                        //context.startActivity(intent);

                    } else if (result[position].equals(result[1])) {
                       // Intent intent = new Intent(context, CalActivity.class);
                        //context.startActivity(intent);
                    } else if (result[position].equals(result[2])) {
                        Intent intent = new Intent(context, IntUserActivity.class);
                        context.startActivity(intent);
                    } else if (result[position].equals(result[3])) {
                        Intent intent = new Intent(context, MyQActivity.class);
                        context.startActivity(intent);
                    } else if (result[position].equals(result[4])) {
                        Intent intent = new Intent(context, ActivityList.class);
                        context.startActivity(intent);
                    } else if (result[position].equals(result[5])) {
                        Intent intent = new Intent(context, HomeActivity.class);
                        context.startActivity(intent);
                    } else if (result[position].equals(result[6])) {
                        Intent intent = new Intent(context, UserActivity.class);
                        context.startActivity(intent);
                    }

                }

            });

            return rowView1;
        }
    }

