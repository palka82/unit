/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import support.StringAdapter;

/**
 *
 * @author Admin
 */
public class Dictionary {
    
    
    private  Map<String, Object> list = new HashMap();
    private  Map<String, Object> session = new HashMap();
    private boolean isCreated=false;
    private Connection con;
    
    
    private Dictionary(){
        
    }
    
    private void setConnection(Connection con){
        this.con=con;
    }
    private void setSession(Map<String, Object> session){
        this.session=session;
    }
    
    public static Dictionary getInstance(Connection con,Map<String, Object> session){
        Dictionary dct= new Dictionary();
        dct.setConnection(con);
        dct.setSession(session);
        return new Dictionary();
    }
    
    private void createDictionary() throws Exception{
        isCreated=true;
    }
    
    public void createDictionary(Boolean upgrade) throws Exception{
        if(isCreated!=true||upgrade==true){
            createDictionary();
        }
    }
    
    public Object get(DictionaryWords word){
        return list.get(word);
    }
    
    
    
    public static enum DictionaryWords{
        CLIENTNAME,USERNAME,
    }
}
