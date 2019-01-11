package com.example.videodemo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.videodemo", appContext.getPackageName());

        String s1 = "2qb+Zp/LD0fZ6Im6lD8S6w1LmrFXA4xL3z7YSq7aIDK3nMZv4ctOX/tAvx0yLxUv5PBf9sEHqt1rAhalm1RdTQ==";
        String s2 = AES.getInstance().decrypt(s1);
        String s3 = AES.getInstance().encrypt(s2.getBytes());
        Log.i("123", "s2="+s2);
        Log.i("123", "s3="+s3);
    }
}
