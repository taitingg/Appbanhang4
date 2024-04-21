package com.example.appbanhang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appbanhang.Adapter.SanPhamAdapter;
import com.example.appbanhang.Model.SanPhamMoi;
import com.example.appbanhang.R;
import com.example.appbanhang.Retrofit.ApiBanHang;
import com.example.appbanhang.Retrofit.RetrofitClient;
import com.example.appbanhang.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    EditText edt_timKiem;
    SanPhamAdapter sanPhamAdapter;
    List<SanPhamMoi> list;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        actionBar();
    }

    private void initView() {
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        list = new ArrayList<>();
        toolbar = findViewById(R.id.toolbar_timKiem);
        recyclerView = findViewById(R.id.recycleView_timKiem);
        edt_timKiem = findViewById(R.id.edt_timKiem);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        edt_timKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    list.clear();
                    sanPhamAdapter = new SanPhamAdapter(getApplicationContext(),list);
                    recyclerView.setAdapter(sanPhamAdapter);
                }else{
                    getDataSearch(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void getDataSearch(String temp) {
        list.clear();
        compositeDisposable.add(apiBanHang.search(temp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        SanPhamMoiModel->{
                            if (SanPhamMoiModel.isSuccess()){
                                list = SanPhamMoiModel.getResult();
                                sanPhamAdapter  = new SanPhamAdapter(getApplicationContext(),list);
                                recyclerView.setAdapter(sanPhamAdapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));
    }


    private void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}