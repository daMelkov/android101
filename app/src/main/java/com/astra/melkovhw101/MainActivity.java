package com.astra.melkovhw101;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] values = prepareContent();
        //BaseAdapter listContentAdapter = createAdapter(values);
        //List<Map<String, String>> content = new ArrayList<>();

        List<Map<String, String>> content = createContent(values);
        SimpleAdapter listContentAdapter = createAdapter(content);

        ListView list = findViewById(R.id.list);
        list.setAdapter(listContentAdapter);
    }

    private SimpleAdapter createAdapter(List<Map<String, String>> content) {
        String[] from = new String[]{"header","content"};
        int[] to = new int[]{R.id.header, R.id.content};
        return new SimpleAdapter(this, content, R.layout.list_item, from, to);
    }

    private List<Map<String, String>> createContent(String[] values) {
        List<Map<String, String>> result = new ArrayList<>();

        for(int i = 0; i < values.length; i++) {
            Map<String, String> item = new HashMap<>();

            String headerValue = values[i];
            i++;
            String contentValue = "";
            while(values[i].length() > 100) {
                contentValue += values[i];
                if(i < values.length - 1) {
                    i++;
                } else {
                    i++;
                    break;
                }
            }
            i--;

            item.put("header", headerValue);
            item.put("content", contentValue);
            result.add(item);
        }

        return result;
    }

//    @NonNull
//    private BaseAdapter createAdapter(String[] values) {
//        return new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
//    }

    @NonNull
    private String[] prepareContent() {
        return getString(R.string.large_text).split("\n\n");
    }
}