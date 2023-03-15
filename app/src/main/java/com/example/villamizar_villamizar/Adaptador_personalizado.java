package com.example.villamizar_villamizar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adaptador_personalizado extends RecyclerView.Adapter<Adaptador_personalizado.ViewHolder>{
    private ArrayList<Producto> listadoInfoRend;
    private OnItemClickListener onItemClickListener;

    public void setListadoInfoRend(ArrayList<Producto> listadoInfoRend) {
        this.listadoInfoRend = listadoInfoRend;
        notifyDataSetChanged();
    }

    public Adaptador_personalizado(ArrayList<Producto> listadoInfoRend) {
        this.listadoInfoRend = listadoInfoRend;
        this.onItemClickListener=null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public Adaptador_personalizado.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_producto,parent,false);
        return new ViewHolder(miView);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador_personalizado.ViewHolder holder, int position) {
        Producto miProducto = listadoInfoRend.get(position);
        holder.enlazar(miProducto);

    }

    @Override
    public int getItemCount() {
        return listadoInfoRend.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvPrice;
        private ImageView imvProduct;
        private Button btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.item_name);
            tvPrice=itemView.findViewById(R.id.item_price);
            imvProduct=itemView.findViewById(R.id.iv_product);
            btnDelete=itemView.findViewById(R.id.delete);
        }
        public void enlazar(Producto miProducto){
            tvName.setText(miProducto.getNombre());
            tvPrice.setText(miProducto.getPrecio().toString());
            Picasso.get().load(miProducto.getUrlImagen()).error(R.drawable.ic_launcher_background).into(imvProduct);
            if(onItemClickListener!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(miProducto,getAdapterPosition());
                    }
                });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onBtnDeleteClick(miProducto, getAdapterPosition());

                }
            });
            }

        }
    }
    public interface OnItemClickListener{
        void onItemClick(Producto miProducto, int posicion);
        void onBtnDeleteClick(Producto miProducto, int posicion);
    }
}
