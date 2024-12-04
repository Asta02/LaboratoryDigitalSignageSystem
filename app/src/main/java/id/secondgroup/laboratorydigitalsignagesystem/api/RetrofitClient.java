package id.secondgroup.laboratorydigitalsignagesystem.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit sRetrofit = null;

    public static Retrofit getClient(String url) {
        Gson gson = new GsonBuilder()
                .setLenient().create();

        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return sRetrofit;
    }
}
