package AdapterRecyclerViewAdditionals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import AddYourOwnItems.AddYourOwnActivity;
import cls0097.auburn.edu.newdisasterchecklist.ListItem2;
import cls0097.auburn.edu.newdisasterchecklist.R;

public class AdditionalsRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView2;
    private RecyclerView.Adapter adapter;
    private Button nextButton2;
    private List<ListItem2> listItems;
    private String[] items2, descriptions2, counts2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additionals_recycler_view);

        items2 = getResources().getStringArray(R.array.items2);
        descriptions2 = getResources().getStringArray(R.array.descriptions2);

        counts2 = getResources().getStringArray(R.array.counts2);

        nextButton2 = findViewById(R.id.nextButton2);

        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        for (int i = 0; i < items2.length; i++) {
            listItems.add(new ListItem2(items2[i], descriptions2[i], counts2[i]));
/*
            Log.d("countEditText HINT", counts2[i] + "");
*/
        }

        adapter = new Adapter2(this, listItems);
        recyclerView2.setAdapter(adapter);

        nextButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAddYourOwnActivity = new Intent(AdditionalsRecyclerViewActivity.this, AddYourOwnActivity.class);
                startActivity(toAddYourOwnActivity);
            }
        });

    }
}
