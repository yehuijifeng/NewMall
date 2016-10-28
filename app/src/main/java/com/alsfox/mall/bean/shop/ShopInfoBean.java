package com.alsfox.mall.bean.shop;

import com.alsfox.mall.bean.merchant.MerchantInfoBean;
import com.alsfox.mall.http.request.RequestUrls;

import java.util.List;

/**
 * Created by 浩 on 2016/10/28.
 * 商品信息
 */

public class ShopInfoBean {

    private float shopHpLv;               // 商品好评率
    private String index;                  // 商品列表排序方式，pub：按最新排序；sale：按销量排序；priceAsc：价格升序；
    private int isCollection;          // 是否收藏，0表示没有收藏，1表示已经收藏
    private int userId;                // 用户ID
    private List<ShopImageBean> shopImgList;   // 商品相册图片列表
    private List<ShopSpecBean> shopSpecList; // 商品规格列表
    private int shopId;            // 商品ID
    private int typeId;            // 分类ID
    private String shopName;           // 商品名称
    private String shopIcon;           // 商品图片地址
    private int isGuige;           // 0使用，-1不使用，默认0
    private int isTimeout = -1;//是否是限时抢购商品,-1,不是，0，是；
    private double shopPrice;          // 商品价格，如果使用规格，该值无效
    private int shopStock;         // 商品库存，如果使用规格，该值无效
    private double dikouPrice;         // 可抵扣金额，如果使用规格，该值无效
    private int getIntegral;       // 可获取积分，如果使用规格，该值无效
    private double showPrice;          // 商品显示价格
    private double delPrice;            //商品抵扣价格
    private String shopIntr;           // 商品图文信息
    private int shopSaleNum;       // 商品销量，默认0
    private int shopPjNum;         // 商品评价数量，默认0
    private int shopHpNum;         // 商品好评数量，默认0
    private float shopZhPj;//商品综合评价
    private ShopCommentBean shopComment;// 商品最新一条评价
    private String shopImg;//第二种图片排列方式
    private MerchantInfoBean dianpuInfo;//所属店铺


    public float getShopHpLv() {
        return shopHpLv;
    }

    public void setShopHpLv(float shopHpLv) {
        this.shopHpLv = shopHpLv;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public int getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(int isCollection) {
        this.isCollection = isCollection;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<ShopImageBean> getShopImgList() {
        return shopImgList;
    }

    public void setShopImgList(List<ShopImageBean> shopImgList) {
        this.shopImgList = shopImgList;
    }

    public List<ShopSpecBean> getShopSpecList() {
        return shopSpecList;
    }

    public void setShopSpecList(List<ShopSpecBean> shopSpecList) {
        this.shopSpecList = shopSpecList;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopIcon() {
        return RequestUrls.IP + shopIcon;
    }

    public void setShopIcon(String shopIcon) {
        this.shopIcon = shopIcon;
    }

    public int getIsGuige() {
        return isGuige;
    }

    public void setIsGuige(int isGuige) {
        this.isGuige = isGuige;
    }

    public int getIsTimeout() {
        return isTimeout;
    }

    public void setIsTimeout(int isTimeout) {
        this.isTimeout = isTimeout;
    }

    public double getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(double shopPrice) {
        this.shopPrice = shopPrice;
    }

    public int getShopStock() {
        return shopStock;
    }

    public void setShopStock(int shopStock) {
        this.shopStock = shopStock;
    }

    public double getDikouPrice() {
        return dikouPrice;
    }

    public void setDikouPrice(double dikouPrice) {
        this.dikouPrice = dikouPrice;
    }

    public int getGetIntegral() {
        return getIntegral;
    }

    public void setGetIntegral(int getIntegral) {
        this.getIntegral = getIntegral;
    }

    public double getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(double showPrice) {
        this.showPrice = showPrice;
    }

    public double getDelPrice() {
        return delPrice;
    }

    public void setDelPrice(double delPrice) {
        this.delPrice = delPrice;
    }

    public String getShopIntr() {
        return shopIntr;
    }

    public void setShopIntr(String shopIntr) {
        this.shopIntr = shopIntr;
    }

    public int getShopSaleNum() {
        return shopSaleNum;
    }

    public void setShopSaleNum(int shopSaleNum) {
        this.shopSaleNum = shopSaleNum;
    }

    public int getShopPjNum() {
        return shopPjNum;
    }

    public void setShopPjNum(int shopPjNum) {
        this.shopPjNum = shopPjNum;
    }

    public int getShopHpNum() {
        return shopHpNum;
    }

    public void setShopHpNum(int shopHpNum) {
        this.shopHpNum = shopHpNum;
    }

    public float getShopZhPj() {
        return shopZhPj;
    }

    public void setShopZhPj(float shopZhPj) {
        this.shopZhPj = shopZhPj;
    }

    public ShopCommentBean getShopComment() {
        return shopComment;
    }

    public void setShopComment(ShopCommentBean shopComment) {
        this.shopComment = shopComment;
    }

    public String getShopImg() {
        return RequestUrls.IP + shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public MerchantInfoBean getDianpuInfo() {
        return dianpuInfo;
    }

    public void setDianpuInfo(MerchantInfoBean dianpuInfo) {
        this.dianpuInfo = dianpuInfo;
    }
}
