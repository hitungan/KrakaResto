package com.example.restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DrinkFragment extends Fragment {
    private Adapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.menu_recyclerview, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_menu);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        adapter = new Adapter(Data.drinkList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //adapter.notifyItemChanged(position);
            }

            @Override
            public void onPlusClick(int position) {
                int x = Data.drinkList.get(position).getFoodQty();
                Data.drinkList.get(position).changeQuantity(x + 1);
                //adapter.notifyItemChanged(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onMinClick(int position) {
                int x = Data.drinkList.get(position).getFoodQty();
                if (x != 0)
                    Data.drinkList.get(position).changeQuantity(x - 1);
                //adapter.notifyItemChanged(position);
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}
