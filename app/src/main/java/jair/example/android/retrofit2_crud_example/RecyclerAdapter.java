package jair.example.android.retrofit2_crud_example;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by Brian on 23/03/2017.
 */

public class RecyclerAdapter extends  RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    List<Alumno> alumnoList = new ArrayList<>();
    Context context;
    public RecyclerAdapter(Context context, List<Alumno> alumnoList){
        this.alumnoList= alumnoList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Alumno user = alumnoList.get(position);

        holder.iduser.setText(user.getIdalumno());
        holder.nombre.setText(user.getNombre());
        holder.direccion.setText(user.getDireccion());
    }

    @Override
    public int getItemCount() {
        return alumnoList.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        TextView iduser;
        TextView nombre;
        TextView direccion;

        public ViewHolder(View itemView) {
            super(itemView);

            iduser = (TextView) itemView.findViewById(R.id.iduser);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            direccion = (TextView) itemView.findViewById(R.id.direccion);
        }
    }
}