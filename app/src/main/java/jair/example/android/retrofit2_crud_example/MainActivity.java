package jair.example.android.retrofit2_crud_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jair.example.android.retrofit2_crud_example.Service.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<Alumno> alumnoList = new ArrayList<>();
    EditText nombre;
    EditText direccion,id;
    Button insertar,actualizar,delete,consulta;
    APIService service;

    RecyclerAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = (EditText) findViewById(R.id.nombre);
        id = (EditText) findViewById(R.id.id);
        direccion = (EditText) findViewById(R.id.direccion);
        insertar = (Button) findViewById(R.id.insertar);
        actualizar = (Button) findViewById(R.id.actualizar);
        delete= (Button) findViewById(R.id.delete);
        consulta = (Button) findViewById(R.id.consulta);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        //Conexion con el webservice
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jairbarzola.esy.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APIService.class);

        id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() > 0) {
                    consulta.setEnabled(true);
                } else {
                    consulta.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultaById();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    insert();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        loadData();

    }

    private void consultaById() {

        String yd = id.getText().toString();
        Call<JSONId> call = service.findAlumno(yd);
        call.enqueue(new Callback<JSONId>() {
            @Override
            public void onResponse(Call<JSONId> call, Response<JSONId> response) {
                JSONId result = response.body();
                if(result.getEstado() ==1){
                    nombre.setText(result.getAlumno().getNombre());
                    direccion.setText(result.getAlumno().getDireccion());
                }else{
                    Toast.makeText(getApplicationContext(),"Estado "+result.getEstado(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JSONId> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void deleteData() {
        String yd = id.getText().toString();
        Alumno alumno = new Alumno(yd);
        Call<Alumno> call= service.deleteAlumno(alumno);
        call.enqueue(new Callback<Alumno>() {
            @Override
            public void onResponse(Call<Alumno> call, Response<Alumno> response) {
                Toast.makeText(getApplicationContext(),"Codigo "+response.message(),Toast.LENGTH_SHORT).show();
                loadData();
            }

            @Override
            public void onFailure(Call<Alumno> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateData() {
        String yd = id.getText().toString();
        String name = nombre.getText().toString();
        String address = direccion.getText().toString();

        Alumno alumno = new Alumno(yd,name,address);
        Call<Alumno> call = service.updateAlumno(alumno);
        call.enqueue(new Callback<Alumno>() {
            @Override
            public void onResponse(Call<Alumno> call, Response<Alumno> response) {
                Toast.makeText(getApplicationContext(),"Codigo "+response.message(),Toast.LENGTH_SHORT).show();
                loadData();
            }

            @Override
            public void onFailure(Call<Alumno> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadData() {
        //Obtener alumnos
       Call<JSONApi> call = service.getUsers();
        call.enqueue(new Callback<JSONApi>() {
            @Override
            public void onResponse(Call<JSONApi> call, Response<JSONApi> response) {
                JSONApi result = response.body();

                if(result.getEstado() == 1){
                    alumnoList= result.getAlumnos();
                    adapter = new RecyclerAdapter(getApplicationContext(),alumnoList);
                    recyclerView.setAdapter(adapter);

                }else{
                    Toast.makeText(getApplicationContext(),"No hay registro de Alumnos",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<JSONApi> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void insert() throws JSONException {




        String name = nombre.getText().toString();
        String address = direccion.getText().toString();

        Alumno alumno = new Alumno(name,address);


        Call<Alumno> alumnoCall = service.insertAlumno(alumno);
        alumnoCall.enqueue(new Callback<Alumno>() {
            @Override
            public void onResponse(Call<Alumno> call, Response<Alumno> response) {
                Toast.makeText(getApplicationContext(),"Codigo "+response.message(),Toast.LENGTH_SHORT).show();
                loadData();
            }

            @Override
            public void onFailure(Call<Alumno> call, Throwable t) {

            }
        });
    }
}
