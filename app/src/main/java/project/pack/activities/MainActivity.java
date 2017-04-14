package project.pack.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.pack.R;

public class MainActivity extends AppCompatActivity {

    // Con ButterKnife Reemplazas      (Button) findViewById ->por     @Bind

    @Bind(R.id.Boton1)
    Button Boton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaIncidente = new Intent(MainActivity.this, CrearIncidenteActivity.class);
                startActivity(pantallaIncidente);
            }
        });
    }

    @OnClick(R.id.Boton1)
    public void onClick(View v) {
        if (Boton1.getVisibility() == View.VISIBLE) {
            Boton1.setVisibility(View.GONE);
        } else {
            Boton1.setVisibility(View.VISIBLE);
        }
    }
}