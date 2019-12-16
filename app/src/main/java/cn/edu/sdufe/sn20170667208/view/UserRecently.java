package cn.edu.sdufe.sn20170667208.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.sdufe.sn20170667208.R;
import cn.edu.sdufe.sn20170667208.dao.RecentlyDao;
import cn.edu.sdufe.sn20170667208.entity.Recently;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.xuexiang.xui.XUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRecently extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        XUI.initTheme(this);//调整应用的基础主题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_recently);
        listView=findViewById(R.id.recently_list_view);
        RecentlyDao recentlyDao=new RecentlyDao(this);
        List list=recentlyDao.queryOrders();
        List recentlyList=new ArrayList();
        for (int i=0;i<list.size();i++){
            Map map=new HashMap();
            Recently recently=(Recently) list.get(i);
            map.put("img",recently.getPhoto());
            map.put("title",recently.getTitle());
            recentlyList.add(map);

        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,recentlyList,R.layout.recently_list_array,new String[]{"img","title"},new int[]{R.id.img,R.id.title});
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            public boolean setViewValue(View v, Object o, String s) {
                if (v.getId() == R.id.img) {
                    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(UserRecently.this).build();
                    ImageLoader imageLoader = ImageLoader.getInstance();
                    imageLoader.init(config);
                    imageLoader.displayImage(o.toString(), (ImageView) v);
                    return true;
                }
                return false;
            }
        });
        listView.setAdapter(simpleAdapter);
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
