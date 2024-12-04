package id.secondgroup.laboratorydigitalsignagesystem.database;

public class KelasResponse {
    private String kel_id;
    private String kel_tahun_ajaran;
    private Integer kel_tingkat;

    public KelasResponse(String kel_id, String kel_tahun_ajaran, Integer kel_tingkat) {
        this.kel_id = kel_id;
        this.kel_tahun_ajaran = kel_tahun_ajaran;
        this.kel_tingkat = kel_tingkat;
    }

    public KelasResponse() {
    }

    public String getKel_id() {
        return kel_id;
    }

    public void setKel_id(String kel_id) {
        this.kel_id = kel_id;
    }

    public String getKel_tahun_ajaran() {
        return kel_tahun_ajaran;
    }

    public void setKel_tahun_ajaran(String kel_tahun_ajaran) {
        this.kel_tahun_ajaran = kel_tahun_ajaran;
    }

    public Integer getKel_tingkat() {
        return kel_tingkat;
    }

    public void setKel_tingkat(Integer kel_tingkat) {
        this.kel_tingkat = kel_tingkat;
    }
}
