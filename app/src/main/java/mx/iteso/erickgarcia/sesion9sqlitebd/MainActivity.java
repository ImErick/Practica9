package mx.iteso.erickgarcia.sesion9sqlitebd;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton addProduct, listProduct, clearAllProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addProduct = (ImageButton) findViewById(R.id.imageButton_add);
        listProduct = (ImageButton) findViewById(R.id.imageButton_list);
        clearAllProducts = (ImageButton) findViewById(R.id.imageButton_delete);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = new FragmentAddUProduct();
                fragmentTransaction.replace(R.id.main_fragment, fragment);
                fragmentTransaction.commit();
            }
        });

        listProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = new FragmentListProduct();
                fragmentTransaction.replace(R.id.main_fragment, fragment);
                fragmentTransaction.commit();
            }
        });

        clearAllProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQL adminSQL = new AdminSQL(MainActivity.this, "test.db", null, 1);
                SQLiteDatabase db = adminSQL.getWritableDatabase();
                db.execSQL("DELETE FROM product");
                db.close();
                Toast.makeText(MainActivity.this, "Delete all information sucessfull", Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = new FragmentAddUProduct();
                fragmentTransaction.replace(R.id.main_fragment, fragment);
                fragmentTransaction.commit();
            }
        });
    }
}
