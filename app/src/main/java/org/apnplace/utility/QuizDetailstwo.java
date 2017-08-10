package org.apnplace.utility;

import java.io.Serializable;

/**
 * Created by dlakshmi on 10/19/2016.
 */
public class QuizDetailstwo  implements Serializable {
    public String opt_id,answer_option;

    public String getOpt_id() {
        return opt_id;
    }

    public void setOpt_id(String opt_id) {
        this.opt_id = opt_id;
    }

    public String getAnswer_option() {
        return answer_option;
    }

    public void setAnswer_option(String answer_option) {
        this.answer_option = answer_option;
    }
}
