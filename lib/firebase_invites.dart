import 'dart:async';

import 'package:flutter/services.dart';
import 'package:meta/meta.dart';

class FirebaseInvites {
  static const MethodChannel _channel = const MethodChannel('firebase_invites');

  static Future<void> sendInvite(
      {@required String title,
      String message,
      String ctaText,
      String deepLink,
      String customImage,
      String emailHtmlContent,
      String emailSubject,
      String analyticsId,
      String iosClientId}) async {
    await _channel.invokeMethod('sendInvite', <String, String>{
      'title': title,
      'message': message,
      'ctaText': ctaText,
      'deepLink': deepLink,
      'customImage': customImage,
      'emailHtmlContent': emailHtmlContent,
      'emailSubject': emailSubject,
      'analyticsId': analyticsId,
      'iosClientId': iosClientId,
    });
  }
}
