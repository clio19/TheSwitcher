package pt.hctec.theswitcher_hugo.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import pt.hctec.theswitcher_hugo.models.Division;

@Database(entities = {Division.class}, version = 1)
public abstract class DivisionDatabase extends RoomDatabase {

    private static DivisionDatabase instance;

    public abstract DivisionDao divisionDao(); // Room subclasses  abstract class

    // Only one can access
    public static synchronized  DivisionDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DivisionDatabase.class, "division_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private DivisionDao divisionDao;

        private PopulateDbAsyncTask(DivisionDatabase db) {
            divisionDao = db.divisionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            divisionDao.insert(new Division("Kitchen",0));
            divisionDao.insert(new Division("Living room", 0));
            divisionDao.insert(new Division("Master bedroom", 0));
            divisionDao.insert(new Division("Guest's bedroom", 0));
            return null;
        }
    }

}

