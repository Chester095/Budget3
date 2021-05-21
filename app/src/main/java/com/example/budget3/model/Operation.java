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
    private double operationAmount;


    @Ignore
    public Operation() {
    }

    public Operation(int operationId, String operationName, String operationDescription, int billId, double operationAmount) {
        System.out.println("SOUT2 - Operation = " + operationId + ", " + operationName + ", " + operationDescription + ", " + operationAmount);
        this.operationId = operationId;
        this.operationName = operationName;
        this.operationDescription = operationDescription;
        this.billId = billId;
        this.operationAmount = operationAmount;
    }

    @Bindable
    public int getOperationId() {
        System.out.println("SOUT - getOperationId = " + operationId);
        return operationId;
    }

    public void setOperationId(int OperationId) {
        System.out.println("SOUT - setOperationId = " + OperationId);
        this.operationId = OperationId;
        notifyPropertyChanged(BR.operationId);
    }

    @Bindable
    public String getOperationName() {
        System.out.println("SOUT - getOperationName = " + operationName);
        return operationName;
    }

    public void setOperationName(String OperationName) {
        System.out.println("SOUT - setOperationName = " + OperationName);
        this.operationName = OperationName;
        notifyPropertyChanged(BR.operationName);
    }

    @Bindable
    public String getOperationDescription() {
        System.out.println("SOUT - getOperationDescription = " + operationDescription);
        return operationDescription;
    }

    public void setOperationDescription(String OperationDescription) {
        System.out.println("SOUT - setOperationDescription = " + OperationDescription);
        this.operationDescription = OperationDescription;
        notifyPropertyChanged(BR.operationDescription);
    }

    @Bindable
    public int getBillId() {
        System.out.println("SOUT - getBillId = " + billId);
        return billId;
    }

    public void setBillId(int billId) {
        System.out.println("SOUT - setBillId = " + billId);
        this.billId = billId;
        notifyPropertyChanged(BR.billId);
    }

    @Bindable
    public double getOperationAmount() {
        System.out.println("SOUT - getOperationAmount = " + operationAmount);
        return operationAmount;
    }

    public void setOperationAmount(double OperationAmount) {
        System.out.println("SOUT - setOperationAmount = " + OperationAmount);
        this.operationAmount = OperationAmount;
        notifyPropertyChanged(BR.operationAmount);
    }

}
