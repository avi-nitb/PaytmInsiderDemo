package com.etamine.paytminsiderdemo.viewmodel;

import android.app.ProgressDialog;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.etamine.paytminsiderdemo.network.NetworkCallsInterface;
import com.etamine.paytminsiderdemo.network.RetrofitClientInstance;
import com.etamine.paytminsiderdemo.models.MasterList;
import com.etamine.paytminsiderdemo.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelMainActivity extends ViewModel {

    MutableLiveData<ArrayList<MasterList>> masterLiveData;
    ArrayList<MasterList> masterList;
    JsonArray groups = new JsonArray();
    JsonArray allEvents = new JsonArray();
    ArrayList<JsonObject> masterData = new ArrayList<JsonObject>();

    public ViewModelMainActivity(){
        masterLiveData = new MutableLiveData<>();
        //Service network call
        callService();
    }

    private void callService() {
        NetworkCallsInterface getDataService = RetrofitClientInstance.getRetrofitInstance().create(NetworkCallsInterface.class);
        Call<JsonObject> call = getDataService.getData();

        Log.wtf("URL Called", call.request().url() + "");
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.wtf("response", response.message());
                groups.addAll(response.body().get("groups").getAsJsonArray());

                Log.d("Group Array", groups.toString());


                Log.wtf("Digital", response.body().getAsJsonObject("list").getAsJsonObject("groupwiseList").getAsJsonArray("Digital Events").toString());
                for (int i = 0; i<groups.size();i++){
                    String tag = groups.get(i).toString().replace("\"","");
                    Log.wtf(tag, tag);
                    JsonObject o = response.body().getAsJsonObject("list").getAsJsonObject("groupwiseList");
                    allEvents.addAll(response.body().getAsJsonObject("list").getAsJsonObject("groupwiseList").getAsJsonArray(tag ));
                }
                ArrayList<MasterList> jj = new ArrayList<>();
                for (int j = 0; j<allEvents.size(); j++){
                    masterData.add(response.body().getAsJsonObject("list")
                            .getAsJsonObject("masterList").getAsJsonObject(allEvents.get(j).toString().replace("\"", "")));
                    JsonObject o = response.body().getAsJsonObject("list").getAsJsonObject("masterList")
                            .getAsJsonObject(allEvents.get(j).toString().replace("\"", ""));
                    Log.wtf("object", o.toString());
                    jj.add(new Gson().fromJson(o.toString(), MasterList.class));

                }

                Type collectionType = new TypeToken<Collection<MasterList>>(){}.getType();
                Collection<MasterList> masterList = new Gson().fromJson(masterData.toString(), collectionType);
                Log.wtf("final list", masterList.toString());
                masterLiveData.setValue((ArrayList<MasterList>) masterList);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.wtf("response", t);
            }
        });

    }

    public MutableLiveData<ArrayList<MasterList>> getMasterMutableLiveData() {
        return masterLiveData;
    }
}
