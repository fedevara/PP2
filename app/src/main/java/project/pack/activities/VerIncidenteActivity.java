package project.pack.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.pack.R;
import project.pack.controller.CacheSingleton;
import project.pack.domain.Incidente;

public class VerIncidenteActivity extends AppCompatActivity {

    @Bind(R.id.btnVolver)
    Button btnVolver;

    @Bind(R.id.lvResultado)
    ListView lvResultado;

    @Bind(R.id.twTitulo)
    TextView twTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_incidente);

        ButterKnife.bind(this);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent layautPaginaPrincipal = new Intent(VerIncidenteActivity.this, MainActivity.class);
                startActivity(layautPaginaPrincipal);
            }
        });
        cargarListaIncidentes();
    }

    private void cargarListaIncidentes() {

        List<Incidente> incidentes = CacheSingleton.getInstance().obtenerListaIncidentes();
        ArrayList<Incidente> listaIncidentes = new ArrayList<Incidente>();

        if (incidentes != null && incidentes.size() > 0) {
            for (int i = 0; i < incidentes.size(); i++) {
                Incidente incidente = incidentes.get(i);
                listaIncidentes.add(incidente);
            }
            ArrayAdapter<Incidente> adaptador = new ArrayAdapter<Incidente>(this, android.R.layout.simple_list_item_1, listaIncidentes);
            lvResultado.setAdapter(adaptador);

        } else {
            Toast.makeText(getApplicationContext(), "Usted no Cargó ningún Incidente.", Toast.LENGTH_LONG).show();
        }
    }
}
