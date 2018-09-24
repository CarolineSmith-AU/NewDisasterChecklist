package AdapterRecyclerViewBasics;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import AdapterRecyclerViewAdditionals.AdditionalsActivity;
import cls0097.auburn.edu.newdisasterchecklist.ListItem2;
import cls0097.auburn.edu.newdisasterchecklist.R;

public class BasicsRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView1;
    private RecyclerView.Adapter adapter;
    private Button nextButton;
    private List<ListItem2> listItems;
    private String[] items, descriptions, counts;
    Adapter1WithEditTextListener a = new Adapter1WithEditTextListener(this, new List<ListItem2>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<ListItem2> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] a) {
            return null;
        }

        @Override
        public boolean add(ListItem2 listItem) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends ListItem2> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, @NonNull Collection<? extends ListItem2> c) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public ListItem2 get(int index) {
            return null;
        }

        @Override
        public ListItem2 set(int index, ListItem2 element) {
            return null;
        }

        @Override
        public void add(int index, ListItem2 element) {

        }

        @Override
        public ListItem2 remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator<ListItem2> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<ListItem2> listIterator(int index) {
            return null;
        }

        @NonNull
        @Override
        public List<ListItem2> subList(int fromIndex, int toIndex) {
            return null;
        }

        public List<String> getCheckedItems() {
            return Adapter1WithEditTextListener.getCheckedItems();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basics_recycler_view);

        items = getResources().getStringArray(R.array.items);
        descriptions = getResources().getStringArray(R.array.descriptions);

        counts = getResources().getStringArray(R.array.counts);

        nextButton = findViewById(R.id.nextButton);

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        for (int i = 0; i < items.length; i++) {
            listItems.add(new ListItem2(items[i], descriptions[i], counts[i]));
            Log.d("countEditText HINT", counts[i] + "");
        }

        adapter = new Adapter1WithEditTextListener(this, listItems);
        recyclerView1.setAdapter(adapter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toAdditionalsActivity = new Intent(BasicsRecyclerViewActivity.this, AdditionalsActivity.class);
                startActivity(toAdditionalsActivity);
            }
        });

        /*resetButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Adapter1WithEditTextListener.ViewHolder b = new Adapter1WithEditTextListener.ViewHolder(v);
                a.resetList(b, v);
            }
        });*/

    }
}
