package ie.cm.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import es.dmoral.toasty.Toasty;
import ie.cm.R;
import ie.cm.main.CoffeeMateApp;
import ie.cm.models.Coffee;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {

    public boolean isFavourite;
    public Coffee aCoffee;
    public ImageView editFavourite;
    private EditText name, shop, price;
    private RatingBar ratingBar;
    public CoffeeMateApp app;

    private OnFragmentInteractionListener mListener;

    public EditFragment() {
        // Required empty public constructor
    }

    public static EditFragment newInstance(Bundle coffeeBundle) {
        EditFragment fragment = new EditFragment();
        fragment.setArguments(coffeeBundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        app = (CoffeeMateApp) getActivity().getApplication();

        if(getArguments() != null)
            aCoffee = getCoffeeObject(getArguments().getString("foodId"));
    }

    private Coffee getCoffeeObject(String id) {

        for (Coffee c : app.coffeeList)
            if (c.coffeeId.equalsIgnoreCase(id))
                return c;

        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit, container, false);

        ((TextView)v.findViewById(R.id.editTitleTV)).setText(aCoffee.coffeeName);

        name = v.findViewById(R.id.editNameET);
        shop = v.findViewById(R.id.editShopET);
        price = v.findViewById(R.id.editPriceET);
        ratingBar = v.findViewById(R.id.editRatingBar);
        editFavourite = v.findViewById(R.id.editFavourite);

        name.setText(aCoffee.coffeeName);
        shop.setText(aCoffee.shop);
        price.setText(""+aCoffee.price);
        ratingBar.setRating((float)aCoffee.rating);

        if (aCoffee.favourite == true) {
            editFavourite.setImageResource(R.drawable.favourites_72_on);
            isFavourite = true;
        } else {
            editFavourite.setImageResource(R.drawable.favourites_72);
            isFavourite = false;
        }
        return v;
    }

    public void saveCoffee(View v) {
        if (mListener != null) {
            String coffeeName = name.getText().toString();
            String coffeeShop = shop.getText().toString();
            String coffeePriceStr = price.getText().toString();
            double ratingValue = ratingBar.getRating();

            double coffeePrice;
            try {
                coffeePrice = Double.parseDouble(coffeePriceStr);
            } catch (NumberFormatException e)
            {            coffeePrice = 0.0;        }

            if ((coffeeName.length() > 0) && (coffeeShop.length() > 0) && (coffeePriceStr.length() > 0)) {
                aCoffee.coffeeName = coffeeName;
                aCoffee.shop = coffeeShop;
                aCoffee.price = coffeePrice;
                aCoffee.rating = ratingValue;

                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                    return;
                }
            }
        } else
            StyleableToast.makeText(getActivity(), "You must Enter Something for Name and Shop",R.style.exampleToast).show();
    }

    public void toggle(View v) {

        if (isFavourite) {
            aCoffee.favourite = false;
            Toasty.error(getActivity(), "Removed From Favourites", Toast.LENGTH_SHORT).show();
            isFavourite = false;
            editFavourite.setImageResource(R.drawable.favourites_72);
        } else {
            aCoffee.favourite = true;
           // StyleableToast.makeText(getActivity(), "Added",R.style.exampleToast).show();

            Toasty.success( getActivity(),"Added To Favourites",
                    Toast.LENGTH_SHORT, true).show();

            isFavourite = true;
            editFavourite.setImageResource(R.drawable.favourites_72_on);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void toggle(View v);
        void saveCoffee(View v);
    }
}
