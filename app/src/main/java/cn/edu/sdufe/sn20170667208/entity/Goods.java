package cn.edu.sdufe.sn20170667208.entity;

/**
 * Created by dell on 2018/11/24.
 */

public class Goods {
    private int goodsid;//商品编号
    private  String title;//商品名称
    private  String introduce;//商品介绍
    private  double price;//商品价格
    private  String photo;//商品图片
    private  String type;//商品类别

    public Goods(String title,String introduce,double price,String photo,String type){
        super();
        this.title=title;
        this.introduce=introduce;
        this.price=price;
        this.photo=photo;
        this.type=type;

    }
    public int getGoodsid() {
        return goodsid;
    }
    public void setGoodsid(int goodsid) {
        this.goodsid = goodsid;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getIntroduce() {
        return introduce;
    }
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
