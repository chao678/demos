package com.example.testapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.testapp.R;
import com.example.testapp.util.CameraUtils2;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PicActivity extends AppCompatActivity {

    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
    }

//    private void showPopueWindow() {
//        View popView = View.inflate(this,R.layout.popupwindow_camera_need,null);
//        Button bt_album = (Button) popView.findViewById(R.id.btn_pop_album);
//        Button bt_camera = (Button) popView.findViewById(R.id.btn_pop_camera);
//        Button bt_cancle = (Button) popView.findViewById(R.id.btn_pop_cancel);
//        //获取屏幕宽高
//        int weight = getResources().getDisplayMetrics().widthPixels;
//        int height = getResources().getDisplayMetrics().heightPixels*1/3;
//        final PopupWindow popupWindow = new PopupWindow(popView,weight,height);
////        popupWindow.setAnimationStyle(R.style.anim_popup_dir);
//        popupWindow.setFocusable(true);
//        //点击外部popueWindow消失
//        popupWindow.setOutsideTouchable(true);
//
//        bt_album.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(i, RESULT_LOAD_IMAGE);
//                popupWindow.dismiss();
//            }
//        });
//        bt_camera.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                takeCamera(RESULT_CAMERA_IMAGE);
//                popupWindow.dismiss();
//            }
//        });
//        bt_cancle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//
//            }
//        });
//        //popupWindow消失屏幕变为不透明
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override public void onDismiss() {
//                WindowManager.LayoutParams lp = getWindow().getAttributes();
//                lp.alpha = 1.0f; getWindow().setAttributes(lp);
//            }
//        });
//        //popupWindow出现屏幕变为半透明
//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.alpha = 0.5f; getWindow().setAttributes(lp);
//        popupWindow.showAtLocation(popView, Gravity.BOTTOM,0,50);
//    }

    private void takeCamera(int num) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            photoFile = createImageFile();
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
            }
        }
        startActivityForResult(takePictureIntent, num);//跳转界面传回拍照所得数据
    }

    private File createImageFile() {
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File image = null;
        try {
            image = File.createTempFile(
                    generateFileName(),  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public static String generateFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        return imageFileName;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


}
