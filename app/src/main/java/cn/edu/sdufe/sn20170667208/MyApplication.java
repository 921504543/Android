
package cn.edu.sdufe.sn20170667208;

import android.app.Application;
import cn.edu.sdufe.sn20170667208.dao.GoodsDao;
import cn.edu.sdufe.sn20170667208.dao.RecentlyDao;
import cn.edu.sdufe.sn20170667208.entity.Recently;
import com.xuexiang.xui.XUI;

public class MyApplication extends Application {

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this); //初始化UI框架
        XUI.debug(true);  //开启UI框架调试日志
        myApplication = this;
        GoodsDao goodsDao=new GoodsDao(this);
        goodsDao.delete();
        goodsDao.addGoods();//初始化数据库
        RecentlyDao recentlyDao=new RecentlyDao(this);
        recentlyDao.delete();
    }

//    public static MyApplication getInstance(){
//        return myApplication;
//    }
}