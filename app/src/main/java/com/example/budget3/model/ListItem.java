package com.example.budget3.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "listItems_table", foreignKeys = @ForeignKey(entity = Bill.class,
        parentColumns = "id", childColumns = "bill_id",//связываем таблицы
        onDelete = ForeignKey.CASCADE)) // указываем, что если срока счета будет удалена, что делать со всеми фильмами (удаляем)
public class ListItem extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "listItem_id")
    private int listItemId;
    @ColumnInfo(name = "listItem_name")
    private String listItemName;
    @ColumnInfo(name = "listItem_description")
    private String listItemDescription;
    @ColumnInfo(name = "bill_id")
    private int billId;

    @Ignore
    public ListItem() {
    }

    public ListItem(int listItemId, String listItemName, String listItemDescription, int billId) {
        this.listItemId = listItemId;
        this.listItemName = listItemName;
        this.listItemDescription = listItemDescription;
        this.billId = billId;
    }

    @Bindable
    public int getListItemId() {
        return listItemId;
    }

    public void setListItemId(int listItemId) {
        this.listItemId = listItemId;
        notifyPropertyChanged(BR.listItemId);
    }

    @Bindable
    public String getListItemName() {
        return listItemName;
    }

    public void setListItemName(String listItemName) {
        this.listItemName = listItemName;
        notifyPropertyChanged(BR.listItemName);
    }

    @Bindable
    public String getListItemDescription() {
        return listItemDescription;
    }

    public void setListItemDescription(String listItemDescription) {
        this.listItemDescription = listItemDescription;
        notifyPropertyChanged(BR.listItemDescription);
    }

    @Bindable
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
        notifyPropertyChanged(BR.billId);
    }
}
