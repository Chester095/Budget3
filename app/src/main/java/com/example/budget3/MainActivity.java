package com.example.budget3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

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

import com.example.budget3.databinding.ActivityMainBinding;
import com.example.budget3.model.Bill;
import com.example.budget3.model.ListItem;
import com.example.budget3.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers clickHandlers;
    private Bill selectedBill;
    private ArrayList<Bill> billArrayList;
    private ArrayList<ListItem> listItemArrayList;
    private RecyclerView recyclerView;
    private ListItemAdapter listItemAdapter;
    private int selectedListItemId;

    public static final int ADD_LISTITEM_REQUEST_CODE = 111;
    public static final int EDIT_LISTITEM_REQUEST_CODE = 222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
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

        ArrayAdapter<Bill> billArrayAdapter = new ArrayAdapter<Bill>(this,
                R.layout.spinner_item, billArrayList);
        billArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(billArrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

//            Toast.makeText(MainActivity.this, "Button is clicked!",
//                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(com.example.budget3.MainActivity.this, com.example.budget3.AddEditActivity.class);
            startActivityForResult(intent, ADD_LISTITEM_REQUEST_CODE);

        }

        //для получения выбранного жанра из выпадающего списка
        //метод для спинера. это стандартный код для спинера
        public void onSelectedItem(AdapterView<?> parent, View view, int position, long id) {

            selectedBill = (Bill) parent.getItemAtPosition(position);

            String message = "id is " + selectedBill.getId() +
                    "\n name is " + selectedBill.getBillName();

//            Toast.makeText(parent.getContext(), message, Toast.LENGTH_SHORT).show();

            loadBillListItemsInArrayList(selectedBill.getId());
        }
    }

    //загружает список фильмов определённого жанра
    private void loadBillListItemsInArrayList(int billID) {
        mainActivityViewModel.getBillListItems(billID).observe(this, new Observer<List<ListItem>>() {
            @Override
            public void onChanged(List<ListItem> listItems) {
                listItemArrayList = (ArrayList<ListItem>) listItems;
                loadRecyclerView();
            }
        });
    }

    //для загрузки RecycledView
    private void loadRecyclerView() {

//        recyclerView = activityMainBinding.secondaryLayout.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        listItemAdapter = new ListItemAdapter();
        listItemAdapter.setListItemArrayList(listItemArrayList);
        recyclerView.setAdapter(listItemAdapter);

        listItemAdapter.setOnItemClickListener(new ListItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListItem listItem) {
                selectedListItemId = listItem.getListItemId();
                Intent intent = new Intent(com.example.budget3.MainActivity.this,
                        com.example.budget3.AddEditActivity.class);
                intent.putExtra(com.example.budget3.AddEditActivity.LISTITEM_ID, selectedListItemId);
                intent.putExtra(com.example.budget3.AddEditActivity.LISTITEM_NAME, listItem.getListItemName());
                intent.putExtra(com.example.budget3.AddEditActivity.LISTITEM_DESCRIPTION, listItem.getListItemDescription());
                startActivityForResult(intent, EDIT_LISTITEM_REQUEST_CODE);
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

                ListItem listItemToDelete = listItemArrayList.get(viewHolder.getAdapterPosition());
                mainActivityViewModel.deleteListItem(listItemToDelete);

            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int selectedBillId = selectedBill.getId();

        if (requestCode == ADD_LISTITEM_REQUEST_CODE && resultCode == RESULT_OK) {

            ListItem listItem = new ListItem();
            listItem.setBillId(selectedBillId);
            listItem.setListItemName(data.getStringExtra(com.example.budget3.AddEditActivity.LISTITEM_NAME));
            listItem.setListItemDescription(data.getStringExtra(com.example.budget3.AddEditActivity.LISTITEM_DESCRIPTION));

            mainActivityViewModel.addNewListItem(listItem);

        } else if (requestCode == EDIT_LISTITEM_REQUEST_CODE && resultCode == RESULT_OK) {

            ListItem listItem = new ListItem();
            listItem.setListItemId(selectedListItemId);
            listItem.setBillId(selectedBillId);
            listItem.setListItemName(data.getStringExtra(com.example.budget3.AddEditActivity.LISTITEM_NAME));
            listItem.setListItemDescription(data.getStringExtra(com.example.budget3.AddEditActivity.LISTITEM_DESCRIPTION));

            mainActivityViewModel.updateListItem(listItem);

        }
    }
}
