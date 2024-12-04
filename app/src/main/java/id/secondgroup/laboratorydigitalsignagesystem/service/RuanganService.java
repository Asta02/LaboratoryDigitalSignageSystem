package id.secondgroup.laboratorydigitalsignagesystem.service;

import java.util.List;

import id.secondgroup.laboratorydigitalsignagesystem.database.DosenResponse;
import id.secondgroup.laboratorydigitalsignagesystem.database.KelasResponse;
import id.secondgroup.laboratorydigitalsignagesystem.database.MatkulResponse;
import id.secondgroup.laboratorydigitalsignagesystem.database.NamaRuangan;
import id.secondgroup.laboratorydigitalsignagesystem.database.ProdiResponse;
import id.secondgroup.laboratorydigitalsignagesystem.database.Ruangan;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RuanganService {
    @GET("getNamaRuangan")
    Call<List<NamaRuangan>> getNamaRuangan();
    @GET("getRuanganByID")
    Call<Ruangan> getRuanganById(@Query("id") int id);
    @GET("getDosenByID")
    Call<DosenResponse> getDosen(@Query("id") int id);
    @GET("getOneProdi")
    Call<ProdiResponse> getProdi(@Query("id") int id);
    @GET("getOneKelas")
    Call<KelasResponse> getKelas(@Query("idProdi") Integer idProdi, @Query("id") String id);
//    @GET("getReguByID")
//    Call<Regu> getRegu(@Query("id") int id);
    @GET("getOneMatkul")
    Call<MatkulResponse> getMatkul(@Query("idProdi") Integer idProdi, @Query("id") Integer id);
}
