package AddYourOwnItems;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import AdapterListViewResult.ResultListViewActivity;
import AdapterRecyclerViewBasics.Adapter1WithEditTextListener;
import cls0097.auburn.edu.newdisasterchecklist.ListItem2;
import cls0097.auburn.edu.newdisasterchecklist.R;

//TODO: When "View Items In Kit" button clicked, totalItemsInKit.size() = 0.

public class AddYourOwnActivity extends AppCompatActivity {

    EditText enterItemEditText;
    EditText enterNumberItemsEditText;
    Button addToKitButton;
    Button viewItemsInKitButton;
    static List<String> totalItemsInKit = new ArrayList<>();
    Adapter1WithEditTextListener a = new Adapter1WithEditTextListener(this, new List<ListItem2>() {


        @Override
        public int size() {
            return totalItemsInKit.size();
        }

        @Override
        public boolean isEmpty() {
            boolean isEmpty = false;
            if (totalItemsInKit.size() == 0) {
                isEmpty = true;
            }
            return isEmpty;
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
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_your_own);

        enterItemEditText = findViewById(R.id.enterItemEditText);
        enterNumberItemsEditText = findViewById(R.id.enterNumberItemsEditText);
        addToKitButton = findViewById(R.id.addToKitButton);
        viewItemsInKitButton = findViewById(R.id.viewKitButton);

        Log.d("totalItemsInKit size", totalItemsInKit.size() + "");

        addToKitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enterItemEditText.getText().equals(null)) {
                    if (!enterNumberItemsEditText.getText().equals(null)) {
                        totalItemsInKit.add(enterNumberItemsEditText.getText() + " " + enterItemEditText.getText().toString());
                        String currItem;
                        for (int i = 0; i < totalItemsInKit.size(); i++) {
                            currItem = totalItemsInKit.get(i);
                            for (int j = 1; j < totalItemsInKit.size(); j++) {
                                if (totalItemsInKit.get(j).equals(currItem) && i != j) {
                                    totalItemsInKit.remove(j);
                                    j--;
                                }
                            }
                        }
                        enterItemEditText.setText("");
                        enterNumberItemsEditText.setText("");
                        Log.d("totalItemsInKit.size()", totalItemsInKit.size() + "");
                    } else {
                        totalItemsInKit.add(enterItemEditText.getText().toString());
                        String currItem;
                        for (int i = 0; i < totalItemsInKit.size(); i++) {
                            currItem = totalItemsInKit.get(i);
                            for (int j = 1; j < totalItemsInKit.size(); j++) {
                                if (totalItemsInKit.get(j).equals(currItem) && i != j) {
                                    totalItemsInKit.remove(j);
                                    j--;
                                }
                            }
                        }
                        enterItemEditText.setText("");
                        enterNumberItemsEditText.setText("");
                    }
                }
                Log.d("totalItemsInKit.size()", totalItemsInKit.size() + "");
            }
        });

        viewItemsInKitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toResultListViewActivity = new Intent(AddYourOwnActivity.this, ResultListViewActivity.class);
                //toResultListViewActivity.putExtra("cls0097.auburn.edu.KIT_RESULT", );
                startActivity(toResultListViewActivity);
                for (int i = 0; i < totalItemsInKit.size(); i++) {
                    if (totalItemsInKit.size() != 0) {
                        Log.d("totalItemsInKit ITEMS", totalItemsInKit.get(i) + "");
                    }
                }
            }
        });

    }

    public List<String> getTotalCheckedItems() {
        return totalItemsInKit;
    }
}
