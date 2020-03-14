https://stackoverflow.com/questions/44911547/where-to-store-android-keystore-file-for-cirlceci-build

This will encode the file and you can copy and paste the value across:
openssl base64 -A -in .signing/release.jks

Then, in your config.yml file at CircleCI, decode it back:
echo $RELEASE_KEYSTORE_BASE64 | base64 -d > .signing/release.jks
base64 -d <<< $RELEASE_KEYSTORE_BASE64 > .signing/release.jks

testing slack

![](https://github.com/moonlitdoor/mld-amessage-android/workflows/Android%20Beta%20CI/badge.svg)