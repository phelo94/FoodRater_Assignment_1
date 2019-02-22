package ie.cm.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import es.dmoral.toasty.Toasty;
import ie.cm.R;
import ie.cm.activities.Base;
import ie.cm.adapters.CoffeeFilter;
import ie.cm.adapters.CoffeeListAdapter;
import ie.cm.models.Coffee;

public class CoffeeFragment  extends Fragment implements
        AdapterView.OnItemClickListener,
        View.OnClickListener,
        AbsListView.MultiChoiceModeListener
{
    public Base activity;
    public static CoffeeListAdapter listAdapter;
    public ListView listView;
    public CoffeeFilter coffeeFilter;
    public boolean favourites = false;

    public CoffeeFragment() {
       //notes say i need to keep constructor empty
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle activityInfo = new Bundle(); // Creates a new Bundle object
        activityInfo.putString("foodId", (String) view.getTag());

        Fragment fragment = EditFragment.newInstance(activityInfo);
        getActivity().setTitle(R.string.editCoffeeLbl);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.homeFrame, fragment)
                .addToBackStack(null)
                .commit();
    }


    public static CoffeeFragment newInstance() {
        CoffeeFragment fragment = new CoffeeFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.activity = (Base) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, parent, false);
        getActivity().setTitle(R.string.recentlyViewedLbl);
        listAdapter = new CoffeeListAdapter(activity, this, activity.app.coffeeList);
        coffeeFilter = new CoffeeFilter(activity.app.coffeeList,"all",listAdapter);

        if (favourites) {
            getActivity().setTitle(R.string.favouritesCoffeeLbl);
            coffeeFilter.setFilter("favourites");
            coffeeFilter.filter(null);
            listAdapter.notifyDataSetChanged();
        }
       // setRandomCoffee();

        listView = v.findViewById(R.id.homeList);

        setListView(v);

        return v;
    }

    public void setListView(View view)
    {
        listView.setAdapter (listAdapter);
        listView.setOnItemClickListener(this);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);
        listView.setEmptyView(view.findViewById(R.id.emptyList));
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onClick(View view)
    {
        if (view.getTag() instanceof Coffee)
        {
            onCoffeeDelete ((Coffee) view.getTag());
        }
    }

    public void onCoffeeDelete(final Coffee coffee)
    {
        String stringName = coffee.coffeeName;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Are you sure you want to Delete the \'Food\' " + stringName + "?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                activity.app.coffeeList.remove(coffee); // remove from our list
                listAdapter.coffeeList.remove(coffee); // update adapters data
                setRandomCoffee();
                listAdapter.notifyDataSetChanged(); // refresh adapter
                Toasty.error(getActivity(), "Removed From Takeaway", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /* ************ MultiChoiceModeListener methods (begin) *********** */
    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu)
    {
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.delete_list_context, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.menu_item_delete_coffee:
                deleteCoffees(actionMode);
                return true;
            default:
                return false;
        }
    }

    public void deleteCoffees(ActionMode actionMode)
    {
        for (int i = listAdapter.getCount() -1 ; i >= 0; i--)
        {
            if (listView.isItemChecked(i))
            {
                activity.app.coffeeList.remove(listAdapter.getItem(i));
                if (favourites)
                   listAdapter.coffeeList.remove(listAdapter.getItem(i));
            }
        }
        setRandomCoffee();
        listAdapter.notifyDataSetChanged();

        actionMode.finish();
    }

    public void setRandomCoffee() {

        ArrayList<Coffee> coffeeList = new ArrayList<>();

        for(Coffee c : activity.app.coffeeList)
            if (c.favourite)
                coffeeList.add(c);

        if (favourites)
            if( !coffeeList.isEmpty()) {
                Coffee randomCoffee = coffeeList.get(new Random()
                            .nextInt(coffeeList.size()));

                ((TextView) getActivity().findViewById(R.id.favouriteCoffeeName)).setText(randomCoffee.coffeeName);
                ((TextView) getActivity().findViewById(R.id.favouriteCoffeeShop)).setText(randomCoffee.shop);
                ((TextView) getActivity().findViewById(R.id.favouriteCoffeePrice)).setText("€ " + randomCoffee.price);
                ((TextView) getActivity().findViewById(R.id.favouriteCoffeeRating)).setText(randomCoffee.rating + " *");
            }
            else {
                ((TextView) getActivity().findViewById(R.id.favouriteCoffeeName)).setText("N/A");
                ((TextView) getActivity().findViewById(R.id.favouriteCoffeeShop)).setText("N/A");
                ((TextView) getActivity().findViewById(R.id.favouriteCoffeePrice)).setText("N/A");
                ((TextView) getActivity().findViewById(R.id.favouriteCoffeeRating)).setText("N/A");
            }
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode)
    {}

    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
    }


}
