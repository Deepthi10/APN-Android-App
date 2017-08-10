package org.apnplace.utility;

/**
 * Created by Kevin Racheal on 4/8/2016.
 */
public class Blog {
    private String headline,content,uname;

    public Blog( String headline, String content,String uname){

         this.setHeadline(headline);
        this.setContent(content);
        this.setUname(uname);
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String header) {
        this.headline = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
