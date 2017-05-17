package project.pack.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.pack.R;
import project.pack.domain.Categoria;
import project.pack.facade.Facade;
import project.pack.logic.CategoriaLogic;
import project.pack.logicImp.CategoriaLogicImp;

public class CrearIncidenteCategoria extends AppCompatActivity {

    @Bind(R.id.etDescripcion)
    EditText etDescripcion;

    @Bind(R.id.btnCrearIncidente)
    Button btnCrearIncidente;

    @Bind(R.id.btnVolver)
    Button btnVolver;

    @Bind(R.id.spnCategorias)
    Spinner spnCategorias;

    @Bind(R.id.txtCategoria)
    TextView txtCategoria;

    @Bind(R.id.linearLayoutCategoria)
    LinearLayout linearLayoutCategoria;



    CategoriaLogic categoriaLogic;
    Facade facade = Facade.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_incidente_categoria);

        ButterKnife.bind(this);

        initSpinnerCategorias();
        linearLayoutCategoria.setVisibility(View.INVISIBLE);
        txtCategoria.setVisibility(View.INVISIBLE);
        spnCategorias.setVisibility(View.INVISIBLE);


        btnCrearIncidente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriaLogic = new CategoriaLogicImp();

                String texto = etDescripcion.getText().toString();

                String[] arrayPalabrasDelTexto = categoriaLogic.getArray(texto);

                ArrayList<Categoria> categorias = categoriaLogic.getCategorias();

                Categoria nuevaCategoria  = categoriaLogic.searchWorld(arrayPalabrasDelTexto, categorias);

                if(nuevaCategoria.getId()!=null){
                    Toast.makeText(getApplicationContext(), "Incedente con Categoria: "+nuevaCategoria.getNombre(), Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "El sistema no encontro una categoria", Toast.LENGTH_LONG).show();
//                    initSpinnerCategorias();
                }
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent layautPaginaPrincipal = new Intent(CrearIncidenteCategoria.this, MainActivity.class);
                startActivity(layautPaginaPrincipal);
            }
        });
    }


    private void initSpinnerCategorias() {

        linearLayoutCategoria.setVisibility(View.VISIBLE);
        txtCategoria.setVisibility(View.VISIBLE);
        spnCategorias.setVisibility(View.VISIBLE);

        ArrayList<Categoria> categorias = facade.getCategorias();

        ArrayAdapter<Categoria> spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categorias);
        spnCategorias.setAdapter(spinner_adapter);
    }
}
