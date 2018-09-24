package AdapterListViewResult;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import AddYourOwnItems.AddYourOwnActivity;
import cls0097.auburn.edu.newdisasterchecklist.R;

//TODO: Set preparednessSeekBar based on checkedItems1 (basic supplies)

public class ResultListViewActivity extends AppCompatActivity {

    ListView resultListView;
    TextView noItemsAddedTextView, preparednessTextView, basicsRedTextView, additionalsBlueTextView;
    List<String> totalItemsInKit;
    String[] items, items2;
    AddYourOwnActivity a = new AddYourOwnActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list_view);

        basicsRedTextView = findViewById(R.id.basicsRedTextView);
        additionalsBlueTextView = findViewById(R.id.additionalsBlueTextView);
        resultListView = findViewById(R.id.resultListView);
        preparednessTextView = findViewById(R.id.preparednessTextView);
        noItemsAddedTextView = findViewById(R.id.noItemsAddedTextView);
        totalItemsInKit = a.getTotalCheckedItems();
        items = getResources().getStringArray(R.array.items);
        items2 = getResources().getStringArray(R.array.items2);

        basicsRedTextView.setTextColor(Color.RED);
        additionalsBlueTextView.setTextColor(Color.BLUE);

        if (totalItemsInKit.size() == 0) {
            noItemsAddedTextView.setVisibility(View.VISIBLE);
            preparednessTextView.setText(R.string.you_have_zero);
            preparednessTextView.setTextColor(Color.RED);
        } else {
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

        }
        Adapter3 adapter3 = new Adapter3(this, totalItemsInKit, items, items2);
        resultListView.setAdapter(adapter3);

        if (totalItemsInKit.size() != 0) {
            String currItem;
            int numOfBasicSupplies = 0;
            for (int i = 0; i < items.length; i++) {
                currItem = items[i];

                for (int j = 0; j < totalItemsInKit.size(); j++) {
                    if (totalItemsInKit.get(j).contains(currItem)) {
                        numOfBasicSupplies++;
                    }

                }
            }
            if (numOfBasicSupplies == 0) {
                preparednessTextView.setText(R.string.you_have_zero);
                preparednessTextView.setTextColor(Color.RED);
            } else if (numOfBasicSupplies < items.length) {
                preparednessTextView.setText("You only have " + numOfBasicSupplies + "/" + items.length + " of the Basic Supplies Listed! You are UNDERPREPARED!");
                preparednessTextView.setTextColor(Color.RED);
            } else {
                preparednessTextView.setText("You have ALL of the Basic Supplies listed! You are FULLY PREPARED!");
                preparednessTextView.setTextColor(Color.GREEN);
            }
        }

    }
}
