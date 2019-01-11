package com.example.videodemo;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.vod.upload.VODSVideoUploadClient;
import com.aliyun.svideo.sdk.external.struct.common.CropKey;
import com.aliyun.svideo.sdk.external.struct.common.VideoDisplayMode;
import com.aliyun.svideo.sdk.external.struct.recorder.CameraType;
import com.aliyun.svideo.sdk.external.struct.recorder.FlashType;
import com.aliyun.svideo.sdk.external.struct.snap.AliyunSnapVideoParam;
import com.example.videodemo.util.AES;
import com.example.videodemo.util.PermissionUtils;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    private static final int REQUEST_RECORD = 2001;
    private final int REQUEST_RECORD_CODE = 0;

    private VODSVideoUploadClient vodsVideoUploadClient;
    private String videoPath;
    /**
     * 权限申请
     */
    String[] permission = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int PERMISSION_REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
        boolean checkResult = PermissionUtils.checkPermissionsGroup(this, permission);
        if (!checkResult) {
            PermissionUtils.requestPermissions(this, permission, PERMISSION_REQUEST_CODE);
        }

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, AliyunVideoRecorder.class));
                startActivityForResult(new Intent(MainActivity.this, AliyunVideoRecorder.class), REQUEST_RECORD);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pai();
            }
        });

        TextView text1 = findViewById(R.id.text1);
        text1.setText(Html.fromHtml(getString(R.string.tip_xinghao_nation)));

    }

    private void pai() {

        AliyunSnapVideoParam recordParam = new AliyunSnapVideoParam.Builder()
//                .setResolutionMode(resolutionMode)
//                .setRatioMode(ratioMode)
                .setRecordMode(AliyunSnapVideoParam.RECORD_MODE_AUTO)
//                .setFilterList(effectDirs)
                .setBeautyLevel(80)
                .setBeautyStatus(true)
                .setCameraType(CameraType.FRONT)
                .setFlashType(FlashType.ON)
                .setNeedClip(true)
//                .setMaxDuration(max)
//                .setMinDuration(min)
//                .setVideoQuality(videoQuality)
//                .setGop(gop)

                /**
                 * 裁剪参数
                 */
                .setFrameRate(25)
                .setCropMode(VideoDisplayMode.FILL)
                //显示分类SORT_MODE_VIDEO视频;SORT_MODE_PHOTO图片;SORT_MODE_MERGE图片和视频
                .setSortMode(AliyunSnapVideoParam.SORT_MODE_VIDEO)
                .build();
        AliyunVideoRecorder.startRecordForResult(this,REQUEST_RECORD,recordParam);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode ==  REQUEST_RECORD){
            if(resultCode == Activity.RESULT_OK && data!= null){
                int type = data.getIntExtra(AliyunVideoRecorder.RESULT_TYPE,0);
                if(type ==  AliyunVideoRecorder.RESULT_TYPE_CROP){
                    String path = data.getStringExtra(CropKey.RESULT_KEY_CROP_PATH);
                    Toast.makeText(this,"文件路径为 "+ path + " 时长为 " + data.getLongExtra(CropKey.RESULT_KEY_DURATION,0),Toast.LENGTH_SHORT).show();
                }else if(type ==  AliyunVideoRecorder.RESULT_TYPE_RECORD){
                    videoPath = data.getStringExtra(AliyunVideoRecorder.OUTPUT_PATH);
                    Toast.makeText(this,"文件路径为 "+ data.getStringExtra(AliyunVideoRecorder.OUTPUT_PATH),
                            Toast.LENGTH_SHORT).show();
                }
            }else if(resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(this,"用户取消录制",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean isAllGranted = true;

            // 判断是否所有的权限都已经授予了
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }

            if (isAllGranted) {
                // 如果所有的权限都授予了
                //Toast.makeText(this, "get All Permisison", Toast.LENGTH_SHORT).show();
            } else {
                // 弹出对话框告诉用户需要权限的原因, 并引导用户去应用权限管理中手动打开权限按钮
                showPermissionDialog();
            }
        }
    }

    //系统授权设置的弹框
    AlertDialog openAppDetDialog = null;
    private void showPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.app_name) + "需要访问 \"相册\"、\"摄像头\" 和 \"外部存储器\",否则会影响绝大部分功能使用, 请到 \"应用信息 -> 权限\" 中设置！");
        builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent);
            }
        });
        builder.setCancelable(false);
        builder.setNegativeButton("暂不设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish();
            }
        });
        if (null == openAppDetDialog) {
            openAppDetDialog = builder.create();
        }
        if (null != openAppDetDialog && !openAppDetDialog.isShowing()) {
            openAppDetDialog.show();
        }
    }

    private void test() {
        String s1 = "2qb+Zp/LD0fZ6Im6lD8S6w1LmrFXA4xL3z7YSq7aIDK3nMZv4ctOX/tAvx0yLxUv5PBf9sEHqt1rAhalm1RdTQ==";
        //解码
        String s2 = AES.getInstance().decrypt(s1);
        //编码
        String s3 = AES.getInstance().encrypt(s2.getBytes());
        Log.i("123", "s2= "+s2);
        Log.i("123", "s3= "+s3);
        if (s1.equals(s3)) {
            Log.i("123", "true");
        }
    }
}
