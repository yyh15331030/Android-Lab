package com.example.asus.project3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;

public class MainActivity extends AppCompatActivity {
    private CommonAdapter commonAdapter;
    private List<Map<String, Object>> data = new ArrayList<>();
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> data2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 为每一项数据创建一个对象，并添加在list中*/
        final List<Info> Infos = new ArrayList<Info>() {{
            add(new Info("Enchated Forest", "¥ 5.00", "作者", "Johanna Basford", "enchatedforest"));
            add(new Info("Arla Milk", "¥ 59.00", "产地", "德国", "arla"));
            add(new Info("Devondale Milk", "¥ 79.00", "产地", "澳大利亚", "devondale"));
            add(new Info("Kindle Oasis", "¥ 2399.00", "版本", "8GB", "kindle"));
            add(new Info("waitrose 早餐麦片", "¥ 179.00", "重量", "2Kg", "waitrose"));
            add(new Info("Mcvitie's 饼干", "¥ 14.90", "产地", "英国", "mcvitie"));
            add(new Info("Ferrero Rocher", "¥ 132.59", "重量", "300g", "ferrero"));
            add(new Info("Maltesers", "¥ 141.43", "重量", "118g", "maltesers"));
            add(new Info("Lindt", "¥ 139.43", "重量", "249g", "lindt"));
            add(new Info("Borggreve", "¥ 28.90", "重量", "640g", "borggreve"));
        }};

        char[] cycle = new char[Infos.size()];
        for (int i = 0; i < Infos.size(); i++) {
            char x = Infos.get(i).getcycle();
            cycle[i] = x;
        }

        String[] name = new String[Infos.size()];
        for (int i = 0; i < Infos.size(); i++) {
            String x = Infos.get(i).getName();
            name[i] = x;
        }

        for (int i = 0; i < Infos.size(); i++) {
            Map<String, Object> temp = new LinkedHashMap<>();
            temp.put("cycle", cycle[i]);
            temp.put("name", name[i]);
            data.add(temp);
        }

        //simpleAdapter = new SimpleAdapter(this, data, R.layout.list1,
          //      new String[]{"cycle", "name"}, new int[]{R.id.cycle, R.id.name});
        commonAdapter = new CommonAdapter<Map<String, Object>>(this, R.layout.list1, data) {
            @Override
            public void convert(ViewHolder holder, Map<String, Object> s) {
                TextView cycle = holder.getView(R.id.cycle);
                cycle.setText(s.get("cycle").toString());
                TextView name = holder.getView(R.id.name);
                name.setText(s.get("name").toString());
            }
        };

        commonAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                Info temp = Infos.get(position);
                intent.putExtra("Info", temp);
                startActivityForResult(intent, 1);
            }

            @Override
            public void onLongClick(int position) {
                data.remove(position);
                commonAdapter.notifyDataSetChanged();
                String x = "移除第" + Integer.toString(position) + "个商品";
                Toast.makeText(MainActivity.this, x, Toast.LENGTH_SHORT).show();
            }
        });

        //final ListView listView = (ListView) findViewById(R.id.recycler_view);
        final RecyclerView listView = (RecyclerView) findViewById(R.id.recycler_view);
        listView.setLayoutManager(new LinearLayoutManager(this));
        //listView.setAdapter(simpleAdapter);
        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(commonAdapter);
        animationAdapter.setDuration(1000);
        listView.setAdapter(animationAdapter);
        listView.setItemAnimator(new OvershootInLeftAnimator());

        /* ListView单击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                Info temp = Infos.get(i);
                intent.putExtra("Info", temp);
                startActivityForResult(intent, 1);
            }
        });*/

         /* ListView长按事件
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Infos.remove(i);
                data.remove(i);
                simpleAdapter.notifyDataSetChanged();
                String x = "移除第" + Integer.toString(i) + "个商品";
                Toast.makeText(MainActivity.this, x, Toast.LENGTH_SHORT).show();
                return true;
            }
        }); */

        final ListView listView2 = (ListView) findViewById(R.id.shoppinglist);
        Map<String, Object> temp2 = new LinkedHashMap<>();
        temp2.put("cycle", '*');
        temp2.put("name", "购物车");
        temp2.put("money", "价格   ");
        data2.add(temp2);
        simpleAdapter = new SimpleAdapter(this, data2, R.layout.list2,
                new String[]{"cycle", "name", "money"}, new int[]{R.id.cycle, R.id.name, R.id.money});
        listView2.setAdapter(simpleAdapter);

        /* 单击购物车列表 */
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                Map<String, String> map = (Map<String, String>) adapterView.getItemAtPosition(i);
                for (int j = 0; j < Infos.size(); j++) {
                    if (Infos.get(j).getName().equals(map.get("name"))) {
                        intent.putExtra("Info", Infos.get(j));
                        startActivityForResult(intent, 1);
                    }
                }
            }
        });

        /* 长按购物车列表 */
        listView2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int j = i;
                Map<String, String> map = (Map<String, String>) adapterView.getItemAtPosition(i);
                if (!map.get("name").equals("购物车") ) {
                    final String m = "从购物车移除" + map.get("name") + "?";
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setTitle("移除商品")
                            .setMessage(m)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    data2.remove(j);
                                    simpleAdapter.notifyDataSetChanged();
                                }})
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {}})
                            .create();
                    alertDialog.show();
                }
                return true;
            }
        });

        final FloatingActionButton floatbutton = (FloatingActionButton) findViewById(R.id.floatbutton);
        floatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listView.getVisibility() == View.VISIBLE) {
                    listView.setVisibility(View.GONE);
                    listView2.setVisibility(View.VISIBLE);
                    floatbutton.setImageResource(R.drawable.mainpage);
                } else {
                    listView.setVisibility(View.VISIBLE);
                    listView2.setVisibility(View.GONE);
                    floatbutton.setImageResource(R.drawable.shoplist);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int resquestCode, int resultCode, Intent intentData) {
        super.onActivityResult(resquestCode, resultCode, intentData);
        if (resquestCode == 1) {
            if (resultCode == 1) {
                Map<String, Object> t = new LinkedHashMap<>();
                t.put("cycle", intentData.getSerializableExtra("cycle"));
                t.put("name", intentData.getSerializableExtra("name"));
                t.put("money", intentData.getSerializableExtra("money"));
                data2.add(t);
                simpleAdapter.notifyDataSetChanged();
            }
        }
    }
}


