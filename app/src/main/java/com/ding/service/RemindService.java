package com.ding.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;

import com.ding.activity.MainActivity;
import com.ding.healthmenu.R;

import java.util.Calendar;

import receiver.MyReceiver;

public class RemindService extends Service {
    private static final int NOTIFICATION_FLAG = 1;
    public RemindService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //将线程接口立刻送到线程队列中
        handler.post(update_thread);

    }

    //使用handler时首先要创建一个handler
    Handler handler = new Handler();
    //要用handler来处理多线程可以使用runnable接口，这里先定义该接口
    //线程中运行该接口的run函数
    Runnable update_thread = new Runnable() {
        public void run() {
            //线程每次执行时输出"UpdateThread..."文字,且自动换行
            //textview的append功能和Qt中的append类似，不会覆盖前面
            //的内容，只是Qt中的append默认是自动换行模式
            Calendar cal = Calendar.getInstance();// 当前日期
            int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时
            int minute = cal.get(Calendar.MINUTE);// 获取分钟
            int second = cal.get(Calendar.SECOND);// 获取秒钟
            if (hour == 10) {
                Log.e("tag", "213456789");
            }

            if (minute == 59) {
                Log.e("tag", "21");
            }
            if (second == 10) {
                Log.e("tag", "21");
                Notification.Builder builder = new Notification.Builder(RemindService.this);
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
                PendingIntent pendingIntent = PendingIntent.getActivity(RemindService.this, 0, mIntent, 0);
                builder.setContentIntent(pendingIntent);
                builder.setSmallIcon(R.mipmap.loading_0);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),     R.mipmap.ic_launcher));
                builder.setAutoCancel(true);
                builder.setContentTitle("普通通知");
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, builder.build());
            }
            //延时1s后又将线程加入到线程队列中
            handler.postDelayed(update_thread, 1000);

        }
    };

    @Override
    public void onStart(Intent intent, int startId) {
// 再次动态注册广播
        IntentFilter localIntentFilter = new IntentFilter("android.intent.action.USER_PRESENT");
        localIntentFilter.setPriority(Integer.MAX_VALUE);// 整形最大值
        MyReceiver searchReceiver = new MyReceiver();
        registerReceiver(searchReceiver, localIntentFilter);
        super.onStart(intent, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //flags = START_STICKY;
        return START_STICKY_COMPATIBILITY;
        //return super.onStartCommand(intent, flags, startId);
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        startService(new Intent(this, RemindService.class));
//    }
}
