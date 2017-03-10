package com.youjuke.optimalmaterialtreasure.entity;

/**
 * 描述：商品类别二级商品的实体分类
 * author：zyb
 * Created by Administrator on 2017/2/14.
 */

public class MaterialsClassify {
    public MaterialsClassify(int cid, String name) {
        this.cid = cid;
        this.name = name;
    }

    private int cid;

    @Override
    public String toString() {
        return "MaterialsClassify{" +
                "cid='" + cid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    private String name;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
