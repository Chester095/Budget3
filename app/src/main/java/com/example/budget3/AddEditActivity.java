package com.example.budget3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.budget3.databinding.ActivityAddEditBinding;
import com.example.budget3.model.Operation;

import java.sql.SQLOutput;

public class AddEditActivity extends AppCompatActivity {

    private Operation operation;
    // константы для полей operation чтобы мы могли иметь доступ к этим константам ото всюду из нашего приложения
    public static final String OPERATION_ID = "OperationId";
    public static final String OPERATION_NAME = "OperationName";
    public static final String OPERATION_DESCRIPTION = "OperationDescription";
    public static final String OPERATION_AMOUNT = "OperationAmount";
    private ActivityAddEditBinding activityAddEditBinding;
    private AddEditActivityClickHandlers addEditActivityClickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("SOUT - onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        operation = new Operation();
        activityAddEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_edit);
        //связываем наш Operation класс используя setOperation
        activityAddEditBinding.setOperation(operation);
        //переменная ClickHandlers
        addEditActivityClickHandlers = new AddEditActivityClickHandlers(this);
        //свяжем её с нашей разметкой
        activityAddEditBinding.setClickHandlers(addEditActivityClickHandlers);

        Intent intent = getIntent();
        //проверям был ли он создан как результат кликания на RecycleView он должен иметь OPERATION_ID
        if (intent.hasExtra(OPERATION_ID)) {
            System.out.println("SOUT -  OPERATION_ID = " + intent.getIntExtra(OPERATION_ID, 0));
            //устанавливаем заголовок
            setTitle("Edit operation");
            //получаем значения объекта
            System.out.println("SOUT -  OPERATION_NAME = " + intent.getStringExtra(OPERATION_NAME));
            operation.setOperationName(intent.getStringExtra(OPERATION_NAME));
            System.out.println("SOUT -  OPERATION_DESCRIPTION = " + intent.getStringExtra(OPERATION_DESCRIPTION));
            operation.setOperationDescription(intent.getStringExtra(OPERATION_DESCRIPTION));
            System.out.println("SOUT -  OPERATION_AMOUNT = " + intent.getIntExtra(OPERATION_AMOUNT, 0));
            operation.setOperationAmount(intent.getIntExtra(OPERATION_AMOUNT, 0));
        } else {
            setTitle("Add operation");
        }

    }

    //создаём Иннер класс. В этой активити можно добавлять или редактировать существующий
    public class AddEditActivityClickHandlers {
        //создаём контекст
        Context context;

        public AddEditActivityClickHandlers(Context context) {
            System.out.println("SOUT -  AddEditActivityClickHandlers  = " + context);

            this.context = context;
        }

        //метод нажатия на кнопку Ок
        public void onOkButtonClicked(View view) {

            if (operation.getOperationName() == null) {
                Toast.makeText(context, "Please input the name", Toast.LENGTH_SHORT).show();
            } else if (operation.getOperationAmount() == 0) {
                Toast.makeText(context, "Введите стоимость", Toast.LENGTH_LONG).show();
            } else {
                //создаём новый Интент
                Intent intent = new Intent();
                //помещаем Экстра в него
                System.out.println("SOUT -  AddEditActivityClickHandlers OPERATION_NAME = " + operation.getOperationName());
                intent.putExtra(OPERATION_NAME, operation.getOperationName());
                System.out.println("SOUT -  AddEditActivityClickHandlers OPERATION_DESCRIPTION = " + operation.getOperationDescription());
                intent.putExtra(OPERATION_DESCRIPTION, operation.getOperationDescription());
                System.out.println("SOUT -  AddEditActivityClickHandlers OPERATION_AMOUNT = " + operation.getOperationAmount());
                intent.putExtra(OPERATION_AMOUNT, operation.getOperationAmount());
                setResult(RESULT_OK, intent);
                finish();

            }

        }
    }
}
