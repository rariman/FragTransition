package com.rariman.fragtrasition;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class ViewListFragment extends ListFragment{

    private ViewListFragmentListener listener;
    private ListView colorListView;
    private ItemColor itemColor[];
    private ItemColorAdapter adapter;

    interface ViewListFragmentListener {
        void onViewSelected(int position, int color);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        itemColor = new ItemColor[]
                {
                        new ItemColor(getResources().getColor(R.color.view0_default_color)),
                        new ItemColor(getResources().getColor(R.color.view1_default_color)),
                        new ItemColor(getResources().getColor(R.color.view2_default_color)),
                        new ItemColor(getResources().getColor(R.color.view3_default_color)),
                        new ItemColor(getResources().getColor(R.color.view4_default_color)),
                };
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        colorListView = getListView();
        colorListView.setOnItemClickListener(colorViewSelected);
        colorListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        adapter = new ItemColorAdapter(getActivity(), itemColor);

        setListAdapter(adapter);
    }

    AdapterView.OnItemClickListener colorViewSelected = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int color = itemColor[position].getColor();
            listener.onViewSelected(position, color);
        }
    };

    public void updateViewColor(int pos, int color)
    {
        itemColor[pos].setColor(color);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (ViewListFragmentListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ViewListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
