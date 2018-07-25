package io.flutter.plugins.firebaseinvites;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import java.util.Map;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.appinvite.FirebaseAppInvite;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.android.gms.appinvite.AppInviteInvitation.IntentBuilder;

/** FirebaseInvitesPlugin */
public class FirebaseInvitesPlugin implements MethodCallHandler {
  private static final int REQUEST_INVITE = 98765;

  private final PluginRegistry.Registrar registrar;

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "firebase_invites");
    channel.setMethodCallHandler(new FirebaseInvitesPlugin(registrar));
  }


  FirebaseInvitesPlugin(PluginRegistry.Registrar registrar) {
    this.registrar = registrar;
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    switch (call.method) {
      case "sendInvite":
        String title = call.argument("title");
        String message = call.argument("message");
        String deepLink = call.argument("deepLink");
        String customImage = call.argument("customImage");
        String ctaText = call.argument("ctaText");
        String iosClientId = call.argument("iosClientId");

        IntentBuilder intent = new AppInviteInvitation.IntentBuilder(title);

        if (deepLink != null) {
          intent.setDeepLink(Uri.parse(deepLink));
        }

        if (message != null) {
          intent.setMessage(message);
        }

        if (customImage != null) {
          intent.setCustomImage(Uri.parse(customImage));
        }

        if (ctaText != null) {
          intent.setCallToActionText(ctaText);
        }

        if (iosClientId != null) {
          intent.setOtherPlatformsTargetApplication(AppInviteInvitation.IntentBuilder.PlatformMode.PROJECT_PLATFORM_IOS, iosClientId);
        }
        registrar.activity().startActivityForResult(intent.build(), REQUEST_INVITE);
        result.success(null);
        break;
      default:
        result.notImplemented();
        break;
    }
  }
}
