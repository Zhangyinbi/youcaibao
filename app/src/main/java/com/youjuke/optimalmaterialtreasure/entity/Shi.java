package com.youjuke.optimalmaterialtreasure.entity;

/**
 * Created by Administrator on 2017/2/16.
 */
public class Shi extends Address {
    private int c_id;
    private String c_name;

    @Override
    public int id() {
        return c_id;
    }

    @Override
    public String name() {
        return c_name;
    }
}
