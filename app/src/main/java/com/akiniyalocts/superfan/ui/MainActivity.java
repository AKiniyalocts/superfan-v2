package com.akiniyalocts.superfan.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.akiniyalocts.superfan.R;
import com.akiniyalocts.superfan.databinding.MainBinding;

public class MainActivity extends AppCompatActivity {

    private MainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


    }
}
