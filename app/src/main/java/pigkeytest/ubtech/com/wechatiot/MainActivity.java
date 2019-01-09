package pigkeytest.ubtech.com.wechatiot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tencent.wechat.Cloud;
import com.ubtech.messageparser.DataContent;
import com.ubtech.messageparser.MessageParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    String TAG="MainActivity";
    Button mSendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

       String testJson="{\"msg_id\":668377127,\"msg_type\":\"set\",\"services\":{\"operation_status\":{\"status\":1}},\"user\":\"oYd-ytwz-EYkcXPb1mo4DmCKaUBw\",\"test_create_time\":1547018303576,\"data\":\"� v \u0003\"}";
       String content=MessageParser.INSTANCE.parseMessage(testJson).getData();
       System.out.println(new String(content));
    }

    
}
