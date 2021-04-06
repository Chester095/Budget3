package com.example.budget3.model;

import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "operations_table", foreignKeys = @ForeignKey(entity = Bill.class,
        parentColumns = "id", childColumns = "bill_id",//связываем таблицы
        onDelete = ForeignKey.CASCADE)) // указываем, что если срока жанра будет удалена, что делать со всеми фильмами (удаляем)

public class Operation extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "operation_id")
    private int operationId;
    @ColumnInfo(name = "operation_name")
    private String operationName;
    @ColumnInfo(name = "operation_description")
    private String operationDescription;
    @ColumnInfo(name = "bill_id")
    private int billId;
    @ColumnInfo(name = "operation_amount")
    private int operationAmount;

    @Ignore
    public Operation() {
    }

    public Operation(int operationId, String operationName, String operationDescription, int billId, int operationAmount) {
        System.out.println("SOUT - Operation");
        this.operationId = operationId;
        this.operationName = operationName;
        this.operationDescription = operationDescription;
        this.billId = billId;
        this.operationAmount = operationAmount;
    }

    @Bindable
    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int OperationId) {
        System.out.println("SOUT - setOperationId");
        this.operationId = OperationId;
        notifyPropertyChanged(BR.operationId);
    }

    @Bindable
    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String OperationName) {
        System.out.println("SOUT - setOperationName");
        this.operationName = OperationName;
        notifyPropertyChanged(BR.operationName);
    }

    @Bindable
    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String OperationDescription) {
        System.out.println("SOUT - setOperationDescription");
        this.operationDescription = OperationDescription;
        notifyPropertyChanged(BR.operationDescription);
    }

    @Bindable
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        System.out.println("SOUT - setBillId");
        this.billId = billId;
        notifyPropertyChanged(BR.billId);
    }

    @Bindable
    public int getOperationAmount() { return operationAmount; }

    public void setOperationAmount(int OperationAmount) {
        System.out.println("SOUT - setOperationAmount_1");
        this.operationAmount = OperationAmount;
        System.out.println("SOUT - setOperationAmount_2");
        notifyPropertyChanged(BR.operationAmount);
    }


    // вот эта фигня загрывает ошибку при компиляции, но при выводе приложения ошибка выключает приложение
    public void setOperationAmount(String stringExtra) {
        System.out.println("SOUT - setOperationAmount_3");
        this.operationAmount = Integer.parseInt(stringExtra);
        notifyPropertyChanged(BR.operationAmount);
    }
}
