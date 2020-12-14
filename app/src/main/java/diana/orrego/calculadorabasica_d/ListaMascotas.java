
package diana.orrego.calculadorabasica_d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaMascotas extends AppCompatActivity {

    DatabaseReference mDatabaseReference;
    ListView ltsMascotas;
    JSONArray datosJSONArray = new JSONArray();
    JSONObject datosJSONObject;
    MyFirebaseInstanceIdService myFirebaseInstanceIdService = new MyFirebaseInstanceIdService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascotas);

        ltsMascotas = findViewById(R.id.ltsMascotas);
        mostrarListadoMascotas();

        ltsMascotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("user", datosJSONArray.getJSONObject(position).getString("user"));
                    bundle.putString("to", datosJSONArray.getJSONObject(position).getString("to"));
                    bundle.putString("from", datosJSONArray.getJSONObject(position).getString("from"));
                    bundle.putString("urlFoto", datosJSONArray.getJSONObject(position).getString("urlFoto"));
                    bundle.putString("urlFotoFirestore", datosJSONArray.getJSONObject(position).getString("urlFotoFirestore"));

                    Intent intent = new Intent(getApplicationContext(), Chats.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), "Error al seleccionar el usuario a chatear: "+ ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void mostrarListadoMascotas(){
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("usuarios");
        mDatabaseReference.orderByChild("token").equalTo(myFirebaseInstanceIdService.miToken).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    if( snapshot.getChildrenCount()<=0 ){
                        registrarUsuario();
                        finish();
                    }
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), "Error al saber si estoy registrado: "+ ex.getMessage(), Toast.LENGTH_LONG).show();
                    registrarUsuario();
                    finish();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    ArrayList<Mascotas> stringArrayList = new ArrayList<Mascotas>();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Mascotas user = dataSnapshot.getValue(Mascotas.class);
                        stringArrayList.add(user);

                        datosJSONObject = new JSONObject();
                        datosJSONObject.put("user", user.getUserName());
                        datosJSONObject.put("to", user.getToken());
                        datosJSONObject.put("from", myFirebaseInstanceIdService.miToken);
                        datosJSONObject.put("urlFoto", user.getUrlFoto());
                        datosJSONObject.put("urlFotoFirestore", user.getUrlFotoFirestore());
                        datosJSONArray.put(datosJSONObject);
                    }
                    adaptadorImagenes adaptadorImg = new adaptadorImagenes(getApplicationContext(), stringArrayList);
                    ltsMascotas.setAdapter(adaptadorImg);
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), "Error al recuperar los donantes: "+ ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }////////////REVISAR AQUIIIII la class
    private void registrarUsuario(){
        Intent intent = new Intent(getApplicationContext(), MascotaActivity.class);
        startActivity(intent);
    }
}