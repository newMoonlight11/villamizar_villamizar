package com.example.villamizar_villamizar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetalleActivity extends AppCompatActivity {
    private TextView tv_tituloD, tv_precioD;
    private ImageView imagenPrincipal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        setTitle(getString(R.string.txt_detalle));
        tv_tituloD=findViewById(R.id.tv_nombre_detalle);
        tv_precioD=findViewById(R.id.tv_precio_detalle);
        imagenPrincipal=findViewById(R.id.iv_imagen_detalle);
        Producto miProductoAtrapado = (Producto) getIntent().getSerializableExtra("producto");
        tv_tituloD.setText(miProductoAtrapado.getNombre());
        tv_precioD.setText(miProductoAtrapado.getPrecio().toString());
        Picasso.get().load(miProductoAtrapado.getUrlImagen()).error(R.drawable.ic_launcher_background).into(imagenPrincipal);
    }
}