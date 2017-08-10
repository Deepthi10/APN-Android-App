package org.apnplace.model;

import org.apnplace.utility.BlogDetails;
import org.apnplace.utility.CommentsDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin Racheal on 4/15/2016.
 */
public class CommentResponse {
    List<CommentsDetails> posts = new ArrayList<CommentsDetails>();

    public List<CommentsDetails> getPosts() {
        return posts;
    }

    public void setPosts(List<CommentsDetails> posts) {
        this.posts = posts;
    }
}

