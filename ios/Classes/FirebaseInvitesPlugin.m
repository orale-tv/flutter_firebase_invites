#import "FirebaseInvitesPlugin.h"
#import <firebase_invites/firebase_invites-Swift.h>

@implementation FirebaseInvitesPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFirebaseInvitesPlugin registerWithRegistrar:registrar];
}
@end
