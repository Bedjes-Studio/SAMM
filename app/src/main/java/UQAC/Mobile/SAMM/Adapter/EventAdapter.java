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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import UQAC.Mobile.SAMM.Base.Cost;
import UQAC.Mobile.SAMM.Base.Earning;
import UQAC.Mobile.SAMM.Base.Event;
import UQAC.Mobile.SAMM.R;
import UQAC.Mobile.SAMM.Base.Refuel;

/**
 * This adapter is used to display all events of a car
 */
public class EventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int LAYOUT_REFUEL = 0;
    private static final int LAYOUT_REPAIR = 1;
    private static final int LAYOUT_EARNING = 2;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    private final Event[] events;

    public EventAdapter(Event[] events) {
        this.events = events;
    }

    /**
     * return type of the item, there is no other solutions than using hard coded strings...
     * maybe store each event type in class
     */
    @Override
    public int getItemViewType(int position) {
        switch (events[position].getClass().getSimpleName()) {
            case "Refuel" :
                return LAYOUT_REFUEL;
            case "Cost":
                return LAYOUT_REPAIR;
            case "Earning":
                return LAYOUT_EARNING;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return events.length;
    }

    /**
     * Create classes for each layout ViewHolder
     */

    public static class RefuelViewHolder extends RecyclerView.ViewHolder {

        private TextView litter;
        private TextView litterPrice;
        private TextView totalCost;
        private TextView date;
        private TextView mileage;
        // TODO : use linearLayout when update will be ready
        // private LinearLayout linearLayout;

        public RefuelViewHolder(@NonNull View eventItemView) {
            super(eventItemView);
            findViewInLayout(eventItemView);
        }

        private void findViewInLayout(View eventItemView) {
            litter = eventItemView.findViewById(R.id.text_view_litter);
            litterPrice = eventItemView.findViewById(R.id.text_view_litter_price);
            totalCost = eventItemView.findViewById(R.id.text_view_total_cost);
            date = eventItemView.findViewById(R.id.text_view_date);
            mileage = eventItemView.findViewById(R.id.text_view_mileage);
            // linearLayout = eventItemView.findViewById(R.id.linearLayout);
        }

        private void setView(Refuel refuel) {

            String litterFormatted = refuel.getLitter() + " L";
            String litterPriceFormatted = refuel.getLitterPrice() + " L/$";
            String totalCostFormatted = refuel.getTotalCost() + " $";
            String mileageFormatted = refuel.getMileage() + " km";
            // TODO : get date from server
            String dateFormatted = dateFormat.format(new Date());
            // String dateFormatted = dateFormat.format(refuel.getDate());

            litter.setText(litterFormatted);
            litterPrice.setText(litterPriceFormatted);
            totalCost.setText(totalCostFormatted);
            mileage.setText(mileageFormatted);
            date.setText(dateFormatted);
            Log.d("HUGO", "Event Date Time : " + dateFormatted);
        }
    }

    static class RepairViewHolder extends RecyclerView.ViewHolder {

        private TextView value;
        private TextView reason;
        private TextView date;
        private TextView mileage;
        // TODO : use linearLayout when update will be ready
        // private LinearLayout linearLayout;

        public RepairViewHolder(@NonNull View eventItemView) {
            super(eventItemView);
            findViewInLayout(eventItemView);
        }

        private void findViewInLayout(View eventItemView) {
            value = eventItemView.findViewById(R.id.text_view_value);
            reason = eventItemView.findViewById(R.id.text_view_reason);
            date = eventItemView.findViewById(R.id.text_view_date);
            mileage = eventItemView.findViewById(R.id.text_view_mileage);
            // linearLayout = eventItemView.findViewById(R.id.linearLayout);
        }

        private void setView(Cost cost) {
            String valueFormatted = cost.getValue() + " $";
            String reasonFormatted = cost.getReason();
            String mileageFormatted = cost.getMileage() + " km";
            // TODO : get date from server
            String dateFormatted = dateFormat.format(new Date());
            // String dateFormatted = dateFormat.format(cost.getDate());

            value.setText(valueFormatted);
            reason.setText(reasonFormatted);
            mileage.setText(mileageFormatted);
            date.setText(dateFormatted);
            Log.d("HUGO", "Event Date Time : " + dateFormatted);
        }
    }

    public static class EarningViewHolder extends RecyclerView.ViewHolder {
        private TextView value;
        private TextView reason;
        private TextView date;
        private TextView mileage;
        // TODO : use linearLayout when update will be ready
        // private LinearLayout linearLayout;

        public EarningViewHolder(@NonNull View eventItemView) {
            super(eventItemView);
            findViewInLayout(eventItemView);
        }

        private void findViewInLayout(View eventItemView) {
            value = eventItemView.findViewById(R.id.text_view_value);
            reason = eventItemView.findViewById(R.id.text_view_reason);
            date = eventItemView.findViewById(R.id.text_view_date);
            mileage = eventItemView.findViewById(R.id.text_view_mileage);
            // linearLayout = eventItemView.findViewById(R.id.linearLayout);
        }

        private void setView(Earning earning) {
            String valueFormatted = earning.getValue() + " $";
            String reasonFormatted = earning.getReason();
            String mileageFormatted = earning.getMileage() + " km";
            // TODO : get date from server
            String dateFormatted = dateFormat.format(new Date());
            // String dateFormatted = dateFormat.format(earning.getDate());

            value.setText(valueFormatted);
            reason.setText(reasonFormatted);
            mileage.setText(mileageFormatted);
            date.setText(dateFormatted);
        }
    }

    // In the onCreateViewHolder, inflate the
    // xml layout as per the viewType.
    // This method returns either of the
    // ViewHolder classes defined above,
    // depending upon the layout passed as a parameter.

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case LAYOUT_REFUEL:
                View layoutRefuel = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_refuel, parent, false);
                return new RefuelViewHolder(layoutRefuel);
            case LAYOUT_REPAIR:
                View layoutRepair = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_repair, parent, false);
                return new RepairViewHolder(layoutRepair);
            case LAYOUT_EARNING:
                View layoutEarning = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_earning, parent, false);
                return new EarningViewHolder(layoutEarning);
            default:
                Log.e("SAMM", "Unable to identify the event class, using refuel layout");
                return onCreateViewHolder(parent, LAYOUT_REFUEL);
        }
    }

    /**
     * Set the view depending on view holder type
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case LAYOUT_REFUEL:
                Refuel refuel = (Refuel) events[position];
                RefuelViewHolder refuelViewHolder = (RefuelViewHolder) holder;
                refuelViewHolder.setView(refuel);
                // TODO : open update activity when clicked
                // setItemOnClickListener(earningViewHolder.linearLayout);
                break;

            case LAYOUT_REPAIR:
                Cost cost = (Cost) events[position];
                RepairViewHolder repairViewHolder = (RepairViewHolder) holder;
                repairViewHolder.setView(cost);
                // TODO : open update activity when clicked
                // setItemOnClickListener(earningViewHolder.linearLayout);
                break;

            case LAYOUT_EARNING:
                Earning earning = (Earning) events[position];
                EarningViewHolder earningViewHolder = (EarningViewHolder) holder;
                earningViewHolder.setView(earning);
                // TODO : open update activity when clicked
                // setItemOnClickListener(earningViewHolder.linearLayout);
                break;
        }
    }

    private void setItemOnClickListener(LinearLayout holderLayout) {
        holderLayout.setOnClickListener(
                (View view) -> {
                    // TODO : implements update activity here
                    Toast.makeText(
                                    view.getContext(),
                                    "Earning",
                                    Toast.LENGTH_SHORT)
                            .show();
                });
    }
}
