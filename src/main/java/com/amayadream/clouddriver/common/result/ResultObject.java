package com.amayadream.clouddriver.common.result;

import com.alibaba.fastjson.JSONObject;

/**
 * Results中的data, 负责快速组装data的数据格式
 * @author : Amayadream
 * @date :   2017-08-17 14:40
 */
public class ResultObject {

    private final JSONObject jsonObject;

    private ResultObject(Builder builder) {
        this.jsonObject = builder.jsonObject;
    }

    public JSONObject value() {
        return this.jsonObject;
    }

    public static class Builder{

        private final JSONObject jsonObject;

        public Builder () {
            jsonObject = new JSONObject();
        }

        public Builder append(String displayName, Object obj) {
            if (obj instanceof ResultObject) {
                jsonObject.put(displayName, ((ResultObject) obj).value());
            } else {
                jsonObject.put(displayName, obj);
            }
            return this;
        }

        public ResultObject build(){
            return new ResultObject(this);
        }
    }

}
