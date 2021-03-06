package cn.edu.sdufe.sn20170667208.view;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.sdufe.sn20170667208.DButil.MyDBHelper;
import cn.edu.sdufe.sn20170667208.R;
import cn.edu.sdufe.sn20170667208.dao.GoodsDao;
import com.xuexiang.xui.XUI;


public class MainActivity extends AppCompatActivity {
    private MyDBHelper myDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XUI.initTheme(this);//调整应用的基础主题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ImageView button_user=(ImageView) findViewById(R.id.user);
//        button_user.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this, UserNotLogin.class);
//                startActivity(intent);
//            }
//        });
    }
//    public void createDB(View view){
//        myDBHelper =new MyDBHelper(this,"Shop.db",null,1);
//        SQLiteDatabase database=myDBHelper.getWritableDatabase();
//        System.out.println("success create");
//        database.close();
//    }
    public void toGoodsFood(View view){
        Intent intent=new Intent(MainActivity.this,GoodsList.class);
        intent.putExtra("type","食品区");
        startActivity(intent);
    }
    public void toGoodsDaily(View view){
        Intent intent=new Intent(MainActivity.this,GoodsList.class);
        intent.putExtra("type","日用品");
        startActivity(intent);
    }
    public void toHome(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void toCar(View view){
        Intent intent=new Intent(this,CarActivity.class);
        startActivity(intent);
    }
    public void toWeather(View view){
        Intent intent=new Intent(this,weatherActivity.class);
        startActivity(intent);
    }
    public void toUser(View view){
        Intent intent=new Intent(this,UserNotLogin.class);
        startActivity(intent);
    }
    public void selectByName(View view){
        Intent intent =new Intent(this,GoodsDetail.class);
        EditText text=(EditText) findViewById(R.id.search);
        intent.putExtra("Goodsname",text.getText().toString());
    }
}
