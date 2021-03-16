package com.example.budget3.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


//Список разделов
@Entity(tableName = "sections_table")
public class Section extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "section_name")
    private String sectionName;

    @Ignore
    public Section() {
    }

    public Section(int id, String sectionName) {
        this.id = id;
        this.sectionName = sectionName;
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
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
//        notifyPropertyChanged(BR.sectionName);
    }

    @Override
    public String toString() {
        return this.sectionName;
    }


}
