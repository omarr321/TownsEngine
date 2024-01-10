package Complier;

import Engine.other.Option;
import Engine.scenes.Scene;

import java.util.HashMap;
import java.util.Map;

public class DataMgmt<T extends Scene> {
    private Map<String, T> dataArr = new HashMap<>();
    private Map<String, Option> optionArr = new HashMap<>();

    private Map<String, Object> dataArrType = new HashMap<>();
    public DataMgmt() {}

    public void setKey(String key, T value) {
        if (this.dataArr.containsKey(key)) {
            this.dataArr.replace(key, value);
        } else {
            this.dataArr.put(key, value);
        }
    }

    public void setKey(String key, Option value) {
        if (this.optionArr.containsKey(key)) {
            this.optionArr.replace(key, value);
        } else {
            this.optionArr.put(key, value);
        }
    }

    public void setKeyType(String key,Object classType) {
        if (this.dataArrType.containsKey(key)) {
            this.dataArrType.replace(key, classType);
        } else {
            this.dataArrType.put(key, classType);
        }
    }

    public T getValue(String key) {
        return dataArr.get(key);
    }

    public Object getValueType(String key) {
        return dataArrType.get(key);
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
