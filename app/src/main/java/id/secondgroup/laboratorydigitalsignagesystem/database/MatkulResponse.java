package id.secondgroup.laboratorydigitalsignagesystem.database;

public class MatkulResponse {
    private Integer mku_id;
    private String mku_nama;

    public MatkulResponse(Integer mku_id, String mku_nama) {
        this.mku_id = mku_id;
        this.mku_nama = mku_nama;
    }

    public MatkulResponse() {
        this.mku_nama = "";
    }

    public Integer getMku_id() {
        return mku_id;
    }

    public void setMku_id(Integer mku_id) {
        this.mku_id = mku_id;
    }

    public String getMku_nama() {
        return mku_nama;
    }

    public void setMku_nama(String mku_nama) {
        this.mku_nama = mku_nama;
    }
}

