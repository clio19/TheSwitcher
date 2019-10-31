package pt.hctec.theswitcher_hugo.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pt.hctec.theswitcher_hugo.models.Division;

@Dao
public interface DivisionDao {

    @Insert
    void insert(Division division);

    @Update
    void update(Division division);

    @Delete
    void delete(Division division);

    @Query("DELETE FROM division_table")
    void deleteAllDivisions();

    @Query("SELECT * FROM division_table ORDER BY title DESC ")
    LiveData<List<Division>> getAllDivisions();


}

