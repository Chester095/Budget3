package com.example.budget3.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Operations_table", foreignKeys = @ForeignKey(entity = Bill.class,
        parentColumns = "id", childColumns = "bill_id",//связываем таблицы
        onDelete = ForeignKey.CASCADE)) // указываем, что если срока жанра будет удалена, что делать со всеми фильмами (удаляем)
public class Operation extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Operation_id")
    private int OperationId;
    @ColumnInfo(name = "Operation_name")
    private String OperationName;
    @ColumnInfo(name = "Operation_description")
    private String OperationDescription;
    @ColumnInfo(name = "bill_id")
    private int billId;

    @Ignore
    public Operation() {
    }

    public Operation(int OperationId, String OperationName, String OperationDescription, int billId) {
        this.OperationId = OperationId;
        this.OperationName = OperationName;
        this.OperationDescription = OperationDescription;
        this.billId = billId;
    }

    @Bindable
    public int getOperationId() {
        return OperationId;
    }

    public void setOperationId(int OperationId) {
        this.OperationId = OperationId;
        notifyPropertyChanged(BR.operationId);
    }

    @Bindable
    public String getOperationName() {
        return OperationName;
    }

    public void setOperationName(String OperationName) {
        this.OperationName = OperationName;
        notifyPropertyChanged(BR.operationName);
    }

    @Bindable
    public String getOperationDescription() {
        return OperationDescription;
    }

    public void setOperationDescription(String OperationDescription) {
        this.OperationDescription = OperationDescription;
        notifyPropertyChanged(BR.operationDescription);
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
