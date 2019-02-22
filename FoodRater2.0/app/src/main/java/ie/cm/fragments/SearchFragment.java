package ie.cm.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import ie.cm.R;

public class SearchFragment extends CoffeeFragment
        implements AdapterView.OnItemSelectedListener {

    String selected;
    SearchView searchView;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        getActivity().setTitle(R.string.searchCoffeesLbl);
        listView = v.findViewById(R.id.searchList); //Bind to the list on our Search layout
        setListView(v);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter
                .createFromResource(getActivity(), R.array.coffeeTypes,
                        android.R.layout.simple_spinner_item);

        spinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = v.findViewById(R.id.searchSpinner);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);

        searchView = v.findViewById(R.id.searchView);
        searchView.setQueryHint("Search your Food Items Here");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                coffeeFilter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                coffeeFilter.filter(newText);
                return false;
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context c) { super.onAttach(c); }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void checkSelected(String selected)
    {
        if (selected != null) {
            if (selected.equals("All Types")) {
                coffeeFilter.setFilter("all");
            } else if (selected.equals("Favourites")) {
                coffeeFilter.setFilter("favourites");
            }

            String filterText = ((SearchView)activity
                    .findViewById(R.id.searchView)).getQuery().toString();

            if(filterText.length() > 0)
                coffeeFilter.filter(filterText);
            else
                coffeeFilter.filter("");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected = parent.getItemAtPosition(position).toString();
        checkSelected(selected);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    @Override
    public void deleteCoffees(ActionMode actionMode) {
        super.deleteCoffees(actionMode);
        checkSelected(selected);
    }

}
