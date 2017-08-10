package org.apnplace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.apnplace.utility.CalDetails;

import java.util.List;

/**
 * Created by Kevin Racheal on 4/22/2016.
 */
public class CalAdapter extends BaseAdapter {
    private Context context;
    private List<CalDetails> caleventdetails;

    public CalAdapter() {
    }

    public CalAdapter(Context context, List<CalDetails> caleventdetails) {
        this.context=context;
        this.caleventdetails=caleventdetails;
    }


    @Override
    public int getCount() {
        return caleventdetails.size();
    }

    @Override
    public Object getItem(int position) {
        return caleventdetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CalDetails data=caleventdetails.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cal_events, null);

        TextView tvid= (TextView) rowView.findViewById(R.id.calid);
        TextView title= (TextView) rowView.findViewById(R.id.tveventtitle);
        TextView start= (TextView) rowView.findViewById(R.id.tvstart);
        TextView end= (TextView) rowView.findViewById(R.id.tvend);
        TextView allday= (TextView) rowView.findViewById(R.id.tvallday);
        TextView endNew= (TextView) rowView.findViewById(R.id.tvendNew);
        TextView ev= (TextView) rowView.findViewById(R.id.tveventDetails);

        tvid.setText(data.getId());
        title.setText(data.getTitle());
        start.setText(data.getStart());
        end.setText(data.getEnd());
        allday.setText(data.getAllDay());
        endNew.setText(data.getEndNew());
        ev.setText(data.getEventDetails());

        return rowView;
    }
}
