package id.secondgroup.laboratorydigitalsignagesystem.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ruangan {
    @SerializedName("id_ruangan")
    @Expose
    private Integer id_ruangan;

    @SerializedName("nama_ruangan")
    @Expose
    private String nama_ruangan;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    @SerializedName("id_dosen1")
    @Expose
    private String id_dosen1;

    @SerializedName("id_dosen2")
    @Expose
    private String id_dosen2;

    @SerializedName("id_dosen3")
    @Expose
    private String id_dosen3;

    @SerializedName("id_prodi")
    @Expose
    private String id_prodi;

    @SerializedName("id_kelas")
    @Expose
    private String id_kelas;

    @SerializedName("id_regu")
    @Expose
    private String id_regu;

    @SerializedName("id_matkul")
    @Expose
    private String id_matkul;

    @SerializedName("password")
    @Expose
    private String password;

    private String icon;

    public Ruangan(Integer id_ruangan, String nama_ruangan, String keterangan, String id_dosen1, String id_dosen2, String id_dosen3, String id_prodi, String id_kelas, String id_regu, String id_matkul, String password) {
        this.id_ruangan = id_ruangan;
        this.nama_ruangan = nama_ruangan;
        this.keterangan = keterangan;
        this.id_dosen1 = id_dosen1;
        this.id_dosen2 = id_dosen2;
        this.id_dosen3 = id_dosen3;
        this.id_prodi = id_prodi;
        this.id_kelas = id_kelas;
        this.id_regu = id_regu;
        this.id_matkul = id_matkul;
        this.password = password;
    }

    public Ruangan(Integer id_ruangan, String nama_ruangan, String keterangan, String id_dosen1, String id_dosen2, String id_dosen3, String id_prodi, String id_kelas, String id_regu, String id_matkul, String password, String icon) {
        this.id_ruangan = id_ruangan;
        this.nama_ruangan = nama_ruangan;
        this.keterangan = keterangan;
        this.id_dosen1 = id_dosen1;
        this.id_dosen2 = id_dosen2;
        this.id_dosen3 = id_dosen3;
        this.id_prodi = id_prodi;
        this.id_kelas = id_kelas;
        this.id_regu = id_regu;
        this.id_matkul = id_matkul;
        this.password = password;
        this.icon = icon;
    }

    public Ruangan() {
        this.id_ruangan = null;
        this.nama_ruangan = null;
        this.keterangan = null;
        this.id_dosen1 = null;
        this.id_dosen2 = null;
        this.id_dosen3 = null;
        this.id_prodi = null;
        this.id_kelas = null;
        this.id_regu = null;
        this.id_matkul = null;
        this.password = null;
        this.icon = null;
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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getId_dosen1() {
        return id_dosen1;
    }

    public void setId_dosen1(String id_dosen1) {
        this.id_dosen1 = id_dosen1;
    }

    public String getId_dosen2() {
        return id_dosen2;
    }

    public void setId_dosen2(String id_dosen2) {
        this.id_dosen2 = id_dosen2;
    }

    public String getId_dosen3() {
        return id_dosen3;
    }

    public void setId_dosen3(String id_dosen3) {
        this.id_dosen3 = id_dosen3;
    }

    public String getId_prodi() {
        return id_prodi;
    }

    public void setId_prodi(String id_prodi) {
        this.id_prodi = id_prodi;
    }

    public String getId_kelas() {
        return id_kelas;
    }

    public void setId_kelas(String id_kelas) {
        this.id_kelas = id_kelas;
    }

    public String getId_regu() {
        return id_regu;
    }

    public void setId_regu(String id_regu) {
        this.id_regu = id_regu;
    }

    public String getId_matkul() {
        return id_matkul;
    }

    public void setId_matkul(String id_matkul) {
        this.id_matkul = id_matkul;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}