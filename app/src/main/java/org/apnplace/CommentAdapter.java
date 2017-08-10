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

import org.apnplace.utility.CommentsDetails;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Kevin Racheal on 4/15/2016.
 */
public class CommentAdapter extends BaseAdapter{
    private Context context;
    //private List<CommentsDetails> posts;
    private List<CommentsDetails>posts;
    String img;
    public CommentAdapter(BlogCommentActivity blogCommentActivity, List<CommentsDetails> posts) {
        this.context=blogCommentActivity;
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

        CommentsDetails data=posts.get(position);
       // System.out.println("Logindetails req:" +  posts.get(0));
       // System.out.println("Logindetails req:" +  posts.get(1));
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_comment_list, null);


        /*TextView tv1=(TextView)rowView.findViewById(R.id.newtvid);
        TextView tv2=(TextView)rowView.findViewById(R.id.newtvuid);
        TextView tv3=(TextView)rowView.findViewById(R.id.newtvuname);
        TextView tv4=(TextView)rowView.findViewById(R.id.newtvheadline);
        TextView tv5=(TextView)rowView.findViewById(R.id.newtvcontent);
        TextView tv6=(TextView)rowView.findViewById(R.id.newtvdate);*/

        TextView comment= (TextView) rowView.findViewById(R.id.tvcomment);
        TextView cid= (TextView) rowView.findViewById(R.id.tvblogcid);
        TextView commented_by=(TextView) rowView.findViewById(R.id.tvblogcommented_by);
        TextView cdate= (TextView) rowView.findViewById(R.id.tvcdate);
        TextView cpid=(TextView)rowView.findViewById(R.id.commentpid);
        ImageView commentdp=(ImageView)rowView.findViewById(R.id.commentdp);

        /*tv1.setText(blogs.get(0));
        tv2.setText(blogs.get(1));
        tv3.setText(blogs.get(2));
        tv4.setText(blogs.get(3));
        tv5.setText(blogs.get(4));
        tv6.setText(blogs.get(5));*/

        comment.setText(data.getComment());
        cid.setText(data.getCid());
        commented_by.setText(data.commented_by);
        cdate.setText(data.getCdate());
        cpid.setText(data.getId());
        img=data.getCommented_by();
        System.out.println("commented by----:" +data.commented_by);
        String uri=img.replace(" ","%20");
        new DownloadImageTask(commentdp).execute("http://apnsplace.cs.odu.edu/images/profile/"+uri+".jpg");

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
