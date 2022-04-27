package com.example.sdkexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.billingclient.api.BillingClient;

import java.util.UUID;

import vn.esgame.sdk.ESGameCallback;
import vn.esgame.sdk.ESGameSDK;
import vn.esgame.sdk.model.User;
import vn.esgame.sdk.util.NSFacebookLogin;
import vn.esgame.sdk.util.NSGoogleLogin;

public class MainActivity extends Activity implements ESGameCallback {
    ConstraintLayout parent;
    Button login;
    Button btnBilling;
    Button btnBillingWeb;
    Button btnLogout;
    TextView tvStatus;
    private String msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("feterere", "fb " + getString(R.string.facebook_app_id));
        Log.e("feterere", "fb " + getString(R.string.fb_login_protocol_scheme));
        MainActivity.this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE);
        parent = findViewById(R.id.parent);
        btnBilling = findViewById(R.id.btnBilling);
        btnBillingWeb = findViewById(R.id.btnBillingWeb);
        btnLogout = findViewById(R.id.btnLogout);
        login = findViewById(R.id.login);
        tvStatus = findViewById(R.id.tvStatus);
        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ESGameSDK.getInstance().handleIntent(intent);
    }

    String productID = "vn.esgame.bltt_gp_zhichonglibao2_00229";
    String productIDWeb = "vn.esgame.bltt_web_gold_00020";
    String serverId = "1";

    private void initView() {
        ESGameSDK.init(this, this);
        ESGameSDK.getInstance().setSandBox(true);
        ESGameSDK.getInstance().handleIntent(getIntent());

        btnBilling.setOnClickListener(view -> {
                    // ESGameSDK.getInstance().showWebSupportPage();
//            ESGameSDK.getInstance().inAppBilling(productID,"test", "1234","extra_data");
                    ESGameSDK.getInstance().inAppBillingWithSkuType(productID, BillingClient.SkuType.INAPP, serverId, "1234", "extra_data");
//            ESGameSDK.getInstance().inAppBilling(productID, "test", "1234","extra_data","{}");

                }
        );

        btnBillingWeb.setOnClickListener(view -> ESGameSDK.getInstance().inAppBillingWeb(productIDWeb, serverId, "1234", UUID.randomUUID().toString()));

        login.setOnClickListener(view -> ESGameSDK.getInstance().login());

        btnLogout.setOnClickListener(view -> ESGameSDK.getInstance().logout());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ESGameSDK.getInstance().onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ESGameSDK.getInstance().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onLoginSuccess(String message, int code, User user) {
        long userId = user.getId();
        String userName = user.getName();
        String displayName = user.getFull_name();
        log("User Login success " + userId + " " + userName);
//        ESGameSDK.getInstance().openGiftCodeForm("1","1");
    }

    @Override
    public void onLoginFailure(String message, int code) {
        Log.d("Demo", "onLoginFailure message " + message + " code " + code);
        log("User Login failed " + message);
    }

    @Override
    public void onLogout() {
        Toast.makeText(this, "User Logout ", Toast.LENGTH_SHORT);
        log(" User not login");
    }

    @Override
    public void onGGBillingResult(boolean success, String sku, String orderId) {
        Log.d("Demo", "onGGBillingResult message " + success + " code " + orderId);
        log("onGGBillingResult " + success + " orderId " + orderId);
    }

    @Override
    public void onWebBillingResult(String itemId, int price) {
        log("onWebBillingResult itemId " + itemId + " price ");
    }

    @Override
    public void onUserInfoChange(User user) {
        log("User info change:" + user.getName());
    }

    private void log(String msg) {
        this.msg += msg + "\n";
        tvStatus.setText(msg);
    }
}
