package css.roomdb_heartrate;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private HeartrateRepository heartrateRepository;    // The RoomDb repository
    private List<Heartrate> heartrateList;              // internal list of heartrates

    /**
     * This constructor is called in a special way from the MainActivity
     * @param application  -- Android includes a link to the entire application rather than the MainActivity since the activity may be destroyed and recreated
     */
    public MainViewModel(@NonNull Application application) {
        super(application);
        heartrateRepository = new HeartrateRepository(application);
        heartrateList = new ArrayList<Heartrate>();
        //heartrateList = heartrateRepository.getAllHeartrates();
    }

    /**
     * Get all the data from the database using the repository
     * and convert the list of heartrates into one long string for the UI
     * @return a string containing all the heartrates with newline characters
     */
    String getHeartratesAsString () {
        String retString = "Heart Rates \n";
        heartrateList = heartrateRepository.getAllHeartrates();
        for (Heartrate hr:heartrateList) {
            retString = retString +  hr.toString() +"\n";
        }
        return retString;
    }

    /**
     * Add a heartrate to the database using the repositroy
     * @param heartRate
     * @param age
     */
    public void insert(Integer heartRate, Integer age) {
        Heartrate hr = new Heartrate(heartRate, age);
        heartrateRepository.insert(hr);
    }

}
