package com.akiniyalocts.superfan.ui.imp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.akiniyalocts.superfan.R;
import com.akiniyalocts.superfan.SuperfanApp;
import com.akiniyalocts.superfan.base.HasComponent;
import com.akiniyalocts.superfan.dagger.main.DaggerMainComponent;
import com.akiniyalocts.superfan.dagger.main.MainComponent;
import com.akiniyalocts.superfan.dagger.main.MainModule;
import com.akiniyalocts.superfan.databinding.MainBinding;
import com.akiniyalocts.superfan.model.AppleProduct;
import com.akiniyalocts.superfan.model.Product;
import com.akiniyalocts.superfan.model.ProductTechSpecs;
import com.akiniyalocts.superfan.ui.CustomTabUtil;
import com.akiniyalocts.superfan.ui.MainPresenter;
import com.akiniyalocts.superfan.ui.MainView;
import com.squareup.picasso.Picasso;

import java.util.List;

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

    @Inject
    Picasso picasso;

    private MainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.injectComponent();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainPresenter.onCreate(savedInstanceState, getIntent());
    }


    @Override
    public void init() {
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this,
                R.array.types, R.layout.type_item);

        typeAdapter.setDropDownViewResource(R.layout.type_item);
        binding.spinnerType.setAdapter(typeAdapter);
        binding.spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(typeAdapter.getItem(position) != null) {
                    mainPresenter.onNewTypeSelected(typeAdapter.getItem(position).toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.sys76Image.setOnClickListener(v -> CustomTabUtil.startTabsIntent(this, mainPresenter.selectedProduct().getProductUrl()));

        binding.appleContainer.setOnClickListener(v -> {

        });

    }

    @Override
    public void showProductNames(List<String> productNames) {
        ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(this, R.layout.model_item, productNames);
        modelAdapter.setDropDownViewResource(R.layout.model_item);
        binding.spinnerModel76.setAdapter(modelAdapter);
        binding.spinnerModel76.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mainPresenter.onProductSelected(productNames.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void showAppleNames(List<String> appleNames) {
        ArrayAdapter<String> modelAdapterMac = new ArrayAdapter<>(this, R.layout.model_item, appleNames);
        modelAdapterMac.setDropDownViewResource(R.layout.model_item);
        binding.spinnerModelMac.setAdapter(modelAdapterMac);
        binding.spinnerModelMac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mainPresenter.onAppleProductSelected(appleNames.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void showCurrentProduct(Product product) {
        picasso.load(product.getThumbUrl())
                .fit()
                .centerInside()
                .into(binding.sys76Image);
        binding.setProduct(product);

    }

    @Override
    public void showCurrentAppleProduct(AppleProduct appleProduct) {
        picasso.load(appleProduct.getImageUrl())
                .fit()
                .centerInside()
                .into(binding.appleImage);
        binding.setTier(appleProduct.getLow());
    }

    @Override
    public void showSpecs(List<ProductTechSpecs> specs) {
        com.akiniyalocts.superfan.ui.imp.MainBinding.setSpecs(binding.productCpu, specs);
    }

    @Override
    public void toggleLoading(boolean loading) {
        binding.setLoading(loading);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
    }
}
