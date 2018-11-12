package mobillium.mobillium;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mobillium.mobillium.adapter.ExpandableListAdapter;
import mobillium.mobillium.adapter.ProductAdapter;
import mobillium.mobillium.adapter.VP_SlideAdepter;
import mobillium.mobillium.model.Product;

public class MainActivity extends AppCompatActivity {

    ImageButton ibColorRed,ibColorGold,ibColorOrange,ibColorWhite;

    private TextView tvXS,tvS,tvM,tvL;

    //List Expland
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    RecyclerView recyclerView,recylerviewHorizonial;

    ViewPager vpSlide;
    VP_SlideAdepter vp_slideAdepter;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private int[] images={
            R.drawable.product,
            R.drawable.elbise1,
            R.drawable.elbise2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        vpSlide=(ViewPager)findViewById(R.id.vpSlide);
        vp_slideAdepter=new VP_SlideAdepter(MainActivity.this,images);
        vpSlide.setAdapter(vp_slideAdepter);
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);
        dotscount = vp_slideAdepter.getCount();
        dots = new ImageView[dotscount];
        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        vpSlide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        ibColorRed = (ImageButton) findViewById(R.id.ibColorRed);
        ibColorGold = (ImageButton) findViewById(R.id.ibColorGold);
        ibColorOrange = (ImageButton) findViewById(R.id.ibColorOrange);
        ibColorWhite = (ImageButton) findViewById(R.id.ibColorWhite);
        tvXS=(TextView)findViewById(R.id.tvXS);
        tvS=(TextView)findViewById(R.id.tvS);
        tvM=(TextView)findViewById(R.id.tvM);
        tvL=(TextView)findViewById(R.id.tvL);

        ibColorRed.setImageResource(R.drawable.unselect_button_red);
        ibColorGold.setImageResource(R.drawable.unselect_button_gold);
        ibColorOrange.setImageResource(R.drawable.unselect_button_orange);
        ibColorWhite.setImageResource(R.drawable.unselect_button_white);

        ibColorRed.setOnClickListener(new setOnClickList());
        ibColorGold.setOnClickListener(new setOnClickList());
        ibColorOrange.setOnClickListener(new setOnClickList());
        ibColorWhite.setOnClickListener(new setOnClickList());

        tvXS.setOnClickListener(new setOnClickBodySize());
        tvS.setOnClickListener(new setOnClickBodySize());
        tvM.setOnClickListener(new setOnClickBodySize());
        tvL.setOnClickListener(new setOnClickBodySize());


        intoExplandList();

        recyclerView = (RecyclerView) findViewById(R.id.recylerview);
        recylerviewHorizonial= (RecyclerView) findViewById(R.id.recylerviewHorizonial);

        ProductAdapter productAdapter = new ProductAdapter(this, Product.getData());
        recyclerView.setAdapter(productAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recylerviewHorizonial.setLayoutManager(horizontalLayoutManager);
        recylerviewHorizonial.setAdapter(productAdapter);


    }

    private void intoExplandList() {
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);



        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Seçildi",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Ürün Bilgisi");
        listDataHeader.add("Ürün Açıklaması");
        listDataHeader.add("İade ve Değişim");
        listDataHeader.add("Beden Tablosu");


        // Adding child data
        List<String> urunBilgisi = new ArrayList<String>();
        urunBilgisi.add("Ürün Bilgisi Girilecek");

        List<String> urunAciklama = new ArrayList<String>();
        urunAciklama.add("Ürün Açıklaması Girilecek");

        List<String> iadeDegisim = new ArrayList<String>();
        iadeDegisim.add("İade ve Değişim Girilecek");

        List<String> bedenTablo = new ArrayList<String>();
        bedenTablo.add("Beden Tablosu Girilecek");


        listDataChild.put(listDataHeader.get(0), urunBilgisi); // Header, Child data
        listDataChild.put(listDataHeader.get(1), urunAciklama);
        listDataChild.put(listDataHeader.get(2), iadeDegisim);
        listDataChild.put(listDataHeader.get(3), bedenTablo);
    }

    private class setOnClickList implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int id=view.getId();

            ibColorRed.setImageResource(R.drawable.unselect_button_red);
            ibColorGold.setImageResource(R.drawable.unselect_button_gold);
            ibColorOrange.setImageResource(R.drawable.unselect_button_orange);
            ibColorWhite.setImageResource(R.drawable.unselect_button_white);

            if(id==R.id.ibColorRed){
                ibColorRed.setImageResource(R.drawable.select_button_red);
            }
            else if(id==R.id.ibColorGold){
                ibColorGold.setImageResource(R.drawable.select_button_gold);
            }
            else if(id==R.id.ibColorOrange){
                ibColorOrange.setImageResource(R.drawable.select_button_orange);
            }
            else if(id==R.id.ibColorWhite){
                ibColorWhite.setImageResource(R.drawable.select_button_white);
            }
        }
    }

    private class setOnClickBodySize implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int id=view.getId();
            tvXS.setTextColor(getResources().getColor(R.color.colorBlack));
            tvXS.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            tvS.setTextColor(getResources().getColor(R.color.colorBlack));
            tvS.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            tvM.setTextColor(getResources().getColor(R.color.colorBlack));
            tvM.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            tvL.setTextColor(getResources().getColor(R.color.colorBlack));
            tvL.setBackgroundColor(getResources().getColor(R.color.colorWhite));

            if(id==R.id.tvXS){
                tvXS.setTextColor(getResources().getColor(R.color.colorWhite));
                tvXS.setBackgroundColor(getResources().getColor(R.color.colorBlack));
            }
            else if(id==R.id.tvS){
                tvS.setTextColor(getResources().getColor(R.color.colorWhite));
                tvS.setBackgroundColor(getResources().getColor(R.color.colorBlack));
            }
            else if(id==R.id.tvM){
                tvM.setTextColor(getResources().getColor(R.color.colorWhite));
                tvM.setBackgroundColor(getResources().getColor(R.color.colorBlack));
            }
            else if(id==R.id.tvL){
                tvL.setTextColor(getResources().getColor(R.color.colorWhite));
                tvL.setBackgroundColor(getResources().getColor(R.color.colorBlack));
            }
        }
    }
}
