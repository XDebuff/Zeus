package com.zeus.core.biometricPrompt;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Log;

import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.core.os.CancellationSignal;

import static com.zeus.core.cache.AppConfig.DEFAULT_KEY_NAME;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public class BiometricPromptManager {

    private static final String TAG = "BiometricPromptManager";

    private FingerprintManagerCompat mFingerprintManagerCompat;
    private Context mContext;
    private AlertDialog mDialog;
    private Cipher mCipher;
    private KeyStore keyStore;
    private CancellationSignal mCancellationSignal;

    public BiometricPromptManager(Context context) {
        super();
        mContext = context;
        mFingerprintManagerCompat = FingerprintManagerCompat.from(mContext);
    }

    public void authenticate() {
        if (!isBiometricPromptEnable()) {
            Log.d(TAG, "无法进行指纹识别工作");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("指纹识别");
        builder.setMessage("请将手指放置指纹识别器，现在开始识别指纹");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mCancellationSignal.cancel();
            }
        });
        mDialog = builder.create();
        mDialog.show();
        mCancellationSignal = new CancellationSignal();
        FingerprintManagerCompat.CryptoObject cryptoObject = new FingerprintManagerCompat.CryptoObject(mCipher);
        mFingerprintManagerCompat.authenticate(cryptoObject,
                0, mCancellationSignal, new FingerPrintListener(), null);
    }

    @TargetApi(23)
    private void initKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(DEFAULT_KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7);
            keyGenerator.init(builder.build());
            keyGenerator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @TargetApi(23)
    private void initCipher() {
        try {
            SecretKey key = (SecretKey) keyStore.getKey(DEFAULT_KEY_NAME, null);
            mCipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC + "/"
                    + KeyProperties.ENCRYPTION_PADDING_PKCS7);
            mCipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void onPause() {
        if (mCancellationSignal != null) {
            mCancellationSignal.cancel();
        }
    }

    private boolean isBiometricPromptEnable() {
        return isAboveSdk23()
                && isHardwareDetected()
                && hasEnrolledFingerprints();
    }

    private boolean isAboveSdk23() {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.M;
    }

    private boolean hasEnrolledFingerprints() {
        return mFingerprintManagerCompat.hasEnrolledFingerprints();
    }

    private boolean isHardwareDetected() {
        return mFingerprintManagerCompat.isHardwareDetected();
    }

    class FingerPrintListener extends FingerprintManagerCompat.AuthenticationCallback {

        /**
         * 无法处理的异常
         *
         * @param errMsgId
         * @param errString
         */
        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            super.onAuthenticationError(errMsgId, errString);
            switch (errMsgId) {
                default:
                    Log.d(TAG, "errMsgId:" + errMsgId
                            + "-------errString:" + errString);
                    break;
            }
            mDialog.setMessage("errMsgId:" + errMsgId
                    + "-------errString:" + errString);
        }

        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            super.onAuthenticationHelp(helpMsgId, helpString);
            switch (helpMsgId) {
                default:
                    Log.d(TAG, "helpMsgId:" + helpMsgId
                            + "-------helpString:" + helpString);
                    break;
            }
            mDialog.setMessage("helpMsgId:" + helpMsgId
                    + "-------helpString:" + helpString);
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            Log.d(TAG, "指纹验证成功");
            mDialog.setMessage("指纹验证成功");
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            Log.d(TAG, "指纹验证错误");
            mDialog.setMessage("指纹验证错误");
        }
    }

}
