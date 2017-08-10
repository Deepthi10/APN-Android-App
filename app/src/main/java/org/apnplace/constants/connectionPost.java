package org.apnplace.constants;

import org.apache.http.NameValuePair;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rgudipati on 11/11/2016.
 */

public class connectionPost {
// Post method for webservice
    public static String startService(String resourcePath, HashMap<String,String> formData)
    {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer("");
        int responseCode=0;
        try {
            URL url = new URL(resourcePath);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            if(formData!=null) {
                List<NameValuePair> params = new ArrayList<>();
                for (String key : formData.keySet())
                    params.add(new BasicNameValuePair(key, formData.get(key)));
                System.out.println(getQuery(params));
                wr.writeBytes(getQuery(params));
            }

            wr.flush();
            wr.close();
            responseCode = connection.getResponseCode();
            System.out.println("Response code from service "+responseCode);
            InputStream stream=connection.getInputStream();
            reader=new BufferedReader(new InputStreamReader(stream));

            String line="";
            while ((line= reader.readLine())!=null)
            {
                buffer.append(line);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return buffer.toString();
    }


    public static String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
