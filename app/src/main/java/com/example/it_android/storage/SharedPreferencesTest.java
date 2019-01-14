package com.example.it_android.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SharedPreferencesTest extends AppCompatActivity {

    @BindView(R.id.edit_userName)
    EditText username;
    @BindView(R.id.edit_content)
    EditText ed_content;
    private String username2, content2;


    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    public void okClick(View view) {//提交数据

        //获取sharedPreferences对象*(纸)
        sharedPreferences = getSharedPreferences("cc", Context.MODE_PRIVATE);
        //获取editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器（笔）
        //存储键值对

/*
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        String string64 = new String(Base64.encode(baos.toByteArray(), 0));
        //ObjectOutputStream将对象转化成流，base64将流转成字符串，然后再保存。
        editor.putString(key, string64);*/

       /* editor.putInt("age", 24);
        editor.putBoolean("isMarried", false);
        editor.putLong("height", 175L);
        editor.putFloat("weight", 60f);*/


        username2 = username.getText().toString();
        content2 = ed_content.getText().toString();
        editor.putString("name",username2);
        editor.putString("content",content2);

        //提交
        editor.commit();//提交修改

        String str = "success";
        ToastUtil.show(SharedPreferencesTest.this, str);

    }

    public void recoveryClick(View view) {//恢复数据

        SharedPreferences read = getSharedPreferences("cc", MODE_PRIVATE);
        //步骤2：获取文件中的值

        String value = read.getString("content", "");
        ed_content.setText(value);

    }



 /*   public static Object getBean(Context context, String key) {
        Object obj = null;
        try {
            String base64 = getSharedPreferences(context).getString(key, "");
            if (base64.equals("")) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }*/

}
