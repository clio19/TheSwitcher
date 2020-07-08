package pt.hctec.theswitcher_hugo.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import pt.hctec.theswitcher_hugo.models.Division;
import pt.hctec.theswitcher_hugo.repositories.DivisionRepository;

public class DivisionViewModel extends AndroidViewModel {
    private DivisionRepository repository;
    private LiveData<List<Division>> allDivisions;

    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    public DivisionViewModel(@NonNull Application application) {
        super(application);
        repository = new DivisionRepository(application);
        allDivisions = repository.getAllDivisions();
    }

    public void insert(final Division division) {
        isUpdating.setValue(true);

        repository.insert(division);
    }

    public void update(Division division) {
        repository.update(division);
    }

    public void delete(Division division) {
        repository.delete(division);
    }

    public void deleteAlDivisions() {
        repository.deleteAllDivisions();
    }

    public LiveData<List<Division>> getAllDivisions() {
        return allDivisions;
    }

}