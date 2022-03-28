package com.focus.focusunitybridge;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.unity3d.player.UnityPlayer;

import java.util.Map;

public class BridgeData {
    /**
     * 此次调用的的标识，需要原样传回去
     */
    private String Key;

    /**
     * 此次调用过来的参数
     */
    private Map<String,Object> Params;

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public Map<String, Object> getParams() {
        return Params;
    }

    public void setParams(Map<String, Object> params) {
        Params = params;
    }

    public String getValue(String key, String defaultVal){
        String val = defaultVal;
        if (getParams() != null && getParams().containsKey(key)){
            val = getParams().get(key).toString();
        }
        return val;
    }

    public int getValue(String key, int defaultVal){
        int val = defaultVal;
        if (getParams() != null && getParams().containsKey(key)){
            val = (int)getParams().get(key);
        }
        return val;
    }

    public boolean getValue(String key, boolean defaultVal){
        boolean val = defaultVal;
        if (getParams() != null && getParams().containsKey(key)){
            val = (boolean)getParams().get(key);
        }
        return val;
    }

    public String[] getValue(String key, String[] defaultVal){
        String[] val = defaultVal;
        if (getParams() != null && getParams().containsKey(key)){
            val = (String[])getParams().get(key);
        }
        return val;
    }

    public static BridgeData parseDataFromJson(String json){
        return JSON.parseObject(json, BridgeData.class);
    }

    public static Activity getUnityPlayerActivity(){
        return UnityPlayer.currentActivity;
    }

    public void notifyUnity(Map<String,Object> paramsMap){
        BridgeData bridgeData = new BridgeData();
        bridgeData.setKey(this.getKey());
        bridgeData.setParams(paramsMap);
        String json = JSON.toJSONString(bridgeData);
        UnityPlayer.UnitySendMessage("FocusUnityBridge", "OnFocusUnityBridgeCallback", json);
    }
}
