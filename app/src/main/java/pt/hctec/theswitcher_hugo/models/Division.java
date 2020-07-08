package pt.hctec.theswitcher_hugo.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "division_table")

public class Division {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private int state;


    public Division(String title, int state) {
        this.title = title;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

