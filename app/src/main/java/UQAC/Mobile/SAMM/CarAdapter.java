package UQAC.Mobile.SAMM;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder>{
    Context context;
    List<Car> carArrayList;

    public static OnItemClickListener listener;

    public CarAdapter(Context context, List<Car> carArrayList){
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

        (holder).linearLayout.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
//                                Toast.makeText(
//                                                view.getContext(),
//                                                view.getContext().toString(),
//                                                Toast.LENGTH_SHORT)
//                                        .show();
                                //faudra voir pour passer en parametre la voiture sur laquelle on click pour avoir
                                // son historique pour l'instant c'est du bullshit
                                // j'ai fais des tests avec putExtra ce sera surement l'id de la voiture qu'on passera
                                listener.onItemClick();
                            }
                        });
    }

    @Override
    public int getItemCount() {
        return carArrayList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder    {
        TextView brand, model, mileage;

        private LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            brand = itemView.findViewById(R.id.text_view_brand);
            model = itemView.findViewById(R.id.text_view_model);
            mileage = itemView.findViewById(R.id.text_view_mileage);

            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

}