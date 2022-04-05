# ESGame SDK Android!

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
- Apple in-app purchase
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
|gg_client_id|`Google client id, we can get it from google json file`            |ESGame|
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

Change two meta-data value. It's appsflyer's meta-data setting.


# Intergration

- Copy esgameparameters.xml to your project
- Merge proguard-rules.pro to your project.
- Import google-service.json to your project.
- Merge AndroidManifest.xml to your project.
- Merge gradle.build to your project.

## Coding

You can delete the current file by clicking the **Remove** button in the file explorer. The file will be moved into the **Trash** folder and automatically deleted after 7 days of inactivity.


```
