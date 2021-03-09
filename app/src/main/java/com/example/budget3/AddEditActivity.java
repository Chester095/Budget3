package com.example.budget3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.budget3.databinding.ActivityAddEditBinding;
import com.example.budget3.model.ListItem;

public class AddEditActivity extends AppCompatActivity {

    private ListItem listItem;

    public static final String LISTITEM_ID = "listItemId";
    public static final String LISTITEM_NAME = "listItemName";
    public static final String LISTITEM_DESCRIPTION = "listItemDescription";
    private ActivityAddEditBinding activityAddEditBinding;
    private AddEditActivityClickHandlers addEditActivityClickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        listItem = new ListItem();
        activityAddEditBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_add_edit);
        activityAddEditBinding.setListItem(listItem);
        addEditActivityClickHandlers = new AddEditActivityClickHandlers(this);
        activityAddEditBinding.setClickHandlers(addEditActivityClickHandlers);

        Intent intent = getIntent();

        if (intent.hasExtra(LISTITEM_ID)) {
            setTitle("Edit listItem");

            listItem.setListItemName(intent.getStringExtra(LISTITEM_NAME));
            listItem.setListItemDescription(intent.getStringExtra(LISTITEM_DESCRIPTION));
        } else {
            setTitle("Add listItem");
        }

    }

    public class AddEditActivityClickHandlers {

        Context context;

        public AddEditActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onOkButtonClicked(View view) {

            if (listItem.getListItemName() == null) {

                Toast.makeText(context, "Please input the mame", Toast.LENGTH_SHORT).show();

            } else {

                Intent intent = new Intent();
                intent.putExtra(LISTITEM_NAME, listItem.getListItemName());
                intent.putExtra(LISTITEM_DESCRIPTION, listItem.getListItemDescription());
                setResult(RESULT_OK, intent);
                finish();

            }

        }
    }
}
