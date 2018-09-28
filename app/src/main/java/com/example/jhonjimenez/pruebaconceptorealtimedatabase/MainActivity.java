package com.example.jhonjimenez.pruebaconceptorealtimedatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String PATH_PUNTOS = "puntos";
    public static final String PATH_PUNTO1 = "punto1";
    @BindView(R.id.textview)
    TextView textview;
    @BindView(R.id.edittext)
    EditText edittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        FirebaseDatabase objectFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference objectDatabaseRefence = objectFirebaseDatabase.getReference(PATH_PUNTOS).child(PATH_PUNTO1);
//        DatabaseReference objectDatabaseRefence = objectFirebaseDatabase.getReference(); -->Así no devolveria la raiz /

        //Así consultamos los datos de esa refencia
        objectDatabaseRefence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textview.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error al consultar los datos", Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
    }
}
