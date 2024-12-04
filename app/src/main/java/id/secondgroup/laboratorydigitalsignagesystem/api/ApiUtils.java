package id.secondgroup.laboratorydigitalsignagesystem.api;

import id.secondgroup.laboratorydigitalsignagesystem.service.RuanganService;

public class ApiUtils {
    public static final String API_URL = "http://10.8.2.175:8080/";

    public ApiUtils() {
    }

    public static RuanganService getRuanganService() {
        return RetrofitClient.getClient(API_URL).create(RuanganService.class);
    }
}
