package ie.cm.main;

import ie.cm.models.Coffee;
import java.util.ArrayList;
import java.util.List;
import android.app.Application;
import android.util.Log;

public class CoffeeMateApp extends Application
{
    public List <Coffee> coffeeList = new ArrayList<>();

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("foodrater", "FoodRater App Started");
    }
}