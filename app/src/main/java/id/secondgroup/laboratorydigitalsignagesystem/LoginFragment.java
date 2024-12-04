package id.secondgroup.laboratorydigitalsignagesystem;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.view.WindowManager;
import android.net.Uri;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import id.secondgroup.laboratorydigitalsignagesystem.database.NamaRuangan;
import id.secondgroup.laboratorydigitalsignagesystem.database.Ruangan;


public class LoginFragment extends Fragment {
    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    private static final int REQUEST_WRITE_SETTINGS = 123;
    private EditText mTtxtUsername;
    private EditText mTxtPassword;
    private Button mBtnLogin;
    private Spinner mSpinnerLab;
    private DashboardViewModel mViewModel;
    private List<NamaRuangan> mRuangan;

    //private Repository mRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this)
                .get(DashboardViewModel.class);
        setImmersiveMode();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_login, container, false);

        mTxtPassword = (EditText) view.findViewById(R.id.txtPassword);
        mBtnLogin = (Button) view.findViewById(R.id.btnLogin);
        mSpinnerLab = (Spinner) view.findViewById(R.id.spinnerLab);

        // Declare the AtomicReference to hold the roomList
        AtomicReference<List<NamaRuangan>> roomListRef = new AtomicReference<>(new ArrayList<>());

        mViewModel.getNamaRuangan().observe(getViewLifecycleOwner(), rooms -> {
            if (rooms != null && !rooms.isEmpty()) {
                roomListRef.set(rooms);

                List<String> roomNamesList = new ArrayList<>();
                for (NamaRuangan roomName : rooms) {
                    roomNamesList.add(roomName.getNama_ruangan());
                }

                String[] options = roomNamesList.toArray(new String[0]);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, options);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                mSpinnerLab.setAdapter(adapter);
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected room from the spinner
                NamaRuangan selectedRoom = roomListRef.get().get(mSpinnerLab.getSelectedItemPosition());

                if (selectedRoom == null) {
                    Toast.makeText(requireContext(), "Pilih ruangan dahulu", Toast.LENGTH_SHORT).show();
                    return;
                }

                String enteredPassword = mTxtPassword.getText().toString().trim();
                String roomPassword = selectedRoom.getPassword();

                if (enteredPassword.isEmpty()) {
                    Toast.makeText(requireContext(), "Masukan password dahulu", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!enteredPassword.equals(roomPassword)) {
                    Toast.makeText(requireContext(), "Password tidak sesuai", Toast.LENGTH_SHORT).show();
                    return;
                }

                int roomId = selectedRoom.getId_ruangan();
                loginWithRoomId(roomId);
            }
        });

        return view;
    }


    // Create a separate method to handle login with the selected room ID
    private void loginWithRoomId(int roomId) {
        Fragment dashboardFragment = DashboardFragment.newInstance(roomId);

        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, dashboardFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_WRITE_SETTINGS) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.System.canWrite(getContext())) {
                // The user has granted the permission, proceed to set screen brightness
                setScreenBrightness(0.01f);
            } else {
                // The user denied the permission, handle this case accordingly
                // For example, you can show a message or disable the screen brightness feature
            }
        }
    }

    private void setScreenBrightness(float brightness) {
        Window window = getActivity().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.screenBrightness = brightness;
        window.setAttributes(layoutParams);

        // Save the brightness level in the system settings
        ContentResolver contentResolver = getContext().getContentResolver();
        Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, (int) (brightness * 255));
    }*/

    @Override
    public void onResume() {
        super.onResume();
        // Enable back button press
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
                }
        });
    }

    private void setImmersiveMode() {
        View decorView = requireActivity().getWindow().getDecorView();
        int flags = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(flags);
    }
}