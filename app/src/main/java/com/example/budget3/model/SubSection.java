package com.example.budget3.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


//Список разделов
@Entity(tableName = "subSections_table")
public class SubSection extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "subSection_name")
    private String subSectionName;

    @Ignore
    public SubSection() {
    }

    public SubSection(int id, String subSectionName) {
        this.id = id;
        this.subSectionName = subSectionName;
    }

    @Bindable //для индентификации
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getSubSectionName() {
        return subSectionName;
    }

    public void setSubSectionName(String subSectionName) {
        this.subSectionName = subSectionName;
//        notifyPropertyChanged(BR.subSectionName);
    }

    @Override
    public String toString() {
        return this.subSectionName;
    }


}
