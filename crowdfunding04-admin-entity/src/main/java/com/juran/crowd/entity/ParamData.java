package com.juran.crowd.entity;

import java.util.List;

/**
 * 作者： Juran on 2021/12/30 17:58
 * 作者博客：iit.la
 */
public class ParamData {
    private List<Integer> array;
    public ParamData() {

    }
    public ParamData(List<Integer> array) {
        super();
        this.array = array;
    }
    @Override
    public String toString() {
        return "ParamData{" +
                "array=" + array +
                '}';
    }

    public List<Integer> getArray() {
        return array;
    }

    public void setArray(List<Integer> array) {
        this.array = array;
    }
}
