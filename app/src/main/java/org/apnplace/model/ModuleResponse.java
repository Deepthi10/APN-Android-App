package org.apnplace.model;

import org.apnplace.utility.ModuleDetails;
import org.apnplace.utility.ScorDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dlakshmi on 10/27/2016.
 */

public class ModuleResponse {
 List<ModuleDetails> modules=new ArrayList<ModuleDetails>();

    public List<ModuleDetails> getModules() {
        return modules;
    }

    public void setModules(List<ModuleDetails> modules) {
        this.modules = modules;
    }

}
