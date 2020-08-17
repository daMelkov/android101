package com.astra.melkovhw101;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Toolbar */
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /* ListView */
        List<Map<String, String>> content = prepareContent();
        ListView list = findViewById(R.id.list);
        list.setAdapter(createAdapter(content));

        /* SwipeRefreshLayout */
        final SwipeRefreshLayout swipe = findViewById(R.id.main_swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipe.setRefreshing(false);
            }
        });
    }

    private BaseAdapter createAdapter(List<Map<String, String>> content) {
        String[] from = new String[]{"header","content"};
        int[] to = new int[]{R.id.header, R.id.content};
        return new SimpleAdapter(this, content, R.layout.list_item, from, to);
    }

    @NonNull
    private List<Map<String, String>> prepareContent() {
        List<Map<String, String>> result = new ArrayList<>();

        String[] values = getString(R.string.large_text).split("\n\n");
        for(int i = 0; i < values.length; i++) {
            Map<String, String> item = new HashMap<>();

            String headerValue = values[i].trim();
            i++;
            StringBuilder contentValue = new StringBuilder();
            while(values[i].length() > 100) {
                contentValue.append(values[i].trim());

                i++;
                if(i >= values.length - 1) {
                    break;
                }
            }
            i--;

            item.put("header", headerValue);
            item.put("content", contentValue.toString());
            result.add(item);
        }

        return result;
    }
}