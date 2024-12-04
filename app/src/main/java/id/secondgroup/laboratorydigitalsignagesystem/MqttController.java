//package id.secondgroup.laboratorydigitalsignagesystem;
//
//import android.util.Log;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import org.eclipse.paho.android.service.MqttAndroidClient;
//import org.eclipse.paho.client.mqttv3.IMqttActionListener;
//import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
//import org.eclipse.paho.client.mqttv3.IMqttToken;
//import org.eclipse.paho.client.mqttv3.MqttCallback;
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//
//public class MqttController extends AppCompatActivity {
//    //private static final String MQTT_BROKER_URL = "tcp://broker.hivemq.com:1883";
//    private static final String MQTT_BROKER_URL = "tcp://astratech.id:1883";
//    private String topic, clientID;
//    private MqttAndroidClient client;
//
//    public MqttController() {
//        try {
//            client = new MqttClient(MQTT_BROKER_URL, clientId);
//            client.setCallback(this);
//
//            MqttConnectOptions options = new MqttConnectOptions();
//            options.setCleanSession(true);
//
//            client.connect(options);
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void connect() {
//        try {
//            MqttConnectOptions options = new MqttConnectOptions(); // Use null for options
//            options.setCleanSession(true);
////            IMqttToken token = client.connect(options);
//            options.setUserName(MQTT_USERNAME);
//            options.setPassword(MQTT_PASSWORD.toCharArray());
//            IMqttToken token = client.connect();
////            IMqttToken token = client.connect(options);
//
//            token.setActionCallback(new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    // We are connected
//                    Log.d(TAG, "onSuccess");
//                    sub();
//                    topicText.setText("On broker: " + MQTT_BROKER_URL);
//                    messageText.setText("On topic: " + topic);
//                }
//
//                @Override
//                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                    // Something went wrong e.g. connection timeout or firewall problems
//                    Log.d(TAG, "onFailure");
//                }
//            });
//        } catch (MqttException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void sub() {
//        try {
//            client.subscribe(topic, 0);
//            client.setCallback(new MqttCallback() {
//                @Override
//                public void connectionLost(Throwable cause) {
//                    Log.e(TAG, "Connection Lost.");
//                }
//
//                @Override
//                public void messageArrived(String topic, MqttMessage message) throws Exception {
//                    topicText.setText(topic);
//                    messageText.setText(new String(message.getPayload()));
//                }
//
//                @Override
//                public void deliveryComplete(IMqttDeliveryToken token) {
//                    Log.i(TAG, "Delivery Completed.");
//                }
//            });
//        } catch (MqttException e) {
//            Log.e(TAG, e.getMessage());
//        }
//    }
//}
