package project.pack.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.pack.R;
import project.pack.facade.Facade;

public class MainActivity extends AppCompatActivity {

    // Con ButterKnife Reemplazas      (Button) findViewById ->por   @Bind

    @Bind(R.id.btnAgragarIncidente)
    Button btnAgragarIncidente;

    @Bind(R.id.btnEliminarCache)
    Button btnEliminarCache;

    Facade facade = Facade.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        facade.initProperties(this);
        ButterKnife.bind(this);

        btnAgragarIncidente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaIncidente = new Intent(MainActivity.this, CrearIncidenteActivity.class);
                startActivity(pantallaIncidente);
            }
        });

        btnEliminarCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Elimino la cache.
                facade.eliminarCache();
                Toast.makeText(getApplicationContext(), "La cache se elimino correctamente", Toast.LENGTH_LONG).show();
            }
        });

    }

    @OnClick(R.id.btnAgragarIncidente)
    public void onClick(View v) {
        if (btnAgragarIncidente.getVisibility() == View.VISIBLE) {
            btnAgragarIncidente.setVisibility(View.GONE);
        } else {
            btnAgragarIncidente.setVisibility(View.VISIBLE);
        }
    }
}