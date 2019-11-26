package cn.edu.sdufe.sn20170667208.entity;

public class Recently {
private String photo;
private double price;
private  String title;

    public Recently(String photo, double price, String title) {
        this.photo = photo;
        this.price = price;
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
