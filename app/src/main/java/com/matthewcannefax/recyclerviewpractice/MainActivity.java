package com.matthewcannefax.recyclerviewpractice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mWordList = getWordList();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wordListSize = mWordList.size()+1;

                //Add a new word to the wordList
                mWordList.addLast(" + Word " + wordListSize);

                //Notify the adapter, that the data has change
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);

                //scroll to the bottom
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });

        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new WordListAdapter(this, mWordList);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private LinkedList<String> getWordList(){
        LinkedList<String> list = new LinkedList<>();
        for(int i = 1; i<=10;i++){
            list.addLast("Word " + i);
        }

        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.reset) {
            mWordList = getWordList();
            mAdapter = new WordListAdapter(this, mWordList);
            mRecyclerView.swapAdapter(mAdapter, true);
            int i = 0;
            return false;
        }

        return super.onOptionsItemSelected(item);
    }
}
