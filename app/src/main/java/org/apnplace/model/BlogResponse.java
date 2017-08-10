package org.apnplace.model;

import org.apnplace.utility.BlogDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin Racheal on 4/11/2016.
 */
public class BlogResponse {

    List<BlogDetails> posts=new ArrayList<BlogDetails>();

    public List<BlogDetails> getPosts() {
        return posts;
    }

    public void setPosts(List<BlogDetails> posts) {
        this.posts = posts;
    }
}
