package cn.edu.sdufe.sn20170667208.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.sdufe.sn20170667208.R;
import cn.edu.sdufe.sn20170667208.dao.GoodsDao;
import cn.edu.sdufe.sn20170667208.dao.RecentlyDao;
import cn.edu.sdufe.sn20170667208.entity.Goods;
import cn.edu.sdufe.sn20170667208.entity.Recently;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.xuexiang.xui.XUI;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsList extends AppCompatActivity {
    private ListView listView;
    GoodsDao goodsDao=new GoodsDao(this);
    RecentlyDao recentlyDao=new RecentlyDao(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XUI.initTheme(this);//调整应用的基础主题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_list);
        listView=(ListView)findViewById(R.id.good_list_view);
        Intent intent=getIntent();
        String type=intent.getStringExtra("type");
        System.out.println(type);
        List list = goodsDao.queryByType(type);//根据名字得到符合条件的集合
        final List goodsList = new ArrayList();//适配器所加载的数组，由map组成

        //初始化goodsList数组
        for (int i = 0; i < list.size(); i++) {
            Map map = new HashMap();
            Goods goods = (Goods) list.get(i);
            map.put("img", goods.getPhoto());
            map.put("title", goods.getTitle());
            map.put("price", goods.getPrice());
            goodsList.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, goodsList, R.layout.goods_list_array, new String[]{"img", "title", "price"}, new int[]{R.id.img, R.id.title, R.id.price});
        //适配器干扰imageview显示方式
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            public boolean setViewValue(View v, Object o, String s) {
                if (v.getId() == R.id.img) {
                    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(GoodsList.this).build();
                    ImageLoader imageLoader = ImageLoader.getInstance();
                    imageLoader.init(config);
                    imageLoader.displayImage(o.toString(), (ImageView) v);
                    return true;
                }
                return false;
            }
        });
        listView.setAdapter(simpleAdapter);
        //商品列表跳转到商品详情
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map goodsmap=(Map) parent.getItemAtPosition(position);
                String img=(String) goodsmap.get("img");
                String title=(String) goodsmap.get("title");
                double price=(Double) goodsmap.get("price");
                Intent intent=new Intent(GoodsList.this,GoodsDetail.class);
                intent.putExtra("img",img);
                intent.putExtra("title",title);
                intent.putExtra("price",price);
                startActivity(intent);
                Recently recently=new Recently(img,price,title);
                recentlyDao.inertIntoRecently(recently);
            }
        });
    }
    public void toHome(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void toCar(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void toWeather(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void toUser(View view){
        Intent intent=new Intent(this,UserNotLogin.class);
        startActivity(intent);
    }
}
