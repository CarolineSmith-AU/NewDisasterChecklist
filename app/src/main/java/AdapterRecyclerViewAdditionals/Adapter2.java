package AdapterRecyclerViewAdditionals;

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

import AddYourOwnItems.AddYourOwnActivity;
import cls0097.auburn.edu.newdisasterchecklist.ListItem2;
import cls0097.auburn.edu.newdisasterchecklist.R;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder> {

    private Context c;
    private List<ListItem2> listItems;
    //private String[] countEditTextValue;
    public static List<String> checkedItems = new ArrayList<>();
    private static SparseBooleanArray itemStateArray = new SparseBooleanArray();
    private static SparseBooleanArray countStateArray = new SparseBooleanArray();
    private static SparseBooleanArray descriptionStateArray = new SparseBooleanArray();

    public Adapter2(Context c, List<ListItem2> listItems) {
        this.c = c;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public Adapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_detail_basics, parent, false);
        return new Adapter2.ViewHolder(v/*, new CustomEditTextListener()*/);
    }

    @Override
    //Most integral part - binds View to data to RecyclerView
    public void onBindViewHolder(@NonNull final Adapter2.ViewHolder holder, int position) {
        ListItem2 item = listItems.get(position);
        holder.itemCheckBox.setText(item.getItem());
        holder.descriptionTextView.setText(item.getDescription());
        /* CustomEditTextListener customEditTextListener = new CustomEditTextListener();*/

        holder.countEditText.setHint(item.getCount());

        holder.bindDescriptionTextState(position);
        final int adapterPosition = holder.getAdapterPosition();

        if (!descriptionStateArray.get(adapterPosition, false) && holder.descriptionTextView.getText().toString().equals("INVISIBLE")) {
            holder.descriptionTextView.setVisibility(View.INVISIBLE);
        } else {
            holder.descriptionTextView.setVisibility(View.VISIBLE);
        }

        Log.d("Description", holder.descriptionTextView.getText().toString());

        holder.bindEditTextState(position);
        holder.bindCheckboxState(position);


        Log.d("itemCheckBox.getText()", holder.itemCheckBox.getText().toString());


        holder.itemCheckBox.setOnClickListener(new View.OnClickListener() {
            CheckBox itemCheckBox;
            TextView descriptionTextView;
            EditText countEditText;

            @Override
            public void onClick(View v) {
                itemCheckBox = v.findViewById(R.id.itemCheckbox);
                descriptionTextView = v.findViewById(R.id.descriptionTextView);
                countEditText = v.findViewById(R.id.countEditText);

                if (!itemStateArray.get(adapterPosition, false) && itemCheckBox.isChecked() && !holder.countEditText.getHint().toString().equals("INVISIBLE")
                        && countStateArray.get(adapterPosition, true)) {
                    itemStateArray.put(adapterPosition, true);

                    countStateArray.put(adapterPosition, true);

                    //holder.countEditText.setText();

                    checkedItems.add(holder.countEditText.getText().toString() + " " + holder.itemCheckBox.getText().toString());


                    holder.countEditText.setVisibility(View.VISIBLE);

                    /*Log.d("checkedItems.size()", checkedItems.size() + "");
                        for (int i = 0; i < checkedItems.size(); i++) {
                            Log.d("checkedItems STRING", checkedItems.get(i) + "\n");
                        }
                    Log.d("countEditText.getHint()", holder.countEditText.getHint().toString());*/
                    Log.d(holder.itemCheckBox.getText().toString() + " checked State", holder.itemCheckBox.isChecked() + "");
                } else if (!itemStateArray.get(adapterPosition, false) && itemCheckBox.isChecked() &&
                        countStateArray.get(adapterPosition, true) && holder.countEditText.getHint().toString().equals("INVISIBLE")) {
                    itemStateArray.put(adapterPosition, true);

                    countStateArray.put(adapterPosition, false);

                    checkedItems.add(holder.itemCheckBox.getText().toString());
                    /*Log.d("checkedItems.size()", checkedItems.size() + "");
                    Log.d("countEditText.getHint()", holder.countEditText.getHint().toString());*/
                    Log.d(holder.itemCheckBox.getText().toString() + " checked State", holder.itemCheckBox.isChecked() + "");
                } else if (itemStateArray.get(adapterPosition, false) && !itemCheckBox.isChecked() && !holder.countEditText.getText().toString().equals("INVISIBLE")
                        && countStateArray.get(adapterPosition, false)) {
                    itemStateArray.put(adapterPosition, false);
                    countStateArray.put(adapterPosition, false);

                    holder.countEditText.setVisibility(View.INVISIBLE);
                    /*Log.d("checkedItems.size()", checkedItems.size() + "");
                    Log.d("countEditText.getHint()", holder.countEditText.getHint().toString());*/
                    Log.d(holder.itemCheckBox.getText().toString() + " checked State", holder.itemCheckBox.isChecked() + "");
                } else if (itemStateArray.get(adapterPosition, false) && !itemCheckBox.isChecked() && holder.countEditText.getText().toString().equals("INVISIBLE")
                        && countStateArray.get(adapterPosition, true)) {
                    itemStateArray.put(adapterPosition, false);
                    countStateArray.put(adapterPosition, false);
                    holder.countEditText.setVisibility(View.INVISIBLE);
                    Log.d("checkedItems.size()", checkedItems.size() + "");
                    Log.d("countEditText.getHint()", holder.countEditText.getHint().toString());
                    Log.d(holder.itemCheckBox.getText().toString() + " checked State", holder.itemCheckBox.isChecked() + "");
                } else if (itemStateArray.get(adapterPosition, false) && !holder.itemCheckBox.isChecked()) {
                    itemStateArray.put(adapterPosition, false);
                    Log.d("checkedItems.size()", checkedItems.size() + "");
                }
                getCheckedItems();
                for (int i = 0; i < checkedItems.size(); i++) {
                    Log.d("checkedItems STRING", getCheckedItems().get(i) + "\n");
                }
                //Log.d("checkedItems.size()", checkedItems.size() + "");
                Log.d("totalCheckedItems", getCheckedItems().size() + "");

            }
        });
    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox itemCheckBox;
        TextView descriptionTextView;
        EditText countEditText;

        public ViewHolder(View itemView/*, CustomEditTextListener customEditTextListener*/) {
            super(itemView);

            itemCheckBox = itemView.findViewById(R.id.itemCheckbox);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            countEditText = itemView.findViewById(R.id.countEditText);
            /* countEditText.addTextChangedListener(customEditTextListener);*/
        }

        void bindCheckboxState(int position) {
            if (!itemStateArray.get(position, false)) {
                itemCheckBox.setChecked(false);
            } else {
                itemCheckBox.setChecked(true);
            }
        }

        void bindEditTextState(int position) {
            if (!countStateArray.get(position, false)) {
                countEditText.setVisibility(View.INVISIBLE);
            } else {
                countEditText.setVisibility(View.VISIBLE);
            }
        }

        void bindDescriptionTextState(int position) {
            if (!descriptionStateArray.get(position, false)) {
                descriptionTextView.setVisibility(View.INVISIBLE);

            } else {
                descriptionTextView.setVisibility(View.VISIBLE);
            }
        }

       /* public View getItemView() {
            return itemView;
        }*/

    }

/*    private class CustomEditTextListener implements TextWatcher {

        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //CharSequence s is what user enters in countEditText
            countEditTextValue[position] = s.toString();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }*/


    //allows you to access this from a separate class
    static public List<String> getCheckedItems() {
        if (checkedItems.size() != 0) {
            for (int i = 0; i < checkedItems.size() - 1; i++) {
                if (checkedItems.get(i + 1).equals(checkedItems.get(i))) {
                    checkedItems.remove(checkedItems.get(i + 1));
                }
            }
            AddYourOwnActivity a = new AddYourOwnActivity();
            a.getTotalCheckedItems().addAll(checkedItems);
        }
        return checkedItems;
    }

    /*static public void resetList(Adapter1WithEditTextListener.ViewHolder h, View view) {
        int position = h.getAdapterPosition();
        view = h.getItemView();
        h = new Adapter1WithEditTextListener.ViewHolder(view);
        if (!itemStateArray.get(position, false) && h.itemCheckBox.isChecked()) {
            h.itemCheckBox.setChecked(false);
        }
    }*/

}

