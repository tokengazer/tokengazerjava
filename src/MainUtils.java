//package com.tg.platform.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainUtils {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readLines(new File("/home/deanyu/桌面/json"), Charsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        for(String line : lines){
            sb.append(line);
        }
        System.out.println(sb.toString());
        CoinList coinList = JSON.parseObject(sb.toString(), new TypeReference<CoinList>(){});
        System.out.println(coinList.getData().size());
    }

    public static class CoinList{
        private Map<String, DataContainer> Data;

        public Map<String, DataContainer> getData() {
            return Data;
        }

        public void setData(Map<String, DataContainer> data) {
            Data = data;
        }
    }

    public static class DataContainer{
        private String Id;

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }
    }
}
