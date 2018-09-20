package AdapterRecyclerViewBasics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cls0097.auburn.edu.newdisasterchecklist.R;

public class BasicsRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView1;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    private String[] items, descriptions, counts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basics_recycler_view);

        items = getResources().getStringArray(R.array.items);
        descriptions = getResources().getStringArray(R.array.descriptions);
        counts = getResources().getStringArray(R.array.counts);

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        for (int i = 0; i < items.length; i++) {
            listItems.add(new ListItem(items[i], descriptions[i], counts[i]));
            Log.d("countEditText HINT", counts[i] + "");
        }

        adapter = new Adapter1(this, listItems);
        recyclerView1.setAdapter(adapter);
    }
}
