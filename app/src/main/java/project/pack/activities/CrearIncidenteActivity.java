package project.pack.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.pack.R;
import project.pack.facade.Facade;

public class CrearIncidenteActivity extends AppCompatActivity {

    @Bind(R.id.txtTitulo)
    TextView titulo;

    @Bind(R.id.txtDescripcion)
    TextView descripcion;

    @Bind(R.id.btnCrearIncidente)
    Button btnCrearIncidente;

    @Bind(R.id.btnLimpiarForm)
    Button btnLimpiarForm;

    @Bind(R.id.btnVolver)
    Button btnVolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_incidente);

        ButterKnife.bind(this);

        btnCrearIncidente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mostrarIncidente = new Intent(CrearIncidenteActivity.this, VerIncidenteActivity.class);
                Facade facade = new Facade();

                // se agrega x default 1 , cambiar en future.
                facade.crearIncidente(1,titulo.getText().toString(), descripcion.getText().toString(), Calendar.getInstance().getTime(), null, null);
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
}
