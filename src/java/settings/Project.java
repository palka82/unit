/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

import support.settings.ProjectSettings;

/**
 *
 * @author Кот
 */
public class Project implements ProjectSettings{
    public Project(){
        
    }
    public final static int maxUploadSizeMb=50;
    public final static String  uploadPath="/usr/local/demoPhone/upload";
    public final static String  baselinkPath="/unit/index";

    @Override
    public int getMaxUploadSizeMb() {
        return maxUploadSizeMb;
    }

    @Override
    public String getUploadPath() {
       return uploadPath;
    }

    @Override
    public String getBaseLinkPath() {
        return baselinkPath;
    }
}
