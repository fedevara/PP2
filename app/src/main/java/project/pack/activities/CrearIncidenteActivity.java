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

    @Bind(R.id.txtLongitud)
    TextView txtLongitud;

    @Bind(R.id.txtLatitud)
    TextView txtLatitud;

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

                if (validarCampos()) {
                    // Armo la Categoria con los ComboBoxs
                    String nombreCategoria = spnCategorias.getSelectedItem().toString();
                    Categoria nombreSubCategoria = (Categoria) spnSubCategorias.getSelectedItem();
                    nombreSubCategoria.setSubCategoria(nombreCategoria);
                    Coordenada coordenada = obtenerCoordenadas();
                    try {
                        facade.crearIncidente(1, titulo.getText().toString(), descripcion.getText().toString(), Calendar.getInstance().getTime(), nombreSubCategoria, coordenada);
                        startActivity(mostrarIncidente);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error. No se logró guardar", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Falta completar campos obligatorios(*)", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnLimpiarForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titulo.setText("");
                descripcion.setText("");
                txtLongitud.setText("");
                ;
                txtLatitud.setText("");
                ;
                spnCategorias.setSelection(0);
                spnSubCategorias.setSelection(0);
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

    private boolean validarCampos() {
        System.out.println(new Coordenada((Math.random() * 3) + 1, (Math.random() * 3) + 1));
        if (titulo.getText().toString() != null && descripcion.getText().toString() != null) {
            String tituloSinEspacios = titulo.getText().toString().trim();
            String descripcionSinEspacios = descripcion.getText().toString().trim();

            if (!tituloSinEspacios.equals("") && !descripcionSinEspacios.equals("") && validarCoordenadas()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private Coordenada obtenerCoordenadas() {
        Coordenada coord = null;
        // Si no es null y no esta vacio
        if (txtLongitud.getText() != null && txtLatitud.getText() != null) {
            if (!(txtLongitud.getText()).toString().isEmpty() && !(txtLatitud.getText()).toString().isEmpty()) {
                String Longitud = txtLongitud.getText().toString();
                String Latitud = txtLatitud.getText().toString();
                coord = new Coordenada(Double.parseDouble(Latitud), Double.parseDouble(Longitud));
            }
        }
        return coord;
    }

    private boolean validarCoordenadas() {
        Coordenada coor = obtenerCoordenadas();
        if (coor != null) {
            // Coordenadas ingresadas
            Double LatitudIN = coor.getLatitud();
            Double LongitudIN = coor.getLongitud();

            // LIMITES DEL PARTIDO
            Double LatitudMin = 34.562054;     //Latitud maximo
            Double LatitudMax = 34.631361;     //Latitud minimo
            Double LongitudMax = 58.693561;    //Longitud maximo
            Double LongitudMin = 58.617442;    //Longitud minimo

            if (LatitudIN <= LatitudMax &&
                    LatitudIN >= LatitudMin &&
                    LongitudIN <= LongitudMax &&
                    LongitudIN >= LongitudMin) {
                // Entra si la coordenada esta dentro del limite
                return true;
            }
        }
        return false;
    }

}
