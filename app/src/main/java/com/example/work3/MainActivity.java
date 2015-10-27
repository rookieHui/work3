package com.example.work3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private CheckBox cbMan,cbMen;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.et);
        cbMan = (CheckBox) findViewById(R.id.cbMan);
        cbMen = (CheckBox) findViewById(R.id.cbMen);
        tv = (TextView) findViewById(R.id.tv);
    }
    public void clc(View view){
        if (!et.getText().toString().equals("")){
            if (cbMan.isChecked()||cbMen.isChecked()) {
                Double weight = Double.parseDouble(et.getText().toString());
                StringBuffer sb = new StringBuffer();
                sb.append("-----评估结果-----\n");
                if(cbMan.isChecked()){
                    sb.append("男性标准身高");
                    double result = evaluateHeight(weight, "男");
                    sb.append((int) result + "厘米\n");
                }
                if (cbMen.isChecked()) {
                    sb.append("女性标准身高");
                    double result = evaluateHeight(weight,"女");
                    sb.append((int) result + "厘米\n");
                }
                tv.setText(sb.toString());
            }else{
                showMessage("请选择性别");
            }
        }else{
            showMessage("请输入体重");
        }
    }

    private void showMessage(String message){
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("系统信息");
        dialog.setMessage(message);
        dialog.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
    private double evaluateHeight(double weight,String sex) {
        double height;
        if (sex == "男") {
            height = 170 - (62 - weight) / 0.6;
        }else {
            height = 159 - (52 - weight) / 0.5;
        }
        return height;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, Menu.NONE, "推出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
