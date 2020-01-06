package cn.edu.sdufe.sn20170667208.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cn.edu.sdufe.sn20170667208.R;
import com.xuexiang.xui.XUI;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//天气页面
//使用网络的时候需要在配置文件里加个权限
public class weatherActivity extends AppCompatActivity {
    //数据来源是聚合

    TextView text_one;
    TextView text_two;
    TextView text_three;
    TextView text_four;
    TextView text_five;
    TextView text_six;
    TextView textView_update;
    TextView cityview;
    String city1;
    String city2="%E6%B5%8E%E5%8D%97";
    //，由于获取天气数据需要用到网络，而涉及到网络传输，所用的时间有时会很长，所以不能放在与界面显示有关的线程里面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XUI.initTheme(this);//调整应用的基础主题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_weather);
        text_one=(TextView)findViewById(R.id.one);
        text_two=(TextView)findViewById(R.id.two);
        text_three=(TextView)findViewById(R.id.three);
        text_four=(TextView)findViewById(R.id.four);
        text_five=(TextView)findViewById(R.id.five);
        text_six=(TextView)findViewById(R.id.six);
        textView_update=(TextView)findViewById(R.id.suggest);
        cityview=(TextView)findViewById(R.id.city);
    }

    public void search(View view){
        EditText editText1 =(EditText)findViewById(R.id.search);
        city1=editText1.getText().toString();
        city2=toUtf8String(city1);
        new Thread(new httpRequest()).start();
        onStart();
    }

    public String toUtf8String(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            char c;
            for (int i = 0; i < s.length(); i++) {
                c = s.charAt(i);
                if (c >= 0 && c <= 255) {
                    stringBuffer.append(c);
                } else {
                    byte[] b;
                    b = Character.toString(c).getBytes("utf-8");
                    for (int j = 0; j < b.length; j++) {
                        int k = b[j];
                        if (k < 0)
                            k += 256;
                        stringBuffer.append("%" + Integer.toHexString(k).toUpperCase());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }


    //线程中的参数，实现Runnale接口的实现类
    @Override
    protected void onStart() {
        super.onStart();
        new Thread(new httpRequest()).start();     
    }

    //内部类
    private class httpRequest implements Runnable{

        //请求数据
        @Override
        public void run() {
            try {
                URL url=new URL("http://v.juhe.cn/weather/index?format=2&cityname="+city2+"&key=5f276e2849077763ad7e0b092ca92392");
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                connection.setConnectTimeout(100000);  //设置等待时间
                connection.setRequestMethod("GET");  //设置请求方法
                connection.setDoInput(true);  //向服务器传输数据
                connection.setDoOutput(true);//向服务器获取数据
                connection.connect();   //建立连接
                if(connection.getResponseCode()!=HttpURLConnection.HTTP_BAD_REQUEST){

                    //BufferedReader读取文本
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));  //InputStreamReader是字节流，适合pdf或者图片
                    String temp="";
                    final StringBuilder response=new StringBuilder();
                    while ((temp=bufferedReader.readLine())!=null){
                        response.append(temp);
                    }

                    Log.d(httpRequest.class.toString(),response.toString());

                    bufferedReader.close();  //关掉输入流

                    JSONObject jsonObject=new JSONObject(response.toString());
                    String resultcode=jsonObject.getString("resultcode");
                    JSONObject result=jsonObject.getJSONObject("result");
                    JSONObject jsonObject1=result.getJSONObject("sk");
                    JSONObject jsonObject2=result.getJSONObject("today");
                    String tempure=jsonObject1.getString("temp");
                    final String wind_direction=jsonObject1.getString("wind_direction");
                    final String temperature=jsonObject2.getString("temperature");
                    final String weather_state=jsonObject2.getString("weather");
                    final String wind_strength=jsonObject1.getString("wind_strength");
                    final String humidity=jsonObject1.getString("humidity");
                    final String update=jsonObject1.getString("time");
                    final String suggest=jsonObject2.getString("dressing_advice");
                    final String city=jsonObject2.getString("city");

                    text_one.post(new Runnable() {
                        @Override
                        public void run() {
                            text_one.setText("天气状况:"+weather_state);
                        }
                    });
                    text_two.post(new Runnable() {
                        @Override
                        public void run() {
                            text_two.setText("温度:"+temperature);
                        }
                    });
                    text_three.post(new Runnable() {
                        @Override
                        public void run() {
                            text_three.setText("风向:"+wind_direction);
                        }
                    });
                    text_four.post(new Runnable() {
                        @Override
                        public void run() {
                            text_four.setText("风力:"+wind_strength);
                        }
                    });
                    text_five.post(new Runnable() {
                        @Override
                        public void run() {
                            text_five.setText("湿度:"+humidity);
                        }
                    });
                    text_six.post(new Runnable() {
                        @Override
                        public void run() {
                            text_six.setText("更新时间:"+update);
                        }
                    });
                    textView_update.post(new Runnable() {
                        @Override
                        public void run() {
                            textView_update.setText("专家建议:"+suggest);
                        }
                    });
                    cityview.post(new Runnable() {
                        @Override
                        public void run() {
                            cityview.setText(city);
                        }
                    });
                }
                connection.disconnect();  //断开连接

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}
