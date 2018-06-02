package com.example.administrator.soft1614080902224;
import java.io.FileOutputStream;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
public class book extends Activity {
    private ImageView imageView;
    private Button bu;
   private Bitmap bitmap;

   Handler handler=new Handler(){
       public void handleMessage(Message msg) {
                        if(msg.what==111){
                               imageView.setImageBitmap(bitmap);
                            }
                    }
    };
    public static final String FILENAME = "setting.set";
    private Context context=this;
    @Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        bu = (Button) this.findViewById(R.id.classs);
        imageView=(ImageView) findViewById(R.id.imageView);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(t).start();
                String content = bu.getText().toString();
                try {
                    // 打开文件获取输出流，文件不存在则自动创建
                    FileOutputStream fos = openFileOutput(FILENAME,
                            Context.MODE_PRIVATE);
                    fos.write(content.getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }
    Thread t=new Thread(){
        public void run() {
            //下载图片的路径
            String iPath="https://ps.ssl.qhimg.com/sdmt/187_135_100/t01435c1cd8cd798666.jpg";
            try {
                //对资源链接
                URL url=new URL(iPath);
                //打开输入流
                InputStream inputStream=url.openStream();
                //对网上资源进行下载转换位图图片
                bitmap=BitmapFactory.decodeStream(inputStream);
                handler.sendEmptyMessage(111);
                inputStream.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    };
}