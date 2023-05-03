package com.example.villamizar_villamizar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class FormularioActivity extends AppCompatActivity {
    private EditText etnombre, etprecio, etimagen;
    private String productoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        etnombre = findViewById(R.id.et_form_nobre_producto);
        etprecio = findViewById(R.id.et_form_precio_producto);
        etimagen = findViewById(R.id.et_form_url_producto);
        productoId = getIntent().getStringExtra("producto_id");
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("productos").document(productoId).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Producto producto = documentSnapshot.toObject(Producto.class);
                        etnombre.setText(producto.getNombre());
                        etprecio.setText(producto.getPrecio().toString());
                        etimagen.setText(producto.getUrlImagen());
                    } else {
                        Toast.makeText(this, "El producto no existe", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al buscar el producto: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                });
    }

    public void clickGuardar(View view) {
        String nombre_producto = etnombre.getText().toString();
        Double precio_producto = Double.parseDouble(etprecio.getText().toString());
        String url_producto = etimagen.getText().toString();
        Producto newProduct = new Producto();
        newProduct.setNombre(nombre_producto);
        newProduct.setPrecio(precio_producto);
        newProduct.setUrlImagen(url_producto);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("productos").document(productoId).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {

                        firestore.collection("productos").document(productoId).set(newProduct);
                        Toast.makeText(this, "Producto actualizado", Toast.LENGTH_SHORT).show();
                    } else {

                        firestore.collection("productos").document().set(newProduct);
                        Toast.makeText(this, "Se creó el producto", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                    startActivity(new Intent(FormularioActivity.this, MainActivity.class));
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al buscar el producto: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                });
        finish();
}}