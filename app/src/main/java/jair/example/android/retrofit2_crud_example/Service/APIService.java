package jair.example.android.retrofit2_crud_example.Service;

import org.json.JSONObject;

import jair.example.android.retrofit2_crud_example.Alumno;
import jair.example.android.retrofit2_crud_example.JSONApi;

import jair.example.android.retrofit2_crud_example.JSONApi;
import jair.example.android.retrofit2_crud_example.JSONId;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Brian on 23/03/2017.
 */

public interface APIService {

    @GET("obtener_alumnos.php")
    Call<JSONApi> getUsers();


    @GET("obtener_alumno_por_id.php")
    Call<JSONId> findAlumno(@Query("idalumno") String id);

    @POST("insertar_alumno.php")
    Call<Alumno> insertAlumno(@Body Alumno alumno);

    @POST("actualizar_alumno.php")
    Call<Alumno> updateAlumno(@Body Alumno alumno);

    @POST("borrar_alumno.php")
    Call<Alumno> deleteAlumno(@Body Alumno alumno);





}
