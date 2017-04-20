package project.pack.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.pack.R;
import project.pack.controller.CacheSingleton;
import project.pack.domain.Incidente;
import project.pack.facade.Facade;

public class VerIncidenteActivity extends AppCompatActivity {


    @Bind(R.id.btnVolver)
    Button btnVolver;

    @Bind(R.id.lvResultado)
    ListView lvResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_incidente);

        ButterKnife.bind(this);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//        recogerExtras();
        cargarListaIncidentes();
    }

    private void cargarListaIncidentes() {

        List<Incidente> incidentes = CacheSingleton.getInstance().obtenerListaIncidentes();
        ArrayList<String> listaIncidentes = new ArrayList<String>();

        for (int i = 0; i < incidentes.size(); i++) {
            Incidente incidente = incidentes.get(i);
            listaIncidentes.add(incidente.getTitulo() + " - " + incidente.getDescripcion());
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaIncidentes);
        lvResultado.setAdapter(adaptador);
    }

/*
    public void recogerExtras() { //Aquí recogemos y tratamos los parámetros
        Facade facade = new Facade();
        Incidente incidente = facade.obtenerIncidente(1);
        titulo.setText(incidente.getTitulo());
        descripcion.setText(incidente.getDescripcion());
    }*/
}
