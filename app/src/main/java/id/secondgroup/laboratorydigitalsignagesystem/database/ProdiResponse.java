package id.secondgroup.laboratorydigitalsignagesystem.database;

public class ProdiResponse {
    private Integer kon_id;
    private String kon_nama;

    public ProdiResponse(Integer kon_id, String kon_nama) {
        this.kon_id = kon_id;
        this.kon_nama = kon_nama;
    }

    public ProdiResponse() {
    }

    public Integer getKon_id() {
        return kon_id;
    }

    public void setKon_id(Integer kon_id) {
        this.kon_id = kon_id;
    }

    public String getKon_nama() {
        return kon_nama;
    }

    public void setKon_nama(String kon_nama) {
        this.kon_nama = kon_nama;
    }
}
