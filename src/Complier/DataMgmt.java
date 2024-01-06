package Complier;

import Engine.other.Option;
import Engine.scenes.Scene;

import java.util.HashMap;
import java.util.Map;

public class DataMgmt<T extends Scene> {
    private Map<String, T> dataArr = new HashMap<>();
    private Map<String, Option> optionArr = new HashMap<>();
    public DataMgmt() {}

    public void setKey(String key, T value) {
        if (dataArr.containsKey(key)) {
            dataArr.replace(key, value);
        } else {
            dataArr.put(key, value);
        }
    }

    public void setKey(String key, Option value) {
        if (optionArr.containsKey(key)) {
            optionArr.replace(key, value);
        } else {
            optionArr.put(key, value);
        }
    }

    public T getValue(String key) {
        return dataArr.get(key);
    }

    public Option getValue_O(String key) {
        return optionArr.get(key);
    }

    public boolean hasKey(String key) {
        return dataArr.containsKey(key);
    }

    public boolean hasKey_O(String key) {
        return optionArr.containsKey(key);
    }

}
