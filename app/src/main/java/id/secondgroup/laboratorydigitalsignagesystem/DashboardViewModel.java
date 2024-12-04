package id.secondgroup.laboratorydigitalsignagesystem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.secondgroup.laboratorydigitalsignagesystem.database.DosenResponse;
import id.secondgroup.laboratorydigitalsignagesystem.database.KelasResponse;
import id.secondgroup.laboratorydigitalsignagesystem.database.MatkulResponse;
import id.secondgroup.laboratorydigitalsignagesystem.database.NamaRuangan;
import id.secondgroup.laboratorydigitalsignagesystem.database.ProdiResponse;
import id.secondgroup.laboratorydigitalsignagesystem.database.Ruangan;
import id.secondgroup.laboratorydigitalsignagesystem.repository.RuanganRepository;

public class DashboardViewModel extends ViewModel {
    private MutableLiveData<Integer> mRuanganId;
    private RuanganRepository mRepository;
    private LiveData<Ruangan> mLiveData;

    public DashboardViewModel() {
        mRepository = RuanganRepository.get();
        mRuanganId = new MutableLiveData<Integer>();
        mLiveData = Transformations.switchMap(mRuanganId, room ->
                mRepository.getRuangan(room));
    }

    public void loadRoom (Integer id) {
        mRuanganId.setValue(id);
    }

    public LiveData<Ruangan> getRuangan() {
        return mLiveData;
    }

    public MutableLiveData<List<NamaRuangan>> getNamaRuangan() {
        return mRepository.getNamaRuangan();
    }

    public LiveData<KelasResponse> getKelas (Integer idProdi, String id) {
        return mRepository.getKelasResponse(idProdi, id);
    }

    public LiveData<ProdiResponse> getProdi(Integer id) {
        return mRepository.getProdiResponse(id);
    }

    public LiveData<MatkulResponse> getMatkul (Integer idProdi, Integer id) {
        return mRepository.getMatkulResponse(idProdi, id);
    }

//    public LiveData<Regu> getRegu (Integer id) {
//        return mRepository.getRegu(id);
//    }

    public LiveData<DosenResponse> getDosen (Integer id) {
        return mRepository.getDosenResponse(id);
    }
}
