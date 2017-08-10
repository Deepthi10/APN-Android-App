package org.apnplace;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.apnplace.utility.ModuleDetails;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by dlakshmi on 10/27/2016.
 */

public class ModuleAdapter extends BaseAdapter {
    Context context;
    List<ModuleDetails>modules;
    String url="http://apnsplace.cs.odu.edu/images/";


    public ModuleAdapter(ModuleActivity context, List<ModuleDetails> modules) {
     this.context=context;
        this.modules=modules;
    }

    @Override
    public int getCount() {
        return modules.size();
    }

    @Override
    public Object getItem(int position) {
        return modules.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ModuleDetails data=modules.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_training, null);
        final TextView tvmid=(TextView)rowView.findViewById(R.id.moduleid);
        TextView tvmname=(TextView)rowView.findViewById(R.id.modulename);
        TextView unlockdate=(TextView)rowView.findViewById(R.id.unlockdate);
        TextView tvmodudesc=(TextView)rowView.findViewById(R.id.moduledesc);
        TextView tvfile=(TextView)rowView.findViewById(R.id.filedetails);
        ImageView modimg=(ImageView)rowView.findViewById(R.id.imageTrain);
        TextView modavail=(TextView)rowView.findViewById(R.id.moduleavailable);


        tvmid.setText(data.getModuleid());
        tvmname.setText(data.getModulename());
        unlockdate.setText(data.getUnlock_date());
        tvmodudesc.setText(data.getModuledesc());
        tvfile.setText(data.getFiledetails());
        String uri=data.getIcon_img().replace(" ", "%20");
        //modimg.setImageIcon(Icon.createWithContentUri("http://apnsplace.cs.odu.edu/images/"+uri));
        modavail.setText(data.getAvailable());
        new DownloadImageTask(modimg).execute("http://apnsplace.cs.odu.edu/images/"+uri);

        return rowView;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
