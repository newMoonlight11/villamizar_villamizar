package com.example.villamizar_villamizar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList <Producto> mainListProductos;
    private RecyclerView rvListadoProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.txt_listado));
        cargarData();
        rvListadoProductos=findViewById(R.id.rv_list_products);
        Adaptador_personalizado miAdaptador = new Adaptador_personalizado(mainListProductos);
        miAdaptador.setOnItemClickListener(new Adaptador_personalizado.OnItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto, int posicion) {
                Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
                intent.putExtra("producto",miProducto);
                startActivity(intent);
            }

            @Override
            public void onBtnDeleteClick(Producto miProducto, int posicion) {
                mainListProductos.remove(posicion);
                miAdaptador.setListadoInfoRend(mainListProductos);
            }
        });
        rvListadoProductos.setAdapter(miAdaptador);
        rvListadoProductos.setLayoutManager(new LinearLayoutManager(this));

    }
    public  void cargarData(){
        Producto producto1= new Producto();
        producto1.setNombre("Computador HP");
        producto1.setPrecio(8000000.0);
        producto1.setUrlImagen("https://pcsystemcolombia.com/wp-content/uploads/2020/11/Todo-En-Uno-HP-200-G4-22-Core-i3.png");

        Producto producto2 = new Producto("Teclado DELL", 250000.0, "https://d2d22nphq0yz8t.cloudfront.net/88e6cc4b-eaa1-4053-af65-563d88ba8b26/https://media.croma.com/image/upload/v1605160253/Croma%20Assets/Computers%20Peripherals/Computer%20Accessories%20and%20Tablets%20Accessories/Images/8984928518174.png/mxw_640,f_auto");
        Producto producto3 = new Producto("Mouse gamer", 50000.0, "https://casafreitas2.vteximg.com.br/arquivos/ids/188606-1000-1000/image-897d4a741fe749d4965f30b8cb6e8921.jpg?v=637617952877700000");
        //inicializar el array & agregar productos
        mainListProductos = new ArrayList<>();
        mainListProductos.add(producto1);
        mainListProductos.add(producto2);
        mainListProductos.add(producto3);

    }
}