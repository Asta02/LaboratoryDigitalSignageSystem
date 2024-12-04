package id.secondgroup.laboratorydigitalsignagesystem;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import android.provider.Settings;
import android.view.WindowManager;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import id.secondgroup.laboratorydigitalsignagesystem.database.KelasResponse;
import id.secondgroup.laboratorydigitalsignagesystem.database.Ruangan;

public class DashboardFragment extends Fragment {
    private static final String TAG = "DashboardFragment";
    private Integer mId;
    private LineChart lineChart;
    private Ruangan mRuangan;
    private TextView mTextRuangan;
    private TextView mTextProdi;
    private TextView mTextKelas;
    private TextView mTextDosen1;
    private TextView mTextDosen2;
    private TextView mTextDosen3;
    private TextView mTextRegu;
    private TextView mTextMatkul;
    private TextView mTextKeterangan;
    private TextView mTextTemperature;
    private ImageView mImageDosen;
    private ProgressBar mProgressTemperature;
    private List<Float> temperatureValues = new ArrayList<>();
    private DashboardViewModel mViewModel;
    //private static final String MQTT_BROKER_URL = "tcp://broker.hivemq.com:1883";
    private static final String MQTT_BROKER_URL = "tcp://astratech.id:1883";
    private String topic, clientID;
    private MqttAndroidClient client;

    private int[] dosenIds;
    private int interval = 5000; // 5 seconds
    private int index = 0;
    private Handler handler = new Handler();

    public static DashboardFragment newInstance(Integer roomID) {
        return new DashboardFragment(roomID);
    }

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    public DashboardFragment() {

    }

    public DashboardFragment(Integer id) {
        mId = id;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this)
                .get(DashboardViewModel.class);
        mViewModel.loadRoom(mId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard, container, false);

        lineChart = view.findViewById(R.id.lineChart);

        mTextRuangan = view.findViewById(R.id.room_name);
        mTextKelas = view.findViewById(R.id.class_name);
        mTextDosen1 = view.findViewById(R.id.dosen1);
        mTextDosen2 = view.findViewById(R.id.dosen2);
        mTextDosen3 = view.findViewById(R.id.dosen3);
        mTextMatkul = view.findViewById(R.id.lecture_name);
        mTextProdi = view.findViewById(R.id.major_name);
        mTextRegu = view.findViewById(R.id.group_name);
        mTextKeterangan = view.findViewById(R.id.editText);
        mTextTemperature = view.findViewById(R.id.temperatureText);
        mProgressTemperature = view.findViewById(R.id.temperatureProgressBar);
        mImageDosen = view.findViewById(R.id.gambar_dosen);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel.getRuangan().observe(
                getViewLifecycleOwner(), room -> {
                    mRuangan = room;
                    mTextRuangan.setText(mRuangan.getNama_ruangan());
                    mTextKeterangan.setText(mRuangan.getKeterangan());
                    mViewModel.getKelas(Integer.parseInt(mRuangan.getId_prodi()), mRuangan.getId_kelas()).observe(
                            getViewLifecycleOwner(), attr -> {
                                mTextKelas.setText(attr.getKel_id().substring(attr.getKel_id().length() - 2));
                            }
                    );
                    mViewModel.getProdi(Integer.parseInt(mRuangan.getId_prodi())).observe(
                            getViewLifecycleOwner(), attr -> {
                                mTextProdi.setText(attr.getKon_nama());
                            }
                    );
                    mViewModel.getMatkul(Integer.parseInt(mRuangan.getId_prodi()), Integer.parseInt(mRuangan.getId_matkul())).observe(
                            getViewLifecycleOwner(), attr -> {
                                mTextMatkul.setText(attr.getMku_nama());
                            }
                    );
//                    mViewModel.getRegu(mRuangan.getIdRegu()).observe(
//                            getViewLifecycleOwner(), attr -> {
//                                mTextRegu.setText(attr.getNamaRegu());
//                            }
//                    );
                    mViewModel.getDosen(Integer.parseInt(mRuangan.getId_dosen1())).observe(
                            getViewLifecycleOwner(), attr -> {
                                mTextDosen1.setText(attr.getNama());
                            }
                    );
                    if (mRuangan.getId_dosen2() != null && !mRuangan.getId_dosen2().isEmpty()) {
                        mViewModel.getDosen(Integer.parseInt(mRuangan.getId_dosen2())).observe(
                                getViewLifecycleOwner(), attr -> {
                                    if (attr != null) {
                                        mTextDosen2.setText(attr.getNama());
                                    }
                                }
                        );
                    }
                    if (mRuangan.getId_dosen3() != null && !mRuangan.getId_dosen3().isEmpty()) {
                        mViewModel.getDosen(Integer.parseInt(mRuangan.getId_dosen3())).observe(
                                getViewLifecycleOwner(), attr -> {
                                    if (attr != null) {
                                        mTextDosen3.setText(attr.getNama());
                                    }
                                }
                        );
                    }

                    //array id dosen
                    if (mRuangan.getId_dosen2() != null && !mRuangan.getId_dosen2().isEmpty() &&
                            mRuangan.getId_dosen3() != null && !mRuangan.getId_dosen3().isEmpty()) {
                        dosenIds = new int[]{
                                Integer.parseInt(mRuangan.getId_dosen1()),
                                Integer.parseInt(mRuangan.getId_dosen2()),
                                Integer.parseInt(mRuangan.getId_dosen3())
                        };
                    } else if (mRuangan.getId_dosen2() != null && !mRuangan.getId_dosen2().isEmpty()) {
                        dosenIds = new int[]{
                                Integer.parseInt(mRuangan.getId_dosen1()),
                                Integer.parseInt(mRuangan.getId_dosen2())
                        };
                    } else {
                        dosenIds = new int[]{
                                Integer.parseInt(mRuangan.getId_dosen1())
                        };
                    }

                    init(mRuangan.getId_prodi());

                    requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), onBackPressedCallback);

