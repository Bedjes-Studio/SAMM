/*
 * Copyright 2022 - Hugo LANGLAIS & Alexia LACOMBE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package UQAC.Mobile.SAMM.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import UQAC.Mobile.SAMM.Base.Car;
import UQAC.Mobile.SAMM.R;

/**
 * This adapted is used to display all cars for the user
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private final Context context;
    private final Car[] carArrayList;

    private OnItemClickListener listener;
    private OnItemLongClickListener listenerLong;

    public CarAdapter(Context context, Car[] carArrayList) {
        this.context = context;
        this.carArrayList = carArrayList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.car_item, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        holder.brand.setText(carArrayList[holder.getAdapterPosition()].getBrand());
        holder.model.setText(carArrayList[holder.getAdapterPosition()].getModel());
        holder.mileage.setText(String.valueOf(carArrayList[holder.getAdapterPosition()].getMileage()));

        holder.linearLayout.setOnClickListener((View view) -> {
            listener.onItemClick(carArrayList[holder.getAdapterPosition()].getId());
        });

        holder.linearLayout.setOnLongClickListener((View view) -> {
            Toast.makeText(
                            view.getContext(),
                            carArrayList[holder.getAdapterPosition()].getId(),
                            Toast.LENGTH_SHORT)
                    .show();
            listenerLong.onItemLongClick(carArrayList[holder.getAdapterPosition()].getId());
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return carArrayList.length;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        listener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(String id);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        listenerLong = onItemLongClickListener;
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(String id);
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView brand, model, mileage;

        private final LinearLayout linearLayout;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);

            brand = itemView.findViewById(R.id.text_view_brand);
            model = itemView.findViewById(R.id.text_view_model);
            mileage = itemView.findViewById(R.id.text_view_mileage);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

}
