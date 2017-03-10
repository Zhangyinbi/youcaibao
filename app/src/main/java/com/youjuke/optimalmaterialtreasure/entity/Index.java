package com.youjuke.optimalmaterialtreasure.entity;

import java.util.List;

/**
 * 描述: 主页数据实体类
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     zyb     创建日期: 2017-02-13 9:52
 */
public class Index {
    public List<String> getBanner_list() {
        return banner_list;
    }

    public void setBanner_list(List<String> banner_list) {
        this.banner_list = banner_list;
    }

    public List<Goods> getHot_list() {
        return hot_list;
    }

    public void setHot_list(List<Goods> hot_list) {
        this.hot_list = hot_list;
    }

    public List<Goods> getBetter_list() {
        return better_list;
    }

    public void setBetter_list(List<Goods> better_list) {
        this.better_list = better_list;
    }

    private List<String > banner_list;
    private List<Goods> better_list;

    public Index(List<String> banner_list, List<Goods> better_list, List<Goods> hot_list) {
        this.banner_list = banner_list;
        this.better_list = better_list;
        this.hot_list = hot_list;
    }

    private List<Goods> hot_list;
    public class Goods{
        private int cid;
        private int fid;
        private int gid;
        private int material_config_id;

        public int getMaterial_config_id() {
            return material_config_id;
        }

        public void setMaterial_config_id(int material_config_id) {
            this.material_config_id = material_config_id;
        }

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }
        private String imgUrl;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getFid() {
            return fid;
        }

        public void setFid(int fid) {
            this.fid = fid;
        }

        public int getId() {
            return gid;
        }

        public void setId(int id) {
            this.gid = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        private String name;
        private String price;
    }
}
