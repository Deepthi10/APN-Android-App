package org.apnplace;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apnplace.utility.BlogDetails;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Kevin Racheal on 4/8/2016.
 */
public class BlogAdapter extends BaseAdapter {
    private String TAG = "BlogAdapter";
    private  Context context;
    private  List<BlogDetails> posts;
    String img;

    public BlogAdapter() {

    }

    public BlogAdapter(Context context, List<BlogDetails> posts) {
        this.context=context;
        this.posts=posts;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BlogDetails data = posts.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, null);
        TextView tv1= (TextView) rowView.findViewById(R.id.tvblogpostid);
        TextView tv2= (TextView) rowView.findViewById(R.id.tvbloguser_id);
        ImageView imageView= (ImageView) rowView.findViewById(R.id.blogdp);
        TextView tv3= (TextView) rowView.findViewById(R.id.tvbloguname);
        TextView tv4= (TextView) rowView.findViewById(R.id.tvblogheadline);
        TextView tv5= (TextView) rowView.findViewById(R.id.tvblogcontent);
        TextView tv6 = (TextView) rowView.findViewById(R.id.tvblogdate);


        tv1.setText(data.getId());
        tv2.setText(data.getUser_id());
        tv3.setText(data.user_name);
        tv4.setText(data.getHeadline());
        tv5.setText(data.getContent());
        System.out.println(data.getDate());
        tv6.setText(data.getDate());
        img=data.getUser_name();
        System.out.println("usernam-by----:" +img);
        System.out.println("usernam-by----:" +data.user_name);
        String uri=img.replace(" ","%20");
        System.out.println("uri in the imagey----:" +uri);
        new DownloadImageTask(imageView).execute("http://apnsplace.cs.odu.edu/images/profile/"+uri+".jpg");
       return rowView;
    }
     class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
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
