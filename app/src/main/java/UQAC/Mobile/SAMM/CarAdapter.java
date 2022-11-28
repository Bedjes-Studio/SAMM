package UQAC.Mobile.SAMM;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder>{
    Context context;
    List<Car> carArrayList;

    public CarAdapter(Context context, ArrayList<Car> carArrayList){
        this.context = context;
        this.carArrayList = carArrayList;
    }

    @NonNull
    @Override
    public CarAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.car_item,parent,false);
        return new CarAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.MyViewHolder holder, int position) {
        holder.brand.setText(carArrayList.get(position).getBrand());
        holder.model.setText(carArrayList.get(position).getModel());
        holder.mileage.setText(String.valueOf(carArrayList.get(position).getMileage()));
    }

    @Override
    public int getItemCount() {
        return carArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder    {
        TextView brand, model, mileage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            brand = itemView.findViewById(R.id.text_view_brand);
            model = itemView.findViewById(R.id.text_view_model);
            mileage = itemView.findViewById(R.id.text_view_mileage);
        }
    }
}
