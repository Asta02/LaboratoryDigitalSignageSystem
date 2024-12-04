package id.secondgroup.laboratorydigitalsignagesystem.database;

public class DosenResponse {
    private Integer kry_id;
    private String kry_username;
    private String nama;
    private String dos_foto;

    public Integer getKry_id() {
        return kry_id;
    }

    public void setKry_id(Integer kry_id) {
        this.kry_id = kry_id;
    }

    public String getKry_username() {
        return kry_username;
    }

    public void setKry_username(String kry_username) {
        this.kry_username = kry_username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDos_foto() {
        return dos_foto;
    }

    public void setDos_foto(String dos_foto) {
        this.dos_foto = dos_foto;
    }

    public DosenResponse(Integer kry_id, String kry_username, String nama, String dos_foto) {
        this.kry_id = kry_id;
        this.kry_username = kry_username;
        this.nama = nama;
        this.dos_foto = dos_foto;
    }

    public DosenResponse() {
        this.kry_username = "";
        this.nama = "";
        this.dos_foto = "";
    }
}
