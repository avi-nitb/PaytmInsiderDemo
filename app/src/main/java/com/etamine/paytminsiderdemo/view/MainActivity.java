package com.etamine.paytminsiderdemo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.etamine.paytminsiderdemo.R;
import com.etamine.paytminsiderdemo.adapters.DigitalEventRecyclerViewAdapter;
import com.etamine.paytminsiderdemo.adapters.EventsRecyclerViewAdapter;
import com.etamine.paytminsiderdemo.adapters.FoodRecyclerViewAdapter;
import com.etamine.paytminsiderdemo.adapters.SportsRecyclerViewAdapter;
import com.etamine.paytminsiderdemo.adapters.TheatreRecyclerViewAdapter;
import com.etamine.paytminsiderdemo.adapters.TravelRecyclerViewAdapter;
import com.etamine.paytminsiderdemo.adapters.WorkshopRecyclerViewAdapter;
import com.etamine.paytminsiderdemo.models.MasterList;
import com.etamine.paytminsiderdemo.viewmodel.ViewModelMainActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv_events, rv_workshops, rv_travel, rv_theatre, rv_digitalEvents, rv_food, rv_sports;
    TextView tvEvents, tvWorkshops, tvTravel, tvDigitalEvents, tvTheatre, tvFood, tvSports;
    ViewModelMainActivity viewModelMainActivity;
    EventsRecyclerViewAdapter eventsRecyclerViewAdapter;
    WorkshopRecyclerViewAdapter workshopRecyclerViewAdapter;
    TheatreRecyclerViewAdapter theatreRecyclerViewAdapter;
    SportsRecyclerViewAdapter sportsRecyclerViewAdapter;
    FoodRecyclerViewAdapter foodRecyclerViewAdapter;
    TravelRecyclerViewAdapter travelRecyclerViewAdapter;
    DigitalEventRecyclerViewAdapter digitalEventRecyclerViewAdapter;
    ArrayList<MasterList> eventList = new ArrayList<>(),
            workshopList = new ArrayList<>(),
            theatreList = new ArrayList<>(),
            foodList = new ArrayList<>(),
            sportsList = new ArrayList<>(),
            travelList = new ArrayList<>(),
            digitalEventsList = new ArrayList<>();
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Views
        rv_events = findViewById(R.id.rv_events);
        rv_workshops = findViewById(R.id.rv_workshops);
        rv_travel = findViewById(R.id.rv_travel);
        rv_theatre = findViewById(R.id.rv_theatre);
        rv_digitalEvents = findViewById(R.id.rv_digitalEvents);
        rv_food = findViewById(R.id.rv_food);
        rv_sports = findViewById(R.id.rv_sports);
        tvEvents = findViewById(R.id.tvEvents);
        tvWorkshops = findViewById(R.id.tvWorkshops);
        tvTravel = findViewById(R.id.tvTravels);
        tvDigitalEvents = findViewById(R.id.tvDigitalEvents);
        tvTheatre = findViewById(R.id.tvTheatre);
        tvFood = findViewById(R.id.tvFoods);
        tvSports = findViewById(R.id.tvSports);

        //Initializing ViewModel
        viewModelMainActivity = ViewModelProviders.of(this).get(ViewModelMainActivity.class);
        viewModelMainActivity.getMasterMutableLiveData().observe(this, masterListUpdateObserver);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait... ");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        progressDialog.show();

        }

        Observer<ArrayList<MasterList>> masterListUpdateObserver = new Observer<ArrayList<MasterList>>() {
            @Override
            public void onChanged(ArrayList<MasterList> masterLists) {
                //set Data to Views

                progressDialog.dismiss();

                for (int i = 0; i< masterLists.size(); i++){
                    String groupName = masterLists.get(i).getGroup_id().getName();
                    if (groupName.equalsIgnoreCase("events")){
                        eventList.add(masterLists.get(i));
                    } else if (groupName.equalsIgnoreCase("workshops")){
                        workshopList.add(masterLists.get(i));
                    } else if (groupName.equalsIgnoreCase("theatre")){
                        theatreList.add(masterLists.get(i));
                    } else if (groupName.equalsIgnoreCase("sports")){
                        sportsList.add(masterLists.get(i));
                    } else if (groupName.equalsIgnoreCase("digital events")){
                        digitalEventsList.add(masterLists.get(i));
                    } else if (groupName.equalsIgnoreCase("food")){
                        foodList.add(masterLists.get(i));
                    } else if (groupName.equalsIgnoreCase("travel")){
                        travelList.add(masterLists.get(i));
                    }
                }
                //Events RecyclerView
                tvEvents.setText("Events");
                eventsRecyclerViewAdapter = new EventsRecyclerViewAdapter(MainActivity.this, eventList);
                rv_events.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rv_events.setAdapter(eventsRecyclerViewAdapter);

                //Workshop recyclerView
                tvWorkshops.setText("Workshops");
                workshopRecyclerViewAdapter = new WorkshopRecyclerViewAdapter(MainActivity.this, workshopList);
                rv_workshops.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rv_workshops.setAdapter(workshopRecyclerViewAdapter);

                //Theater recyclerView
                tvTheatre.setText("Theatre");
                theatreRecyclerViewAdapter = new TheatreRecyclerViewAdapter(MainActivity.this, theatreList);
                rv_theatre.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rv_theatre.setAdapter(theatreRecyclerViewAdapter);

                //Sports recyclerView
                tvSports.setText("Sports");
                sportsRecyclerViewAdapter = new SportsRecyclerViewAdapter(MainActivity.this, sportsList);
                rv_sports.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rv_sports.setAdapter(sportsRecyclerViewAdapter);

                //Digital Events
                tvDigitalEvents.setText("Digital Events");
                digitalEventRecyclerViewAdapter = new DigitalEventRecyclerViewAdapter(MainActivity.this, digitalEventsList);
                rv_digitalEvents.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rv_digitalEvents.setAdapter(digitalEventRecyclerViewAdapter);

                //Food recyclerView
                tvFood.setText("Food");
                foodRecyclerViewAdapter = new FoodRecyclerViewAdapter(MainActivity.this, foodList);
                rv_food.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rv_food.setAdapter(foodRecyclerViewAdapter);

                //Travel recyclerView
                tvTravel.setText("Travel");
                travelRecyclerViewAdapter = new TravelRecyclerViewAdapter(MainActivity.this, travelList);
                rv_travel.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rv_travel.setAdapter(travelRecyclerViewAdapter);
            }
        };
}
