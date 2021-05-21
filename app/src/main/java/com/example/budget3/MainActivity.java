package com.example.budget3;

import android.content.Intent;
import android.os.Bundle;

import com.example.budget3.databinding.ActivityMainBinding;
import com.example.budget3.model.Bill;
import com.example.budget3.model.Operation;
import com.example.budget3.viewmodel.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers clickHandlers;
    private Bill selectedBill;
    private ArrayList<Bill> billArrayList;
    private ArrayList<Operation> operationArrayList;
    private RecyclerView recyclerView;
    private OperationAdapter OperationAdapter;
    private int selectedOperationId;
        //эти константы нужны чтобы понять результаты
    public static final int ADD_OPERATION_REQUEST_CODE = 111;
    public static final int EDIT_OPERATION_REQUEST_CODE = 222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        System.out.println("SOUT - protected void onCreate");
        setSupportActionBar(toolbar);

        activityMainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(MainActivityViewModel.class);

        clickHandlers = new MainActivityClickHandlers();
        activityMainBinding.setClickHandlers(clickHandlers);

        mainActivityViewModel.getBills().observe(this, new Observer<List<Bill>>() {
            @Override
            public void onChanged(List<Bill> bills) {

                billArrayList = (ArrayList<Bill>) bills;
                for (Bill bill : bills) {
                    Log.d("MyTAG", bill.getBillName());
                }

                showInSpinner();
            }
        });

    }

    private void showInSpinner() {
        System.out.println("SOUT - showInSpinner");
        ArrayAdapter<Bill> billArrayAdapter = new ArrayAdapter<Bill>(this,
                R.layout.spinner_item, billArrayList);
        billArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(billArrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("SOUT - onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("SOUT - onOptionsItemSelected");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MainActivityClickHandlers {

        public void onFabClicked(View view) {
            System.out.println("SOUT - onFabClicked");

            //чтобы открывать AddEditActivity
            Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
                //так как по нажатию кнопки мы создаём новый объект, то передаём ADD_OPERATION_REQUEST_CODE
            startActivityForResult(intent, ADD_OPERATION_REQUEST_CODE);

        }

        //для получения выбранного жанра из выпадающего списка
        //метод для спинера. это стандартный код для спинера
        public void onSelectedItem(AdapterView<?> parent, View view, int position, long id) {
            System.out.println("SOUT - onSelectedItem");
            selectedBill = (Bill) parent.getItemAtPosition(position);

            String message = "id is " + selectedBill.getId() +
                    "\n name is " + selectedBill.getBillName();

//            Toast.makeText(parent.getContext(), message, Toast.LENGTH_SHORT).show();

            loadBillOperationsInArrayList(selectedBill.getId());
        }
    }

    //загружает список фильмов определённого жанра
    private void loadBillOperationsInArrayList(int billID) {
        System.out.println("SOUT - loadBillOperationsInArrayList");
        mainActivityViewModel.getBillOperations(billID).observe(this, new Observer<List<Operation>>() {
            @Override
            public void onChanged(List<Operation> operations) {
                operationArrayList = (ArrayList<Operation>) operations;
                loadRecyclerView();
            }
        });
    }

    //для загрузки RecyclerView
    private void loadRecyclerView() {
        System.out.println("SOUT - loadRecyclerView");
        recyclerView = activityMainBinding.secondaryLayout.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
            //устанавливаем адаптер для нашего RecyclerView
        OperationAdapter = new OperationAdapter();
            //передайм АррейЛист в OperationAdapter
        OperationAdapter.setOperationArrayList(operationArrayList);
        recyclerView.setAdapter(OperationAdapter);

        OperationAdapter.setOnItemClickListener(new OperationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Operation operation) {
                selectedOperationId = operation.getOperationId();
                Intent intent = new Intent(MainActivity.this,AddEditActivity.class);
                intent.putExtra(AddEditActivity.OPERATION_ID, selectedOperationId);
                intent.putExtra(AddEditActivity.OPERATION_NAME, operation.getOperationName());
                intent.putExtra(AddEditActivity.OPERATION_DESCRIPTION, operation.getOperationDescription());
                intent.putExtra(AddEditActivity.OPERATION_AMOUNT, operation.getOperationAmount());
                startActivityForResult(intent, EDIT_OPERATION_REQUEST_CODE);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {

                Operation operationToDelete = operationArrayList.get(viewHolder.getAdapterPosition());
                mainActivityViewModel.deleteOperation(operationToDelete);

            }
        }).attachToRecyclerView(recyclerView);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        System.out.println("SOUT - onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);

        int selectedBillId = selectedBill.getId();

        if (requestCode == ADD_OPERATION_REQUEST_CODE && resultCode == RESULT_OK) {

            Operation operation = new Operation();
            System.out.println("SOUT -  selectedBillId = " + selectedBillId);
            operation.setBillId(selectedBillId);
            System.out.println("SOUT -  OPERATION_NAME_1 = " + AddEditActivity.OPERATION_NAME);
            operation.setOperationName(data.getStringExtra(AddEditActivity.OPERATION_NAME));
            System.out.println("SOUT -  OPERATION_DESCRIPTION_1 = " + AddEditActivity.OPERATION_DESCRIPTION);
            operation.setOperationDescription(data.getStringExtra(AddEditActivity.OPERATION_DESCRIPTION));
            System.out.println("SOUT -  OPERATION_AMOUNT_1 = " + AddEditActivity.OPERATION_AMOUNT);
            operation.setOperationAmount(data.getDoubleExtra(AddEditActivity.OPERATION_AMOUNT,0));


            mainActivityViewModel.addNewOperation(operation);

        } else if (requestCode == EDIT_OPERATION_REQUEST_CODE && resultCode == RESULT_OK) {

            Operation operation = new Operation();
            operation.setOperationId(selectedOperationId);
            operation.setBillId(selectedBillId);
            System.out.println("SOUT -  OPERATION_NAME_2 = " + AddEditActivity.OPERATION_NAME);
            operation.setOperationName(data.getStringExtra(AddEditActivity.OPERATION_NAME));
            System.out.println("SOUT -  OPERATION_DESCRIPTION_2 = " + AddEditActivity.OPERATION_DESCRIPTION);
            operation.setOperationDescription(data.getStringExtra(AddEditActivity.OPERATION_DESCRIPTION));
            System.out.println("SOUT -  OPERATION_AMOUNT_2 = " + AddEditActivity.OPERATION_AMOUNT);
            operation.setOperationAmount(data.getDoubleExtra(AddEditActivity.OPERATION_AMOUNT,0));


            mainActivityViewModel.updateOperation(operation);

        }
    }
}
