package com.example.it_android.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FileInputStreamTest extends AppCompatActivity {

    @BindView(R.id.edit_userName)
    EditText username;
    @BindView(R.id.edit_content)
    EditText ed_content;
    private String username2, content2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    public void okClick(View view) {//存储数据


        username2 = username.getText().toString();
        content2 = ed_content.getText().toString();

        write(username2+content2);
        String str = "success";
        ToastUtil.show(FileInputStreamTest.this, str);

    }

    public void recoveryClick(View view) {//从文件读取数据

        read();
        String str = "success";
        ToastUtil.show(FileInputStreamTest.this, str);

    }


    public void write(String msg) {//文件中保存此字符串
        // 步骤1：获取输入值
        if (msg == null) return;
        try {
            // 步骤2:创建一个FileOutputStream对象,MODE_APPEND追加模式
            FileOutputStream fos = openFileOutput("message.txt",
                    MODE_APPEND);
            // 步骤3：将获取过来的值放入文件
            fos.write(msg.getBytes());
            // 步骤4：关闭数据流
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String read() {//从文件读取数据

        try {
            FileInputStream inStream = this.openFileInput("message.txt");//保存的文件名为“data”
            byte[] buffer = new byte[1024];
            int hasRead = 0;
            StringBuilder sb = new StringBuilder();
            while ((hasRead = inStream.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, hasRead));
            }

            inStream.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



/*
    private void saveFileData() {
        BufferedWriter writer = null;
        try {
            FileOutputStream out = openFileOutput("data", MODE_PRIVATE);//保存的文件名为“data”
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write("this is a message");//文件中保存此字符串
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void getFileData() {
        BufferedReader reader = null;
        try {
            FileInputStream fileInputStream = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(fileInputStream));

            String line = "";
            StringBuilder result  = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            Log.d("Test", "result data is " + result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

*/

}
