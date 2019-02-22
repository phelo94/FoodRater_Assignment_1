package ie.cm.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
import ie.cm.R;
import ie.cm.activities.Home;
import ie.cm.main.CoffeeMateApp;
import ie.cm.models.Coffee;

public class AddFragment extends Fragment {

    private String 		coffeeName, coffeeShop;
    private double 		coffeePrice, ratingValue;
    private EditText name, shop, price;
    private RatingBar ratingBar;
    private Button saveButton;
    private CoffeeMateApp app;

    public AddFragment() {
        // Required empty public constructor
    }

    public static AddFragment newInstance() {
        AddFragment fragment = new AddFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (CoffeeMateApp) getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add, container, false);
        getActivity().setTitle(R.string.addACoffeeLbl);
        name = v.findViewById(R.id.addNameET);
        shop =  v.findViewById(R.id.addShopET);
        price =  v.findViewById(R.id.addPriceET);
        ratingBar =  v.findViewById(R.id.addRatingBar);
        saveButton = v.findViewById(R.id.addCoffeeBtn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCoffee();
            }
        });

        return v;
    }

    public void addCoffee() {

        coffeeName = name.getText().toString();
        coffeeShop = shop.getText().toString();
        try {
            coffeePrice = Double.parseDouble(price.getText().toString());
        } catch (NumberFormatException e) {
            coffeePrice = 0.0;
        }
        ratingValue = ratingBar.getRating();

        if ((coffeeName.length() > 0) && (coffeeShop.length() > 0)
                && (price.length() > 0)) {
            Coffee c = new Coffee(coffeeName, coffeeShop, ratingValue,
                    coffeePrice, false);

            app.coffeeList.add(c);
            startActivity(new Intent(this.getActivity(), Home.class));
        } else
            /*Toast.makeText(
                    this.getActivity(),
                    "You must Enter Something for "
                            + "\'Name\', \'Shop\' and \'Price\'",
                    Toast.LENGTH_SHORT).show();
                    */

        Toasty.warning( getActivity(),"You must Enter Something for \"\n" +
                        "                            + \"\\'Name\\', \\'Shop\\' and \\'Price\\'",
                Toast.LENGTH_LONG, true).show();
    }
}