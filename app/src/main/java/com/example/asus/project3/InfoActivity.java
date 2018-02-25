package com.example.asus.project3;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {
    private boolean tag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Info p = (Info) getIntent().getSerializableExtra("Info");
        ImageView imageView = (ImageView) findViewById(R.id.bgimage);
        String temp = p.getBackground();
        Resources resources = getResources();
        int id = resources.getIdentifier(temp, "drawable", getPackageName());
        imageView.setImageResource(id);

        /* 返回上一页面 */
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(p.getName());
        TextView money = (TextView) findViewById(R.id.information);
        money.setText(p.getMoney());
        final TextView info1 = (TextView) findViewById(R.id.info1);
        info1.setText(p.getInfo1());
        TextView info2 = (TextView) findViewById(R.id.info2);
        info2.setText(p.getInfo2());

        String[] operation1 = new String[]{"更多产品信息"};
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, R.layout.more, operation1);
        ListView listView1 = (ListView) findViewById(R.id.more);
        listView1.setAdapter(arrayAdapter1);

        String[] operation2 = new String[]{"一键下单", "分享商品", "不感兴趣", "查看更多产品促销信息"};
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, R.layout.more, operation2);
        ListView listView2 = (ListView) findViewById(R.id .listview);
        listView2.setAdapter(arrayAdapter2);

        /* 星星的切换 */
        final ImageView star = (ImageView) findViewById(R.id.star);
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tag) {
                    star.setImageResource(R.drawable.full_star);
                    tag = true;
                } else {
                    star.setImageResource(R.drawable.empty_star);
                    tag = false;
                }
            }
        });

        final char scycle = p.getcycle();
        final String sname = p.getName();
        final String smoney = p.getMoney();

        /* 点击加入购物车 */
        final ImageView buy = (ImageView) findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InfoActivity.this, "商品已加到购物车", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InfoActivity.this, MainActivity.class);
                intent.putExtra("cycle", scycle);
                intent.putExtra("name", sname);
                intent.putExtra("money", smoney);
                setResult(1, intent);
            }
        });
    }
}
