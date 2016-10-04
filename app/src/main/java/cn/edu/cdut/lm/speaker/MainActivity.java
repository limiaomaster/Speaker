package cn.edu.cdut.lm.speaker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity  {


    private List<Mp3Info> list ;
    private RecyclerView recyclerView;
    private int DATEBASE_VERSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = MediaUtil.getMyMp3List(MainActivity.this);


        //list = MediaUtil.getMp3List(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_singleMusic);
        //1
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //2
        SingleSongRVAdapter singleSongRVAdapter = new SingleSongRVAdapter(this,list);
        recyclerView.setAdapter(singleSongRVAdapter);
        //3
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

}
