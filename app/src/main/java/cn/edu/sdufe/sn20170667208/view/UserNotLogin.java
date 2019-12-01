package cn.edu.sdufe.sn20170667208.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.sdufe.sn20170667208.R;

public class UserNotLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_not_login);
    }
    public void toUserLogging(View view){
        Intent intent=new Intent(this, UserLogging.class);
        startActivity(intent);
    }
    public void toUserSignUp(View view){
        Intent intent=new Intent(this,UserSignUp.class);
        startActivity(intent);
    }

}
