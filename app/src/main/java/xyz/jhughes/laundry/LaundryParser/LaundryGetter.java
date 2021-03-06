package xyz.jhughes.laundry.LaundryParser;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hughesjeff
 */
public class LaundryGetter {

    private ArrayList<Machine> machines;

    public LaundryGetter(String building) {
        Retrofit retroFit = new Retrofit.Builder().baseUrl("http://api.tylorgarrett.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        MachineRetrofitInterface retrofitInterface = retroFit.create(MachineRetrofitInterface.class);
        Call<ArrayList<Machine>> call = retrofitInterface.machines(building);
        ArrayList<Machine> machineArrayList = null;
        try {
            machineArrayList = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (machineArrayList == null) {
            System.out.println("Yo, I'm in here");
            return;
        }

        machines = machineArrayList;
    }

    public ArrayList<Machine> getMachines() {
        return machines;
    }
}
