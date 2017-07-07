package project.pack.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.pack.R;
import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.facade.Facade;

public class CrearIncidenteConDetectorDeCategoria extends AppCompatActivity {

    @Bind(R.id.txtLongitud)
    TextView txtLongitud;

    @Bind(R.id.txtLatitud)
    TextView txtLatitud;

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

    Facade facade = Facade.getInstance();

    boolean mostrarSpinner=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_incidente_con_detector_de_categoria);

        ButterKnife.bind(this);

        initSpinnerCategorias();
        linearLayoutCategoria.setVisibility(View.INVISIBLE);
        txtCategoria.setVisibility(View.INVISIBLE);
        spnCategorias.setVisibility(View.INVISIBLE);

        btnCrearIncidente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(true) {
//                if(validarCampos()) {

                    Categoria categoria = obtenerCategoria();

                    if(!mostrarSpinner){
                        try {
                            Coordenada coordenada = obtenerCoordenadas();
                            int categoriaId = categoria.getId();
                            facade.crearIncidente("incidente creado", etDescripcion.getText().toString(), Calendar.getInstance().getTime(), categoria, coordenada);
                            Intent mostrarIncidente = new Intent(CrearIncidenteConDetectorDeCategoria.this, VerIncidenteActivity.class);
                            startActivity(mostrarIncidente);

                        } catch (NullPointerException e) {
                            Toast.makeText(getApplicationContext(), "No se encontró la categoria", Toast.LENGTH_LONG).show();
                            mostrarSpinner = true;
                            initSpinnerCategorias();
                        }
                    }
                    else{
                        crearIncidentePorSpinner();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Faltan completar campos obligatorios(*)", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent layautPaginaPrincipal = new Intent(CrearIncidenteConDetectorDeCategoria.this, MainActivity.class);
                startActivity(layautPaginaPrincipal);
            }
        });
    }

    private void crearIncidentePorSpinner() {
        Categoria categoria = obtenerCategoriaSpinner();
        Coordenada coordenada = obtenerCoordenadas();
        facade.crearIncidente("incidente creado", etDescripcion.getText().toString(), Calendar.getInstance().getTime(), categoria, coordenada);
        Intent mostrarIncidente = new Intent(CrearIncidenteConDetectorDeCategoria.this, VerIncidenteActivity.class);
        startActivity(mostrarIncidente);
    }


    private void initSpinnerCategorias() {
        linearLayoutCategoria.setVisibility(View.VISIBLE);
        txtCategoria.setVisibility(View.VISIBLE);
        spnCategorias.setVisibility(View.VISIBLE);

        List<Categoria> categorias = facade.getCategorias();

        ArrayAdapter<Categoria> spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categorias);
        spnCategorias.setAdapter(spinner_adapter);
    }

    private Categoria obtenerCategoria(){
        String descripcion = etDescripcion.getText().toString();
        Categoria categoria  = facade.obtenerCagoriaPorDescripcion(descripcion);
        return categoria;
    }

    private Categoria obtenerCategoriaSpinner(){
        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria = (Categoria) spnCategorias.getSelectedItem();
        return nuevaCategoria;
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

    private boolean validarCampos() {
        System.out.println(new Coordenada((Math.random() * 3) + 1, (Math.random() * 3) + 1));
        if (etDescripcion.getText().toString() != null) {
            String descripcionSinEspacios = etDescripcion.getText().toString().trim();

            if (!descripcionSinEspacios.equals("") && validarCoordenadas()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
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
