package org.apnplace;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.connectionPost;
import org.apnplace.constants.uploadPicture;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import static org.apnplace.HomeActivity.MylastPREFERENCES;


public class UploadProfile extends AppCompatActivity implements View.OnClickListener {

    private static final int RESULT_LOAD_IMAGE=1;
    private static final String SERVER_ADDRESS="http://apnplace.org/";
    static final int REQUEST_IMAGE_CAPTURE = 1;

    Button UploadButton;
    TextView tap;
    ImageView ImageToupload,downloadImage;
    private String imagepath = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
       // showActionBar();
        setContentView(R.layout.activity_upload_profile);
        APNSApplication apnsApplication = (APNSApplication) getApplicationContext();
       ImageToupload=(ImageView)findViewById(R.id.imageToUpload);
        downloadImage=(ImageView)findViewById(R.id.downloadedImage);
        UploadButton=(Button)findViewById(R.id.buploadImagebutton);
        tap=(TextView)findViewById(R.id.taptv);
        new DownloadImageTask().execute("http://apnsplace.cs.odu.edu/images/profile/"+apnsApplication.getUser_name()+".jpg");
        ImageToupload.setOnClickListener(this);
        UploadButton.setOnClickListener(this);

    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        SharedPreferences sharedPreferences;
        sharedPreferences=getSharedPreferences(MylastPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lastpage","UploadProfile");
        editor.commit();
    }

    @Override
    public void onClick(View view) {
        final APNSApplication apnsApplication = (APNSApplication) getApplicationContext();
     switch (view.getId()){

         case R.id.imageToUpload:
              Intent galleryIntent= new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            // Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
             startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
            // startActivityForResult(intent,RESULT_LOAD_IMAGE);
             break;
         case R.id.buploadImagebutton:
             Bitmap image=((BitmapDrawable)ImageToupload.getDrawable()).getBitmap();
                 new UploadImage(image, apnsApplication.getUser_name()).execute();
          //   new DownloadImageTask().execute("http://apnsplace.cs.odu.edu/images/profile/"+apnsApplication.getUser_name()+".jpg");
            // Toast.makeText(getApplicationContext(),"username is:"+apnsApplication.getUser_name(),Toast.LENGTH_LONG).show();
             break;

     }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK && data!=null){
            Uri selectedImage=data.getData();
            ImageToupload.setImageURI(selectedImage);
          //  Toast.makeText(getApplicationContext(),"On Activity result----data"+data.getData(),Toast.LENGTH_LONG).show();
        }
    }
    private class UploadImage extends AsyncTask<String, Integer, String> {

        Bitmap image;
        String name;
        private ProgressDialog dialog = new ProgressDialog(UploadProfile.this);
        public UploadImage(Bitmap image,String name){
            this.image=image;
            this.name=name;
        }


        @Override
        protected void onPreExecute() {
            dialog.setMessage("loading...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
         //   ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
       //     image.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
       //     String encodedImage= Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);

//            ArrayList<NameValuePair>dataToSend=new ArrayList<>();
//            dataToSend.add(new BasicNameValuePair("image",encodedImage));
//            dataToSend.add(new BasicNameValuePair("uname",name));
//
//            HttpParams httpRequestParams=getHttpRequestParams();
//            HttpClient client=new DefaultHttpClient(httpRequestParams);
//            HttpPost post=new HttpPost(SERVER_ADDRESS + "UpdatePictureTest.php");
//
//            try{
//                post.setEntity(new UrlEncodedFormEntity(dataToSend));
//                client.execute(post);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            HashMap map=new HashMap();
//            map.put("image",image);
//            map.put("uname",name);
            try {
//                File file = new File("");
//                OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
//                image.compress(Bitmap.CompressFormat.JPEG, 100, os);
//                os.close();

                uploadPicture upload= new uploadPicture("http://apnsplace.cs.odu.edu/iosUploadImage.php","UTF-8");
                upload.addFormField("uname",name);
                upload.addFilePart("image",image);
               return  upload.finish().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String aVoid) {
            dialog.cancel();
            super.onPostExecute(aVoid);
            System.out.println("res--"+aVoid);
            Toast.makeText(getApplicationContext(),"Image Updated Succesfully",Toast.LENGTH_LONG).show();
Intent in=new Intent(UploadProfile.this,HomeActivity.class);
            startActivity(in);
        }
    }

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        // ImageView bmImage;

        public DownloadImageTask() {
            // this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            ImageToupload.setImageBitmap(result);
            ImageToupload.invalidate();
        }
    }
    private HttpParams getHttpRequestParams(){
     HttpParams httpRequestParams=new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams,100*30);
        HttpConnectionParams.setSoTimeout(httpRequestParams,100*30);
        return  httpRequestParams;
    }
}