                    // Start the periodic timer to cycle through dosenIds
                    handler.postDelayed(cycleDosenIdsTask, interval);
            }
        );
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Apakah ingin mengganti ruangan?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new LoginFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
            }
        });
        builder.create().show();
    }

    // Define a separate onBackPressedCallback to handle back button press
    private final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            showConfirmationDialog();
        }
    };

    private Runnable cycleDosenIdsTask = new Runnable() {
        @Override
        public void run() {
            int dosenId = dosenIds[index];
            long delay = index * interval;

            // Update the text appearance based on the index
            updateTextAppearance(index);

            // Load the image with the specified delay
            loadImageWithDelay(dosenId, delay, handler);

            // Increment the index for the next iteration
            index = (index + 1) % dosenIds.length;

            // Schedule the next iteration
            handler.postDelayed(this, interval);
        }
    };

    @Override
    public void onDestroyView() {
        removeObservers();
        super.onDestroyView();
    }

    private void removeObservers() {
        mViewModel.getRuangan().removeObservers(getViewLifecycleOwner());
        handler.removeCallbacks(cycleDosenIdsTask);
    }

    private void updateTextAppearance(int index) {
        if (index == 0) {
            mTextDosen1.setTextAppearance(R.style.IsiRuangCustomText);
            mTextDosen2.setTextAppearance(R.style.InfoRuangCustomText);
            mTextDosen3.setTextAppearance(R.style.InfoRuangCustomText);
        } else if (index == 1) {
            mTextDosen1.setTextAppearance(R.style.InfoRuangCustomText);
            mTextDosen2.setTextAppearance(R.style.InfoRuangCustomText);
            mTextDosen3.setTextAppearance(R.style.IsiRuangCustomText);
        } else if (index == 2) {
            mTextDosen1.setTextAppearance(R.style.InfoRuangCustomText);
            mTextDosen2.setTextAppearance(R.style.IsiRuangCustomText);
            mTextDosen3.setTextAppearance(R.style.InfoRuangCustomText);
        }
    }

    private void loadImageWithDelay(int dosenId, long delay, Handler handler) {
        handler.postDelayed(() -> {
            mViewModel.getDosen(dosenId).observe(getViewLifecycleOwner(), attr -> {
                // Load the new image into the ImageView using Glide
                Glide.with(requireContext())
                        .load("https://sidos.polytechnic.astra.ac.id/Files/" + attr.getDos_foto())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(mImageDosen);
            });
        }, delay);
    }

    //set data lineChart
    private void setData() {
        ArrayList<Entry> entries = new ArrayList<>();

        int maxEntries = Math.min(8, temperatureValues.size());

        for (int i = 0; i < maxEntries; i++) {
            entries.add(new Entry(i, temperatureValues.get(i)));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Suhu");

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    private void init(String prodiId) {
        clientID = UUID.randomUUID().toString();
        topic =  prodiId  + "/prodi";
        client = new MqttAndroidClient(getContext(), MQTT_BROKER_URL, clientID);
        connect();
    }


    private void connect() {
        try {
            MqttConnectOptions options = new MqttConnectOptions(); // Use null for options
            options.setCleanSession(true);
//            IMqttToken token = client.connect(options);
            IMqttToken token = client.connect();
//            IMqttToken token = client.connect(options);

            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Log.d(TAG, "onSuccess");
                    sub();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d(TAG, "onFailure");
                }
            });
        } catch (MqttException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private void sub() {
        try {
            client.subscribe(mId + "/ruangan", 1);
            client.subscribe(mId + "/prodi", 1);
            client.subscribe(mId + "/matkul", 1);
            client.subscribe(mId + "/kelas", 1);
            //client.subscribe(mId + "/regu", 0);
            client.subscribe(mId + "/dosen1", 1);
            client.subscribe(mId + "/dosen2", 1);
            client.subscribe(mId + "/dosen3", 1);
            client.subscribe(mId + "/keterangan", 1);
            client.subscribe("/B0488D86E694/temperature", 1);
            client.subscribe("/B0488D86E694/humidity", 1);
            client.subscribe("/B0488D86E694/light", 1);
            client.subscribe("/B0488D86E694/power", 1);
            client.subscribe("/B0488D86E694/energy", 1);
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Log.e(TAG, "Connection Lost.");
                    Log.e(TAG, "Attempting to reconnect");
                    connect();
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("From topic: " + topic + ", you got message: " + message);

                    if (topic.contains("prodi")) {
                        mViewModel.getRuangan().observe(
                                getViewLifecycleOwner(), room -> {
                                    mRuangan = room;
                                    mTextProdi.setText(mRuangan.getId_prodi());
                                    mViewModel.getProdi(Integer.parseInt(message.toString())).observe(
                                            getViewLifecycleOwner(), attr -> {
                                                mTextProdi.setText(attr.getKon_nama());
                                            }
                                    );
                                }
                        );
                    }
                    else if (topic.contains("matkul")) {
                        mViewModel.getRuangan().observe(
                                getViewLifecycleOwner(), room -> {
                                    mRuangan = room;
                                    mTextMatkul.setText(mRuangan.getId_matkul());
                                    mViewModel.getMatkul(Integer.parseInt(mRuangan.getId_prodi()), Integer.parseInt(message.toString())).observe(
                                            getViewLifecycleOwner(), attr -> {
                                                mTextMatkul.setText(attr.getMku_nama());
                                            }
                                    );
                                }
                        );
                    }
                    else if (topic.contains("dosen1")) {
                        mViewModel.getRuangan().observe(
                                getViewLifecycleOwner(), room -> {
                                    mRuangan = room;
                                    mTextDosen1.setText(mRuangan.getId_dosen1());
                                    mViewModel.getDosen(Integer.parseInt(mRuangan.getId_dosen1())).observe(
                                            getViewLifecycleOwner(), attr -> {
                                                mTextDosen1.setText(attr.getNama());
                                            }
                                    );
                                }
                        );
                    }
                    else if (topic.contains("dosen2")) {
                        mViewModel.getRuangan().observe(
                                getViewLifecycleOwner(), room -> {
                                    mRuangan = room;
                                    mTextDosen2.setText(mRuangan.getId_dosen2());
                                    mViewModel.getDosen(Integer.parseInt(mRuangan.getId_dosen2())).observe(
                                            getViewLifecycleOwner(), attr -> {
                                                mTextDosen2.setText(attr.getNama());
                                            }
                                    );
                                }
                        );
                    }
                    else if (topic.contains("dosen3")) {
                        mViewModel.getRuangan().observe(
                                getViewLifecycleOwner(), room -> {
                                    mRuangan = room;
                                    mTextDosen3.setText(mRuangan.getId_dosen3());
                                    mViewModel.getDosen(Integer.parseInt(mRuangan.getId_dosen3())).observe(
                                            getViewLifecycleOwner(), attr -> {
                                                mTextDosen3.setText(attr.getNama());
                                            }
                                    );
                                }
                        );
                    }
                    else if (topic.contains("temperature")) {
                        mTextTemperature.setText(message.toString() + "°C");

                        temperatureValues.add(Float.valueOf(message.toString()));
                        //System.out.println("nilai float" + temperatureValues);

                        float temperatureValue = Float.valueOf(message.toString());
                        int temperatureValueInt = Math.round(temperatureValue);
                        //System.out.println("Nilai temp" + temperatureValueInt);
                        mProgressTemperature.setProgress(temperatureValueInt);

                        setData();
                    }
                    else if (topic.contains("kelas")) {
                        // Assuming message contains a number, we'll extract the last two characters.
                        String lastTwoChars = message.toString().substring(Math.max(0, message.toString().length() - 2));
                        mTextKelas.setText(lastTwoChars);
                    }
                    else if (topic.contains("ruangan")) {
                        mTextRuangan.setText(message.toString());
                    }
                    else if (topic.contains("keterangan")) {
                        mTextKeterangan.setText(message.toString());
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Log.i(TAG, "Delivery Completed.");
                }
            });
        } catch (MqttException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
