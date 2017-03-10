package com.youjuke.optimalmaterialtreasure.entity;

/**
 * Created by Administrator on 2017/2/16.
 */
public class Qu extends Address {
    private int d_id;
    private String d_name;

    @Override
    public int id() {
        return d_id;
    }

    @Override
    public String name() {
        return d_name;
    }
}
