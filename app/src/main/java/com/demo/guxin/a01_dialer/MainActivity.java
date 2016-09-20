package com.demo.guxin.a01_dialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private EditText et_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_number = (EditText) findViewById(R.id.et_number);
    }

    public void onClick(View view){
        String number = et_number.getText().toString();

        switch (view.getId()){
            // 数字键
            case R.id.btn_1:
                number = number + "1";
                et_number.setText(number);
                et_number.setSelection(number.length()); // 光标移动到最后一位
                break;
            case R.id.btn_2:
                number = number + "2";
                et_number.setText(number);
                et_number.setSelection(number.length()); // 光标移动到最后一位
                break;
            case R.id.btn_3:
                number = number + "3";
                et_number.setText(number);
                et_number.setSelection(number.length()); // 光标移动到最后一位
                break;
            case R.id.btn_4:
                number = number + "4";
                et_number.setText(number);
                et_number.setSelection(number.length()); // 光标移动到最后一位
                break;
            case R.id.btn_5:
                number = number + "5";
                et_number.setText(number);
                et_number.setSelection(number.length()); // 光标移动到最后一位
                break;
            case R.id.btn_6:
                number = number + "6";
                et_number.setText(number);
                et_number.setSelection(number.length()); // 光标移动到最后一位
                break;
            case R.id.btn_7:
                number = number + "7";
                et_number.setText(number);
                et_number.setSelection(number.length()); // 光标移动到最后一位
                break;
            case R.id.btn_8:
                number = number + "8";
                et_number.setText(number);
                et_number.setSelection(number.length()); // 光标移动到最后一位
                break;
            case R.id.btn_9:
                number = number + "9";
                et_number.setText(number);
                et_number.setSelection(number.length()); // 光标移动到最后一位
                break;
            case R.id.btn_star:

                break;
            case R.id.btn_0:
                number = number + "0";
                et_number.setText(number);
                et_number.setSelection(number.length()); // 光标移动到最后一位
                break;
            case R.id.btn_well:

                break;

            // 功能键
            case R.id.btn_clean_all:
                et_number.setText("");
                break;
            case R.id.btn_dial:
                dial();
                break;
            case R.id.btn_clean_one:
                if(!TextUtils.isEmpty(number)){
                    number = number.substring(0, number.length() - 1);
                    et_number.setText(number);
                    et_number.setSelection(number.length()); // 光标移动到最后一位
                }
                break;

            default:
                break;
        }
    }

    private void dial() {
        // 检查是否获得了权限（Android6.0运行时权限）
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            // 没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.CALL_PHONE)) {
                // 返回值：
//                          如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
//                          如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
//                          如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                // 弹窗需要解释为何需要该权限，再次请求授权
                Toast.makeText(MainActivity.this, "请授权！", Toast.LENGTH_LONG).show();

                // 帮跳转到该应用的设置界面，让用户手动授权
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }else{
                // 不需要解释为何需要该权限，直接请求授权
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        }else {
            // 已经获得授权，可以打电话
            callPhone();
        }
    }

    private void callPhone() {
        String number = et_number.getText().toString();
        if (TextUtils.isEmpty(number)) {
            // 提醒用户
            // 注意：在这个匿名内部类中如果用this则表示是View.OnClickListener类的对象，
            // 所以必须用MainActivity.this来指定上下文环境。
            Toast.makeText(MainActivity.this, "号码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            // 拨号：激活系统的拨号组件
            Intent intent = new Intent(); // 意图对象：动作 + 数据
            intent.setAction(Intent.ACTION_CALL); // 设置动作
            Uri data = Uri.parse("tel:" + number); // 设置数据
            intent.setData(data);
            startActivity(intent); // 激活Activity组件
        }
    }

    // 处理权限申请的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授权成功，继续打电话
                    callPhone();
                } else {
                    // 授权失败！
                    Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }

    }
}
