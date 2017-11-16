package com.sty.character.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etName;
    private RadioGroup rgGroup;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();
    }

    private void initViews() {
        etName = (EditText) findViewById(R.id.et_name);
        rgGroup = (RadioGroup) findViewById(R.id.rg_group);
        btnCalculate = (Button) findViewById(R.id.btn_calculate);
    }

    private void setListeners(){
        btnCalculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_calculate:
                calculate();
                break;
            default:
                break;
        }
    }

    private void calculate(){
        //1.获取用户名
        String name = etName.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(getApplicationContext(), "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        //2.判断选中性别
        int checkedRadioButtonId = rgGroup.getCheckedRadioButtonId();
        //3.判断一下具体选中的确定性别
        int sex = 0; //默认值为0   1：男   2：女  3：其他
        switch(checkedRadioButtonId){
            case R.id.rb_male: //男
                sex = 1;
                break;
            case R.id.rb_female: //女
                sex = 2;
                break;
            case R.id.rb_other: //其他
                sex = 3;
                break;
            default:
                break;
        }
        //4.判断性别
        if(sex == 0){
            Toast.makeText(getApplicationContext(), "请选择性别", Toast.LENGTH_SHORT).show();
            return;
        }
        //5.跳转到ResultActivity界面
        Intent intent = new Intent(this, ResultActivity.class);
        //5.1把name和sex传递到结果页面，底层是HashMap实现
        intent.putExtra("name", name);
        intent.putExtra("sex", sex);
        //5.2开启activity
        startActivity(intent);
    }
}
