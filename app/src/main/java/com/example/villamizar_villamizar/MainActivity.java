package com.example.villamizar_villamizar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Producto> mainListProductos = new ArrayList<>();
    private RecyclerView rvListadoProductos;
    Adaptador_personalizado miAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.txt_listado));

        rvListadoProductos = findViewById(R.id.rv_list_products);
        miAdaptador = new Adaptador_personalizado(mainListProductos);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        cargarData();
        miAdaptador.setOnItemClickListener(new Adaptador_personalizado.OnItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto, int posicion) {
                Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
                intent.putExtra("producto", miProducto);
                intent.putExtra("producto_id", miProducto.getId());
                startActivity(intent);
            }

            @Override
            public void onBtnDeleteClick(Producto miProducto, int posicion) {
                mainListProductos.remove(posicion);
                miAdaptador.setListadoInfoRend(mainListProductos);
                firestore.collection("productos").document(miProducto.getId()).delete();
            }
        });
        rvListadoProductos.setAdapter(miAdaptador);
        rvListadoProductos.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        mainListProductos.clear();
        cargarData();
    }

    public void cargarData() {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("productos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {

                        Producto productoAtrapado = document.toObject(Producto.class);
                        productoAtrapado.setId(document.getId());
                        mainListProductos.add(productoAtrapado);
                    }
                    miAdaptador.setListadoInfoRend(mainListProductos);
                } else {
                    Toast.makeText(MainActivity.this, "No se pudo conectar al servidor", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void cerrarSesion(View view) {
        SharedPreferences misPreferencias = getSharedPreferences("tienda_app", MODE_PRIVATE);
        SharedPreferences.Editor myEditor = misPreferencias.edit();
        myEditor.clear();
        myEditor.apply();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void clickAgregarProducto(View view) {
        Intent myIntent = new Intent(this, FormularioActivity.class);
        startActivity(myIntent);
    }
}