package com.wangjing.easyphotopickersample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wangjing.easyphotopicker.EasyPhotoActivity;

public class MainActivity extends AppCompatActivity {


    Button btn_jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_jump = findViewById(R.id.btn_jump);

        btn_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,EasyPhotoActivity.class));
            }
        });
    }
}
