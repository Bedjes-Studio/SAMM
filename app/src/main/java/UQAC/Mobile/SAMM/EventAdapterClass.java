package UQAC.Mobile.SAMM;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static UQAC.Mobile.SAMM.EventItemClass.LayoutEarning;
import static UQAC.Mobile.SAMM.EventItemClass.LayoutRefuel;
import static UQAC.Mobile.SAMM.EventItemClass.LayoutRepair;


public class EventAdapterClass extends RecyclerView.Adapter {

    //private List<EventItemClass> eventItemClassList;
    private Event[] eventlist;

    //constructor
//    public EventAdapterClass(List<EventItemClass> eventItemClassList){
//        this.eventItemClassList = eventItemClassList;
//    }

    public EventAdapterClass(Event[] eventList){
        this.eventlist = eventList;
    }


    // Override the getItemViewType method.
    // This method uses a switch statement
    // to assign the layout to each item
    // depending on the viewType passed

    @Override
    public int getItemViewType(int position){
        //switch (eventItemClassList.get(position).getViewType()) {
        switch (eventlist[position].getClass().getSimpleName()) {
            //case 0:
            case "Refuel":
                return LayoutRefuel;
//            case 1:
//                return LayoutRepair;
            case "Earning":
                return LayoutEarning;
            default:
                return -1;
        }
    }

    // Create classes for each layout ViewHolder

    class LayoutRefuelViewHolder extends RecyclerView.ViewHolder{
        private TextView litter, litterPrice, totalCost, date, mileage;
        private LinearLayout linearLayout;

        public LayoutRefuelViewHolder(@NonNull View eventItemView){
            super(eventItemView);

            // find the view
            litter = eventItemView.findViewById(R.id.text_view_litter);
            litterPrice = eventItemView.findViewById(R.id.text_view_litter_price);
            totalCost = eventItemView.findViewById(R.id.text_view_total_cost);
            date = eventItemView.findViewById(R.id.text_view_date);
            mileage = eventItemView.findViewById(R.id.text_view_mileage);

            linearLayout = eventItemView.findViewById(R.id.linearLayout);
        }

        //method to set the views that will
        // be used further in onBindViewHolder method.
        private void setView(String litterValue, String litterPriceValue, String totalCostValue, Date dateValue, String mileageValue){
            litter.setText(litterValue);
            litterPrice.setText(litterPriceValue);
            totalCost.setText(totalCostValue);
            mileage.setText(mileageValue);

            String myFormat="MM/dd/yy";
            SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
            //dateText.setText(dateFormat.format(myCalendar.getTime()));

            //date.setText(dateFormat.format(dateValue));
        }
    }

    class LayoutRepairViewHolder extends RecyclerView.ViewHolder{
        private TextView value, date, mileage;
        private LinearLayout linearLayout;

        public LayoutRepairViewHolder(@NonNull View eventItemView){
            super(eventItemView);

            // find the view
            value = eventItemView.findViewById(R.id.text_view_value);
            //date = eventItemView.findViewById(R.id.text_view_date);
            mileage = eventItemView.findViewById(R.id.text_view_mileage);

            linearLayout = eventItemView.findViewById(R.id.linearLayout);
        }

        //method to set the views that will
        // be used further in onBindViewHolder method.
        private void setView(String valueValue, String dateValue, String mileageValue){
            value.setText(valueValue);
            mileage.setText(mileageValue);
            //date.setText(dateValue);
        }
    }

    class LayoutEarningViewHolder extends RecyclerView.ViewHolder{
        private TextView value, reason, date, mileage;
        private LinearLayout linearLayout;

        public LayoutEarningViewHolder(@NonNull View eventItemView){
            super(eventItemView);

            // find the view
            value = eventItemView.findViewById(R.id.text_view_value);
            reason = eventItemView.findViewById(R.id.text_view_reason);
            //date = eventItemView.findViewById(R.id.text_view_date);
            mileage = eventItemView.findViewById(R.id.text_view_mileage);

            linearLayout = eventItemView.findViewById(R.id.linearLayout);
        }

