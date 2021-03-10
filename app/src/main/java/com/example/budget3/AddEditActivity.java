package com.example.budget3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.budget3.databinding.ActivityAddEditBinding;
import com.example.budget3.model.Operation;

public class AddEditActivity extends AppCompatActivity {

    private Operation operation;

    public static final String Operation_ID = "OperationId";
    public static final String Operation_NAME = "OperationName";
    public static final String Operation_DESCRIPTION = "OperationDescription";
    private ActivityAddEditBinding activityAddEditBinding;
    private AddEditActivityClickHandlers addEditActivityClickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        operation = new Operation();
        activityAddEditBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_add_edit);
        activityAddEditBinding.setOperation(operation);
        addEditActivityClickHandlers = new AddEditActivityClickHandlers(this);
        activityAddEditBinding.setClickHandlers(addEditActivityClickHandlers);

        Intent intent = getIntent();

        if (intent.hasExtra(Operation_ID)) {
            setTitle("Edit operation");

            operation.setOperationName(intent.getStringExtra(Operation_NAME));
            operation.setOperationDescription(intent.getStringExtra(Operation_DESCRIPTION));
        } else {
            setTitle("Add operation");
        }

    }

    public class AddEditActivityClickHandlers {

        Context context;

        public AddEditActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onOkButtonClicked(View view) {

            if (operation.getOperationName() == null) {

                Toast.makeText(context, "Please input the mame", Toast.LENGTH_SHORT).show();

            } else {

                Intent intent = new Intent();
                intent.putExtra(Operation_NAME, operation.getOperationName());
                intent.putExtra(Operation_DESCRIPTION, operation.getOperationDescription());
                setResult(RESULT_OK, intent);
                finish();

            }

        }
    }
}
