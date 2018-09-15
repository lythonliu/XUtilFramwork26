package com.example.administrator.xutilframwork26;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.administrator.xutilframwork26.annotion.ContentView;
import com.example.administrator.xutilframwork26.annotion.OnClick;
import com.example.administrator.xutilframwork26.annotion.OnLongClick;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity{
    public static  final String TAG="dongnao";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ListView listView=null;
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
    }
    public  void itemClick(AdapterView<?> parent, View view, int position, long id)
    {

    }
    @OnClick(R.id.text2)
    public void onClick()
    {
        Toast.makeText(this,"单击",Toast.LENGTH_SHORT).show();
    }
    @OnLongClick({R.id.text})
    public  boolean click(View view)
    {
        Toast.makeText(this,"长按",Toast.LENGTH_SHORT).show();
        return true;
    }
}
