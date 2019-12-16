package cn.edu.sdufe.sn20170667208.view;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.sdufe.sn20170667208.DButil.MyDBHelper;
import cn.edu.sdufe.sn20170667208.R;
import cn.edu.sdufe.sn20170667208.dao.UserDao;
import com.xuexiang.xui.XUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        //　获取资源
        ListView list1 = (ListView)findViewById(R.id.list_view);
        //　构建Adapter
        String[] arrayName = {"孙悟空","牛魔王","猪八戒","哪吒","江流儿"};
        // 头像图标
        int[] headerID = {R.drawable.home,R.drawable.home,R.drawable.home,R.drawable.home,R.drawable.home};

        List<Map<String, Object>> listTest = new ArrayList<Map<String, Object>>();

        for(int i = 0; i < arrayName.length; i++){
            Map<String, Object> listitem = new HashMap<String, Object>();

            listitem.put("header", headerID[i]);
            listitem.put("name", arrayName[i]);
            listTest.add(listitem);
        }

        // 创建SimpleAdapter
        // Context context	上下文对象
        //List<? extends Map<String, ?>> data	数据源是含有Map的一个集合
        //int resource	每一个item的布局文件
        //String[] from	new String[]{}数组，数组的里面的每一项要与第二个参数中的存入map集合的的key值一样，一一对应
        //int[] to	new int[]{}数组，数组里面的第三个参数中的item里面的控件id。

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listTest, R.layout.list_array,
                new String[] {"header", "name"}, new int[] {R.id.header, R.id.name});

        // 为listview设置适配器
        list1.setAdapter(simpleAdapter);

    }
}