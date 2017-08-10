package org.apnplace.model;

import java.util.List;

/**
 * Created by dlakshmi on 10/21/2016.
 */

public class Answers {
 private String moduleid;
    private List<UserAnswers>answer_opt;

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    public List<UserAnswers> getAnswer_opt() {
        return answer_opt;
    }

    public void setAnswer_opt(List<UserAnswers> answer_opt) {
        this.answer_opt = answer_opt;
    }

    public static class UserAnswers{
        private String opt_id;
        private String question_id;

        public String getOpt_id() {
            return opt_id;
        }

        public void setOpt_id(String opt_id) {
            this.opt_id = opt_id;
        }

        public String getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(String question_id) {
            this.question_id = question_id;
        }
    }

}


