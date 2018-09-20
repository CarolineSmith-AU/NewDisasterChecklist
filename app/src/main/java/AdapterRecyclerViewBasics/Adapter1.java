package AdapterRecyclerViewBasics;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cls0097.auburn.edu.newdisasterchecklist.R;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder> {

    private Context c;
    public List<ListItem> listItems;
    public List<String> checkedItems = new ArrayList<>();
    private SparseBooleanArray itemStateArray = new SparseBooleanArray();
    private SparseBooleanArray countStateArray = new SparseBooleanArray();

    public Adapter1(Context c, List<ListItem> listItems) {
        this.c = c;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public Adapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_detail_basics, parent, false);
        return new ViewHolder(v);
    }

    @Override
    //Most integral part - binds View to data to RecyclerView
    public void onBindViewHolder(@NonNull final Adapter1.ViewHolder holder, final int position) {

            ListItem item = listItems.get(position);
            holder.bindCheckboxState(position);
            holder.itemCheckBox.setText(item.getItem());
            holder.descriptionTextView.setText(item.getDescription());
            if (holder.descriptionTextView.getText().toString().equals("INVISIBLE")) {
                holder.descriptionTextView.setVisibility(View.INVISIBLE);
            }
            holder.countEditText.setHint(item.getCount());
            holder.bindEditTextState(position);

            Log.d("itemCheckBox.getText()", holder.itemCheckBox.getText().toString());

            holder.itemCheckBox.setOnClickListener(new View.OnClickListener() {
                CheckBox itemCheckBox;
                TextView descriptionTextView;
                EditText countEditText;

                @Override
                public void onClick(View v) {
                    int adapterPosition = holder.getAdapterPosition();
                    itemCheckBox = v.findViewById(R.id.itemCheckbox);
                    descriptionTextView = v.findViewById(R.id.descriptionTextView);
                    countEditText = v.findViewById(R.id.countEditText);

                    if (!itemStateArray.get(adapterPosition, false) && !holder.countEditText.getHint().toString().equals("INVISIBLE")
                            && !countStateArray.get(adapterPosition, false)) {
                        itemStateArray.put(adapterPosition, true);
                        countStateArray.put(adapterPosition, true);
                        checkedItems.add(holder.itemCheckBox.getText().toString());
                        holder.countEditText.setVisibility(View.VISIBLE);
                        Log.d("checkedItems.size()", checkedItems.size() + "");
                        Log.d("countEditText.getHint()", holder.countEditText.getHint().toString());
                        Log.d(holder.itemCheckBox.getText().toString() + " checked State", holder.itemCheckBox.isChecked() + "");
                    } else if (!itemStateArray.get(adapterPosition, false) &&
                            countStateArray.get(adapterPosition, false) && holder.countEditText.getHint().toString().equals(R.string.make_invisible)) {
                        itemStateArray.put(adapterPosition, true);
                        countStateArray.put(adapterPosition, false);
                        checkedItems.add(holder.itemCheckBox.getText().toString());
                        Log.d("checkedItems.size()", checkedItems.size() + "");
                        Log.d("countEditText.getHint()", holder.countEditText.getHint().toString());
                        Log.d(holder.itemCheckBox.getText().toString() + " checked State", holder.itemCheckBox.isChecked() + "");
                    } else {
                        itemStateArray.put(adapterPosition, false);
                        countStateArray.put(adapterPosition, false);
                        checkedItems.remove(holder.itemCheckBox.getText().toString());
                        holder.countEditText.setVisibility(View.INVISIBLE);
                        Log.d("checkedItems.size()", checkedItems.size() + "");
                        Log.d("countEditText.getHint()", holder.countEditText.getHint().toString());
                        Log.d(holder.itemCheckBox.getText().toString() + " checked State", holder.itemCheckBox.isChecked() + "");
                    }
                }
            });


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox itemCheckBox;
        TextView descriptionTextView;
        EditText countEditText;

        public ViewHolder(View itemView) {
            super(itemView);

            itemCheckBox = itemView.findViewById(R.id.itemCheckbox);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            countEditText = itemView.findViewById(R.id.countEditText);
        }

        void bindCheckboxState(int position) {
            if (!itemStateArray.get(position, false)) {
                itemCheckBox.setChecked(false);
            }
            else {
                itemCheckBox.setChecked(true);
            }
        }

        void bindEditTextState(int position) {
            if (!countStateArray.get(position, false)) {
                countEditText.setVisibility(View.INVISIBLE);
            }
            else {
                countEditText.setVisibility(View.VISIBLE);
            }
        }

    }
}
