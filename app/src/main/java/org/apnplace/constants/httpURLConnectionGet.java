package org.apnplace.constants;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Kevin Racheal on 4/6/2016.
 */

public class httpURLConnectionGet {

//Get method for webservice
    public static String call(String resourcepath, String input)
    {
        HttpURLConnection connection=null;
        BufferedReader reader=null;
        StringBuilder buffer=new StringBuilder();
        DataOutputStream wr = null;
        try {
            URL url = new URL(resourcepath);
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            InputStream stream=connection.getInputStream();
            reader=new BufferedReader(new InputStreamReader(stream));
            int responseCode = connection.getResponseCode();
            Log.d("", "call: " + responseCode);

            String line="";

            while ((line= reader.readLine())!=null)
            {

                buffer.append(line);

            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("", "@@@@@@@@@@call: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("", "@@@@@@@@@@call2: " + e.getMessage());
        }finally {

            if (wr != null) {
                try {
                    wr.flush();
                    wr.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return buffer.toString();
    }


}