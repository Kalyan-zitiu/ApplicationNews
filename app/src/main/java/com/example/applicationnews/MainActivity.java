package com.example.applicationnews;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.applicationnews.ActivityControl.CollectList;
import com.example.applicationnews.ActivityControl.HistoryListActivity;
import com.example.applicationnews.ActivityControl.LoginActivity;
import com.example.applicationnews.ActivityControl.SearchActivity;
import com.example.applicationnews.ActivityControl.TabNewsFragment;
import com.example.applicationnews.ActivityControl.UpdatePasswordActivity;
import com.example.applicationnews.enity.TitleE;
import com.example.applicationnews.enity.UserE;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    // private String[] titles={"娱乐","军事","教育","文化","将康","财经","体育","汽车","科技"};
    private List<TitleE> titles = new ArrayList<>();
    private TabLayout tab_layout;
    private ViewPager2 viewPager;

    private NavigationView nav_view;

    private TextView tv_username;

    private TextView tv_nickname;

    private ImageView btn_open_drawerLayout;

    private DrawerLayout drawer_layout;

    private EditText search_bar;

    private ImageView btn_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化title数据
        titles.add(new TitleE("推荐","top"));
        titles.add(new TitleE("国内","guonei"));
        titles.add(new TitleE("国际","guoji"));
        titles.add(new TitleE("娱乐","yule"));
        titles.add(new TitleE("体育","tiyu"));
        titles.add(new TitleE("军事","junshi"));
        titles.add(new TitleE("科技","keji"));
        titles.add(new TitleE("财经","caijing"));
        titles.add(new TitleE("游戏","upixo"));
        titles.add(new TitleE("汽车","qiche"));
        titles.add(new TitleE("健康","jiankang"));


        //初始化控件
        tab_layout =findViewById(R.id.tab_layout);
        viewPager =findViewById(R.id.viewPager);
        nav_view =findViewById(R.id.nav_view);
        tv_username=nav_view.getHeaderView(0).findViewById(R.id.tv_username);
        tv_nickname=nav_view.getHeaderView(0).findViewById(R.id.tv_nickname);
        //初始化搜索栏控件
        search_bar = findViewById(R.id.search_bar);
        //初始化放大镜控件
        btn_search =findViewById(R.id.btn_search);
        //打开抽屉
        drawer_layout = findViewById(R.id.drawer_layout);
        btn_open_drawerLayout=findViewById(R.id.btn_open_drawerLayout);

        //打开抽屉点击事件
        btn_open_drawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.open();
            }
        });

        //设置放大镜点击事件
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = search_bar.getText().toString();
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        //nav_view点击事件
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.nav_history){
                    //跳转到历史记录
                    Intent intent = new Intent(MainActivity.this, HistoryListActivity.class);
                    startActivity(intent);
                }
                if (menuItem.getItemId()==R.id.nav_password){
                    //判断是否登录
                    UserE userE = UserE.getsUserE();
                    if (null !=userE) {
                        startActivity(new Intent(MainActivity.this,UpdatePasswordActivity.class));
                        //跳转到修改密码
                    }else {
                        Toast.makeText(MainActivity.this, "请登录", Toast.LENGTH_SHORT).show();
                    }
                }
                if (menuItem.getItemId() == R.id.nav_exit) {
                    UserE userE = UserE.getsUserE();
                    if (null!=userE) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("温馨提示")
                                .setMessage("确认要退出登录吗？")
                                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                        UserE.setsUserE(null);
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .show();
                    }else {
                        Toast.makeText(MainActivity.this, "请登录", Toast.LENGTH_SHORT).show();
                    }
                }
                if(menuItem.getItemId() == R.id.nav_collect){
                    Intent intent = new Intent(MainActivity.this, CollectList.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        //设置ViewPager的adapter
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                String tittle = titles.get(position).getPy_title();
                TabNewsFragment tabNewsFragment = TabNewsFragment.newInstance(tittle);

                return tabNewsFragment;
            }

            @Override
            public int getItemCount() {
                return titles.size();
            }
        });

        //设置tablayout的点击形事件
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //设置viewPager选中当前页面
                viewPager.setCurrentItem(tab.getPosition(),false);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //关联tab_layout和Viewpager
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tab_layout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int i) {
                tab.setText(titles.get(i).getTitle());
            }
        });

        tabLayoutMediator.attach();

    }

    @Override
    protected void onResume() {
        super.onResume();
        UserE userE = UserE.getsUserE();
        if (null != userE){
            tv_username.setText(userE.getUsername());
            tv_nickname.setText(userE.getNickname());
        }else {
            tv_username.setText("请登录");
            tv_nickname.setText(" ");
            //登录点击事件
            tv_username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        }

    }
}