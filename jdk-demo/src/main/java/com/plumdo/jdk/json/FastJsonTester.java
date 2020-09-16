package com.plumdo.jdk.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 * @author wengwh
 * @date 2020/9/16
 */
public class FastJsonTester {
    public static void main(String[] args) {
        JSONObject jsonObject =  new JSONObject();
        jsonObject.put("id",7000);
        JSONObject jsonObject1 =  new JSONObject();
        jsonObject1.put("uinfo",jsonObject);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject1);
        for (Object userInfo : jsonArray) {
            Object uid = JSONPath.eval(userInfo, "$.uinfo.id2.name");
            System.out.println(""+uid);
        }
        double fightingLost = 1D;

        Object s= Math.round(fightingLost * 100) / 100.0;
        System.out.println(""+s);
    }
}
