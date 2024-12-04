package id.secondgroup.laboratorydigitalsignagesystem.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NamaRuangan {
    @SerializedName("id_ruangan")
    @Expose
    private Integer id_ruangan;

    @SerializedName("nama_ruangan")
    @Expose
    private String nama_ruangan;

    @SerializedName("password")
    @Expose
    private String password;

    public NamaRuangan() {
        this.id_ruangan = null;
        this.nama_ruangan = null;
        this.password = null;
    }

    public NamaRuangan(Integer id_ruangan, String nama_ruangan, String password) {
        this.id_ruangan = id_ruangan;
        this.nama_ruangan = nama_ruangan;
        this.password = password;
    }

    public Integer getId_ruangan() {
        return id_ruangan;
    }

    public void setId_ruangan(Integer id_ruangan) {
        this.id_ruangan = id_ruangan;
    }

    public String getNama_ruangan() {
        return nama_ruangan;
    }

    public void setNama_ruangan(String nama_ruangan) {
        this.nama_ruangan = nama_ruangan;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
