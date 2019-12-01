package cn.edu.sdufe.sn20170667208.view;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.sdufe.sn20170667208.DButil.MyDBHelper;
import cn.edu.sdufe.sn20170667208.R;

public class UserLogging extends AppCompatActivity {
    EditText loginName;
    EditText loginPassword;
    String loginName_db;
    String loginPassword_db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_ing);
        loginName= (EditText) findViewById(R.id.login_name);
        loginPassword=(EditText)findViewById(R.id.login_password);
    }
    public void toUserLogged(View view){
        String username=loginName.getText().toString();
        String password=loginPassword.getText().toString();
        MyDBHelper myDBHelper=new MyDBHelper(this,"Shop.db",null,1);
        SQLiteDatabase database=myDBHelper.getReadableDatabase();
        Log.i("usernameAndPassword","----->username:"+username+"----password:"+password);
        if (username.equals("")||password.equals("")){
            Toast.makeText(UserLogging.this,"用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        }else {
            //从数据库中按照username 查出password
            try{
                Cursor cursor=database.query("user",new String[]{"username","password"},"username=?",new String[]{username},null,null,null);
                while(cursor.moveToNext()){
                    int index0=cursor.getColumnIndex("username");
                    loginName_db=cursor.getString(index0);
                    int index=cursor.getColumnIndex("password");
                    loginPassword_db=cursor.getString(index);
                    System.out.println("----->username:"+username+"   password:"+password);
                    Log.i("usernameAndPassword","----->username:"+username+"   password:"+password);
                }
                cursor.close();
            }catch(Exception e){
            }finally{
                database.close();
            }
            //进行用户名，密码比对
            if(username.equals(loginName_db)&&password.equals(loginPassword_db)){
                Intent intent =new Intent(this,UserLogged.class);
                String content_name=loginName_db;
                String content_passwd=loginPassword_db;
                if(content_name!=null&&content_passwd!=null){
                    intent.putExtra("transform_username",content_name);
                    intent.putExtra("transform_passwd",content_passwd);
                }
                startActivity(intent);
            }else{
                Toast.makeText(UserLogging.this,"密码错误", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void returnToNotLogin(View view){
        Intent intent =new Intent(this,UserNotLogin.class);
        startActivity(intent);
    }

}