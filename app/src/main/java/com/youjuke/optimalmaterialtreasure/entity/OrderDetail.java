package com.youjuke.optimalmaterialtreasure.entity;

import java.util.List;

/**
 * 描述: TODO
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian xiao     创建日期: 2017-02-20 13:54
 */
public class OrderDetail {

    /**
     * totalInfo : {"totalCount":2,"totalPrice":152,"accountMoney":"0.00","orders":[{"id":"92","addtime":"2017-02-20 13:52:20"}]}
     * materials : [{"materialName":"光纤箱","materialProperty":"1","brandId":"15","brandName":"光大","materialId":"507","materialClassifyId":"41","materialModel":"GRB-L","materialNorms":"300*400","materialColor":"白色","materialUnit":"只","materialLoss":"0.00","materialPrice":"144.00","materialCount":"1","totalPrice":144},{"materialName":"滑石粉","materialProperty":"1","brandId":"92","brandName":"家佳宝","materialId":"1432","materialClassifyId":"54","materialModel":"500目","materialNorms":"15kg","materialColor":"无","materialUnit":"包","materialLoss":"0.00","materialPrice":"8.00","materialCount":"1","totalPrice":8}]
     * buildingSite : {"ownerName":"as","ownerMobile":"13676941583","provinceId":"4","provinceName":"山西","cityId":"18","cityName":"晋城","districtId":"252","districtName":"泽州县","ownerAddress":"SASsaSAs","acceptAddress":"山西&nbsp;晋城&nbsp;泽州县&nbsp;SASsaSAs"}
     */

    private TotalInfoBean totalInfo;
    private BuildingSiteBean buildingSite;
    private List<MaterialsBean> materials;

    public TotalInfoBean getTotalInfo() {
        return totalInfo;
    }

    public void setTotalInfo(TotalInfoBean totalInfo) {
        this.totalInfo = totalInfo;
    }

    public BuildingSiteBean getBuildingSite() {
        return buildingSite;
    }

    public void setBuildingSite(BuildingSiteBean buildingSite) {
        this.buildingSite = buildingSite;
    }

    public List<MaterialsBean> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MaterialsBean> materials) {
        this.materials = materials;
    }

    public static class TotalInfoBean {
        /**
         * totalCount : 2
         * totalPrice : 152
         * accountMoney : 0.00
         * orders : [{"id":"92","addtime":"2017-02-20 13:52:20"}]
         */

        private String totalCount;
        private String totalPrice;
        private String accountMoney;
        private List<OrdersBean> orders;

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getAccountMoney() {
            return accountMoney;
        }

        public void setAccountMoney(String accountMoney) {
            this.accountMoney = accountMoney;
        }

        public List<OrdersBean> getOrders() {
            return orders;
        }

        public void setOrders(List<OrdersBean> orders) {
            this.orders = orders;
        }

        public static class OrdersBean {
            /**
             * id : 92
             * addtime : 2017-02-20 13:52:20
             */

            private String id;
            private String addtime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }
        }
    }

    public static class BuildingSiteBean {
        /**
         * ownerName : as
         * ownerMobile : 13676941583
         * provinceId : 4
         * provinceName : 山西
         * cityId : 18
         * cityName : 晋城
         * districtId : 252
         * districtName : 泽州县
         * ownerAddress : SASsaSAs
         * acceptAddress : 山西&nbsp;晋城&nbsp;泽州县&nbsp;SASsaSAs
         */

        private String ownerName;
        private String ownerMobile;
        private String provinceId;
        private String provinceName;
        private String cityId;
        private String cityName;
        private String districtId;
        private String districtName;
        private String ownerAddress;
        private String acceptAddress;

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getOwnerMobile() {
            return ownerMobile;
        }

        public void setOwnerMobile(String ownerMobile) {
            this.ownerMobile = ownerMobile;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public String getOwnerAddress() {
            return ownerAddress;
        }

        public void setOwnerAddress(String ownerAddress) {
            this.ownerAddress = ownerAddress;
        }

        public String getAcceptAddress() {
            return acceptAddress;
        }

        public void setAcceptAddress(String acceptAddress) {
            this.acceptAddress = acceptAddress;
        }
    }


}
