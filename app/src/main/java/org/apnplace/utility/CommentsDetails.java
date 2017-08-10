package org.apnplace.utility;

import java.io.Serializable;

/**
 * Created by Kevin Racheal on 4/15/2016.
 */
public class CommentsDetails implements Serializable {
public String cid,comment,cdate,commented_by,id;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getCommented_by() {
        return commented_by;
    }

    public void setCommented_by(String commented_by) {
        this.commented_by = commented_by;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
