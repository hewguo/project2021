package com.suola.project.model;

import java.util.List;

/**
 * @ClassName PrjUtils
 * @Description TODO
 * @Author hewguo
 * @Date 2020-12-24 12:21
 * @Version 1.0
 **/
public class PrjUtils {
    public static String arrayToStr(List list){
        if(list!=null&&list.size()>0){
            String str="";
            for(Object obj:list){
                str+=obj.toString()+",";
            }
            str=str.substring(0,str.length()-1);
            return "["+str+"]";
        }else{
            return "[]";
        }
    }
}
