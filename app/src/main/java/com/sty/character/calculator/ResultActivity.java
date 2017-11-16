package com.sty.character.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by Shi Tianyi on 2017/11/16/0016.
 */

public class ResultActivity extends AppCompatActivity {
    private TextView tvName;
    private TextView tvSex;
    private TextView tvResult;

    private String name;
    private int sex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        loadParameters();
        initViews();
    }

    private void loadParameters(){
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        sex = intent.getIntExtra("sex", 0);
    }

    private void initViews() {
        tvName = (TextView) findViewById(R.id.tv_name);
        tvSex = (TextView) findViewById(R.id.tv_sex);
        tvResult = (TextView) findViewById(R.id.tv_result);

        tvName.setText(name); //显示姓名
        byte[] bytes = null;
        try {
            switch (sex) { //显示性别
                case 1:
                    tvSex.setText("男");
                    bytes = name.getBytes("gbk");
                    break;
                case 2:
                    tvSex.setText("女");
                    bytes = name.getBytes("utf-8");
                    break;
                case 3:
                default:
                    tvSex.setText("其他");
                    bytes = name.getBytes("iso-8859-1");
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        int score = calculateCharacter(bytes);
        if(score > 90){
            tvResult.setText("您的人品非常好，您家的祖坟都冒青烟了");
        }else if(score > 70){
            tvResult.setText("有你这样的人品算是不错了...");
        }else if(score > 60){
            tvResult.setText("您的人品刚刚及格");
        }else{
            tvResult.setText("您的人品不及格");
        }
    }

    /**
     * 根据输入的姓名和性别来计算人品得分
     * @return
     */
    private int calculateCharacter(byte[] bytes){
        Log.i("Tag", Arrays.toString(bytes));

        int total = 0;
        for(byte b : bytes){
            int number = b & 0xff;
            total += number;
        }
        int score = Math.abs(total) % 100;
        return score;
    }
}
