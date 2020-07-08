package pt.hctec.theswitcher_hugo.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import pt.hctec.theswitcher_hugo.db.DivisionDao;
import pt.hctec.theswitcher_hugo.db.DivisionDatabase;
import pt.hctec.theswitcher_hugo.models.Division;

public class DivisionRepository {

    private DivisionDao divisionDao;
    private LiveData<List<Division>> allDivisions;

    public DivisionRepository(Application application) {
        DivisionDatabase database = DivisionDatabase.getInstance(application);
        divisionDao = database.divisionDao();
        allDivisions = divisionDao.getAllDivisions();
    }

    public void insert(Division division) {
        new InsertDivisonAsyncTask(divisionDao).execute(division);
    }

    public void update(Division division) {
        new UpdateDivisionAsyncTask(divisionDao).execute(division);
    }

    public void delete(Division division) {
        new DeleteDivisionAsyncTask(divisionDao).execute(division);
    }

    public void deleteAllDivisions() {
        new DeleteAllDivisionsAsyncTask(divisionDao).execute();
    }

    public LiveData<List<Division>> getAllDivisions() {
        return allDivisions;
    }


    private class InsertDivisonAsyncTask extends AsyncTask<Division, Void, Void> {
        private DivisionDao divisionDao;

        private InsertDivisonAsyncTask(DivisionDao divisionDao) {
            this.divisionDao = divisionDao;
        }

        @Override
        protected Void doInBackground(Division... divisions) {
            divisionDao.insert(divisions[0]);
            return null;
        }
    }

    private static class UpdateDivisionAsyncTask extends AsyncTask<Division, Void, Void> {
        private DivisionDao divisionDao;

        private UpdateDivisionAsyncTask(DivisionDao divisionDao) {
            this.divisionDao = divisionDao;
        }

        @Override
        protected Void doInBackground(Division... divisions) {
            divisionDao.update(divisions[0]);
            return null;
        }
    }

    private static class DeleteDivisionAsyncTask extends AsyncTask<Division, Void, Void> {
        private DivisionDao divisionDao;

        private DeleteDivisionAsyncTask(DivisionDao divisionDao) {
            this.divisionDao = divisionDao;
        }

        @Override
        protected Void doInBackground(Division... divisions) {
            divisionDao.delete(divisions[0]);
            return null;
        }
    }

    private static class DeleteAllDivisionsAsyncTask extends AsyncTask<Void, Void, Void> {
        private DivisionDao divisionDao;

        private DeleteAllDivisionsAsyncTask(DivisionDao divisionDao) {
            this.divisionDao = divisionDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            divisionDao.deleteAllDivisions();
            return null;
        }
    }

}
