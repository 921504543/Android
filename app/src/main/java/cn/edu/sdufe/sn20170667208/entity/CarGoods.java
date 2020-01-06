package cn.edu.sdufe.sn20170667208.entity;

public class CarGoods {

    private String title;
    private String photo;
    private String number;//商品数量
    private  double price;

    public CarGoods(String title, String photo, String number, double price) {
        this.title = title;
        this.photo = photo;
        this.number = number;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
