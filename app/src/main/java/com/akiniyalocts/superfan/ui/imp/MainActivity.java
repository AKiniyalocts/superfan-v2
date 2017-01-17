package com.akiniyalocts.superfan.ui.imp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;

import com.akiniyalocts.superfan.R;
import com.akiniyalocts.superfan.SuperfanApp;
import com.akiniyalocts.superfan.base.HasComponent;
import com.akiniyalocts.superfan.dagger.main.DaggerMainComponent;
import com.akiniyalocts.superfan.dagger.main.MainComponent;
import com.akiniyalocts.superfan.dagger.main.MainModule;
import com.akiniyalocts.superfan.databinding.MainBinding;
import com.akiniyalocts.superfan.ui.MainPresenter;
import com.akiniyalocts.superfan.ui.MainView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements HasComponent<MainComponent>, MainView {

    @Override
    public MainComponent getComponent() {
        return DaggerMainComponent.builder()
                .applicationComponent(((SuperfanApp)getApplication()).getComponent())
                .mainModule(new MainModule(this))
                .build();
    }

    @Override
    public void injectComponent() {
        getComponent().inject(this);
    }


    @Inject
    MainPresenter mainPresenter;

    private MainBinding binding;

    private SeekBar.OnSeekBarChangeListener seekListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.injectComponent();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainPresenter.onCreate(savedInstanceState, getIntent());
    }


    @Override
    public void init() {

        seekListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        binding.seek.setOnSeekBarChangeListener(seekListener);

        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this,
                R.array.types, R.layout.type_item);


        typeAdapter.setDropDownViewResource(R.layout.type_item);
        binding.spinnerType.setAdapter(typeAdapter);

        ArrayAdapter<CharSequence> modelAdapter = ArrayAdapter.createFromResource(this, R.array.test_models, R.layout.model_item);
        modelAdapter.setDropDownViewResource(R.layout.model_item);
        binding.spinnerModel76.setAdapter(modelAdapter);

        ArrayAdapter<CharSequence> modelAdapterMac = ArrayAdapter.createFromResource(this, R.array.test_models_mac, R.layout.model_item);
        modelAdapter.setDropDownViewResource(R.layout.model_item);
        binding.spinnerModelMac.setAdapter(modelAdapterMac);

    }

    @Override
    public void toggleLoading(boolean loading) {
        binding.setLoading(loading);
    }



}
