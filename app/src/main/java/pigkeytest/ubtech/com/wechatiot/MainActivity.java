package pigkeytest.ubtech.com.wechatiot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tencent.wechat.Cloud;
import com.ubtech.messageparser.MessageEventBus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String TAG="MainActivity";
    Button mSendButton;
    TextView mReceiverWechatContent;

    public MainActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mReceiverWechatContent=findViewById(R.id.tv_show);
        mSendButton=findViewById(R.id.button2);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String body="{\n" +
                        "  \"device_type\": \"gh_6064295bfad2\",\n" +
                        "  \"device_id\": \"gh_6064295bfad2_d11fafd815c759ba\",\n" +
                        "  \"msg_type\": \"notify\",\n" +
                        "  \"data\":\"hello world\",\n" +
                        "  \"services\": {\n" +
                        "    \"operation_status\": {\n" +
                        "      \"status\": 1\n" +
                        "    }\n" +
                        "  }\n" +
                        "}";
                int taskid=Cloud.sendDataToServer(Cloud.WECHAT_CLOUD_SERVICE ,body);
                Log.d(TAG,"taskid is "+taskid);
                Log.d(TAG,"deviceID is "+Cloud.getDeviceId());
            }
        });
        String testdevlicense="925651DD6A039BF5FA3153FC5C5380C2682E74329691EC964F715712856ADBE3339EB682B24A1349DB297DB981F8FBD9B40A5025F84A1D522C0517ABDDADBB8543C48C8BEAC07AB407CF0BF0FE47BA18";
        if((Cloud.init(testdevlicense))){
            Log.d(TAG,"sdk is running ");
        }else {
            Log.d(TAG,"Device license is error");
        }

        int taskid=Cloud.sendDataToServer(1 ,"hello world");
        Log.d(TAG,"taskid is "+taskid);
        Log.d(TAG,"deviceID is "+Cloud.getDeviceId());
        Log.d(TAG,"SDK VERSION IS "+Cloud.getSDKVersion());

     //  String testJson="{\"msg_id\":668377127,\"msg_type\":\"set\",\"services\":{\"operation_status\":{\"status\":1}},\"user\":\"oYd-ytwz-EYkcXPb1mo4DmCKaUBw\",\"test_create_time\":1547018303576,\"data\":\"� v \u0003\"}";
      // String content=MessageParser.INSTANCE.parseMessage(testJson).getData();
     //  System.out.println(new String(content));
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(final MessageEventBus event) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mReceiverWechatContent.setText(event.getMessageContent());
            }
        });
        Log.d(TAG,"jsonobject  "+event.getMessageId());
        try {
            JSONObject mMessage = new JSONObject();
            mMessage.put("asy_error_code", 0);
            mMessage.put("asy_error_msg","ok");
            mMessage.put("msg_id",event.getMessageId());
            mMessage.put("msg_type","set");
            JSONObject mStatus = new JSONObject();
            mStatus.put("status", 1);
            JSONObject mOperation_status = new JSONObject();
            mOperation_status.put("operation_status",mStatus);
            mMessage.put("services",mOperation_status);
            Log.d(TAG,"jsonobject  "+mMessage.toString());
            sendMessage(mMessage.toString());
        }catch(JSONException e){
            e.printStackTrace();
        }


    };

    private void sendMessage(String body){
        int taskid=Cloud.sendDataToServer(Cloud.WECHAT_CLOUD_SERVICE ,body);
        Log.d(TAG,"sendMessage taskid is "+taskid);
    }

}
