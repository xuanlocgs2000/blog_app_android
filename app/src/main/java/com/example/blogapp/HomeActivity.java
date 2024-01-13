package com.example.blogapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.blogapp.Fragments.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> activityResultLauncher= registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    int result = o.getResultCode();
                    Intent data = o.getData();
                    if (result==RESULT_OK){
            Uri imgUri = data.getData();
            Intent i = new Intent(HomeActivity.this, AddPostActivity.class);
            i.setData(imgUri);
            startActivity(i);
        }
                }
            }
    );
    private FragmentManager fragmentManager;
    private FloatingActionButton flb;
    private static  final  int GALLERY_ADD_POST=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameHomeContainer, new HomeFragment()).commit();
        initView();
    }

    private void initView() {
        flb = (FloatingActionButton) findViewById(R.id.fltButton);
        flb.setOnClickListener(v->{
            Intent i  = new Intent(Intent.ACTION_PICK);
            i.setType("image/*");
//            startActivityForResult(i,GALLERY_ADD_POST);
            activityResultLauncher.launch(i);
        });

    }
//    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode,resultCode,data);
//        if (requestCode==GALLERY_ADD_POST&&resultCode==RESULT_OK){
//            Uri imgUri = data.getData();
//            Intent i = new Intent(HomeActivity.this, AddPostActivity.class);
//            i.setData(imgUri);
//            startActivity(i);
//        }
//    }
}