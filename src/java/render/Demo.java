/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import support.StringAdapter;
import support.db.executor.Row;
import support.web.AbsEnt;
import support.web.EnumAttrType;
import support.web.FormOption;
import support.web.FormOptionInterface;
import support.web.entities.WebEnt;

/**
 *
 * @author Кот
 */
public class Demo {
    
    public static String showDemo(Map<String, Object> request,Map<String, Object> service){
        String result="";
        try{
            if(StringAdapter.NotNull(service.get("user"))){
                Row user=(Row) service.get("user");
                result+=""+user.get("phonenumber")+user.get("phonepass");
                result+=getHtml();
            }else{
                result+="Не определен пользователь";
            }
        }catch(Exception e){
            result+=StringAdapter.getStackTraceException(e);
        }
        return result;
    }
    
     private static String getHtml(){
        return "                <h2>\n" +
"                    Registration</h2>\n" +
"                <br />\n" +
"                <table style='width: 100%'>\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <label style=\"height: 100%\">\n" +
"                                Display Name:</label>\n" +
"                        </td>\n" +
"                        <td>\n" +
"                            <input type=\"text\" style=\"width: 100%; height: 100%\" id=\"txtDisplayName\" value=\"\"\n" +
"                                placeholder=\"e.g. John Doe\" />\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <label style=\"height: 100%\">\n" +
"                                Private Identity<sup>*</sup>:</label>\n" +
"                        </td>\n" +
"                        <td>\n" +
"                            <input type=\"text\" style=\"width: 100%; height: 100%\" id=\"txtPrivateIdentity\" value=\"\"\n" +
"                                placeholder=\"e.g. +33600000000\" />\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <label style=\"height: 100%\">\n" +
"                                Public Identity<sup>*</sup>:</label>\n" +
"                        </td>\n" +
"                        <td>\n" +
"                            <input type=\"text\" style=\"width: 100%; height: 100%\" id=\"txtPublicIdentity\" value=\"\"\n" +
"                                placeholder=\"e.g. sip:+33600000000@doubango.org\" />\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <label style=\"height: 100%\">Password:</label>\n" +
"                        </td>\n" +
"                        <td>\n" +
"                            <input type=\"password\" style=\"width: 100%; height: 100%\" id=\"txtPassword\" value=\"\" />\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <label style=\"height: 100%\">Realm<sup>*</sup>:</label>\n" +
"                        </td>\n" +
"                        <td>\n" +
"                            <input type=\"text\" style=\"width: 100%; height: 100%\" id=\"txtRealm\" value=\"\" placeholder=\"e.g. doubango.org\" />\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td colspan=\"2\" align=\"right\">\n" +
"                            <input type=\"button\" class=\"btn btn-success\" id=\"btnRegister\" value=\"LogIn\" disabled onclick='sipRegister();' />\n" +
"                            &nbsp;\n" +
"                            <input type=\"button\" class=\"btn btn-danger\" id=\"btnUnRegister\" value=\"LogOut\" disabled onclick='sipUnRegister();' />\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td colspan=\"3\">\n" +
"                            <p class=\"small\"><sup>*</sup> <i>Mandatory Field</i></p>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td colspan=\"3\">\n" +
"                            <a class=\"btn\" href=\"http://code.google.com/p/sipml5/wiki/Public_SIP_Servers\" target=\"_blank\">Need SIP account?</a>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td colspan=\"3\">\n" +
"                            <a class=\"btn\" href=\"./expert.htm\" target=\"_blank\">Expert mode?</a>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                </table>\n" +
"            </div>\n" +
"            <div id=\"divCallCtrl\" class=\"span7 well\" style='display:table-cell; vertical-align:middle'>\n" +
"                <label style=\"width: 100%;\" align=\"center\" id=\"txtCallStatus\">\n" +
"                </label>\n" +
"                <h2>\n" +
"                    Call control\n" +
"                </h2>\n" +
"                <br />\n" +
"                <table style='width: 100%;'>\n" +
"                    <tr>\n" +
"                        <td style=\"white-space:nowrap;\">\n" +
"                            <input type=\"text\" style=\"width: 100%; height:100%;\" id=\"txtPhoneNumber\" value=\"\" placeholder=\"Enter phone number to call\" />\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td colspan=\"1\" align=\"right\">\n" +
"                            <div class=\"btn-toolbar\" style=\"margin: 0; vertical-align:middle\">\n" +
"                                <!--div class=\"btn-group\">\n" +
"                                    <input type=\"button\" id=\"btnBFCP\" style=\"margin: 0; vertical-align:middle; height: 100%;\" class=\"btn btn-primary\" value=\"BFCP\" onclick='sipShareScreen();' disabled />\n" +
"                                </div-->\n" +
"                                <div id=\"divBtnCallGroup\" class=\"btn-group\">\n" +
"                                    <button id=\"btnCall\" disabled class=\"btn btn-primary\" data-toggle=\"dropdown\">Call</button>\n" +
"                                </div>&nbsp;&nbsp;\n" +
"                                <div class=\"btn-group\">\n" +
"                                    <input type=\"button\" id=\"btnHangUp\" style=\"margin: 0; vertical-align:middle; height: 100%;\" class=\"btn btn-primary\" value=\"HangUp\" onclick='sipHangUp();' disabled />\n" +
"                                </div>\n" +
"                             </div>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td id=\"tdVideo\" class='tab-video'>\n" +
"                            <div id=\"divVideo\" class='div-video'>\n" +
"                                <div id=\"divVideoRemote\" style='position:relative; border:1px solid #009; height:100%; width:100%; z-index: auto; opacity: 0'>\n" +
"                                    <video class=\"video\" width=\"100%\" height=\"100%\" id=\"video_remote\" autoplay=\"autoplay\" style=\"opacity: 0; \n" +
"                                        background-color: #000000; -webkit-transition-property: opacity; -webkit-transition-duration: 2s;\">\n" +
"                                    </video>\n" +
"                                </div>\n" +
"\n" +
"                                <div id=\"divVideoLocalWrapper\" style=\"margin-left: 0px; border:0px solid #009; z-index: 1000\">\n" +
"                                    <iframe class=\"previewvideo\" style=\"border:0px solid #009; z-index: 1000\"> </iframe>\n" +
"                                    <div id=\"divVideoLocal\" class=\"previewvideo\" style=' border:0px solid #009; z-index: 1000'>\n" +
"                                        <video class=\"video\" width=\"100%\" height=\"100%\" id=\"video_local\" autoplay=\"autoplay\" muted=\"true\" style=\"opacity: 0;\n" +
"                                            background-color: #000000; -webkit-transition-property: opacity;\n" +
"                                            -webkit-transition-duration: 2s;\">\n" +
"                                        </video>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                                <div id=\"divScreencastLocalWrapper\" style=\"margin-left: 90px; border:0px solid #009; z-index: 1000\">\n" +
"                                    <iframe class=\"previewvideo\" style=\"border:0px solid #009; z-index: 1000\"> </iframe>\n" +
"                                    <div id=\"divScreencastLocal\" class=\"previewvideo\" style=' border:0px solid #009; z-index: 1000'>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                                <!--div id=\"div1\" style=\"margin-left: 300px; border:0px solid #009; z-index: 1000\">\n" +
"                                    <iframe class=\"previewvideo\" style=\"border:0px solid #009; z-index: 1000\"> </iframe>\n" +
"                                    <div id=\"div2\" class=\"previewvideo\" style='border:0px solid #009; z-index: 1000'>\n" +
"                                      <input type=\"button\" class=\"btn\" style=\"\" id=\"Button1\" value=\"Button1\" /> &nbsp;\n" +
"                                      <input type=\"button\" class=\"btn\" style=\"\" id=\"Button2\" value=\"Button2\" /> &nbsp;\n" +
"                                    </div>\n" +
"                                </div-->\n" +
"                            </div>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                       <td align='center'>\n" +
"                            <div id='divCallOptions' class='call-options' style='opacity: 0; margin-top: 0px'>\n" +
"                                <input type=\"button\" class=\"btn\" style=\"\" id=\"btnFullScreen\" value=\"FullScreen\" disabled onclick='toggleFullScreen();' /> &nbsp;\n" +
"                                <input type=\"button\" class=\"btn\" style=\"\" id=\"btnMute\" value=\"Mute\" onclick='sipToggleMute();' /> &nbsp;\n" +
"                                <input type=\"button\" class=\"btn\" style=\"\" id=\"btnHoldResume\" value=\"Hold\" onclick='sipToggleHoldResume();' /> &nbsp;\n" +
"                                <input type=\"button\" class=\"btn\" style=\"\" id=\"btnTransfer\" value=\"Transfer\" onclick='sipTransfer();' /> &nbsp;\n" +
"                                <input type=\"button\" class=\"btn\" style=\"\" id=\"btnKeyPad\" value=\"KeyPad\" onclick='openKeyPad();' />\n" +
"                            </div>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                </table>\n" +
"            </div>\n" +
"        </div>\n" +
"        \n" +
"        <br />";
    }
    
}
