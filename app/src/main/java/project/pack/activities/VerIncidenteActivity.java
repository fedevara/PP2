package project.pack.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.pack.R;
import project.pack.domain.Incidente;
import project.pack.facade.Facade;

public class VerIncidenteActivity extends AppCompatActivity {

    @Bind(R.id.txtTitulo)
    TextView titulo;

    @Bind(R.id.txtDescripcion)
    TextView descripcion;

    //este es un comentario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_incidente);

        ButterKnife.bind(this);

        recogerExtras();
    }

    public void recogerExtras() { //Aquí recogemos y tratamos los parámetros
        Facade facade = new Facade();
        Incidente incidente = facade.obtenerIncidente("ID1");
        titulo.setText(incidente.getTitulo());
        descripcion.setText(incidente.getDescripcion());
    }
}
