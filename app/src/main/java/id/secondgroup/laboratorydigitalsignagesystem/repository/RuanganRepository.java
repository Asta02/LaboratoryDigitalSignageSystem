package id.secondgroup.laboratorydigitalsignagesystem.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.secondgroup.laboratorydigitalsignagesystem.api.ApiUtils;
import id.secondgroup.laboratorydigitalsignagesystem.database.DosenResponse;
import id.secondgroup.laboratorydigitalsignagesystem.database.KelasResponse;
import id.secondgroup.laboratorydigitalsignagesystem.database.MatkulResponse;
import id.secondgroup.laboratorydigitalsignagesystem.database.NamaRuangan;
import id.secondgroup.laboratorydigitalsignagesystem.database.ProdiResponse;
import id.secondgroup.laboratorydigitalsignagesystem.database.Ruangan;
import id.secondgroup.laboratorydigitalsignagesystem.database.Ruangan;
import id.secondgroup.laboratorydigitalsignagesystem.service.RuanganService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RuanganRepository {
    private static RuanganRepository INSTANCE;

    private final RuanganService mRuanganService;

    private RuanganRepository(Context context) {
        mRuanganService = ApiUtils.getRuanganService();
    }

    public static void initialize(Context context) {
        if (INSTANCE == null) INSTANCE = new RuanganRepository(context);
    }

    public static RuanganRepository get() {
        return INSTANCE;
    }

    public MutableLiveData<Ruangan> getRuangan(int ruanganId) {
        MutableLiveData<Ruangan> ruangan = new MutableLiveData<>();

        Call<Ruangan> call = mRuanganService.getRuanganById(ruanganId);
        call.enqueue(new Callback<Ruangan>() {
            @Override
            public void onResponse(@NonNull Call<Ruangan> call, @NonNull Response<Ruangan> response) {
                if (response.isSuccessful()) ruangan.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Ruangan> call, @NonNull Throwable t) {
                System.out.println("Error API call : " + t.getMessage());
            }
        });

        return ruangan;
    }
    public MutableLiveData<List<NamaRuangan>> getNamaRuangan() {
        MutableLiveData<List<NamaRuangan>> ruangan = new MutableLiveData<>();
        Call<List<NamaRuangan>> call = mRuanganService.getNamaRuangan();
        call.enqueue(new Callback<List<NamaRuangan>>() {
            @Override
            public void onResponse(@NonNull Call<List<NamaRuangan>> call, @NonNull Response<List<NamaRuangan>> response) {
                if (response.isSuccessful()) {
                    ruangan.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<NamaRuangan>> call, @NonNull Throwable t) {
                System.out.println("Error API call : " + t.getMessage());
            }
        });

        return ruangan;
    }
    public MutableLiveData<ProdiResponse> getProdiResponse(Integer id) {
        MutableLiveData<ProdiResponse> prodiResponse = new MutableLiveData<>();

        Call<ProdiResponse> call = mRuanganService.getProdi(id);
        call.enqueue(new Callback<ProdiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProdiResponse> call, @NonNull Response<ProdiResponse> response) {
                if (response.isSuccessful()) prodiResponse.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ProdiResponse> call, @NonNull Throwable t) {
                System.out.println("Error API call : " + t.getMessage());
            }
        });

        return prodiResponse;
    }

    public MutableLiveData<MatkulResponse> getMatkulResponse(Integer idProdi, Integer id) {
        MutableLiveData<MatkulResponse> matkulResponse = new MutableLiveData<>();

        Call<MatkulResponse> call = mRuanganService.getMatkul(idProdi, id);
        call.enqueue(new Callback<MatkulResponse>() {
            @Override
            public void onResponse(@NonNull Call<MatkulResponse> call, @NonNull Response<MatkulResponse> response) {
                if (response.isSuccessful()) matkulResponse.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MatkulResponse> call, @NonNull Throwable t) {
                System.out.println("Error API call : " + t.getMessage());
            }
        });

        return matkulResponse;
    }
    
    public MutableLiveData<KelasResponse> getKelasResponse(Integer idProdi, String id) {
        MutableLiveData<KelasResponse> kelasResponse = new MutableLiveData<>();

        Call<KelasResponse> call = mRuanganService.getKelas(idProdi, id);
        call.enqueue(new Callback<KelasResponse>() {
            @Override
            public void onResponse(@NonNull Call<KelasResponse> call, @NonNull Response<KelasResponse> response) {
                if (response.isSuccessful()) kelasResponse.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<KelasResponse> call, @NonNull Throwable t) {
                System.out.println("Error API call : " + t.getMessage());
            }
        });

        return kelasResponse;
    }

    public MutableLiveData<DosenResponse> getDosenResponse(Integer id) {
        MutableLiveData<DosenResponse> dosenResponse = new MutableLiveData<>();

        Call<DosenResponse> call = mRuanganService.getDosen(id);
        call.enqueue(new Callback<DosenResponse>() {
            @Override
            public void onResponse(@NonNull Call<DosenResponse> call, @NonNull Response<DosenResponse> response) {
                if (response.isSuccessful()) {
                    dosenResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<DosenResponse> call, @NonNull Throwable t) {
                System.out.println("Error API call : " + t.getMessage());
            }
        });

        return dosenResponse;
    }

//    public MutableLiveData<Regu> getRegu(Integer id) {
//        MutableLiveData<Regu> regu = new MutableLiveData<>();
//
//        Call<Regu> call = mRuanganService.getRegu(id);
//        call.enqueue(new Callback<Regu>() {
//            @Override
//            public void onResponse(@NonNull Call<Regu> call, @NonNull Response<Regu> response) {
//                if (response.isSuccessful()) regu.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Regu> call, @NonNull Throwable t) {
//                System.out.println("Error API call : " + t.getMessage());
//            }
//        });
//
//        return regu;
//    }
}
