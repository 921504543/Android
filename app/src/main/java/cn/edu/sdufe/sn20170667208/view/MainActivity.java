package cn.edu.sdufe.sn20170667208.view;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.sdufe.sn20170667208.DButil.MyDBHelper;
import cn.edu.sdufe.sn20170667208.R;


public class MainActivity extends AppCompatActivity {
    private MyDBHelper myDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView button_user=(ImageView) findViewById(R.id.user);
        button_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, UserNotLogin.class);
                startActivity(intent);
            }
        });
    }
    public void createDB(View view){
        myDBHelper =new MyDBHelper(this,"Shop.db",null,1);
        SQLiteDatabase database=myDBHelper.getWritableDatabase();
        System.out.println("success create");
        database.close();
    }
}
