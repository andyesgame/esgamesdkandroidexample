
# ESGame Android SDK !

This document demonstrades ESGame SDK for android.


# Prequiresites

- Android studio
- ESGame client id
- ESGame client secret
- Google service json file
- Facebook id and facebook client token
- Appsflyer development key
# Functionality
SDK provide some functionality below:
- ESGame normal login: use email/account and password
- Fast login : use deviceId 
- Sign-in with Apple
- Log-in with Facebook
- Log-in with Google
- Google in-app billing
- Third-party payment (should only use with non Google version)
- Analystic ( Firebase, Facebook, Appslyer)

# SDK Config

## string.xml
ESGame has few parameters
|Property               |Description                          |Provider                         |
|----------------|-------------------------------|-----------------------------|
|app_flyer_dev_key|`Appsflyer development key`            |ESGame|
|es_client_id|`Esgame client id`            |ESGame|
|es_client_secret|`Esgame client secret`            |ESGame|
|gg_client_id|`Google client id, we can get it from google json file with client_type 3`            |ESGame|
|facebook_app_id|`Facebook app id`            |ESGame|
|facebook_client_token|`Facebook client token`            |ESGame|
|fb_login_protocol_scheme|`Facebook protocol scheme (format fb+facebook_app_id, for example fb11111111111 )`            |ESGame|

Open esgameparameters.xml and edit it , then run application.
# AndroidManifest.xml
You need modify some attributes in androidmanifest.xml

    <data android:scheme="schema" android:host="payment" android:pathPattern=".*"/>
  
  change schema and host ( we need this setting to open app from browser when user want to purchase-third party payment ).
  

    <meta-data android:name="AF_STORE" android:value="apk"/>  
<meta-data android:name="CHANNEL" android:value="apk"/>

Change two meta-data value. It's appsflyer's meta-data setting (only for third-party store).


# Intergration

- Copy esgameparameters.xml to your project
- Merge proguard-rules.pro to your project.
- Import google-service.json to your project.
- Merge AndroidManifest.xml to your project.
- Merge gradle.build to your project.

## Coding
Init sdk:

    protected void onCreate(Bundle var1) {  
    this.requestWindowFeature(1);  
	 super.onCreate(var1);  
	 ...
	 ESGameSDK.init(this, this);  
	 ESGameSDK.getInstance().handleIntent(getIntent());  
	 ...
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

Implement ESGame's callback:

> void onLoginSuccess(String message, int code, User user)

Login success callback

|Property               |Type                          |Description                         |
|----------------|-------------------------------|-----------------------------|
|message|String            |message from server|
|code|Integer            |response code from server|
|user|User            |User information|

> void onLoginFailure(String message, int code)

Log-in failure callback
|Property               |Type                          |Description                         |
|----------------|-------------------------------|-----------------------------|
|message|String            |message of error from server|
|code|Integer            |error code from server|

> void onLogout()

Log-out callback

> void onGGBillingResult(boolean success, String sku, String orderId)

Google billing  callback
|Property               |Type                          |Description                         |
|----------------|-------------------------------|-----------------------------|
|success|Boolean            |Transaction success or not|
|sku|String            |product's identifier|
|orderId|String            |Order's identifier|

> void onWebBillingResult(String itemId, int price)

Thirt-party payment  callback
|Property               |Type                          |Description                         |
|----------------|-------------------------------|-----------------------------|
|itemId|String            |product's identifier|
|price|Integer            |Product's price|

> void onUserInfoChange(User user)

User's information  callback
|Property               |Type                          |Description                         |
|----------------|-------------------------------|-----------------------------|
|itemId|String            |product's identifier|
|price|Integer            |Product's price|


## Command

ESGame'SDK provide some methods:

- ESGameSDK.getInstance().login()
ESGame will open Login view if user was not login in the past, or let user login.
- ESGameSDK.getInstance().logout()
Let user log-out.
- ESGameSDK.getInstance().inAppBillingWithSkuType(productID, sku_type ,server_id, player_id,extra_data);
Google billing payment method.

|Property               |Type                          |Description                         |
|----------------|-------------------------------|-----------------------------|
|productID|String            |product's identifier|
|sku_type|BillingClient.SkuType            |Product's type|
|server_id|String            |Server' identifier|
|player_id|String            |ESGame Player's identifier|
|extra_data|String            |ESGame Transaction's information|

- ESGameSDK.getInstance().inAppBillingWeb(productID ,server_id, player_id,extra_data);
Web payment method.

|Property               |Type                          |Description                         |
|----------------|-------------------------------|-----------------------------|
|productID|String            |product's identifier|
|server_id|String            |Server' identifier|
|player_id|String            |ESGame Player's identifier|
|extra_data|String            |ESGame Transaction's information|
```
  
