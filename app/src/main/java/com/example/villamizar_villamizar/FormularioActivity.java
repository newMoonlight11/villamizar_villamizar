package com.example.villamizar_villamizar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class FormularioActivity extends AppCompatActivity {
    private EditText etnombre, etprecio, etimagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        etnombre = findViewById(R.id.et_form_nobre_producto);
        etprecio = findViewById(R.id.et_form_precio_producto);
        etimagen = findViewById(R.id.et_form_url_producto);
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
        firestore.collection("productos").add(newProduct);
        Toast.makeText(this, "Se creó el producto", Toast.LENGTH_SHORT).show();
        finish();
    }
}