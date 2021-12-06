package com.example.voizbook.controller.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.voizbook.R;
import com.example.voizbook.controller.Adapter.BookSelectionAdapter;
import com.example.voizbook.controller.Adapter.PhotoViewpager2Adapter;
import com.example.voizbook.controller.animation.ZoomOutPageTransformer;
import com.example.voizbook.model.BookSelection;
import com.example.voizbook.model.PhoTo;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;


public class MainActivity extends AppCompatActivity {
    Toolbar toolBar;
    ViewFlipper sliderImg;
    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    private List<PhoTo> listPhoto;
    private Handler handler = new Handler();

    // set slide
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(viewPager2.getCurrentItem() == listPhoto.size() -1 ){
                viewPager2.setCurrentItem(0);
            }else
            {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapping();
        initView();
    }

    // ánh xạ
    public void Mapping() {
        toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        viewPager2 = (ViewPager2) findViewById(R.id.viewPage2);
        circleIndicator3 = (CircleIndicator3) findViewById(R.id.circleIndicator3);
    }

    public void initView( ){


        // slide img
        PhotoAdapter();

        // book selection
        viewBookSelection();


    }

    private void PhotoAdapter() {
        listPhoto = getListPhoto();
        PhotoViewpager2Adapter adapter  = new PhotoViewpager2Adapter(listPhoto);
        viewPager2.setAdapter(adapter);

        //connect view page with circelindicator
        circleIndicator3.setViewPager(viewPager2);


        //auto run
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });

        // animation
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
    }
    private List<PhoTo> getListPhoto(){
        List<PhoTo> list = new ArrayList<>();
        list.add(new PhoTo(R.drawable.img_slider_muonkiep));
        list.add(new PhoTo(R.drawable.img_slider_nhagiak));
        list.add(new PhoTo(R.drawable.img_slider_tuyet));
        list.add(new PhoTo(R.drawable.img_slider_muonkiep));
        return list;
    }


    //  pause slider when  out app
    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable,3000);
    }






    public void viewBookSelection(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerCategory);
        recyclerView.setHasFixedSize(true);

        //set horizontal
        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        //recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<BookSelection> bookArr = new ArrayList<>();
        bookArr.add(new BookSelection(R.drawable.category,"Trinh thám"));
        bookArr.add(new BookSelection(R.drawable.category,"Trẻ em"));
        bookArr.add(new BookSelection(R.drawable.category,"Pháp luật"));
        bookArr.add(new BookSelection(R.drawable.category,"Y tế & sức khỏe"));
        bookArr.add(new BookSelection(R.drawable.category,"Tiểu thuyết"));

        BookSelectionAdapter bookSelectionAdapter = new BookSelectionAdapter(bookArr,getApplicationContext());
        recyclerView.setAdapter(bookSelectionAdapter);


        // RecyclerView phia duoi
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.recyclerCategory2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setAdapter(bookSelectionAdapter);
    }
}