        //method to set the views that will
        // be used further in onBindViewHolder method.
        private void setView(String valueValue, String reasonValue, Date dateValue, String mileageValue){
            value.setText(valueValue);
            reason.setText(reasonValue);
            mileage.setText(mileageValue);


            String myFormat="MM/dd/yy";
            SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
            //dateText.setText(dateFormat.format(myCalendar.getTime()));

            //date.setText(dateFormat.format(dateValue));
        }
    }

    // In the onCreateViewHolder, inflate the
    // xml layout as per the viewType.
    // This method returns either of the
    // ViewHolder classes defined above,
    // depending upon the layout passed as a parameter.

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                      int viewType)
    {
        switch (viewType){
            case LayoutRefuel:
                View layoutRefuel = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.refuel_item, parent, false);
                return new LayoutRefuelViewHolder(layoutRefuel);
            case LayoutRepair:
                View layoutRepair = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.repair_item, parent, false);
                return new LayoutRepairViewHolder(layoutRepair);
            case LayoutEarning:
                View layoutEarning = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.earning_item, parent, false);
                return new LayoutEarningViewHolder(layoutEarning);
            default:
                return null;
        }
    }

    // In onBindViewHolder, set the Views for each element
    // of the layout using the methods defined in the
    // respective ViewHolder classes.

    @Override
    public  void onBindViewHolder(
            @NonNull RecyclerView.ViewHolder holder,
            int position)
    {
//        switch (eventItemClassList.get(position).getViewType()){
        switch (eventlist[position].getClass().getSimpleName()) {
            case "Refuel":
                Refuel refuel = (Refuel) eventlist[position];
                String litter = String.valueOf(refuel.getLitter()) + " L";
                String litterPrice = String.valueOf(refuel.getLitterPrice()) + " L/$";
                String totalCost = String.valueOf(refuel.getTotalCost()) + " $";
                Date date = refuel.getDate();
                String mileage = String.valueOf(refuel.getMileage()) + " km";
                ((LayoutRefuelViewHolder)holder).setView(litter, litterPrice, totalCost, date, mileage);

                // The following code pops a toast message
                // when the item layout is clicked.
                // This message indicates the corresponding layout.
                ((LayoutRefuelViewHolder)holder)
                        .linearLayout.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view)
                                    {
//                                        Toast.makeText(
//                                                view.getContext(),
//                                                "Refuel",
//                                                Toast.LENGTH_SHORT)
//                                                .show();
                                    }
                                });
                break;

//            case LayoutRepair:
//                String value = eventItemClassList.get(position).getValue();
//                date = eventItemClassList.get(position).getDate();
//                mileage = eventItemClassList.get(position).getMileage();
//                ((LayoutRepairViewHolder)holder).setView(value, date, mileage);
//
//                // The following code pops a toast message
//                // when the item layout is clicked.
//                // This message indicates the corresponding layout.
//                ((LayoutRepairViewHolder)holder)
//                        .linearLayout.setOnClickListener(
//                                new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view)
//                                    {
//                                        Toast.makeText(
//                                                        view.getContext(),
//                                                        "Repair",
//                                                        Toast.LENGTH_SHORT)
//                                                .show();
//                                    }
//                                });
//                break;
//
            case "Earning":
                Earning earning = (Earning) eventlist[position];
                String value = String.valueOf(earning.getValue()) + " $";
                String reason = earning.getReason();
                date = earning.getDate();
                mileage = String.valueOf(earning.getMileage()) + " km";
                ((LayoutEarningViewHolder)holder).setView(value, reason, date, mileage);

                // The following code pops a toast message
                // when the item layout is clicked.
                // This message indicates the corresponding layout.
                ((LayoutEarningViewHolder)holder)
                        .linearLayout.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view)
                                    {
//                                        Toast.makeText(
//                                                        view.getContext(),
//                                                        "Earning",
//                                                        Toast.LENGTH_SHORT)
//                                                .show();
                                    }
                                });
                break;
        }
    }

    @Override
    public int getItemCount(){
        return eventlist.length;
    }

}
