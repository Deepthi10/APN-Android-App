package org.apnplace.model;

import org.apnplace.utility.CalDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin Racheal on 4/22/2016.
 */
public class CalResponse {
   List<CalDetails> caleventdetails=new ArrayList<CalDetails>();

   public List<CalDetails> getCaleventdetails() {
      return caleventdetails;
   }

   public void setCaleventdetails(List<CalDetails> caleventdetails) {
      this.caleventdetails = caleventdetails;
   }
}
