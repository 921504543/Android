package cn.edu.sdufe.sn20170667208.view;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.sdufe.sn20170667208.R;
import cn.edu.sdufe.sn20170667208.dao.GoodsDao;
import cn.edu.sdufe.sn20170667208.dao.OrdersDao;
import cn.edu.sdufe.sn20170667208.dao.ShoppingCarDao;
import cn.edu.sdufe.sn20170667208.entity.CarGoods;
import cn.edu.sdufe.sn20170667208.entity.Goods;
import cn.edu.sdufe.sn20170667208.entity.Orders;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.xuexiang.xui.XUI;

import java.util.List;

public class GoodsDetail extends AppCompatActivity {
    ShoppingCarDao shoppingCarDao=new ShoppingCarDao(this);
    GoodsDao goodsDao=new GoodsDao(this);
    private TextView textView;
    private ImageView imageView;
    private  TextView good_title,good_price,good_introduce;
    OrdersDao ordersDao=new OrdersDao(this);


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XUI.initTheme(this);//调整应用的基础主题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail);
        textView=(TextView)findViewById(R.id.detail_number);
        good_title=(TextView)findViewById(R.id.detail_title);
        good_price=(TextView)findViewById(R.id.detail_price);
        imageView=(ImageView)findViewById(R.id.detail_img);
        textView.setText("2");
        good_introduce=(TextView)findViewById(R.id.detail_introduce);

        
        
        Intent intent=getIntent();
        String img=intent.getStringExtra("img");
        String tilte=intent.getStringExtra("title");
        double price=intent.getDoubleExtra("price",100);


        //获取输入的信息，从而查询商品
        List titleList= goodsDao.queryByName(tilte);
            Goods goods = (Goods) titleList.get(0);
            String introduce = goods.getIntroduce();
            good_title.setText(tilte);
            String price1 = String.valueOf(price);
            good_price.setText("价格：" + price1);
            good_introduce.setText("商品介绍：" + introduce);
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(GoodsDetail.this).build();
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.init(config);
            imageLoader.displayImage(img, imageView);
    }
    
    
    
    
    
    public void Subduction(View view){
        String str=textView.getText().toString();
        int sum=Integer.parseInt(str);
        int sum_goods=sum-1;
        if(sum_goods==0){
            Toast.makeText(this,"商品已售完",Toast.LENGTH_SHORT).show();
            textView.setText("0");
        }else if(sum_goods>0){

            textView.setText(String.valueOf(sum_goods));
        }else {
            textView.setText("0");
        }
    }
    
    
    
    public void Addition(View view){
        String str=textView.getText().toString();
        int sum=Integer.parseInt(str);
        int sum_goods=sum+1;
        textView.setText(String.valueOf(sum_goods));
    }



    public void detail_back(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }



    public void insertShoppingCar(View view){           //跳转到购物车
        Intent intent=getIntent();
        String img=intent.getStringExtra("img");
        String tilte=intent.getStringExtra("title");
        double price=intent.getDoubleExtra("price",100);
        String number=textView.getText().toString();
        CarGoods carGoods=new CarGoods(tilte,img,number,price);
        shoppingCarDao.inertIntoCar(carGoods);

        Toast.makeText(this,"已成功加入购物车",Toast.LENGTH_SHORT).show();


    }



    public void buyNow(View view){          //立即购买

        Intent intent=getIntent();
        String img=intent.getStringExtra("img");
        String tilte=intent.getStringExtra("title");
        double price=intent.getDoubleExtra("price",100);
        Orders orders=new Orders(img,price,tilte);
        ordersDao.insertIntoOrders(orders);
        Toast.makeText(GoodsDetail.this,"购买完成",Toast.LENGTH_SHORT).show();
    }
}

