package AdapterListViewResult;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import AddYourOwnItems.AddYourOwnActivity;
import cls0097.auburn.edu.newdisasterchecklist.R;

public class Adapter3 extends BaseAdapter {

    LayoutInflater mInflater;
    Context c;
    AddYourOwnActivity a = new AddYourOwnActivity();
    List<String> stringKitItems = a.getTotalCheckedItems();
    String[] items, items2;

    public Adapter3(Context c, List<String> stringKitItems, String[] items, String[] items2) {

        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.c = c;
        this.stringKitItems = stringKitItems;
        this.items = items;
        this.items2 = items2;
    }

    @Override
    public int getCount() {
        return stringKitItems.size();
    }

    @Override
    public Object getItem(int position) {
        return stringKitItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<String> removeDuplicates() {
        //List<String> finalList = null;
        if (stringKitItems.size() != 0) {
            String currItem;
            for (int i = 0; i < stringKitItems.size(); i++) {
                currItem = stringKitItems.get(i);
                for (int j = 1; j < stringKitItems.size(); j++) {
                    if (stringKitItems.get(j).equals(currItem) && i != j) {
                        stringKitItems.remove(j);
                        j--;
                    }
                }
            }
        }
        return stringKitItems;
    }

    public void setTextColor(String[] items, String[] items2, List<String> list, TextView numberTextView, TextView itemTextView, int position) {
        if (list.size() != 0) {
            String currItem;
            for (int i = 0; i < items.length; i++) {
                currItem = items[i];

                for (int j = position; j < list.size(); j++) {
                    if (list.get(position).contains(currItem)) {
                        numberTextView.setTextColor(Color.RED);
                        itemTextView.setTextColor(Color.RED);
                    }

                }
            }

            for (int i = 0; i < items2.length; i++) {
                currItem = items2[i];
                if (list.get(position).contains(currItem)) {
                    numberTextView.setTextColor(Color.BLUE);
                    itemTextView.setTextColor(Color.BLUE);
                }
            }
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.listview_detail_result, null);

        TextView numberTextView = v.findViewById(R.id.numberTextView);
        TextView itemTextView3 = v.findViewById(R.id.itemTextView3);
        removeDuplicates();
        Log.d("stringKI.size()", stringKitItems.size() + "");

        String number = (position + 1) + "). ";
        String item = stringKitItems.get(position);

        numberTextView.setText(number);
        itemTextView3.setText(item);
        setTextColor(items, items2, removeDuplicates(), numberTextView, itemTextView3, position);

        return v;
    }
}
