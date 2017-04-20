package project.pack.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.pack.R;
import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.facade.Facade;

public class CrearIncidenteActivity extends AppCompatActivity {

    @Bind(R.id.txtTitulo)
    TextView titulo;

    @Bind(R.id.txtDescripcion)
    TextView descripcion;

    @Bind(R.id.spnCategorias)
    Spinner spnCategorias;

    @Bind(R.id.spnSubCategorias)
    Spinner spnSubCategorias;

    @Bind(R.id.btnCrearIncidente)
    Button btnCrearIncidente;

    @Bind(R.id.btnLimpiarForm)
    Button btnLimpiarForm;

    @Bind(R.id.btnVolver)
    Button btnVolver;

    Facade facade = Facade.getInstance();
    Map<String, ArrayList<Categoria>> subCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_incidente);

        ButterKnife.bind(this);

        initSpinnerCategorias();

        btnCrearIncidente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mostrarIncidente = new Intent(CrearIncidenteActivity.this, VerIncidenteActivity.class);
                Facade facade = Facade.getInstance();

                if(validarCampos()){
                    // Armo la Categoria con los ComboBoxs
                    String nombreCategoria = spnCategorias.getSelectedItem().toString();
                    Categoria nombreSubCategoria = (Categoria) spnSubCategorias.getSelectedItem();
                    nombreSubCategoria.setSubCategoria(nombreCategoria);

                    facade.crearIncidente(1, titulo.getText().toString(), descripcion.getText().toString(), Calendar.getInstance().getTime(), nombreSubCategoria, new Coordenada((Math.random() * 3) + 1,(Math.random() * 3) + 1));
                    startActivity(mostrarIncidente);
                }else{
                    Toast.makeText(getApplicationContext(), "Falta completar campos obligatorios(*)", Toast.LENGTH_LONG).show();
                }
                facade.crearIncidente(1, titulo.getText().toString(), descripcion.getText().toString(), Calendar.getInstance().getTime(), (Categoria) lista.get(0), new Coordenada((Math.random() * 3) + 1, (Math.random() * 3) + 1));
                startActivity(mostrarIncidente);
            }
        });

        btnLimpiarForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titulo.setText("");
                descripcion.setText("");
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initSpinnerCategorias() {
        ArrayList<Categoria> categorias = facade.getCategorias();
        subCategorias = facade.getSubCategorias();

        ArrayAdapter<Categoria> spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categorias);
        spnCategorias.setAdapter(spinner_adapter);

        spnCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                loadOnClicSpinner(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });
    }

    private void loadOnClicSpinner(View view) {
        Categoria categoria = (Categoria) spnCategorias.getSelectedItem();
        ArrayList<Categoria> subCategorias = new ArrayList<Categoria>();
        Map<String, ArrayList<Categoria>> categorias = facade.getSubCategorias();

        if (categoria.getNombre().equals("Transito")) {
            subCategorias = categorias.get("Transito");
        } else if (categoria.getNombre().equals("Robo")) {
            subCategorias = categorias.get("Robo");
        } else if (categoria.getNombre().equals("Reclamo")) {
            subCategorias = categorias.get("Reclamo");
        } else {
            subCategorias.add(new Categoria(0, "Error", "Error"));
        }

        ArrayAdapter<Categoria> spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, subCategorias);
        spnSubCategorias.setAdapter(spinner_adapter);
    }

    public boolean validarCampos(){
        if(titulo.getText().toString()!= null && descripcion.getText().toString()!=null){
            String tituloSinEspacios = titulo.getText().toString().trim();
            String descripcionSinEspacios = descripcion.getText().toString().trim();

            if(!tituloSinEspacios.equals("") && !descripcionSinEspacios.equals("")){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
