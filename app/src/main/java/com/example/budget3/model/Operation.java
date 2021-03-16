package com.example.budget3.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.NO_ACTION;

//https://stackoverflow.com/questions/58593506/room-compile-problem-column-references-a-foreign-key-but-it-is-not-part-of-an
//https://startandroid.ru/ru/courses/architecture-components/27-course/architecture-components/530-urok-6-room-entity.html
@Entity(tableName = "operations_table",
        foreignKeys = {
                @ForeignKey(
                        entity = Bill.class,
                        parentColumns = {"id"},
                        childColumns = {"bill_id"},
                        onDelete = NO_ACTION
                ),
                @ForeignKey(
                        entity = Section.class,
                        parentColumns = {"id"},
                        childColumns = {"section_id"},
                        onDelete = NO_ACTION
                ),
                @ForeignKey(
                        entity = SubSection.class,
                        parentColumns = {"id"},
                        childColumns = {"subsection_id"},
                        onDelete = NO_ACTION
                ),

        },
        indices = {@Index(value = {"bill_id", "section_id", "subsection_id"}, unique = true)})
/*        @ForeignKey(
        parentColumns = "id", childColumns = "bill_id",//связываем таблицы
        onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = {"bill_id"}, unique = true)}) // указываем, что если срока жанра будет удалена, что делать со всеми фильмами (удаляем)*/


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
    @ColumnInfo(name = "section_id")
    private int sectionId;
    @ColumnInfo(name = "subsection_id")
    private int subSectionId;
    @ColumnInfo(name = "operation_amount")
    private String operationAmount;

    @Ignore
    public Operation() {
    }

    public Operation(int operationId, String operationName, String operationDescription, int billId, int sectionId, int subSectionId, String operationAmount) {
        this.operationId = operationId;
        this.operationName = operationName;
        this.operationDescription = operationDescription;
        this.billId = billId;
        this.sectionId = sectionId;
        this.subSectionId = subSectionId;
        this.operationAmount = operationAmount;
    }

    @Bindable
    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int OperationId) {
        this.operationId = OperationId;
        notifyPropertyChanged(BR.operationId);
    }

    @Bindable
    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String OperationName) {
        this.operationName = OperationName;
        notifyPropertyChanged(BR.operationName);
    }

    @Bindable
    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String OperationDescription) {
        this.operationDescription = OperationDescription;
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

    @Bindable
    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
//        notifyPropertyChanged(BR.sectionId);
    }

    @Bindable
    public int getSubSectionId() {
        return subSectionId;
    }

    public void setSubSectionId(int subSectionId) {
        this.subSectionId = subSectionId;
//        notifyPropertyChanged(BR.subSectionId);
    }

    @Bindable
    public String getOperationAmount() {
        return operationAmount;
    }

    public void setOperationAmount(String OperationAmount) {
        this.operationAmount = OperationAmount;
        notifyPropertyChanged(BR.operationAmount);
    }
}
