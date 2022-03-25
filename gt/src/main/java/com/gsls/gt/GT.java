package com.gsls.gt;

import static android.content.Context.POWER_SERVICE;

import android.Manifest;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.http.SslError;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.friendlyarm.AndroidSDK.HardwareInit;
import com.github.mjdev.libaums.UsbMassStorageDevice;
import com.github.mjdev.libaums.fs.FileSystem;
import com.github.mjdev.libaums.fs.UsbFile;
import com.github.mjdev.libaums.fs.UsbFileInputStream;
import com.github.mjdev.libaums.fs.UsbFileOutputStream;
import com.github.mjdev.libaums.partition.Partition;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ProtocolException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import android_serialport_api.SerialPort;
import android_serialport_api.SerialPortFinder;
import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 工具类说明：
 * GSLS_Tool
 * <p>
 * <p>
 * <p>
 * 更新时间:2022.3.25
 * CSDN 博客/官网教程:https://blog.csdn.net/qq_39799899
 * GitHub https://github.com/1079374315/GT
 * 更新内容：（1.3.6.9 版本 大爆料：新增 MVC、MVP、MVVM 辅助框架）
 * 内容如下：
 * 1.新增 GT_PopupWindow 类
 * 2.LOG 类新增 GT.logt("标记日志"); GT.errt("标记日志");
 * 3.优化 时间与时间戳互转的方法 GT.GT_Date.timeToTimestampToTime(String timeOrTimestamp, String timeFormat)
 * 4.新增 编程辅助框架 MVC/MVP/MVVM/GT/GT_Binding模式
 * 5.增强 AnnotationAssist 反射、注解工具类,新增以下功能方法
 * 1.获取类的泛类型
 * 2.反射类中方法进行赋值
 * 3.获取反射方法的返回值
 * 4.获取反射变量的值(可私用变量)
 * 5.利用反射给变量设置值(可私有变量)
 * 6.反射该类所有的变量值 (可接受类型 class、String(类包名)、实体类)
 * 7.字符串转Class
 * 6.增强分享功能
 * 7.优化所有的 DataBinding自动生成类 ，增强 GT_BindingViewModel 并支持 通过泛类映射实例化 ，增加了 适配器的 DataBinding
 * 8.增加 WebViewUtils 类
 * 9.
 * <p>
 * <p>
 * 小提示：(用于 AndroidStudio )
 * 收起所有的 方法: Ctrl + Shift +  - (减号)
 * 展开所有的 方法: Ctrl + Shift +  + (加号)
 * 代码格式化快捷键是 Ctrl + Alt + L
 */

@SuppressWarnings("ALL")
public class GT {

    //================================== 所有属于 GT 类的属性 =======================================
    private static GT gtAndroid = null;          //定义 GT 对象
    private static Toast toast;//弱引用 Toast
    private static Context context;//弱引用 Context
    private static FragmentActivity fragmentActivity;//弱引用 当前动态的 上下文对象
    //================================== 提供访问 GT 属性的接口======================================

    /**
     * 获取线程安全的 GT 单例对象
     *
     * @return GT  返回 GT 对象
     */
    public static GT getGT() {
        if (gtAndroid == null) {
            synchronized (GT.class) {
                if (gtAndroid == null) {
                    gtAndroid = new GT();
                }
            }
        }
        return gtAndroid;
    }

    /**
     * 获取 Context
     *
     * @return
     */
    public static Context getActivity() {
        if (context == null) {
            context = GT_Activity.getApplicationByReflect();
        }
        return context;
    }

    public static FragmentActivity getFragmentActivity() {
        if (context == null) {
            context = GT_Activity.getApplicationByReflect();
        }
        if (context != null && context instanceof FragmentActivity) {
            fragmentActivity = (FragmentActivity) context;
        }
        return fragmentActivity;
    }

    /**
     * 绑定 Activity
     *
     * @param activity
     * @Activity 为外部提供访问 GT Context 接口
     */
    public static void build(Object obj) {
        context = (Context) AnnotationAssist.returnActivityOrView(obj)[0];
        if (context == null) {
            context = getActivity();
        }
        AnnotationAssist.initAll(obj); //初始化 IOC 注解
    }

    /**
     * 给与非常用的类型进行组件绑定
     *
     * @param obj
     * @param view
     */
    public static void build(Object obj, View view) {
        AnnotationAssist.initAll(obj);
        AnnotationAssist.initView(obj, view);
        AnnotationAssist.initClick(obj, view);
    }

    //============================================= 静态调用方法 =====================================

    /**
     * @param number 提示报错文件是第几级
     * @return String 报错的文件与行号
     * @报错提示 该提示可通过 GT 提供的接口 的实例获取
     */
    public static String getLineInfo(int number) {
        if (number < 0) {
            return "log Hierarchy error";
        }
        StackTraceElement ste = null;
        try {
            ste = new Throwable().getStackTrace()[number];
        } catch (Exception e) {
            getLineInfo(--number);
        }
        return "提示的文件  " + ste.getFileName() + "  行号 " + ste.getLineNumber();
    }

    /**
     * @return String 报错的文件与行号
     * @报错提示 该提示可通过 GT 提供的接口 的实例获取
     */
    public static String getLineInfo() {
        StackTraceElement ste = new Throwable().getStackTrace()[1];//默认是当前报错层级
        return "提示的文件  " + ste.getFileName() + "  行号 " + ste.getLineNumber();
    }

    /**
     * 提示消息 Log
     *
     * @param msg object 类型的消息
     */
    public static void log(Object msg) {
        if (LOG.LOG_TF) {
            LOG.setLogData(String.valueOf(msg));
            Log.i(LOG.LOG_TAG + "i", String.valueOf(msg));
        }
    }

    /**
     * @param mg
     * @详细提示消息
     */
    public static void logs(Object mg) {
        if (LOG.LOG_TF) {
            String prefix = "";
            prefix = LOG.getPrefix(LOG.lineInfoIndex);
            LOG.setLogData(prefix + mg);
            Log.i("GT_i", prefix + mg);
            if (LOG.LOG_FILE_TF) {// 打印到sd卡
                if (TextUtils.isEmpty(prefix)) {
                    prefix = LOG.getPrefix(LOG.lineInfoIndex);
                }
                LOG.writeToSdCard(LOG.path, prefix, mg);
            }
        }
    }

    /**
     * @param mg
     * @标记日志
     */
    public static void logt(Object mg) {
        if (LOG.LOG_TF) {
            String prefix = "";
            prefix = LOG.getPrefixT(LOG.lineInfoIndex);
            LOG.setLogData(prefix + mg);
            Log.i("GT_i", prefix + mg);
            if (LOG.LOG_FILE_TF) {// 打印到sd卡
                if (TextUtils.isEmpty(prefix)) {
                    prefix = LOG.getPrefix(LOG.lineInfoIndex);
                }
                LOG.writeToSdCard(LOG.path, prefix, mg);
            }
        }
    }

    /**
     * 打印所有提示消息 Log
     *
     * @param msg object 类型的消息
     */
    public static void logAll(Object msg) {
        if (LOG.LOG_TF) {
            String strMsg = msg.toString();
            if (strMsg.length() > LOG.logMaxLength) {
                while (true) {
                    String substring = strMsg.substring(0, LOG.logMaxLength);
                    LOG.setLogData("--- " + substring);
                    Log.i(LOG.LOG_TAG + "i", "--- " + substring);
                    strMsg = strMsg.substring(LOG.logMaxLength);
                    if (strMsg.length() <= LOG.logMaxLength) {
                        LOG.setLogData(strMsg);
                        Log.i(LOG.LOG_TAG + "i", strMsg);
                        break;
                    }
                }
            } else {
                LOG.setLogData(String.valueOf(msg));
                Log.i(LOG.LOG_TAG + "i", String.valueOf(msg));
            }
        }
    }

    /**
     * 提示消息 Log
     *
     * @param msg object 类型的消息
     */
    public static void err(Object msg) {
        if (LOG.LOG_TF) {
            Log.e(LOG.LOG_TAG + "e", String.valueOf(msg));
            LOG.setLogData(String.valueOf(msg));
        }
    }

    /**
     * @param mg
     * @详细提示消息
     */
    public static void errs(Object mg) {
        if (LOG.LOG_TF) {
            String prefix = "";
            prefix = LOG.getPrefix(LOG.lineInfoIndex);
            Log.e("GT_i", prefix + mg);
            LOG.setLogData(prefix + mg);
            if (LOG.LOG_FILE_TF) {// 打印到sd卡
                if (TextUtils.isEmpty(prefix)) {
                    prefix = LOG.getPrefix(LOG.lineInfoIndex);
                }
                LOG.writeToSdCard(LOG.path, prefix, mg);
            }
        }
    }

    /**
     * @param mg
     * @标记日志
     */
    public static void errt(Object mg) {
        if (LOG.LOG_TF) {
            String prefix = "";
            prefix = LOG.getPrefixT(LOG.lineInfoIndex);
            Log.e("GT_i", prefix + mg);
            LOG.setLogData(prefix + mg);
            if (LOG.LOG_FILE_TF) {// 打印到sd卡
                if (TextUtils.isEmpty(prefix)) {
                    prefix = LOG.getPrefix(LOG.lineInfoIndex);
                }
                LOG.writeToSdCard(LOG.path, prefix, mg);
            }
        }
    }

    /**
     * 打印所有提示消息 Log
     *
     * @param msg object 类型的消息
     */
    public static void errAll(Object msg) {
        if (LOG.LOG_TF) {
            String strMsg = msg.toString();
            if (strMsg.length() > LOG.logMaxLength) {
                while (true) {
                    String substring = strMsg.substring(0, LOG.logMaxLength);
                    LOG.setLogData("--- " + substring);
                    Log.e(LOG.LOG_TAG + "i", "--- " + substring);
                    strMsg = strMsg.substring(LOG.logMaxLength);
                    if (strMsg.length() <= LOG.logMaxLength) {
                        LOG.setLogData(strMsg);
                        Log.e(LOG.LOG_TAG + "i", strMsg);
                        break;
                    }
                }
            } else {
                LOG.setLogData(String.valueOf(msg));
                Log.e(LOG.LOG_TAG + "i", String.valueOf(msg));
            }
        }
    }

    /**
     * 提示消息 Log
     *
     * @param title 日志标题
     * @param msg   日志消息
     */
    public static void log(Object title, Object msg) {
        if (LOG.LOG_TF) {

            LOG.setLogData("--- Run" +
                    "\n\n---------------------" + title + "------------------------\n" +
                    "" + msg + "\n" +
                    "---------------------" + title + "------------------------\n\n" +
                    "--- Close");
            Log.i(LOG.LOG_TAG + "i",
                    "--- Run" +
                            "\n\n---------------------" + title + "------------------------\n" +
                            "" + msg + "\n" +
                            "---------------------" + title + "------------------------\n\n" +
                            "--- Close"
            );
        }

    }

    /**
     * 打印所有提示消息 Log
     *
     * @param msg object 类型的消息
     */
    public static void logAll(Object title, Object msg) {
        if (LOG.LOG_TF) {
            LOG.setLogData("Run ----- " + title + " ---------");
            Log.i(LOG.LOG_TAG + "i", "Run ----- " + title + " ---------");
            String strMsg = msg.toString();
            if (strMsg.length() > LOG.logMaxLength) {
                while (true) {
                    String substring = strMsg.substring(0, LOG.logMaxLength);
                    LOG.setLogData("--- " + substring);
                    Log.i(LOG.LOG_TAG + "i", "--- " + substring);
                    strMsg = strMsg.substring(LOG.logMaxLength);
                    if (strMsg.length() <= LOG.logMaxLength) {
                        LOG.setLogData(strMsg);
                        Log.i(LOG.LOG_TAG + "i", strMsg);
                        break;
                    }
                }
            } else {
                LOG.setLogData(String.valueOf(msg));
                Log.i(LOG.LOG_TAG + "i", String.valueOf(msg));
            }

            LOG.setLogData("----- " + title + " ----- Close");
            Log.i(LOG.LOG_TAG + "i", "----- " + title + " ----- Close");

        }
    }

    /**
     * 提示消息 Log
     *
     * @param title 日志标题
     * @param msg   日志消息
     */
    public static void err(Object title, Object msg) {
        if (LOG.LOG_TF) {
            LOG.setLogData("--- Run" +
                    "\n\n---------------------" + title + "------------------------\n" +
                    "" + msg + "\n" +
                    "---------------------" + title + "------------------------\n\n" +
                    "--- Close");
            Log.e(LOG.LOG_TAG + "e",
                    "--- Run" +
                            "\n\n---------------------" + title + "------------------------\n" +
                            "" + msg + "\n" +
                            "---------------------" + title + "------------------------\n\n" +
                            "--- Close"
            );
        }

    }

    /**
     * 打印所有提示消息 Log
     *
     * @param msg object 类型的消息
     */
    public static void errAll(Object title, Object msg) {
        if (LOG.LOG_TF) {
            LOG.setLogData("Run ----- " + title + " ---------");
            Log.e(LOG.LOG_TAG + "i", "Run ----- " + title + " ---------");
            String strMsg = msg.toString();
            if (strMsg.length() > LOG.logMaxLength) {
                while (true) {
                    String substring = strMsg.substring(0, LOG.logMaxLength);
                    Log.e(LOG.LOG_TAG + "i", "--- " + substring);
                    strMsg = strMsg.substring(LOG.logMaxLength);
                    if (strMsg.length() <= LOG.logMaxLength) {
                        Log.e(LOG.LOG_TAG + "i", strMsg);
                        break;
                    }
                }
            } else {
                LOG.setLogData(String.valueOf(msg));
                Log.e(LOG.LOG_TAG + "i", String.valueOf(msg));
            }

            LOG.setLogData("----- " + title + " ----- Close");
            Log.e(LOG.LOG_TAG + "i", "----- " + title + " ----- Close");

        }
    }

    /**
     * 单个消息框 Toast
     *
     * @param msg object 类型的消息
     */
    public static void toast_s(Object msg) {
        if (TOAST.TOAST_TF) {
            if (context != null && context != null) {
                Toast.makeText(context, String.valueOf(msg), Toast.LENGTH_SHORT).show();
            } else {
                if (LOG.LOG_TF)//设置为默认输出日志
                    err("GT_bug", "消息框错误日志：你没有为 Context 进行赋值 ，却引用了 Toast 导致该功能无法实现。解决措施 在调用 toast 代码之前添加：GT.getGT().setactivity(activity);");
            }

        }
    }

    /**
     * 单个 Toast
     *
     * @param msg  消息
     * @param time 显示时间
     */
    public static void toast_time(Object msg, long time) {
        if (TOAST.TOAST_TF) {
            if (context != null && context != null) {
                final Toast toast = Toast.makeText(context, String.valueOf(msg), Toast.LENGTH_LONG);
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        toast.show();
                    }
                }, 0, 3000);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        toast.cancel();
                        timer.cancel();
                    }
                }, time);
            } else {
                if (LOG.LOG_TF)//设置为默认输出日志
                    err("GT_bug", "消息框错误日志：你没有为 Context 进行赋值 ，却引用了 Toast 导致该功能无法实现。解决措施 在调用 toast 代码之前添加：GT.getGT().setactivity(activity);");
            }

        }
    }/**/

    /**
     * 可多个消息框 Toast
     *
     * @param context 上下文
     * @param msg     object 类型的消息
     */
    public static void toast_s(Context context, Object msg) {
        if (TOAST.TOAST_TF)
            Toast.makeText(context, String.valueOf(msg), Toast.LENGTH_SHORT).show();
    }

    /**
     * 可多个消息框 Toast
     *
     * @param context 上下文
     * @param msg     object 类型的消息
     */
    public static void toast_time(Context context, Object msg, int time) {
        if (TOAST.TOAST_TF) {
            final Toast toast = Toast.makeText(context, String.valueOf(msg), Toast.LENGTH_LONG);
            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    toast.show();
                }
            }, 0, 3000);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    toast.cancel();
                    timer.cancel();
                }
            }, time);

        }
    }

    /**
     * @param content
     * @标准Toast
     */
    public static void toast(Object message) {
        if (getActivity() != null) {
            if (toast == null) {
                toast = Toast.makeText(getActivity(), message.toString(), Toast.LENGTH_SHORT);
            } else {
                toast.setText(message.toString());
            }
            toast.show();
        } else {
            log(getLineInfo(1), "当前没有赋值 Context 无法显示 Toast ");
        }

    }

    public static void toast(Context context, Object content) {
        if (toast == null) {
            toast = Toast.makeText(getActivity(), content.toString(), Toast.LENGTH_SHORT);
        } else {
            toast.setText(content.toString());
        }
        toast.show();
    }

    public static GT_Fragment getGT_Fragment() {
        return GT_Fragment.gt_fragment;
    }

    /**
     * @param dialogFragment
     * @跳转其他的 DialogFragment
     */
    public static void startDialogFragment(DialogFragment dialogFragment) {
        dialogFragment.show(getFragmentActivity().getSupportFragmentManager(), dialogFragment.getClass().toString());// 弹出退出提示
    }

    public static void startDialogFragment(Fragment fragment, DialogFragment dialogFragment) {
        dialogFragment.setTargetFragment(fragment, 1);
        dialogFragment.show(getFragmentActivity().getSupportFragmentManager(), dialogFragment.getClass().toString());// 弹出退出提示
    }

    /**
     * @param dialogFragment
     * @跳转其他的 DialogFragment
     */
    public static void startDialogFragment(DialogFragment dialogFragment, FragmentManager fragmentManager) {
        dialogFragment.show(fragmentManager, dialogFragment.getClass().toString());// 弹出退出提示
    }

    /**
     * @param dialogFragment
     * @跳转其他的 DialogFragment
     */
    public static void startDialogFragment(Class<?> dialogFragmentClass, FragmentManager fragmentManager) {

        DialogFragment fragment = null;

        try {
            fragment = (DialogFragment) dialogFragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        fragment.show(fragmentManager, fragment.getClass().toString());// 弹出退出提示
    }

    public static void startDialogFragment(Fragment fragment, FragmentManager supportFragmentManager, DialogFragment dialogFragment) {
        dialogFragment.setTargetFragment(fragment, 1);
        dialogFragment.show(supportFragmentManager, dialogFragment.getClass().toString());// 弹出退出提示
    }

    public static void startDialogFragment(Class<?> dialogFragmentClass) {

        DialogFragment fragment = null;

        try {
            fragment = (DialogFragment) dialogFragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        fragment.show(getFragmentActivity().getSupportFragmentManager(), fragment.getClass().toString());// 弹出退出提示
    }

    public static void startDialogFragment(Fragment fragment, Class<?> dialogFragmentClass) {
        DialogFragment dialogFragment = null;
        try {
            dialogFragment = (DialogFragment) dialogFragmentClass.newInstance();
            dialogFragment.setTargetFragment(fragment, 1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        dialogFragment.show(getFragmentActivity().getSupportFragmentManager(), fragment.getClass().toString());// 弹出退出提示
    }

    /**
     * @param toFragment
     * @跳转 Fragment
     */
    public static void startFragment(Fragment toFragment) {
        if (GT_Fragment.gt_fragment != null) {
            GT_Fragment.gt_fragment.startFragment(toFragment);
        }
    }

    /**
     * @param toFragment
     * @跳转 Fragment
     */
    public static void startFragment(Class<?> toFragment) {
        if (GT_Fragment.gt_fragment != null) {
            GT_Fragment.gt_fragment.startFragment(toFragment);
        }
    }

    /**
     * 跳转Fragment
     *
     * @param fragmentId
     * @param toFragment
     */
    public static void startFragment(int fragmentId, Fragment toFragment) {
        if (GT_Fragment.gt_fragment != null) {
            GT_Fragment.gt_fragment.startFragment(fragmentId, toFragment);
        }
    }

    /**
     * 跳转Fragment
     *
     * @param fragmentId
     * @param toFragment
     */
    public static void startFragment(int fragmentId, Class<?> toFragment) {
        if (GT_Fragment.gt_fragment != null) {
            GT_Fragment.gt_fragment.startFragment(fragmentId, toFragment);
        }
    }

    /**
     * 跳转Fragment
     *
     * @param toFragment 跳转的Fragment
     * @param startMode  启动模式
     */
    public static void startFragment(Fragment toFragment, int startMode) {
        if (GT_Fragment.gt_fragment != null) {
            GT_Fragment.gt_fragment.startMode(startMode).startFragment(toFragment);
        }
    }

    /**
     * 跳转Fragment
     *
     * @param toFragment 跳转的Fragment
     * @param startMode  启动模式
     */
    public static void startFragment(Class<?> toFragment, int startMode) {
        if (GT_Fragment.gt_fragment != null) {
            GT_Fragment.gt_fragment.startMode(startMode).startFragment(toFragment);
        }
    }

    /**
     * 跳转Fragment
     *
     * @param fragmentId 跳转的容器
     * @param toFragment 跳转的Fragment
     * @param startMode  启动模式
     */
    public static void startFragment(int fragmentId, Fragment toFragment, int startMode) {
        if (GT_Fragment.gt_fragment != null) {
            GT_Fragment.gt_fragment.startMode(startMode).startFragment(fragmentId, toFragment);
        }
    }

    /**
     * 跳转Fragment
     *
     * @param fragmentId 跳转的容器
     * @param toFragment 跳转的Fragment
     * @param startMode  启动模式
     */
    public static void startFragment(int fragmentId, Class<?> toFragment, int startMode) {
        if (GT_Fragment.gt_fragment != null) {
            GT_Fragment.gt_fragment.startMode(startMode).startFragment(fragmentId, toFragment);
        }
    }

    /**
     * @param toFragment
     * @跳转 Fragment
     */
    public static void startFragmentHome(Fragment toFragment) {
        if (GT_Fragment.gt_fragment != null) {
            GT_Fragment.gt_fragment.startFragmentHome(toFragment);
        }
    }

    /**
     * @param toFragment
     * @跳转 Fragment
     */
    public static void startFragmentHome(Class<?> toFragment) {
        if (GT_Fragment.gt_fragment != null) {
            GT_Fragment.gt_fragment.startFragmentHome(toFragment);
        }
    }

    /**
     * 使用首页模式启动Fragment
     *
     * @param fragmentId
     * @param toFragment
     */
    public static void startFragmentHome(int fragmentId, Fragment toFragment) {
        if (GT_Fragment.gt_fragment != null) {
            GT_Fragment.gt_fragment.startMode(GT_Fragment.MODE_HOME);
            GT_Fragment.gt_fragment.startFragment(fragmentId, toFragment);
        }
    }

    /**
     * 使用首页模式启动Fragment
     *
     * @param fragmentId
     * @param toFragment
     */
    public static void startFragmentHome(int fragmentId, Class<?> toFragment) {
        if (GT_Fragment.gt_fragment != null) {
            GT_Fragment.gt_fragment.startMode(GT_Fragment.MODE_HOME);
            GT_Fragment.gt_fragment.startFragment(fragmentId, toFragment);
        }
    }

    /**
     * 启动悬浮窗
     *
     * @param toFragment
     */
    public static void startFloatingWindow(Class<?> toFragment) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(getActivity())) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getActivity().getPackageName()));
                getFragmentActivity().startActivityForResult(intent, 0);
            } else {
                getActivity().startService(new Intent(getActivity(), toFragment));
            }
        } else {
            getActivity().startService(new Intent(getActivity(), toFragment));
        }
    }

    //============================================= 日志功能 =========================================

    /**
     * @用于打详细日志的 LOG 框架
     */
    public static class LOG {

        public LOG() {
        }

        //保存log的路径
        private static String path = Environment.getExternalStorageDirectory().getPath() + "/GT_LOG/";
        private static String logFilePath = ""; //自定义 打印日志的文件路径
        //格式化不包含秒的时间
        private static SimpleDateFormat dfd = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
        //保存log的文件名称
        public static String fileName = "-log-" + "GT".substring("GT".lastIndexOf(".") + 1) + ".txt";
        private static String printFileName = "";         //用于自定义 打印文件名

        public static boolean LOG_TF = true;        //控制外部所有的 Log 显示
        public static boolean LOG_FILE_TF = false;  //控制是否打日志到本地(注意：打开比较耗内存资源，该功能仅用于测试，在上线时请关闭它)
        public static boolean GT_LOG_TF = false;    //控制内部所有的 Log 显示
        public static String LOG_TAG = "GT_";       //控制日志 TAG 值
        public static int logMaxLength = 3900;      //日志打印最大长度 默认是 3900 可修改
        private static int lineInfoIndex = 5;       //日志当前属于的层数,默认是5级返回层级

        public static int getLineInfoIndex() {
            return lineInfoIndex;
        }

        public static void setLineInfoIndex(int lineInfoIndex) {
            LOG.lineInfoIndex = lineInfoIndex;
        }

        public static String getLogFilePath() {
            return logFilePath;
        }

        public static void setLogFilePath(String logFilePath) {
            LOG.logFilePath = logFilePath;
        }

        public static String getPath() {
            return path;
        }

        public static void setPath(String path) {
            LOG.path = path;
        }

        public static SimpleDateFormat getDfd() {
            return dfd;
        }

        public static void setDfd(SimpleDateFormat dfd) {
            LOG.dfd = dfd;
        }

        public static String getFileName() {
            return fileName;
        }

        public static void setFileName(String fileName) {
            LOG.fileName = fileName;
        }

        public static boolean isLogTf() {
            return LOG_TF;
        }

        public static void setLogTf(boolean logTf) {
            LOG_TF = logTf;
        }

        public static boolean isLogFileTf() {
            return LOG_FILE_TF;
        }

        /**
         * @param logFileTf
         * @param activity
         * @打开本地打印
         */
        public static void setLogFileTf(boolean logFileTf, Activity activity) {
            LOG_FILE_TF = logFileTf;
            if (logFileTf) {
                printFileName = ApplicationUtils.getAppName(activity);
            }
        }

        public static boolean isGtLogTf() {
            return GT_LOG_TF;
        }

        public static void setGtLogTf(boolean gtLogTf) {
            GT_LOG_TF = gtLogTf;
        }

        public static String getLogTag() {
            return LOG_TAG;
        }

        public static void setLogTag(String logTag) {
            LOG_TAG = logTag;
        }

        public static String getPrintFileName() {
            return printFileName;
        }

        public static void setPrintFileName(String printFileName) {
            LOG.printFileName = printFileName;
        }

        public static SimpleDateFormat getDfs() {
            return dfs;
        }

        public static void setDfs(SimpleDateFormat dfs) {
            LOG.dfs = dfs;
        }

        private static String prefix; // 占位符

        // 获取log打印前缀(行数、类名、方法名)
        private static String getPrefix(int number) {
            if (number < 0) {
                return "log Hierarchy error ";
            }
            try {
                StackTraceElement caller = java.lang.Thread.currentThread().getStackTrace()[number];// new Throwable().getStackTrace()[number];
                prefix = "%s(" + caller.getFileName() + ":%d)" + " %s(): ";//初始化
                String packName = caller.getClassName().substring(0, caller.getClassName().lastIndexOf(".") + 1);
                prefix = String.format(prefix, packName, caller.getLineNumber(), caller.getMethodName()); // 替换
            } catch (Exception e) {
//                errs("获取日志层级出现错误e:" + e);
                getPrefix(--number);
            }
            return prefix;
        }

        private static String getPrefixT(int number) {
            if (number < 0) {
                return "log Hierarchy error ";
            }
            try {
                StackTraceElement caller = java.lang.Thread.currentThread().getStackTrace()[number];// new Throwable().getStackTrace()[number];
                prefix = "(" + caller.getFileName() + ":%d): ";//初始化
                prefix = String.format(prefix, caller.getLineNumber()); // 替换
            } catch (Exception e) {
//                errs("获取日志层级出现错误e:" + e);
                getPrefix(--number);
            }
            return prefix;
        }

        /**
         * log打印到sdCard
         *
         * @param path   文件路径(不含文件名)
         * @param prefix log前缀内容
         * @param msg    打印内容
         * @ 格式化包含秒的时间
         */
        private static SimpleDateFormat dfs = new SimpleDateFormat("HH:mm:ss.SSS", Locale.CHINESE);

        /**
         * 打本地日志
         *
         * @param path
         * @param prefix
         * @param msg
         */
        public static void writeToSdCard(String path, String prefix, Object msg) {
            if (logFilePath != null && logFilePath.length() > 0) {
                path = Environment.getExternalStorageDirectory().getPath() + "/" + logFilePath;
            }
            String time = dfs.format(new Date());
            File file = createPathFile(path);
            BufferedWriter out = null;
            try {
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
                out.write("\r\n[" + time + "]" + getProcessTag() + prefix + msg);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //获取当前进程信息(PID,TID,ThreadId)
        private static String getProcessTag() {
            return "<PID:" + android.os.Process.myPid() + ",TID:" + android.os.Process.myTid() + ",ThreadId:" + java.lang.Thread.currentThread().getId() + ">";
        }

        /**
         * 根据文件路径 创建文件
         *
         * @param path 文件路径(不含文件名)
         */
        public static File createPathFile(String path) {
            File fileDir = new File(path);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            //如果自定义了 打印文件名就给它初始化上
            if (printFileName != null && !"".equals(printFileName)) {
                fileName = "-" + printFileName + ".txt";
            }
            String filePath = path + dfd.format(new Date()) + fileName;
            File file = new File(filePath);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                    err("LOG日志报错", getLineInfo(1) + "[logGT] createPathFile(): e=" + e);
                }
            }
            return file;
        }

        //注册App异常捕获
        public static void initAppErrLogTry(Activity activity) {
            AppErrLog.getInstance().init(activity);//初始化
        }


        //向日志广播发送日志信息
        public static void setLogData(String value) {
           /* if (!DarknessMagic.GT_FloatingWindow.isOpenLog) return;
            Intent intent = new Intent();
//            intent.setAction(DarknessMagic.GT_FloatingWindow.GT_Floating.UtilsGTApp.class.getName());
            intent.putExtra("logAllData", value + "\n");
            getActivity().sendBroadcast(intent);*/
        }

    }

    /**
     * @APP 错误异常信息捕获
     */
    private static class AppErrLog implements java.lang.Thread.UncaughtExceptionHandler {
        private static AppErrLog sInstance = new AppErrLog();
        private java.lang.Thread.UncaughtExceptionHandler mDefaultMyCrashHandler;
        private Context mContext;

        private AppErrLog() {
        }

        public static AppErrLog getInstance() {
            return sInstance;
        }

        public void init(Context context) {
            mDefaultMyCrashHandler = java.lang.Thread.getDefaultUncaughtExceptionHandler();
            java.lang.Thread.setDefaultUncaughtExceptionHandler(this);
            mContext = context.getApplicationContext();
        }

        /**
         * 当程序中有未被捕获的异常，系统将会调用这个方法
         *
         * @param t 出现未捕获异常的线程
         * @param e 得到异常信息
         */
        @Override
        public void uncaughtException(java.lang.Thread t, Throwable e) {
            try {
                //保存到本地
//            exportExceptionToSDCard(e);
                GT.logs("************************ ErrLogRun ***************************");
                GT.logs("PhoneData：" + appendPhoneInfo());
                GT.logs("---------------------------------------------------------------");
                GT.logs("ErrLog:" + e);
                GT.logs("************************ ErrLogClose ***************************");
                //下面也可以写上传的服务器的代码
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            //如果系统提供了默认的异常处理器，则交给系统去结束程序，否则就自己结束自己
            if (mDefaultMyCrashHandler != null) {
                mDefaultMyCrashHandler.uncaughtException(t, e);
            } else {
                GT.log("exit app");
            }
        }

        /**
         * 获取手机信息
         */
        private String appendPhoneInfo() throws PackageManager.NameNotFoundException {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            StringBuilder sb = new StringBuilder();
            //App版本
            sb.append("App Version: ");
            sb.append(pi.versionName);
            sb.append("_");
            sb.append(pi.versionCode + "\n");

            //Android版本号
            sb.append("OS Version: ");
            sb.append(Build.VERSION.RELEASE);
            sb.append("_");
            sb.append(Build.VERSION.SDK_INT + "\n");

            //手机制造商
            sb.append("Vendor: ");
            sb.append(Build.MANUFACTURER + "\n");

            //手机型号
            sb.append("Model: ");
            sb.append(Build.MODEL + "\n");

            //CPU架构
            sb.append("CPU: ");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                sb.append(Arrays.toString(Build.SUPPORTED_ABIS));
            } else {
                sb.append(Build.CPU_ABI);
            }
            return sb.toString();
        }
    }

    //============================================= 吐司类 =====================================

    /**
     * @吐司类
     */
    public static class TOAST {

        public static boolean TOAST_TF = true;      //控制外部所有的 toast 显示
        public static boolean GT_TOAST_TF = false;  //控制内部所有的 toast 显示

        public static boolean isToastTf() {
            return TOAST_TF;
        }

        public static void setToastTf(boolean toastTf) {
            TOAST_TF = toastTf;
        }

        public static boolean isGtToastTf() {
            return GT_TOAST_TF;
        }

        public static void setGtToastTf(boolean gtToastTf) {
            GT_TOAST_TF = gtToastTf;
        }

        /**
         * Toast 自定义 View
         */
        public static class ToastView {

            private static Toast toast;
            private View view;

            public Toast getToast() {
                return toast;
            }

            public void ShowToast() {
                toast.show();
            }


            public View getView() {
                return view;
            }


            /**
             * @param layout 布局
             * @return
             */
            public ToastView initLayout(int layout) {
                if (TOAST.TOAST_TF) {
                    if (context != null && context != null) {
                        view = LayoutInflater.from(context).inflate(layout, null);
                        toast = new Toast(context);
                        toast.setView(view);
                    } else {
                        if (LOG.LOG_TF) {//设置为默认输出日志
                            err("GT_bug", "消息框错误日志：你没有为 Context 进行赋值 ，却引用了 Toast 导致该功能无法实现。解决措施 在调用 toast 代码之前添加：GT.getGT().setactivity(activity);");
                        }
                    }
                }
                return this;
            }

            public ToastView initLayout(int layout, Context context) {
                if (TOAST.TOAST_TF) {
                    if (context != null) {
                        view = LayoutInflater.from(context).inflate(layout, null);
                        toast = new Toast(context);
                        toast.setView(view);
                    } else {
                        if (LOG.LOG_TF) {//设置为默认输出日志
                            err("GT_bug", "消息框错误日志：你没有为 Context 进行赋值 ，却引用了 Toast 导致该功能无法实现。解决措施 在调用 toast 代码之前添加：GT.getGT().setactivity(activity);");
                        }
                    }
                }
                return this;
            }

            /**
             * @param layout  布局
             * @param Gravity Gravity.*****  用这个变量里面的值可以控制显示位置 如果为 0 就显示默认位置
             * @return
             */
            public ToastView initLayout(int layout, int Gravity) {
                if (TOAST.TOAST_TF) {
                    if (context != null && context != null) {
                        view = LayoutInflater.from(context).inflate(layout, null);
                        toast = new Toast(context);
                        if (Gravity != 0)
                            toast.setGravity(Gravity, 0, 0);
                        toast.setView(view);
                    } else {
                        if (LOG.LOG_TF) {//设置为默认输出日志
                            err("GT_bug", "消息框错误日志：你没有为 Context 进行赋值 ，却引用了 Toast 导致该功能无法实现。解决措施 在调用 toast 代码之前添加：GT.getGT().setactivity(activity);");
                        }
                    }
                }
                return this;
            }


            public ToastView initLayout(int layout, int Gravity, Context context) {

                if (TOAST.TOAST_TF) {
                    if (context != null) {
                        view = LayoutInflater.from(context).inflate(layout, null);
                        toast = new Toast(context);
                        if (Gravity != 0)
                            toast.setGravity(Gravity, 0, 0);
                        toast.setView(view);
                    } else {
                        if (LOG.LOG_TF) {//设置为默认输出日志
                            err("GT_bug", "消息框错误日志：你没有为 Context 进行赋值 ，却引用了 Toast 导致该功能无法实现。解决措施 在调用 toast 代码之前添加：GT.getGT().setactivity(activity);");
                        }
                    }
                }
                return this;
            }


        }

    }

    //============================================= 数据类 =====================================

    /**
     * 数据发送与接收类(支持跨进出跨APP发送接收数据)
     * sendUpdateUiBroadcast
     * updateUi
     */
    public static class DataSendReception {

        //发送方法

        /**
         * 发送更新UI广播(简洁版.支持跨APP传输)
         *
         * @param context
         * @param broadcastName 接收方必须用相同的这个地址注册
         * @param data
         */
        public static void sendUpdateUiBroadcast(Object broadcastName, Object data) {
            Context context = getActivity();
            if (context != null && broadcastName != null) {

                //判断是否为class
                if (broadcastName instanceof Class) {
                    Class<?> objClass = (Class<?>) broadcastName;
                    broadcastName = objClass.getName();
                }

                Intent intent = null;
                //判断是否为 Intent
                if (data instanceof Intent) {
                    intent = (Intent) data;
                } else {
                    intent = new Intent();
                    intent.putExtra(broadcastName.toString(), data.toString());
                }
                intent.setAction(broadcastName.toString());
                context.sendBroadcast(intent);
            }
        }

        /**
         * 发送更新UI广播(标准版.支持跨APP传输)
         *
         * @param context
         * @param broadcastName 接收方必须用相同的这个地址注册
         * @param intent
         */
        public static void sendUpdateUiBroadcast(Context context, Object broadcastName, Object data) {
            if (context != null && broadcastName != null && data != null) {
                //判断是否为class
                if (broadcastName instanceof Class) {
                    Class<?> objClass = (Class<?>) broadcastName;
                    broadcastName = objClass.getName();
                }

                Intent intent = null;
                //判断是否为 Intent
                if (data instanceof Intent) {
                    intent = (Intent) data;
                } else {
                    intent = new Intent();
                    intent.putExtra(broadcastName.toString(), data.toString());
                }

                intent.setAction(broadcastName.toString());
                context.sendBroadcast(intent);
            }
        }

        //接收方法

        /**
         * 内部注册监听数据的方法
         *
         * @param registerMessage
         */
        public static void registerUpdateUiBroadcast(Object registerMessage) {
            Context context = getActivity();
            if (context == null || registerMessage == null) {
                errs("注册参数为空，注册监听数据失败！");
                return;
            }

            String actionData = registerMessage.getClass().getName();

            OnReceptionDataListener onReceptionDataListener = null;
            if (registerMessage instanceof OnReceptionDataListener) {
                onReceptionDataListener = (OnReceptionDataListener) registerMessage;
            }
            //注册广播
            UiReceiver uiReceiver = new UiReceiver(actionData, onReceptionDataListener);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(actionData);
            context.registerReceiver(uiReceiver, intentFilter);
        }

        /**
         * 注册监听数据的方法
         *
         * @param context
         * @param broadcastName   接收暗号，必须与发送的暗号一致
         * @param onReceptionData 监听数据反馈接口
         */
        public static void registerUpdateUiBroadcast(Object broadcastName, OnReceptionDataListener onReceptionDataListener) {
            Context context = getActivity();
            if (context == null || broadcastName == null || onReceptionDataListener == null) {
                errs("注册参数为空，注册监听数据失败！");
                return;
            }

            if (broadcastName instanceof Class) {
                Class<?> objClass = (Class<?>) broadcastName;
                broadcastName = objClass.getName();
            }

            //注册广播
            UiReceiver uiReceiver = new UiReceiver(broadcastName, onReceptionDataListener);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(broadcastName.toString());
            context.registerReceiver(uiReceiver, intentFilter);
        }

        /**
         * 注册监听数据的方法
         *
         * @param context
         * @param broadcastName   接收暗号，必须与发送的暗号一致
         * @param onReceptionData 监听数据反馈接口
         */
        public static void registerUpdateUiBroadcast(Context context, Object broadcastName, OnReceptionDataListener onReceptionDataListener) {
            if (context == null || broadcastName == null || onReceptionDataListener == null) {
                errs("注册参数为空，注册监听数据失败！");
                return;
            }

            if (broadcastName instanceof Class) {
                Class<?> objClass = (Class<?>) broadcastName;
                broadcastName = objClass.getName();
            }

            //注册广播
            UiReceiver uiReceiver = new UiReceiver(broadcastName, onReceptionDataListener);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(broadcastName.toString());
            context.registerReceiver(uiReceiver, intentFilter);
        }

        //接收数据接口
        public interface OnReceptionDataListener {
            void updateUi(Context context, Intent intent, String defaultBroadcastName);//刷新UI
        }

        /**
         * 定义一个接收到消息后刷新UI的内部类广播
         */
        public static class UiReceiver extends BroadcastReceiver {
            private OnReceptionDataListener onReceptionDataListener;//监听广播数据的接口
            private String defaultBroadcastName;//默认键

            //构造方法初始化
            public UiReceiver(Object defaultBroadcastName, OnReceptionDataListener onReceptionDataListener) {
                this.onReceptionDataListener = onReceptionDataListener;
                this.defaultBroadcastName = defaultBroadcastName.toString();
            }

            @Override
            public void onReceive(Context context, Intent intent) {
                //是否注销当前广播监听
                if (intent != null) {
                    if (intent.getBooleanExtra("isUnregisterReceiver", false)) {
                        context.unregisterReceiver(this); //注销广播
                        return;
                    }
                }
                onReceptionDataListener.updateUi(context, intent, defaultBroadcastName);//接收数据
            }

        }

        /**
         * 注销广播
         *
         * @param broadcastName 要关闭广播的名字
         */
        public static void unregisterReceiver(Object broadcastName) {
            Context context = getActivity();
            if (context != null && broadcastName != null) {
                //判断是否为class
                if (broadcastName instanceof Class) {
                    Class<?> objClass = (Class<?>) broadcastName;
                    broadcastName = objClass.getName();
                }
                Intent intent = new Intent();
                intent.putExtra("isUnregisterReceiver", true);
                intent.setAction(broadcastName.toString());
                context.sendBroadcast(intent);
            }
        }

    }

    /**
     * 对 Object 的增删查改操作
     */
    public static class SaveObject {

        public static interface SaveBean extends Serializable {
        }

        /**
         * 保存Object
         *
         * @param context
         * @param obj
         * @return 是否保存成功
         */
        public static synchronized boolean saveObject(Context context, Object obj) {
            if (context == null || obj == null) {
                errs("保存的参数为 null");
                return false;
            }
            try {
                File file = new File(context.getFilesDir().getPath() + obj.getClass().getName());
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                out.writeObject(obj);//存储Object
                out.close();//关闭存储流
            } catch (IOException e) {
                GT.errs("异常:" + e);
                e.printStackTrace();
                return false;
            }
            return true;
        }

        /**
         * 删除Object
         *
         * @param context
         * @param cla
         * @return
         */
        public static synchronized boolean deleteObject(Context context, Class<?> cla) {
            if (context == null || cla == null) {
                errs("删除的参数为 null");
                return false;
            }
            try {
                File file = new File(context.getFilesDir().getPath() + cla.getName());
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                Object obj = new Object();//保证不会空指针
                try {
                    obj = cla.newInstance();//实体化
                } catch (IllegalAccessException e) {
                    GT.errs("异常:" + e);
                    e.printStackTrace();
                    return false;
                } catch (InstantiationException e) {
                    GT.errs("异常:" + e);
                    e.printStackTrace();
                    return false;
                }
                out.writeObject(obj);//存储Object
                out.close();//关闭存储流
            } catch (IOException e) {
                GT.errs("异常:" + e);
                e.printStackTrace();
                return false;
            }
            return true;
        }

        /**
         * 获取Object
         *
         * @param context
         * @param cla
         * @param <T>
         * @return
         */
        public static synchronized <T> T queryObject(Context context, Class<T> cla) {

            if (context == null || cla == null) {
                errs("查询的参数为 null");
                return null;
            }
            T t = null;
            try {
                File file = new File(context.getFilesDir().getPath() + cla.getName());
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                t = (T) in.readObject();
                in.close();//关闭反序列化数据流
            } catch (IOException | ClassNotFoundException e) {
                GT.errs("异常:" + e);
                e.printStackTrace();
            }
            return t;

        }

        /**
         * 更改Object
         *
         * @param context
         * @param obj
         * @return
         */
        public static synchronized boolean updateObject(Context context, Object obj) {
            if (context == null || obj == null) {
                errs("修改的参数为 null");
                return false;
            }
            try {
                File file = new File(context.getFilesDir().getPath() + obj.getClass().getName());
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                out.writeObject(obj);//存储Object
                out.close();//关闭存储流
            } catch (IOException e) {
                GT.errs("异常:" + e);
                e.printStackTrace();
                return false;
            }
            return true;
        }

    }

    /**
     * 数据持久化 SharedPreferences
     */
    public static class GT_SharedPreferences implements SaveObject.SaveBean {

        //构建 GT_SharedPreferences
        @Target(ElementType.FIELD)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface Build {
            String setSpName() default "GT";       //默认SP名称

            boolean setISCommit() default true;     //默认自动提交
        }

        private Context context;

        public void commit() {
            sp_e.apply();
        }  // 使用 apply 手动提交 如果提交后还有后续操作，建议使用 apply，先会写入内存，然后再异步写入磁盘

        public void clear() {
            sp_e.clear();
            sp_e.commit();
        }//清空    //如果使用 commit 来提交事务，是直接写入磁盘 ，如果需要频繁的提交的话， apply 的性能会优于 commit

        private SharedPreferences sp;

        public SharedPreferences getSharedPreferences() {
            return sp;
        }           //获取 SharedPreferences

        private SharedPreferences.Editor sp_e;

        public SharedPreferences.Editor getEditor() {
            return sp_e;
        }   //获取 SharedPreferences.Editor

        private boolean commit = false;             //定义是否自动提交

        /**
         * 初始化 SP
         *
         * @param context     上下文
         * @param SPName      存储的名字
         * @param permissions 存储可读取的权限
         * @param commit      是否自动提交
         */
        public GT_SharedPreferences(Context context, String sPName, boolean commit) {
            this.context = context;
            this.commit = commit;
            sp = context.getSharedPreferences(sPName, 0);//打开 或 创建 SharedPreferences
            sp_e = sp.edit();//让userData处于编辑状态
        }

        /**
         * 增加
         *
         * @param key    保存数据的 key
         * @param object 存储的数据
         * @return 返回 sp 操作对象
         */
        public GT_SharedPreferences save(String key, Object object) {
            boolean TF = true;//监测保存状态是否正常
            if (object instanceof String) {
                sp_e.putString(key, object.toString());
            } else if (object instanceof Integer) {
                sp_e.putInt(key, (Integer) object);
            } else if (object instanceof Long) {
                sp_e.putLong(key, (Long) object);
            } else if (object instanceof Float) {
                sp_e.putFloat(key, (Float) object);
            } else if (object instanceof Boolean) {
                sp_e.putBoolean(key, (Boolean) object);
            } else if (object instanceof Set) {
                sp_e.putStringSet(key, (Set) object);
            } else {
                if (LOG.GT_LOG_TF) log(context, "进行对象保存");
                String json = JSON.toJson(object);
                String json_class = object.getClass().toString();
                sp_e.putString(key, json);                           //保存对象的 Json 数据
                sp_e.putString(key + "_class", json_class);            //保存对象的 class 数据
            }
            if (commit && TF) sp_e.apply();  //如果设置了自动提交 并且 保存状态正常 即可自定提交
            return this;

        }

        /**
         * 删除数据
         *
         * @param key 删除的 key
         * @return 返回 sp 操作对象
         */
        public SharedPreferences.Editor delete(String key) {
            if (query(key) != null) {
                sp_e.remove(key);
                if (commit) sp_e.apply();
            } else {
                if (LOG.GT_LOG_TF) log("删除失败  当前 sp 中无此 key");
            }
            return sp_e;
        }

        /**
         * 修改数据
         *
         * @param key    修改的 key
         * @param object 新的数据
         * @return 返回 sp 操作类
         */
        public GT_SharedPreferences updata(String key, Object object) {
            if (query(key) != null) {
                if (LOG.GT_LOG_TF)
                    log(context, "进入到 updata 查询的数据不为null");
                save(key, object);
            }
            return this;
        }

        /**
         * 查询数据
         *
         * @param key 查询数据的 key
         * @return 返回 sp 操作类
         */
        public Object query(String key) {
            Object obj = null;
            try {
                obj = sp.getInt(key, 0);
            } catch (ClassCastException e1) {
                if (LOG.GT_LOG_TF)
                    log(context, "Int 数据装换异常");
                try {
                    String str_class = sp.getString(key + "_class", null);     //获取对象 class 数据
                    String str = sp.getString(key, null);                          //获取对象 Json  数据
                    if (str_class == null) {      //如果 class 数据为空
                        obj = str;              //普通的 Json 数据
                    } else {
                        Object object_class = getObj(str_class);    //通过对象的 class 反射出 实例对象
                        try {
                            obj = JSON.fromJson(str, object_class.getClass());     //通过 JSON 与 实例对象 获取相应的 Object 对象
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (ClassCastException e2) {
                    if (LOG.GT_LOG_TF)
                        log(context, "String 数据装换异常");
                    try {
                        obj = sp.getLong(key, 0);
                    } catch (ClassCastException e3) {
                        if (LOG.GT_LOG_TF)
                            log(context, "Long 数据装换异常");
                        try {
                            obj = sp.getFloat(key, 0f);
                        } catch (ClassCastException e4) {
                            if (LOG.GT_LOG_TF)
                                log(context, "Float 数据装换异常");
                            try {
                                obj = sp.getBoolean(key, false);
                            } catch (ClassCastException e5) {
                                if (LOG.GT_LOG_TF)
                                    log(context, "Boolean 数据装换异常");
                                try {
                                    obj = sp.getStringSet(key, null);
                                } catch (ClassCastException e6) {
                                    if (LOG.GT_LOG_TF)
                                        log(context, "StringSet 数据装换异常");
                                    obj = null;
                                }
                            }
                        }
                    }
                }
            }
            return obj;
        }

        /**
         * 可返回指定类型
         *
         * @param key
         * @param dataType
         * @param <T>
         * @return
         */
        public <T> T query(String key, Class<T> dataType) {
            Object obj = null;
            if (dataType == Integer.class) {
                obj = sp.getInt(key, 0);
            } else if (dataType == String.class) {
                obj = sp.getString(key, null);
            } else if (dataType == Long.class) {
                obj = sp.getLong(key, 0);
            } else if (dataType == Float.class) {
                obj = sp.getFloat(key, 0);
            } else if (dataType == Boolean.class) {
                obj = sp.getBoolean(key, false);
            } else if (dataType == Set.class) {
                obj = sp.getStringSet(key, null);
            } else {// Bean
                String str_class = sp.getString(key + "_class", null);     //获取对象 class 数据
                String str = sp.getString(key, null);                          //获取对象 Json  数据
                if (str_class == null) {      //如果 class 数据为空
                    obj = str;              //普通的 Json 数据
                } else {
                    Object object_class = getObj(str_class);    //通过对象的 class 反射出 实例对象
                    try {
                        obj = JSON.fromJson(str, object_class.getClass());     //通过 JSON 与 实例对象 获取相应的 Object 对象
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            return (T) obj;
        }

        /**
         * 查 List
         *
         * @param key  查 list 的 key
         * @param type 数据的类型
         * @return 返回 sp 操作类
         */
        public Object queryList(String key, Type type) {
            /**
             *  字符串 转 List 参数说明：
             *
             * key：与之前 save 是的 key
             *  type：
             *  Type type = new TypeToken<List<***Bean>>(){}.getType()
             *
             *  所以你只要传一个 new TypeToken<List<***Bean>>(){}.getType() 即可
             *  ***Bean 解释:   List<***Bean>   是之前 save 时 List的泛型
             *
             */
            Object obj = null;
            String str_class = sp.getString(key + "_class", null);     //获取对象 class 数据
            String str = sp.getString(key, null);                          //获取对象 Json  数据
            if (str_class != null && str_class.equals("class java.util.ArrayList")) {      //如果 class 数据为空
                try {
                    obj = JSON.fromJson(str, type.getClass());//字符串转 List
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return obj;
        }

        /**
         * 通过 对象的.class 属性反射对象
         *
         * @param objectClass 全类名
         * @return 返回 反射的实体类
         */
        private Object getObj(String objectClass) {
            Object obj = null;
            String[] strs = objectClass.split(" ");
            String str = strs[1];
            Class<?> clazz = getClass();
            try {
                clazz = Class.forName(str);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            try {
                obj = clazz.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            return obj;        //返回实例化好的类

        }

        /**
         * 获取所有
         *
         * @return 返回 map
         */
        public Map<String, ?> queryAll() {
            return sp.getAll();
        }

    }

    /**
     * GT_IO  Android 的 内部 IO 流
     */
    public static class GT_IO {

        /**
         * 使用实例：
         * 第一步：
         * GT.GT_IO io = new GT.GT_IO(this);//创建 IO 对象
         * 第二步：
         * io.save(editText.getText().toString(),"King");//保存数据
         * 第三步：
         * String king = io.query("King"); //获取数据
         */

        private Context context;

        /**
         * 实例化 上下文
         *
         * @param context
         */
        public GT_IO(Context context) {
            this.context = context;
        }

        /**
         * 保存数据
         *
         * @param saveData 保存的数据
         * @param dataName 保存文件名称
         * @return 返回 IO 操作对象
         */
        public GT_IO save(String saveData, String dataName) {

            FileOutputStream fos = null;//文件输出流
            try {
                fos = context.openFileOutput(dataName, context.MODE_PRIVATE);//获取文件输出流对象
                fos.write(saveData.getBytes());//保存备忘信息
                fos.flush();//清除缓存
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();//关闭输出流
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return this;
        }

        /**
         * 查询文件
         *
         * @param dataName 文件绝对路径包括文件名
         * @return
         */
        public String query(String dataName) {
            FileInputStream fis = null;//文件输入流对象
            String data = null;
            byte[] buffer = null;
            try {
                fis = context.openFileInput(dataName);//获得文件输入流对象
                buffer = new byte[fis.available()];//实例化字节数组
                fis.read(buffer);//从输入流中读取数据
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();//关闭输入流对象
                        data = new String(buffer);//把字节数组中的数据转换为字符串
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return data;
        }

    }

    /**
     * 增强版外部存储
     */
    public static class FileUtils {

        /**
         * 保存单个文件
         *
         * @param saveData 文件保存的数据
         * @param savePaht 文件保存的路径
         * @param fileName 文件名与扩展名
         */
        public static void saveData(String saveData, String savePaht, String fileName) {
            save(saveData, savePaht, fileName);
        }

        /**
         * 保存多个文件
         *
         * @param savePaht 多个文件保存的地址
         * @param saveMap  保存多个文件的数据 key = 文件名与扩展名  value = 文件里的数据
         */
        public static void saveDataAll(String savePaht, Map<String, String> saveMap) {
            for (String key : saveMap.keySet()) {
                save(saveMap.get(key), savePaht, key);
            }
        }

        /**
         * 文件是否存在
         *
         * @param filePathAddFileName
         * @return
         */
        public static boolean fileExist(String filePath) {
            return new File(filePath).exists();
        }

        /**
         * 该文件是否为文件夹
         *
         * @param filePath
         * @return
         */
        public static boolean fileIsDirectory(String filePath) {
            File file = new File(filePath);
            boolean isFolder = false;
            if (file.exists()) {
                isFolder = file.isDirectory();
            }
            return isFolder;
        }

        /**
         * 保存文件数据
         *
         * @param saveData
         * @param savePaht
         * @param fileName
         */
        public static void save(String saveData, String savePaht, String fileName) {
            File fileNull = new File(savePaht);
            if (!fileNull.exists()) {
                fileNull.mkdirs();
            }

            File file = new File(savePaht, fileName);
            FileOutputStream fos = null;

            try {
                fos = new FileOutputStream(file);
                fos.write(saveData.getBytes());
                fos.flush();
            } catch (FileNotFoundException var18) {
                var18.printStackTrace();
            } catch (IOException var19) {
                var19.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException var17) {
                        var17.printStackTrace();
                    }
                }

            }

        }

        /**
         * 查询文件数据
         *
         * @param queryPaht
         * @param fileName
         * @return
         */
        public static String query(String queryPaht, String fileName) {
            File fileNull = new File(queryPaht);
            if (!fileNull.exists()) {
                fileNull.mkdirs();
            }

            File file = new File(queryPaht, fileName);
            FileInputStream fis = null;
            byte[] buffer = null;
            String data = null;

            try {
                fis = new FileInputStream(file);
                buffer = new byte[fis.available()];
                fis.read(buffer);
            } catch (FileNotFoundException var19) {
                var19.printStackTrace();
            } catch (IOException var20) {
                var20.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                        data = new String(buffer);
                    } catch (IOException var18) {
                        var18.printStackTrace();
                    }
                }

            }

            return data;
        }

        /**
         * 查询当前文件路径汇总有多少个文件
         *
         * @param filePath
         * @return
         */
        public static List<String> queryFilePathFileNumber(String filePath) {

            List<String> fileNameList = new ArrayList<>();

            //判断当前路径是否存在
            File file = new File(filePath);
            if (!file.exists()) {
                return fileNameList;
            }

            try {
                File[] files = file.listFiles();
                if (files == null || files.length == 0) return fileNameList;
                for (File fileOne : files) {
                    if (fileOne.isDirectory()) {
                        fileNameList.add("目录:" + fileOne.getName());
                    } else {
                        fileNameList.add("文件:" + fileOne.getName());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return fileNameList;
            }
            return fileNameList;
        }

        /**
         * 获取文件描述
         *
         * @param filePath
         * @param fileName
         * @return
         */
        public static String getFileSizeDescriptor(String filePath, String fileName) {

            try {
                long fileSize = getFileSize(new File(filePath, fileName));
                String fileDescriptor = formetFileSize(fileSize);
                return fileDescriptor;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * 获取指定文件大小
         *
         * @param file
         * @return
         * @throws Exception
         */
        public static long getFileSize(File file) throws Exception {
            long size = 0;
            if (file.exists()) {
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                size = fis.available();
            } else {
                file.createNewFile();
            }
            return size;
        }

        /**
         * 转换文件大小
         *
         * @param fileS
         * @return
         */
        public static String formetFileSize(long fileS) {
            DecimalFormat df = new DecimalFormat("#.00");
            String fileSizeString = "";
            String wrongSize = "0B";
            if (fileS == 0) {
                return wrongSize;
            }
            if (fileS < 1024) {
                fileSizeString = df.format((double) fileS) + "B";
            } else if (fileS < 1048576) {
                fileSizeString = df.format((double) fileS / 1024) + "KB";
            } else if (fileS < 1073741824) {
                fileSizeString = df.format((double) fileS / 1048576) + "MB";
            } else {
                fileSizeString = df.format((double) fileS / 1073741824) + "GB";
            }
            return fileSizeString;
        }

        /**
         * 删除当前目录下所有的文件已经子文件
         *
         * @param deleteFile
         */
        public static void deleteAllFiles(File deleteFile, boolean deleteThisFile) {

            if (!deleteFile.exists()) {
                return;
            }

            File files[] = deleteFile.listFiles();
            if (files != null && files.length != 0) {
                for (File file : files) {
                    if (file.isDirectory()) { // 判断是否为文件夹
                        deleteAllFiles(file, deleteThisFile);
                        try {
                            file.delete();
                        } catch (Exception e) {
                        }
                    } else {
                        if (file.exists()) { // 判断是否存在
                            deleteAllFiles(file, deleteThisFile);
                            try {
                                file.delete();
                            } catch (Exception e) {
                            }
                        }
                    }
                }

                //是否删除当前文件
                if (deleteThisFile) {
                    try {
                        deleteFile.delete();
                    } catch (Exception e) {
                    }
                }

            } else {
                try {
                    deleteFile.delete();
                } catch (Exception e) {
                }
            }
        }

        /**
         * 重命名
         *
         * @param path
         * @param oldName
         * @param newName
         */
        public static void changeFileDirName(String path, String oldName, String newName) {
            File oldFile = new File(path + "/" + oldName);
            File newFile = new File(path + "/" + newName);

            oldFile.renameTo(newFile);
        }

        /**
         * getUsb路径
         *
         * @return
         */
        public static List<String> getUsbPath() {
            List<String> usbData = getUsbData();
            if (usbData == null || usbData.size() == 0) return null;
            List<String> usbPath = new ArrayList<>();
            for (String userData : usbData) {
                userData = userData.substring(userData.lastIndexOf("/") + 1);
                usbPath.add("/storage/" + userData + "/");
            }
            return usbPath;
        }

        /**
         * getUsb路径
         *
         * @return
         */
        public static List<String> getUsbName() {
            List<String> usbData = getUsbData();
            if (usbData == null || usbData.size() == 0) return null;
            List<String> usbName = new ArrayList<>();
            for (String userData : usbData) {
                userData = userData.substring(userData.lastIndexOf("/") + 1);
                usbName.add(userData);
            }
            return usbName;
        }

        /**
         * 获取USB信息
         *
         * @return
         */
        public static List<String> getUsbData() {
            String filePath = "/proc/mounts";
            File file = new File(filePath);
            List<String> lineList = new ArrayList<>();
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);
                if (inputStream != null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "GBK");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        if (line.contains("vfat")) {
                            lineList.add(line);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (lineList.size() == 0) return null;
            List<String> usbPaths = new ArrayList<>();
            for (int i = 0; i < lineList.size(); i++) {
                String editPath = lineList.get(i);
                int start = editPath.indexOf("/mnt");
                int end = editPath.indexOf(" vfat");
                usbPaths.add(editPath.substring(start, end));
            }
            return usbPaths;
        }

        /**
         * 复制单个文件
         *
         * @param fromFileStr
         * @param toFileStr
         * @return
         */
        public static void copyFile(String fromFileStr, String toFileStr) {

            File fromFile = new File(fromFileStr);
            File toFile = new File(toFileStr);

            if (!fromFile.exists()) {
                return;
            }

            //开始拷贝文件
            copy(fromFileStr, toFileStr);
        }

        /**
         * 拷贝文件
         *
         * @param fromFile
         * @param toFile
         * @return
         */
        private static int copy(String fromFile, String toFile) {
            try {
                InputStream inputStream = new FileInputStream(fromFile);
                OutputStream outputStream = new FileOutputStream(toFile);
                byte bt[] = new byte[1024];
                int d;
                while ((d = inputStream.read(bt)) > 0) {
                    outputStream.write(bt, 0, d);
                }
                inputStream.close();
                outputStream.close();
                return 0;
            } catch (Exception e) {
                return -1;
            }
        }

        /**
         * 复制所有文件
         *
         * @param fromFile
         * @param toFile
         * @return
         */
        public static int copyAllFile(String fromFile, String toFile) {
            //要复制的文件目录
            File[] currentFiles;
            File root = new File(fromFile);
            //如同判断SD卡是否存在或者文件是否存在
            //如果不存在则 return出去
            if (!root.exists()) {
                return -1;
            }
            //如果存在则获取当前目录下的全部文件 填充数组
            currentFiles = root.listFiles();
            if (currentFiles == null) {//如果只有一个文件，那就使用拷贝一个文件的方法
                FileUtils.copyFile(fromFile, toFile);
                return 0;
            }

            //目标目录
            File targetDir = new File(toFile);
            //创建目录
            if (!targetDir.exists()) {
                targetDir.mkdirs();
            }
            //遍历要复制该目录下的全部文件
            for (int i = 0; i < currentFiles.length; i++) {
                if (currentFiles[i].isDirectory())//如果当前项为子目录 进行递归
                {
//                GT.log("1 " + currentFiles[i].getPath() + "/" + "--------" + toFile + "/" + currentFiles[i].getName() + "/");
                    copyAllFile(currentFiles[i].getPath() + "/", toFile + "/" + currentFiles[i].getName() + "/");

                } else//如果当前项为文件则进行文件拷贝
                {
//                GT.log("2 " + currentFiles[i].getPath() + "--------" + toFile + "/" + currentFiles[i].getName());
                    CopySdcardFile(currentFiles[i].getPath(), toFile + "/" + currentFiles[i].getName());
                }
            }
            return 0;
        }

        /**
         * 要复制的目录下的所有非子目录(文件夹)文件拷贝
         *
         * @param fromFile
         * @param toFile
         * @return
         */
        private static int CopySdcardFile(String fromFile, String toFile) {
            try {
                InputStream fosfrom = new FileInputStream(fromFile);
                OutputStream fosto = new FileOutputStream(toFile);
                byte bt[] = new byte[1024];
                int c;
                while ((c = fosfrom.read(bt)) > 0) {
                    fosto.write(bt, 0, c);
                }
                fosfrom.close();
                fosto.close();
                return 0;

            } catch (Exception ex) {
                return -1;
            }
        }

        /**
         * 获取文件夹中所有文件名
         *
         * @param path
         * @return
         */
        public static List<String> getFilesAllName(String path) {
            File file = new File(path);
            File[] files = file.listFiles();
            if (files == null) {
                Log.e("error", "空目录");
                return null;
            }
            List<String> s = new ArrayList<String>();
            for (int i = 0; i < files.length; i++) {
                s.add(files[i].getAbsolutePath());
            }
            return s;
        }

        /**
         * 清空文件夹中所有文件
         *
         * @param file         清空的文件路径
         * @param isSaveFolder 是否保存当前文件夹 true：b
         */
        public static void deleteAllFile(File file, boolean isSaveFolder) {

            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    File f = files[i];
                    deleteAllFile(f, isSaveFolder);
                }
                if (!isSaveFolder) {// 是否保留本文件夹
                    file.delete();// 如要保留文件夹，只删除文件，请注释这行
                }
            } else if (file.exists()) {
                file.delete();
            }
        }

        /**
         * 获取手机根目录
         *
         * @return
         */
        public static String getAppDirectory(Context context, boolean isNew) {
            if (isNew) {
                if (Build.VERSION.SDK_INT >= 26) {
                    return ContextCompat.getExternalFilesDirs(context, Environment.DIRECTORY_DCIM)[0].getAbsolutePath();
                } else {
                    return Environment.getExternalStorageDirectory().toString();
                }
            }
            return Environment.getExternalStorageDirectory().toString();
        }


        //自定义U盘读写权限
        private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
        private Context context;
        private OnUSB_flashDiskListener onUSB_flashDiskListener;

        public FileUtils(Context context) {
            this.context = context;
        }

        /**
         * U盘事件监听
         *
         * @param onUSB_flashDiskListener
         */
        public void onUSB_flashDiskListener(OnUSB_flashDiskListener onUSB_flashDiskListener) {
            this.onUSB_flashDiskListener = onUSB_flashDiskListener;
            IntentFilter usbDeviceStateFilter = new IntentFilter();
            usbDeviceStateFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
            usbDeviceStateFilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
            context.registerReceiver(mOtgReceiver, usbDeviceStateFilter);
            //注册监听自定义广播
            IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
            context.registerReceiver(mOtgReceiver, filter);
        }

        /**
         * @description OTG广播，监听U盘的插入及拔出
         * @author ldm
         * @time 2017/9/1 17:20
         * @param
         */
        private BroadcastReceiver mOtgReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                switch (action) {
                    case ACTION_USB_PERMISSION://接受到自定义广播
                        UsbDevice usbDevice = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                        //允许权限申请
                        if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                            if (usbDevice != null) {
                                //用户已授权，可以进行读取操作
                                UsbFile usbFile = readDevice(getUsbMass(usbDevice));//成功读取信息的
                                onUSB_flashDiskListener.onPermissionObtainedSuccessfully(usbDevice, usbFile);
                            } else {
                                onUSB_flashDiskListener.onError("No USB flash drive is inserted");
                            }
                        } else {
                            onUSB_flashDiskListener.onObtainingPermissionFailed(usbDevice);
                        }
                        break;
                    case UsbManager.ACTION_USB_DEVICE_ATTACHED://接收到U盘设备插入广播
                        UsbDevice device_add = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                        if (device_add != null) {
                            //接收到U盘插入广播，尝试读取U盘设备数据
                            onUSB_flashDiskListener.onReceiveInsertBroadcast(device_add);
                            redUDiskDevsList();//很难读取到信息的
                        }
                        break;
                    case UsbManager.ACTION_USB_DEVICE_DETACHED://接收到U盘设设备拔出广播
                        onUSB_flashDiskListener.onReceivePullOutBroadcast();
                        break;
                }
            }
        };

        /**
         * @description U盘设备读取
         * @author ldm
         * @time 2017/9/1 17:20
         */
        private void redUDiskDevsList() {
            //设备管理器
            UsbManager usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
            //获取U盘存储设备
            storageDevices = UsbMassStorageDevice.getMassStorageDevices(context);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
            //一般手机只有1个OTG插口
            for (UsbMassStorageDevice device : storageDevices) {
                //读取设备是否有权限
                if (device == null) continue;
                if (usbManager.hasPermission(device.getUsbDevice())) {
                    readDevice(device);
                } else {
                    //没有权限，进行申请
                    usbManager.requestPermission(device.getUsbDevice(), pendingIntent);
                }
            }
            if (storageDevices.length == 0) {
                onUSB_flashDiskListener.onError("The usb flash drive is unavailable");
            }
        }

        //当前处接U盘列表
        private UsbMassStorageDevice[] storageDevices;

        private UsbMassStorageDevice getUsbMass(UsbDevice usbDevice) {
            for (UsbMassStorageDevice device : storageDevices) {
                if (usbDevice.equals(device.getUsbDevice())) {
                    return device;
                }
            }
            return null;
        }

        //当前U盘所在文件目录

        private UsbFile readDevice(UsbMassStorageDevice device) {
            try {
                device.init();//初始化
                //设备分区
                Partition partition = device.getPartitions().get(0);
                //文件系统
                FileSystem currentFs = partition.getFileSystem();
                String volumeLabel = currentFs.getVolumeLabel();//可以获取到设备的标识
                //通过FileSystem可以获取当前U盘的一些存储信息，包括剩余空间大小，容量等等
                //U盘信息
                USB_Bean usb_bean = new USB_Bean();
                usb_bean.setCapacity(formetFileSize(currentFs.getCapacity()));
                usb_bean.setHaveBeenUsed(formetFileSize(currentFs.getOccupiedSpace()));
                usb_bean.setFreeSpace(formetFileSize(currentFs.getFreeSpace()));
                usb_bean.setBlockSize(formetFileSize(currentFs.getChunkSize()));
                usb_bean.setType(currentFs.getType());
                onUSB_flashDiskListener.onU_DiskInformation(usb_bean);
                return currentFs.getRootDirectory();//设置当前文件对象为根目录
            } catch (Exception e) {
                onUSB_flashDiskListener.onError(e.toString());
            }
            return null;
        }

        //USB 接口
        public static interface OnUSB_flashDiskListener {
            void onReceiveInsertBroadcast(UsbDevice device_add);//接收插入广播

            void onPermissionObtainedSuccessfully(UsbDevice usbDevice, UsbFile usbFile);//获取权限成功

            void onObtainingPermissionFailed(UsbDevice usbDevice);//获取权限失败

            void onU_DiskInformation(USB_Bean usbBean);//U盘信息

            void onError(String message);//U盘异常

            void onReceivePullOutBroadcast();//接收拔出广播
        }

        //U盘的实体类信息
        public class USB_Bean {

            private String capacity;        //容量
            private String haveBeenUsed;    //当前占用容量
            private String freeSpace;       //空闲空间
            private String blockSize;       //块大小
            private int type;               //类型

            public USB_Bean() {
                super();
            }

            public USB_Bean(String capacity, String haveBeenUsed, String freeSpace, String blockSize, int type) {
                this.capacity = capacity;
                this.haveBeenUsed = haveBeenUsed;
                this.freeSpace = freeSpace;
                this.blockSize = blockSize;
                this.type = type;
            }

            public String getCapacity() {
                return capacity;
            }

            public void setCapacity(String capacity) {
                this.capacity = capacity;
            }

            public String getHaveBeenUsed() {
                return haveBeenUsed;
            }

            public void setHaveBeenUsed(String haveBeenUsed) {
                this.haveBeenUsed = haveBeenUsed;
            }

            public String getFreeSpace() {
                return freeSpace;
            }

            public void setFreeSpace(String freeSpace) {
                this.freeSpace = freeSpace;
            }

            public String getBlockSize() {
                return blockSize;
            }

            public void setBlockSize(String blockSize) {
                this.blockSize = blockSize;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            @Override
            public String toString() {
                return "USB_Bean{" +
                        "capacity='" + capacity + '\'' +
                        ", haveBeenUsed='" + haveBeenUsed + '\'' +
                        ", freeSpace='" + freeSpace + '\'' +
                        ", blockSize='" + blockSize + '\'' +
                        ", type=" + type +
                        '}';
            }
        }

        /**
         * 读取SD卡文件与写入USB中
         *
         * @param os
         * @param is
         * @param sdFile
         * @param onUSBSaveListener
         * @throws IOException
         */
        private static void redFileStream(OutputStream os, InputStream is, File sdFile, OnUSB_SaveListener onUSBSaveListener) throws IOException {
            int length = (int) sdFile.length();
            int count = 0;
            byte[] buffer = new byte[1024];
            while (true) {
                int numread = is.read(buffer);
                count += numread;
                int mProgress = (int) (((float) count / length) * 100);
                if (onUSBSaveListener != null)
                    onUSBSaveListener.onInProgress(mProgress);
                if (numread < 0) break;//下载完成
                os.write(buffer, 0, numread);
            }
            os.flush();
            os.close();
            is.close();
        }

        /**
         * 把本地文件写入到U盘中
         *
         * @param sdFile            SD卡中的文件
         * @param usbFile           USB盘文件对象
         * @param uDiskPath         USB文件路径(可写纯路径或路径+完整文件名)如：测试路径/AA/拷贝的数据.xls    测试路径/AA
         * @param onUSBSaveListener 监听USB接口
         * @return
         */
        public static boolean saveSDFile2OTG(final File sdFile, UsbFile usbFile, String uDiskPath, OnUSB_SaveListener onUSBSaveListener) {
            UsbFile uFile;
            FileInputStream fis;
            try {
                fis = new FileInputStream(sdFile);//读取选择的文件的
                if (usbFile.isDirectory()) {//如果选择是个文件夹
                    UsbFile[] usbFiles = usbFile.listFiles();
                    if (usbFiles.length > 0) {
                        for (UsbFile file : usbFiles) {
                            if (file.getName().equals(sdFile.getName())) {
                                file.delete();
                            }
                        }
                    }

                    //创建U盘中的文件夹
                    FileBean fileBean = parseFilePath(uDiskPath);
                    List<String> filePathList = fileBean.getFilePathList();
                    if (filePathList != null) {
                        for (String folder : filePathList)
                            usbFile = usbFile.createDirectory(folder);
                    }

                    //设置文件名称
                    String fileName = sdFile.getName();
                    if (fileBean.fileName != null) {
                        fileName = fileBean.fileName;
                    }

                    //开始创建U盘文件
                    uFile = usbFile.createFile(fileName);
                    UsbFileOutputStream uos = new UsbFileOutputStream(uFile);
                    try {
                        if (onUSBSaveListener != null)
                            onUSBSaveListener.onStart();
                        redFileStream(uos, fis, sdFile, onUSBSaveListener);
                        if (onUSBSaveListener != null)
                            onUSBSaveListener.onClose(true);
                        return true;
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (onUSBSaveListener != null)
                            onUSBSaveListener.onClose(false);
                    }
                }
            } catch (final Exception e) {
                e.printStackTrace();
                if (onUSBSaveListener != null)
                    onUSBSaveListener.onClose(false);
                return false;
            }
            if (onUSBSaveListener != null)
                onUSBSaveListener.onClose(false);
            return false;
        }

        /**
         * 解析路径
         *
         * @param uDiskPath
         * @return
         */
        public static FileBean parseFilePath(String uDiskPath) {
            FileBean fileBean = new FileBean();
            List<String> list = null;
            String[] filePaths = uDiskPath.split("/");
            if (uDiskPath.contains(".")) {
                // 指定了文件名
                if (uDiskPath.contains("/")) {
                    // 指定了路径
                    list = new ArrayList<>();
                    for (int i = 0; i < filePaths.length - 1; i++) {
                        list.add(filePaths[i]);
                    }
                    fileBean.setFilePath(uDiskPath.substring(0, uDiskPath.lastIndexOf("/")));
                    String fileName = uDiskPath.substring(uDiskPath.lastIndexOf("/") + 1, uDiskPath.length());
                    fileBean.setFileName(fileName);
                } else {
                    // 未指定路径
                    fileBean.setFileName(uDiskPath);
                }
            } else {
                if (uDiskPath.contains("/")) {
                    // 纯路径
                    fileBean.setFilePath(uDiskPath.substring(0, uDiskPath.lastIndexOf("/")));
                }

            }
            fileBean.setFilePathList(list);
            return fileBean;

        }

        public static class FileBean {
            private String fileName;// 文件名
            private List<String> filePathList;// 文件路径
            private String filePath;// 文件路径

            public FileBean() {
            }

            public FileBean(String fileName, List<String> filePathList, String filePath) {
                this.fileName = fileName;
                this.filePathList = filePathList;
                this.filePath = filePath;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public List<String> getFilePathList() {
                return filePathList;
            }

            public void setFilePathList(List<String> filePathList) {
                this.filePathList = filePathList;
            }

            public String getFilePath() {
                return filePath;
            }

            public void setFilePath(String filePath) {
                this.filePath = filePath;
            }

            @Override
            public String toString() {
                return "FileBean{" +
                        "fileName='" + fileName + '\'' +
                        ", filePathList=" + filePathList +
                        ", filePath='" + filePath + '\'' +
                        '}';
            }
        }

        //USB 接口
        public interface OnUSB_SaveListener {
            void onStart();//任务开始

            void onInProgress(int progress);//获取权限成功

            void onClose(boolean copyState);//任务结束

        }

        /**
         * 读取 USB设备列表
         *
         * @return USB设备列表
         */
        public UsbMassStorageDevice[] getDeviceList() {
            UsbManager usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
            //获取存储设备
            storageDevices = UsbMassStorageDevice.getMassStorageDevices(context);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
            //可能有几个 一般只有一个 因为大部分手机只有1个otg插口
            for (UsbMassStorageDevice device : storageDevices) {
//            Log.e(TAG, device.getUsbDevice().getDeviceName());
                //有就直接读取设备是否有权限
                if (!usbManager.hasPermission(device.getUsbDevice())) {
                    //没有权限请求权限
                    usbManager.requestPermission(device.getUsbDevice(), pendingIntent);
                }
            }
            return storageDevices;
        }

        /**
         * 读取 USB 内文件夹下文件列表
         *
         * @param usbFolder usb文件夹
         * @return 文件列表
         */
        public ArrayList<UsbFile> getUsbFolderFileList(UsbFile usbFolder) {
            //更换当前目录
            ArrayList<UsbFile> usbFiles = new ArrayList<>();
            try {
                Collections.addAll(usbFiles, usbFolder.listFiles());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return usbFiles;
        }

        /**
         * 复制 USB文件到本地
         *
         * @param targetFile        需要复制的文件
         * @param savePath          复制的目标文件路径
         * @param onUSBSaveListener 下载进度回调
         * @return 复制结果
         */
        public boolean saveUSbFileToLocal(UsbFile targetFile, String savePath, OnUSB_SaveListener onUSBSaveListener) {
            boolean result;
            try {
                //开始写入
                UsbFileInputStream uis = new UsbFileInputStream(targetFile);//读取选择的文件的
                FileOutputStream fos = new FileOutputStream(savePath);
                //这里uis.available一直为0
//            int avi = uis.available();
                long avi = targetFile.getLength();
                int writeCount = 0;
                int bytesRead;
                byte[] buffer = new byte[1024];
                if (onUSBSaveListener != null)
                    onUSBSaveListener.onStart();
                while ((bytesRead = uis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                    writeCount += bytesRead;
//                Log.e(TAG, "Progress : write : " + writeCount + " All : " + avi);
                    //回调下载进度
                    if (onUSBSaveListener != null)
                        onUSBSaveListener.onInProgress((int) (writeCount * 100 / avi));
                }
                fos.flush();
                uis.close();
                fos.close();
                result = true;
                if (onUSBSaveListener != null)
                    onUSBSaveListener.onClose(true);
            } catch (Exception e) {
                e.printStackTrace();
                result = false;
                if (onUSBSaveListener != null)
                    onUSBSaveListener.onClose(false);
            }
            return result;
        }


    }

    /**
     * @Hibernate SQL
     * TODO 注意：SQL好像可以直接"增加列"与"修改列名"，且不会导致数据丢失
     * hibernate.sqLiteDatabase2.execSQL("ALTER TABLE 表名 ADD COLUMN 新增加的列名 int");//测试成功
     * hibernate.sqLiteDatabase2.execSQL("ALTER TABLE 表名 RENAME COLUMN 旧表名 TO 新表名");//测试成功
     */
    public static class Hibernate implements SaveObject.SaveBean {

        public Hibernate() {
            Context context = getActivity();
            if (context != null) {
                this.context = context;
            } else {
                errs("当前并没有绑定 Activity 无法使用无参构造方法，请先使用 GT.GT.build(this); 进行 Activity 绑定。");
            }
        }

        //=============================== 实例化 动态的 Hibernate 对象 ====================================
        private Context context;
        private Hibernate hibernate;

        /**
         * 初始化 Hibernate
         *
         * @param context     上下文
         * @param SQLName     数据库名称
         * @param sqlVersions 数据库版本
         * @param scanTable   数据库映射类
         * @return
         */
        public Hibernate initialize(String sQLName, int sqlVersions, String passWord, Object scanTable) {
            if (hibernate == null) {
                synchronized (Hibernate.class) {
                    if (hibernate == null) {
                        hibernate = this;
                        initSQL(sQLName, sqlVersions, passWord, scanTable); //数据库操作执行
                    }
                }
            }
            return hibernate;
        }

        public Hibernate getHibernate() {
            if (hibernate == null) {
                synchronized (Hibernate.class) {
                    if (hibernate == null) {
                        hibernate = this;
                        initSQL(null, -1, null, null);//数据库操作执行
                    }
                }
            }
            return hibernate;
        }

        public Hibernate(Context context) {
            this.context = context;
        }

        //=============================== 数据库注解 ====================================

        //构建Hibernate
        @Target(ElementType.FIELD)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface Build {
            String setSqlName() default "GT";//数据库默认名为 GT.db

            int setSqlVersion() default 1;//数据库默认版本从 1

            Class<?>[] setSqlTables() default {};//数据库默认加载的数据库,如果设置了映射的表那就不再扫描bean与entity标注的类

            boolean setIsThread() default false;//是否使用线程创建数据库：TODO 注意，如果在线程中创建数据库就不能直接创建就引用数据库对象，需要触发使用

            String setPassWord() default "123456";//默认的数据库加密密码
        }

        //设置映射标识
        @Target(ElementType.TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface GT_Bean {
            String[] setSqlNames() default {};//设置属于那个数据库的表,默认是空
//            boolean setIsEncryption() default false;//默认不进行加密
        }

        @Target(ElementType.TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface GT_Entity {
            String[] setSqlNames() default {};//设置属于那个数据库的表,默认是空
//            boolean setIsEncryption() default false;//默认不进行加密
        }

        //设置主键注解
        @Target({ElementType.FIELD})
        @Retention(RetentionPolicy.RUNTIME)
        public @interface GT_Key {
            boolean setAutoincrement() default true;    //默认为 手动增长

            int setLength() default 200;                //设置主键长度

            String[] setCheckValues() default {};       //设置固定选择的String值如：(男、女)

            double setMax() default Double.MAX_VALUE;   //设置最大值

            double setMin() default Double.MIN_VALUE;   //设置最小值
        }

        //表字段特殊设置注解
        @Target({ElementType.FIELD})
        @Retention(RetentionPolicy.RUNTIME)
        public @interface GT_Property {

            String setOldTableValue() default "";       //用于修改字段名，填写旧的字段名

            boolean setNotNull() default false;         //设置字段不为null,默认可为null

            String[] setCheckValues() default {};       //设置固定选择的String值如：(男、女)

            int setLength() default 200;                //限制字段大小

            double setMax() default Double.MAX_VALUE;   //设置最大值

            double setMin() default Double.MIN_VALUE;   //设置最小值

            boolean setNotInit() default false;         //设置是否需要持久化,默认是持久化

            String[] setDefaultValue() default {};      //设置默认值，请规范使用

            boolean setIsEncryption() default false;   //是否加密,默认不加密

        }

        //=============================== 数据库属性 ====================================
        private String DATABASE_NAME = "GT.db";   //默认数据库名称(可修改)
        private int DATABASE_VERSION = 1;         //默认数据库版本(可升级)
        private Object SCAN_TABLE = null;         //默认数据库映射方式(可修改)
        private boolean isDeleteNullTable;        //是否删除无用表

        public String getDATABASE_NAME() {
            return DATABASE_NAME;
        }

        public void setDATABASE_NAME(String DATABASE_NAME) {
            this.DATABASE_NAME = DATABASE_NAME;
        }

        public int getDATABASE_VERSION() {
            return DATABASE_VERSION;
        }

        public void setDATABASE_VERSION(int DATABASE_VERSION) {
            this.DATABASE_VERSION = DATABASE_VERSION;
        }

        //=============================== 数据库对象 ====================================
        public SQLiteDatabase sqLiteDatabase2;
        //=============================== 数据库语句 ====================================
        public static String SQL_CODE = "";

        //=============================== 无实体类完成数据库 ====================================
        public SQLiteDatabase getSqliteDatabase() {
            return sqLiteDatabase2;
        }

        /**
         * 关闭SQL对象
         *
         * @return
         */
        public Hibernate close() {
            if (sqLiteDatabase2 != null) {
                sqLiteDatabase2.close();//ok
            }
            return this;
        }

        /**
         * @param sqLiteDatabase
         * @设置Hibernate数据库的SQL管理对象
         */
        public void setSqLiteDatabase(SQLiteDatabase sqLiteDatabase) {
            this.sqLiteDatabase2 = sqLiteDatabase;
        }

        //=============================== 数据表路径 ====================================

        private List<Class<?>> tableList;//创建表class集合

        public List<Class<?>> getTableList() {
            return tableList;
        }

        public void setTableList(List<Class<?>> tableList) {
            this.tableList = tableList;
        }

        //表字段与字段的类型
        private List<String> tableStr;

        public List<String> getTableStr() {
            return tableStr;
        }

        public void setTableStr(List<String> tableStr) {
            this.tableStr = tableStr;
        }

        //=============================== 数据库管理 ====================================

        /**
         * @管理数据库核心类
         */
        private class DatabaseHelper extends SQLiteOpenHelper {

            private DatabaseHelper databaseHelper = null;
            private Context context = null;

            public DatabaseHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
                this.context = context;
            }

            public synchronized DatabaseHelper getDatabaseHelper(Context context) {
                if (databaseHelper == null) {
                    databaseHelper = new DatabaseHelper(context);
                }
                return databaseHelper;
            }

            //构造方法
            public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
                super(context, name, factory, version);
                this.context = context;
            }

            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                scanTable(SCAN_TABLE); //设置创建或更新升级的数据库表
                sqLiteDatabase2 = sqLiteDatabase;
                //如果设置了 独自的 SQL 语句代码就用单独设置的
                if (null != SQL_CODE && !"".equals(SQL_CODE)) {
//                    GT.log("手动代码:", SQL_CODE);
                    sqLiteDatabase.execSQL(SQL_CODE); //创建数据库 sql 语句 并 执行
                    SQL_CODE = "";//初始化
                } else {//否则用智能SQL代码
                    for (String sqlStr : creationTableNameList) {
//                        GT.log("智能代码:", sqlStr);
                        sqLiteDatabase.execSQL(sqlStr); //创建数据库 sql 语句 并 执行
                    }
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
                //如果当前版本需要升级
                if (oldVersion < newVersion) {
                    scanTable(SCAN_TABLE); //设置创建或更新升级的数据库表
                    //更新开始
                    sqLiteDatabase2 = sqLiteDatabase;
                    if (SQL_CODE != null && !"".equals(SQL_CODE)) {//用户指定的代码
                        sqLiteDatabase.execSQL(SQL_CODE);
                        SQL_CODE = "";//初始化
                    } else {//用智能代码

                        for (int i = 0; i < creationTableNameList.size(); i++) { //遍历需要升级的表

                            //第一步：获取想要更新升级的所有表名，将表名全部改成“待删除表”
                            if (isTable(tableNameList.get(i))) {//如果当前数据库存在该表
                                updateTableName(tableNameList.get(i), "temp_" + tableNameList.get(i));//修改表名
                            }

                            //第二步：创建最新的表
                            if (!isTable(tableNameList.get(i))) {//当前表如果不存在
                                if (sqLiteDatabase2 == null) return;
                                sqLiteDatabase2.execSQL(creationTableNameList.get(i));//ok
                            }

                            //第三步：将旧表数据导入新表中
                            if (isTable("temp_" + tableNameList.get(i)) && isTable(tableNameList.get(i))) {//如果当前数据库存在该表

                                List<String> oldList = new ArrayList<>();//需要导入的旧字段
                                List<String> newList = new ArrayList<>();//需要导入的新字段

                                List<String> tempSQLTableValue = getTableAllValue("temp_" + tableNameList.get(i));//获取上个版本表所有字段
                                List<String> SQLTableValue = getTableAllValue(tableNameList.get(i));//获取当前最新版本数据库表所有字段
                                for (String tableValue : tempSQLTableValue) {//遍历旧版本，如果新版本也有就存入 待导入数据字段行列
                                    if (SQLTableValue.contains(tableValue)) {
                                        oldList.add(tableValue);//存入容器中
                                        newList.add(tableValue);//存入容器中
                                    }
                                }

                                //当前表是否需要修改字段
                                //Book{bookName bookNumber ,name number }
                                if (updateTableValueList != null && updateTableValueList.size() > 0) {
                                    for (String updateData : updateTableValueList) {
                                        String updateTableName = updateData.substring(0, updateData.indexOf("{"));//获取需要修改的表名
                                        if (!tableNameList.get(i).equals(updateTableName))
                                            continue;//过滤掉不需要修改的表

                                        //开始解析 修改字段
                                        String updateValue = updateData;
                                        updateValue = updateValue.substring(updateValue.indexOf("{") + 1, updateValue.indexOf("}"));
                                        String[] updateArrayValue = updateValue.split(",");
                                        String[] oldArray = updateArrayValue[0].split(" ");
                                        String[] newArray = updateArrayValue[1].split(" ");

                                        for (String old : oldArray) {
                                            oldList.add(old);//存入容器中
                                        }

                                        for (String news : newArray) {
                                            newList.add(news);//存入容器中
                                        }


                                    }
                                }
                                //将旧表中 需要导入的数据 导入到新表中
                                inputTableData("temp_" + tableNameList.get(i), oldList, tableNameList.get(i), newList);
                            }


                            //第四步：删除旧表
                            if (isTable("temp_" + tableNameList.get(i))) {//如果当前数据库存在该表
                                deleteTable("temp_" + tableNameList.get(i));
                            }

                        }

                        //第五步：删除数据库中多余无用的表
                        for (String tableName : getSqlAllTableName()) {//获取数据库中所有的表
                            if (!"sqlite_sequence".equals(tableName)) {//过来掉系统自带的表
                                if (!tableNameList.contains(tableName)) {//如果数据库表没有存在最新数据库更新的表名当中
                                    deleteTable(tableName);//删除当前无效的表
                                }
                            }
                        }

                        //更新结束
                    }
                }

            }
        }

        //=============================== 数据库操作 ====================================

        /**
         * @param oldTableName 旧表名称
         * @param NewTableName 新表名称
         * @return
         * @修改表名称
         */
        public synchronized Hibernate updateTableName(String oldTableName, String NewTableName) {
            String sql = "ALTER TABLE " + oldTableName + " RENAME TO " + NewTableName;
            if (sqLiteDatabase2 == null) return this;
            sqLiteDatabase2.execSQL(sql);//ok
            return this;
        }

        /**
         * @param oldTableName 旧表名称
         * @param NewTableName 新表名称
         * @return
         * @修改表名称
         */
        public synchronized Hibernate updateTableName(Class<?> oldTableNameClass, Class<?> NewTableNameClass) {
            String sql = "ALTER TABLE " + oldTableNameClass.getSimpleName() + " RENAME TO " + NewTableNameClass.getSimpleName();
            if (sqLiteDatabase2 == null) return this;
            sqLiteDatabase2.execSQL(sql);//ok
            return this;
        }

        /**
         * @param tableName 表名
         * @return
         * @获取表所有字段名
         */
        public synchronized List<String> getTableAllValue(String tableName) {
            Cursor tempCursor = null;
            List<String> tableNameList = new ArrayList<>();
            try {
                String sql = "SELECT * FROM " + tableName + " WHERE 0";
                if (sqLiteDatabase2 == null) return tableNameList;
                tempCursor = sqLiteDatabase2.rawQuery(sql, null);//ok
                String[] columnNames = tempCursor.getColumnNames();
                for (String str : columnNames) {
                    tableNameList.add(str);
                }
                return tableNameList;
            } catch (Exception e) {
                err(getLineInfo(1), "出现异常操作失误:" + e);
            } finally {
                if (tempCursor != null) {
                    tempCursor.close();
                }
            }
            return tableNameList;
        }

        /**
         * @param tableClass 表名
         * @return
         * @获取表所有字段名
         */
        public synchronized List<String> getTableAllValue(Class<?> tableClass) {
            if (tableClass == null || !isTable(tableClass.getSimpleName())) return null;
            Cursor tempCursor = null;
            List<String> tableNameList = new ArrayList<>();
            try {
                String sql = "SELECT * FROM " + tableClass.getSimpleName() + " WHERE 0";
                if (sqLiteDatabase2 == null) return tableNameList;
                tempCursor = sqLiteDatabase2.rawQuery(sql, null);//ok
                String[] columnNames = tempCursor.getColumnNames();
                for (String str : columnNames) {
                    tableNameList.add(str);
                }
                return tableNameList;
            } catch (Exception e) {
                err(getLineInfo(1), "出现异常操作失误:" + e);
            } finally {
                if (tempCursor != null) {
                    tempCursor.close();
                }
            }

            return tableNameList;
        }

        /**
         * 创建表
         *
         * @param tableClass
         * @return
         */
        public synchronized boolean createTable(Class<?> tableClass) {
            if (!isTable(tableClass)) {
                scanTable(tableClass);//将类转为SQL代码
                for (String sqlStr : creationTableNameList) {
                    if (sqLiteDatabase2 == null) return false;
                    sqLiteDatabase2.execSQL(sqlStr);//ok
                }
                return true;
            }
            return false;
        }

        public synchronized boolean createTable(String tableName) {

            return false;
        }


        /**
         * @param tableName
         * @return
         * @删除表
         */
        public synchronized Hibernate deleteTable(String tableName) {
            try {
                String sql = "DROP TABLE " + tableName;
                if (sqLiteDatabase2 == null) return this;
                sqLiteDatabase2.execSQL(sql);//ok
            } catch (Exception e) {
                err(getLineInfo(1), "出现异常操作失误:" + e);
            }
            return this;
        }

        /**
         * @param tableClass
         * @return
         * @删除表
         */
        public synchronized Hibernate deleteTable(Class<?> tableClass) {
            try {
                String sql = "DROP TABLE " + tableClass.getSimpleName();
                if (sqLiteDatabase2 == null) return this;
                sqLiteDatabase2.execSQL(sql);//ok
            } catch (Exception e) {
                err(getLineInfo(1), "出现异常操作失误:" + e);
            }
            return this;
        }

        /**
         * @return
         * @获取当前数据库所有表名称
         */
        public synchronized List<String> getSqlAllTableName() {
            List<String> strList = new ArrayList<>();
            if (sqLiteDatabase2 == null) return strList;
            Cursor cursor = sqLiteDatabase2.rawQuery("select name from sqlite_master where type='table' order by name", null);//ok
            while (cursor.moveToNext()) {
                String tableName = cursor.getString(0);
                if ("android_metadata".equals(tableName)) {//是否遍历到系统数据库
                    deleteTable("android_metadata");//删除系统创建的数据库
                } else if (!"sqlite_sequence".equals(tableName)) {
                    strList.add(tableName);
                }
            }
            return strList;
        }

        /**
         * 获取数据库所有名称
         *
         * @return
         */
        public synchronized List<String> getSqlAllName() {
            if (context == null) return null;
            List<String> filesAllNames = FileUtils.getFilesAllName(context.getFilesDir().getParent() + "/databases/");
            if (filesAllNames == null) {
                return null;
            } else {

                //将路径去掉
                for (int i = 0; i < filesAllNames.size(); i++) {
                    String filesAllName = filesAllNames.get(i);
                    String[] split = filesAllName.split("/");
                    filesAllName = split[split.length - 1];
                    if (filesAllName.contains(".db")) {
                        filesAllNames.set(i, filesAllName);
                    }
                }

                List<String> sqlNames = new ArrayList<>();
                //去掉非数据库的文件
                for (String filesName : filesAllNames) {
                    String extension = filesName.substring(filesName.lastIndexOf(".") + 1);
                    if ("db".equals(extension)) {
                        sqlNames.add(filesName);
                    }
                }
                return sqlNames;
            }
        }

        /**
         * @param tableName 表名
         * @return 存在返回 true 不存在返回 false
         * @监测当前数据库是否存在此表
         */
        public synchronized boolean isTable(String tableName) {
            List<String> sqlAllTableName = getSqlAllTableName();
            if (sqlAllTableName.contains(tableName)) {
                return true;
            }
            return false;
        }

        /**
         * @param tableClass 表名
         * @return 存在返回 true 不存在返回 false
         * @监测当前数据库是否存在此表
         */
        public synchronized boolean isTable(Class<?> tableClass) {
            List<String> sqlAllTableName = getSqlAllTableName();
            if (sqlAllTableName.contains(tableClass.getSimpleName())) {
                return true;
            }
            return false;
        }

        /**
         * @param oldTable 旧表
         * @param newTable 新表
         * @return
         * @导入表的数据 (自动匹配相同表字段自动导入数据)
         */
        public synchronized Hibernate inputTableData(String oldTable, String newTable) {

            List<String> tempSQLTableValue = getTableAllValue(oldTable);//获取上个版本表所有字段
            List<String> SQLTableValue = getTableAllValue(newTable);//获取当前最新版本数据库表所有字段
            //效验出 旧版本 与 新版本 数据库均有的字段
            List<String> SQLValue = new ArrayList<>();
            for (String tableValue : tempSQLTableValue) {//遍历旧版本，如果新版本也有就存入 待导入数据字段行列
                if (SQLTableValue.contains(tableValue)) {
                    SQLValue.add(tableValue);//存入容器中
                }
            }

            //生成可用的表字段
            String tableChars = "";//表字段
            for (String str : SQLValue) {
                tableChars += (str + ",");
            }
            tableChars = tableChars.substring(0, tableChars.length() - 1);//去掉SQLCode 最后一个无用逗号

            String inputSQL = "INSERT INTO " + newTable + "(" + tableChars + ") SELECT " + tableChars + " FROM " + oldTable;
            if (sqLiteDatabase2 == null) return this;
            sqLiteDatabase2.execSQL(inputSQL);//ok

            return this;
        }

        /**
         * @param oldTableClass 旧表
         * @param newTableClass 新表
         * @return
         * @导入表的数据 (自动匹配相同表字段自动导入数据)
         */
        public synchronized Hibernate inputTableData(Class<?> oldTableClass, Class<?> newTableClass) {

            String oldTable = oldTableClass.getSimpleName();
            String newTable = newTableClass.getSimpleName();

            List<String> tempSQLTableValue = getTableAllValue(oldTable);//获取上个版本表所有字段
            List<String> SQLTableValue = getTableAllValue(newTable);//获取当前最新版本数据库表所有字段
            //效验出 旧版本 与 新版本 数据库均有的字段
            List<String> SQLValue = new ArrayList<>();
            for (String tableValue : tempSQLTableValue) {//遍历旧版本，如果新版本也有就存入 待导入数据字段行列
                if (SQLTableValue.contains(tableValue)) {
                    SQLValue.add(tableValue);//存入容器中
                }
            }

            //生成可用的表字段
            String tableChars = "";//表字段
            for (String str : SQLValue) {
                tableChars += (str + ",");
            }
            tableChars = tableChars.substring(0, tableChars.length() - 1);//去掉SQLCode 最后一个无用逗号

            String inputSQL = "INSERT INTO " + newTable + "(" + tableChars + ") SELECT " + tableChars + " FROM " + oldTable;
            if (sqLiteDatabase2 == null) return this;
            sqLiteDatabase2.execSQL(inputSQL);//ok

            return this;
        }

        /**
         * @param oldTable     旧表
         * @param oldTableList 旧表集合
         * @param newTable     新表
         * @param newTableList 新表集合
         * @return
         * @导入表的数据 (指定匹配相同表字段自动导入数据)
         */
        public synchronized Hibernate inputTableData(String oldTable, List<String> oldTableList, String newTable, List<String> newTableList) {

            //如果当前数据库存在该表
            if (isTable(oldTable) && isTable(newTable)) {

                //生成旧的可用表字段
                String oldTableChar = "";
                for (String str : oldTableList) {
                    oldTableChar += (str + ",");
                }
                oldTableChar = oldTableChar.substring(0, oldTableChar.length() - 1);//去掉SQLCode 最后一个无用逗号

                //生成新的可用表字段
                String newTableChar = "";
                for (String str : newTableList) {
                    newTableChar += (str + ",");
                }
                newTableChar = newTableChar.substring(0, newTableChar.length() - 1);//去掉SQLCode 最后一个无用逗号


                String inputSQL = "INSERT INTO " + newTable + "(" + newTableChar + ") SELECT " + oldTableChar + " FROM " + oldTable;
                if (sqLiteDatabase2 == null) return this;
                sqLiteDatabase2.execSQL(inputSQL);//ok
            }

            return this;
        }

        /**
         * @param oldTable     旧表
         * @param oldTableList 旧表集合
         * @param newTable     新表
         * @param newTableList 新表集合
         * @return
         * @导入表的数据 (指定匹配相同表字段自动导入数据)
         */
        public synchronized Hibernate inputTableData(Class<?> oldTableClass, List<String> oldTableList, Class<?> newTableClass, List<String> newTableList) {

            String oldTable = oldTableClass.getSimpleName();
            String newTable = newTableClass.getSimpleName();


            if (isTable(oldTable) && isTable(newTable)) {//如果当前数据库存在该表

                //生成旧的可用表字段
                String oldTableChar = "";
                for (String str : oldTableList) {
                    oldTableChar += (str + ",");
                }
                oldTableChar = oldTableChar.substring(0, oldTableChar.length() - 1);//去掉SQLCode 最后一个无用逗号

                //生成新的可用表字段
                String newTableChar = "";
                for (String str : newTableList) {
                    newTableChar += (str + ",");
                }
                newTableChar = newTableChar.substring(0, newTableChar.length() - 1);//去掉SQLCode 最后一个无用逗号


                String inputSQL = "INSERT INTO " + newTable + "(" + newTableChar + ") SELECT " + oldTableChar + " FROM " + oldTable;
                if (sqLiteDatabase2 == null) return this;
                sqLiteDatabase2.execSQL(inputSQL);//ok
            }

            return this;
        }

        /**
         * 返回List或Map
         *
         * @param type
         * @param value
         * @param <T>
         * @return
         */
        private Object returnListOrMap(Class<?> type, String value) {

            if (type == List.class) {
                if (value == null || "null".equals(value)) return null;
                //如果是 List java.lang.String[1111, 2222, 3333]
                String valueType = value.substring(0, value.indexOf("["));
                String valueData = value.substring(value.indexOf("[") + 1, value.length() - 1);
                String[] arrayValue = valueData.split(",");

                if (valueType == null || valueType.length() == 0) return null;

                switch (valueType) {
                    case "java.lang.String":
                        List<String> strList = new ArrayList<String>();
                        for (String str : arrayValue) {
                            strList.add(str);
                        }
                        return strList;
                    case "java.lang.Integer":
                        List<Integer> intList = new ArrayList<Integer>();
                        for (String str : arrayValue) {
                            intList.add(Integer.parseInt(str));
                        }
                        return intList;
                    case "java.lang.Boolean":
                        List<Boolean> booleanList = new ArrayList<Boolean>();
                        for (String str : arrayValue) {
                            booleanList.add(Boolean.parseBoolean(str));
                        }
                        return booleanList;
                    case "java.lang.Double":
                        List<Double> doubleList = new ArrayList<Double>();
                        for (String str : arrayValue) {
                            doubleList.add(Double.parseDouble(str));
                        }
                        return doubleList;
                    case "java.lang.Float":
                        List<Float> floatList = new ArrayList<Float>();
                        for (String str : arrayValue) {
                            floatList.add(Float.parseFloat(str));
                        }
                        return floatList;
                    case "java.sql.Time":
                        List<Time> timeList = new ArrayList<Time>();
                        for (String str : arrayValue) {
                            SimpleDateFormat format2 = new SimpleDateFormat("hh:mm:ss");// 格式化类型
                            Date d2 = null;
                            try {
                                d2 = format2.parse(str);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Time startTimeFmt = new Time(d2.getTime());
                            timeList.add(startTimeFmt);
                        }
                        return timeList;
                    case "java.util.Date":
                    case "java.sql.Date":
                        List<Date> dateList = new ArrayList<Date>();
                        for (String str : arrayValue) {
                            try {
                                Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(str);
                                dateList.add(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        return dateList;
                    case "java.lang.Long":
                        List<Long> longList = new ArrayList<Long>();
                        for (String str : arrayValue) {
                            longList.add(Long.parseLong(str));
                        }
                        return longList;
                    case "[B"://byte数组
                        List<Object> byteArrayList = new ArrayList<Object>();
                        for (String str : arrayValue) {
                            byteArrayList.add(str);
                        }
                        return byteArrayList;
                    case "java.util.ArrayList": {
                        //解析二级
                        valueData = valueData.substring(1, valueData.length() - 1);
                        String[] split = valueData.split("\\], \\[");
                        List<Object> listList = new ArrayList<Object>();
                        for (String str : split) {
                            List<Object> list = new ArrayList<Object>();
                            String[] split1 = str.split(", ");
                            for (String str1 : split1) {
                                if (str1 != null) {
                                    list.add(str1);
                                } else {
                                    list.add(str1);
                                }
                            }
                            listList.add(list);
                        }
                        return listList;
                    }
                    case "java.util.HashMap": {
                        //解析二级 Map {key1=value1, key2=value2}, {2=2, 22=22}
                        List<Object> mapList = new ArrayList<Object>();
                        valueData = valueData.substring(1, valueData.length() - 1);
                        String[] split = valueData.split("\\}, \\{");//key1=value1, key2=value2}, {2=2, 22=22
                        for (String str : split) {
                            Map<Object, Object> map = new HashMap<>();
                            String[] split1 = str.split(", ");//key1=value1, key2=value2
                            for (String str1 : split1) {
                                String[] split2 = str1.split("=");//key1=value1
                                map.put(split2[0], split2[1]);
                            }
                            mapList.add(map);
                        }
                        return mapList;
                    }
                    default: {
                        //非基础类型的数据处理

                        //获取集合中实体类的数据类型
                        Class<?> clazz = null;
                        try {
                            clazz = Class.forName(valueType);
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }

                        //解析集合数据
                        arrayValue = valueData.split("\\},\\{");

                        //生成集合数据并返回
                        List<Object> mapList = new ArrayList<Object>();
                        for (int i = 0; i < arrayValue.length; i++) {
                            String str = arrayValue[i];
                            if (i == 0) {
                                str = str + "}";
                            } else if (i == arrayValue.length - 1) {
                                str = "{" + str;
                            } else {
                                str = "{" + str + "}";
                            }
                            Object o = null;
                            try {
                                o = JSON.fromJson(str, clazz);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            mapList.add(o);
                        }
                        return mapList;
                    }
                }


            } else if (type == Map.class) {
                if (value == null || "null".equals(value)) return null;
                //如果是 Map java.lang.String[key1, key2, key3]
                //          java.lang.String[1号, 2号, 3号]

                String[] split = value.split("\n");
                if (split.length != 2) {
                    return null;
                }

                String keys = split[0];//map-key
                String values = split[1];//map-value

                // Key 解析
                int keysIndex = keys.indexOf("[");//解析普通类型
                if (keysIndex == -1) {
                    keysIndex = keys.indexOf("{");//解析特殊类型
                }
                String keysType = keys.substring(0, keysIndex);
                String keysData = keys.substring(keysIndex + 1, keys.length() - 1);
                String[] arraykeysData = keysData.split(",");//解析待带入的key值

                // value 解析
                int valuesIndex = values.indexOf("[");//解析普通类型
                if (valuesIndex == -1) {
                    valuesIndex = values.indexOf("{");//解析特殊类型
                }
                String valuesType = values.substring(0, valuesIndex);
                String valuesData = values.substring(valuesIndex + 1, values.length() - 1);

                Map<Object, Object> map = new HashMap<>();
                Class<?> keysTypeClass = null;
                Class<?> valuesTypeClass = null;
                try {
                    keysTypeClass = Class.forName(keysType);
                    valuesTypeClass = Class.forName(valuesType);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                //解析key数据
                String[] keysDataArray = null;
                if (returnType(keysTypeClass) == null) {
                    //特殊类型
                    keysDataArray = keysData.split("\\},\\{");
                } else {
                    //普通类型
                    keysDataArray = keysData.split(", ");
                }

                //解析value数据
                String[] valuesDataArray = null;
                if (returnType(valuesTypeClass) == null) {
                    //特殊类型
                    valuesDataArray = valuesData.split("\\},\\{");

                } else {
                    //普通类型
                    valuesDataArray = valuesData.split(", ");
                }


                Object keyObject = null;
                Object valueObject = null;

                //生成Map
                for (int i = 0; i < keysDataArray.length; i++) {

                    String keyStr = keysDataArray[i];
                    String valueStr = valuesDataArray[i];

                    //如果key是特殊类型就做特殊处理
                    if (returnType(keysTypeClass) == null) {
                        if (i == 0) {
                            keyStr += "}";
                        } else if (i == keysDataArray.length - 1) {
                            keyStr = "{" + keyStr;
                        } else {
                            keyStr = "{" + keyStr + "}";
                        }
                        try {
                            keyObject = JSON.fromJson(keyStr, keysTypeClass);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            keyObject = keyStr.toString();
                        }
                    } else {
                        keyObject = keyStr.toString();
                    }

                    //如果value是特殊类型就做特殊处理
                    if (returnType(valuesTypeClass) == null) {
                        if (i == 0) {
                            valueStr += "}";
                        } else if (i == keysDataArray.length - 1) {
                            valueStr = "{" + valueStr;
                        } else {
                            valueStr = "{" + valueStr + "}";
                        }

                        try {
                            valueObject = JSON.fromJson(valueStr, valuesTypeClass);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            valueObject = valueStr.toString();
                        }

                    } else {
                        valueObject = valueStr.toString();
                    }

                    map.put(keyObject, valueObject);


                }

                return map;

            }


            return null;
        }

        /**
         * 返回类型
         *
         * @param type
         * @return
         */
        private Class returnType(Class<?> type) {

            switch (type.getName()) {
                case "java.lang.String":
                    return String.class;
                case "java.lang.Integer":

                    return Integer.class;
                case "java.lang.Boolean":
                    return Boolean.class;
                case "java.lang.Double":
                    return Double.class;
                case "java.lang.Float":
                    return Float.class;
                case "java.sql.Time":
                    return Time.class;
                case "java.util.Date":
                case "java.sql.Date":
                    return Date.class;
                case "java.lang.Long":
                    return Long.class;
                case "[B"://byte数组
                    return Byte.class;
                case "java.util.ArrayList": {
                    return ArrayList.class;
                }
                case "java.util.HashMap": {
                    return HashMap.class;
                }
                default:
                    //特殊类型
                    return null;
            }
        }

        /**
         * 返回类型
         *
         * @param type
         * @return
         */
        private Object returnTypeDataValue(Object objValue, Class<?> type) {
            if (objValue == null) return null;

            String objValueStr = objValue.toString();

            try {
                switch (type.getName()) {
                    case "java.lang.String":
                        return String.valueOf(objValue);
                    case "java.lang.Integer":
                        return Integer.parseInt(objValueStr);
                    case "java.lang.Boolean":
                        return Boolean.parseBoolean(objValueStr);
                    case "java.lang.Double":
                        return Double.parseDouble(objValueStr);
                    case "java.lang.Float":
                        return Float.parseFloat(objValueStr);
                    case "java.sql.Time":
                        SimpleDateFormat format2 = new SimpleDateFormat("hh:mm:ss");// 格式化类型
                        Date d2 = null;
                        try {
                            d2 = format2.parse(objValueStr);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Time startTimeFmt = new Time(d2.getTime());
                        return startTimeFmt;
                    case "java.util.Date":
                    case "java.sql.Date":
                        try {
                            Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(objValueStr);
                            return date;
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return null;
                        }
                    case "java.lang.Long":
                        return Long.parseLong(objValueStr);
                    case "[B"://byte数组
                        return Byte.parseByte(objValueStr);
                    default:
                        //特殊类型
                        return null;
                }
            } catch (Exception e) {
                return null;
            }

        }

        /**
         * ======================================================== 表的 聚合函数 代码 ========================================
         */

        private boolean status = true;//当前增删查改的状态
        private int statusNumber = 0;//数据库操作返回值

        // SQL 操作属性
        private String conditions = null;           //条件
        private String[] values = null;             //条件值
        private String[] returnValues = null;       //返回字段
        private String orderByStr = null;           //排序
        private String limitStr = null;             //限量
        private boolean isLast = false;             //是否查询到最后一条 默认从第一条查询
        private boolean saveAddUpdateKey = false;   //保存与修改主键是否已被创建
        //        private boolean isEncryption;//是否加密
        private String passWord = "";//数据加密密码


        //初始化数据库参数
        private void initSqlParameter() {
            status = true;
            statusNumber = 0;
            saveAddUpdateKey = false;
        }

        //释放数据库参数
        private void closeSqlParameter() {
            values = null;
            conditions = null;
            conditions = null;
            returnValues = null;
            orderByStr = null;
            limitStr = null;
        }

        /**
         * 设置返回参数
         *
         * @param returnValues
         * @return
         */
        public Hibernate select(Object returnValues) {

            String returnValueType = returnValues.getClass().getSimpleName();

            if (!returnValueType.equals("String") && !returnValueType.equals("String[]")) {
                err(getLineInfo(LOG.lineInfoIndex), "返回的数据 returnValue 类型仅支持String 与 String[]，操作失败");
                status = false;
                return null;
            }

            //解析查询条件值
            String[] returnValues1 = new String[1];//解析筛选值
            if (returnValueType.equals("String[]")) {
                returnValues1 = (String[]) returnValues;
            } else if (returnValueType.equals("String")) {
                returnValues1[0] = (String) returnValues;
            }

            this.returnValues = returnValues1;
            return this;
        }

        /**
         * 条件参数
         *
         * @param conditions 条件
         * @param values     值
         * @return
         */
        public Hibernate where(Object conditions, Object values) {

            if (conditions == null || values == null) {
                err(getLineInfo(LOG.lineInfoIndex), "条件参数 为 null，操作失败");
                status = false;
                return this;
            }

            //监测条件
            String conditionsType = conditions.getClass().getSimpleName();
            String valuesType = values.getClass().getSimpleName();

            if (!conditionsType.equals("String") && !conditionsType.equals("String[]")) {
                err(getLineInfo(LOG.lineInfoIndex), "条件参数 conditionsType 类型仅支持String 与 String[]，操作失败");
                status = false;
                return this;
            }

            //解析查询条件 conditions （String）
            String condition = "";//解析筛选条件
            if (conditionsType.equals("String[]")) {
                for (String str : (String[]) conditions) {
                    condition += (str + " = ? and ");
                }

                //去掉最后一个多余 and
                condition = condition.substring(0, condition.lastIndexOf("and"));
            } else if (conditionsType.equals("String")) {
                condition = (String) conditions;
            }

            //解析查询条件值
            String[] valuesArray = new String[1];//解析筛选值
            if (valuesType.equals("String[]")) {
                valuesArray = (String[]) values;
            } else {
                valuesArray[0] = String.valueOf(values);
            }

            this.conditions = condition;
            this.values = valuesArray;
            return this;
        }

        /**
         * 根据 orderByStr 进行倒序
         * <p>
         * 实例:limit(4).flashback("loginID"); 返回最后4条数据
         *
         * @param orderByStr 根据表的该字段返回最后的数据(倒序)
         * @return
         */
        public Hibernate flashback(String orderByStr) {
            this.orderByStr = orderByStr + " desc";
            return this;
        }

        /**
         * 当前页数显示多少条数据
         * 实例：
         * new int[]{20} 一个数表示，当前返回多少条数据
         * new int[]{1,100} 两个数表示，第一页显示100条数据
         *
         * @param limitStr
         * @return
         */
        public Hibernate limit(int[] limitStr) {
            if (limitStr == null || limitStr.length < 1 || limitStr.length > 2) return this;
            if (limitStr.length == 1) {
                this.limitStr = limitStr[0] + "";
            } else if (limitStr.length == 2) {
                this.limitStr = limitStr[0] + "," + limitStr[1];
            }
            return this;
        }

        public boolean isLast() {
            return isLast;
        }

        /**
         * 是否查询最后一个内容
         *
         * @param last 默认为 false
         */
        public Hibernate isLast(boolean last) {
            isLast = last;
            return this;
        }

        /**
         * @return 当前数据库操作后的状态
         */
        public boolean isStatus() {
            return status;
        }

        /**
         * @return 数据库操作返回值
         */
        public int getStatus() {
            return statusNumber;
        }

        /**
         * 保存和修改提取的添加父类的方法
         *
         * @param bean
         * @param class1
         * @param contentValues
         * @param <T>
         */
        private <T> void saveSuper(T bean, Class class1, ContentValues contentValues) {

            Class superclass = class1.getSuperclass();

            //递归去执行父类
            if (Object.class != class1) {
                saveSuper(bean, superclass, contentValues);
            } else {
                return;
            }

            String simpleName = class1.getSimpleName();//获取表名

            //获取所有成员变量
            Field[] fields = class1.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // 设置属性是可以访问的
                try {
                    Class<?> type = field.getType();//获取字段类型
                    String name = field.getName();//获取属性名
                    Object val = field.get(bean);// 得到此属性的值

                    //判断当前字段子表是否存在，如果存在，那就优先录用子表的，跳过本次解析
                    if (contentValues.containsKey(name)) {
                        continue;
                    }

                    GT_Property gt_Property = field.getAnnotation(GT_Property.class);//如果是不被初始化的那就进行跳过解析
                    if (gt_Property != null && gt_Property.setNotInit()) {
                        continue;
                    }

                    GT_Key annotation = field.getAnnotation(GT_Key.class);
                    if (annotation != null) {
                        //如果主键未被解析过那就进行再父类解析,如果这个字段是Key并且是 "自增",或是不映射字段 的就跳过
                        if (saveAddUpdateKey || annotation.setAutoincrement())
                            continue;
                    }


                    if (String.class == type) {
                        if (val == null) {
                            contentValues.put(name, (String) null);
                            continue;
                        }
                        contentValues.put(name, (String) val);
                    } else if (int.class == type || Integer.class == type) {
                        if (val == null) {
                            contentValues.put(name, 0);
                            continue;
                        }
                        contentValues.put(name, (int) val);
                    } else if (boolean.class == type || Boolean.class == type) {
                        if (val == null) {
                            contentValues.put(name, false);
                            continue;
                        }
                        contentValues.put(name, (boolean) val);
                    } else if (double.class == type || Double.class == type) {
                        if (val == null) {
                            contentValues.put(name, 0);
                            continue;
                        }
                        contentValues.put(name, (double) val);
                    } else if (float.class == type || Float.class == type) {
                        if (val == null) {
                            contentValues.put(name, 0);
                            continue;
                        }
                        contentValues.put(name, (float) val);
                    } else if (Time.class == type) {
                        if (val == null) {
                            contentValues.put(name, (String) null);
                            continue;
                        }
                        contentValues.put(name, String.valueOf((Time) val));
                    } else if (Date.class == type || java.sql.Date.class == type) {
                        if (val == null) {
                            contentValues.put(name, (String) null);
                            continue;
                        }
                        contentValues.put(name, String.valueOf((Date) val));
                    } else if (long.class == type || Long.class == type) {
                        if (val == null) {
                            contentValues.put(name, 0);
                            continue;
                        }
                        contentValues.put(name, (long) val);
                    } else if (short.class == type || Short.class == type) {
                        if (val == null) {
                            contentValues.put(name, 0);
                            continue;
                        }
                        contentValues.put(name, (short) val);
                    } else if (byte[].class.equals(type)) {
                        contentValues.put(name, (byte[]) val);
                    }


                    //数组类型
                    else if (String[].class == type) {
                        //String[]
                        String valueData = JSON.toJson(val);
                        contentValues.put(name, valueData);
                    } else if (int[].class == type) {
                        //int[]
                        String valueData = JSON.toJson(val);
                        contentValues.put(name, valueData);
                    } else if (boolean[].class == type) {
                        //boolean[]
                        String valueData = JSON.toJson(val);
                        contentValues.put(name, valueData);
                    } else if (double[].class == type) {
                        //double[]
                        String valueData = JSON.toJson(val);
                        contentValues.put(name, valueData);
                    } else if (float[].class == type) {
                        //float[]
                        String valueData = JSON.toJson(val);
                        contentValues.put(name, valueData);
                    } else if (long[].class == type) {
                        //long[]
                        String valueData = JSON.toJson(val);
                        contentValues.put(name, valueData);
                    } else if (short[].class == type) {
                        //short[]
                        String valueData = JSON.toJson(val);
                        contentValues.put(name, valueData);
                    } else if (List.class == type || Map.class == type) { //存储 List 与 Map 类型的
                        if (val == null) {
                            contentValues.put(name, (String) null);
                            continue;
                        }

                        String data = "";
                        //如果是 List 类型的存储
                        if (val.getClass() == ArrayList.class) {
                            ArrayList<?> list = (ArrayList<?>) val;
                            if (list.size() != 0) {
                                Object o = list.get(0);
                                if (o != null) {
                                    Class aClass = returnType(o.getClass());//返回当前类型
                                    if (aClass == null) {//如果是特殊类型 ，那就进过json 处理 再存储
                                        String jsonStr = JSON.toJson(val);//将 bean 转为 json
                                        data = o.getClass().getName() + jsonStr;
                                    } else {
                                        data = aClass.getName() + val.toString();
                                    }
                                } else {
                                    data = val.toString();
                                }
                            } else {
                                data = val.toString();
                            }
                        } else if (val.getClass() == HashMap.class) {
                            Set<?> keys = ((HashMap<?, ?>) val).keySet();
                            Collection<?> values = ((HashMap<?, ?>) val).values();
                            if (values.size() != 0) {
                                Object keyObj = keys.iterator().next();
                                Object valObj = values.iterator().next();
                                if (keyObj != null && valObj != null) {

                                    String keysData = "";
                                    String valuesData = "";

                                    if (returnType(keyObj.getClass()) == null) {//如果是特殊类型 ，那就进过json 处理 再存储
                                        keysData = JSON.toJson(keys);//将 bean 转为 json;
                                    } else {
                                        keysData = keys.toString();
                                    }

                                    if (returnType(valObj.getClass()) == null) {//如果是特殊类型 ，那就进过json 处理 再存储
                                        valuesData = JSON.toJson(values);//将 bean 转为 json;
                                    } else {
                                        valuesData = values.toString();
                                    }

                                    data = keyObj.getClass().getName() + keysData + "\n" + valObj.getClass().getName() + valuesData;
                                } else {
                                    data = val.toString();
                                }
                            } else {
                                data = val.toString();
                            }
                        }

                        contentValues.put(name, data);
                    } else {
                        String jsonStr = JSON.toJson(val);//将 bean 转为 json
                        contentValues.put(name, jsonStr);
                    }

                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                    errs(getLineInfo(LOG.lineInfoIndex) + "数据出现问题");
                    status = false;
                    return;
                }
            }

            return;
        }

        /**
         * 保存和修改的操作核心方法
         *
         * @param bean
         * @param contentValues
         * @param <T>
         */
        private <T> void saveAddUpdateOperation(T bean, ContentValues contentValues) {
            Class<?> class1 = bean.getClass();//当前类

           /* GT_Bean gt_bean = class1.getAnnotation(GT_Bean.class);//获取该类 GT_Bean 的注解类
            if (gt_bean != null) {
                isEncryption = gt_bean.setIsEncryption();
            }*/

            //获取所有成员变量
            Field[] fields = class1.getDeclaredFields();
            //解析并设置保存的数据
            for (Field field : fields) {
                field.setAccessible(true); // 设置属性是可以访问的
                try {
                    Class<?> type = field.getType();//获取字段类型
                    String name = field.getName();//获取属性名
                    Object val = field.get(bean);// 得到此属性的值
                    if (val == null) continue;

                    GT_Property gt_Property = field.getAnnotation(GT_Property.class);//如果是不被初始化的那就进行跳过解析
                    if (gt_Property != null && gt_Property.setNotInit()) {
                        continue;
                    }

                    GT_Key annotation = field.getAnnotation(GT_Key.class);
                    if (annotation != null) {//如果这个字段是Key并且是 "自增",或是不映射字段 的就跳过
                        saveAddUpdateKey = true;//已被主键标识过
                        if (annotation.setAutoincrement()) {//如果是自增的那就直接跳过
                            continue;
                        }
                    }

                    //解析多个默认值，并随机选择后设置为默认值
                    boolean defaultOneValue = false;//是否是一个默认值
                    if (gt_Property != null) {
                        String[] defaultValues = gt_Property.setDefaultValue();
                        if (defaultValues.length > 1) {
                            int anInt = GT_Random.getInt(0, defaultValues.length);
                            String defaultValue = defaultValues[anInt];
                            if (val == null || !String.valueOf(val).equals("0")) {
                                val = defaultValues[anInt];//将默认值赋给插入的数据
                            }
                        } else if (defaultValues.length == 1) {
                            defaultOneValue = true;
                        }
                    }

                    //插入普通字段
                    if (String.class == type) {
                        if (defaultOneValue && val == null) continue;
                        //加密数据

                        if (gt_Property != null && gt_Property.setIsEncryption()) {
                            val = Encryption.DES.encryptPassword(val, passWord);
                        }

                        /*if (isEncryption) {
                            if(gt_Property == null){
                                val = GT.Encryption.DES.encryptPassword(val, passWord);
                            }else if(gt_Property.setIsEncryption()){
                                val = GT.Encryption.DES.encryptPassword(val, passWord);
                            }
                        }*/
                        contentValues.put(name, (String) val);
                    } else if (short.class == type || Short.class == type) {
                        short value = Short.parseShort(val.toString());
                        if (defaultOneValue && value == 0) continue;
                        contentValues.put(name, value);
                    } else if (int.class == type || Integer.class == type) {
                        int value = Integer.parseInt(val.toString());
                        if (defaultOneValue && value == 0) continue;
                        contentValues.put(name, value);
                    } else if (long.class == type || Long.class == type) {
                        long value = Long.parseLong(val.toString());
                        if (defaultOneValue && value == 0) continue;
                        contentValues.put(name, value);

                    } else if (boolean.class == type || Boolean.class == type) {
                        boolean value = Boolean.parseBoolean(val.toString());
                        if (defaultOneValue && !value) continue;
                        contentValues.put(name, value);

                    } else if (float.class == type || Float.class == type) {
                        float value = Float.parseFloat(val.toString());
                        if (defaultOneValue && value == 0) continue;
                        contentValues.put(name, value);

                    } else if (double.class == type || Double.class == type) {
                        double value = Double.parseDouble(val.toString());
                        if (defaultOneValue && value == 0) continue;
                        contentValues.put(name, value);

                    } else if (Time.class == type) {
                        if (val == null) continue;
                        contentValues.put(name, String.valueOf((Time) val));

                    } else if (Date.class == type || java.sql.Date.class == type) {
                        if (val == null) continue;
                        contentValues.put(name, String.valueOf((Date) val));

                    } else if (byte[].class.equals(type)) {
                        if (val == null) continue;
                        contentValues.put(name, (byte[]) val);
                    }


                    //数组类型
                    else if (String[].class == type ||
                            int[].class == type ||
                            boolean[].class == type ||
                            double[].class == type ||
                            float[].class == type ||
                            long[].class == type ||
                            short[].class == type) {
                        contentValues.put(name, JSON.toJson(val));//存储数组类型
                    }


                    //存储 List 与 Map 类型的
                    else if (List.class == type || Map.class == type) {
                        String data = "";
                        //如果是 List 类型的存储
                        if (val.getClass() == ArrayList.class) {
                            ArrayList<?> list = (ArrayList<?>) val;
                            if (list.size() != 0) {
                                Object o = list.get(0);
                                if (o != null) {
                                    Class aClass = returnType(o.getClass());//返回当前类型
                                    if (aClass == null) {//如果是特殊类型 ，那就进过json 处理 再存储
                                        String jsonStr = JSON.toJson(val);//将 bean 转为 json
                                        data = o.getClass().getName() + jsonStr;
                                    } else {
                                        data = aClass.getName() + val.toString();
                                    }
                                } else {
                                    data = val.toString();
                                }
                            } else {
                                data = val.toString();
                            }
                        } else if (val.getClass() == HashMap.class) {
                            Set<?> keys = ((HashMap<?, ?>) val).keySet();
                            Collection<?> values = ((HashMap<?, ?>) val).values();
                            if (values.size() != 0) {
                                Object keyObj = keys.iterator().next();
                                Object valObj = values.iterator().next();
                                if (keyObj != null && valObj != null) {

                                    String keysData = "";
                                    String valuesData = "";

                                    if (returnType(keyObj.getClass()) == null) {//如果是特殊类型 ，那就进过json 处理 再存储
                                        keysData = JSON.toJson(keys);//将 bean 转为 json;
                                    } else {
                                        keysData = keys.toString();
                                    }

                                    if (returnType(valObj.getClass()) == null) {//如果是特殊类型 ，那就进过json 处理 再存储
                                        valuesData = JSON.toJson(values);//将 bean 转为 json;
                                    } else {
                                        valuesData = values.toString();
                                    }

                                    data = keyObj.getClass().getName() + keysData
                                            + "\n" +
                                            valObj.getClass().getName() + valuesData;
                                } else {
                                    data = val.toString();
                                }
                            } else {
                                data = val.toString();
                            }
                        }

                        contentValues.put(name, data);
                    }


                    //存储实体类
                    else {
                        //TODO 装换实体类需要用规则的创建，构造、set、get、toString、都有才行
                        String jsonStr = JSON.toJson(val);//将 bean 转为 json
                        //如果现有的JSON解析无效，那就使用更高级的JSON进行解析
//                        if (val != null && jsonStr == null || "null".equals(jsonStr) || jsonStr.length() == 0) {
//                            jsonStr = GsonUtils.toJson(val);
//                        }
                        contentValues.put(name, jsonStr);
                    }

                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                    errs(getLineInfo(LOG.lineInfoIndex) + "数据出现问题");
                    status = false;
                    return;
                }
            }

            //优先遍历子表再去遍历父表
            Class<?> superclass = class1.getSuperclass();//获取父类
            //递归去执行父类
            if (Object.class != superclass) {
                saveSuper(bean, superclass, contentValues);//使用保存父类的方法进行保存
            }
        }

        //查询父类
        private <T> void querySuper(T bean, Class<?> class1, String[] tableChar, Cursor cursor) {

            //递归查询父类字段
            Class<?> superclass = class1.getSuperclass();
            if (Object.class != superclass) {
                querySuper(bean, superclass, tableChar, cursor);
            } else {
                //如果已经到基类Object那就不再递归了
                return;
            }

            Field[] fields = superclass.getDeclaredFields();
            for (Field field : fields) {

                //用于解密
                GT_Property gt_Property = field.getAnnotation(GT_Property.class);

                String valueName = field.getName();
                Class<?> type = field.getType();
                field.setAccessible(true);
                for (String str : tableChar) {
                    Object obj = null;
                    if (str.equals(valueName)) {
                        if (String.class == type) {
                            String value = cursor.getString(cursor.getColumnIndex(valueName));
                            //解密
                            if (gt_Property != null && gt_Property.setIsEncryption()) {
                                value = Encryption.DES.decryptPassword(value, passWord);
                            }
                            obj = value;
                        } else if (int.class == type || Integer.class == type) {
                            int value = cursor.getInt(cursor.getColumnIndex(valueName));
                            obj = value;
                        } else if (boolean.class == type || Boolean.class == type) {
                            int value = cursor.getInt(cursor.getColumnIndex(valueName));// false:0   true:1
                            if (value == 1) {
                                obj = true;
                            } else if (value == 0) {
                                obj = false;
                            }
                        } else if (double.class == type || Double.class == type) {
                            double value = cursor.getDouble(cursor.getColumnIndex(valueName));
                            obj = value;
                        } else if (float.class == type || Float.class == type) {
                            float value = cursor.getFloat(cursor.getColumnIndex(valueName));
                            obj = value;
                        } else if (Time.class == type) {
                            String time = cursor.getString(cursor.getColumnIndex(valueName));
                            if (time == null || time.length() == 0) {
                                time = new Time(System.currentTimeMillis()).toString();
                            }
                            SimpleDateFormat format2 = new SimpleDateFormat("hh:mm:ss");// 格式化类型
                            Date d2 = null;
                            try {
                                d2 = format2.parse(time);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Time startTimeFmt = new Time(d2.getTime());
                            obj = startTimeFmt;
                        } else if (Date.class == type || java.sql.Date.class == type) {
                            String value = cursor.getString(cursor.getColumnIndex(valueName));
                            try {
                                Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(value);
                                obj = date;
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        } else if (long.class == type || Long.class == type) {
                            long value = cursor.getLong(cursor.getColumnIndex(valueName));
                            obj = value;
                        } else if (short.class == type || Short.class == type) {
                            short value = cursor.getShort(cursor.getColumnIndex(valueName));
                            obj = value;
                        } else if (byte[].class.equals(type)) {
                            byte[] value = cursor.getBlob(cursor.getColumnIndex(valueName));
                            obj = value;
                        }


                        //数组类型
                        else if (String[].class == type) {
                            //String[]
                            String value = cursor.getString(cursor.getColumnIndex(valueName));
                            if (value == null) {
                                obj = null;
                            } else if ("[]".equals(value)) {
                                obj = new String[]{};
                            } else if (value.length() >= 5) {
                                obj = value.substring(2, value.length() - 2).split("\",\"");
                            }
                        } else if (int[].class == type) {
                            //int[]
                            String value = cursor.getString(cursor.getColumnIndex(valueName));
                            int[] intArr = null;
                            if (value == null) {
                                obj = null;
                            } else if ("[]".equals(value)) {
                                obj = new int[]{};
                            } else if (value.length() >= 3) {
                                value = value.substring(1, value.length() - 1);

                                if (value.indexOf(",") != -1) {
                                    String[] split = value.split(",");
                                    intArr = new int[split.length];
                                    for (int p = 0; p < split.length; p++) {
                                        intArr[p] = Integer.parseInt(split[p]);
                                    }
                                } else {
                                    intArr = new int[]{Integer.parseInt(value)};
                                }
                            }
                            obj = intArr;
                        } else if (boolean[].class == type) {
                            //boolean[]
                            String value = cursor.getString(cursor.getColumnIndex(valueName));
                            boolean[] booleanArr = null;
                            if (value == null) {
                                obj = null;
                            } else if ("[]".equals(value)) {
                                obj = new boolean[]{};
                            } else if (value.length() >= 3) {
                                value = value.substring(1, value.length() - 1);

                                if (value.indexOf(",") != -1) {
                                    String[] split = value.split(",");
                                    booleanArr = new boolean[split.length];
                                    for (int p = 0; p < split.length; p++) {
                                        booleanArr[p] = Boolean.parseBoolean(split[p]);
                                    }
                                } else {
                                    booleanArr = new boolean[]{Boolean.parseBoolean(value)};
                                }
                            }
                            obj = booleanArr;
                        } else if (double[].class == type) {
                            //double[]
                            String value = cursor.getString(cursor.getColumnIndex(valueName));
                            double[] doubleArr = null;
                            if (value == null) {
                                obj = null;
                            } else if ("[]".equals(value)) {
                                obj = new double[]{};
                            } else if (value.length() >= 3) {
                                value = value.substring(1, value.length() - 1);

                                if (value.indexOf(",") != -1) {
                                    String[] split = value.split(",");
                                    doubleArr = new double[split.length];
                                    for (int p = 0; p < split.length; p++) {
                                        doubleArr[p] = Double.parseDouble(split[p]);
                                    }
                                } else {
                                    doubleArr = new double[]{Double.parseDouble(value)};
                                }
                            }
                            obj = doubleArr;
                        } else if (float[].class == type) {
                            //float[]
                            String value = cursor.getString(cursor.getColumnIndex(valueName));
                            float[] floatArr = null;
                            if (value == null) {
                                obj = null;
                            } else if ("[]".equals(value)) {
                                obj = new float[]{};
                            } else if (value.length() >= 3) {
                                value = value.substring(1, value.length() - 1);

                                if (value.indexOf(",") != -1) {
                                    String[] split = value.split(",");
                                    floatArr = new float[split.length];
                                    for (int p = 0; p < split.length; p++) {
                                        floatArr[p] = Float.parseFloat(split[p]);
                                    }
                                } else {
                                    floatArr = new float[]{Float.parseFloat(value)};
                                }
                            }
                            obj = floatArr;
                        } else if (long[].class == type) {
                            //long[]
                            String value = cursor.getString(cursor.getColumnIndex(valueName));
                            long[] longArr = null;
                            if (value == null) {
                                obj = null;
                            } else if ("[]".equals(value)) {
                                obj = new long[]{};
                            } else if (value.length() >= 3) {
                                value = value.substring(1, value.length() - 1);

                                if (value.indexOf(",") != -1) {
                                    String[] split = value.split(",");
                                    longArr = new long[split.length];
                                    for (int p = 0; p < split.length; p++) {
                                        longArr[p] = Long.parseLong(split[p]);
                                    }
                                } else {
                                    longArr = new long[]{Long.parseLong(value)};
                                }
                            }
                            obj = longArr;
                        } else if (short[].class == type) {
                            //short[]
                            String value = cursor.getString(cursor.getColumnIndex(valueName));
                            short[] shortArr = null;
                            if (value == null) {
                                obj = null;
                            } else if ("[]".equals(value)) {
                                obj = new short[]{};
                            } else if (value.length() >= 3) {
                                value = value.substring(1, value.length() - 1);

                                if (value.indexOf(",") != -1) {
                                    String[] split = value.split(",");
                                    shortArr = new short[split.length];
                                    for (int p = 0; p < split.length; p++) {
                                        shortArr[p] = Short.parseShort(split[p]);
                                    }
                                } else {
                                    shortArr = new short[]{Short.parseShort(value)};
                                }
                            }
                            obj = shortArr;
                        } else if (List.class == type || Map.class == type) {

                            String value = cursor.getString(cursor.getColumnIndex(valueName));
                            if (value == null || "null".equals(value)) {
                                obj = null;
                            } else {
                                //第二次转换 List OR Map
                                obj = returnListOrMap(type, value);
                            }
                        } else {
//                                    err(getLineInfo(LOG.lineInfoIndex), "解析的表文件 [" + tableName + "] 类中的字段 [" + field + "] 出现不支持类型。");
                            String value = cursor.getString(cursor.getColumnIndex(valueName));
                            String name = field.getName();//获取属性名
                            Object val = null;
                            try {
                                val = field.get(bean);// 得到此属性的值
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            try {
                                obj = JSON.fromJson(value, type);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    try {
                        if (obj != null) {
                            field.set(bean, obj);
                        }
                        continue;
                    } catch (Exception e) {
//                                    e.printStackTrace();
                        errs("查询数据中出现了无法转换的类型:" + e);
                    }
                }

            }
        }

        //查询核心方法
        private <T> List<T> queryCoreAlgorithm(Class<T> tableNameClass, boolean isQueryAll) {
            String tableName = tableNameClass.getSimpleName();//获取表名称

            //判断这个表是否存在
            if (!isTable(tableName)) {
                err(getLineInfo(LOG.lineInfoIndex), "查询的表不存在，操作失败");
                status = false;
                return null;
            }

            //获取查询该表的返回字段
            if (returnValues == null) {
                List<String> tableAllValue = getTableAllValue(tableName);
                if (tableAllValue != null && tableAllValue.size() > 0) {
                    returnValues = new String[tableAllValue.size()];
                    for (int i = 0; i < tableAllValue.size(); i++) {
                        returnValues[i] = tableAllValue.get(i);
                    }
                }
            }
            List<T> beanList = new ArrayList<T>();//创建容器

            //初始化数据库参数
            initSqlParameter();

            //反射生成对象并注入
            if (sqLiteDatabase2 == null) return null;
            Cursor cursor = sqLiteDatabase2.query(tableName, returnValues, conditions, values, null, null, orderByStr, limitStr);//ok
            if (cursor != null && cursor.getCount() > 0) {
                if (isLast()) {
                    cursor.moveToLast();//移动到最后一位
                } else {
                    cursor.moveToFirst();//移动到首位
                }
                for (int i = 0; i < cursor.getCount(); i++) {
                    T bean = null;//定义泛型
                    try {
                        bean = tableNameClass.newInstance();//实体化
                    } catch (IllegalAccessException e) {
                        err(getLineInfo(LOG.lineInfoIndex), "数据库操作 实体类缺少无参构造，操作失败e：" + e);
                        status = false;
                        return null;
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                        err(getLineInfo(LOG.lineInfoIndex), "数据库操作 实体类缺少无参构造，操作失败e：" + e);
                        status = false;
                        return null;
                    }

                    //递归查询父类
                    querySuper(bean, tableNameClass, returnValues, cursor);

                    Field[] fields = bean.getClass().getDeclaredFields();
                    for (Field field : fields) {

                        //用于解密
                        GT_Property gt_Property = field.getAnnotation(GT_Property.class);//如果是不被初始化的那就进行跳过解析

                        String valueName = field.getName();
                        Class<?> type = field.getType();
                        field.setAccessible(true);
                        if (returnValues != null) {
                            for (String str : returnValues) {
                                Object obj = null;
                                if (str.equals(valueName)) {
                                    if (String.class == type) {
                                        String value = cursor.getString(cursor.getColumnIndex(valueName));
//                                      //解密
                                        if (gt_Property != null && gt_Property.setIsEncryption()) {
                                            value = Encryption.DES.decryptPassword(value, passWord);
                                        }
                                        obj = value;
                                    } else if (int.class == type || Integer.class == type) {
                                        int value = cursor.getInt(cursor.getColumnIndex(valueName));
                                        obj = value;
                                    } else if (boolean.class == type || Boolean.class == type) {
                                        int value = cursor.getInt(cursor.getColumnIndex(valueName));// false:0   true:1
                                        if (value == 1) {
                                            obj = true;
                                        } else if (value == 0) {
                                            obj = false;
                                        }
                                    } else if (double.class == type || Double.class == type) {
                                        double value = cursor.getDouble(cursor.getColumnIndex(valueName));
                                        obj = value;
                                    } else if (float.class == type || Float.class == type) {
                                        float value = cursor.getFloat(cursor.getColumnIndex(valueName));
                                        obj = value;
                                    } else if (Time.class == type) {
                                        String time = cursor.getString(cursor.getColumnIndex(valueName));
                                        if (time == null || time.length() == 0) {
                                            obj = null;
                                            continue;
                                        }
                                        SimpleDateFormat format2 = new SimpleDateFormat("hh:mm:ss");// 格式化类型
                                        Date d2 = null;
                                        try {
                                            d2 = format2.parse(time);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        Time startTimeFmt = new Time(d2.getTime());
                                        obj = startTimeFmt;
                                    } else if (Date.class == type || java.sql.Date.class == type) {
                                        String value = cursor.getString(cursor.getColumnIndex(valueName));
                                        if (value == null || value.length() == 0) {
                                            obj = null;
                                            continue;
                                        }
                                        try {
                                            Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(value);
                                            obj = date;
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                    } else if (long.class == type || Long.class == type) {
                                        long value = cursor.getLong(cursor.getColumnIndex(valueName));
                                        obj = value;
                                    } else if (short.class == type || Short.class == type) {
                                        short value = cursor.getShort(cursor.getColumnIndex(valueName));
                                        obj = value;
                                    } else if (byte[].class.equals(type)) {
                                        byte[] value = cursor.getBlob(cursor.getColumnIndex(valueName));
                                        obj = value;
                                    }


                                    //数组类型
                                    else if (String[].class == type) {
                                        //String[]
                                        String value = cursor.getString(cursor.getColumnIndex(valueName));
                                        if (value == null) {
                                            obj = null;
                                        } else if ("[]".equals(value)) {
                                            obj = new String[]{};
                                        } else if (value.length() >= 5) {
                                            obj = value.substring(2, value.length() - 2).split("\",\"");
                                        }
                                    } else if (int[].class == type) {
                                        //int[]
                                        String value = cursor.getString(cursor.getColumnIndex(valueName));
                                        int[] intArr = null;
                                        if (value == null) {
                                            obj = null;
                                        } else if ("[]".equals(value)) {
                                            obj = new int[]{};
                                        } else if (value.length() >= 3) {
                                            value = value.substring(1, value.length() - 1);

                                            if (value.indexOf(",") != -1) {
                                                String[] split = value.split(",");
                                                intArr = new int[split.length];
                                                for (int p = 0; p < split.length; p++) {
                                                    intArr[p] = Integer.parseInt(split[p]);
                                                }
                                            } else {
                                                intArr = new int[]{Integer.parseInt(value)};
                                            }
                                        }
                                        obj = intArr;
                                    } else if (boolean[].class == type) {
                                        //boolean[]
                                        String value = cursor.getString(cursor.getColumnIndex(valueName));
                                        boolean[] booleanArr = null;
                                        if (value == null) {
                                            obj = null;
                                        } else if ("[]".equals(value)) {
                                            obj = new boolean[]{};
                                        } else if (value.length() >= 3) {
                                            value = value.substring(1, value.length() - 1);

                                            if (value.indexOf(",") != -1) {
                                                String[] split = value.split(",");
                                                booleanArr = new boolean[split.length];
                                                for (int p = 0; p < split.length; p++) {
                                                    booleanArr[p] = Boolean.parseBoolean(split[p]);
                                                }
                                            } else {
                                                booleanArr = new boolean[]{Boolean.parseBoolean(value)};
                                            }
                                        }
                                        obj = booleanArr;
                                    } else if (double[].class == type) {
                                        //double[]
                                        String value = cursor.getString(cursor.getColumnIndex(valueName));
                                        double[] doubleArr = null;
                                        if (value == null) {
                                            obj = null;
                                        } else if ("[]".equals(value)) {
                                            obj = new double[]{};
                                        } else if (value.length() >= 3) {
                                            value = value.substring(1, value.length() - 1);

                                            if (value.indexOf(",") != -1) {
                                                String[] split = value.split(",");
                                                doubleArr = new double[split.length];
                                                for (int p = 0; p < split.length; p++) {
                                                    doubleArr[p] = Double.parseDouble(split[p]);
                                                }
                                            } else {
                                                doubleArr = new double[]{Double.parseDouble(value)};
                                            }
                                        }
                                        obj = doubleArr;
                                    } else if (float[].class == type) {
                                        //float[]
                                        String value = cursor.getString(cursor.getColumnIndex(valueName));
                                        float[] floatArr = null;
                                        if (value == null) {
                                            obj = null;
                                        } else if ("[]".equals(value)) {
                                            obj = new float[]{};
                                        } else if (value.length() >= 3) {
                                            value = value.substring(1, value.length() - 1);

                                            if (value.indexOf(",") != -1) {
                                                String[] split = value.split(",");
                                                floatArr = new float[split.length];
                                                for (int p = 0; p < split.length; p++) {
                                                    floatArr[p] = Float.parseFloat(split[p]);
                                                }
                                            } else {
                                                floatArr = new float[]{Float.parseFloat(value)};
                                            }
                                        }
                                        obj = floatArr;
                                    } else if (long[].class == type) {
                                        //long[]
                                        String value = cursor.getString(cursor.getColumnIndex(valueName));
                                        long[] longArr = null;
                                        if (value == null) {
                                            obj = null;
                                        } else if ("[]".equals(value)) {
                                            obj = new long[]{};
                                        } else if (value.length() >= 3) {
                                            value = value.substring(1, value.length() - 1);

                                            if (value.indexOf(",") != -1) {
                                                String[] split = value.split(",");
                                                longArr = new long[split.length];
                                                for (int p = 0; p < split.length; p++) {
                                                    longArr[p] = Long.parseLong(split[p]);
                                                }
                                            } else {
                                                longArr = new long[]{Long.parseLong(value)};
                                            }
                                        }
                                        obj = longArr;
                                    } else if (short[].class == type) {
                                        //short[]
                                        String value = cursor.getString(cursor.getColumnIndex(valueName));
                                        short[] shortArr = null;
                                        if (value == null) {
                                            obj = null;
                                        } else if ("[]".equals(value)) {
                                            obj = new short[]{};
                                        } else if (value.length() >= 3) {
                                            value = value.substring(1, value.length() - 1);

                                            if (value.indexOf(",") != -1) {
                                                String[] split = value.split(",");
                                                shortArr = new short[split.length];
                                                for (int p = 0; p < split.length; p++) {
                                                    shortArr[p] = Short.parseShort(split[p]);
                                                }
                                            } else {
                                                shortArr = new short[]{Short.parseShort(value)};
                                            }
                                        }
                                        obj = shortArr;
                                    } else if (List.class == type || Map.class == type) {

                                        String value = cursor.getString(cursor.getColumnIndex(valueName));
                                        if (value == null || "null".equals(value)) {
                                            obj = null;
                                        } else {
                                            //第二次转换 List OR Map
                                            obj = returnListOrMap(type, value);
                                        }
                                    } else {
//                                    err(getLineInfo(LOG.lineInfoIndex), "解析的表文件 [" + tableName + "] 类中的字段 [" + field + "] 出现不支持类型。");
                                        String value = cursor.getString(cursor.getColumnIndex(valueName));
                                        String name = field.getName();//获取属性名
                                        Object val = null;
                                        try {
                                            val = field.get(bean);// 得到此属性的值
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            obj = JSON.fromJson(value, type);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }

                                try {
                                    if (obj != null) {
                                        field.set(bean, obj);
                                    }
                                    continue;
                                } catch (Exception e) {
//                                    e.printStackTrace();
                                    errs("查询数据中出现了无法转换的类型:" + e);
                                }
                            }
                        }
                    }
                    beanList.add(bean);//存入查询到的数据

                    //是否查询全部
                    if (isQueryAll) {
                        cursor.moveToNext();//移动到下一位
                    } else {
                        //查询到一个后退出
                        break;
                    }
                }
            }
            cursor.close();//释放资源

            //释放数据库参数
            closeSqlParameter();
            return beanList;
        }

        /**
         * 将该实体类中的真正主键设置为条件与条件值
         *
         * @param bean
         * @param <T>
         */
        private <T> void wheresKeyValue(T bean, Class<?> aClass) {

            Class<?> superclass = aClass.getSuperclass();//父类
            if (superclass != Object.class) {
                wheresKeyValue(bean, superclass);//递归调用
            }

            for (Field field : aClass.getDeclaredFields()) {
                field.setAccessible(true); // 设置属性是可以访问的
                GT_Key annotation = field.getAnnotation(GT_Key.class);
                if (annotation != null) {
                    try {
                        String name = field.getName();//获取属性名
                        Object val = field.get(bean);// 得到此属性的值
//                            log("当前主键是:" + "name:" + name + "  val:" + val);
                        conditions = name + " = ?";
                        values = new String[]{val.toString()};
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        /**
         * 获取表数据总数量
         *
         * @param tableClass
         * @return 返回当前表数据总条数，如果当前表不存在则返回-1
         */
        public long count(Class<?> tableClass) {
            if (tableClass == null) return -1;
            long count = 0;
            String tableName = tableClass.getSimpleName();
            if (isTable(tableName)) {
                String sqlCode = "select count(1) from " + tableName;//默认计数语句
                if (conditions != null && values != null) {
                    sqlCode += " where " + conditions;
                }
                if (sqLiteDatabase2 == null) return -1;
                Cursor cursor = sqLiteDatabase2.rawQuery(sqlCode, values);//ok
                if (cursor != null && cursor.moveToFirst()) {
                    count = cursor.getLong(0);
                }
                cursor.close();
            } else {
                count = -1;
            }

            return count;
        }

        /**
         * 求当列总和
         *
         * @param aClass
         * @param columnName
         * @return
         */
        public Object sum(Class<?> aClass, String columnName) {
            if (aClass == null || columnName == null) return null;
            String tableName = aClass.getSimpleName();//表名
            String sqlCode = "select sum(" + columnName + ") from " + tableName;//默认的代码
            if (conditions != null && values != null) {
                sqlCode += " where " + conditions;
            }
            //查一下数据库怎么查询该字段是属于什么类型的数据
            Object sum = -1;
            if (sqLiteDatabase2 == null) return null;
            Cursor cursor = sqLiteDatabase2.rawQuery(sqlCode, values);//ok
            if (cursor != null && cursor.moveToFirst()) {
                sum = cursor.getInt(0);
            }
            cursor.close();
            return sum;
        }

        /**
         * 求当列最大值
         *
         * @param aClass
         * @param columnName
         * @return
         */
        public Object max(Class<?> aClass, String columnName) {
            if (aClass == null || columnName == null) return null;
            String tableName = aClass.getSimpleName();//表名
            String sqlCode = "select max(" + columnName + ") from " + tableName;//默认的代码
            if (conditions != null && values != null) {
                sqlCode += " where " + conditions;
            }
            //查一下数据库怎么查询该字段是属于什么类型的数据
            Object sum = -1;
            if (sqLiteDatabase2 == null) return null;
            Cursor cursor = sqLiteDatabase2.rawQuery(sqlCode, values);//ok
            if (cursor != null && cursor.moveToFirst()) {
                sum = cursor.getInt(0);
            }
            cursor.close();
            return sum;
        }

        /**
         * 求当列小值
         *
         * @param aClass
         * @param columnName
         * @return
         */
        public Object min(Class<?> aClass, String columnName) {
            if (aClass == null || columnName == null) return null;
            String tableName = aClass.getSimpleName();//表名
            String sqlCode = "select min(" + columnName + ") from " + tableName;//默认的代码
            if (conditions != null && values != null) {
                sqlCode += " where " + conditions;
            }
            //查一下数据库怎么查询该字段是属于什么类型的数据
            Object sum = -1;
            if (sqLiteDatabase2 == null) return null;
            Cursor cursor = sqLiteDatabase2.rawQuery(sqlCode, values);//ok
            if (cursor != null && cursor.moveToFirst()) {
                sum = cursor.getInt(0);
            }
            cursor.close();
            return sum;
        }

        /**
         * 求当平均数
         *
         * @param aClass
         * @param columnName
         * @return
         */
        public Object average(Class<?> aClass, String columnName) {
            if (aClass == null || columnName == null) return null;
            String tableName = aClass.getSimpleName();//表名
            String sqlCode = "select avg(" + columnName + ") from " + tableName;//默认的代码
            if (conditions != null && values != null) {
                sqlCode += " where " + conditions;
            }
            //查一下数据库怎么查询该字段是属于什么类型的数据
            Object sum = -1;
            if (sqLiteDatabase2 == null) return null;
            Cursor cursor = sqLiteDatabase2.rawQuery(sqlCode, values);//ok
            if (cursor != null && cursor.moveToFirst()) {
                sum = cursor.getInt(0);
            }
            cursor.close();
            return sum;
        }

        /**
         * 开启事务
         *
         * @return
         */
        public synchronized Hibernate beginTransaction() {
            if (sqLiteDatabase2 == null) return this;
            sqLiteDatabase2.beginTransaction();//开启事务 ok
            return this;
        }

        /**
         * 设置事务成功
         *
         * @return
         */
        public synchronized Hibernate setTransactionSuccessful() {
            if (sqLiteDatabase2 == null) return this;
            sqLiteDatabase2.setTransactionSuccessful();//设置该事务成功 ok
            return this;
        }

        /**
         * 结束事务
         *
         * @return
         */
        public synchronized Hibernate endTransaction() {
            if (sqLiteDatabase2 == null) return this;
            sqLiteDatabase2.endTransaction();//结束本次事务 ok
            return this;
        }

        /**
         * ======================================================== 表的 增删查改 代码 ========================================
         */


        //============================================= 无实体类操作 =================================

        /**
         * @param tableName     表名称
         * @param contentValues 内容值
         * @return
         * @保存
         */
        public synchronized Hibernate save(String tableName, ContentValues contentValues) {
            if (!isTable(tableName)) {
                err(getLineInfo(LOG.lineInfoIndex), "保存的表不存在，操作失败");
                status = false;
                return this;
            }
            initSqlParameter();
            if (sqLiteDatabase2 == null) return this;
            long insert = sqLiteDatabase2.insert(tableName, null, contentValues);//ok
            statusNumber = (int) insert;
            //设置状态码
            if (insert == -1) {
                status = false;
            } else {
                status = true;
            }
            return this;
        }

        /**
         * 异步保存
         *
         * @param tableName     表名称
         * @param contentValues 内容值
         * @return
         */
        public synchronized Hibernate saveAsync(String tableName, ContentValues contentValues) {
            GT.Thread.runJava(new Runnable() {
                @Override
                public void run() {
                    if (!isTable(tableName)) {
                        err(getLineInfo(LOG.lineInfoIndex), "保存的表不存在，操作失败");
                        status = false;
                        return;
                    }
                    initSqlParameter();
                    if (sqLiteDatabase2 == null) return;
                    long insert = sqLiteDatabase2.insert(tableName, null, contentValues);//ok
                    statusNumber = (int) insert;
                    //设置状态码
                    if (insert == -1) {
                        status = false;
                    } else {
                        status = true;
                    }
                }
            });
            return this;
        }

        /**
         * @param tableName   表名
         * @param condition   条件
         * @param valuesArray 条件值
         * @return
         * @删除
         */
        public synchronized Hibernate delete(String tableName) {

            if (tableName == null || conditions == null || values == null) {
                err(getLineInfo(LOG.lineInfoIndex), "删除的 " + tableName + " 数据为null，操作失败");
                return this;
            }
            //进行删除
            if (!isTable(tableName)) {
                err(getLineInfo(LOG.lineInfoIndex), "删除的表不存在，操作失败");
                status = false;
                return this;
            }
            //初始化
            initSqlParameter();
            if (sqLiteDatabase2 == null) return this;
            int update = sqLiteDatabase2.delete(tableName, conditions, values);//ok
            if (update == 0) {
                status = false;
            } else {
                status = true;
            }
            closeSqlParameter();
            return this;
        }

        /**
         * 异步删除
         *
         * @param tableName   表名
         * @param condition   条件
         * @param valuesArray 条件值
         * @return
         * @删除
         */
        public synchronized Hibernate deleteAsync(String tableName) {
            GT.Thread.runJava(new Runnable() {
                @Override
                public void run() {
                    if (tableName == null || conditions == null || values == null) {
                        err(getLineInfo(LOG.lineInfoIndex), "删除的 " + tableName + " 数据为null，操作失败");
                        return;
                    }
                    //进行删除
                    if (!isTable(tableName)) {
                        err(getLineInfo(LOG.lineInfoIndex), "删除的表不存在，操作失败");
                        status = false;
                    }
                    //初始化
                    initSqlParameter();
                    if (sqLiteDatabase2 == null) return;
                    int update = sqLiteDatabase2.delete(tableName, conditions, values);//ok
                    if (update == 0) {
                        status = false;
                    } else {
                        status = true;
                    }
                    closeSqlParameter();
                }
            });

            return this;
        }

        /**
         * @param tableName   表名
         * @param condition   条件
         * @param valuesArray 条件值
         * @return
         * @查询
         */
        public synchronized Cursor query(String tableName) {

            //判断这个表是否存在
            if (!isTable(tableName)) {
                err(getLineInfo(2), "查询的表不存在，操作失败");
                status = false;
                return null;
            }
            initSqlParameter();
            if (sqLiteDatabase2 == null) return null;
            Cursor cursor = sqLiteDatabase2.query(tableName, returnValues, conditions, values, null, null, orderByStr, limitStr);//ok
            statusNumber = cursor.getCount();
            orderByStr = "";
            limitStr = "";
            cursor.moveToFirst();
            closeSqlParameter();
            return cursor;
        }

        /**
         * @param tableName    表名
         * @param returnValues 返回内容值(若返回全部可写 null)
         * @param condition    条件
         * @param valuesArray  条件值
         * @return
         * @修改
         */
        public synchronized Hibernate update(String tableName, ContentValues contentValues) {
            if (!isTable(tableName)) {
                err(getLineInfo(LOG.lineInfoIndex), "修改的表不存在，操作失败");
                status = false;
                return this;
            }
            initSqlParameter();
            if (sqLiteDatabase2 == null) return this;
            statusNumber = sqLiteDatabase2.update(tableName, contentValues, conditions, values);//ok
            //进行更新
            if (statusNumber == 0) {
                status = false;
            } else {
                status = true;
            }
            closeSqlParameter();
            return this;
        }

        /**
         * 更新异步
         *
         * @param tableName    表名
         * @param returnValues 返回内容值(若返回全部可写 null)
         * @param condition    条件
         * @param valuesArray  条件值
         * @return
         * @修改
         */
        public synchronized Hibernate updateAsync(String tableName, ContentValues contentValues) {
            GT.Thread.runJava(new Runnable() {
                @Override
                public void run() {
                    if (!isTable(tableName)) {
                        err(getLineInfo(LOG.lineInfoIndex), "修改的表不存在，操作失败");
                        status = false;
                        return;
                    }
                    initSqlParameter();
                    if (sqLiteDatabase2 == null) return;
                    statusNumber = sqLiteDatabase2.update(tableName, contentValues, conditions, values);//ok
                    //进行更新
                    if (statusNumber == 0) {
                        status = false;
                    } else {
                        status = true;
                    }
                    closeSqlParameter();
                }
            });
            return this;
        }

        /**
         * @param tableClass
         * @return
         * @删除所有表
         */
        public synchronized Hibernate deleteAll(String tableName) {

            //判断这个表是否存在
            if (!isTable(tableName)) {
                err(getLineInfo(LOG.lineInfoIndex), "删除的表不存在，操作失败");
                status = false;
                return this;
            }
            initSqlParameter();
            if (sqLiteDatabase2 == null) return this;
            statusNumber = sqLiteDatabase2.delete(tableName, null, null);//ok
            //进行更新
            if (statusNumber == 0) {
                status = false;
            } else {
                status = true;
            }

            return this;
        }

        /**
         * 删除全部异步
         *
         * @param tableClass
         * @return
         * @删除所有表
         */
        public synchronized Hibernate deleteAllAsync(String tableName) {

            GT.Thread.runJava(new Runnable() {
                @Override
                public void run() {
                    //判断这个表是否存在
                    if (!isTable(tableName)) {
                        err(getLineInfo(LOG.lineInfoIndex), "删除的表不存在，操作失败");
                        status = false;
                        return;
                    }
                    initSqlParameter();
                    if (sqLiteDatabase2 == null) return;
                    statusNumber = sqLiteDatabase2.delete(tableName, null, null);//ok
                    //进行更新
                    if (statusNumber == 0) {
                        status = false;
                    } else {
                        status = true;
                    }
                }
            });

            return this;
        }

        //============================================= 实体类操作 =================================

        /**
         * @param bean //要保存的对象
         * @return
         * @保存数据
         */
        public synchronized <T> Hibernate save(T bean) {

            if (bean == null) {
                err(getLineInfo(LOG.lineInfoIndex), "保存的对象为null，操作失败！");
                status = false;
                return this;
            }

            String simpleName = bean.getClass().getSimpleName();//获取表名
            //检查数据库中是否存在该表
            if (!isTable(simpleName)) {
                err(getLineInfo(LOG.lineInfoIndex), "当前数据库中 " + simpleName + " 表不存在，操作失败！.");
                status = false;
                return this;
            }

            //创建游标
            ContentValues contentValues = new ContentValues();

            initSqlParameter();

            //保存和修改的操作
            saveAddUpdateOperation(bean, contentValues);

            //开始插入
            if (sqLiteDatabase2 == null) return this;
            long insert = sqLiteDatabase2.insert(bean.getClass().getSimpleName(), null, contentValues);//ok
            statusNumber = (int) insert;
            //设置状态码
            if (insert == -1) {
                status = false;
            } else {
                status = true;
            }


            return this;
        }

        /**
         * 异步保存
         *
         * @param bean //要保存的对象
         * @return
         * @保存数据
         */
        public synchronized <T> Hibernate saveAsync(T bean) {

            GT.Thread.runJava(new Runnable() {
                @Override
                public void run() {
                    if (bean == null) {
                        err(getLineInfo(LOG.lineInfoIndex), "保存的对象为null，操作失败！");
                        status = false;
                        return;
                    }

                    String simpleName = bean.getClass().getSimpleName();//获取表名
                    //检查数据库中是否存在该表
                    if (!isTable(simpleName)) {
                        err(getLineInfo(LOG.lineInfoIndex), "当前数据库中 " + simpleName + " 表不存在，操作失败！.");
                        status = false;
                        return;
                    }

                    //创建游标
                    ContentValues contentValues = new ContentValues();

                    initSqlParameter();

                    //保存和修改的操作
                    saveAddUpdateOperation(bean, contentValues);

                    //开始插入
                    if (sqLiteDatabase2 == null) return;
                    long insert = sqLiteDatabase2.insert(bean.getClass().getSimpleName(), null, contentValues);//ok
                    statusNumber = (int) insert;
                    //设置状态码
                    if (insert == -1) {
                        status = false;
                    } else {
                        status = true;
                    }
                }
            });


            return this;
        }

        /**
         * 如果一次性存储的数据很多，推荐用所有存储
         * 存储所有数据,当前测试效率：存储10万条数据，09:07:53.199——09:07:59.646 耗时6秒多
         * 注意，影响存储的效率有如下：
         * 1.该实体类复杂层度会直接影响存储效率
         * 2.当前装载的安卓系统配置也会影响到存储效率
         *
         * @param beanList
         * @param <T>
         * @return
         */
        public synchronized <T> Hibernate saveAll(List<T> beanList) {

            if (beanList == null && beanList.size() != 0) {
                err(getLineInfo(LOG.lineInfoIndex), "保存全部的对象为null，操作失败！");
                status = false;
                return this;
            }

            long insert = 0;

            String tableName = beanList.get(0).getClass().getSimpleName();
            //检查数据库中是否存在该表
            if (!isTable(tableName)) {
                err(getLineInfo(LOG.lineInfoIndex), "当前数据库中 " + tableName + " 表不存在，操作失败！.");
                status = false;
                return this;
            }

            try {
                beginTransaction();//开启事务
                //创建游标
                ContentValues contentValues = new ContentValues();
                initSqlParameter();
                for (T bean : beanList) {
                    //保存和修改的操作
                    saveAddUpdateOperation(bean, contentValues);//保存全部
                    //数据库操作
                    if (sqLiteDatabase2 == null) return this;
                    insert = sqLiteDatabase2.insert(tableName, null, contentValues);//ok
                    contentValues.clear();
                }
                setTransactionSuccessful();//设置本次事务成功
            } catch (Exception e) {
                err(getLineInfo(LOG.lineInfoIndex), "当前数据库中 " + tableName + " 存储全部，操作失败！.");
                status = false;
                return this;
            } finally {
                endTransaction();//结束事务

            }

            statusNumber = (int) insert;
            //设置状态码
            if (insert == -1) {
                status = false;
            } else {
                status = true;
            }
            return this;
        }

        /**
         * 异步保存
         * 如果一次性存储的数据很多，推荐用所有存储
         * 存储所有数据,当前测试效率：存储10万条数据，09:07:53.199——09:07:59.646 耗时6秒多
         * 注意，影响存储的效率有如下：
         * 1.该实体类复杂层度会直接影响存储效率
         * 2.当前装载的安卓系统配置也会影响到存储效率
         *
         * @param beanList
         * @param <T>
         * @return
         */
        public synchronized <T> Hibernate saveAllAsync(List<T> beanList) {

            GT.Thread.runJava(new Runnable() {
                @Override
                public void run() {
                    if (beanList == null && beanList.size() != 0) {
                        err(getLineInfo(LOG.lineInfoIndex), "保存全部的对象为null，操作失败！");
                        status = false;
                        return;
                    }

                    long insert = 0;

                    String tableName = beanList.get(0).getClass().getSimpleName();
                    //检查数据库中是否存在该表
                    if (!isTable(tableName)) {
                        err(getLineInfo(LOG.lineInfoIndex), "当前数据库中 " + tableName + " 表不存在，操作失败！.");
                        status = false;
                        return;
                    }

                    try {
                        beginTransaction();//开启事务
                        //创建游标
                        ContentValues contentValues = new ContentValues();
                        initSqlParameter();
                        for (T bean : beanList) {
                            //保存和修改的操作
                            saveAddUpdateOperation(bean, contentValues);//保存全部
                            //数据库操作
                            if (sqLiteDatabase2 == null) return;
                            insert = sqLiteDatabase2.insert(tableName, null, contentValues);//ok
                            contentValues.clear();
                        }
                        setTransactionSuccessful();//设置本次事务成功
                    } catch (Exception e) {
                        err(getLineInfo(LOG.lineInfoIndex), "当前数据库中 " + tableName + " 存储全部，操作失败！.");
                        status = false;
                        return;
                    } finally {
                        endTransaction();//结束事务

                    }

                    statusNumber = (int) insert;
                    //设置状态码
                    if (insert == -1) {
                        status = false;
                    } else {
                        status = true;
                    }
                }
            });
            return this;
        }

        /**
         * 删除表(如果没有设置 删除条件 与 条件值 将无法删除)
         *
         * @param beanClass  删除表的名称
         * @param conditions 删除条件
         * @param values     删除条件对应值
         * @return
         */
        public synchronized <T> Hibernate delete(T classOrBean) {

            Class<?> aClass = classOrBean.getClass();

            if (classOrBean == null || (classOrBean.getClass() == Class.class && (conditions == null || values == null))) {
                err(getLineInfo(LOG.lineInfoIndex), "删除的 " + classOrBean + " 数据为null，操作失败");
                return this;
            }


            if (classOrBean.getClass() == Class.class) {
                aClass = (Class) classOrBean;
            } else {
                aClass = classOrBean.getClass();
            }

            String simpleName = aClass.getSimpleName();//获取表名

            //如果没有自己设置条件，那就直接使用该对象的ID值进行默认操作
            if (conditions == null || values == null) {
                wheresKeyValue(classOrBean, aClass);
            }

            //检查数据库中是否存在该表
            if (!isTable(simpleName)) {
                err(getLineInfo(LOG.lineInfoIndex), "当前数据库中 " + simpleName + " 表不存在，操作失败！...");
                status = false;
                return this;
            }

            initSqlParameter();

            //进行更新
            if (sqLiteDatabase2 == null) return this;
            int update = sqLiteDatabase2.delete(simpleName, conditions, values);//ok

            if (update == 0) {
                status = false;
            } else {
                status = true;
            }

            closeSqlParameter();

            return this;
        }

        /**
         * 异步删除
         * 删除表(如果没有设置 删除条件 与 条件值 将无法删除)
         *
         * @param beanClass  删除表的名称
         * @param conditions 删除条件
         * @param values     删除条件对应值
         * @return
         */
        public synchronized <T> Hibernate deleteAsync(T classOrBean) {

            GT.Thread.runJava(new Runnable() {
                @Override
                public void run() {
                    Class<?> aClass = classOrBean.getClass();

                    if (classOrBean == null || (classOrBean.getClass() == Class.class && (conditions == null || values == null))) {
                        err(getLineInfo(LOG.lineInfoIndex), "删除的 " + classOrBean + " 数据为null，操作失败");
                        return;
                    }

                    if (classOrBean.getClass() == Class.class) {
                        aClass = (Class) classOrBean;
                    } else {
                        aClass = classOrBean.getClass();
                    }

                    String simpleName = aClass.getSimpleName();//获取表名

                    //如果没有自己设置条件，那就直接使用该对象的ID值进行默认操作
                    if (conditions == null || values == null) {
                        wheresKeyValue(classOrBean, aClass);
                    }

                    //检查数据库中是否存在该表
                    if (!isTable(simpleName)) {
                        err(getLineInfo(LOG.lineInfoIndex), "当前数据库中 " + simpleName + " 表不存在，操作失败！...");
                        status = false;
                        return;
                    }

                    initSqlParameter();

                    //进行更新
                    if (sqLiteDatabase2 == null) return;
                    int update = sqLiteDatabase2.delete(simpleName, conditions, values);//ok

                    if (update == 0) {
                        status = false;
                    } else {
                        status = true;
                    }

                    closeSqlParameter();
                }
            });

            return this;
        }

        /**
         * @param beanClass 删除的表
         * @param keyValue  删除的ID
         * @return
         * @更具ID删除
         */
        public synchronized Hibernate delete(Class<?> beanClass, Object keyValue) {

            if (beanClass == null || keyValue == null) {
                err(getLineInfo(LOG.lineInfoIndex), "删除的 beanClass 数据为null，操作失败");
                return this;
            }

            String simpleName = beanClass.getSimpleName();//获取表名

            //检查数据库中是否存在该表
            List<String> tableAllValue = null;
            if (isTable(simpleName)) {
                tableAllValue = getTableAllValue(simpleName);
                if (tableAllValue == null || tableAllValue.size() == 0) {
                    err(getLineInfo(LOG.lineInfoIndex), "删除的表数据为空，操作失败");
                    status = false;
                    return this;
                }
            } else {
                err(getLineInfo(LOG.lineInfoIndex), "删除的表不存在，操作失败");
                status = false;
                return this;
            }

            //进行更新
            if (sqLiteDatabase2 == null) return this;
            int update = sqLiteDatabase2.delete(simpleName, tableAllValue.get(0) + "= ?", new String[]{keyValue.toString()});//ok

            if (update == 0) {
                status = false;
            } else {
                status = true;
            }

            return this;
        }

        /**
         * 异步删除
         *
         * @param beanClass 删除的表
         * @param keyValue  删除的ID
         * @return
         * @更具ID删除
         */
        public synchronized Hibernate deleteAsync(Class<?> beanClass, Object keyValue) {

            GT.Thread.runJava(new Runnable() {
                @Override
                public void run() {
                    if (beanClass == null || keyValue == null) {
                        err(getLineInfo(LOG.lineInfoIndex), "删除的 beanClass 数据为null，操作失败");
                        return;
                    }

                    String simpleName = beanClass.getSimpleName();//获取表名

                    //检查数据库中是否存在该表
                    List<String> tableAllValue = null;
                    if (isTable(simpleName)) {
                        tableAllValue = getTableAllValue(simpleName);
                        if (tableAllValue == null || tableAllValue.size() == 0) {
                            err(getLineInfo(LOG.lineInfoIndex), "删除的表数据为空，操作失败");
                            status = false;
                            return;
                        }
                    } else {
                        err(getLineInfo(LOG.lineInfoIndex), "删除的表不存在，操作失败");
                        status = false;
                        return;
                    }

                    //进行更新
                    if (sqLiteDatabase2 == null) return;
                    int update = sqLiteDatabase2.delete(simpleName, tableAllValue.get(0) + "= ?", new String[]{keyValue.toString()});//ok

                    if (update == 0) {
                        status = false;
                    } else {
                        status = true;
                    }
                }
            });

            return this;
        }

        /**
         * @param tableClass
         * @return
         * @删除所有表
         */
        public synchronized Hibernate deleteAll(Class<?> tableClass) {

            //初始化与获取必要属性
            String simpleName = tableClass.getSimpleName();//获取表名

            //判断这个表是否存在
            if (!isTable(simpleName)) {
                err(getLineInfo(LOG.lineInfoIndex), "删除的表不存在，操作失败");
                status = false;
                return this;
            }
            initSqlParameter();
            if (sqLiteDatabase2 == null) return this;
            statusNumber = sqLiteDatabase2.delete(simpleName, conditions, values);//ok
            //进行更新
            if (statusNumber == 0) {
                status = false;
            } else {
                status = true;
            }
            closeSqlParameter();
            return this;
        }

        /**
         * 异步删除
         *
         * @param tableClass
         * @return
         * @删除所有表
         */
        public synchronized Hibernate deleteAllAsync(Class<?> tableClass) {

            GT.Thread.runJava(new Runnable() {
                @Override
                public void run() {
                    //初始化与获取必要属性
                    String simpleName = tableClass.getSimpleName();//获取表名

                    //判断这个表是否存在
                    if (!isTable(simpleName)) {
                        err(getLineInfo(LOG.lineInfoIndex), "删除的表不存在，操作失败");
                        status = false;
                        return;
                    }
                    initSqlParameter();
                    if (sqLiteDatabase2 == null) return;
                    statusNumber = sqLiteDatabase2.delete(simpleName, conditions, values);//ok
                    //进行更新
                    if (statusNumber == 0) {
                        status = false;
                    } else {
                        status = true;
                    }
                    closeSqlParameter();
                }
            });

            return this;
        }

        /**
         * @param tableNameClass 查询的表
         * @param values         查询ID
         * @param <T>
         * @return
         * @根据表ID查询数据
         */
        public synchronized <T> T query(Class<T> tableNameClass) {
            if (tableNameClass == null) {
                err(getLineInfo(LOG.lineInfoIndex), "查询的数据为 null，操作失败");
                status = false;
                return null;
            }
            //反射生成对象并注入
            List<T> list = queryCoreAlgorithm(tableNameClass, false);
            if (list == null || list.size() == 0) {
                return null;
            } else {
                return list.get(0);
            }
        }

        /**
         * @param tableNameClass 查询的表
         * @param values         查询ID
         * @param <T>
         * @return
         * @根据表ID查询数据
         */
        public synchronized <T> T query(Class<T> tableNameClass, Object values) {

            if (tableNameClass == null || values == null) {
                err(getLineInfo(LOG.lineInfoIndex), "查询的数据为 null，操作失败");
                status = false;
                return null;
            }

            String tableName = tableNameClass.getSimpleName();

            //判断这个表是否存在
            if (!isTable(tableName)) {
                err(getLineInfo(LOG.lineInfoIndex), "查询的表不存在，操作失败");
                status = false;
                return null;
            }

            //获取表名
            List<String> tableAllValue = getTableAllValue(tableName);

            //条件参数
            where(tableAllValue.get(0) + " = ?", new String[]{values.toString()});

            //反射生成对象并注入
            List<T> list = queryCoreAlgorithm(tableNameClass, false);
            if (list == null || list.size() == 0) {
                return null;
            } else {
                return list.get(0);
            }
        }

        /**
         * 根据表查询表中所有数据
         *
         * @param tableNameClass
         * @param <T>
         * @return
         */
        public synchronized <T> List<T> queryAll(Class<T> tableNameClass) {
            if (tableNameClass == null) {
                err(getLineInfo(LOG.lineInfoIndex), "查询的数据为 null，操作失败");
                status = false;
                return null;
            }

            //算法
            List<T> list = queryCoreAlgorithm(tableNameClass, true);
            return list;
        }

        /**
         * @param bean       更新的表数据(实体类)
         * @param conditions 修改条件
         * @param values     修改条件对应值
         * @return
         * @更新表
         */
        public synchronized <T> Hibernate update(T bean) {
            if (bean == null) {
                err(getLineInfo(LOG.lineInfoIndex), "修改的数据为null，操作失败");
                status = false;
                return this;
            }

            Class<?> aClass = bean.getClass();

            String simpleName = aClass.getSimpleName();//获取表名
            //检查数据库中是否存在该表
            if (!isTable(simpleName)) {
                err(getLineInfo(LOG.lineInfoIndex), "当前数据库中 " + simpleName + " 表不存在，操作失败！.");
                status = false;
                return this;
            }

            //如果没有自己设置条件，那就直接使用该对象的ID值进行默认操作
            if (conditions == null || values == null) {
                wheresKeyValue(bean, aClass);//第一次调用
            }

            ContentValues contentValues = new ContentValues();
            initSqlParameter();
            //保存和修改的操作
            saveAddUpdateOperation(bean, contentValues);
            if (sqLiteDatabase2 == null) return this;
            statusNumber = sqLiteDatabase2.update(aClass.getSimpleName(), contentValues, conditions, values);//ok
            //进行更新
            if (statusNumber == 0) {
                status = false;
            } else {
                status = true;
            }
            closeSqlParameter();
            return this;
        }

        /**
         * 异步更新
         *
         * @param bean       更新的表数据(实体类)
         * @param conditions 修改条件
         * @param values     修改条件对应值
         * @return
         * @更新表
         */
        public synchronized <T> Hibernate updateAsync(T bean) {
            GT.Thread.runJava(new Runnable() {
                @Override
                public void run() {
                    if (bean == null) {
                        err(getLineInfo(LOG.lineInfoIndex), "修改的数据为null，操作失败");
                        status = false;
                        return;
                    }

                    Class<?> aClass = bean.getClass();

                    String simpleName = aClass.getSimpleName();//获取表名
                    //检查数据库中是否存在该表
                    if (!isTable(simpleName)) {
                        err(getLineInfo(LOG.lineInfoIndex), "当前数据库中 " + simpleName + " 表不存在，操作失败！.");
                        status = false;
                        return;
                    }

                    //如果没有自己设置条件，那就直接使用该对象的ID值进行默认操作
                    if (conditions == null || values == null) {
                        wheresKeyValue(bean, aClass);//第一次调用
                    }

                    ContentValues contentValues = new ContentValues();
                    initSqlParameter();
                    //保存和修改的操作
                    saveAddUpdateOperation(bean, contentValues);
                    if (sqLiteDatabase2 == null) return;
                    statusNumber = sqLiteDatabase2.update(aClass.getSimpleName(), contentValues, conditions, values);//ok
                    //进行更新
                    if (statusNumber == 0) {
                        status = false;
                    } else {
                        status = true;
                    }
                    closeSqlParameter();
                }
            });
            return this;
        }

        /**
         * @param bean       更新的表数据(实体类)
         * @param conditions 修改条件
         * @param values     修改条件对应值
         * @return
         * @更新表
         */
        public synchronized Hibernate update(Class<?> tableClass, Object bean) {
            if (bean == null) {
                err(getLineInfo(LOG.lineInfoIndex), "修改的数据为null，操作失败");
                status = false;
                return this;
            }

            String simpleName = bean.getClass().getSimpleName();//获取表名
            //检查数据库中是否存在该表
            if (!isTable(simpleName)) {
                err(getLineInfo(LOG.lineInfoIndex), "当前数据库中 " + simpleName + " 表不存在，操作失败！.");
                status = false;
                return this;
            }

            //判断更新对象是 实体类 还是 ContentValues 对象
            if (ContentValues.class != bean.getClass()) {
                ContentValues contentValues = new ContentValues();
                initSqlParameter();
                //保存和修改的操作
                saveAddUpdateOperation(bean, contentValues);

                if (conditions == null || values == null) {
                    wheresKeyValue(bean, tableClass);
                }

                if (sqLiteDatabase2 == null) return this;
                statusNumber = sqLiteDatabase2.update(bean.getClass().getSimpleName(), contentValues, conditions, values);//ok
            } else {
                if (conditions == null || values == null) {
                    err(getLineInfo(LOG.lineInfoIndex), "修改的数据为null，操作失败");
                    status = false;
                    return this;
                }
                if (sqLiteDatabase2 == null) return this;
                statusNumber = sqLiteDatabase2.update(tableClass.getSimpleName(), (ContentValues) bean, conditions, values);//ok
            }
            //进行更新
            if (statusNumber == 0) {
                status = false;
            } else {
                status = true;
            }
            closeSqlParameter();
            return this;
        }

        /**
         * 异步更新
         *
         * @param bean       更新的表数据(实体类)
         * @param conditions 修改条件
         * @param values     修改条件对应值
         * @return
         * @更新表
         */
        public synchronized Hibernate updateAsync(Class<?> tableClass, Object bean) {
            GT.Thread.runJava(new Runnable() {
                @Override
                public void run() {
                    if (bean == null) {
                        err(getLineInfo(LOG.lineInfoIndex), "修改的数据为null，操作失败");
                        status = false;
                        return;
                    }

                    String simpleName = bean.getClass().getSimpleName();//获取表名
                    //检查数据库中是否存在该表
                    if (!isTable(simpleName)) {
                        err(getLineInfo(LOG.lineInfoIndex), "当前数据库中 " + simpleName + " 表不存在，操作失败！.");
                        status = false;
                        return;
                    }

                    //判断更新对象是 实体类 还是 ContentValues 对象
                    if (ContentValues.class != bean.getClass()) {
                        ContentValues contentValues = new ContentValues();
                        initSqlParameter();
                        //保存和修改的操作
                        saveAddUpdateOperation(bean, contentValues);

                        if (conditions == null || values == null) {
                            wheresKeyValue(bean, tableClass);
                        }

                        if (sqLiteDatabase2 == null) return;
                        statusNumber = sqLiteDatabase2.update(bean.getClass().getSimpleName(), contentValues, conditions, values);//ok
                    } else {
                        if (conditions == null || values == null) {
                            err(getLineInfo(LOG.lineInfoIndex), "修改的数据为null，操作失败");
                            status = false;
                            return;
                        }
                        if (sqLiteDatabase2 == null) return;
                        statusNumber = sqLiteDatabase2.update(tableClass.getSimpleName(), (ContentValues) bean, conditions, values);//ok
                    }
                    //进行更新
                    if (statusNumber == 0) {
                        status = false;
                    } else {
                        status = true;
                    }
                    closeSqlParameter();
                }
            });
            return this;
        }

        /**
         * @param bean     修改表的 实体类 或 ContentValues 值
         * @param keyValue 修改表里的那个ID行数据
         * @return
         * @修改表
         */
        public synchronized Hibernate update(Object bean, Object keyValue) {

            //判空
            if (bean == null || keyValue == null) {
                err(getLineInfo(LOG.lineInfoIndex), "修改的参数为null，操作失败");
                status = false;
                return this;
            }

            //初始化与获取必要属性
            ContentValues contentValues = new ContentValues();
            statusNumber = 0;

            String simpleName = bean.getClass().getSimpleName();//获取表名
            //判断这个表是否存在
            List<String> tableAllValue = null;
            if (isTable(simpleName)) {
                tableAllValue = getTableAllValue(simpleName);
                if (tableAllValue == null || tableAllValue.size() == 0) {
                    err(getLineInfo(LOG.lineInfoIndex), "修改的表数据为空，操作失败");
                    status = false;
                    return this;
                }
            } else {
                err(getLineInfo(LOG.lineInfoIndex), "修改的表不存在，操作失败");
                status = false;
                return this;
            }

            initSqlParameter();
            //保存和修改的操作
            saveAddUpdateOperation(bean, contentValues);
            if (sqLiteDatabase2 == null) return this;
            statusNumber = sqLiteDatabase2.update(simpleName, contentValues, tableAllValue.get(0) + "= ?", new String[]{keyValue.toString()});//ok

            //进行更新
            if (statusNumber == 0) {
                status = false;
            } else {
                status = true;
            }

            return this;
        }

        /**
         * 异步更新
         *
         * @param bean     修改表的 实体类 或 ContentValues 值
         * @param keyValue 修改表里的那个ID行数据
         * @return
         * @修改表
         */
        public synchronized Hibernate updateAsync(Object bean, Object keyValue) {

            GT.Thread.runJava(new Runnable() {
                @Override
                public void run() {
                    //判空
                    if (bean == null || keyValue == null) {
                        err(getLineInfo(LOG.lineInfoIndex), "修改的参数为null，操作失败");
                        status = false;
                        return;
                    }

                    //初始化与获取必要属性
                    ContentValues contentValues = new ContentValues();
                    statusNumber = 0;

                    String simpleName = bean.getClass().getSimpleName();//获取表名
                    //判断这个表是否存在
                    List<String> tableAllValue = null;
                    if (isTable(simpleName)) {
                        tableAllValue = getTableAllValue(simpleName);
                        if (tableAllValue == null || tableAllValue.size() == 0) {
                            err(getLineInfo(LOG.lineInfoIndex), "修改的表数据为空，操作失败");
                            status = false;
                            return;
                        }
                    } else {
                        err(getLineInfo(LOG.lineInfoIndex), "修改的表不存在，操作失败");
                        status = false;
                        return;
                    }

                    initSqlParameter();
                    //保存和修改的操作
                    saveAddUpdateOperation(bean, contentValues);
                    if (sqLiteDatabase2 == null) return;
                    statusNumber = sqLiteDatabase2.update(simpleName, contentValues, tableAllValue.get(0) + "= ?", new String[]{keyValue.toString()});//ok

                    //进行更新
                    if (statusNumber == 0) {
                        status = false;
                    } else {
                        status = true;
                    }
                }
            });

            return this;
        }

        /**
         * @param tableClass
         * @param contentValues
         * @return
         * @更新这张表全部数据
         */
        public synchronized Hibernate updateAll(Class<?> tableClass, ContentValues contentValues) {

            //判空
            if (contentValues == null || tableClass == null) {
                err(getLineInfo(LOG.lineInfoIndex), "修改的参数为null，操作失败");
                status = false;
                return this;
            }

            //初始化与获取必要属性
            String simpleName = tableClass.getSimpleName();//获取表名
            statusNumber = 0;

            //判断这个表是否存在
            if (!isTable(simpleName)) {
                err(getLineInfo(LOG.lineInfoIndex), "修改的表不存在，操作失败");
                status = false;
                return this;
            }
            if (sqLiteDatabase2 == null) return this;
            statusNumber = sqLiteDatabase2.update(simpleName, contentValues, conditions, values);//ok
            //进行更新
            if (statusNumber == 0) {
                status = false;
            } else {
                status = true;
            }

            return this;
        }

        /**
         * 异步更新
         *
         * @param tableClass
         * @param contentValues
         * @return
         * @更新这张表全部数据
         */
        public synchronized Hibernate updateAllAsync(Class<?> tableClass, ContentValues contentValues) {

            GT.Thread.runJava(new Runnable() {
                @Override
                public void run() {
                    //判空
                    if (contentValues == null || tableClass == null) {
                        err(getLineInfo(LOG.lineInfoIndex), "修改的参数为null，操作失败");
                        status = false;
                        return;
                    }

                    //初始化与获取必要属性
                    String simpleName = tableClass.getSimpleName();//获取表名
                    statusNumber = 0;

                    //判断这个表是否存在
                    if (!isTable(simpleName)) {
                        err(getLineInfo(LOG.lineInfoIndex), "修改的表不存在，操作失败");
                        status = false;
                        return;
                    }
                    if (sqLiteDatabase2 == null) return;
                    statusNumber = sqLiteDatabase2.update(simpleName, contentValues, conditions, values);//ok
                    //进行更新
                    if (statusNumber == 0) {
                        status = false;
                    } else {
                        status = true;
                    }
                }
            });

            return this;
        }

        /**
         * ======================================================== Hibernate 创建数据库 代码 ========================================
         */

        //====================================== 设置扫描表路径 ======================================

        /**
         * @param scanTablePath (使用详情请参考官网)
         * @return
         * @主要作用：将类转为SQL代码
         * @初始化扫描数据库实体类路径
         * @可输入的值如下：
         * @1.实体类的包路径 com.gsls.gtlibrary.enity
         * @2.实体类的class User.class
         * @3.实体类的 List/Set/Array ：List<Class<?>> 、 Set<Class<?>、 Class<?>[]
         */
        public Hibernate scanTable(Object scanTable) {
//            GT.log("反射解析");
            isDeleteNullTable = true;//需要检查是否有要删除的表
            isCreateTable = true;
            tableNameList.clear();
            creationTableNameList.clear();
            updateTableValueList.clear();
            //判空
            if (scanTable == null) {
                scanTable = ApplicationUtils.getPackageName(context);
            }

            //实例化 数据库表集合
            if (tableList == null) {
                tableList = new ArrayList<>();
            } else {
                tableList.clear();//清空
            }

            boolean isReflect = false;//是否需要反射得到 class
            //解析路径 或 引用赋值
            String EnityPackagePath = "";
            if (scanTable instanceof Class<?>) {//添加单个的表
                Class<?> classs = (Class<?>) scanTable;
                tableList.add(classs);//添加到数据库表的集合中
            } else if (scanTable instanceof String) {//添加该路径下所有表
                EnityPackagePath = scanTable.toString();//如果是路径直接赋值
                isReflect = true;
            } else if (scanTable instanceof List) {//添加该集合中所有表
                tableList = (List<Class<?>>) scanTable;//如果是class 集合直接引用
            } else if (scanTable instanceof Set) {//添加该集合中所有表
                Set<Class<?>> tableSet = (Set<Class<?>>) scanTable;//如果是 Set 转换到 List 去
                for (Class<?> classs : tableSet) {
                    tableList.add(classs);
                }
            } else if (scanTable instanceof Class<?>[]) {//添加该集合中所有表
                Class<?>[] classArray = (Class<?>[]) scanTable;//如果是 Array 转换到 List 去
                for (Class<?> classs : classArray) {
                    tableList.add(classs);
                }
            } else {
                errs("当前不支持 " + scanTable.getClass() + "  类型。");
                isCreateTable = false;
                return this;
            }

            //检测扫描路径
            if (isReflect) {
                //如果需要反射就进行反射得到 class
                if (!"".equals(EnityPackagePath)) {
                    loadHibernateAnnotation(EnityPackagePath, context);//加载数据库注解
                } else {
                    errs("当前扫描数据库实体类的路径有错误！请检查该路径。EnityPackagePath = " + EnityPackagePath);
                    isCreateTable = false;
                    return this;
                }
            }

            //进行 class 反射解析
            if (tableList != null && tableList.size() > 0) {
                analysisClassData();//解析 class
            }

            return this;
        }
        //====================================== 第三步：反射解析 class 信息自动生成 SQL代码 =================================

        /**
         * @反射解析 Class 数据
         * @return
         * @待完成
         */
        private final List<String> creationTableNameList = new ArrayList<>();//实例化表名集合
        private final List<String> tableNameList = new ArrayList<>();//实例化表名集合
        private final List<String> updateTableValueList = new ArrayList<>();//实例化存储更新字段的集合
        private String tableSqlCode;//创建表的SQL语句
        private String sqlChar = "";//添加要创建表字段
        private String KeyType = "";//创建主键的类型
        private String KeySqlCode = "";//创建主键的数据库代码
        private String fieldPropertyCode = "";//字段属性

        //解析 主键 与 字段 自动生成 SQL 语句
        private Hibernate analysisClassData() {

            KeyType = "";//初始化该表的主键
            KeySqlCode = "";//初始化该表的主键代码

            //遍历所有集成的 SQL 语句
            for (int i = 0; i < tableList.size(); i++) {

                Class<?> aClass = tableList.get(i);//获取需要处理的 Class

                String tableName = aClass.getSimpleName();//获取表名
                if (tableNameList.contains(tableName)) continue;//如果当前数据库表中已经存在该表，那就不再添加

                //存储需要更新的数据库字段
                saveUpdateData(aClass);

                //添加解析表名
                tableNameList.add(tableName);//存储表名

                //初始化
                tableSqlCode = "CREATE TABLE " + tableName + "(";//创建表的SQL语句
                sqlChar = "";//初始化 SQL 字段
                KeySqlCode = "";//初始化主键的创建代码
                fieldPropertyCode = "";//字段属性代码

                //解析类中所有字段
                String returnData = analysisClassAllChar(aClass);
                if ("this".equals(returnData)) {
                    return this;
                }

                creationTableNameList.add(tableSqlCode + KeySqlCode + sqlChar + ")");//添加在创建表时需要执行的
            }
            return this;
        }


        /**
         * 解析类中所有字段
         *
         * @param aClass 需要递归父类
         */
        private String analysisClassAllChar(Class<?> aClass) {

            String tableName = aClass.getSimpleName();//获取表名

            //遍历所有成员变量
            for (Field field : aClass.getDeclaredFields()) {

                fieldPropertyCode = "";//初始化
                Class<?> type = field.getType();//获取当前字段类型
                String tableChar = field.getName();//获取当前字段的名称

                GT_Property gt_Property = field.getAnnotation(GT_Property.class);//创建表时的
                if (gt_Property != null && gt_Property.setNotInit()) {
                    continue;
                }

                //解析主键：优先使用子表主键
                GT_Key gt_Key = field.getAnnotation(GT_Key.class);//创建表时的
                if (gt_Key != null) {
                    //如果注解为主键，且是第一次的主键(从子表向父表递归)，那就进行生成主键
                    if (KeySqlCode.length() != 0) {
                        continue;//跳过本次
                    }

                    //字段属性解析设置
                    int fieldLength = gt_Key.setLength();          //获取字段长度,仅对String有效
                    String[] checkValuesLength = gt_Key.setCheckValues();    //获取限定数据，仅String字段使用
                    double max = gt_Key.setMax();                  //最大值
                    double min = gt_Key.setMin();                  //最小值
                    if (checkValuesLength.length != 0) {//TODO男女的还不行,如果是这种就完犊子了 (你，我，他，其他)
                        fieldPropertyCode = " CHECK(";
                        //遍历添加限制值
                        for (int i = 0; i < checkValuesLength.length; i++) {
                            if (i >= checkValuesLength.length - 1) {
                                fieldPropertyCode += tableChar + "='" + checkValuesLength[i] + "'";
                            } else {
                                fieldPropertyCode += tableChar + "='" + checkValuesLength[i] + "' OR ";
                            }

                        }


                        fieldPropertyCode += ")";
                    } else if (max != Double.MAX_VALUE || min != Double.MIN_VALUE) {
                        fieldPropertyCode = " CHECK(" + tableChar + ">=" + min + " AND " + tableChar + "<=" + max + ")";
                    }


                    //若需要兼容其他字段可直接在这添加，不会对其他代码有影响
                    if (String.class == type) {
                        KeyType = "varchar(" + fieldLength + ")" + fieldPropertyCode;
                    } else if (int.class == type || Integer.class == type) {
                        KeyType = "integer" + fieldPropertyCode;
                    } else if (boolean.class == type || Boolean.class == type) {
                        KeyType = "BOOLEAN";
                    } else if (double.class == type || Double.class == type) {
                        KeyType = "DOUBLE" + fieldPropertyCode;
                    } else if (float.class == type || Float.class == type) {
                        KeyType = "FLOAT" + fieldPropertyCode;
                    } else if (Time.class == type) {
                        KeyType = "TIME";
                    } else if (Date.class == type || java.sql.Date.class == type) {
                        KeyType = "DATE";
                    } else if (long.class == type || Long.class == type) {
                        KeyType = "LONG" + fieldPropertyCode;
                    } else if (short.class == type || Short.class == type) {
                        KeyType = "varchar(6)";
                    } else if (byte[].class.equals(type)) {
                        KeyType = "BLOB";
                    } else if (List.class == type || Map.class == type) {
                        KeyType = "TEXT";
                    } else {
                        err(getLineInfo(), "解析的表文件 [" + tableName + "] 类中的字段 [" + field + "] 出现不支持类型。");
                        isCreateTable = false;
                        return "this";
                    }

                    //TODO 自增需要增加 String、Bolean、Float、Time
                    boolean autoincrement = gt_Key.setAutoincrement();//获取 主键类型 注解值
                    if (autoincrement) {//自动增长
//                        GT.log(tableName + "自增长:" + tableChar + " 类型:" + KeyType);
                        if (KeyType.contains("integer") || KeyType.contains("LONG")) {
                            KeySqlCode = field.getName() + " " + KeyType.toUpperCase() + " PRIMARY KEY AUTOINCREMENT NOT NULL";
                        } else {
                            err(getLineInfo(), "解析的表文件 [" + tableName + "] 类中的字段 [" + field + "] 不是int类型，暂不支持自增。");
                            isCreateTable = false;
                            return "this";
                        }
                    } else {//手动增长
//                        GT.log(tableName + "手动增长:" + tableChar);
                        if ("integer".equals(KeyType)) {//如果是整数型就需要转换一下
                            KeyType = "INT";
                        }
                        KeySqlCode = field.getName() + " " + KeyType.toUpperCase() + " PRIMARY KEY NOT NULL";
                    }
                    continue;//跳过字段的解析
                }


                //TODO 普通表字段解析
                int fieldLength = 200;//字段长度
                if (gt_Property != null) {

                    //字段属性解析设置
                    fieldLength = gt_Property.setLength();                  //获取字段长度,仅对String有效
                    String[] checkValuesLength = gt_Property.setCheckValues();        //获取限定数据，仅String字段使用
                    double max = gt_Property.setMax();                      //最大值
                    double min = gt_Property.setMin();                      //最小值
                    boolean notNull = gt_Property.setNotNull();             //不为null,默认值是 false
                    String[] defaultValue = gt_Property.setDefaultValue();  //默认值,如果只有一个那就在创建的时候直接定死，如果有多个，那就直接去保存，修改操作里去随机默认值

                    //字段限定值
                    if (checkValuesLength.length != 0) {
                        fieldPropertyCode = " CHECK(";
                        //遍历添加限制值
                        for (int i = 0; i < checkValuesLength.length; i++) {
                            if (i >= checkValuesLength.length - 1) {
                                fieldPropertyCode += tableChar + "='" + checkValuesLength[i] + "'";
                            } else {
                                fieldPropertyCode += tableChar + "='" + checkValuesLength[i] + "' OR ";
                            }

                        }
                        fieldPropertyCode += ")";
                    } else if (max != Double.MAX_VALUE || min != Double.MIN_VALUE) {
                        //CHECK(shortValue between 1 and 5)
                        fieldPropertyCode = " CHECK(" + tableChar + " BETWEEN " + (min - 1) + " AND " + max + ")";
                    }

                    //是否可为null
                    if (notNull) {
                        fieldPropertyCode += " NOT NULL";//添加不可以为null
                    }


                    //设置默认值
                    if (defaultValue.length == 1) {
                        if (int.class == type ||
                                boolean.class == type ||
                                double.class == type ||
                                float.class == type ||
                                long.class == type ||
                                short.class == type) {
                            fieldPropertyCode += " DEFAULT " + defaultValue[0] + "";
                        } else {
                            fieldPropertyCode += " DEFAULT '" + defaultValue[0] + "'";
                        }

                    }

                    //开始其他属性的解析


                }


                if (sqlChar.contains(tableChar)) {
                    continue;//过滤掉第二次添加的(父类)已添加的字段字段的解析
                }

                //若需要兼容其他字段可直接在这添加，不会对其他代码有影响
                if (String.class == type) {
                    sqlChar += (", " + tableChar + " VARCHAR(" + fieldLength + ")" + fieldPropertyCode);
                } else if (int.class == type) {
                    sqlChar += (", " + tableChar + " INT" + fieldPropertyCode);
                } else if (Integer.class == type) {
                    sqlChar += (", " + tableChar + " INTEGER" + fieldPropertyCode);
                } else if (boolean.class == type || Boolean.class == type) {
                    sqlChar += (", " + tableChar + " BOOLEAN" + fieldPropertyCode);
                } else if (double.class == type || Double.class == type) {
                    sqlChar += (", " + tableChar + " DOUBLE" + fieldPropertyCode);
                } else if (float.class == type || Float.class == type) {
                    sqlChar += (", " + tableChar + " FLOAT" + fieldPropertyCode);
                } else if (Time.class == type) {
                    sqlChar += (", " + tableChar + " TIME" + fieldPropertyCode);
                } else if (Date.class == type || java.sql.Date.class == type) {
                    sqlChar += (", " + tableChar + " DATE" + fieldPropertyCode);
                } else if (long.class == type || Long.class == type) {
                    sqlChar += (", " + tableChar + "  VARCHAR(20)" + fieldPropertyCode);
                } else if (short.class == type || Short.class == type) {
                    sqlChar += (", " + tableChar + "  VARCHAR(6)" + fieldPropertyCode);
                } else if (byte[].class.equals(type)) {
                    sqlChar += (", " + tableChar + " BLOB");
                } else if (List.class == type || Map.class == type) {
                    sqlChar += (", " + tableChar + " TEXT");
                } else {
                    //自动创建
//                        err(getLineInfo(), "解析的表文件 [" + tableName + "] 类中的字段 [" + field + "] 出现不支持类型。");
//                        isCreateTable = false;
                    sqlChar += (", " + tableChar + " TEXT");
//                        return this;
                }

            }

            //解析父类,放在下面代表，在父类子类有相同字段时，优先选用子表里的字段
            Class<?> superclass = aClass.getSuperclass();
            if (Object.class != superclass) {
                analysisClassAllChar(superclass);
            }

            return "";
        }


        /**
         * @param aClass
         * @存储需要更新的数据
         */
        private synchronized void saveUpdateData(Class<?> mClass) {
            String simpleName = mClass.getSimpleName();//获取表名
            Field[] fields = mClass.getDeclaredFields();//获取所有成员变量
            String oldStr = "";//存储旧字段
            String newStr = "";//存储新字段
            for (Field field : fields) {
                String newTableName = field.getName();//获取新字段名
                GT_Property gt_Property = field.getAnnotation(GT_Property.class);//保存修改数据解析
                if (gt_Property != null) {
                    String oldTableName = gt_Property.setOldTableValue();//获取注解值
                    if (oldTableName != null && !"".equals(oldTableName)) {
                        //过滤掉 字段值 与 注解值 一样的问题
                        if (newTableName.equals(oldTableName)) continue;
                        oldStr += oldTableName + " ";
                        newStr += newTableName + " ";
                    }
                }
            }
            if (oldStr != null && newStr != null && !"".equals(oldStr) && !"".equals(newStr)) {
                updateTableValueList.add(simpleName + "{" + oldStr + "," + newStr + "}");
            }
        }

        //====================================== 第四步：创建数据库对象 ======================================
        public boolean isCreateTable = true;//是否创建数据库

        private Hibernate initSQL(String sqlName, int sqlVersion, String passWord, Object scanTable) {

            //初始化数据库名称
            if (sqlName != null) {
                DATABASE_NAME = sqlName + ".db";
            }

            //初始化数据库版本号
            if (sqlVersion > 0) {
                DATABASE_VERSION = sqlVersion;
            }

            //初始化数据库加密密码
            if (passWord != null) {
                this.passWord = passWord;
            }

            if (isCreateTable) {//是否执行创建数据库代码
                SCAN_TABLE = scanTable;//映射表的方式赋值
                isDeleteNullTable = false;
                DatabaseHelper databaseHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

                sqLiteDatabase2 = databaseHelper.getWritableDatabase();//ok
                if (isDeleteNullTable) {
                    deleteUnnecessaryTable();//删除掉多余的表
                } else {
                    if (isTable("android_metadata")) {
                        deleteTable("android_metadata");
                    }
                }
                /*try {
                    sqLiteDatabase2 = databaseHelper.getWritableDatabase();//ok
                    if (isDeleteNullTable) {
                        deleteUnnecessaryTable();//删除掉多余的表
                    } else {
                        if (isTable("android_metadata")) {
                            deleteTable("android_metadata");
                        }
                    }
                } catch (RuntimeException e) {
                    errs(getLineInfo() + " 数据库报错:" + e);
                }*/
            }
            return this;
        }

        /**
         * 删除掉多余的表,如系统自己创建的表
         */
        private void deleteUnnecessaryTable() {

            //删除系统创建的多余表
            List<String> okTableNameList = new ArrayList<>();
            for (Class classz : tableList) {
                okTableNameList.add(classz.getSimpleName());
            }

            List<String> sqlAllTableName = getSqlAllTableName();
            for (String tableName : sqlAllTableName) {
                if (!okTableNameList.contains(tableName)) {//判断当前创建的表是否为系统创建的表
                    if (isTable(tableName)) {
                        deleteTable(tableName).isStatus();//删除表
                    }
                }
            }
            okTableNameList.clear();
            okTableNameList = null;
        }

        //====================================== 加载 包名扫描 SQL 注解 ======================================
        private void loadHibernateAnnotation(String EnityPackagePath, Context context) {
            DexFile dexFile = null;
            try {
                dexFile = new DexFile(context.getPackageCodePath());
            } catch (IOException e) {
                e.printStackTrace();
                isCreateTable = false;
            }
            Enumeration<String> enumeration = dexFile.entries();
            while (enumeration.hasMoreElements()) {
                String className = enumeration.nextElement();
//                GT.log("className:" + className);//com.gsls.activity.MainActivity
                //在当前所有可执行的类里面查找包含有该包名的所有类
                if (className.contains(EnityPackagePath)) {
                    Class<?> clazz1 = null;
                    try {
                        clazz1 = Class.forName(className);
                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
                    }
                    if (clazz1 == null) continue;//在扫描到第三模块时会为null

                    //过滤掉未被注解过的类
                    GT_Bean bean_Bean = clazz1.getAnnotation(GT_Bean.class);//获取该类 ContextView 的注解类
                    GT_Entity bean_Entity = clazz1.getAnnotation(GT_Entity.class);//获取该类 ContextView 的注解类

                    if (bean_Bean != null || bean_Entity != null) {

                        //分库映射(如果实体类中指定了被映射的数据库，那就根据数据库名称来判断该表是否映射)
                        String[] userSqlName = new String[0];
                        userSqlName = null;
                        if (bean_Bean != null) {
                            userSqlName = bean_Bean.setSqlNames();//获取用户指定的数据库名称
                        } else if (bean_Entity != null) {
                            userSqlName = bean_Entity.setSqlNames();//获取用户指定的数据库名称
                        }

                        if (userSqlName != null && userSqlName.length != 0) {
                            String gt_sqlName = DATABASE_NAME.substring(0, DATABASE_NAME.indexOf("."));//获取当前系统执行的数据库名称
                            //StringArray to List
                            if (!Arrays.asList(userSqlName).contains(gt_sqlName)) {
                                continue;//跳过本次添加的表
                            }
                        }
                        tableList.add(clazz1);
                    }

                }
            }
        }

        //数据库类结束
    }

    /**
     * 管理手机数据
     */
    public static class CleanDataUtils {

        /**
         * 需要查下缓存大小
         *
         * @param context
         * @return
         */
        public static String getTotalCacheSize(Context context) throws Exception {
            long cacheSize = getFolderSize(context.getCacheDir());
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                cacheSize += getFolderSize(context.getExternalCacheDir());
            }
            return getFormatSize(cacheSize);
        }

        /**
         * 清空缓存
         *
         * @param context
         */
        public static void clearAllCache(Context context) {
            deleteDir(context.getCacheDir());
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                deleteDir(context.getExternalCacheDir());
            }

        }

        /**
         * 删除 dir 文件
         *
         * @param dir
         * @return
         */
        private static boolean deleteDir(File dir) {
            if (dir != null && dir.isDirectory()) {
                String[] children = dir.list();
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }
            return dir.delete();
        }

        /**
         * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * *
         *
         * @param context
         */
        public static void cleanInternalCache(Context context) {
            deleteFilesByDirectory(context.getCacheDir());
        }

        /**
         * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * *
         *
         * @param context
         */
        public static void cleanDatabases(Context context) {
            deleteFilesByDirectory(new File("/data/data/"
                    + context.getPackageName() + "/databases"));
        }

        /**
         * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) *
         *
         * @param context
         */
        public static void cleanSharedPreference(Context context) {
            deleteFilesByDirectory(new File("/data/data/"
                    + context.getPackageName() + "/shared_prefs"));
        }

        /**
         * 按名字清除本应用数据库 * *
         *
         * @param context
         * @param dbName
         */
        public static void cleanDatabaseByName(Context context, String dbName) {
            context.deleteDatabase(dbName);
        }

        /**
         * 清除/data/data/com.xxx.xxx/files下的内容 * *
         *
         * @param context
         */
        public static void cleanFiles(Context context) {
            deleteFilesByDirectory(context.getFilesDir());
        }

        /**
         * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
         *
         * @param context
         */
        public static void cleanExternalCache(Context context) {
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                deleteFilesByDirectory(context.getExternalCacheDir());
            }
        }

        /**
         * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 * *
         *
         * @param filePath
         */
        public static void cleanCustomCache(String filePath) {
            deleteFilesByDirectory(new File(filePath));
        }

        /**
         * 清除本应用所有的数据 * *
         *
         * @param context
         * @param filepath
         */
        public static void cleanApplicationData(Context context, String... filepath) {
            cleanInternalCache(context);
            cleanExternalCache(context);
            cleanDatabases(context);
            cleanSharedPreference(context);
            cleanFiles(context);
            if (filepath == null) {
                return;
            }
            for (String filePath : filepath) {
                cleanCustomCache(filePath);
            }
        }

        /**
         * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * *
         *
         * @param directory
         */
        private static void deleteFilesByDirectory(File directory) {
            if (directory != null && directory.exists() && directory.isDirectory()) {
                for (File item : directory.listFiles()) {
                    item.delete();
                }
            }
        }

        // 获取文件
        //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
        //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
        public static long getFolderSize(File file) throws Exception {
            long size = 0;
            try {
                File[] fileList = file.listFiles();
                for (int i = 0; i < fileList.length; i++) {
                    // 如果下面还有文件
                    if (fileList[i].isDirectory()) {
                        size = size + getFolderSize(fileList[i]);
                    } else {
                        size = size + fileList[i].length();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return size;
        }

        /**
         * 删除指定目录下文件及目录
         *
         * @param deleteThisPath
         * @param filePath
         * @return
         */
        public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
            if (!TextUtils.isEmpty(filePath)) {
                try {
                    File file = new File(filePath);
                    if (file.isDirectory()) {// 如果下面还有文件
                        File files[] = file.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            deleteFolderFile(files[i].getAbsolutePath(), true);
                        }
                    }
                    if (deleteThisPath) {
                        if (!file.isDirectory()) {// 如果是文件，删除
                            file.delete();
                        } else {// 目录
                            if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                                file.delete();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 格式化单位
         *
         * @param size
         * @return
         */
        public static String getFormatSize(double size) {
            double kiloByte = size / 1024;
            //        if (kiloByte < 1) {
            //            return size + "Byte";
            //        }

            double megaByte = kiloByte / 1024;
            if (megaByte < 1) {
                BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
                return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                        .toPlainString() + "K";
            }

            double gigaByte = megaByte / 1024;
            if (gigaByte < 1) {
                BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
                return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                        .toPlainString() + "M";
            }

            double teraBytes = gigaByte / 1024;
            if (teraBytes < 1) {
                BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
                return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                        .toPlainString() + "G";
            }
            BigDecimal result4 = new BigDecimal(teraBytes);
            return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                    + "T";
        }

        public static String getCacheSize(File file) throws Exception {
            return getFormatSize(getFolderSize(file));
        }

    }

    /**
     * 随机类
     */
    public static class GT_Random {

        private final static Random random = new Random();

        /**
         * 获取一个未知整数
         *
         * @return int
         */
        public static int getInt() {
            int value = 0;
            try {
                int min = -2147483648;
                int max = 2147483647;
                value = random.nextInt(max) % (max - min + 1) + min;
            } catch (Exception e) {
                value = random.nextInt(1079);
            }
            return value;
        }

        /**
         * 获取随机范围的数
         *
         * @param min
         * @param max
         * @return
         */
        public static int getInt(int min, int max) {
            int i = random.nextInt(max) % (max - min + 2) + min;
            if (i > max) i--;
            return i;
        }

        /**
         * 获取定义字符之间的随机字符
         *
         * @param startChar
         * @param closeChar
         * @return
         */
        public static char getChar(char startChar, char closeChar) {
            return (char) (startChar + Math.random() * (closeChar - startChar + 1));
        }

        /**
         * 随机获取一个名称
         *
         * @return
         */
        public static String getName(int nameLength) {
            if (nameLength < 1)
                return null;
            String sum = "";
            for (int i = 1; i < nameLength + 1; i++) {
                String str = "";
                int hightPos; //
                int lowPos;
                hightPos = (176 + Math.abs(random.nextInt(39)));
                lowPos = (161 + Math.abs(random.nextInt(93)));
                byte[] b = new byte[2];
                b[0] = (Integer.valueOf(hightPos)).byteValue();
                b[1] = (Integer.valueOf(lowPos)).byteValue();
                try {
                    str = new String(b, "GBK");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    System.out.println("错误");
                }
                sum += str.charAt(0);
            }
            return sum;
        }

    }

    /**
     * 串口工具包
     */
    public static class SerialPortUtils {

        @Target(ElementType.FIELD)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface Build {
            String setComAll();       //默认SP名称

            int setBaudRate();

            boolean setISLog() default false;     //默认自动提交
        }


        public SerialPort serialPort;
        public SerialPortFinder serialPortFinder;
        public InputStream inputStream;
        public OutputStream outputStream;
        private int TYPE = 10;                  //默认十进制
        public static int FLAGS = 0;            //默认为 0
        private boolean isShowLog;              //是否显示串口内日志 true:显示  false:不显示
        public int defaultSpeed = 1000;         //默认速度
        public int waitingTime = defaultSpeed;  //默认等待读取数据时间 每次读取等待时间为 1 秒 注意：尽量使用默认的速率来读取数据，读取太快可能会读取数据异常
        public int readSpeed = 1000;            //读取速度
        public int readByteMax = 1024;          //读取字节的最大值
        private byte[] buffer;                  //读取数据的容器
        private boolean stopRead = false;       //停止读取标识

        /**
         * 停止读取
         *
         * @return
         */
        public SerialPortUtils stopRead() {
            stopRead = true;
            return this;
        }

        /**
         * @param context   上下文
         * @param COM_All   主板物理通讯地址
         * @param BAUD_RATE 波特率
         */
        public SerialPortUtils(Context context, String COM_All, int BAUD_RATE) {
            if (serialPortFinder == null) {
                serialPortFinder = new SerialPortFinder();
            }
            try {
                serialPort = new SerialPort(new File(COM_All), BAUD_RATE, FLAGS);
                inputStream = serialPort.getInputStream();
                outputStream = serialPort.getOutputStream();
                if (isShowLog) {
                    log(COM_All + "——打开串口成功!");
                    Toast.makeText(context, COM_All + "——打开串口成功!", Toast.LENGTH_LONG).show();
                }

                try {
                    HardwareInit.InitSerialPort(COM_All, BAUD_RATE);//初始化插件校准数据
                } catch (Exception e) {
                    log("初始化插件失败！");
                }

                buffer = new byte[readByteMax]; //读取数据的容器

            } catch (IOException e) {
                if (isShowLog) {
                    log(COM_All + "打开串口失败!");
                    Toast.makeText(context, COM_All + "打开串口失败!", Toast.LENGTH_LONG).show();
                }
            }
        }

        /**
         * @param context     上下文
         * @param COM_All     主板物理通讯地址
         * @param BAUD_RATE   波特率
         * @param readByteMax 读取Byte最大值
         */
        public SerialPortUtils(Context context, String COM_All, int BAUD_RATE, int readByteMax) {
            if (serialPortFinder == null) {
                serialPortFinder = new SerialPortFinder();
            }
            try {
                serialPort = new SerialPort(new File(COM_All), BAUD_RATE, FLAGS);
                inputStream = serialPort.getInputStream();
                outputStream = serialPort.getOutputStream();
                if (isShowLog) {
                    log(COM_All + "——打开串口成功!");
                    Toast.makeText(context, COM_All + "——打开串口成功!", Toast.LENGTH_LONG).show();
                }

                try {
                    HardwareInit.InitSerialPort(COM_All, BAUD_RATE);//初始化插件校准数据
                } catch (Exception e) {
                    log("初始化插件失败！");
                }

                buffer = new byte[readByteMax]; //读取数据的容器

            } catch (IOException e) {
                if (isShowLog) {
                    log(COM_All + "打开串口失败!");
                    if (context != null) {
                        Toast.makeText(context, COM_All + "打开串口失败!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }

        /**
         * @param context   上下文
         * @param COM_All   主板物理通讯地址
         * @param BAUD_RATE 波特率
         * @param isShowLog 是否显示内部日志
         */
        public SerialPortUtils(Context context, String COM_All, int BAUD_RATE, boolean isShowLog) {
            this.isShowLog = isShowLog;
            if (serialPortFinder == null) {
                serialPortFinder = new SerialPortFinder();
            }
            try {
                serialPort = new SerialPort(new File(COM_All), BAUD_RATE, FLAGS);
                inputStream = serialPort.getInputStream();
                outputStream = serialPort.getOutputStream();

                try {
                    HardwareInit.InitSerialPort(COM_All, BAUD_RATE);//初始化插件校准数据
                } catch (Exception e) {
                    log("初始化插件失败！");
                }

                if (isShowLog) {
                    log(COM_All + "——打开串口成功!");
                    if (context != null) {
                        Toast.makeText(context, COM_All + "——打开串口成功!", Toast.LENGTH_LONG).show();
                    }
                }

                buffer = new byte[readByteMax]; //读取数据的容器

            } catch (IOException e) {
                if (isShowLog) {
                    log(COM_All + "打开串口失败!");
                    Toast.makeText(context, COM_All + "打开串口失败!", Toast.LENGTH_LONG).show();
                }
            }
        }

        /**
         * @param context     上下文
         * @param COM_All     主板物理通讯地址
         * @param BAUD_RATE   波特率
         * @param readByteMax 读取Byte最大值
         */
        public SerialPortUtils(Context context, String COM_All, int BAUD_RATE, int readByteMax, boolean isShowLog) {
            this.isShowLog = isShowLog;
            if (serialPortFinder == null) {
                serialPortFinder = new SerialPortFinder();
            }
            try {
                serialPort = new SerialPort(new File(COM_All), BAUD_RATE, FLAGS);
                inputStream = serialPort.getInputStream();
                outputStream = serialPort.getOutputStream();

                try {
                    HardwareInit.InitSerialPort(COM_All, BAUD_RATE);//初始化插件校准数据
                } catch (Exception e) {
                    log("初始化插件失败！");
                }

                if (isShowLog) {
                    log(COM_All + "——打开串口成功!");
                    if (context != null) {
                        Toast.makeText(context, COM_All + "——打开串口成功!", Toast.LENGTH_LONG).show();
                    }
                }

                buffer = new byte[readByteMax]; //读取数据的容器

            } catch (IOException e) {
                if (isShowLog) {
                    log(COM_All + "打开串口失败!");
                    Toast.makeText(context, COM_All + "打开串口失败!", Toast.LENGTH_LONG).show();
                }
            }
        }


        /***************************************************** 其他功能性方法 *****************************************************/

        public SerialPortUtils setWaitingTimeAndReadSpeed(int waitingTime, int readSpeed) {
            this.defaultSpeed = waitingTime;
            this.readSpeed = readSpeed;
            return this;
        }

        public int getReadSpeed() {
            return readSpeed;
        }

        /**
         * 设置读取数据期间的速度
         *
         * @param readSpeed 设置读取到数据后的读取数据速度
         */
        public SerialPortUtils setReadSpeed(int readSpeed) {
            this.readSpeed = readSpeed;
            return this;
        }

        public int getWaitingTime() {
            return waitingTime;
        }

        /**
         * 设置等待下位机发送数据的等待间隔时间
         *
         * @param waitingTime
         */
        public SerialPortUtils setWaitingTime(int waitingTime) {
            this.defaultSpeed = waitingTime;
            return this;
        }

        public int getTYPE() {
            return TYPE;
        }

        public SerialPortUtils setTYPE(int TYPE) {
            this.TYPE = TYPE;
            return this;
        }

        //恢复默认的十进制
        private void closeParameter() {
            TYPE = 10;
        }

        /***************************************************** 读取下位机数据的原始方法 *****************************************************/

        /**
         * 按照次数读取数据
         *
         * @param count 本次读取多少次又有数据
         * @return
         */
        public byte[] readByteDataCount(int count) {
            stopRead = false;
            if (inputStream == null || count <= 0) {
                if (isShowLog)
                    log("读取数据失败！");
                return null;
            }
            //读取次数的
            List<byte[]> byteList = new ArrayList<>();
            int arrayMax = 0;

            while (true) {
                if (count <= 0) {
                    break;
                }

                //停止读取
                if (stopRead) {
                    return null;
                }

                try {
                    int size = inputStream.read(buffer);
                    if (isShowLog)
                        log("size:" + size);
                    if (size > 0) {
                        waitingTime = readSpeed;//加速
                        count--;
                        arrayMax += size;
                        byte[] readBytes = new byte[size];
                        System.arraycopy(buffer, 0, readBytes, 0, size);
                        byteList.add(readBytes);
                    } else {
                        waitingTime = defaultSpeed;//恢复默认速度
                    }
                } catch (IOException e) {
                    if (isShowLog)
                        log("读取失败:" + e);
                    break;
                }
                sleep(waitingTime);
            }

            //将所有的片段都组合在一起
            byte[] bufferAll = new byte[arrayMax];//最大容器
            int allIndex = 0;//最大容器的索引
            for (int i = 0; i < byteList.size(); i++) {
                byte[] bytes = byteList.get(i);
                for (int j = 0; j < bytes.length; j++) {
                    bufferAll[allIndex++] = bytes[j];
                }
            }
            return bufferAll;
        }

        /**
         * 按照时间读取数据
         *
         * @param time 读取多少秒的数据
         * @return
         */
        public byte[] readByteDataTime(int time) {

            stopRead = false;

            if (inputStream == null || time <= 0) {
                if (isShowLog)
                    log("读取数据失败！");
                return null;
            }
            //读取一个时间段的
            List<byte[]> byteList = new ArrayList<>();
            int arrayMax = 0;

            while (true) {
                if (time <= 0) {
                    break;
                }

                //停止读取
                if (stopRead) {
                    return null;
                }

                try {
                    int size = inputStream.read(buffer);
                    if (isShowLog)
                        log("size:" + size);
                    if (size > 0) {
                        waitingTime = readSpeed;
                        arrayMax += size;
                        byte[] readBytes = new byte[size];
                        System.arraycopy(buffer, 0, readBytes, 0, size);
                        byteList.add(readBytes);
                    } else {
                        waitingTime = defaultSpeed;
                    }
                } catch (IOException e) {
                    if (isShowLog)
                        log("读取失败:" + e);
                    break;
                }
                time--;
                sleep(waitingTime);
            }

            //将所有的片段都组合在一起
            byte[] bufferAll = new byte[arrayMax];//最大容器
            int allIndex = 0;//最大容器的索引
            for (int i = 0; i < byteList.size(); i++) {
                byte[] bytes = byteList.get(i);
                for (int j = 0; j < bytes.length; j++) {
                    bufferAll[allIndex++] = bytes[j];
                }
            }
            return bufferAll;
        }

        /**
         * 按照在一定时间内读取的次数来读取数据
         *
         * @param time 读取多少秒的数据
         * @return
         */
        public byte[] readByteDataTimeAddCount(int time, int count) {

            stopRead = false;

            if (inputStream == null || time <= 0 || count <= 0) {
                if (isShowLog)
                    log("读取数据失败！");
                return null;
            }
            //读取一个时间段的
            List<byte[]> byteList = new ArrayList<>();
            int arrayMax = 0;

            while (true) {
                if (time <= 0 || count <= 0) {
                    break;
                }

                //停止读取
                if (stopRead) {
                    return null;
                }

                try {
                    int size = inputStream.read(buffer);
                    if (isShowLog)
                        log("size:" + size);
                    if (size > 0) {
                        waitingTime = readSpeed;//加速
                        count--;
                        arrayMax += size;
                        byte[] readBytes = new byte[size];
                        System.arraycopy(buffer, 0, readBytes, 0, size);
                        byteList.add(readBytes);
                    } else {
                        waitingTime = defaultSpeed;//恢复默认速度
                    }
                } catch (IOException e) {
                    if (isShowLog)
                        log("读取失败:" + e);
                    break;
                }
                time--;
                sleep(waitingTime);
            }

            //将所有的片段都组合在一起
            byte[] bufferAll = new byte[arrayMax];//最大容器
            int allIndex = 0;//最大容器的索引
            for (int i = 0; i < byteList.size(); i++) {
                byte[] bytes = byteList.get(i);
                for (int j = 0; j < bytes.length; j++) {
                    bufferAll[allIndex++] = bytes[j];
                }
            }
            return bufferAll;
        }

        /**
         * 按照起始符与结束符开始读取数据
         *
         * @param start 开始读取的标识
         * @param close 结束读取的标识
         * @return
         */
        public byte[] readByteDataStartAddClose(String start, String close) {

            stopRead = false;

            if (inputStream == null || start == null || close == null) {
                if (isShowLog)
                    log("读取数据失败！");
                return null;
            }
            //读取一个区域间的
            List<byte[]> byteList = new ArrayList<>();//总数据
            int arrayMax = 0;//总数据索引
            boolean isRead = false;//是否开始读取数据

            //读取本次下位机发送的所有数据
            while (true) {

                //停止读取
                if (stopRead) {
                    return null;
                }

                try {
                    int size = inputStream.read(buffer);
                    if (isShowLog)
                        log("size:" + size);
                    if (size > 0) {
                        waitingTime = readSpeed;//加速
                        byte[] readBytes = new byte[size];
                        arrayMax += size;
                        System.arraycopy(buffer, 0, readBytes, 0, size);
                        byteList.add(readBytes);
                        isRead = true;
                    } else {
                        waitingTime = defaultSpeed;//恢复默认速度
                    }
                    if (isRead && size == -1) {//如果等于 true 表示开始读取数据
                        break;//结束本次读取
                    }
                } catch (IOException e) {
                    if (isShowLog)
                        log("读取失败:" + e);
                    break;
                }

                sleep(waitingTime);
            }

            //将所有的片段都组合在一起
            byte[] bufferAll = new byte[arrayMax];//最大容器
            int allIndex = 0;//最大容器的索引
            for (int i = 0; i < byteList.size(); i++) {
                byte[] bytes = byteList.get(i);
                for (int j = 0; j < bytes.length; j++) {
                    bufferAll[allIndex++] = bytes[j];
                }
            }

            //遍历截取起始符和结束符的数据
            String sumData = "";
            String startData = "";
            String closeData = "";

            //总数据
            for (byte str : bufferAll) {
                sumData += str + "-GT-";
            }
            //开始符数据
            for (byte str : start.getBytes()) {
                startData += str + "-GT-";
            }
            //结束符数据
            for (byte str : close.getBytes()) {
                closeData += str + "-GT-";
            }
            //截取需要的数据
            sumData = sumData.substring(sumData.indexOf(startData) + startData.length(), sumData.indexOf(closeData));

            String[] split = sumData.split("-GT-");
            bufferAll = new byte[split.length];//最大容器
            for (int i = 0; i < bufferAll.length; i++) {
                bufferAll[i] = Byte.parseByte(split[i]);
            }

            return bufferAll;
        }

        /**
         * 按照本次读取的所有数据
         *
         * @return
         */
        public byte[] readByteDataAll() {

            stopRead = false;

            if (inputStream == null) {
                if (isShowLog)
                    log("读取数据失败！");
                return null;
            }
            //读取一个区域间的
            List<byte[]> byteList = new ArrayList<>();//总数据
            int arrayMax = 0;//总数据索引
            boolean isRead = false;//是否开始读取数据

            //读取本次下位机发送的所有数据
            while (true) {

                //停止读取
                if (stopRead) {
                    return null;
                }

                try {
                    int size = inputStream.read(buffer);
                    if (isShowLog)
                        log("size:" + size);
                    if (size > 0) {
                        waitingTime = readSpeed;//加速
                        byte[] readBytes = new byte[size];
                        arrayMax += size;
                        System.arraycopy(buffer, 0, readBytes, 0, size);
                        byteList.add(readBytes);
                        isRead = true;
                    } else {
                        waitingTime = defaultSpeed;//恢复默认速度
                    }
                    if (isRead && size == -1) {//如果等于 true 表示开始读取数据
                        break;//结束本次读取
                    }
                } catch (IOException e) {
//                e.printStackTrace();
                    if (isShowLog)
                        log("读取失败:" + e);
                    break;
                }

                sleep(waitingTime);
            }

            //将所有的片段都组合在一起
            byte[] bufferAll = new byte[arrayMax];//最大容器
            int allIndex = 0;//最大容器的索引
            for (int i = 0; i < byteList.size(); i++) {
                byte[] bytes = byteList.get(i);
                for (int j = 0; j < bytes.length; j++) {
                    bufferAll[allIndex++] = bytes[j];
                }
            }

            return bufferAll;
        }


        /***************************************************** 读取数据方法 *****************************************************/

        /**
         * 按照次数读取数据
         *
         * @param count 读取数据的次数
         * @return
         */
        public String readDataCount(int count) {
            byte[] bytes = readByteDataCount(count);
            if (bytes != null) {
                return returnTypeData(bytes);
            }
            return null;
        }

        /**
         * 按时间读取数据
         *
         * @param time 读取数据的时间
         * @return
         */
        public String readDataTime(int time) {
            byte[] bytes = readByteDataTime(time);
            if (bytes != null) {
                return returnTypeData(bytes);
            }
            return null;
        }

        /**
         * 按照在一定时间内读取的次数来读取数据
         *
         * @param time  读取数据的时间
         * @param count 在规定的时间内读取数据的次数
         * @return
         */
        public String readDataTimeAddCount(int time, int count) {
            byte[] bytes = readByteDataTimeAddCount(time, count);
            if (bytes != null) {
                return returnTypeData(bytes);
            }
            return null;
        }

        /**
         * 按照起始符与结束符读取数据
         *
         * @param start 开始读取数据的开始符号
         * @param close 结束读取数据的结束符号
         * @return
         */
        public String readDataStartAddClose(String start, String close) {
            byte[] bytes = readByteDataStartAddClose(start, close);
            if (bytes != null) {
                return returnTypeData(bytes);
            }
            return null;
        }

        /**
         * 读取本次所有数据
         *
         * @return
         */
        public String readDataAll() {
            byte[] bytes = readByteDataAll();
            if (bytes != null) {
                return returnTypeData(bytes);
            }
            return null;

        }

        /**
         * 读取曲线数据
         * 格式：,T:0.000,C:-3.620;90,91,92,91,91,91,91,91,91,90,90,90,91,91,90,90,92,91
         *
         * @param spValue 检测值与曲线值的分隔符
         * @return
         */
        public String readCurveData(String spValue) {
            if (spValue == null) {
                if (isShowLog)
                    log("读取数据失败！");
                return null;
            }
            byte[] bytes = readByteDataAll();//读取本次所有下位机发送的数据
            if (bytes != null) {
                //确定前后区数组大小
                byte[] startByte = null;
                byte[] closeByte = null;
                for (int i = 0; i < bytes.length; i++) {
                    byte aByte = bytes[i];
                    if (aByte == spValue.getBytes()[0]) {
                        startByte = new byte[i + 1];
                        closeByte = new byte[bytes.length - startByte.length];
                        break;
                    }
                }
                String startStr = null, closeStr = null;
                try {
                    //处理分割区前的数据
                    System.arraycopy(bytes, 0, startByte, 0, startByte.length);
                    startStr = returnTypeData(startByte);
                    //处理分割区后的数据
                    System.arraycopy(bytes, startByte.length, closeByte, 0, closeByte.length);
                    closeStr = setTYPE(16).returnTypeData(closeByte);
                } catch (Exception e) {//NullPointerException
                    log("串口读取异常:" + e);
                }

                return startStr + closeStr;
            }
            return null;
        }

        /***************************************************** 发送数据方法 *****************************************************/

        /**
         * 发送数据
         * 比如：Charset.forName("GB2312")
         *
         * @param sendData 发送数据的内容
         * @param charset  发送数据的格式
         */
        public SerialPortUtils sendData(final String sendData, Charset charset) {
            if (outputStream == null || sendData == null) {
                if (isShowLog)
                    log("发送数据失败！");
                return this;
            }

            if (isShowLog)
                log("发送给下位机的数据:" + sendData);

            //设置发送数据的格式
            byte[] bytes;
            if (charset == null) {
                bytes = sendData.getBytes();
            } else {
                bytes = sendData.getBytes(charset);//发给打印机的
            }

            try {
                outputStream.write(bytes);
                outputStream.flush();
                if (isShowLog)
                    log("发送数据成功！");
            } catch (IOException e) {
                e.printStackTrace();
                if (isShowLog)
                    log("发送数据失败！");
            }
            return this;
        }

        /**
         * 发送数据
         *
         * @param sendData
         * @return
         */
        public SerialPortUtils sendData(final String sendData) {
            if (outputStream == null || sendData == null) {
                if (isShowLog)
                    log("发送数据失败！");
                return this;
            }

            if (isShowLog)
                log("发送给下位机的数据:" + sendData);

            //设置发送数据的格式
            byte[] bytes = sendData.getBytes();
            try {
                outputStream.write(bytes);
                outputStream.flush();
                if (isShowLog)
                    log("发送数据成功！");
            } catch (IOException e) {
                e.printStackTrace();
                if (isShowLog)
                    log("发送数据失败！");
            }
            return this;
        }


        /***************************************************** 数据进制转换方法 *****************************************************/

        /**
         * 返回转换好的进制数据
         *
         * @param bytes 需要转换进制的数据
         * @return
         */
        public String returnTypeData(byte[] bytes) {
            String readData = "";
            switch (TYPE) {
                case 10:
                    readData = new String(bytes, Charset.forName("gbk"));
                    break;
                case 16:
                    int[] ints = DataUtils.byte2Int(bytes);
                    for (int value : ints) {
                        readData += value + ",";
                    }
                    break;
            }
            closeParameter();
            return readData;
        }

        /**
         * 释放资源
         */
        public void close() {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (serialPort != null) {
                serialPort.close();
                serialPort = null;
            }

            if (serialPortFinder != null) {
                serialPortFinder = null;
            }

        }

        /**
         * 日志
         *
         * @param msg
         */
        private static void log(Object msg) {
            Log.i("GT_i", msg.toString());
            DataSendReception.sendUpdateUiBroadcast(SerialPortUtils.class.getName(), msg.toString());
        }


        /**
         * 睡眠
         *
         * @param millis
         */
        public static void sleep(long millis) {
            try {
                java.lang.Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 语言框架
     */
    public static class LanguageUtils {

        private static Context context;

        @SuppressLint("NewApi")
        public static void updateLanguage(Locale locale) {
            //获取想要切换的语言类型
            Resources resources = context.getResources();//获得res资源对象
            Configuration configuration = resources.getConfiguration();//获得设置对象
            configuration.setLocale(locale);//设置语言
            DisplayMetrics dm = resources.getDisplayMetrics();//获得屏幕参数：主要是分辨率，像素等。
            resources.updateConfiguration(configuration, dm);//更新语言
        }


        public static String getString(int stringID) {
            return context.getResources().getString(stringID);
        }

/**

 国家/地区	语言代码	国家/地区	语言代码
 简体中文(中国)	zh-rCN	繁体中文(台湾地区)	zh-rTW
 繁体中文(香港)	zh-rHK	英语(香港)	en-rHK
 英语(美国)	en-rUS	英语(英国)	en-rQB
 英语(全球)	en-rWW	英语(加拿大)	en-rCA
 英语(澳大利亚)	en-rAU	英语(爱尔兰)	en-rIE
 英语(芬兰)	en-rFI	芬兰语(芬兰)	fi-rFI
 英语(丹麦)	en-rDK	丹麦语(丹麦)	da-rDK
 英语(以色列)	en-rIL	希伯来语(以色列)	he-rIL
 英语(南非)	en-rZA	英语(印度)	en-rIN
 英语(挪威)	en-rNO	英语(新加坡)	en-rSG
 英语(新西兰)	en-rNZ	英语(印度尼西亚)	en-rID
 英语(菲律宾)	en-rPH	英语(泰国)	en-rTH
 英语(马来西亚)	en-rMY	英语(阿拉伯)	en-rXA
 韩文(韩国)	ko-rKR	日语(日本)	ja-rJP
 荷兰语(荷兰)	nl-rNL	荷兰语(比利时)	nl-rBE
 葡萄牙语(葡萄牙)	pt-rPT	葡萄牙语(巴西)	pt-rBR
 法语(法国)	fr-rFR	法语(卢森堡)	fr-rLU
 法语(瑞士)	fr-rCH	法语(比利时)	fr-rBE
 法语(加拿大)	fr-rCA	西班牙语(拉丁美洲)	es-rLA
 西班牙语(西班牙)	es-rES	西班牙语(阿根廷)	es-rAR
 西班牙语(美国)	es-rUS	西班牙语(墨西哥)	es-rMX
 西班牙语(哥伦比亚)	es-rCO	西班牙语(波多黎各)	es-rPR
 德语(德国)	de-rDE	德语(奥地利)	de-rAT
 德语(瑞士)	de-rCH	俄语(俄罗斯)	ru-rRU
 意大利语(意大利)	it-rIT	希腊语(希腊)	el-rGR
 挪威语(挪威)	no-rNO	匈牙利语(匈牙利)	hu-rHU
 土耳其语(土耳其)	tr-rTR	捷克语(捷克共和国)	cs-rCZ
 斯洛文尼亚语	sl-rSL	波兰语(波兰)	pl-rPl
 瑞典语(瑞典)	sv-rSE	西班牙语 (智利)	es-rCL 1
 **/

    }

    /***************************************************** 数据转换工具类 *****************************************************/
    public static class DataUtils {

        /**
         * 16进制 byte 转10进制 int
         *
         * @param data
         * @return
         */
        public static int[] byte2Int(byte[] data) {
            int[] result = new int[data.length / 2];
            int value;
            int extra = 0;
            if (data.length % 2 != 0) {
                extra = 2;
            }
            for (int i = 0; i < data.length - extra; i += 2) {
                int int1 = (data[i] & 0xff) << 8;
                value = int1 + (data[i + 1] & 0xff);
                result[i / 2] = value;
            }
            return result;
        }

        // 判断奇数或偶数，位运算，最后一位是1则为奇数，为0是偶数
        public static int isOdd(int num) {
            return num & 1;
        }

        //Hex字符串转int
        public static int HexToInt(String inHex) {
            return Integer.parseInt(inHex, 16);
        }

        public static String IntToHex(int intHex) {
            return Integer.toHexString(intHex);
        }

        //Hex字符串转byte
        public static byte HexToByte(String inHex) {
            return (byte) Integer.parseInt(inHex, 16);
        }

        //1字节转2个Hex字符
        public static String Byte2Hex(Byte inByte) {
            return String.format("%02x", new Object[]{inByte}).toUpperCase();
        }

        //字节数组转转hex字符串
        public static String ByteArrToHex(byte[] inBytArr) {
            StringBuilder strBuilder = new StringBuilder();
            for (byte valueOf : inBytArr) {
                strBuilder.append(Byte2Hex(Byte.valueOf(valueOf)));
                strBuilder.append(" ");
            }
            return strBuilder.toString();
        }

        //字节数组转转hex字符串，可选长度
        public static String ByteArrToHex(byte[] inBytArr, int offset, int byteCount) {
            StringBuilder strBuilder = new StringBuilder();
            int j = byteCount;
            for (int i = offset; i < j; i++) {
                strBuilder.append(Byte2Hex(Byte.valueOf(inBytArr[i])));
            }
            return strBuilder.toString();
        }

        //转hex字符串转字节数组
        public static byte[] HexToByteArr(String inHex) {
            byte[] result;
            int hexlen = inHex.length();
            if (isOdd(hexlen) == 1) {
                hexlen++;
                result = new byte[(hexlen / 2)];
                inHex = "0" + inHex;
            } else {
                result = new byte[(hexlen / 2)];
            }
            int j = 0;
            for (int i = 0; i < hexlen; i += 2) {
                result[j] = HexToByte(inHex.substring(i, i + 2));
                j++;
            }
            return result;
        }

        /**
         * 按照指定长度切割字符串
         *
         * @param inputString 需要切割的源字符串
         * @param length      指定的长度
         * @return
         */
        public static List<String> getDivLines(String inputString, int length) {
            List<String> divList = new ArrayList<>();
            int remainder = (inputString.length()) % length;
            // 一共要分割成几段
            int number = (int) Math.floor((inputString.length()) / length);
            for (int index = 0; index < number; index++) {
                String childStr = inputString.substring(index * length, (index + 1) * length);
                divList.add(childStr);
            }
            if (remainder > 0) {
                String cStr = inputString.substring(number * length, inputString.length());
                divList.add(cStr);
            }
            return divList;
        }

        /**
         * 计算长度，两个字节长度
         *
         * @param val value
         * @return 结果
         */
        public static String twoByte(String val) {
            if (val.length() > 4) {
                val = val.substring(0, 4);
            } else {
                int l = 4 - val.length();
                for (int i = 0; i < l; i++) {
                    val = "0" + val;
                }
            }
            return val;
        }

        /**
         * 校验和
         *
         * @param cmd 指令
         * @return 结果
         */
        public static String sum(String cmd) {
            List<String> cmdList = getDivLines(cmd, 2);
            int sumInt = 0;
            for (String c : cmdList) {
                sumInt += HexToInt(c);
            }
            String sum = IntToHex(sumInt);
            sum = twoByte(sum);
            cmd += sum;
            return cmd.toUpperCase();
        }


    }

//=========================================== 网络类 =========================================

    /**
     * network 网络类
     */
    public static class Network {

        /**
         * 监听网络状态 true 网络正常  false 网络异常
         *
         * @param context 上下文
         * @return boolean  true 为当前网络正常    false 则反之
         */
        @SuppressLint("MissingPermission")
        public static boolean netWorkStatus(Context context) {
            ConnectivityManager cwjManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cwjManager.getActiveNetworkInfo() != null) {
                return cwjManager.getActiveNetworkInfo().isAvailable();
            }
            return false;
        }

        /**
         * 获取手机 IP 地址
         *
         * @param context 上下文
         * @return String   返回当前 ip 地址
         */
        public static String getIPAddress(Context context) {
            @SuppressLint("MissingPermission") NetworkInfo info = ((ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                    try {
                        //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                            NetworkInterface intf = en.nextElement();
                            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                                InetAddress inetAddress = enumIpAddr.nextElement();
                                if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                    return inetAddress.getHostAddress();
                                }
                            }
                        }
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }


                } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                    @SuppressLint("MissingPermission") WifiInfo wifiInfo = wifiManager.getConnectionInfo();

                    int ipAddress1 = wifiInfo.getIpAddress();
                    String ipAddress = (ipAddress1 & 0xFF) + "." +
                            ((ipAddress1 >> 8) & 0xFF) + "." +
                            ((ipAddress1 >> 16) & 0xFF) + "." +
                            (ipAddress1 >> 24 & 0xFF);
                    return ipAddress;
                }
            } else {
                //当前无网络连接,请在设置中打开网络
                GT.toast_s("当前无网络");
            }
            return null;
        }

        //检测当前手机是否可上网
        public static boolean isInternet(Context context) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
            @SuppressLint("MissingPermission") NetworkInfo info = manager.getActiveNetworkInfo();// 检查网络连接，如果无网络可用，就不需要进行连网操作等
            if (info == null || !manager.getBackgroundDataSetting()) {
                return false;
            }
            return true;
        }

        /**
         * 获取当前网络属于 无网络(返回0)、WF(返回1)、2G(返回2)、3G(返回3)、4G(返回4) 网络
         *
         * @param context
         * @return
         */
        public static int getNetworkState(Context context) {
            return IntenetUtil.getNetworkState(context);
        }

        /**
         * 获取当前网络属于 无网络、WF、2G、3G、4G网络
         * //没有网络连接
         * public static final int NETWORN_NONE = 0;
         * //wifi连接
         * public static final int NETWORN_WIFI = 1;
         * //手机网络数据连接类型
         * public static final int NETWORN_2G = 2;
         * public static final int NETWORN_3G = 3;
         * public static final int NETWORN_4G = 4;
         * public static final int NETWORN_MOBILE = 5;
         */
        private static class IntenetUtil {

            //没有网络连接
            public static final int NETWORN_NONE = 0;
            //wifi连接
            public static final int NETWORN_WIFI = 1;
            //手机网络数据连接类型
            public static final int NETWORN_2G = 2;
            public static final int NETWORN_3G = 3;
            public static final int NETWORN_4G = 4;
            public static final int NETWORN_MOBILE = 5;

            /**
             * 获取当前网络连接类型
             *
             * @param context
             * @return
             */
            public static int getNetworkState(Context context) {
                //获取系统的网络服务
                ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                //如果当前没有网络
                if (null == connManager)
                    return NETWORN_NONE;
                //获取当前网络类型，如果为空，返回无网络
                @SuppressLint("MissingPermission") NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
                if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
                    return NETWORN_NONE;
                }
                // 判断是不是连接的是不是wifi
                @SuppressLint("MissingPermission") NetworkInfo wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (null != wifiInfo) {
                    NetworkInfo.State state = wifiInfo.getState();
                    if (null != state)
                        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                            return NETWORN_WIFI;
                        }
                }
                // 如果不是wifi，则判断当前连接的是运营商的哪种网络2g、3g、4g等
                @SuppressLint("MissingPermission") NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (null != networkInfo) {
                    NetworkInfo.State state = networkInfo.getState();
                    String strSubTypeName = networkInfo.getSubtypeName();
                    if (null != state)
                        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                            switch (activeNetInfo.getSubtype()) {
                                //如果是2g类型
                                case TelephonyManager.NETWORK_TYPE_GPRS: // 联通2g
                                case TelephonyManager.NETWORK_TYPE_CDMA: // 电信2g
                                case TelephonyManager.NETWORK_TYPE_EDGE: // 移动2g
                                case TelephonyManager.NETWORK_TYPE_1xRTT:
                                case TelephonyManager.NETWORK_TYPE_IDEN:
                                    return NETWORN_2G;
                                //如果是3g类型
                                case TelephonyManager.NETWORK_TYPE_EVDO_A: // 电信3g
                                case TelephonyManager.NETWORK_TYPE_UMTS:
                                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                                case TelephonyManager.NETWORK_TYPE_HSDPA:
                                case TelephonyManager.NETWORK_TYPE_HSUPA:
                                case TelephonyManager.NETWORK_TYPE_HSPA:
                                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                                case TelephonyManager.NETWORK_TYPE_EHRPD:
                                case TelephonyManager.NETWORK_TYPE_HSPAP:
                                    return NETWORN_3G;
                                //如果是4g类型
                                case TelephonyManager.NETWORK_TYPE_LTE:
                                    return NETWORN_4G;
                                default:
                                    //中国移动 联通 电信 三种3G制式
                                    if (strSubTypeName.equalsIgnoreCase("TD-SCDMA") || strSubTypeName.equalsIgnoreCase("WCDMA") || strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                                        return NETWORN_3G;
                                    } else {
                                        return NETWORN_MOBILE;
                                    }
                            }
                        }
                }
                return NETWORN_NONE;
            }
        }

    }

    /**
     * JSON 接口解析 json 与 Bean 互转
     * TODO 装换实体类需要用规则的创建，构造、set、get、toString、都有才行
     */
    public static class JSON {

        /**
         * 将对象转换成Json字符串
         * TODO 装换实体类需要用规则的创建，构造、set、get、toString、都有才行
         *
         * @param obj
         * @return json类型字符串
         */
        public static String toJson(Object obj) {
            JSONStringer js = new JSONStringer();
            serialize(js, obj);
            return js.toString();
        }

        /**
         * 序列化为JSON
         *
         * @param js json对象
         * @param o  待需序列化的对象
         */
        private static void serialize(JSONStringer js, Object o) {
            if (isNull(o)) {
                try {
                    js.value(null);
                } catch (JSONException e) {
                    //空异常
                }
                return;
            }

            Class<?> clazz = o.getClass();
            if (isObject(clazz)) { // 对象
                serializeObject(js, o);
            } else if (isArray(clazz)) { // 数组
                serializeArray(js, o);
            } else if (isCollection(clazz)) { // 集合
                Collection<?> collection = (Collection<?>) o;
                serializeCollect(js, collection);
            } else if (isMap(clazz)) { // Map
                HashMap<?, ?> collection = (HashMap<?, ?>) o;
                serializeMap(js, collection);
            } else { // 单个值
                try {
                    js.value(o);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 序列化数组
         *
         * @param js    json对象
         * @param array 数组
         */
        private static void serializeArray(JSONStringer js, Object array) {
            try {
                js.array();
                for (int i = 0; i < Array.getLength(array); ++i) {
                    Object o = Array.get(array, i);
                    serialize(js, o);
                }
                js.endArray();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 序列化集合
         *
         * @param js         json对象
         * @param collection 集合
         */
        private static void serializeCollect(JSONStringer js, Collection<?> collection) {
            try {
                js.array();
                for (Object o : collection) {
                    serialize(js, o);
                }
                js.endArray();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 序列化Map
         *
         * @param js  json对象
         * @param map map对象
         */
        private static void serializeMap(JSONStringer js, Map<?, ?> map) {
            try {
                js.object();
                Map<?, Object> valueMap = (Map<?, Object>) map;
                Iterator<? extends Map.Entry<?, Object>> it = valueMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<?, Object> entry = (Map.Entry<?, Object>) it.next();
                    js.key(entry.getKey().toString());
                    serialize(js, entry.getValue());
                }
                js.endObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 序列化对象
         *
         * @param js  json对象
         * @param obj 待序列化对象
         */
        private static void serializeObject(JSONStringer js, Object obj) {
            try {
                js.object();
                Class<? extends Object> objClazz = obj.getClass();
                //获取所有的数组
                Method[] methods = objClazz.getDeclaredMethods();
                //获取所有的字段
                Field[] fields = objClazz.getDeclaredFields();
                //遍历这个对象
                for (Field field : fields) {
                    try {
                        String fieldType = field.getType().getSimpleName();
                        String fieldGetName = parseMethodName(field.getName(), "get");
                        if (!haveMethod(methods, fieldGetName)) {
                            continue;
                        }
                        Method fieldGetMet = objClazz.getMethod(fieldGetName, new Class[]{});
                        Object fieldVal = fieldGetMet.invoke(obj, new Object[]{});
                        Object result = null;
                        if ("Date".equals(fieldType)) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                            result = sdf.format((Date) fieldVal);

                        } else {
                            if (null != fieldVal) {
                                result = fieldVal;
                            }
                        }
                        js.key(field.getName());
                        serialize(js, result);
                    } catch (Exception e) {
                        continue;
                    }
                }
                js.endObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 判断是否存在某属性的 get方法
         *
         * @param methods     引用方法的数组
         * @param fieldMethod 方法名称
         * @return true或者false
         */
        public static boolean haveMethod(Method[] methods, String fieldMethod) {
            for (Method met : methods) {
                if (fieldMethod.equals(met.getName())) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 拼接某属性的 get或者set方法
         *
         * @param fieldName  字段名称
         * @param methodType 方法类型
         * @return 方法名称
         */
        public static String parseMethodName(String fieldName, String methodType) {
            if (null == fieldName || "".equals(fieldName)) {
                return null;
            }
            return methodType + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }

        /**
         * 给字段赋值
         *
         * @param obj    实例对象
         * @param valMap 值集合
         */
        public static void setFieldValue(Object obj, Map<String, String> valMap) {
            Class<?> cls = obj.getClass();
            // 取出bean里的所有方法
            Method[] methods = cls.getDeclaredMethods();
            Field[] fields = cls.getDeclaredFields();

            for (Field field : fields) {
                try {
                    String setMetodName = parseMethodName(field.getName(), "set");
                    if (!haveMethod(methods, setMetodName)) {
                        continue;
                    }
                    Method fieldMethod = cls.getMethod(setMetodName, field
                            .getType());
                    String value = valMap.get(field.getName());
                    if (null != value && !"".equals(value)) {
                        String fieldType = field.getType().getSimpleName();
                        if ("String".equals(fieldType)) {
                            fieldMethod.invoke(obj, value);
                        } else if ("Date".equals(fieldType)) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                            Date temp = sdf.parse(value);
                            fieldMethod.invoke(obj, temp);
                        } else if ("Integer".equals(fieldType)
                                || "int".equals(fieldType)) {
                            Integer intval = Integer.parseInt(value);
                            fieldMethod.invoke(obj, intval);
                        } else if ("Long".equalsIgnoreCase(fieldType)) {
                            Long temp = Long.parseLong(value);
                            fieldMethod.invoke(obj, temp);
                        } else if ("Double".equalsIgnoreCase(fieldType)) {
                            Double temp = Double.parseDouble(value);
                            fieldMethod.invoke(obj, temp);
                        } else if ("Boolean".equalsIgnoreCase(fieldType)) {
                            Boolean temp = Boolean.parseBoolean(value);
                            fieldMethod.invoke(obj, temp);
                        } else {
                            System.out.println("setFieldValue not supper type:" + fieldType);
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }

        }

        /**
         * bean对象转Map
         *
         * @param obj 实例对象
         * @return map集合
         */
        public static Map<String, String> beanToMap(Object obj) {
            Class<?> cls = obj.getClass();
            Map<String, String> valueMap = new HashMap<String, String>();
            // 取出bean里的所有方法
            Method[] methods = cls.getDeclaredMethods();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                try {
                    String fieldType = field.getType().getSimpleName();
                    String fieldGetName = parseMethodName(field.getName(), "get");
                    if (!haveMethod(methods, fieldGetName)) {
                        continue;
                    }
                    Method fieldGetMet = cls.getMethod(fieldGetName, new Class[]{});
                    Object fieldVal = fieldGetMet.invoke(obj, new Object[]{});
                    String result = null;
                    if ("Date".equals(fieldType)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                        result = sdf.format((Date) fieldVal);

                    } else {
                        if (null != fieldVal) {
                            result = String.valueOf(fieldVal);
                        }
                    }
                    valueMap.put(field.getName(), result);
                } catch (Exception e) {
                    continue;
                }
            }
            return valueMap;

        }

        /**
         * 给对象的字段赋值
         *
         * @param obj            类实例
         * @param fieldSetMethod 字段方法
         * @param fieldType      字段类型
         * @param value
         */
        public static void setFiedlValue(Object obj, Method fieldSetMethod, String fieldType, Object value) {

            try {
                if (null != value && !"".equals(value)) {
                    if ("String".equals(fieldType)) {
                        fieldSetMethod.invoke(obj, value.toString());
                    } else if ("Date".equals(fieldType)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                        Date temp = sdf.parse(value.toString());
                        fieldSetMethod.invoke(obj, temp);
                    } else if ("Integer".equals(fieldType)
                            || "int".equals(fieldType)) {
                        Integer intval = Integer.parseInt(value.toString());
                        fieldSetMethod.invoke(obj, intval);
                    } else if ("Long".equalsIgnoreCase(fieldType)) {
                        Long temp = Long.parseLong(value.toString());
                        fieldSetMethod.invoke(obj, temp);
                    } else if ("Double".equalsIgnoreCase(fieldType)) {
                        Double temp = Double.parseDouble(value.toString());
                        fieldSetMethod.invoke(obj, temp);
                    } else if ("Boolean".equalsIgnoreCase(fieldType)) {
                        Boolean temp = Boolean.parseBoolean(value.toString());
                        fieldSetMethod.invoke(obj, temp);
                    } else if ("Map".equals(fieldType)) {

                        Map map = fromJson(value.toString(), Map.class);


                        fieldSetMethod.invoke(obj, map);
                    } else if ("List".equals(fieldType)) {
                        fieldSetMethod.invoke(obj, fromJson(value.toString(), List.class));
                    } else if ("Set".equals(fieldType)) {
                        fieldSetMethod.invoke(obj, fromJson(value.toString(), Set.class));
                    } else {
                        fieldSetMethod.invoke(obj, value);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        /**
         * 将Map中存在JSON的数据转为 实体类
         *
         * @param mapJSON
         * @param tClass
         * @param <T>
         * @return
         */
        public static <T> Map<?, T> mapJSONToMapObj(Map<?, ?> mapJSON, Class<T> tClass) {
            Map<Object, T> map = null;
            try {
                map = fromJson(mapJSON.toString(), Map.class);
                for (Object key : map.keySet()) {
                    T o = map.get(key);
                    T t = fromJson(o.toString(), tClass);
                    map.put(key, t);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return map;
        }

        /**
         * 反序列化简单对象
         *
         * @param jo    json对象
         * @param clazz 实体类类型
         * @return 反序列化后的实例
         * @throws JSONException
         */
        public static <T> T fromJson(JSONObject jo, Class<T> clazz) throws JSONException {
            if (clazz == null || isNull(jo)) {
                return null;
            }

            T t = newInstance(clazz);
            if (t == null) {
                return null;
            }
            if (isMap(clazz)) {
                setField(t, jo);
            } else {
                // 取出bean里的所有方法
                Method[] methods = clazz.getDeclaredMethods();
                Field[] fields = clazz.getDeclaredFields();
                for (Field f : fields) {
                    String setMetodName = parseMethodName(f.getName(), "set");
                    if (!haveMethod(methods, setMetodName)) {
                        continue;
                    }
                    try {
                        Method fieldMethod = clazz.getMethod(setMetodName, f.getType());
                        setField(t, fieldMethod, f, jo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return t;
        }

        /**
         * 反序列化简单对象
         *
         * @param jsonStr json字符串
         * @param clazz   实体类类型
         * @return 反序列化后的实例
         * @throws JSONException
         */
        public static <T> T fromJson(String jsonStr, Class<T> clazz) throws JSONException {
            if (clazz == null || jsonStr == null || jsonStr.length() == 0) {
                return null;
            }

            JSONObject jo = new JSONObject(jsonStr);
            if (isNull(jo)) {
                return null;
            }

            return fromJson(jo, clazz);
        }

        /**
         * 反序列化数组对象
         *
         * @param ja    json数组
         * @param clazz 实体类类型
         * @return 反序列化后的数组
         */
        public static <T> T[] fromJsonArray(JSONArray ja, Class<T> clazz) throws JSONException {
            if (clazz == null || isNull(ja)) {
                return null;
            }

            int len = ja.length();

            @SuppressWarnings("unchecked")
            T[] array = (T[]) Array.newInstance(clazz, len);

            for (int i = 0; i < len; ++i) {
                JSONObject jo = ja.getJSONObject(i);
                T o = fromJson(jo, clazz);
                array[i] = o;
            }

            return array;
        }


        /**
         * 反序列化数组对象
         *
         * @param jsonStr json字符串
         * @param clazz   实体类类型
         * @return 序列化后的数组
         */
        public static <T> T[] fromJsonArray(String jsonStr, Class<T> clazz) throws JSONException {
            if (clazz == null || jsonStr == null || jsonStr.length() == 0) {
                return null;
            }
            JSONArray jo = null;
            try {
                jo = new JSONArray(jsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (isNull(jo)) {
                return null;
            }

            return fromJsonArray(jo, clazz);
        }

        /**
         * 反序列化泛型集合
         *
         * @param ja              json数组
         * @param collectionClazz 集合类型
         * @param genericType     实体类类型
         * @return
         * @throws JSONException
         */
        @SuppressWarnings("unchecked")
        public static <T> Collection<T> parseCollection(JSONArray ja, Class<?> collectionClazz,
                                                        Class<T> genericType) throws JSONException {

            if (collectionClazz == null || genericType == null || isNull(ja)) {
                return null;
            }

            Collection<T> collection = (Collection<T>) newInstance(collectionClazz);

            for (int i = 0; i < ja.length(); ++i) {
                try {
                    JSONObject jo = ja.getJSONObject(i);
                    T o = fromJson(jo, genericType);
                    collection.add(o);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return collection;
        }

        /**
         * 反序列化泛型集合
         *
         * @param jsonStr         json字符串
         * @param collectionClazz 集合类型
         * @param genericType     实体类类型
         * @return 反序列化后的数组
         * @throws JSONException
         */
        public static <T> Collection<T> parseCollection(String jsonStr, Class<?> collectionClazz,
                                                        Class<T> genericType) throws JSONException {
            if (collectionClazz == null || genericType == null || jsonStr == null
                    || jsonStr.length() == 0) {
                return null;
            }
            JSONArray jo = null;
            try {
                //如果为数组，则此处转化时，需要去掉前面的键，直接后面的[]中的值
                int index = jsonStr.indexOf("[");
                String arrayString = null;

                //获取数组的字符串
                if (-1 != index) {
                    arrayString = jsonStr.substring(index);
                }

                //如果为数组，使用数组转化
                if (null != arrayString) {
                    jo = new JSONArray(arrayString);
                } else {
                    jo = new JSONArray(jsonStr);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (isNull(jo)) {
                return null;
            }

            return parseCollection(jo, collectionClazz, genericType);
        }

        /**
         * 根据类型创建对象
         *
         * @param clazz 待创建实例的类型
         * @return 实例对象
         * @throws JSONException
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static <T> T newInstance(Class<T> clazz) throws JSONException {
            if (clazz == null)
                return null;
            T obj = null;
            if (clazz.isInterface()) {
                if (clazz.equals(Map.class)) {
                    obj = (T) new HashMap();
                } else if (clazz.equals(List.class)) {
                    obj = (T) new ArrayList();
                } else if (clazz.equals(Set.class)) {
                    obj = (T) new HashSet();
                } else {
                    throw new JSONException("unknown interface: " + clazz);
                }
            } else {
                try {
                    obj = clazz.newInstance();//实体化
                } catch (IllegalAccessException e) {
                    return null;
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return obj;
        }

        /**
         * 设定Map的值
         *
         * @param obj 待赋值字段的对象
         * @param jo  json实例
         */
        private static void setField(Object obj, JSONObject jo) {
            try {
                Iterator<String> keyIter = jo.keys();
                String key;
                Object value;
                @SuppressWarnings("unchecked")
                Map<String, Object> valueMap = (Map<String, Object>) obj;
                while (keyIter.hasNext()) {
                    key = (String) keyIter.next();
                    value = jo.get(key);
                    valueMap.put(key, value);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        /**
         * 设定字段的值
         *
         * @param obj            待赋值字段的对象
         * @param fieldSetMethod 字段方法名
         * @param field          字段
         * @param jo             json实例
         */
        private static void setField(Object obj, Method fieldSetMethod, Field field, JSONObject jo) {

            String name = field.getName();
            Class<?> clazz = field.getType();

            try {
                if (isArray(clazz)) { // 数组
                    Class<?> c = clazz.getComponentType();
                    JSONArray ja = jo.optJSONArray(name);
                    if (!isNull(ja)) {
                        Object array = fromJsonArray(ja, c);
                        setFiedlValue(obj, fieldSetMethod, clazz.getSimpleName(), array);
                    }
                } else if (isCollection(clazz)) { // 泛型集合
                    // 获取定义的泛型类型
                    Class<?> c = null;
                    Type gType = field.getGenericType();
                    if (gType instanceof ParameterizedType) {
                        ParameterizedType ptype = (ParameterizedType) gType;
                        Type[] targs = ptype.getActualTypeArguments();
                        if (targs.length > 0) {
                            Type t = targs[0];
                            c = (Class<?>) t;
                        }
                    }

                    JSONArray ja = jo.optJSONArray(name);
                    if (!isNull(ja)) {
                        Object o = parseCollection(ja, clazz, c);
                        setFiedlValue(obj, fieldSetMethod, clazz.getSimpleName(), o);
                    }
                } else if (isSingle(clazz)) { // 值类型
                    Object o = jo.opt(name);
                    if (o != null) {
                        setFiedlValue(obj, fieldSetMethod, clazz.getSimpleName(), o);
                    }
                } else if (isObject(clazz)) { // 对象
                    JSONObject j = jo.optJSONObject(name);
                    if (!isNull(j)) {
                        Object o = fromJson(j, clazz);
                        setFiedlValue(obj, fieldSetMethod, clazz.getSimpleName(), o);
                    }
                } else if (isList(clazz) || isMap(clazz) || isSet(clazz)) { // list 或 Map 或 Set
                    JSONObject j = jo.optJSONObject(name);
                    if (!isNull(j)) {
                        Object o = fromJson(j, clazz);
                        setFiedlValue(obj, fieldSetMethod, clazz.getSimpleName(), o);
                    }
                } else {
                    //或者位置类型
                    JSONObject j = jo.optJSONObject(name);
                    if (!isNull(j)) {
                        Object o = fromJson(j, clazz);
                        setFiedlValue(obj, fieldSetMethod, clazz.getSimpleName(), o);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 设定字段的值
         *
         * @param obj   待赋值字段的对象
         * @param field 字段
         * @param jo    json实例
         */
        @SuppressWarnings("unused")
        private static void setField(Object obj, Field field, JSONObject jo) {
            String name = field.getName();
            Class<?> clazz = field.getType();
            try {
                if (isArray(clazz)) { // 数组
                    Class<?> c = clazz.getComponentType();
                    JSONArray ja = jo.optJSONArray(name);
                    if (!isNull(ja)) {
                        Object array = fromJsonArray(ja, c);
                        field.set(obj, array);
                    }
                } else if (isCollection(clazz)) { // 泛型集合
                    // 获取定义的泛型类型
                    Class<?> c = null;
                    Type gType = field.getGenericType();
                    if (gType instanceof ParameterizedType) {
                        ParameterizedType ptype = (ParameterizedType) gType;
                        Type[] targs = ptype.getActualTypeArguments();
                        if (targs != null && targs.length > 0) {
                            Type t = targs[0];
                            c = (Class<?>) t;
                        }
                    }
                    JSONArray ja = jo.optJSONArray(name);
                    if (!isNull(ja)) {
                        Object o = parseCollection(ja, clazz, c);
                        field.set(obj, o);
                    }
                } else if (isSingle(clazz)) { // 值类型
                    Object o = jo.opt(name);
                    if (o != null) {
                        field.set(obj, o);
                    }
                } else if (isObject(clazz)) { // 对象
                    JSONObject j = jo.optJSONObject(name);
                    if (!isNull(j)) {
                        Object o = fromJson(j, clazz);
                        field.set(obj, o);
                    }
                } else if (isList(clazz)) { // 列表
                    JSONObject j = jo.optJSONObject(name);
                    if (!isNull(j)) {
                        Object o = fromJson(j, clazz);
                        field.set(obj, o);
                    }
                } else {
                    throw new Exception("unknow type!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 判断对象是否为空
         *
         * @param obj 实例
         * @return
         */
        private static boolean isNull(Object obj) {
            if (obj instanceof JSONObject) {
                return JSONObject.NULL.equals(obj);
            }
            return obj == null;
        }

        /**
         * 判断是否是值类型
         *
         * @param clazz
         * @return
         */
        private static boolean isSingle(Class<?> clazz) {
            return isBoolean(clazz) || isNumber(clazz) || isString(clazz);
        }

        /**
         * 是否布尔值
         *
         * @param clazz
         * @return
         */
        public static boolean isBoolean(Class<?> clazz) {
            return (clazz != null)
                    && ((Boolean.TYPE.isAssignableFrom(clazz)) || (Boolean.class
                    .isAssignableFrom(clazz)));
        }

        /**
         * 是否数值
         *
         * @param clazz
         * @return
         */
        public static boolean isNumber(Class<?> clazz) {
            return (clazz != null)
                    && ((Byte.TYPE.isAssignableFrom(clazz)) || (Short.TYPE.isAssignableFrom(clazz))
                    || (Integer.TYPE.isAssignableFrom(clazz))
                    || (Long.TYPE.isAssignableFrom(clazz))
                    || (Float.TYPE.isAssignableFrom(clazz))
                    || (Double.TYPE.isAssignableFrom(clazz)) || (Number.class
                    .isAssignableFrom(clazz)));
        }

        /**
         * 判断是否是字符串
         *
         * @param clazz
         * @return
         */
        public static boolean isString(Class<?> clazz) {
            return (clazz != null)
                    && ((String.class.isAssignableFrom(clazz))
                    || (Character.TYPE.isAssignableFrom(clazz)) || (Character.class
                    .isAssignableFrom(clazz)));
        }

        /**
         * 判断是否是对象
         *
         * @param clazz
         * @return
         */
        private static boolean isObject(Class<?> clazz) {
            return clazz != null && !isSingle(clazz) && !isArray(clazz) && !isCollection(clazz) && !isMap(clazz);
        }

        /**
         * 判断是否是数组
         *
         * @param clazz
         * @return
         */
        public static boolean isArray(Class<?> clazz) {
            return clazz != null && clazz.isArray();
        }

        /**
         * 判断是否是集合
         *
         * @param clazz
         * @return
         */
        public static boolean isCollection(Class<?> clazz) {
            return clazz != null && Collection.class.isAssignableFrom(clazz);
        }

        /**
         * 判断是否是Map
         *
         * @param clazz
         * @return
         */
        public static boolean isMap(Class<?> clazz) {
            return clazz != null && Map.class.isAssignableFrom(clazz);
        }

        /**
         * 判断是否是列表
         *
         * @param clazz
         * @return
         */
        public static boolean isList(Class<?> clazz) {
            return clazz != null && List.class.isAssignableFrom(clazz);
        }

        /**
         * 判断是否是列表
         *
         * @param clazz
         * @return
         */
        public static boolean isSet(Class<?> clazz) {
            return clazz != null && Set.class.isAssignableFrom(clazz);
        }

    }

    /**
     * OkGo 网络请求框架
     */
    public static class OkGo {

        private Map<String, String> map;
        private String url;


        /**
         * 初始化 map 并进行添加参数
         *
         * @param key
         * @param value
         * @return
         */
        public OkGo addParameter(String key, String value) {
            if (map == null) {
                map = new HashMap<>();//初始化 map
            } else {
                if (!map.containsKey(key)) {//如果当前 map 中没有此key
                    map.put(key, value);
                }
            }
            return this;
        }

        /**
         * 实例化  post 请求
         *
         * @param url
         * @param map
         */
        public OkGo(String url, Map<String, String> map) {
            this.url = url;
            this.map = map;
        }

        /**
         * 实例化 get 请求
         *
         * @param url
         */
        public OkGo(String url) {
            this.url = url;
        }

        /**
         * 请求网络数据 Post
         *
         * @param stringCallback new 一个 StringCallback 内部类接口
         */
        public void loadDataPost(StringCallback stringCallback) {
            if (map == null) {
                com.lzy.okgo.OkGo
                        .<String>post(url)
                        .execute(stringCallback);

            } else {
                com.lzy.okgo.OkGo
                        .<String>post(url)
                        .params(map)
                        .execute(stringCallback);
            }
        }

        /**
         * 请求网络数据 Get
         *
         * @param stringCallback new 一个 StringCallback 内部类接口
         */
        public void loadDataGet(StringCallback stringCallback) {
            if (map == null) {
                com.lzy.okgo.OkGo
                        .<String>get(url)
                        .execute(stringCallback);

            } else {
                com.lzy.okgo.OkGo
                        .<String>get(url)
                        .params(map)
                        .execute(stringCallback);
            }
        }

    }

    /**
     * okHttp 网络请求框架
     */
    public static class OkHttp {

        private OkHttpClient mOkHttpClient = null;
        private Call call = null;
        private String url = null;
        private Map<String, String> map = null;

        /**
         * 初始化 参数 post
         *
         * @param url
         * @param map
         */
        public OkHttp(String url, Map<String, String> map) {
            mOkHttpClient = new OkHttpClient();
            this.url = url;
            this.map = map;
        }

        /**
         * 初始化 参数 get
         *
         * @param url
         */
        public OkHttp(String url) {
            mOkHttpClient = new OkHttpClient();
            this.url = url;
        }

        /**
         * 请求数据
         *
         * @param callback new 一个 Callback 类的内部类
         */
        public void loadData(Callback callback) {
            if (url != null) {
                Request request = null;
                if (map != null && map.size() > 0) {
                    FormBody.Builder builder = new FormBody.Builder();
                    for (String key : map.keySet()) {
                        builder.add(key, map.get(key));
                    }
                    RequestBody formBody = builder.build();
                    request = new Request.Builder()
                            .url(url)
                            .post(formBody)
                            .build();
                } else {
                    request = new Request.Builder()
                            .url(url)
                            .build();
                }
                call = mOkHttpClient.newCall(request);
                call.enqueue(callback);

            }
        }


        /**
         * 下载工具
         */
        public static class DownloadUtil {

            private static DownloadUtil downloadUtil;
            private static OkHttpClient okHttpClient;

            public static DownloadUtil get() {
                if (downloadUtil == null) {
                    downloadUtil = new DownloadUtil();
                }
                return downloadUtil;
            }

            public DownloadUtil() {
                okHttpClient = new OkHttpClient();
            }


            /**
             * @param url          下载连接
             * @param destFileDir  下载的文件储存目录
             * @param destFileName 下载文件名称，后面记得拼接后缀，否则手机没法识别文件类型
             * @param listener     下载监听
             */

            public static void download(final String url, final String destFileDir, final String destFileName, final OnDownloadListener listener) {

                Request request = new Request.Builder()
                        .url(url)
                        .build();

                OkHttpClient client = new OkHttpClient();

                //异步请求
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // 下载失败监听回调
                        listener.onDownloadFailed(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        InputStream is = null;
                        byte[] buf = new byte[2048];
                        int len = 0;
                        FileOutputStream fos = null;

                        //储存下载文件的目录
                        File dir = new File(destFileDir);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        File file = new File(dir, destFileName);

                        try {

                            is = response.body().byteStream();
                            long total = response.body().contentLength();
                            fos = new FileOutputStream(file);
                            long sum = 0;
                            while ((len = is.read(buf)) != -1) {
                                fos.write(buf, 0, len);
                                sum += len;
                                int progress = (int) (sum * 1.0f / total * 100);
                                //下载中更新进度条
                                listener.onDownloading(progress);
                            }
                            fos.flush();
                            //下载完成
                            listener.onDownloadSuccess(file);
                        } catch (Exception e) {
                            listener.onDownloadFailed(e);
                        } finally {

                            try {
                                if (is != null) {
                                    is.close();
                                }
                                if (fos != null) {
                                    fos.close();
                                }
                            } catch (IOException e) {

                            }

                        }


                    }
                });
            }


            public interface OnDownloadListener {

                /**
                 * 下载进度
                 */
                void onDownloading(int progress);

                /**
                 * 下载成功之后的文件
                 */
                void onDownloadSuccess(File file);

                /**
                 * 下载异常信息
                 */

                void onDownloadFailed(Exception e);
            }
        }


    }

    /**
     * HttpUtil 原始网络请求类
     * 将 Map 中的参数解析成想要的 get 参数
     * https://apis.map.qq.com/ws/geocoder/v1/?location=22.5948,114.3069163&get_poi=1&key=J6HBZ-N3K33-D2B3V-YH7I4-37AVE-NJFMT
     */
    public static class HttpUtil {
        private static final String UTF_8 = "utf-8";
        private static final String GET = "GET";
        private static final String POST = "POST";
        private static String value = "";
        private static String url = "";

        private static String type = "text";//请求参数的类型

        public static void setTextType() {
            type = "text";
        }

        public static void setJsonType() {
            type = "json";
        }

        /**
         * Map转参数
         *
         * @param paramMap
         * @return
         */
        public static String mapToParams(Map<String, Object> paramMap) {
            StringBuffer stringBuffer = new StringBuffer();
            if (paramMap != null && paramMap.size() > 0) {
                stringBuffer.append("?");//url 与 请求参数的分隔符
                for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                    stringBuffer.append(entry.getKey());
                    stringBuffer.append("=");
                    stringBuffer.append(entry.getValue());
                    stringBuffer.append("&");
                }
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);//去掉最后一个 & 字符
                return stringBuffer.toString();
            }

            return null;
        }

        /**
         * get请求封装
         *
         * @param url      请求域名
         * @param paramMap 请求体
         * @param listener 请求接口
         */
        public static void getRequest(final String url, final Map<String, Object> paramMap, final OnLoadData listener) {
            if (url == null || listener == null) {
                return;
            }

            String params = mapToParams(paramMap);
            if (params == null) {
                err(getLineInfo(), "请求参数不能为null");
                return;
            }

            //将解析好的参数直接调用 getRequest 进行get请求参数
            getRequest(url + params, listener);
        }

        /**
         * get请求封装
         *
         * @param url      请求域名
         * @param listener 请求接口
         */
        public static void getRequest(final String url, final OnLoadData listener) {
            if (url == null || listener == null) {
                return;
            }
            Thread.runJava(new Runnable() { //为请求网络数据开启子线程
                @Override
                public void run() {
                    HttpURLConnection conn = null;
                    try {
                        URL path = new URL(url);//获取 Url
                        conn = (HttpURLConnection) path.openConnection();//打开连接
                        conn.setRequestMethod(GET);    //设置请求方式
                        int code = conn.getResponseCode();
                        if (code == 200) {//应答码200表示请求成功
                            try {
                                onSuccess(listener, conn);//请求成功
                            } catch (Exception e1) {
                                err("e:" + e1);
                            }
                        } else {
                            GT.err("向服务器get请求返回的code:" + code);
                        }
                    } catch (Exception error) {
                        try {
                            onError(listener, error, conn);//请求失败
                        } catch (Exception e1) {
                            err("e1:" + error);
                            err("e2:" + e1);
                        }
                    }
                }
            });
        }

        /**
         * post请求
         *
         * @param url      请求的url
         * @param map      请求体
         * @param listener 请求接口
         */
        public static void postRequest(final String url, final Map<String, Object> map, final OnLoadData listener) {

            String params = mapToParams(map);
            if (params == null) {
                err(getLineInfo(), "请求参数不能为null");
            }

            postRequest(url + "?" + params, listener, null);
        }

        /**
         * @param url       请求的url
         * @param map       请求体
         * @param mapHeader 请求头
         * @param listener  请求接口
         */
        public static void postRequest(final String url, final Map<String, Object> map, Map<String, Object> mapHeader, final OnLoadData listener) {

            String params = mapToParams(map);
            if (url == null || params == null) {
                err(getLineInfo(), "请求参数不能为null");
            }

            postRequest(url + "?" + params, listener, mapHeader);
        }

        /**
         * POST请求
         */
        public static void postRequest(final String url, final OnLoadData listener, Map<String, Object> mapHeader) {

            if (url == null || listener == null) {
                return;
            }

            value = "";// 初始化
            HttpUtil.url = "";// 初始化

            if (url.contains("?")) {
                String[] arrayUrl = url.split("\\?");
                if (arrayUrl.length >= 2) {
                    HttpUtil.url = arrayUrl[0];
                    for (int i = 1; i < arrayUrl.length; i++) {
                        value += arrayUrl[i];
                    }
                } else {
                    return;
                }
            } else {
                HttpUtil.url = url;
            }

            Thread.runJava(new Runnable() {// 为网络请求开启子线程
                @Override
                public void run() {
                    HttpURLConnection conn = null;
                    try {
                        // 打开连接
                        URL path = new URL(HttpUtil.url);// 1. 生成URL
                        conn = (HttpURLConnection) path.openConnection();// 2. HttpURLConnection 打开连接
                        conn.setRequestMethod(POST);// 3. 设置为 POST 请求
                        conn.setDoInput(true);//可向连接中写入
                        conn.setDoOutput(true);//可向连接中读取数据
                        conn.setUseCaches(false);//禁止使用缓存
                        conn.setInstanceFollowRedirects(true);//自动执行Http重定向

                        //请求头添加处
                        if (type.equals("text")) {
                            // Text 请求
                            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//4. Content-Type,这里是固定写法，发送内容的类型
                        } else {
                            // json 请求
                            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");// 设置文件类型:
                        }
                        conn.setRequestProperty("Connection", "Keep-Alive");
                        conn.setRequestProperty("Charset", "UTF-8");
                        conn.setRequestProperty("accept", "application/json");

                        //添加自定义的请求头
                        if (mapHeader != null) {
                            for (String key : mapHeader.keySet()) {
                                String value = String.valueOf(mapHeader.get(key));
                                conn.setRequestProperty(key, value);
                            }
                        }

                        // 向服务器提交请求数据
                        conn.setDoOutput(true);// 5. output，这里要记得开启输出流，将自己要添加的参数用这个输出流写进去，传给服务端，这是socket的基本结构
                        OutputStream os = conn.getOutputStream();// 获取输出流
                        os.write(value.getBytes(UTF_8));// 一定要记得将自己的参数转换为字节，编码格式是utf-8
                        os.flush();// 关闭输出流

                        int code = conn.getResponseCode();
                        if (code == 200) {// 应答码200表示请求成功
                            try {
                                onSuccess(listener, conn);// 请求成功
                            } catch (Exception e1) {
                                err("e:" + e1);
                            }

                        } else {
                            GT.err("向服务器post请求返回的code:" + code);
                        }

                    } catch (Exception e) {
                        try {
                            onError(listener, e, conn);
                        } catch (Exception e1) {
                            err("e1:" + e);
                            err("e2:" + e1);
                        }
                    }
                }
            });

        }

        /**
         * 反馈失败
         *
         * @param listener
         * @param onError
         */
        private static void onError(final OnLoadData listener, final Exception onError, HttpURLConnection conn) {
            conn.disconnect();//断开连接
            listener.onError(onError.toString());
        }

        /**
         * 反馈成功
         *
         * @param listener
         * @param con
         * @throws IOException
         */
        private static void onSuccess(final OnLoadData listener, HttpURLConnection con) throws IOException {
            InputStream inputStream = con.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();//创建内存输出流
            int len;
            byte[] bytes = new byte[1024];
            if (inputStream != null) {
                while ((len = inputStream.read(bytes)) != -1) {
                    baos.write(bytes, 0, len);
                }
                final String str = new String(baos.toByteArray(), UTF_8);
                listener.onSuccess(str);
                inputStream.close();//关闭
                con.disconnect();//断开连接
            }
        }

        //请求接口
        public interface OnLoadData {
            void onSuccess(String response);

            void onError(String response);
        }


         /* GT.Thread.runJava(new Runnable() {
            @Override
            public void run() {

                URL url;
                try {
                    url = new URL(urlStr);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.setUseCaches(false);
                    urlConnection.setInstanceFollowRedirects(true);
                    urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());

                    String param = "location="
                            + URLEncoder.encode("22.5948,114.3069163","utf-8")
                            + "&get_poi="
                            + URLEncoder.encode("1","utf-8")
                            + "&key="
                            + URLEncoder.encode("J6HBZ-N3K33-D2B3V-YH7I4-37AVE-NJFMT","utf-8");

                    out.writeBytes(param);
                    out.flush();
                    out.close();

                    if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){

                        InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
                        BufferedReader buffer = new BufferedReader(in);
                        String inputLine = null;

                        String result = "\n";

                        while ((inputLine = buffer.readLine()) != null){
                            result += inputLine + "\n";
                        }
                        in.close();

                        GT.log("result:" + result);

                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });*/


    }

    /**
     * WebWiew 工具类
     */
    public static class WebViewUtils {

        /**
         * 需要添加 xml/network_security_config.xml
         * 配置文件:android:networkSecurityConfig="@xml/network_security_config"
         * <p>
         * <?xml version="1.0" encoding="utf-8"?>
         * <network-security-config>
         * <base-config cleartextTrafficPermitted="true" />
         * </network-security-config>
         * <p>
         * 前提网络权限
         * <uses-permission android:name="android.permission.INTERNET"/>
         */

        private static long webSize = -1;

        /**
         * 获取网页代码
         *
         * @param htmlUrl               网页地址
         * @param htmlSize              网页大小(可以直接获取 onGetClose 上获取到确切值)
         * @param onGetHtmlCodeListener 对应接口
         */
        public static void getHtmlData(String htmlUrl, int htmlSize, OnGetHtmlCodeListener onGetHtmlCodeListener) {
            Thread.runJava(new Runnable() {
                @Override
                public void run() {
                    if (htmlUrl == null || onGetHtmlCodeListener == null) {
                        onGetHtmlCodeListener.onGetError(htmlUrl, "The parameter cannot be empty");
                        return;
                    }
                    HttpURLConnection conn = null;
                    String html = null;
                    try {
                        conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
                    } catch (IOException e) {
//            e.printStackTrace();
                        onGetHtmlCodeListener.onGetError(htmlUrl, e);
                    }
                    try {
                        conn.setRequestMethod("GET");
                    } catch (ProtocolException e) {
//            e.printStackTrace();
                        onGetHtmlCodeListener.onGetError(htmlUrl, e);
                    }
                    conn.setConnectTimeout(5 * 1000);
                    InputStream inStream = null;//通过输入流获取html数据
                    try {
                        inStream = conn.getInputStream();
                    } catch (IOException e) {
//            e.printStackTrace();
                        onGetHtmlCodeListener.onGetError(htmlUrl, e);

                    }
                    onGetHtmlCodeListener.onGetStart(htmlUrl);
                    byte[] data = new byte[0];//得到html的二进制数据
                    webSize = -1;
                    try {
                        data = readInputStream(inStream, htmlSize, onGetHtmlCodeListener);
                    } catch (Exception e) {
//            e.printStackTrace();
                        onGetHtmlCodeListener.onGetError(htmlUrl, e);
                        return;
                    }

                    try {
                        html = new String(data, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
                        onGetHtmlCodeListener.onGetError(htmlUrl, e);
                        return;
                    }
                    onGetHtmlCodeListener.onGetClose(htmlUrl, html, webSize);
                }
            });

        }

        private static byte[] readInputStream(InputStream inStream, int webSize2, OnGetHtmlCodeListener onGetHtmlCodeListener) throws Exception {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            int sumLen = 0;
            int len = 0;
            byte[] buffer = new byte[1024];
            while (true) {
                len = inStream.read(buffer);
                sumLen += len;
                int mProgress = (int) (((float) sumLen / webSize2) * 100);
                if (len < 0) break;
                onGetHtmlCodeListener.onGetProgress(mProgress);
                outStream.write(buffer, 0, len);
            }
            inStream.close();
            webSize = sumLen;
            return outStream.toByteArray();
        }

        public interface OnGetHtmlCodeListener {

            /**
             * @加载开始
             */
            void onGetStart(String url);

            /**
             * @加载中
             */
            void onGetProgress(int progress);

            /**
             * @加载结束
             */
            void onGetClose(String url, String htmlCode, long htmlSize);

            /**
             * @加载错误
             */
            void onGetError(String url, Object errorMessage);

        }


        /**
         * 加载 PC 版的网页
         *
         * @param webView 网页对象
         * @param url     要加载的网页
         */
        @SuppressLint("SetJavaScriptEnabled")
        public static WebView loadPCHtml(Context context, WebView webView, String url, boolean isCache, OnLoadWebViewListener onLoadWebViewListener) {
            if (webView == null) return null;

            //设置PC网
            webView.getSettings().setUserAgentString("Mozilla/5.0 (WindowUtilss NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36");
            //支持获取手势焦点
            webView.requestFocusFromTouch();

            loadAppHtml(context, webView, url, isCache, onLoadWebViewListener);

            return webView;

        }

        private static boolean isLoadSuccess = false;

        /**
         * 加载AppHtml网页
         *
         * @param webView
         * @param url
         */
        @SuppressLint("SetJavaScriptEnabled")
        public static WebView loadAppHtml(Context context, WebView webView, String url, boolean isCache, final OnLoadWebViewListener onLoadWebViewListener) {
            if (webView == null || context == null) return webView;

            //声明WebSettings子类
            WebSettings webSettings = webView.getSettings();

            //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
            webSettings.setJavaScriptEnabled(true);
            // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
            // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

            //设置自适应屏幕，两者合用
            webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
            webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

            //缩放操作
            webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
            webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
            webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

            //其他细节操作
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
            webSettings.setAllowFileAccess(true); //设置可以访问文件
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
            webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
            webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式


            // 通过设置WebView的settings实现
            String cacheDirPath = context.getFilesDir().getAbsolutePath() + "cache/";
            webSettings.setDatabasePath(cacheDirPath);

            if (isCache) {
                //使用缓存:
                webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//没网，则从本地获取，即离线加载

                webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
                webSettings.setDatabaseEnabled(true);   //开启 database storage API 功能
                webSettings.setAppCacheEnabled(true);//开启 Application Caches 功能
            } else {
                //不使用缓存:
                webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
            }
            //缓存模式如下：
            //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
            //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
            //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
            //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。

            //获得网页的加载进度并显示
            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    super.onProgressChanged(view, newProgress);
                    if (newProgress <= 100 && !isLoadSuccess) {
                        if (newProgress == 100) {
                            isLoadSuccess = true;
                        }
                        onLoadWebViewListener.onLoadingProgress(view, newProgress);
                    }
                }

            });


            //设置加载Web的生命周期
            webView.setWebViewClient(new WebViewClient() {

                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    handler.proceed();    //表示等待证书响应
                    // handler.cancel();      //表示挂起连接，为默认方式
                    // handler.handleMessage(null);    //可做其他处理
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    //设定加载开始的操作
                    onLoadWebViewListener.onLoadStart(view, url, favicon);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    //设定加载结束的操作
                    onLoadWebViewListener.onLoadClose(view, url);
                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    //该方法传回了错误码，根据错误类型可以进行不同的错误分类处理
                    onLoadWebViewListener.onLoadError(view, errorCode, description, failingUrl);
                }

            });


            // 特别注意：5.1以上默认禁止了https和http混用，以下方式是开启
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }

            if (url != null) {
                webView.loadUrl(url);
            }

            return webView;

        }

        /**
         * 加载 html
         *
         * @param context               活动
         * @param isPC                  是否电脑端
         * @param webView               WebView
         * @param url                   加载的网址
         * @param isCache               是否缓存
         * @param onLoadWebViewListener 接口监听
         * @return
         */
        public static WebView loadHtml(Context context, boolean isPC, WebView webView, String url, boolean isCache, OnLoadWebViewListener onLoadWebViewListener) {
            if (isPC) {
                return loadPCHtml(context, webView, url, isCache, onLoadWebViewListener);
            } else {
                return loadAppHtml(context, webView, url, isCache, onLoadWebViewListener);
            }
        }

        /**
         * 仅仅加强 WebView
         *
         * @param context
         * @param webView
         * @param isCache
         * @return
         */
        public static WebView setWebView(Context context, WebView webView, boolean isCache) {
            if (webView == null || context == null) return webView;

            //声明WebSettings子类
            WebSettings webSettings = webView.getSettings();

            //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
            webSettings.setJavaScriptEnabled(true);
            // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
            // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

            //设置自适应屏幕，两者合用
            webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
            webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

            //缩放操作
            webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
            webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
            webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

            //其他细节操作
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
            webSettings.setAllowFileAccess(true); //设置可以访问文件
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
            webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
            webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式


            // 通过设置WebView的settings实现
            String cacheDirPath = context.getFilesDir().getAbsolutePath() + "cache/";
            webSettings.setDatabasePath(cacheDirPath);

            if (isCache) {
                //使用缓存:
                webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//没网，则从本地获取，即离线加载

                webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
                webSettings.setDatabaseEnabled(true);   //开启 database storage API 功能
                webSettings.setAppCacheEnabled(true);//开启 Application Caches 功能
            } else {
                //不使用缓存:
                webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
            }
            return webView;
        }

        /**
         * 加载一个网页
         *
         * @param webView
         * @param url
         * @return
         */
        public static boolean loadWeb_URL(WebView webView, String url) {
            if (webView == null || url == null) return false;
            try {
                webView.loadUrl(url);
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        /**
         * 加载一个Asset 文件里的 html 文件
         *
         * @param webView
         * @param htmlFile 需要加上后缀名 如：test.html
         * @return
         */
        public static boolean loadWeb_Asset(WebView webView, String htmlFile) {
            if (webView == null || htmlFile == null) return false;
            try {
                webView.loadUrl("file:///android_asset/" + htmlFile);
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        /**
         * 加载一个 SD卡里的
         *
         * @param webView
         * @param htmlFile
         * @return
         */
        public static boolean loadWeb_SdCard(WebView webView, String htmlFile) {
            if (webView == null || htmlFile == null) return false;
            try {
                webView.loadUrl("content://com.android.htmlfileprovider/sdcard/" + htmlFile);
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        /**
         * 加载一个 html源码
         * @param webView
         * @param htmlCode
         * @return
         */
        public static boolean loadWeb_HtmlCode(WebView webView, String htmlCode) {
            if (webView == null || htmlCode == null) return false;
            try {
                webView.loadData(htmlCode, "text/html", "UTF-8");
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        //加载网页接口
        public interface OnLoadWebViewListener {

            /**
             * @加载开始
             */
            void onLoadStart(WebView view, String url, Bitmap favicon);

            /**
             * @加载中
             */
            void onLoadingProgress(WebView view, int progress);

            /**
             * @加载结束
             */
            void onLoadClose(WebView view, String url);

            /**
             * @加载错误
             */
            void onLoadError(WebView view, int errorCode, String description, String failingUrl);

        }

    }

    /**
     * 网络连接
     */
    public static class GT_Socket {

        public static boolean isLog = false;//是否打开日志

        public interface OnStateListener {
            void start(Object obj);

            void loading(int progress);

            void close(Object obj);
        }

        public static class TCP {

            //构建 GT_SharedPreferences
            @Target(ElementType.FIELD)
            @Retention(RetentionPolicy.RUNTIME)
            public @interface Build {
                String setIP() default "null";      //默认IP地址

                int setPort();                      //默认的端口号

                boolean isLog() default false;      //是否使用日志
            }

            private ServerSocket serverSocket;//服务器套接字
            private Socket socket;//服务器、客户端套接字

            //文字
            private PrintStream printStream;//输出
            private BufferedReader bufferedReader;//输入
            //图片
            private DataOutputStream dataOutputStream;//输出
            private DataInputStream dataInputStream;//输入

            private int port = -1;//端口号：必须大于1023 0~1023为系统保留端口

            /**
             * 创建服务器
             *
             * @param port
             */
            public TCP(int port) {
                this.port = port;
                Thread.runJava(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            serverSocket = new ServerSocket(port);//创建服务器
                            if (isLog)
                                GT.log("服务器：创建好服务器，等待连接");
                            socket = serverSocket.accept();//开始等待客户端连接
                            if (isLog)
                                GT.log("服务器：有客户端连接");
                            //文字
                            printStream = new PrintStream(socket.getOutputStream());//输出
                            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));//输入
                            //图片
                            dataOutputStream = new DataOutputStream(socket.getOutputStream());//输出
                            dataInputStream = new DataInputStream(socket.getInputStream());//输入

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            /**
             * 创建客户端
             *
             * @param port
             */
            public TCP(String ip, int port) {
                this.port = port;
                Thread.runJava(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (isLog)
                                GT.log("客户端：开始连接服务器");
                            socket = new Socket(ip, port);//连接服务器
                            if (isLog)
                                GT.log("客户端：连接服务器成功！");
                            //文字
                            printStream = new PrintStream(socket.getOutputStream());//输出
                            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));//输入
                            //图片
                            dataOutputStream = new DataOutputStream(socket.getOutputStream());//输出
                            dataInputStream = new DataInputStream(socket.getInputStream());//输入
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            /**
             * 发送文本数据
             *
             * @param data
             * @return
             */
            public TCP sendText(Object data) {
                if (printStream == null) return this;
                Thread.runJava(new Runnable() {
                    @Override
                    public void run() {
                        printStream.println(String.valueOf(data));
                    }
                });
                return this;
            }

            /**
             * 读取文本数据
             *
             * @return
             */
            public String readText() {
                if (bufferedReader == null) return null;
                String data = null;
                try {
                    data = bufferedReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return data;
            }

            /**
             * 发送图片
             *
             * @param bitmap 将你的图片资源转为 Bitmap
             * @return
             */
            public TCP sendImg(Bitmap bitmap) {
                if (dataOutputStream == null) return this;
                Thread.runJava(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            GT.log("bitmap:" + bitmap);
                            ByteArrayOutputStream bout = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bout);
                            int len = bout.size();           //写入字节的长度，再写入图片的字节
                            dataOutputStream.writeInt(len);  //这里打印一下发送的长度
                            dataOutputStream.write(bout.toByteArray());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return this;
            }

            /***
             * 读取图片带进度的
             * @param onReadData
             * @return
             */
            public Bitmap readImg(OnStateListener stateListener) {
                if (dataInputStream == null) return null;
                Bitmap bmp = null;
                int size = 0;
                try {
                    size = dataInputStream.readInt();
                    byte[] data = new byte[size];
                    int len = 0;
                    stateListener.start(size);
                    while (len < size) {
                        len += dataInputStream.read(data, len, size - len);
                        int mProgress = (int) (((float) len / size) * 100);
                        stateListener.loading(mProgress);
                    }
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
                    stateListener.close(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bmp;
            }

            /**
             * 读取图片
             *
             * @return
             */
            public Bitmap readImg() {
                if (dataInputStream == null) return null;
                Bitmap bmp = null;
                int size = 0;
                try {
                    size = dataInputStream.readInt();
                    byte[] data = new byte[size];
                    int len = 0;
                    while (len < size) {
                        len += dataInputStream.read(data, len, size - len);
                    }
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bmp;
            }

            /**
             * 发送图片
             *
             * @param imgPath
             * @return
             */
            public TCP sendImg(String imgFilePath) {
                if (dataOutputStream == null) return this;
                Thread.runJava(new Runnable() {
                    @Override
                    public void run() {
                        FileInputStream fis = null;
                        try {
                            fis = new FileInputStream(imgFilePath);
                            int size = fis.available();
                            byte[] data = new byte[size];
                            fis.read(data);
                            dataOutputStream.writeInt(size);
                            dataOutputStream.write(data);
                            dataOutputStream.flush();
                            fis.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return this;
            }

            /**
             * 读取图片
             *
             * @param saveFilePath 保存读取到的图片文件路径
             * @return
             */
            public boolean readImg(String saveFilePath) {
                if (dataInputStream == null) return false;
                int len = 0;
                try {
                    len = dataInputStream.readInt();
                    byte[] bytes = new byte[(int) len];  //然后在读这个长度的字节到字节数组
                    dataInputStream.readFully(bytes);  //将独到的内容保存为文件到本地
                    File file = new File(saveFilePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(bytes);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }

            /**
             * 发送文件
             *
             * @param path            文件路径
             * @param fileName        文件名称
             * @param onStateListener 状态接口
             * @return
             */
            public boolean sendFile(String path, String fileName, OnStateListener onStateListener) {
                Thread.runJava(new Runnable() {
                    @Override
                    public void run() {
                        onStateListener.start(fileName);
                        printStream.println(fileName);//发送文件名
                        OutputStream outputData = null;
                        try {
                            outputData = socket.getOutputStream();
                            FileInputStream fileInput = new FileInputStream(path + fileName);
                            int size = -1;
                            byte[] buffer = new byte[1024];
//                            GT.log("开始读取...");
                            while (true) {
//                                GT.log("开始read");
                                int i = size = fileInput.read(buffer, 0, 1024);
//                                GT.log("结束read：" + i);
                                if (i == -1) {
                                    GT.log("跳出循环...");
                                    break;
                                }
//                                GT.log("读取中...");
                                onStateListener.loading(size);
//                                GT.log("开始写入...");
                                outputData.write(buffer, 0, size);
//                                GT.log("写入完毕...");
                            }
//                            GT.log("结束读取...");
                            onStateListener.close("ok");
                            outputData.close();
                            fileInput.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                return false;
            }

            /**
             * 读取文件
             *
             * @param saveFilePath 保存文件的路径
             */
            public String readFile(String saveFile, OnStateListener onStateListener) {
                if (saveFile == null) return null;
                String fileName = "";
                try {
                    fileName = bufferedReader.readLine();
                    onStateListener.start(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //获取文件路径
                String filePath = saveFile.substring(0, saveFile.lastIndexOf("/") + 1);
                // 创建文件的存储路径
                File file = new File(filePath);
                if (!file.exists()) {
                    file.mkdirs();
                }

                //将文件存储到本地
                InputStream dataStream = null;
                try {
                    dataStream = socket.getInputStream();
                    FileOutputStream fileOutputStream = new FileOutputStream(saveFile, false);
                    byte[] buffer = new byte[1024];
                    int size = -1;
                    while ((size = dataStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, size);
                        onStateListener.loading(size);
                    }
                    fileOutputStream.close();
                    dataStream.close();
                    onStateListener.close(saveFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }


                return saveFile;
            }


            /**
             * 释放资源
             */
            public void close() {
                //关闭输入输出流
                if (socket != null) {
                    try {
                        socket.shutdownInput();
                        socket.shutdownOutput();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    bufferedReader = null;
                }

                if (printStream != null) {
                    printStream.close();
                    printStream = null;
                }

                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    dataOutputStream = null;
                }

                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    dataInputStream = null;
                }

                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    socket = null;
                }


            }

        }

        public static class UDP {


        }

    }

//============================================= 小工具类 =======================================

    /**
     * data 日期类
     */
    public static class GT_Date {

        /**
         * 使用案例
         * long currentTimeMillis = System.currentTimeMillis();
         * <p>
         * 输出：输入的时间：1567233280386
         * System.out.println("输入的时间：" + currentTimeMillis);
         * <p>
         * currentTimeMillis = 1538364324000L;
         * <p>
         * Lunar lunar = new Lunar(currentTimeMillis);//初始化高级功能
         * 节气: 如果指定的日期有节气则返回当天节气，如果没有则返回 "" 空字符串 不是 null
         * System.out.println("节气:" + lunar.getTermString());
         * <p>
         * 生肖:狗
         * System.out.println("生肖:" + lunar.getAnimalString());
         * <p>
         * 星期：2
         * System.out.println("星期：" + lunar.getDayOfWeek());//星期几(星期日为:1, 星期六为:7)
         * <p>
         * 干支历:戊戌年辛酉月丙寅日
         * System.out.println("干支历:" + lunar.getCyclicalDateString());
         * <p>
         * 农历:戊戌年八月廿二日
         * System.out.println("农历:" + lunar.getLunarDateString());
         * <p>
         * 当前是否为 农历节日:true
         * boolean lFestival = lunar.isLFestival();
         * System.out.println("当前是否为 农历节日:" + lFestival);
         * <p>
         * 农历节日:燃灯佛诞
         * if(lFestival){
         * System.out.println("农历节日:" + lunar.getLFestivalName());
         * }
         * <p>
         * 当前是否为公历节日:true
         * boolean sFestival = lunar.isSFestival();
         * System.out.println("当前是否为公历节日:" + lFestival);
         * if(sFestival){
         * 公历节日:国庆节
         * System.out.println("公历节日:" + lunar.getSFestivalName());
         * }
         * <p>
         * 当前是否为节日:true
         * boolean festival = lunar.isFestival();
         * System.out.println("当前是否为节日:" + festival);
         * <p>
         * 当前是否放假:true
         * boolean holiday = lunar.isHoliday();
         * System.out.println("当前是否放假:" + holiday);
         * <p>
         * <p>
         * Date[] jieqi = Lunar.jieqilist(2019);
         * for (int i = 0; i < Lunar.solarTerm.length; i++) {
         * System.out.print(Lunar.solarTerm[i]);
         *
         * @SuppressWarnings("deprecation") int month = jieqi[i].getMonth();
         * month += 1;
         * System.out.print(month + "月");
         * System.out.println(jieqi[i].getDate());
         * }
         * //对应结果
         * 小寒1月6
         * 大寒1月20
         * 立春2月4
         * 雨水2月19
         * 惊蛰3月6
         * 春分3月21
         * 清明4月5
         * 谷雨4月21
         * 立夏5月6
         * 小满5月22
         * 芒种6月6
         * 夏至6月22
         * 小暑7月8
         * 大暑7月23
         * 立秋8月8
         * 处暑8月24
         * 白露9月8
         * 秋分9月24
         * 寒露10月9
         * 霜降10月24
         * 立冬11月8
         * 小雪11月23
         * 大雪12月8
         * 冬至12月22
         */


        /**
         * 时间与时间戳互转
         *
         * @param timeOrTimestamp 时间或时间戳
         * @param timeFormat      转换格式
         * @return
         * @throws ParseException
         */
        public static String timeToTimestampToTime(String timeOrTimestamp, String timeFormat) throws ParseException {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat);
            Date date;
            try {
                //时间戳转时间
                long timeLong = Long.parseLong(timeOrTimestamp);
                date = new Date(timeLong);
                return simpleDateFormat.format(date);
            } catch (Exception e) {
                try {
                    //时间转时间戳
                    date = simpleDateFormat.parse(timeOrTimestamp);
                    assert date != null;
                    long ts = date.getTime();
                    return String.valueOf(ts);
                } catch (Exception e1) {
                    //如果是因为时间格式有问题，那就返回当前时间并提示
                    err("The date format is wrong");
                    return dateToStamp(GT_Date.getDateTime_CH(), "yyyy-MM-dd HH:mm:ss");
                }
            }
        }

        private Lunar lunar = null;

        /**
         * @return the lunar
         */
        public Lunar getLunar() {
            return lunar;
        }

        /**
         * @param lunar the lunar to set
         */
        public void setLunar(Lunar lunar) {
            this.lunar = lunar;
        }

        /**
         * 获取中国日期
         *
         * @return
         */
        public static String getDateTime_CH() {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            return df.format(new Date());   //获取当时间
        }

        /**
         * 获取美国日期
         *
         * @return
         */
        public static String getDateTime_US() {
            SimpleDateFormat df = new SimpleDateFormat("ss:mm:HH dd-MM-yyyy");//设置日期格式
            return df.format(new Date());   //获取当时间
        }

        /**
         * 日期格式
         *
         * @param dateTimeFormat
         * @return
         */
        public static String getDateTime(String dateTimeFormat) {
            SimpleDateFormat df = new SimpleDateFormat(dateTimeFormat);//设置日期格式
            return df.format(new Date());   //获取当时间
        }

        /**
         * 初始化时间 基础功能
         */
        public GT_Date() {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            time = df.format(new Date());   //获取当时间
            times = time.split(" ");        //分割时间 年月日  时分秒 数组
            ymd = times[0].split("-");    //分割年月日 数组
            hms = times[1].split(":");    //分割时分秒 数组
        }

        /**
         * 初始化时间 高级功能
         */
        public GT_Date(long timestamp) {

            //初始化基本的时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            time = df.format(new Date());   //获取当时间
            times = time.split(" ");        //分割时间 年月日  时分秒 数组
            ymd = times[0].split("-");    //分割年月日 数组
            hms = times[1].split(":");    //分割时分秒 数组

            //初始化高级功能
            lunar = new Lunar(timestamp);

        }

        /**
         * 将多少秒转换成 时分秒 如：100秒 = 1分钟40秒
         *
         * @param seconds
         * @return
         */
        public static String secondsToTime(int seconds) {
            int h = seconds / 3600; // 小时
            int m = (seconds % 3600) / 60; // 分钟
            int s = (seconds % 3600) % 60; // 秒
            if (h > 0) {
                return h + "小时" + m + "分钟" + s + "秒";
            }
            if (m > 0) {
                return m + "分钟" + s + "秒";
            }
            return s + "秒";
        }

        /**
         * ***************日历工具的基础功能*******************
         */

        private static String time;        //定义返回的 时间整体字符串
        private static String[] times;     //定义分割后产生的 年月日 / 时分秒 数组
        private static String[] ymd;       //定义分割后产生的 年月日 数组
        private static String[] hms;       //定义分割后产生的 时分秒 数组

        /**
         * 获取当前星期
         *
         * @return
         */
        public static String getWeekOfDateString() {
            String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0)
                w = 0;
            return weekDays[w];
        }


        /**
         * 获取当前星期
         *
         * @return
         */
        public static int getWeekOfDateInt() {
            int[] weekDays = {0, 1, 2, 3, 4, 5, 6};
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0)
                w = 0;
            return weekDays[w];
        }


        /**
         * 获取当前 年月日
         *
         * @return
         */
        public String getYearMonthDay() {
            return times[0];
        }

        /**
         * 获取年
         *
         * @return
         */
        public String getYear() {
            return ymd[0];
        }

        /**
         * 获取月
         *
         * @return
         */
        public String getMonth() {
            return ymd[1];
        }

        /**
         * 获取日
         *
         * @return
         */
        public String getDay() {
            return ymd[2];
        }

        /**
         * /获取当前 时分秒
         *
         * @return
         */
        public String getHourMinuteSecond() {
            return times[1];
        }

        /**
         * 获取时
         *
         * @return
         */
        public String getHour() {
            return hms[0];
        }

        /**
         * 获取分
         *
         * @return
         */
        public String getMinute() {
            return hms[1];
        }

        /**
         * 获取秒
         *
         * @return
         */
        public String getSecond() {
            return hms[2];
        }

        /**
         * 时间戳转 时间 YYYY-MM-DD 00:00:00
         *
         * @param dataTime
         * @return
         */
        public static String toTime(String dataTime, String timeFormat) {
            SimpleDateFormat formatter = new SimpleDateFormat(timeFormat, Locale.getDefault());
            long myTime = Long.parseLong(dataTime);
            long lt = new Long(myTime * 1000);//返回来的时间戳1476929029是毫秒，这里要乘1000才是正确的北京时间
            Date date = new Date(lt);
            String time = formatter.format(date);
            return time;
        }

        /**
         * 时间转时间戳
         *
         * @param time 如：2021-07-23 16:36:12  格式必须为这样，单数也要前加 0 可以这样：2021-07-23 00:00:00
         * @return 返回时间戳
         */
        public static long toTimestamp(String time) {
            long times = 0;
            try {
                times = (int) ((Timestamp.valueOf(time).getTime()) / 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return times;
        }

        /**
         * 时分秒
         *
         * @param dataTime
         * @return
         */
        public static String toTime_hms(String dataTime) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            long myTime = Long.parseLong(dataTime);
            long lt = new Long(myTime * 1000);//返回来的时间戳1476929029是毫秒，这里要乘1000才是正确的北京时间
            Date date = new Date(lt);
            String time = formatter.format(date);
            time = time.substring(time.length() - 8, time.length());
            return time;
        }

        /**
         * 离现在过去几小时
         *
         * @param dataTime
         * @return
         */
        public String toPastTime(String dataTime) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            long myTime = Long.parseLong(dataTime);
            long lt = new Long(myTime * 1000);//返回来的时间戳1476929029是毫秒，这里要乘1000才是正确的北京时间
            Date date = new Date(lt);
            String time = formatter.format(date);
            time = time.substring(time.length() - 8, time.length());
            time = time.substring(0, 2);
            String hour = getHour();
            int timeInt = Integer.parseInt(time);
            int hourInt = Integer.parseInt(hour);
            int showTime;

            if (hourInt < timeInt) {
                timeInt = 24 - timeInt; //一天减去 当时发送的时间
                showTime = hourInt + timeInt;
            } else {
                showTime = hourInt - timeInt;
            }

            //判断当前过去的时间是否大于0 最小为 1小时前
            if (showTime > 0) {
                time = showTime + "小时前";
            } else {//否则 进行分钟判断

                time = formatter.format(date);
                time = time.substring(time.length() - 8, time.length());
                time = time.substring(3, 5);

                timeInt = Integer.parseInt(time);
                hourInt = Integer.parseInt(getMinute());
                showTime = hourInt - timeInt;

                if (showTime > 0) {
                    time = showTime + "分钟前";
                } else {
                    time = "刚刚";
                }
            }

            return time;
        }

        /**
         * 时间戳转 年月日
         *
         * @param dataTime
         * @return
         */
        public static String toYearMonthDay(String dataTime) {
            SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd", Locale.getDefault());
            long myTime = Long.parseLong(dataTime);
            long lt = new Long(myTime * 1000);//返回来的时间戳1476929029是毫秒，这里要乘1000才是正确的北京时间
            Date date = new Date(lt);
            String time = formatter.format(date);
            return time;
        }

        /**
         * 时间戳转时间
         *
         * @param dataTime
         * @return
         */
        public static String stampToDate(String s, String timeFormat) throws ParseException {
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat);
            long lt = new Long(s);
            Date date = new Date(lt);
            res = simpleDateFormat.format(date);
            return res;
        }

        public static String dateToStamp(String s, String timeFormat) throws ParseException {
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat);
            Date date = simpleDateFormat.parse(s);
            long ts = date.getTime();
            res = String.valueOf(ts);
            return res;
        }

        /**
         * ***********************日历的高级功能***********************
         */

        public static class Lunar {

            /**
             * 获得某天前个节气日期差
             *
             * @return 日期数
             */
            public static long getbeforesolarTerm(int year, Date date) {
                List<Date> jieqi = Alljieqi(year);
                int[] jieqibeforeafter = getnearsolarTerm(year, date);
                return MyDate.DateDays(date, jieqi.get(jieqibeforeafter[0]));
            }

            /**
             * 获得某天后个节气日期差
             *
             * @return 日期数
             */
            public static long getaftersolarTerm(int year, Date date) {
                List<Date> jieqi = Alljieqi(year);
                int[] jieqibeforeafter = getnearsolarTerm(year, date);
                return MyDate.DateDays(date, jieqi.get(jieqibeforeafter[1]));

            }

            public static List<Date> Alljieqi(int year) {

                List<Date> jieqi = new ArrayList<Date>();
                Date[] temp;
                temp = jieqilist(year - 1);
                jieqi.addAll(Arrays.asList(temp));
                temp = jieqilist(year);
                jieqi.addAll(Arrays.asList(temp));
                temp = jieqilist(year + 1);
                jieqi.addAll(Arrays.asList(temp));
                return jieqi;
            }


            /**
             * 获得某天前后两个节气序号
             *
             * @return
             */
            public static int[] getnearsolarTerm(int year, Date date) {
                List<Date> jieqi = Alljieqi(year);

                int[] returnValue = new int[2];
                for (int i = 0; i < jieqi.size(); i++) {
                    if (date.getTime() > jieqi.get(i).getTime()) {
                        continue;
                    }
                    if (i % 2 == 0) {//只管气
                        returnValue[0] = i - 2;
                        returnValue[1] = i;
                    } else {
                        returnValue[0] = i - 1;
                        returnValue[1] = i + 1;

                    }
                    break;
                }

                return returnValue;
            }


            /**
             * 获得某年中所有节气Date
             *
             * @return
             */
            public static Date[] jieqilist(int year) {
                Date[] returnvalue = new Date[solarTerm.length];

                for (int i = 0; i < solarTerm.length; i++) {

                    Date t = getSolarTermCalendar(year, i);
                    returnvalue[i] = t;

                }
                return returnvalue;
            }


            private final static int[] lunarInfo = {
                    0x4bd8, 0x4ae0, 0xa570, 0x54d5, 0xd260, 0xd950, 0x5554, 0x56af,
                    0x9ad0, 0x55d2, 0x4ae0, 0xa5b6, 0xa4d0, 0xd250, 0xd295, 0xb54f,
                    0xd6a0, 0xada2, 0x95b0, 0x4977, 0x497f, 0xa4b0, 0xb4b5, 0x6a50,
                    0x6d40, 0xab54, 0x2b6f, 0x9570, 0x52f2, 0x4970, 0x6566, 0xd4a0,
                    0xea50, 0x6a95, 0x5adf, 0x2b60, 0x86e3, 0x92ef, 0xc8d7, 0xc95f,
                    0xd4a0, 0xd8a6, 0xb55f, 0x56a0, 0xa5b4, 0x25df, 0x92d0, 0xd2b2,
                    0xa950, 0xb557, 0x6ca0, 0xb550, 0x5355, 0x4daf, 0xa5b0, 0x4573,
                    0x52bf, 0xa9a8, 0xe950, 0x6aa0, 0xaea6, 0xab50, 0x4b60, 0xaae4,
                    0xa570, 0x5260, 0xf263, 0xd950, 0x5b57, 0x56a0, 0x96d0, 0x4dd5,
                    0x4ad0, 0xa4d0, 0xd4d4, 0xd250, 0xd558, 0xb540, 0xb6a0, 0x95a6,
                    0x95bf, 0x49b0, 0xa974, 0xa4b0, 0xb27a, 0x6a50, 0x6d40, 0xaf46,
                    0xab60, 0x9570, 0x4af5, 0x4970, 0x64b0, 0x74a3, 0xea50, 0x6b58,
                    0x5ac0, 0xab60, 0x96d5, 0x92e0, 0xc960, 0xd954, 0xd4a0, 0xda50,
                    0x7552, 0x56a0, 0xabb7, 0x25d0, 0x92d0, 0xcab5, 0xa950, 0xb4a0,
                    0xbaa4, 0xad50, 0x55d9, 0x4ba0, 0xa5b0, 0x5176, 0x52bf, 0xa930,
                    0x7954, 0x6aa0, 0xad50, 0x5b52, 0x4b60, 0xa6e6, 0xa4e0, 0xd260,
                    0xea65, 0xd530, 0x5aa0, 0x76a3, 0x96d0, 0x4afb, 0x4ad0, 0xa4d0,
                    0xd0b6, 0xd25f, 0xd520, 0xdd45, 0xb5a0, 0x56d0, 0x55b2, 0x49b0,
                    0xa577, 0xa4b0, 0xaa50, 0xb255, 0x6d2f, 0xada0, 0x4b63, 0x937f,
                    0x49f8, 0x4970, 0x64b0, 0x68a6, 0xea5f, 0x6b20, 0xa6c4, 0xaaef,
                    0x92e0, 0xd2e3, 0xc960, 0xd557, 0xd4a0, 0xda50, 0x5d55, 0x56a0,
                    0xa6d0, 0x55d4, 0x52d0, 0xa9b8, 0xa950, 0xb4a0, 0xb6a6, 0xad50,
                    0x55a0, 0xaba4, 0xa5b0, 0x52b0, 0xb273, 0x6930, 0x7337, 0x6aa0,
                    0xad50, 0x4b55, 0x4b6f, 0xa570, 0x54e4, 0xd260, 0xe968, 0xd520,
                    0xdaa0, 0x6aa6, 0x56df, 0x4ae0, 0xa9d4, 0xa4d0, 0xd150, 0xf252, 0xd520
            };


            private final static int[] solarTermInfo = {
                    0, 21208, 42467, 63836, 85337, 107014, 128867, 150921,
                    173149, 195551, 218072, 240693, 263343, 285989, 308563, 331033,
                    353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758
            };
            public final static String[] Tianan = {
                    "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"
            };
            public final static String[] Deqi = {
                    "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"
            };
            public final static String[] Animals = {
                    "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"
            };
            public final static String[] solarTerm = {
                    "小寒", "大寒", "立春", "雨水", "惊蛰", "春分",
                    "清明", "谷雨", "立夏", "小满", "芒种", "夏至",
                    "小暑", "大暑", "立秋", "处暑", "白露", "秋分",
                    "寒露", "霜降", "立冬", "小雪", "大雪", "冬至"
            };
            public final static String[] lunarString1 = {
                    "零", "一", "二", "三", "四", "五", "六", "七", "八", "九"
            };
            public final static String[] lunarString2 = {
                    "初", "十", "廿", "卅", "正", "腊", "冬", "闰"
            };
            /**
             * 国历节日 *表示放假日
             */
            private final static String[] sFtv = {
                    "0101*元旦", "0214 情人节", "0308 妇女节", "0312 植树节",
                    "0315 消费者权益日", "0401 愚人节", "0501*劳动节", "0504 青年节",
                    "0509 郝维节", "0512 护士节", "0601 儿童节", "0701 建党节 香港回归纪念",
                    "0801 建军节", "0808 父亲节", "0816 燕衔泥节", "0909 毛泽东逝世纪念",
                    "0910 教师节", "0928 孔子诞辰", "1001*国庆节", "1006 老人节",
                    "1024 联合国日", "1111 光棍节", "1112 孙中山诞辰纪念", "1220 澳门回归纪念",
                    "1225 圣诞节", "1226 毛泽东诞辰纪念"
            };
            /**
             * 农历节日 *表示放假日
             */
            private final static String[] lFtv = {
                    "0101*春节、弥勒佛诞", "0106 定光佛诞", "0115 元宵节",
                    "0208 释迦牟尼佛出家", "0215 释迦牟尼佛涅槃", "0209 海空上师诞",
                    "0219 观世音菩萨诞", "0221 普贤菩萨诞", "0316 准提菩萨诞",
                    "0404 文殊菩萨诞", "0408 释迦牟尼佛诞", "0415 佛吉祥日——释迦牟尼佛诞生、成道、涅槃三期同一庆(即南传佛教国家的卫塞节)",
                    "0505 端午节", "0513 伽蓝菩萨诞", "0603 护法韦驮尊天菩萨诞",
                    "0619 观世音菩萨成道——此日放生、念佛，功德殊胜",
                    "0707 七夕情人节", "0713 大势至菩萨诞", "0715 中元节",
                    "0724 龙树菩萨诞", "0730 地藏菩萨诞", "0815 中秋节",
                    "0822 燃灯佛诞", "0909 重阳节", "0919 观世音菩萨出家纪念日",
                    "0930 药师琉璃光如来诞", "1005 达摩祖师诞", "1107 阿弥陀佛诞",
                    "1208 释迦如来成道日，腊八节", "1224 小年",
                    "1229 华严菩萨诞", "0100*除夕"
            };
            /**
             * 某月的第几个星期几
             */
            private static String[] wFtv = {
                    "0520 母亲节", "0716 合作节", "0730 被奴役国家周"
            };

            private static int toInt(String str) {
                try {
                    return Integer.parseInt(str);
                } catch (Exception e) {
                    return -1;
                }
            }

            private final static Pattern sFreg = Pattern.compile("^(\\d{2})(\\d{2})([\\s\\*])(.+)$");
            private final static Pattern wFreg = Pattern.compile("^(\\d{2})(\\d)(\\d)([\\s\\*])(.+)$");

            private synchronized void findFestival() {
                System.out.println("进入节日获取");
                int sM = this.getSolarMonth();
                int sD = this.getSolarDay();
                int lM = this.getLunarMonth();
                int lD = this.getLunarDay();
                int sy = this.getSolarYear();
                Matcher m;
                for (int i = 0; i < Lunar.sFtv.length; i++) {
                    m = Lunar.sFreg.matcher(Lunar.sFtv[i]);

                    if (m.find()) {
                        if (sM == Lunar.toInt(m.group(1)) && sD == Lunar.toInt(m.group(2))) {
                            this.isSFestival = true;
                            this.sFestivalName = m.group(4);
                            if ("*".equals(m.group(3))) {
                                this.isHoliday = true;
                            }
                            break;
                        }
                    }
                }
                for (int i = 0; i < Lunar.lFtv.length; i++) {
                    m = Lunar.sFreg.matcher(Lunar.lFtv[i]);
                    if (m.find()) {
                        if (lM == Lunar.toInt(m.group(1)) && lD == Lunar.toInt(m.group(2))) {
                            this.isLFestival = true;
                            this.lFestivalName = m.group(4);
                            if ("*".equals(m.group(3))) {
                                this.isHoliday = true;
                            }
                            break;
                        }
                    }
                }
                // 月周节日
                int w, d;
                for (int i = 0; i < Lunar.wFtv.length; i++) {
                    m = Lunar.wFreg.matcher(Lunar.wFtv[i]);
                    if (m.find()) {
                        if (this.getSolarMonth() == Lunar.toInt(m.group(1))) {
                            w = Lunar.toInt(m.group(2));
                            d = Lunar.toInt(m.group(3));
                            if (this.solar.get(Calendar.WEEK_OF_MONTH) == w
                                    && this.solar.get(Calendar.DAY_OF_WEEK) == d) {
                                this.isSFestival = true;
                                this.sFestivalName += "|" + m.group(5);
                                if ("*".equals(m.group(4))) {
                                    this.isHoliday = true;
                                }
                            }
                        }
                    }
                }
                if (sy > 1874 && sy < 1909) {
                    this.description = "光绪" + (((sy - 1874) == 1) ? "元" : "" + (sy - 1874));
                }
                if (sy > 1908 && sy < 1912) {
                    this.description = "宣统" + (((sy - 1908) == 1) ? "元" : String.valueOf(sy - 1908));
                }
                if (sy > 1911 && sy < 1950) {
                    this.description = "民国" + (((sy - 1911) == 1) ? "元" : String.valueOf(sy - 1911));
                }
                if (sy > 1949) {
                    this.description = "共和国" + (((sy - 1949) == 1) ? "元" : String.valueOf(sy - 1949));
                }
                this.description += "年";
                this.sFestivalName = this.sFestivalName.replaceFirst("^\\|", "");
                this.isFinded = true;
            }

            private boolean isFinded = false;
            private boolean isSFestival = false;
            private boolean isLFestival = false;
            private String sFestivalName = "";
            private String lFestivalName = "";
            private String description = "";
            private boolean isHoliday = false;

            /**
             * 返回农历年闰月月份
             *
             * @param lunarYear 指定农历年份(数字)
             * @return 该农历年闰月的月份(数字, 没闰返回0)
             */
            private static int getLunarLeapMonth(int lunarYear) {
                // 数据表中,每个农历年用16bit来表示,
                // 前12bit分别表示12个月份的大小月,最后4bit表示闰月
                // 若4bit全为1或全为0,表示没闰, 否则4bit的值为闰月月份
                int leapMonth = Lunar.lunarInfo[lunarYear - 1900] & 0xf;
                leapMonth = (leapMonth == 0xf ? 0 : leapMonth);
                return leapMonth;
            }


            /**
             * 返回农历年闰月的天数
             *
             * @param lunarYear 指定农历年份(数字)
             * @return 该农历年闰月的天数(数字)
             */
            private static int getLunarLeapDays(int lunarYear) {
                // 下一年最后4bit为1111,返回30(大月)
                // 下一年最后4bit不为1111,返回29(小月)
                // 若该年没有闰月,返回0
                return Lunar.getLunarLeapMonth(lunarYear) > 0 ? ((Lunar.lunarInfo[lunarYear - 1899] & 0xf) == 0xf ? 30
                        : 29)
                        : 0;
            }

            /**
             * 返回农历年的总天数
             *
             * @param lunarYear 指定农历年份(数字)
             * @return 该农历年的总天数(数字)
             */
            private static int getLunarYearDays(int lunarYear) {
                // 按小月计算,农历年最少有12 * 29 = 348天
                int daysInLunarYear = 348;
                // 数据表中,每个农历年用16bit来表示,
                // 前12bit分别表示12个月份的大小月,最后4bit表示闰月
                // 每个大月累加一天
                for (int i = 0x8000; i > 0x8; i >>= 1) {
                    daysInLunarYear += ((Lunar.lunarInfo[lunarYear - 1900] & i) != 0) ? 1
                            : 0;
                }
                // 加上闰月天数
                daysInLunarYear += Lunar.getLunarLeapDays(lunarYear);

                return daysInLunarYear;
            }

            /**
             * 返回农历年正常月份的总天数
             *
             * @param lunarYear  指定农历年份(数字)
             * @param lunarMonth 指定农历月份(数字)
             * @return 该农历年闰月的月份(数字, 没闰返回0)
             */
            private static int getLunarMonthDays(int lunarYear, int lunarMonth) {
                // 数据表中,每个农历年用16bit来表示,
                // 前12bit分别表示12个月份的大小月,最后4bit表示闰月
                int daysInLunarMonth = ((Lunar.lunarInfo[lunarYear - 1900] & (0x10000 >> lunarMonth)) != 0) ? 30
                        : 29;
                return daysInLunarMonth;
            }


            /**
             * 取 Date 对象中用全球标准时间 (UTC) 表示的日期
             *
             * @param date 指定日期
             * @return UTC 全球标准时间 (UTC) 表示的日期
             */
            public static synchronized int getUTCDay(Date date) {
                Lunar.makeUTCCalendar();
                synchronized (utcCal) {
                    utcCal.clear();
                    utcCal.setTimeInMillis(date.getTime());
                    return utcCal.get(Calendar.DAY_OF_MONTH);
                }
            }

            private static GregorianCalendar utcCal = null;

            private static synchronized void makeUTCCalendar() {
                if (Lunar.utcCal == null) {
                    Lunar.utcCal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                }
            }

            /**
             * 返回全球标准时间 (UTC) (或 GMT) 的 1970 年 1 月 1 日到所指定日期之间所间隔的毫秒数。
             *
             * @param y   指定年份
             * @param m   指定月份
             * @param d   指定日期
             * @param h   指定小时
             * @param min 指定分钟
             * @param sec 指定秒数
             * @return 全球标准时间 (UTC) (或 GMT) 的 1970 年 1 月 1 日到所指定日期之间所间隔的毫秒数
             */
            public static synchronized long UTC(int y, int m, int d, int h, int min, int sec) {
                Lunar.makeUTCCalendar();
                synchronized (utcCal) {
                    utcCal.clear();
                    utcCal.set(y, m, d, h, min, sec);
                    return utcCal.getTimeInMillis();
                }
            }

            /**
             * 返回公历年节气的日期
             *
             * @param solarYear 指定公历年份(数字)
             * @param index     指定节气序号(数字,0从小寒算起)
             * @return 日期(数字, 所在月份的第几天)
             */
            private static int getSolarTermDay(int solarYear, int index) {

                return Lunar.getUTCDay(getSolarTermCalendar(solarYear, index));
            }

            /**
             * 返回公历年节气的日期
             *
             * @param solarYear 指定公历年份(数字)
             * @param index     指定节气序号(数字,0从小寒算起)
             * @return 日期(数字, 所在月份的第几天)
             */
            public static Date getSolarTermCalendar(int solarYear, int index) {
                long l = (long) 31556925974.7 * (solarYear - 1900)
                        + solarTermInfo[index] * 60000L;
                l = l + Lunar.UTC(1900, 0, 6, 2, 5, 0);
                return new Date(l);
            }

            private Calendar solar;
            private int lunarYear;
            private int lunarMonth;
            private int lunarDay;
            private boolean isLeap;
            private boolean isLeapYear;
            private int solarYear;
            private int solarMonth;
            private int solarDay;
            private int cyclicalYear = 0;
            private int cyclicalMonth = 0;
            private int cyclicalDay = 0;
            private int maxDayInMonth = 29;

            /**
             * 通过 Date 对象构建农历信息
             *
             * @param date 指定日期对象
             */
            public Lunar(Date date) {
                if (date == null) {
                    date = new Date();
                }
                this.init(date.getTime());
            }

            /**
             * 通过 TimeInMillis 构建农历信息
             *
             * @param TimeInMillis
             */
            public Lunar(long TimeInMillis) {
                this.init(TimeInMillis);
            }

            private void init(long TimeInMillis) {
                this.solar = Calendar.getInstance();
                this.solar.setTimeInMillis(TimeInMillis);
                Calendar baseDate = new GregorianCalendar(1900, 0, 31);
                long offset = (TimeInMillis - baseDate.getTimeInMillis()) / 86400000;
                // 按农历年递减每年的农历天数，确定农历年份
                this.lunarYear = 1900;
                int daysInLunarYear = Lunar.getLunarYearDays(this.lunarYear);
                while (this.lunarYear < 2100 && offset >= daysInLunarYear) {
                    offset -= daysInLunarYear;
                    daysInLunarYear = Lunar.getLunarYearDays(++this.lunarYear);
                }
                // 农历年数字

                // 按农历月递减每月的农历天数，确定农历月份
                int lunarMonth = 1;
                // 所在农历年闰哪个月,若没有返回0
                int leapMonth = Lunar.getLunarLeapMonth(this.lunarYear);
                // 是否闰年
                this.isLeapYear = leapMonth > 0;
                // 闰月是否递减
                boolean leapDec = false;
                boolean isLeap = false;
                int daysInLunarMonth = 0;
                while (lunarMonth < 13 && offset > 0) {
                    if (isLeap && leapDec) { // 如果是闰年,并且是闰月
                        // 所在农历年闰月的天数
                        daysInLunarMonth = Lunar.getLunarLeapDays(this.lunarYear);
                        leapDec = false;
                    } else {
                        // 所在农历年指定月的天数
                        daysInLunarMonth = Lunar.getLunarMonthDays(this.lunarYear, lunarMonth);
                    }
                    if (offset < daysInLunarMonth) {
                        break;
                    }
                    offset -= daysInLunarMonth;

                    if (leapMonth == lunarMonth && isLeap == false) {
                        // 下个月是闰月
                        leapDec = true;
                        isLeap = true;
                    } else {
                        // 月份递增
                        lunarMonth++;
                    }
                }
                this.maxDayInMonth = daysInLunarMonth;
                // 农历月数字
                this.lunarMonth = lunarMonth;
                // 是否闰月
                this.isLeap = (lunarMonth == leapMonth && isLeap);
                // 农历日数字
                this.lunarDay = (int) offset + 1;
                // 取得干支历
                this.getCyclicalData();
            }

            /**
             * 取干支历 不是历年，历月干支，而是中国的从立春节气开始的节月，是中国的太阳十二宫，阳历的。
             */
            private void getCyclicalData() {
                this.solarYear = this.solar.get(Calendar.YEAR);
                this.solarMonth = this.solar.get(Calendar.MONTH);
                this.solarDay = this.solar.get(Calendar.DAY_OF_MONTH);
                // 干支历
                int cyclicalYear = 0;
                int cyclicalMonth = 0;
                int cyclicalDay = 0;

                // 干支年 1900年立春後为庚子年(60进制36)
                int term2 = Lunar.getSolarTermDay(solarYear, 2); // 立春日期
                // 依节气调整二月分的年柱, 以立春为界
                if (solarMonth < 1 || (solarMonth == 1 && solarDay < term2)) {
                    cyclicalYear = (solarYear - 1900 + 36 - 1) % 60;
                } else {
                    cyclicalYear = (solarYear - 1900 + 36) % 60;
                }

                // 干支月 1900年1月小寒以前为 丙子月(60进制12)
                int firstNode = Lunar.getSolarTermDay(solarYear, solarMonth * 2); // 传回当月「节」为几日开始
                // 依节气月柱, 以「节」为界
                if (solarDay < firstNode) {
                    cyclicalMonth = ((solarYear - 1900) * 12 + solarMonth + 12) % 60;
                } else {
                    cyclicalMonth = ((solarYear - 1900) * 12 + solarMonth + 13) % 60;
                }

                // 当月一日与 1900/1/1 相差天数
                // 1900/1/1与 1970/1/1 相差25567日, 1900/1/1 日柱为甲戌日(60进制10)
                cyclicalDay = (int) (Lunar.UTC(solarYear, solarMonth, solarDay, 0, 0, 0) / 86400000 + 25567 + 10) % 60;
                this.cyclicalYear = cyclicalYear;
                this.cyclicalMonth = cyclicalMonth;
                this.cyclicalDay = cyclicalDay;
            }

            /**
             * 取农历年生肖
             *
             * @return 农历年生肖(例 : 龙)
             */
            public String getAnimalString() {
                return Lunar.Animals[(this.lunarYear - 4) % 12];
            }

            /**
             * 返回公历日期的节气字符串
             *
             * @return 二十四节气字符串, 若不是节气日, 返回空串(例 : 冬至)
             */
            public String getTermString() {
                // 二十四节气
                String termString = "";
                if (Lunar.getSolarTermDay(solarYear, solarMonth * 2) == solarDay) {
                    termString = Lunar.solarTerm[solarMonth * 2];
                } else if (Lunar.getSolarTermDay(solarYear, solarMonth * 2 + 1) == solarDay) {
                    termString = Lunar.solarTerm[solarMonth * 2 + 1];
                }
                return termString;
            }

            /**
             * 取得干支历字符串
             *
             * @return 干支历字符串(例 : 甲子年甲子月甲子日)
             */
            public String getCyclicalDateString() {
                return this.getCyclicaYear() + "年" + this.getCyclicaMonth() + "月"
                        + this.getCyclicaDay() + "日";
            }

            /**
             * 年份天干
             *
             * @return 年份天干
             */
            public int getTiananY() {
                return Lunar.getTianan(this.cyclicalYear);
            }

            /**
             * 月份天干
             *
             * @return 月份天干
             */
            public int getTiananM() {
                return Lunar.getTianan(this.cyclicalMonth);
            }

            /**
             * 日期天干
             *
             * @return 日期天干
             */
            public int getTiananD() {
                return Lunar.getTianan(this.cyclicalDay);
            }

            /**
             * 年份地支
             *
             * @return 年分地支
             */
            public int getDeqiY() {
                return Lunar.getDeqi(this.cyclicalYear);
            }

            /**
             * 月份地支
             *
             * @return 月份地支
             */
            public int getDeqiM() {
                return Lunar.getDeqi(this.cyclicalMonth);
            }

            /**
             * 日期地支
             *
             * @return 日期地支
             */
            public int getDeqiD() {
                return Lunar.getDeqi(this.cyclicalDay);
            }

            /**
             * 取得干支年字符串
             *
             * @return 干支年字符串
             */
            public String getCyclicaYear() {
                return Lunar.getCyclicalString(this.cyclicalYear);
            }

            /**
             * 取得干支月字符串
             *
             * @return 干支月字符串
             */
            public String getCyclicaMonth() {
                return Lunar.getCyclicalString(this.cyclicalMonth);
            }

            /**
             * 取得干支日字符串
             *
             * @return 干支日字符串
             */
            public String getCyclicaDay() {
                return Lunar.getCyclicalString(this.cyclicalDay);
            }

            /**
             * 返回农历日期字符串
             *
             * @return 农历日期字符串
             */
            public String getLunarDayString() {
                return Lunar.getLunarDayString(this.lunarDay);
            }

            /**
             * 返回农历日期字符串
             *
             * @return 农历日期字符串
             */
            public String getLunarMonthString() {
                return (this.isLeap() ? "闰" : "") + Lunar.getLunarMonthString(this.lunarMonth);
            }

            /**
             * 返回农历日期字符串
             *
             * @return 农历日期字符串
             */
            public String getLunarYearString() {
                return Lunar.getLunarYearString(this.lunarYear);
            }

            /**
             * 返回农历表示字符串
             *
             * @return 农历字符串(例 : 甲子年正月初三)
             */
            public String getLunarDateString() {
                return this.getLunarYearString() + "年"
                        + this.getLunarMonthString() + "月"
                        + this.getLunarDayString() + "日";
            }

            /**
             * 农历年是否是闰月
             *
             * @return 农历年是否是闰月
             */
            public boolean isLeap() {
                return isLeap;
            }

            /**
             * 农历年是否是闰年
             *
             * @return 农历年是否是闰年
             */
            public boolean isLeapYear() {
                return isLeapYear;
            }

            /**
             * 当前农历月是否是大月
             *
             * @return 当前农历月是大月
             */
            public boolean isBigMonth() {
                return this.getMaxDayInMonth() > 29;
            }

            /**
             * 当前农历月有多少天
             *
             * @return 当前农历月有多少天
             */
            public int getMaxDayInMonth() {
                return this.maxDayInMonth;
            }

            /**
             * 农历日期
             *
             * @return 农历日期
             */
            public int getLunarDay() {
                return lunarDay;
            }

            /**
             * 农历月份
             *
             * @return 农历月份
             */
            public int getLunarMonth() {
                return lunarMonth;
            }

            /**
             * 农历年份
             *
             * @return 农历年份
             */
            public int getLunarYear() {
                return lunarYear;
            }

            /**
             * 公历日期
             *
             * @return 公历日期
             */
            public int getSolarDay() {
                return solarDay;
            }

            /**
             * 公历月份
             *
             * @return 公历月份 (不是从0算起)
             */
            public int getSolarMonth() {
                return solarMonth + 1;
            }

            /**
             * 公历年份
             *
             * @return 公历年份
             */
            public int getSolarYear() {
                return solarYear;
            }

            /**
             * 星期几
             *
             * @return 星期几(星期日为 : 1, 星期六为 : 7)
             */
            public int getDayOfWeek() {
                return this.solar.get(Calendar.DAY_OF_WEEK);
            }

            /**
             * 黑色星期五
             *
             * @return 是否黑色星期五
             */
            public boolean isBlackFriday() {
                return (this.getSolarDay() == 13 && this.solar.get(Calendar.DAY_OF_WEEK) == 6);
            }

            /**
             * 是否是今日
             *
             * @return 是否是今日
             */
            public boolean isToday() {
                Calendar clr = Calendar.getInstance();
                return clr.get(Calendar.YEAR) == this.solarYear
                        && clr.get(Calendar.MONTH) == this.solarMonth
                        && clr.get(Calendar.DAY_OF_MONTH) == this.solarDay;
            }

            /**
             * 取得公历节日名称
             *
             * @return 公历节日名称, 如果不是节日返回空串
             */
            public String getSFestivalName() {
                return this.sFestivalName;
            }

            /**
             * 取得农历节日名称
             *
             * @return 农历节日名称, 如果不是节日返回空串
             */
            public String getLFestivalName() {
                return this.lFestivalName;
            }

            /**
             * 是否是农历节日
             *
             * @return 是否是农历节日
             */
            public boolean isLFestival() {
                if (!this.isFinded) {
                    this.findFestival();
                }
                return this.isLFestival;
            }

            /**
             * 是否是公历节日
             *
             * @return 是否是公历节日
             */
            public boolean isSFestival() {
                if (!this.isFinded) {
                    this.findFestival();
                }
                return this.isSFestival;
            }

            /**
             * 是否是节日
             *
             * @return 是否是节日
             */
            public boolean isFestival() {
                return this.isSFestival() || this.isLFestival();
            }

            /**
             * 是否是放假日
             *
             * @return 是否是放假日
             */
            public boolean isHoliday() {
                if (!this.isFinded) {
                    this.findFestival();
                }
                return this.isHoliday;
            }

            /**
             * 其它日期说明
             *
             * @return 日期说明(如 : 民国2年)
             */
            public String getDescription() {
                if (!this.isFinded) {
                    this.findFestival();
                }
                return this.description;
            }

            /**
             * 干支字符串
             *
             * @param cyclicalNumber 指定干支位置(数字,0为甲子)
             * @return 干支字符串
             */
            private static String getCyclicalString(int cyclicalNumber) {
                return Lunar.Tianan[Lunar.getTianan(cyclicalNumber)] + Lunar.Deqi[Lunar.getDeqi(cyclicalNumber)];
            }

            /**
             * 获得地支
             *
             * @param cyclicalNumber
             * @return 地支 (数字)
             */
            private static int getDeqi(int cyclicalNumber) {
                return cyclicalNumber % 12;
            }

            /**
             * 获得天干
             *
             * @param cyclicalNumber
             * @return 天干 (数字)
             */
            private static int getTianan(int cyclicalNumber) {
                return cyclicalNumber % 10;
            }

            /**
             * 返回指定数字的农历年份表示字符串
             *
             * @param lunarYear 农历年份(数字,0为甲子)
             * @return 农历年份字符串
             */
            private static String getLunarYearString(int lunarYear) {
                return Lunar.getCyclicalString(lunarYear - 1900 + 36);
            }

            /**
             * 返回指定数字的农历月份表示字符串
             *
             * @param lunarMonth 农历月份(数字)
             * @return 农历月份字符串 (例:正)
             */
            private static String getLunarMonthString(int lunarMonth) {
                String lunarMonthString = "";
                if (lunarMonth == 1) {
                    lunarMonthString = Lunar.lunarString2[4];
                } else {
                    if (lunarMonth > 9) {
                        lunarMonthString += Lunar.lunarString2[1];
                    }
                    if (lunarMonth % 10 > 0) {
                        lunarMonthString += Lunar.lunarString1[lunarMonth % 10];
                    }
                }
                return lunarMonthString;
            }

            /**
             * 返回指定数字的农历日表示字符串
             *
             * @param lunarDay 农历日(数字)
             * @return 农历日字符串 (例: 廿一)
             */
            private static String getLunarDayString(int lunarDay) {
                if (lunarDay < 1 || lunarDay > 30) {
                    return "";
                }
                int i1 = lunarDay / 10;
                int i2 = lunarDay % 10;
                String c1 = Lunar.lunarString2[i1];
                String c2 = Lunar.lunarString1[i2];
                if (lunarDay < 11) {
                    c1 = Lunar.lunarString2[0];
                }
                if (i2 == 0) {
                    c2 = Lunar.lunarString2[1];
                }
                return c1 + c2;
            }


            //日期工具辅助类
            private static class MyDate {

                private static final int[] dayMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                public int day;
                @SuppressWarnings("unused")
                public int dayCyl;
                @SuppressWarnings("unused")
                public boolean isLeap;
                @SuppressWarnings("unused")
                public int monCyl;
                public int month;
                public int year;
                @SuppressWarnings("unused")
                public int yearCyl;

                @SuppressWarnings("unused")
                public MyDate() {
                }

                @SuppressWarnings("unused")
                public MyDate(int paramInt1, int paramInt2, int paramInt3) {
                    this.year = paramInt1;
                    this.month = paramInt2;
                    this.day = paramInt3;
                }

                @SuppressWarnings("unused")
                public static int GetNumDayOfMonth(int paramInt1, int paramInt2) {
                    int i = dayMonth[(paramInt2 - 1)];
                    if ((IsBigYear(paramInt1)) && (paramInt2 == 2)) {
                        i++;
                    }
                    return i;
                }

                public static boolean IsBigYear(int paramInt) {
                    if (paramInt % 400 == 0) {
                        return true;
                    }
                    return (paramInt % 4 == 0) && (paramInt % 100 != 0);
                }

                public static long DateDays(Date aDate, Date aDate2) {
                    long myTime;
                    long myTime2;
                    long days = 0;
                    myTime = (aDate.getTime() / 1000);
                    // SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
                    myTime2 = (aDate2.getTime() / 1000);
                    if (myTime > myTime2) {
                        days = (myTime - myTime2) / (1 * 60 * 60 * 24);
                    } else {
                        days = (myTime2 - myTime) / (1 * 60 * 60 * 24);
                    }
                    return days;

                }
                // 求2个日期的天数

                @SuppressWarnings("unused")
                public static long DateDays(String date1, String date2) throws ParseException {

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    long myTime;
                    Date aDate2;
                    Date aDate;
                    long myTime2;
                    long days = 0;

                    aDate = formatter.parse(date1);// 任意日期，包括当前日期
                    myTime = (aDate.getTime() / 1000);

                    // SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
                    aDate2 = formatter.parse(date2);// 任意日期，包括当前日期
                    myTime2 = (aDate2.getTime() / 1000);

                    if (myTime > myTime2) {
                        days = (myTime - myTime2) / (1 * 60 * 60 * 24);
                    } else {
                        days = (myTime2 - myTime) / (1 * 60 * 60 * 24);
                    }


                    return days;

                }

                // 求2个日期的天数
                public static long DateDays2(int year1, int month1, int day1, int year2,
                                             int month2, int day2) throws ParseException, ParseException {

                    String date1;
                    String date2;
                    date1 = year1 + "-" + month1 + "-" + day1;
                    date2 = year2 + "-" + month2 + "-" + day2;
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    long myTime;
                    Date aDate;
                    Date aDate2;
                    long myTime2;
                    long days = 0;

                    aDate = formatter.parse(date1);// 任意日期，包括当前日期
                    myTime = (aDate.getTime() / 1000);

                    // SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
                    aDate2 = formatter.parse(date2);// 任意日期，包括当前日期
                    myTime2 = (aDate2.getTime() / 1000);

                    if (myTime > myTime2) {
                        days = (myTime - myTime2) / (1 * 60 * 60 * 24);
                    } else {
                        days = (myTime2 - myTime) / (1 * 60 * 60 * 24);
                    }


                    return days;

                }

                public int GetNumDayFrom19000101() {
                    int day = 0;
                    try {
                        day = (int) DateDays2(this.year, this.month, this.day, 1900, 1, 1);
                    } catch (ParseException ex) {
                        System.out.println(ex.getMessage());
                    }
                    return day;
                }

                @SuppressWarnings("unused")
                public long GetSFrom19000101() {
                    return 86400L * (1L + GetNumDayFrom19000101());
                }

            }


        }


    }

    /**
     * 手机屏幕操作
     */
    public static class ScreenOperation {

        /**
         * 点击屏幕 可根据 屏幕的比例 与 具体的 X,Y 坐标点击
         */
        public static class AutoTouch {
            private static int width = 0;
            private static int height = 0;

            /**
             * 传入在屏幕中的比例位置，坐标左上角为基准
             *
             * @param act    传入Activity对象
             * @param ratioX 需要点击的x坐标在屏幕中的比例位置
             * @param ratioY 需要点击的y坐标在屏幕中的比例位置
             */
            public static void autoClickRatio(Activity act, final double ratioX, final double ratioY) {
                width = act.getWindowManager().getDefaultDisplay().getWidth();
                height = act.getWindowManager().getDefaultDisplay().getHeight();
                Thread.runJava(new Runnable() {
                    @Override
                    public void run() {
                        // 线程睡眠0.3s
                        Thread.sleep(300);
                        // 生成点击坐标
                        int x = (int) (width * ratioX);
                        int y = (int) (height * ratioY);

                        // 利用ProcessBuilder执行shell命令
                        String[] order = {"input", "tap", "" + x, "" + y};
                        try {
                            new ProcessBuilder(order).start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            /**
             * 传入在屏幕中的坐标，坐标左上角为基准
             *
             * @param act 传入Activity对象
             * @param x   需要点击的x坐标
             * @param y   需要点击的x坐标
             */
            public static void autoClickPos(Activity act, final double x, final double y) {
                width = act.getWindowManager().getDefaultDisplay().getWidth();
                height = act.getWindowManager().getDefaultDisplay().getHeight();
                // 利用ProcessBuilder执行shell命令
                String[] order = {"input", "tap", "" + x, "" + y};
                try {
                    new ProcessBuilder(order).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    /**
     * @AppUtils 应用程序的小工具集合
     */
    public static class ApplicationUtils {

        /********************************************* 分享功能 *********************************/

        /**
         * 分享文字
         *
         * @param activity
         * @param title
         * @param content
         */
        public static void senText(Activity activity, String title, String content) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, content);
            shareIntent = Intent.createChooser(shareIntent, title);
            activity.startActivity(shareIntent);
        }

        /**
         * 分享文件
         *
         * @param activity
         * @param sharTitle
         * @param filePath
         */
        public static void shareFile(Activity activity, String sharTitle, String filePath) {
            Intent intent = new Intent(Intent.ACTION_SEND);// 发送多个文件
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            activity.startActivity(Intent.createChooser(intent, sharTitle));

        }

        /**
         * 分享图片（不推荐方式）
         *
         * @param activity
         * @param bitmap
         */
        public static void shareImageBitmap_F(Activity activity, Bitmap bitmap, String title) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            Uri u = Uri.parse(MediaStore.Images.Media.insertImage(activity.getContentResolver(), bitmap, null, null));//将截图bitmap存系统相册
            intent.putExtra(Intent.EXTRA_STREAM, u);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(Intent.createChooser(intent, title));
        }

        /**
         * 分享图片
         *
         * @param activity
         * @param uri
         */
        public static void shareImageUri(Activity activity, Uri uri) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(Intent.createChooser(intent, "分享图片"));
        }

        /**
         * 分享图片(推荐方式)
         *
         * @param activity
         * @param view
         * @param AUTHORITY
         */
        public static void shareImageView_T(Activity activity, View view, String AUTHORITY) {
            Bitmap bitmapFromView = createBitmapFromView(view);
            Uri uri = saveBitmapToSD(activity, bitmapFromView, FileUtils.getAppDirectory(activity, false) + "/GT", "gt_share.png", AUTHORITY);
            shareImageUri(activity, uri);
        }

        /**
         * 分享图片(推荐方式)
         *
         * @param activity
         * @param bitmapFromView
         * @param AUTHORITY
         */
        public static void shareBitmapView_T(Activity activity, Bitmap bitmapFromView, String AUTHORITY) {
            Uri uri = saveBitmapToSD(activity, bitmapFromView, FileUtils.getAppDirectory(activity, false) + "/GT", "gt_share.png", AUTHORITY);
            shareImageUri(activity, uri);
        }

        /**
         * 指定分享的渠道（不推荐方式）
         *
         * @param context
         * @param bitmap
         * @param shareType 指定分享渠道
         */
        public void shareImageType_F(Context context, Bitmap bitmap, String shareType) {
            try {
                ComponentName componentName = null;
                switch (shareType) {
                    case "新浪"://新浪
                        componentName = new ComponentName("com.sina.weibo", "com.sina.weibo.composerinde.ComposerDispatchActivity");
                        break;
                    case "QQ空间"://QQ空间
//                       componentName = new ComponentName("com.qzone", "com.qzonex.module.operation.ui.QZonePublishMoodActivity");
                        return;
                    case "QQ"://QQ
                        componentName = new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity");
                        break;
                    case "微信"://微信
                        componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
                        break;
                    case "微信朋友圈"://微信朋友圈
                        componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
                        break;
                    case "微信收藏"://微信收藏
                        componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.AddFavoriteUI");
                        break;
                    case "分享更多"://分享更多
                        try {
                            shareImageBitmap_F((Activity) context, bitmap, "选择分享渠道");
                        } catch (Exception e) {
                            ToastUtils.showShort("暂未安装改APP,分享失败.");
                        }
                        return;
                }
                Uri uriToImage = Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, null, null));
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.setType("image/*");
                // 遍历所有支持发送图片的应用。找到需要的应用
                shareIntent.setComponent(componentName);
                context.startActivity(Intent.createChooser(shareIntent, "Share"));
            } catch (Exception e) {
                GT.errs("分享异常:" + e);
            }
        }

        /**
         * 指定分享的渠道（推荐方式）
         *
         * @param context
         * @param bitmap
         * @param shareType 指定分享渠道
         */
        public void shareImageType_T(Context context, Bitmap bitmap, String shareType, String AUTHORITY) {
            try {
                ComponentName componentName = null;
                switch (shareType) {
                    case "新浪"://新浪
                        componentName = new ComponentName("com.sina.weibo", "com.sina.weibo.composerinde.ComposerDispatchActivity");
                        break;
                    case "QQ空间"://QQ空间
//                       componentName = new ComponentName("com.qzone", "com.qzonex.module.operation.ui.QZonePublishMoodActivity");
                        return;
                    case "QQ"://QQ
                        componentName = new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity");
                        break;
                    case "微信"://微信
                        componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
                        break;
                    case "微信朋友圈"://微信朋友圈
                        componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
                        break;
                    case "微信收藏"://微信收藏
                        componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.AddFavoriteUI");
                        break;
                    case "分享更多"://分享更多
                        try {
                            shareImageBitmap_F((Activity) context, bitmap, "选择分享渠道");
                        } catch (Exception e) {
                            ToastUtils.showShort("暂未安装改APP,分享失败.");
                        }
                        return;
                }
//                Uri uriToImage = Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, null, null));
                Uri uriToImage = saveBitmapToSD(context, bitmap, FileUtils.getAppDirectory(context, false) + "/GT", "gt_share.png", AUTHORITY);
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.setType("image/*");
                // 遍历所有支持发送图片的应用。找到需要的应用
                shareIntent.setComponent(componentName);
                context.startActivity(Intent.createChooser(shareIntent, "Share"));
            } catch (Exception e) {
                GT.errs("分享异常:" + e);
            }
        }

        /**
         * 保存方法
         * <p>
         * 需要添加这个配置：AUTHORITY
         * <provider
         * android:name="androidx.core.content.FileProvider"
         * android:authorities="com.gsls.gt.fileprovider"
         * android:exported="false"
         * android:grantUriPermissions="true">
         * <meta-data
         * android:name="android.support.FILE_PROVIDER_PATHS"
         * android:resource="@xml/file_paths" />
         * </provider>
         * <p>
         * file_paths.xml :
         * <paths>
         * <external-path path="." name="external_storage_root" />
         * </paths>
         *
         * @param context
         * @param bitmap    图片资源
         * @param savePath  保存路径(不需要加文件名)
         * @param fileName  文件名字(需要加文件扩展名)
         * @param AUTHORITY 这个需要添加上面的配置文件
         * @return
         */
        public static Uri saveBitmapToSD(Context context, Bitmap bitmap, String savePath, String fileName, String AUTHORITY) {
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(savePath, fileName);
            if (file.exists()) {
                file.delete();
            }
            try {
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                Uri uri;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && AUTHORITY != null) {
                    uri = FileProvider.getUriForFile(context, AUTHORITY, file);
                } else {
                    uri = Uri.fromFile(file);
                }
                out.flush();
                out.close();
                return uri;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 截图
         *
         * @param activity
         * @return
         */
        public static Bitmap takeScreenShot(Activity activity) {
            View dView = activity.getWindow().getDecorView();
            dView.setDrawingCacheEnabled(true);
            dView.buildDrawingCache();
            return Bitmap.createBitmap(dView.getDrawingCache());
        }

        /**
         * View 转 Bitmap
         *
         * @param view
         * @return
         */
        public static Bitmap createBitmapFromView(View view) {
            //是ImageView直接获取
            if (view instanceof ImageView) {
                Drawable drawable = ((ImageView) view).getDrawable();
                if (drawable instanceof BitmapDrawable) {
                    return ((BitmapDrawable) drawable).getBitmap();
                }
            }
            view.clearFocus();
            Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            if (bitmap != null) {
                Canvas canvas = new Canvas(bitmap);
                view.draw(canvas);
                canvas.setBitmap(null);
            }
            return bitmap;
        }

        /**
         * 保存图片
         *
         * @param context  上下文
         * @param view     保存图片的组件
         * @param fileName 文件名
         */
        public static void saveImage(Activity activity, View view, String savePath, String fileName) {

            // 保存图片
            Bitmap bm = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bm);
            view.draw(canvas);

            // 更新图库
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdirs();// 创建整个目录
            }

            try {
                FileOutputStream out = new FileOutputStream(savePath + fileName);
                bm.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 通知系统更新图库
            if (file.exists()) {
                try {
                    MediaStore.Images.Media.insertImage(activity.getContentResolver(), savePath + fileName, fileName, null);// 把文件插入到系统图库
                    activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(savePath + fileName))));// 发送广播通知系统
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }

        /**
         * 保存图片
         *
         * @param activity
         * @param bitmap
         * @param savePath
         * @param fileName
         */
        public static void saveImage(Activity activity, Bitmap bitmap, String savePath, String fileName) {

            // 更新图库
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdirs();// 创建整个目录
            }

            try {
                FileOutputStream out = new FileOutputStream(savePath + fileName);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 通知系统更新图库
            if (file.exists()) {
                try {
                    MediaStore.Images.Media.insertImage(activity.getContentResolver(), savePath + fileName, fileName, null);// 把文件插入到系统图库
                    activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(savePath + fileName))));// 发送广播通知系统
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }

        /**
         * 截图到相册
         *
         * @param activity
         * @param savePath
         * @param fileName
         */
        public static void screenshotToPhoto(Activity activity, String savePath, String fileName) {

            View dView = activity.getWindow().getDecorView();
            dView.setDrawingCacheEnabled(true);
            dView.buildDrawingCache();
            Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());

            // 更新图库
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdirs();// 创建整个目录
            }

            try {
                FileOutputStream out = new FileOutputStream(savePath + fileName);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 通知系统更新图库
            if (file.exists()) {
                try {
                    MediaStore.Images.Media.insertImage(activity.getContentResolver(), savePath + fileName, fileName, null);// 把文件插入到系统图库
                    activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(savePath + fileName))));// 发送广播通知系统
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }

        /********************************************* 其他功能 *********************************/

        /**
         * 弹出软件盘
         *
         * @param editText
         * @param activity
         */
        public static void editKeyboard(EditText editText, Activity activity) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            editText.requestFocus();// 为搜索框 获取光标
        }

        /**
         * 收起软键盘
         *
         * @param editText
         * @param activity
         */
        public static void editKeyShrink(EditText editText, Activity activity) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }

        /**
         * 将字符串复制到粘贴板上
         *
         * @param context
         * @param text
         */
        public static void copyToClipboard(Context context, String text) {
            ClipboardManager systemService = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            systemService.setPrimaryClip(ClipData.newPlainText("text", text));
        }

        /**
         * 获取粘贴板的内容
         *
         * @param context
         * @return
         */
        public static String getClipboardContentTest(Context context) {
            ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            if (cm != null) {
                ClipData data = cm.getPrimaryClip();
                if (data != null) {
                    ClipData.Item item = data.getItemAt(0);
                    if (item != null) {
                        return item.getText().toString();
                    }
                }
            }
            return null;
        }

        /**
         * 获取App名字
         *
         * @param activity
         * @return
         */
        public static String getAppName(Context context) {
            try {
                PackageManager packageManager = context.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                int labelRes = packageInfo.applicationInfo.labelRes;
                return context.getResources().getString(labelRes);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 获取软件版本号
         *
         * @param mContext
         * @return
         */
        public static int getVersionCode(Context mContext) {
            int versionCode = 0;
            try {
                // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
                versionCode = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            return versionCode;
        }

        /**
         * 获取版本号名称
         *
         * @param context
         * @return
         */
        public static String getVerName(Context context) {
            String verName = "";
            try {
                verName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            return verName;
        }

        /**
         * 解压文件
         *
         * @param zipPtath        解压文件的路径
         * @param outputDirectory 解压后的输出路径
         * @param isDeleteZipPage 是否保留压缩文件
         * @throws IOException
         */
        public static void unzipFile(String zipPtath, String outputDirectory, boolean isDeleteZipPage)
                throws IOException {
            /**
             * 解压assets的zip压缩文件到指定目录
             *
             * @param context上下文对象
             * @param assetName压缩文件名
             * @param outputDirectory输出目录
             * @param isReWrite是否覆盖
             * @throws IOException
             */

            //	        Log.i(TAG,"开始解压的文件： "  + zipPtath + "\n" + "解压的目标路径：" + outputDirectory );
            // 创建解压目标目录
            File file = new File(outputDirectory);
            // 如果目标目录不存在，则创建
            if (!file.exists()) {
                file.mkdirs();
            }
            // 打开压缩文件
            InputStream inputStream = new FileInputStream(zipPtath);
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);

            // 读取一个进入点
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            // 使用1Mbuffer
            byte[] buffer = new byte[1024 * 1024];
            // 解压时字节计数
            int count = 0;
            // 如果进入点为空说明已经遍历完所有压缩包中文件和目录
            while (zipEntry != null) {
                if (!zipEntry.isDirectory()) { // 如果是一个文件
                    // 如果是文件
                    String fileName = zipEntry.getName();
                    fileName = fileName.substring(fileName.lastIndexOf("/") + 1); // 截取文件的名字 去掉原文件夹名字
                    file = new File(outputDirectory + File.separator + fileName); // 放到新的解压的文件路径

                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    while ((count = zipInputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, count);
                    }
                    fileOutputStream.close();

                }

                // 定位到下一个文件入口
                zipEntry = zipInputStream.getNextEntry();
            }
            zipInputStream.close();
            if (isDeleteZipPage) {
                new File(zipPtath).delete();// 删除当前补丁压缩包
            }

        }

        /**
         * 获取当前apk包名
         *
         * @param context
         * @return
         */
        public static String getPackageName(Context context) {
            try {
                PackageManager packageManager = context.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                return packageInfo.packageName;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * @获取当前手机里的应用列表
         */
        public static class PrintPhoneAppList {

            public List<String> getAllAppList(Activity activity) {
                List<ResolveInfo> resolveInfos = getResolveInfos(activity);
                List<String> appData = getAppData(resolveInfos, activity);
                return appData;
            }

            private List<ResolveInfo> getResolveInfos(Activity activity) {
                List<ResolveInfo> appList = null;

                Intent intent = new Intent(Intent.ACTION_MAIN, null);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                PackageManager pm = activity.getPackageManager();
                appList = pm.queryIntentActivities(intent, 0);
                Collections.sort(appList, new ResolveInfo.DisplayNameComparator(pm));

                return appList;

            }

            private List<String> getAppData(List<ResolveInfo> resolveInfos, Activity activity) {

                List<String> appData = new ArrayList<>();
                PackageManager packageManager = activity.getPackageManager();
                for (int i = 0; i < resolveInfos.size(); i++) {
                    String pkg = resolveInfos.get(i).activityInfo.packageName;
                    String cls = resolveInfos.get(i).activityInfo.name;
                    String title = null;

                    try {
                        ApplicationInfo applicationInfo = packageManager.getPackageInfo(pkg, i).applicationInfo;
                        title = applicationInfo.loadLabel(packageManager).toString();
                    } catch (Exception e) {

                    }
                    appData.add(title + "：" + pkg + "/" + cls);
                }
                return appData;
            }

        }

        /**
         * 获取当前手机所有App信息
         *
         * @param context
         * @return
         */
        public static ArrayList<HashMap<String, Object>> getAllAppData(Context context) {
            PackageManager pckMan = context.getPackageManager();
            ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
            List<PackageInfo> packageInfo = pckMan.getInstalledPackages(0);
            for (PackageInfo pInfo : packageInfo) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("appimage", pInfo.applicationInfo.loadIcon(pckMan));
                item.put("packageName", pInfo.packageName);
                item.put("versionCode", pInfo.versionCode);
                item.put("versionName", pInfo.versionName);
                item.put("appName", pInfo.applicationInfo.loadLabel(pckMan).toString());
                items.add(item);
            }
            return items;
        }

        /**
         * 是否在前台
         *
         * @param context
         * @return
         */
        public static boolean isFrontDesk(Context context) {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> runnings = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo running : runnings) {
                if (running.processName.equals(ApplicationUtils.getPackageName(context))) {
                    if (running.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                            || running.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }

        /**
         * 提示错误消息
         *
         * @param editText
         * @param errorMessage
         */
        public static void hintErrorMessage(EditText editText, String errorMessage) {
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
            editText.setError(Html.fromHtml("<font color='white'>" + errorMessage + "</font>"));
        }

        /**
         * 是否在后台
         *
         * @param context
         * @return 后台返回 false 前台返回 true
         */
        public static boolean isBackground(Context context) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                if (appProcess.processName.equals(context.getPackageName())) {
                    if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            return true;
        }

        /**
         * 获取所有APPName
         *
         * @return
         */
        public static List<String> getAppAllPackName() {
            List<String> packages = new ArrayList<String>();
            try {
                Process p = Runtime.getRuntime().exec("pm list packages");
                InputStreamReader isr = new InputStreamReader(p.getInputStream(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String line = br.readLine();
                while (line != null) {
                    line = line.trim();
                    if (line.length() > 8) {
                        String prefix = line.substring(0, 8);
                        if (prefix.equalsIgnoreCase("package:")) {
                            line = line.substring(8).trim();
                            if (!TextUtils.isEmpty(line)) {
                                packages.add(line);
                            }
                        }
                    }
                    line = br.readLine();
                }
                br.close();
                p.destroy();
            } catch (Exception e) {
//                GT.log("异常");
            }
            return packages;
        }

        /**
         * 判断手机号码是否规则
         *
         * @param input
         * @return
         */
        public static boolean isPhoneNumber(String input) {
            String regex = "(1[0-9][0-9]|15[0-9]|18[0-9])\\d{8}";
            Pattern p = Pattern.compile(regex);
            return p.matches(regex, input);
        }

        /**
         * 判断是否停止
         */
        private static boolean mIsCancel = false;

        public static boolean ismIsCancel() {
            return mIsCancel;
        }

        /**
         * 是否停止下载
         *
         * @param mIsCancel
         */
        public static void setmIsCancel(boolean mIsCancel) {
            ApplicationUtils.mIsCancel = mIsCancel;
        }

        /**
         * 下载File
         *
         * @param url
         * @param savePath
         * @param saveFileName
         * @param listener
         */
        public static void downloadFile(final String url, final String savePath, final String saveFileName, final OnDownloadListener listener) {
            Thread.runJava(new Runnable() {
                @Override
                public void run() {
                    HttpURLConnection conn;
                    try {
                        conn = (HttpURLConnection) new URL(url).openConnection();
                        conn.connect();
                        InputStream is = conn.getInputStream();
                        int length = conn.getContentLength();

                        File file = new File(savePath, saveFileName);
                        FileOutputStream fos = new FileOutputStream(file);

                        int count = 0;
                        byte[] buffer = new byte[1024];
                        listener.onDownloadStart(file);
                        while (!mIsCancel) {
                            int numread = is.read(buffer);
                            count += numread;
                            // 计算进度条的当前位置
                            int mProgress = (int) (((float) count / length) * 100);
                            //下载中更新进度条
                            listener.onDownloading(mProgress);
                            if (numread < 0) {
                                //下载完成
                                listener.onDownloadSuccess(file);
                                break;
                            }
                            fos.write(buffer, 0, numread);
                        }
                        fos.close();
                        is.close();

                    } catch (IOException e) {
                        listener.onDownloadFailed(e);
                    }
                }
            });
        }

        public interface OnDownloadListener {

            /**
             * 下载成开始
             */
            void onDownloadStart(File file);

            /**
             * 下载进度
             */
            void onDownloading(int progress);

            /**
             * 下载成功
             */
            void onDownloadSuccess(File file);


            /**
             * 下载异常信息
             */

            void onDownloadFailed(Exception e);
        }

        /**
         * 判断当前应用是否是debug状态
         *
         * @param context
         * @return 是 debug版本就返回 true
         */
        public static boolean isApkInDebug(Context context) {
            if (context == null) return true;
            try {
                ApplicationInfo info = context.getApplicationInfo();
                return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
            } catch (Exception e) {
                return false;
            }
        }

    }

//=========================================== APP迭代类（更新、热修复bug） =========================================

    /**
     * APP 迭代类
     */
    public static class AppIteration {

        //更新 APP 版本
        public static class UpdateApp {

            /**
             * 内置 更新 APP 版本 注意
             * <p>
             * 详细教程：https://blog.csdn.net/qq_39799899/article/details/102470084
             * <p>
             * 3.记得添加权限
             * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /> <!-- 写入手机权限 -->
             * <uses-permission android:name="android.permission.INTERNET" />  <!-- 网络权限 -->
             * <uses-permission android:name="android.permission.READ_PHONE_STATE" />  <!-- 读取手机权限 -->
             */

            /**
             * 安装APK
             *
             * @param apkPath
             * @安装新版本 1.在清单文件添加数据共享
             * <application>
             * ...
             * <!--自动安装需要添加-->
             * <provider
             * android:name="androidx.core.content.FileProvider"
             * android:authorities="自己包名.fileprovider"
             * android:grantUriPermissions="true"
             * android:exported="false">
             * <meta-data
             * android:name="android.support.FILE_PROVIDER_PATHS"
             * android:resource="@xml/file_paths" />
             * </provider>
             * ...
             * </<application>
             * <p>
             * 2.添加 file_paths.xml 文件
             * <paths>
             * <external-path path="." name="external_storage_root" />
             * </paths>
             */
            /**
             * @param activity
             * @param apkPath  相对APP的详细位置包括：路径与xxx.apk
             */
            public static void installNewApk(Activity activity, String apkPath) {
                Uri uri;
                Intent intent = new Intent(Intent.ACTION_VIEW);

                //支持7.0
                if (Build.VERSION.SDK_INT >= 24) {
                    uri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".fileprovider", new File(apkPath));
                } else {
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    uri = Uri.fromFile(new File(apkPath));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                } else {
                }
                intent.setDataAndType(uri, "application/vnd.android.package-archive"); // 对应apk类型
                activity.getApplication().startActivity(intent);
            }


        }


        // 热修复 APP
        public static class RepairAPP {

            // 这下面两个属性可自己修改
            private static String repairFileName = null; // 修复文件名 默认补丁包文件名为 当前APP版本号 开头
            private static String repairFilePath = null; // 修复文件路径 默认补丁包的路径为 当前App包名 下的 thermalRemediation文件夹下
            private static String repairFileFolderName = "thermalRemediation"; // 默认存储热修复默认的文件夹名称
            private static String repairBugFilePath = null;// 热修复 bug 文件路径

            public static String getRepairFileName() {
                return repairFileName;
            }

            public static String getRepairFilePath() {
                return repairFilePath;
            }

            public static void setRepairFileName(String repairFileName) {
                RepairAPP.repairFileName = repairFileName;
            }

            public static void setRepairFilePath(String repairFilePath) {
                RepairAPP.repairFilePath = repairFilePath;
            }

            public static String getRepairFileFolderName() {
                return repairFileFolderName;
            }

            public static void setRepairFileFolderName(String repairFileFolderName) {
                RepairAPP.repairFileFolderName = repairFileFolderName;
            }

            private static final String DEX_SUFFIX = ".dex";
            private static final String APK_SUFFIX = ".apk";
            private static final String JAR_SUFFIX = ".jar";
            private static final String ZIP_SUFFIX = ".zip";
            private static final String DEX_DIR = "odex";
            private static final String OPTIMIZE_DEX_DIR = "optimize_dex";
            private static HashSet<File> loadedDex = new HashSet<File>();

            static {
                loadedDex.clear();
            }

            /**
             * @param context
             * @初始化 热修复路径与文件格式
             */
            public static void init(Context context) {
                // 如果 补丁包文件夹名为 null
                if (repairFilePath == null) {
                    repairFilePath = ApplicationUtils.getPackageName(context) + "/" + repairFileFolderName;// 设置补丁包目录为当前
                }

                // 如果 补丁包文件名为 null
                if (repairFileName == null) {
                    repairFileName = ApplicationUtils.getVerName(context);// 默认补丁包文件名为 当前APP版本号 开头
                }

            }

            /**
             * 开启修复
             *
             * @param context
             */
            public static void startRepair(final Context context) {
                init(context);
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                // 遍历所有的修复dex , 因为可能是多个dex修复包
                File fileDir = externalStorageDirectory != null
                        ? new File(externalStorageDirectory, RepairAPP.repairFilePath)
                        : new File(context.getFilesDir(), RepairAPP.DEX_DIR);// data/user/0/包名/files/odex（这个可以任意位置）
                if (!fileDir.exists()) {// 如果目录不存在就创建所有目录，这里需要添加权限
                    fileDir.mkdirs();
                }

                repairBugFilePath = getRepairAppDirectory(context);// 赋值热修复 Bug 文件路径

                if (RepairAPP.isGoingToFix(context)) {// 是否需要热修复
                    RepairAPP.loadFixedDex(context, Environment.getExternalStorageDirectory());// 加载补丁包

                    // 解决 oat 文件的 bug
                    for (String path : FileUtils.getFilesAllName(repairBugFilePath)) {
                        if (path.indexOf("oat") != -1) {
                            repairBugFilePath += "/oat";
                            FileUtils.deleteAllFile(new File(repairBugFilePath), false);
                        }

                    }

                }
            }

            /**
             * @return
             * @获取手机热修复根目录
             */
            public static String getRepairAppDirectory(Context context) {
                if (RepairAPP.getRepairFilePath() == null) {
                    init(context);
                }
                return Environment.getExternalStorageDirectory() + "/" + RepairAPP.getRepairFilePath()
                        + "/";

            }

            /**
             * 加载补丁，使用默认目录：data/data/包名/files/odex
             *
             * @param context
             */
            public static void loadFixedDex(Context context) {
                loadFixedDex(context, null);
            }

            /**
             * 加载补丁
             *
             * @param context       上下文
             * @param patchFilesDir 补丁所在目录
             */
            public static void loadFixedDex(Context context, File patchFilesDir) {
                // dex合并之前的dex
                doDexInject(context, loadedDex);
            }

            /**
             * @验证是否需要热修复
             * @author bthvi
             * @time 2019/10/10 11:42
             * @desc 验证是否需要热修复
             */
            public static boolean isGoingToFix(Context context) {
                boolean canFix = false;
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                // 遍历所有的修复dex , 因为可能是多个dex修复包
                File fileDir = externalStorageDirectory != null ? new File(externalStorageDirectory, repairFilePath)
                        : new File(context.getFilesDir(), DEX_DIR);// data/data/包名/files/odex（这个可以任意位置）
                File[] listFiles = fileDir.listFiles();
                if (listFiles != null) {
                    for (File file : listFiles) {
                        if (file.getName().startsWith(repairFileName) && (file.getName().endsWith(DEX_SUFFIX)
                                || file.getName().endsWith(APK_SUFFIX) || file.getName().endsWith(JAR_SUFFIX)
                                || file.getName().endsWith(ZIP_SUFFIX))) {

                            loadedDex.add(file);// 存入集合
                            // 有目标dex文件, 需要修复
                            canFix = true;
                        }
                    }
                }
                return canFix;
            }

            /**
             * @param context
             * @param downloadUrl
             * @下载Zip补丁包并加载
             */
            public static void downloadZipServicePack(final Context context, final String downloadUrl) {
                final String savePath = RepairAPP.getRepairAppDirectory(context)
                        + "thermalRemediation.zip";

                File file = new File(RepairAPP.getRepairAppDirectory(context));
                if (!file.exists()) {
                    file.mkdirs();
                }

                Thread.runJava(new Runnable() {
                    @Override
                    public void run() {
                        File file = new File(savePath);// 记得加扩展名
                        file.getParentFile().mkdir();
                        try {
                            file.createNewFile();
                            URL url2 = new URL(downloadUrl);
                            HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
                            conn.connect();
                            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                InputStream ips = conn.getInputStream();
                                FileOutputStream fops = new FileOutputStream(file);

                                byte[] buf = new byte[1024];
                                int read = ips.read(buf);
                                while (read != -1) {
                                    fops.write(buf, 0, read);
                                    fops.flush();
                                    read = ips.read(buf);
                                }
                                fops.close();
                                ips.close();
                                conn.disconnect();
                            }
                        } catch (Exception e) {
                            if (LOG.isGtLogTf()) {
                                GT.log(getLineInfo(1), "网络下载app报错： " + e);
                            }
                        }
                        try {
                            ApplicationUtils.unzipFile(savePath,
                                    RepairAPP.getRepairAppDirectory(context), true);
                        } catch (IOException e) {
                            if (LOG.isGtLogTf()) {
                                log(getLineInfo(1), "解压失败： " + e);
                            }
                            e.printStackTrace();
                        }

                        RepairAPP.startRepair(context);// 开始热修复

                    }
                });
            }

            private static void doDexInject(Context context, HashSet<File> loadedDex) {

                String optimizeDir = context.getFilesDir().getAbsolutePath() + File.separator + OPTIMIZE_DEX_DIR;
                File fopt = new File(optimizeDir);
                if (!fopt.exists()) {
                    fopt.mkdirs();
                }

                try {
                    // 1.加载应用程序dex的Loader
                    PathClassLoader pathLoader = (PathClassLoader) context.getClassLoader();
                    for (File dex : loadedDex) {
                        // 2.加载指定的修复的dex文件的Loader
                        DexClassLoader dexLoader = new DexClassLoader(dex.getAbsolutePath(), // 修复好的dex（补丁）所在目录
                                fopt.getAbsolutePath(), // 存放dex的解压目录（用于jar、zip、apk格式的补丁）
                                null, // 加载dex时需要的库
                                pathLoader// 父类加载器
                        );
                        // 3.开始合并
                        // 合并的目标是Element[],重新赋值它的值即可

                        /**
                         * BaseDexClassLoader中有 变量: DexPathList pathList DexPathList中有 变量 Element[]
                         * dexElements 依次反射即可
                         */

                        // 3.1 准备好pathList的引用
                        Object dexPathList = getPathList(dexLoader);
                        Object pathPathList = getPathList(pathLoader);
                        // 3.2 从pathList中反射出element集合
                        Object leftDexElements = getDexElements(dexPathList);
                        Object rightDexElements = getDexElements(pathPathList);
                        // 3.3 合并两个dex数组
                        Object dexElements = combineArray(leftDexElements, rightDexElements);
                        // 重写给PathList里面的Element[] dexElements;赋值
                        Object pathList = getPathList(pathLoader);// 一定要重新获取，不要用pathPathList，会报错
                        setField(pathList, pathList.getClass(), "dexElements", dexElements);
                    }
                    //                   Toast.makeText(appContext, "修复完成", Toast.LENGTH_SHORT).show();
                    //                    log("修复完成");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * 反射给对象中的属性重新赋值
             */
            private static void setField(Object obj, Class<?> cl, String field, Object value)
                    throws NoSuchFieldException, IllegalAccessException {
                Field declaredField = cl.getDeclaredField(field);
                declaredField.setAccessible(true);
                declaredField.set(obj, value);
            }

            /**
             * 反射得到对象中的属性值
             */
            private static Object getField(Object obj, Class<?> cl, String field)
                    throws NoSuchFieldException, IllegalAccessException {
                Field localField = cl.getDeclaredField(field);
                localField.setAccessible(true);
                return localField.get(obj);
            }

            /**
             * 反射得到类加载器中的pathList对象
             */
            private static Object getPathList(Object baseDexClassLoader)
                    throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
                //  当前这行代码在有些机型上会产生oat文件，我已经做了处理
                return getField(baseDexClassLoader, Class.forName("dalvik.system.BaseDexClassLoader"), "pathList");
            }

            /**
             * 反射得到pathList中的dexElements
             */
            private static Object getDexElements(Object pathList) throws NoSuchFieldException, IllegalAccessException {
                return getField(pathList, pathList.getClass(), "dexElements");
            }

            /**
             * 数组合并
             */
            private static Object combineArray(Object arrayLhs, Object arrayRhs) {
                Class<?> clazz = arrayLhs.getClass().getComponentType();
                int i = Array.getLength(arrayLhs);// 得到左数组长度（补丁数组）
                int j = Array.getLength(arrayRhs);// 得到原dex数组长度
                int k = i + j;// 得到总数组长度（补丁数组+原dex数组）
                Object result = Array.newInstance(clazz, k);// 创建一个类型为clazz，长度为k的新数组
                System.arraycopy(arrayLhs, 0, result, 0, i);
                System.arraycopy(arrayRhs, 0, result, i, j);
                return result;
            }

        }


    }

//=========================================== 字符串加密类 =========================================

    /**
     * @加密类
     */
    public static class Encryption {

        /**
         * @MD5 加密算法
         */
        public static class MD5 {

            private static final String hexDigIts[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
                    "e", "f"};

            /**
             * MD5加密
             *
             * @param origin 字符
             * @return
             */
            public static String encryptMD5(String origin) {
                String resultString = null;
                try {
                    resultString = new String(origin);
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    if (null == "UTF-8" || "".equals("UTF-8")) {
                        resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
                    } else {
                        resultString = byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
                    }
                } catch (Exception e) {
                }
                return resultString;
            }

            private static String byteArrayToHexString(byte b[]) {
                StringBuffer resultSb = new StringBuffer();
                for (int i = 0; i < b.length; i++) {
                    resultSb.append(byteToHexString(b[i]));
                }
                return resultSb.toString();
            }

            private static String byteToHexString(byte b) {
                int n = b;
                if (n < 0) {
                    n += 256;
                }
                int d1 = n / 16;
                int d2 = n % 16;
                return hexDigIts[d1] + hexDigIts[d2];
            }

        }

        /**
         * @DES 加密算法
         */
        public static class DES {

            private static String paw =
                    "         4&(\n" +
                            "       ` ~&&\\yM#1\n" +
                            "        ,_'Q!!NMW&\n" +
                            "        WCb 7N✪4D Q%,,\n" +
                            "        PM'*MDk#M0p,\n" +
                            "            ]@J0&e~~4r' ,+bQEQ\n" +
                            "             F8I&#'   _&B$$✪W#&$\n" +
                            "              &0A1   L#DE&E~!Q&Q,\n" +
                            " _=,        ,#0RN1  _T@0$'   ZN$Q.   grNq5\n" +
                            " ^✪'d     ,0K0pK^  g*Q0g'    #Q4p&,/g9X*&#,_/✪(q\n" +
                            "  TA1   ,sDQWh4^  x&NM0` _   #FQ#K#fA#   `*K#XWP~-\n" +
                            "   ^&p,wNMM0qD: /HE#EN' ..#g)~ '@NG0Qx,    `=X*\n" +
                            "  '  '43$'hE✪##m0D04f_g  ~^ ~   `-00**0\n" +
                            "           =0#ONq2W0BF^#, _           ✪p,,\n" +
                            "             `  ^''~   ✪~b''        **R3`\n" +
                            "                      ow,F         +#F~'\n" +
                            "                      /-9!          ` \\\n" +
                            "                       R";

            /**
             * 加密
             *
             * @param clearText
             * @return
             */
            public static String encryptPassword(Object clearText, Object password) {
                password += paw;
                try {
                    DESKeySpec keySpec = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        keySpec = new DESKeySpec(String.valueOf(password).getBytes(StandardCharsets.UTF_8));
                    }
                    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                    SecretKey key = keyFactory.generateSecret(keySpec);

                    Cipher cipher = Cipher.getInstance("DES");
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        return Base64.encodeToString(cipher.doFinal(String.valueOf(clearText).getBytes(StandardCharsets.UTF_8)), Base64.DEFAULT);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return String.valueOf(clearText);
            }

            /**
             * 解密
             *
             * @param encryptedPwd
             * @return
             */
            public static String decryptPassword(Object encryptedPwd, Object password) {
                password += paw;
                try {
                    DESKeySpec keySpec = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        keySpec = new DESKeySpec(String.valueOf(password).getBytes(StandardCharsets.UTF_8));
                    }
                    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                    SecretKey key = keyFactory.generateSecret(keySpec);

                    byte[] encryptedWithoutB64 = Base64.decode(String.valueOf(encryptedPwd), Base64.DEFAULT);
                    Cipher cipher = Cipher.getInstance("DES");
                    cipher.init(Cipher.DECRYPT_MODE, key);
                    byte[] plainTextPwdBytes = cipher.doFinal(encryptedWithoutB64);
                    return new String(plainTextPwdBytes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return String.valueOf(encryptedPwd);
            }
        }


    }

//=========================================== APP权限类 =========================================

    /**
     * APP 权限管理 类
     */
    public static final class AppAuthorityManagement {

        //android6.0之后要动态获取权限 读写权限
        public static void readWritePermission(Activity activity) {
            final int REQUEST_EXTERNAL_STORAGE = 1;
            String[] PERMISSIONS_STORAGE = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};

            try {
                //检测是否有写的权限
                int permission = ActivityCompat.checkSelfPermission(activity,
                        "android.permission.WRITE_EXTERNAL_STORAGE");
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    if (LOG.isGtLogTf()) {
                        err(getLineInfo(1), "读写获取权限失败");
                    }
                    // 没有写的权限，去申请写的权限，会弹出对话框
                    ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
                }
            } catch (Exception e) {
                if (LOG.isGtLogTf()) {
                    err(getLineInfo(1), "读写获取权限报错");
                }
                e.printStackTrace();
            }
        }

        /**
         * @功能：申请白名单 需要添加权限：<uses-permission android:name="android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS" />
         */
        public static void whiteList(Activity activity) {

            //申请白名单
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PowerManager powerManager = (PowerManager) activity.getSystemService(POWER_SERVICE);
                boolean hasIgnored = powerManager.isIgnoringBatteryOptimizations(activity.getPackageName());
                //  判断当前APP是否有加入电池优化的白名单，如果没有，弹出加入电池优化的白名单的设置对话框。
                if (!hasIgnored) {
                    Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                    intent.setData(Uri.parse("package:" + activity.getPackageName()));
                    activity.startActivity(intent);
                }
            }

        }

    }

    /**
     * 权限申请类
     */
    public static class Permission {

        private static final String TAG_PERMISSION = "TAG_PERMISSION";
        private static final String TAG_EACH_PERMISSION = "TAG_EACH_PERMISSION";
        private static Permission Permission = null;
        private static FragmentManager fragmentManager;
        private static PermissionFragment fragment;
        private PermissionDescription permissionDescription;
        private static String[] permission;

        public static Permission init(FragmentActivity context, String... permission) {
            if (Permission == null) {
                synchronized (GT.class) {
                    if (Permission == null) {
                        Permission = new Permission();
                    }
                }
            }
            if (permission == null || permission.length == 0) {
                GT.err("Permission list is empty. Application failed!");
                return Permission;
            }
            Permission.permission = permission;
            fragmentManager = context.getSupportFragmentManager();
            fragment = (PermissionFragment) context.getSupportFragmentManager().findFragmentByTag(TAG_EACH_PERMISSION);
            return Permission;
        }

        public Permission permissions(OnPermissionListener onPermissionListener) {
            Thread.runJava(new Runnable() {
                @Override
                public void run() {

                    Thread.runAndroid(new Runnable() {
                        @Override
                        public void run() {

                            if (permissionDescription != null) {
                                permissionDescription.invalidClose();
                            }

                            permissionDescription = new PermissionDescription();

                            if (fragment == null) {
                                fragment = PermissionFragment.newInstance(permission, onPermissionListener, Permission, permissionDescription);
                            }

                            if (fragmentManager != null) {
                                fragmentManager
                                        .beginTransaction()
                                        .add(fragment, TAG_PERMISSION)
                                        .commitAllowingStateLoss();
                                try {
                                    fragmentManager.executePendingTransactions();
                                } catch (IllegalStateException e) {
                                    GT.log("进入异常:" + e);
//                    fragment.getChildFragmentManager().executePendingTransactions();
                                }
                            }

                        }
                    });

                }
            });


            return Permission;
        }


        //权限接口
        public interface OnPermissionListener {
            //申请权限说明
            void onExplainRequestReason(PermissionDescription onPDListener);

            /**
             * 转到设置
             *
             * @return true:继续申请权限，false:不在进行权限申请
             */
            boolean onForwardToSettings();

            //开始申请
            void request(boolean allGranted, String[] grantedList, String[] deniedList, String message);
        }

        //权限申请说明
        public static class PermissionDescription {

            private boolean acceptAdvice = false;
            private boolean closeAcceptAdvice = false;
            private boolean isValidClose = true;//默认是有效关闭
            private int waitingTime = 0;//设置等待用户授权时间,毫秒

            public boolean isValidClose() {
                return isValidClose;
            }

            public void setCloseAcceptAdvice(boolean closeAcceptAdvice) {
                this.closeAcceptAdvice = closeAcceptAdvice;
            }

            public int getWaitingTime() {
                return waitingTime;
            }

            /**
             * 设置权限申请等待时间，如果时间过了还未申请，表示拒绝权限，如果未-1代表一直等待
             *
             * @param waitingTime（毫秒）
             */
            public void setWaitingTime(int waitingTime) {
                this.waitingTime = waitingTime;
            }

            public boolean isCloseAcceptAdvice() {
                return closeAcceptAdvice;
            }

            public boolean isAcceptAdvice() {
                return acceptAdvice;
            }

            /**
             * 设置是否接受请求意见
             *
             * @param acceptAdvice
             */
            public void setAcceptAdvice(boolean acceptAdvice) {
                this.acceptAdvice = acceptAdvice;
                closeAcceptAdvice = true;
            }

            /**
             * 结束本次授权申明
             */
            public void invalidClose() {
                closeAcceptAdvice = true;
                isValidClose = false;//设置无效关闭
            }

            public void close() {
                closeAcceptAdvice = true;
            }


        }

        /**
         * 申请权限的 Fragment
         */
        public static class PermissionFragment extends Fragment {

            private String[] permission;
            private OnPermissionListener onPermissionListener;
            private Permission Permission;
            private PermissionDescription permissionDescription;
            private List<String> grantedList = new ArrayList<>();//已通过
            private List<String> deniedList = new ArrayList<>();//未通过
            private boolean isAllGranted = true;//检查权限是否全部通过

            public PermissionFragment(String[] permission, OnPermissionListener onPermissionListener, Permission Permission, PermissionDescription permissionDescription) {
                this.permission = permission;
                this.onPermissionListener = onPermissionListener;
                this.Permission = Permission;
                this.permissionDescription = permissionDescription;
            }

            public static PermissionFragment newInstance(String[] permission, OnPermissionListener onPermissionListener, Permission Permission, PermissionDescription permissionDescription) {
                return new PermissionFragment(permission, onPermissionListener, Permission, permissionDescription);
            }

            private FragmentActivity mActivity;

            @Override
            public void onAttach(@NonNull Context context) {
                super.onAttach(context);
                mActivity = getActivity();
            }

            @Override
            public void onDestroy() {
                super.onDestroy();
                permissionDescription.invalidClose();
            }

            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setRetainInstance(true);

                isAllGranted = true;
                if (permission != null && permission.length != 0) {//判断权限是否为 null

                    //检查权限是否全部授予
                    for (int i = 0; i < permission.length; i++) {
                        int code = ContextCompat.checkSelfPermission(mActivity, permission[i]);
                        if (code == -1) {
                            isAllGranted = false;
                        }
                    }

                    if (isAllGranted) {
                        onPermissionListener.request(true, permission, new String[]{}, "All permissions approved");
                        return;
                    }

                    //调用授权说明对话框
                    onPermissionListener.onExplainRequestReason(permissionDescription);

                    Thread.runJava(new Runnable() {
                        @Override
                        public void run() {

                            int i = 0;
                            //等待结束申请说明
                            while (!permissionDescription.isCloseAcceptAdvice()) {
                                Thread.sleep(300);
                            }

                            if (permissionDescription.isValidClose()) {//判断是否有效关闭
                                if (permissionDescription.isAcceptAdvice()) {//判断用户是否同意授权
                                    if (permission != null && permission.length != 0) {
                                        if (onPermissionListener.onForwardToSettings()) {//是否继续进行权限申请
                                            requestPermissions(permission, 1);//开始授权
                                        } else {//特殊权限方法
                                            onPermissionListener.request(false, new String[]{}, permission, "Stop the authorization");
                                        }
                                    }
                                } else {//用户拒绝授权
                                    onPermissionListener.request(false, new String[]{}, permission, "The user rejected the authorization");
                                }
                            }
                        }
                    });

                } else {
                    onPermissionListener.request(false, new String[]{}, permission, "Add the authorization information in the Permissions method");
                    return;
                }


            }


            @Override
            public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                if (deniedList.size() != 0 || grantedList.size() != 0) {
                    deniedList.clear();
                    grantedList.clear();
                }
                for (int i = 0; i < permissions.length; i++) {
                    int code = ContextCompat.checkSelfPermission(mActivity, permissions[i]);
                    if (code == -1) {
                        deniedList.add(permissions[i]);//获取为同意的权限
                    } else if (code == 0) {
                        grantedList.add(permissions[i]);//获取同意的权限
                    }
                }

                String[] grantedArray = new String[grantedList.size()];
                for (int i = 0; i < grantedList.size(); i++) {
                    grantedArray[i] = grantedList.get(i);
                }

                String[] deniedArray = new String[deniedList.size()];
                for (int i = 0; i < deniedList.size(); i++) {
                    deniedArray[i] = deniedList.get(i);
                }

                if (deniedList.size() == 0) {
                    onPermissionListener.request(true, grantedArray, deniedArray, "All permissions approved");
                } else {
                    onPermissionListener.request(false, grantedArray, deniedArray, "All permissions are not approved");
                }


            }
        }


    }

//============================================= UI类 ===========================================

    /**
     * 加载进度条
     */
    public static class ProgressDialogUtils {

        /**
         * 简易的进度条加载框
         *
         * @param context
         * @param isCancelable 是否点击可取消
         * @param message      消息内容
         * @return
         */
        public static ProgressDialog initProgressBarDialog(Context context, boolean isCancelable, String message) {
            ProgressDialog progressDialog = initCircleProgressDialog(context, isCancelable, message);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 进度条 setProgress
            return progressDialog;
        }


        /**
         * 简易的圆圈加载框
         *
         * @param context
         * @param isCancelable 是否点击可取消
         * @param message      消息内容
         * @return
         */
        public static ProgressDialog initCircleProgressDialog(Context context, boolean isCancelable, String message) {
            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(isCancelable);
            if (message != null) {
                progressDialog.setMessage(message);
            }
            return progressDialog;
        }


        /**
         * 初始化进度条加载条
         *
         * @param context
         * @param isCancelable    是否点击可取消
         * @param icon            图标
         * @param title           标题
         * @param message         消息内容
         * @param maxProgress     进度最大值
         * @param dialogInterface 取消监听
         * @return
         */
        public static ProgressDialog initProgressBarDialog(Context context, boolean isCancelable, int icon, String title, String message, int maxProgress, DialogInterface.OnCancelListener dialogInterface) {
            ProgressDialog progressDialog = initCircleProgressDialog(context, isCancelable, icon, title, message, dialogInterface);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 进度条 setProgress
            progressDialog.setMax(maxProgress);
            return progressDialog;
        }


        /**
         * 初始化圆圈加载条
         *
         * @param context
         * @param isCancelable    是否点击可取消
         * @param icon            图标
         * @param title           标题
         * @param message         消息内容
         * @param dialogInterface 取消监听
         * @return
         */
        public static ProgressDialog initCircleProgressDialog(Context context, boolean isCancelable, int icon, String title, String message, DialogInterface.OnCancelListener dialogInterface) {
            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(isCancelable);

            if (icon != -1) {
                progressDialog.setIcon(icon);
            }

            if (title != null) {
                progressDialog.setTitle(title);
            }

            if (message != null) {
                progressDialog.setMessage(message);
            }

            if (isCancelable) {
                if (dialogInterface != null) {
                    progressDialog.setOnCancelListener(dialogInterface);//设置监听取消加载框事件
                }
            }

            return progressDialog;
        }

    }

    /**
     * Notification 通知类
     */
    public static class GT_Notification {

        private static int NOTIFYID = 0x1997; //通知id
        private static String CHANEL_ID = "com.gsls.king";
        private static String CHANEL_DESCRIPTION = "GT 描述";
        private static String CHANEL_NAME = "GT_Android复习";

        /**
         * 设置 通知类的 code
         *
         * @param NotifyId
         */
        public GT_Notification setNotifyId(int NotifyId) {
            NOTIFYID = NotifyId;
            return this;
        }

        /**
         * 设置 通知类的 id
         *
         * @param ChanelId
         */
        public GT_Notification setChanelId(String ChanelId) {
            CHANEL_ID = ChanelId;
            return this;
        }

        /**
         * 设置 通知
         *
         * @param ChanelDescription
         */
        public GT_Notification setChanelDescription(String ChanelDescription) {
            CHANEL_DESCRIPTION = ChanelDescription;
            return this;
        }

        /**
         * 设置通知 名字
         *
         * @param ChanelName
         */
        public GT_Notification setChanelName(String ChanelName) {
            CHANEL_NAME = ChanelName;
            return this;
        }

        private Activity activity;

        /**
         * 实例化 通知类
         *
         * @param activity
         */
        public GT_Notification(Activity activity) {
            this.activity = activity;
        }

        /**
         * 初始化 通知类
         *
         * @param icon       图标
         * @param title      标题
         * @param text       内容
         * @param time       时间
         * @param voiceTF    是否设置声音振动
         * @param autoCancel 设置通知打开后自动消失
         * @param cla        设置跳转的 页面
         * @return 返回 通知类
         */
        public NotificationManagerCompat sendingNotice(int icon, String title, String text, int time, boolean voiceTF, boolean autoCancel, Class<?> cla) {

            /**
             * 由于 Notification.Builder 仅支持 Android 4.1及之后的版本，为了解决兼容性问题， Notification.Builder 仅支持 API 26 与 26 之前的版本
             * Google 在 Android Support v4 中加入了 NotificationCompat.Builder 类
             */
            String channelId = createNotificationChannel(activity.getApplicationContext());//创建Notification Channel
            NotificationCompat.Builder builder = new NotificationCompat.Builder(activity, channelId);//创建Notification并与Channel关联

            builder.setSmallIcon(icon);//设置通知图标
            builder.setAutoCancel(autoCancel);//设置通知打开后自动消失
            builder.setContentTitle(title);//设置标题
            builder.setContentText(text);//设置内容
            if (time == 0) builder.setWhen(System.currentTimeMillis());//设置系统当前时间为发送时间
            else builder.setWhen(time);//设置用户设置的发送时间
            if (voiceTF)
                builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);//设置默认的声音与默认的振动

            if (cla != null) {    //如果 cla 不为空就设置跳转的页面
                Intent intent = new Intent(activity, cla);
                PendingIntent pi = PendingIntent.getActivity(activity, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                builder.setContentIntent(pi);//设置通知栏 点击跳转
            }

            //发布通知
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(activity.getApplicationContext());
            notificationManagerCompat.notify(NOTIFYID, builder.build());
            return notificationManagerCompat;
        }

        /**
         * 创建通知
         *
         * @param context
         * @return
         */
        private String createNotificationChannel(Context context) {
            // O (API 26)及以上版本的通知需要NotificationChannels。
            if (context != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                // 初始化NotificationChannel。
                NotificationChannel notificationChannel = new NotificationChannel(CHANEL_ID, CHANEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                notificationChannel.setDescription(CHANEL_DESCRIPTION);

                // 向系统添加 NotificationChannel。试图创建现有通知
                // 通道的初始值不执行任何操作，因此可以安全地执行
                // 启动顺序
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                assert notificationManager != null;
                notificationManager.createNotificationChannel(notificationChannel);

                return CHANEL_ID;
            } else {
                return null; // 为pre-O(26)设备返回 null
            }
        }

    }

    /**
     * Android GT 动画
     * 动画后面加 F 的则表示 该动画 是假的动画 后面为 T 的则表示为 真动画
     */
    public static class GT_Animation implements SaveObject.SaveBean {

        public GT_Animation() {
        }

        /**
         * @param x           初始 X 位置
         * @param toX         最终 X 位置
         * @param y           初始 Y 位置
         * @param toY         最终 Y 位置
         * @param time        动画持续时间
         * @param isSaveClose 是否保持动画结束时的最终状态
         * @param runCount    播放动画的次数 -1 表示无限循环
         * @param toAndFro    是否来回播放
         * @param view        给 View 添加动画
         * @return
         * @移动动画（假）
         */
        public GT_Animation translate_F(float x, float toX, float y, float toY, long time, boolean isSaveClose, int runCount, boolean toAndFro, View view) {
            Animation translateAnimation = new TranslateAnimation(x, toX, y, toY);
            translateAnimation.setDuration(time);                   //设置动画持续周期
            translateAnimation.setFillAfter(isSaveClose);           //设置动画结束之后的状态是否是动画的最终状态，true，表示是保持动画结束时的最终状态
            translateAnimation.setFillBefore(!isSaveClose);         //动画播放完后，视图是否会停留在动画开始的状态，默认为true
            if (runCount == -1) {
                translateAnimation.setRepeatCount(Animation.INFINITE);  //播放无限次数
            } else {
                translateAnimation.setRepeatCount(runCount);            //播放的次数
            }
            if (toAndFro) translateAnimation.setRepeatMode(Animation.RESTART);       //是否来回的播放
            view.startAnimation(translateAnimation);                //开始播放
            return this;
        }

        /**
         * @param x           初始 X 位置
         * @param toX         最终 X 位置
         * @param y           初始 Y 位置
         * @param toY         最终 Y 位置
         * @param time        动画持续时间
         * @param isSaveClose 是否保持动画结束时的最终状态
         * @param runCount    播放动画的次数 -1 表示为无限次数
         * @param toAndFro    是否来回播放
         * @return
         * @移动item动画（假）
         */
        public Animation translat_Item_F(float x, float toX, float y, float toY, long time, boolean isSaveClose, int runCount, boolean toAndFro) {
            Animation translateAnimation = new TranslateAnimation(x, toX, y, toY);
            translateAnimation.setDuration(time);               //设置动画持续周期
            translateAnimation.setFillAfter(isSaveClose);       //设置动画结束之后的状态是否是动画的最终状态，true，表示是保持动画结束时的最终状态
            translateAnimation.setFillBefore(!isSaveClose);     // 动画播放完后，视图是否会停留在动画开始的状态，默认为true
            if (runCount == -1) {
                translateAnimation.setRepeatCount(Animation.INFINITE);  //播放无限次数
            } else {
                translateAnimation.setRepeatCount(runCount);            //播放的次数
            }
            if (toAndFro) translateAnimation.setRepeatMode(Animation.RESTART);   //是否来回的播放
            return translateAnimation;
        }

        /**
         * @param x        初始 X 位置
         * @param toX      最终 X 位置
         * @param time     执行动画时间
         * @param runCount 执行动画次数
         * @param toAndFro 是否来回播放
         * @param view     给View加入动画
         * @return
         * @左右动画（真）
         */
        public GT_Animation translateX_T(float x, float toX, long time, int runCount, boolean toAndFro, View view) {
            ObjectAnimator translateX = ObjectAnimator.ofFloat(view, "translationX", x, toX);
            translateX.setDuration(time);      //动画执行时间
            if (runCount == -1) {
                translateX.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                translateX.setRepeatCount(runCount);//循环多少次
            }
            if (toAndFro) translateX.setRepeatMode(ValueAnimator.REVERSE);//是否来回播放
            translateX.start();//执行动画
            return this;
        }

        /**
         * @param x        初始 X 位置
         * @param toX      最终 X 位置
         * @param time     执行动画时间
         * @param runCount 执行动画次数
         * @param toAndFro 是否来回播放
         * @param view     给View加入动画
         * @return
         * @左右item动画（真）
         */
        public ObjectAnimator translateX_Item_T(float x, float toX, long time, int runCount, boolean toAndFro, View view) {
            ObjectAnimator translateX = ObjectAnimator.ofFloat(view, "translationX", x, toX);
            translateX.setDuration(time);      //动画执行时间
            if (runCount == -1) {
                translateX.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                translateX.setRepeatCount(runCount);//循环多少次
            }
            if (toAndFro) translateX.setRepeatMode(ValueAnimator.REVERSE);//是否来回播放
            return translateX;
        }

        /**
         * @param y        初始 Y 位置
         * @param toY      最终 Y 位置
         * @param time     执行动画时间
         * @param runCount 执行动画次数
         * @param toAndFro 是否来回播放
         * @param view     给View加入动画
         * @return
         * @上下动画（真）
         */
        public GT_Animation translateY_T(float y, float toY, long time, int runCount, boolean toAndFro, View view) {
            ObjectAnimator translateY = ObjectAnimator.ofFloat(view, "translationY", y, toY);
            translateY.setDuration(time);      //动画执行时间
            if (runCount == -1) {
                translateY.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                translateY.setRepeatCount(runCount);//循环多少次
            }
            if (toAndFro) translateY.setRepeatMode(ValueAnimator.REVERSE);//是否来回播放
            translateY.start();//执行动画
            return this;
        }

        /**
         * @param y        初始 Y 位置
         * @param toY      最终 Y 位置
         * @param time     执行动画时间
         * @param runCount 执行动画次数
         * @param toAndFro 是否来回播放
         * @param view     给View加入动画
         * @return
         * @上下item动画（真）
         */
        public ObjectAnimator translateY_Item_T(float y, float toY, long time, int runCount, boolean toAndFro, View view) {
            ObjectAnimator translateY = ObjectAnimator.ofFloat(view, "translationY", y, toY);
            translateY.setDuration(time);      //动画执行时间
            if (runCount == -1) {
                translateY.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                translateY.setRepeatCount(runCount);//循环多少次
            }
            if (toAndFro) translateY.setRepeatMode(ValueAnimator.REVERSE);//是否来回播放
            return translateY;
        }

        /**
         * @param x        初始 X 坐标
         * @param toX      最终 X 坐标
         * @param y        初始 Y 坐标
         * @param toY      最终 Y 坐标
         * @param time     消耗时间
         * @param runCount 执行次数
         * @param toAndFro 是否来回播放
         * @param view     动画的View
         * @return
         * @平移动画（真）
         */
        public GT_Animation translate_T(float x, float toX, float y, float toY, long time, int runCount, boolean toAndFro, View view) {

            AnimatorSet animatorSet = new AnimatorSet();

            ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", x, toX);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(view, "translationY", y, toY);

            //设置动画时间
            translationX.setDuration(time);
            translationY.setDuration(time);

            if (runCount == -1) {
                translationX.setRepeatCount(ValueAnimator.INFINITE);//无限循环
                translationY.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                translationX.setRepeatCount(runCount);//循环多少次
                translationY.setRepeatCount(runCount);//循环多少次
            }

            //设置动画是否来回播放
            if (toAndFro) {
                translationX.setRepeatMode(ObjectAnimator.REVERSE);
                translationY.setRepeatMode(ObjectAnimator.REVERSE);
            }

            animatorSet.play(translationX).with(translationY);
            animatorSet.start();

            return this;
        }

        /**
         * @param x        初始 X 坐标
         * @param toX      最终 X 坐标
         * @param y        初始 Y 坐标
         * @param toY      最终 Y 坐标
         * @param time     消耗时间
         * @param runCount 执行次数
         * @param toAndFro 是否来回播放
         * @param view     动画的View
         * @return
         * @平移item动画（真）
         */
        public AnimatorSet translate_Item_T(float x, float toX, float y, float toY, long time, int runCount, boolean toAndFro, View view) {

            AnimatorSet animatorSet = new AnimatorSet();

            ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", x, toX);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(view, "translationY", y, toY).setDuration(time);

            //设置动画时间
            translationX.setDuration(time);
            translationY.setDuration(time);

            if (runCount == -1) {
                translationX.setRepeatCount(ValueAnimator.INFINITE);//无限循环
                translationY.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                translationX.setRepeatCount(runCount);//循环多少次
                translationY.setRepeatCount(runCount);//循环多少次
            }

            //设置动画是否来回播放
            if (toAndFro) {
                translationX.setRepeatMode(ObjectAnimator.REVERSE);
                translationY.setRepeatMode(ObjectAnimator.REVERSE);
            }

            animatorSet.play(translationX).with(translationY);

            return animatorSet;
        }


        /**
         * @param x           原始 X 尺寸
         * @param toX         结束 X 尺寸
         * @param y           原始 Y 尺寸
         * @param toY         结束 Y 尺寸
         * @param time        动画持续时间
         * @param isSaveClose 是否保持动画结束时的最终状态
         * @param runCount    播放动画的次数 -1 表示无限次数
         * @param toAndFro    是否来回播放
         * @param view        给 View 添加动画
         * @return
         * @缩放动画（假）
         */
        public GT_Animation scale_F(float x, float toX, float y, float toY, long time, boolean isSaveClose, int runCount, boolean toAndFro, View view) {
            Animation scaleAnimation = new ScaleAnimation(x, toX, y, toY, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setDuration(time);                   //动画时间
            scaleAnimation.setFillAfter(isSaveClose);           //设置动画结束之后的状态是否是动画的最终状态，true，表示是保持动画结束时的最终状态
            scaleAnimation.setFillBefore(!isSaveClose);         //动画播放完后，视图是否会停留在动画开始的状态，默认为true
            if (runCount == -1) {
                scaleAnimation.setRepeatCount(Animation.INFINITE);  //播放无限次数
            } else {
                scaleAnimation.setRepeatCount(runCount);            //播放的次数
            }
            if (toAndFro) scaleAnimation.setRepeatMode(Animation.RESTART);       //是否来回的播放
            view.startAnimation(scaleAnimation);                //开始播放
            return this;
        }

        /**
         * @param x           原始 X 尺寸
         * @param toX         结束 X 尺寸
         * @param y           原始 Y 尺寸
         * @param toY         结束 Y 尺寸
         * @param time        动画持续时间
         * @param isSaveClose 是否保持动画结束时的最终状态
         * @param runCount    播放动画的次数 -1 表示无限次数
         * @param toAndFro    是否来回播放
         * @return
         * @缩放item动画（假）
         */
        public Animation scales_F(float x, float toX, float y, float toY, long time, boolean isSaveClose, int runCount, boolean toAndFro) {
            Animation scaleAnimation = new ScaleAnimation(x, toX, y, toY, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setDuration(time);                   //动画时间
            scaleAnimation.setFillAfter(isSaveClose);           //设置动画结束之后的状态是否是动画的最终状态，true，表示是保持动画结束时的最终状态
            scaleAnimation.setFillBefore(!isSaveClose);         //动画播放完后，视图是否会停留在动画开始的状态，默认为true
            if (runCount == -1) {
                scaleAnimation.setRepeatCount(Animation.INFINITE);  //播放无限次数
            } else {
                scaleAnimation.setRepeatCount(runCount);            //播放的次数
            }
            if (toAndFro) scaleAnimation.setRepeatMode(Animation.RESTART);       //是否来回的播放
            return scaleAnimation;
        }

        /**
         * @param x           原始 X 尺寸
         * @param toX         结束 X 尺寸
         * @param time        动画持续时间
         * @param isSaveClose 是否保持动画结束时的最终状态
         * @param runCount    播放动画的次数 -1 表示无限次数
         * @param toAndFro    是否来回播放
         * @param view        给 View 添加动画
         * @return
         * @缩放动画（真）
         */
        public GT_Animation scaleX_T(float x, float toX, long time, boolean isSaveClose, int runCount, boolean toAndFro, View view) {
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", x, toX);
            scaleX.setDuration(time);      //动画执行时间
            if (runCount == -1) {
                scaleX.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                scaleX.setRepeatCount(runCount);//循环多少次
            }
            if (toAndFro) scaleX.setRepeatMode(ValueAnimator.REVERSE);//是否来回播放
            scaleX.start();//执行动画
            return this;
        }

        /**
         * @param x           原始 X 尺寸
         * @param toX         结束 X 尺寸
         * @param time        动画持续时间
         * @param isSaveClose 是否保持动画结束时的最终状态
         * @param runCount    播放动画的次数 -1 表示无限次数
         * @param toAndFro    是否来回播放
         * @param view        给 View 添加动画
         * @return
         * @缩放item动画（真）
         */
        public ObjectAnimator scaleX_item_T(float x, float toX, long time, boolean isSaveClose, int runCount, boolean toAndFro, View view) {
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", x, toX);
            scaleX.setDuration(time);      //动画执行时间
            if (runCount == -1) {
                scaleX.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                scaleX.setRepeatCount(runCount);//循环多少次
            }
            if (toAndFro) scaleX.setRepeatMode(ValueAnimator.REVERSE);//是否来回播放
            return scaleX;
        }

        /**
         * @param y           原始 Y 尺寸
         * @param toY         结束 Y 尺寸
         * @param time        动画持续时间
         * @param isSaveClose 是否保持动画结束时的最终状态
         * @param runCount    播放动画的次数 -1 表示无限次数
         * @param toAndFro    是否来回播放
         * @param view        给 View 添加动画
         * @return
         * @缩放动画（真）
         */
        public GT_Animation scaleY_T(float y, float toY, long time, boolean isSaveClose, int runCount, boolean toAndFro, View view) {
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", y, toY);
            scaleY.setDuration(time);      //动画执行时间
            if (runCount == -1) {
                scaleY.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                scaleY.setRepeatCount(runCount);//循环多少次
            }
            if (toAndFro) scaleY.setRepeatMode(ValueAnimator.REVERSE);//是否来回播放
            scaleY.start();//执行动画
            return this;
        }

        /**
         * @param y           原始 Y 尺寸
         * @param toY         结束 Y 尺寸
         * @param time        动画持续时间
         * @param isSaveClose 是否保持动画结束时的最终状态
         * @param runCount    播放动画的次数 -1 表示无限次数
         * @param toAndFro    是否来回播放
         * @param view        给 View 添加动画
         * @return
         * @缩放item动画（真）
         */
        public ObjectAnimator scaleY_item_T(float y, float toY, long time, boolean isSaveClose, int runCount, boolean toAndFro, View view) {
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", y, toY);
            scaleY.setDuration(time);      //动画执行时间
            if (runCount == -1) {
                scaleY.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                scaleY.setRepeatCount(runCount);//循环多少次
            }
            if (toAndFro) scaleY.setRepeatMode(ValueAnimator.REVERSE);//是否来回播放
            return scaleY;
        }

        /**
         * @param x        初始 X 坐标
         * @param toX      最终 X 坐标
         * @param y        初始 Y 坐标
         * @param toY      最终 Y 坐标
         * @param time     消耗时间
         * @param runCount 执行次数
         * @param toAndFro 是否来回播放
         * @param view     动画的View
         * @return
         * @缩放动画（真）
         */
        public GT_Animation scale_T(float x, float toX, float y, float toY, long time, int runCount, boolean toAndFro, View view) {

            AnimatorSet animatorSet = new AnimatorSet();

            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", x, toX);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", y, toY);

            //设置动画时间
            scaleX.setDuration(time);
            scaleY.setDuration(time);

            if (runCount == -1) {
                scaleX.setRepeatCount(ValueAnimator.INFINITE);//无限循环
                scaleY.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                scaleX.setRepeatCount(runCount);//循环多少次
                scaleY.setRepeatCount(runCount);//循环多少次
            }

            //设置动画是否来回播放
            if (toAndFro) {
                scaleX.setRepeatMode(ObjectAnimator.REVERSE);
                scaleY.setRepeatMode(ObjectAnimator.REVERSE);
            }

            animatorSet.play(scaleX).with(scaleY);
            animatorSet.start();

            return this;
        }

        /**
         * @param x        初始 X 坐标
         * @param toX      最终 X 坐标
         * @param y        初始 Y 坐标
         * @param toY      最终 Y 坐标
         * @param time     消耗时间
         * @param runCount 执行次数
         * @param toAndFro 是否来回播放
         * @param view     动画的View
         * @return
         * @缩放item动画（真）
         */
        public AnimatorSet scale_item_T(float x, float toX, float y, float toY, long time, int runCount, boolean toAndFro, View view) {

            AnimatorSet animatorSet = new AnimatorSet();

            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", x, toX);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", y, toY);

            //设置动画时间
            scaleX.setDuration(time);
            scaleY.setDuration(time);

            if (runCount == -1) {
                scaleX.setRepeatCount(ValueAnimator.INFINITE);//无限循环
                scaleY.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                scaleX.setRepeatCount(runCount);//循环多少次
                scaleY.setRepeatCount(runCount);//循环多少次
            }

            //设置动画是否来回播放
            if (toAndFro) {
                scaleX.setRepeatMode(ObjectAnimator.REVERSE);
                scaleY.setRepeatMode(ObjectAnimator.REVERSE);
            }

            animatorSet.play(scaleX).with(scaleY);

            return animatorSet;
        }


        /**
         * @param degrees     View初始角度
         * @param toDegrees   View旋转角度
         * @param time        动画执行时间
         * @param time        动画持续时间
         * @param isSaveClose 是否保持动画结束时的最终状态
         * @param runCount    播放动画的次数 -1 表示无限次数
         * @param toAndFro    是否来回播放
         * @param view        执行View动画
         * @return
         * @平面旋转动画（假）
         */
        public GT_Animation rotate_F(float degrees, float toDegrees, long time, boolean isSaveClose, int runCount, boolean toAndFro, View view) {
            Animation rotateAnimation = new RotateAnimation(degrees, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(time);                   //动画时间
            rotateAnimation.setFillAfter(isSaveClose);           //设置动画结束之后的状态是否是动画的最终状态，true，表示是保持动画结束时的最终状态
            rotateAnimation.setFillBefore(!isSaveClose);         //动画播放完后，视图是否会停留在动画开始的状态，默认为true
            if (runCount == -1) {
                rotateAnimation.setRepeatCount(Animation.INFINITE);  //播放无限次数
            } else {
                rotateAnimation.setRepeatCount(runCount);            //播放的次数
            }
            if (toAndFro) rotateAnimation.setRepeatMode(Animation.RESTART);       //是否来回的播放
            view.startAnimation(rotateAnimation);                //开始播放
            return this;
        }

        /**
         * @param degrees     View初始角度
         * @param toDegrees   View旋转角度
         * @param time        动画执行时间
         * @param time        动画持续时间
         * @param isSaveClose 是否保持动画结束时的最终状态
         * @param runCount    播放动画的次数 -1 表示无限次数
         * @param toAndFro    是否来回播放
         * @return
         * @平面旋转item动画（假）
         */
        public Animation rotates_F(float degrees, float toDegrees, long time, boolean isSaveClose, int runCount, boolean toAndFro) {
            Animation rotateAnimation = new RotateAnimation(degrees, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(time);                   //动画时间
            rotateAnimation.setFillAfter(isSaveClose);           //设置动画结束之后的状态是否是动画的最终状态，true，表示是保持动画结束时的最终状态
            rotateAnimation.setFillBefore(!isSaveClose);         //动画播放完后，视图是否会停留在动画开始的状态，默认为true
            if (runCount == -1) {
                rotateAnimation.setRepeatCount(Animation.INFINITE);  //播放无限次数
            } else {
                rotateAnimation.setRepeatCount(runCount);            //播放的次数
            }
            if (toAndFro) rotateAnimation.setRepeatMode(Animation.RESTART);       //是否来回的播放
            return rotateAnimation;
        }

        /**
         * @param x        初始化 X 坐标
         * @param toX      最终的 X 坐标
         * @param time     动画耗时
         * @param runCount 动画播放次数
         * @param toAndFro 是否来回播放
         * @param view     要使用动画的View
         * @return
         * @旋转动画X（真）
         */
        public GT_Animation rotatesX_T(float x, float toX, long time, int runCount, boolean toAndFro, View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", x, toX);

            rotationX.setDuration(time);//设置时间

            if (runCount == -1) {
                rotationX.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                rotationX.setRepeatCount(runCount);//循环多少次
            }

            //设置动画是否来回播放
            if (toAndFro) {
                rotationX.setRepeatMode(ObjectAnimator.REVERSE);
            }

            rotationX.start();//开启动画
            return this;
        }

        /**
         * @param x        初始化 X 坐标
         * @param toX      最终的 X 坐标
         * @param time     动画耗时
         * @param runCount 动画播放次数
         * @param toAndFro 是否来回播放
         * @param view     要使用动画的View
         * @return
         * @旋转item动画X（真）
         */
        public ObjectAnimator rotatesX_Item_T(float x, float toX, long time, int runCount, boolean toAndFro, View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", x, toX);

            rotationX.setDuration(time);//设置时间

            if (runCount == -1) {
                rotationX.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                rotationX.setRepeatCount(runCount);//循环多少次
            }

            //设置动画是否来回播放
            if (toAndFro) {
                rotationX.setRepeatMode(ObjectAnimator.REVERSE);
            }

            rotationX.start();//开启动画
            return rotationX;
        }

        /**
         * @param y        初始化 Y 坐标
         * @param toY      最终的 Y 坐标
         * @param time     动画耗时
         * @param runCount 动画播放次数
         * @param toAndFro 是否来回播放
         * @param view     要使用动画的View
         * @return
         * @旋转动画Y（真）
         */
        public GT_Animation rotatesY_T(float y, float toY, long time, int runCount, boolean toAndFro, View view) {
            ObjectAnimator rotationY = ObjectAnimator.ofFloat(view, "rotationY", y, toY);

            rotationY.setDuration(time);//设置时间

            if (runCount == -1) {
                rotationY.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                rotationY.setRepeatCount(runCount);//循环多少次
            }

            //设置动画是否来回播放
            if (toAndFro) {
                rotationY.setRepeatMode(ObjectAnimator.REVERSE);
            }

            rotationY.start();//开启动画
            return this;
        }

        /**
         * @param y        初始化 Y 坐标
         * @param toY      最终的 Y 坐标
         * @param time     动画耗时
         * @param runCount 动画播放次数
         * @param toAndFro 是否来回播放
         * @param view     要使用动画的View
         * @return
         * @旋转item动画Y（真）
         */
        public ObjectAnimator rotatesY_Item_T(float y, float toY, long time, int runCount, boolean toAndFro, View view) {
            ObjectAnimator rotationY = ObjectAnimator.ofFloat(view, "rotationY", y, toY);

            rotationY.setDuration(time);//设置时间

            if (runCount == -1) {
                rotationY.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                rotationY.setRepeatCount(runCount);//循环多少次
            }

            //设置动画是否来回播放
            if (toAndFro) {
                rotationY.setRepeatMode(ObjectAnimator.REVERSE);
            }

            return rotationY;
        }

        /**
         * @param z        初始化 Z 坐标
         * @param toZ      最终的 Z 坐标
         * @param time     动画耗时
         * @param runCount 动画播放次数
         * @param toAndFro 是否来回播放
         * @param view     要使用动画的View
         * @return
         * @旋转动画Z（真）
         */
        public GT_Animation rotatesZ_T(float z, float toZ, long time, int runCount, boolean toAndFro, View view) {
            ObjectAnimator rotationZ = ObjectAnimator.ofFloat(view, "rotation", z, toZ);

            rotationZ.setDuration(time);//设置时间

            if (runCount == -1) {
                rotationZ.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                rotationZ.setRepeatCount(runCount);//循环多少次
            }

            //设置动画是否来回播放
            if (toAndFro) {
                rotationZ.setRepeatMode(ObjectAnimator.REVERSE);
            }

            rotationZ.start();//开启动画
            return this;
        }

        /**
         * @param z        初始化 Z 坐标
         * @param toZ      最终的 Z 坐标
         * @param time     动画耗时
         * @param runCount 动画播放次数
         * @param toAndFro 是否来回播放
         * @param view     要使用动画的View
         * @return
         * @旋转item动画Z（真）
         */
        public ObjectAnimator rotatesZ_item_T(float z, float toZ, long time, int runCount, boolean toAndFro, View view) {
            ObjectAnimator rotationZ = ObjectAnimator.ofFloat(view, "rotation", z, toZ);

            rotationZ.setDuration(time);//设置时间

            if (runCount == -1) {
                rotationZ.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                rotationZ.setRepeatCount(runCount);//循环多少次
            }

            //设置动画是否来回播放
            if (toAndFro) {
                rotationZ.setRepeatMode(ObjectAnimator.REVERSE);
            }
            return rotationZ;
        }


        /**
         * @param alpha       初始透明度
         * @param toAlpha     最终透明度
         * @param time        动画执行时间
         * @param isSaveClose 是否保持动画结束时的最终状态
         * @param runCount    播放动画的次数 -1 表示无限次数
         * @param toAndFro    是否来回播放
         * @param view        执行View动画
         * @return
         * @透明动画（假）
         */
        public GT_Animation alpha_F(float alpha, float toAlpha, long time, boolean isSaveClose, int runCount, boolean toAndFro, View view) {
            Animation alphaAnimation = new AlphaAnimation(alpha, toAlpha);
            alphaAnimation.setDuration(time);                   //动画时间
            alphaAnimation.setFillAfter(isSaveClose);           //设置动画结束之后的状态是否是动画的最终状态，true，表示是保持动画结束时的最终状态
            alphaAnimation.setFillBefore(!isSaveClose);         //动画播放完后，视图是否会停留在动画开始的状态，默认为true
            if (runCount == -1) {
                alphaAnimation.setRepeatCount(Animation.INFINITE);  //播放无限次数
            } else {
                alphaAnimation.setRepeatCount(runCount);            //播放的次数
            }
            if (toAndFro) alphaAnimation.setRepeatMode(Animation.RESTART);       //是否来回的播放
            view.startAnimation(alphaAnimation);                //开启动画
            return this;
        }

        /**
         * @param alpha       初始透明度
         * @param toAlpha     最终透明度
         * @param time        动画执行时间
         * @param isSaveClose 是否保持动画结束时的最终状态
         * @param runCount    播放动画的次数 -1 表示无限次数
         * @param toAndFro    是否来回播放
         * @return
         * @透明item动画（假）
         */
        public Animation alphas_F(float alpha, float toAlpha, long time, boolean isSaveClose, int runCount, boolean toAndFro) {
            Animation alphaAnimation = new AlphaAnimation(alpha, toAlpha);
            alphaAnimation.setDuration(time);                   //动画时间
            alphaAnimation.setFillAfter(isSaveClose);           //设置动画结束之后的状态是否是动画的最终状态，true，表示是保持动画结束时的最终状态
            alphaAnimation.setFillBefore(!isSaveClose);         //动画播放完后，视图是否会停留在动画开始的状态，默认为true
            if (runCount == -1) {
                alphaAnimation.setRepeatCount(Animation.INFINITE);  //播放无限次数
            } else {
                alphaAnimation.setRepeatCount(runCount);            //播放的次数
            }
            if (toAndFro) alphaAnimation.setRepeatMode(Animation.RESTART);       //是否来回的播放
            return alphaAnimation;
        }

        /**
         * @param alpha    初始透明度
         * @param toAlpha  最终透明度
         * @param time     动画执行时间
         * @param runCount 播放动画的次数 -1 表示无限次数
         * @param toAndFro 是否来回播放
         * @return
         * @透明动画（真）
         */
        public GT_Animation alpha_T(float alpha, float toAlpha, long time, int runCount, boolean toAndFro, View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "alpha", alpha, toAlpha);

            rotationX.setDuration(time);//设置时间

            if (runCount == -1) {
                rotationX.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                rotationX.setRepeatCount(runCount);//循环多少次
            }

            //设置动画是否来回播放
            if (toAndFro) {
                rotationX.setRepeatMode(ObjectAnimator.REVERSE);
            }

            rotationX.start();//开启动画
            return this;
        }

        /**
         * @param alpha    初始透明度
         * @param toAlpha  最终透明度
         * @param time     动画执行时间
         * @param runCount 播放动画的次数 -1 表示无限次数
         * @param toAndFro 是否来回播放
         * @return
         * @透明item动画（真）
         */
        public ObjectAnimator alpha_item_T(float alpha, float toAlpha, long time, int runCount, boolean toAndFro, View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "alpha", alpha, toAlpha);

            rotationX.setDuration(time);//设置时间

            if (runCount == -1) {
                rotationX.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            } else {
                rotationX.setRepeatCount(runCount);//循环多少次
            }

            //设置动画是否来回播放
            if (toAndFro) {
                rotationX.setRepeatMode(ObjectAnimator.REVERSE);
            }

            return rotationX;
        }


        /**
         * @param annotationList
         * @param view
         * @return
         * @添加组合动画（假）
         */
        public GT_Animation animationSet_F(List<Animation> annotationList, View view) {
            AnimationSet animationSet = new AnimationSet(true);
            if (annotationList != null && annotationList.size() > 1) {
                for (Animation animation : annotationList) {
                    animationSet.addAnimation(animation);
                }
            }
            view.startAnimation(animationSet);//开启组合动画
            return this;
        }

        /**
         * @param annotationList
         * @return
         * @添加组合item动画（假）
         */
        public AnimationSet animationSets_F(List<Animation> annotationList) {
            AnimationSet animationSet = new AnimationSet(true);
            if (annotationList != null && annotationList.size() > 1) {
                for (Animation animation : annotationList) {
                    animationSet.addAnimation(animation);
                }
            }
            return animationSet;
        }

    }

    /**
     * WindowUtils 窗体类
     */
    public static abstract class WindowUtils {

        /**
         * 开启游戏窗体模式
         *
         * @param activity
         */
        public static void startGameWindow(Activity activity) {
            try {
                WindowUtils.light(activity);//屏幕常亮
                WindowUtils.immersionMode(activity);//沉浸式模式
                WindowUtils.Close_virtualButton(activity);//关闭虚拟按钮
                WindowUtils.hideActionBar((AppCompatActivity) activity);//隐藏 ActionBar
            } catch (Exception e) {
                if (LOG.isGtLogTf())
                    GT.err(getLineInfo(1), "请去掉调用该方法前面所有关于 沉浸式 关闭虚拟按钮 隐藏 ActionBar 等类似的代码");
            }
        }

        /**
         * 开启永久的游戏窗口模式
         * 开启线程去一直开启全屏模式
         * 除非必要，否则勿用该方法
         *
         * @param activity
         */
        public static void startGameWindowUtilss(final Activity activity) {
            Thread.runJava(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Thread.sleep(1000);
                        Thread.runAndroid(new Runnable() {
                            @Override
                            public void run() {
                                startGameWindow(activity);//开启游戏窗口
                            }
                        });
                    }
                }
            });
        }

        /**
         * 屏幕常亮
         *
         * @param activity
         */
        public static void light(Activity activity) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//屏幕常亮
        }

        /**
         * 设置全屏模式
         *
         * @param activity
         */
        public static void fullScreen(Activity activity) {
            activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        /**
         * 关闭虚拟按钮
         *
         * @param activity
         */
        public static void Close_virtualButton(Activity activity) {
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);

            activity.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            //布局位于状态栏下方
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            //全屏
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            //隐藏导航栏
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                    if (Build.VERSION.SDK_INT >= 19) {
                        uiOptions |= 0x00001000;
                    } else {
                        uiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
                    }
                    activity.getWindow().getDecorView().setSystemUiVisibility(uiOptions);
                }
            });

            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);

            Window _window = activity.getWindow();
            WindowManager.LayoutParams params = _window.getAttributes();
            params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
            _window.setAttributes(params);

        }

        /**
         * 关闭虚拟按钮2
         *
         * @param WindowUtils
         */
        public static void setHideVirtualKey(Window WindowUtils) {
            //保持布局状态
            int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    //布局位于状态栏下方
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                    //全屏
                    View.SYSTEM_UI_FLAG_FULLSCREEN |
                    //隐藏导航栏
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            if (Build.VERSION.SDK_INT >= 19) {
                uiOptions |= 0x00001000;
            } else {
                uiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
            }
            WindowUtils.getDecorView().setSystemUiVisibility(uiOptions);
        }

        /**
         * 获取屏幕 宽度
         *
         * @param appCompatActivity
         * @return
         */
        public static int getWindowUtilsWidth(AppCompatActivity appCompatActivity) {
            int width = appCompatActivity.getWindowManager().getDefaultDisplay().getWidth();
            return width;
        }

        /**
         * 获取屏幕 高度
         *
         * @param appCompatActivity
         * @return
         */
        public static int getWindowUtilsHeight(AppCompatActivity appCompatActivity) {
            int height = appCompatActivity.getWindowManager().getDefaultDisplay().getHeight();
            return height;
        }

        /**
         * 隐藏状态栏
         *
         * @param activity
         */
        public static void hideStatusBar(AppCompatActivity activity) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }

        /**
         * 隐藏ActionBar
         *
         * @param activity
         */
        public static void hideActionBar(AppCompatActivity activity) {
            ActionBar actionBar = activity.getSupportActionBar();
            if (activity != null) {
                actionBar.hide();
            }
        }

        /**
         * 隐藏导航栏
         *
         * @param activity
         */
        public static void hideNavigationBar(Activity activity) {
            if (Build.VERSION.SDK_INT >= 19) {
                View decorView = activity.getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }

        /**
         * 透明状态栏
         *
         * @param activity
         */
        public static void lucencyStatusBar(Activity activity) {
            if (Build.VERSION.SDK_INT >= 21) {
                View decorView = activity.getWindow().getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }

        /**
         * 透明导航栏
         *
         * @param activity
         */
        public static void lucencyNavigationBar(Activity activity) {
            if (Build.VERSION.SDK_INT >= 21) {
                View decorView = activity.getWindow().getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
                decorView.setSystemUiVisibility(option);
                activity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }


        /**
         * 沉浸式模式 (隐藏状态栏，去掉ActionBar，隐藏导航栏)
         *
         * @param activity
         */
        public static void immersionMode(Activity activity) {
            if (Build.VERSION.SDK_INT >= 19) {
                View decorView = activity.getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }

        /**
         * 返回当前是 横屏 还是 竖屏
         *
         * @param activity
         * @return 横屏返回 true 竖屏返回 false
         */
        public static boolean isLandscapeAndPortrait(Activity activity) {
            Configuration mConfiguration = activity.getResources().getConfiguration(); //获取设置的配置信息
            if (mConfiguration.orientation == mConfiguration.ORIENTATION_LANDSCAPE) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * one_three:   0-6 的 值来进行强制的 横竖屏、自适应等
         * 执行强制的 横屏 或 竖屏
         *
         * @param activity
         */
        public static void AutoLandscapeAndPortrait(Activity activity, int one_three) {
            switch (one_three) {
                case 0:
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制为正横屏
                    break;
                case 1:
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);//强制为反横屏
                    break;
                case 2:
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制为正竖屏
                    break;
                case 3:
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);//强制为反竖屏
                    break;
                case 4:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_USER);//为虚拟按键提供旋转屏幕提示
                    }
                    break;
                case 5:
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);//自动适宜 正横屏、反横屏、正竖屏
                    break;
                case 6:
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);//自动适宜 正横屏、反横屏
                    break;
            }


        }


    }

    /**
     * 封装 Activity 管理器
     */
    public static class GT_Activity implements SaveObject.SaveBean {

        /**
         * 封装第一代 Activity
         * 封装特点：
         * 1.出现添加布局、初始化布局、加载数据方法
         * 2.获取组件还是使用原始的 findViewById
         */
        public abstract static class BaseActivity extends AppCompatActivity {

            //切换Activity 动画
            private static int cutIndex = 0;//GT内置提供的切换特效
            private static int startAnim = 0;//用户自定义
            private static int closeAnim = 0;//用户自定义

            /**
             * 设置 Activity 切换特效
             * 1  淡入淡出
             * 2  左右滑出
             * 暂时仅支持该两种
             *
             * @param cutIndex
             */
            public void setActivityCutIndex(int cutIndex) {
                this.cutIndex = cutIndex;
            }

            /**
             * 自定义 Activity 切换特效
             *
             * @param startAnim
             * @param closeAnim
             */
            public void setActivityCut(int startAnim, int closeAnim) {
                this.startAnim = startAnim;
                this.closeAnim = closeAnim;
                cutIndex = -1;
            }

            protected GT_Fragment gt_fragment;

            /**
             * 初始化 加载布局
             */
            protected int loadLayout() {
                return 0;
            }

            /**
             * 在绘制完 View 之前设置数据
             */
            protected void initDrawView() {
            }

            /**
             * 初始化 UI
             */
            protected abstract void initView(Bundle savedInstanceState);

            /**
             * 功能方法
             */
            protected void loadData() {
            }

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                gt_fragment = GT_Fragment.gt_fragment;
                int loadLayout = loadLayout();
                if (loadLayout != 0 && loadLayout != -1) {
                    initDrawView();// 设置绘制前的数据
                    setContentView(loadLayout);// 加载布局
                    initView(savedInstanceState);// 初始化 UI
                    loadData();// 功能方法
                }

            }

            /**
             * 构建 GT 工具包
             *
             * @param context
             */
            protected void build(Context context) {
                GT.build(context);
            }

            /**
             * 跳转 Activity
             *
             * @param activityClass
             */
            protected void startActivity(Class activityClass) {

                Context activity = getActivity();
                if (activity == null) return;
                activity.startActivity(new Intent(activity, activityClass));

                //切换动画
                switch (cutIndex) {
                    case 1:
                        //淡入淡出
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                    case 2:
                        //左右滑动
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    default:
                        //用户自定义
                        if (cutIndex == -1 && startAnim != 0 && closeAnim != 0) {
                            overridePendingTransition(startAnim, closeAnim);
                        }
                        break;
                }
            }

            /**
             * @param dialogFragment
             * @跳转其他的 DialogFragment
             */
            protected void startDialogFragment(DialogFragment dialogFragment) {
                dialogFragment.show(getSupportFragmentManager(), dialogFragment.getClass().toString());// 弹出退出提示
            }

            /**
             * @param dialogFragment
             * @跳转其他的 DialogFragment
             */
            protected void startDialogFragment(Class<?> dialogFragmentClass) {

                DialogFragment fragment = null;

                try {
                    fragment = (DialogFragment) dialogFragmentClass.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

                fragment.show(getSupportFragmentManager(), fragment.getClass().toString());// 弹出退出提示
            }

            /**
             * @param supportFragmentManager
             * @param dialogFragment         跳转其他的
             */
            public void startDialogFragment(FragmentManager supportFragmentManager, DialogFragment dialogFragment) {
                dialogFragment.show(supportFragmentManager, dialogFragment.getClass().toString());// 弹出退出提示
            }

            /**
             * @param toFragment
             * @跳转 Fragment
             */
            protected void startFragment(Fragment toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragment(toFragment);
                }
            }

            /**
             * @param toFragment
             * @跳转 Fragment
             */
            protected void startFragment(Class<?> toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragment(toFragment);
                }
            }

            /**
             * 跳转Fragment
             *
             * @param fragmentId
             * @param toFragment
             */
            protected void startFragment(int fragmentId, Fragment toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragment(fragmentId, toFragment);
                }
            }

            /**
             * 跳转Fragment
             *
             * @param fragmentId
             * @param toFragment
             */
            protected void startFragment(int fragmentId, Class<?> toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragment(fragmentId, toFragment);
                }
            }

            /**
             * 跳转Fragment
             *
             * @param toFragment 跳转的Fragment
             * @param startMode  启动模式
             */
            protected void startFragment(Fragment toFragment, int startMode) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startMode(startMode).startFragment(toFragment);
                }
            }

            /**
             * 跳转Fragment
             *
             * @param toFragment 跳转的Fragment
             * @param startMode  启动模式
             */
            protected void startFragment(Class<?> toFragment, int startMode) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startMode(startMode).startFragment(toFragment);
                }
            }

            /**
             * 跳转Fragment
             *
             * @param fragmentId 跳转的容器
             * @param toFragment 跳转的Fragment
             * @param startMode  启动模式
             */
            protected void startFragment(int fragmentId, Fragment toFragment, int startMode) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startMode(startMode).startFragment(fragmentId, toFragment);
                }
            }

            /**
             * 跳转Fragment
             *
             * @param fragmentId 跳转的容器
             * @param toFragment 跳转的Fragment
             * @param startMode  启动模式
             */
            protected void startFragment(int fragmentId, Class<?> toFragment, int startMode) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startMode(startMode).startFragment(fragmentId, toFragment);
                }
            }

            /**
             * @param toFragment
             * @跳转 Fragment
             */
            protected void startFragmentHome(Fragment toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragmentHome(toFragment);
                }
            }

            /**
             * @param toFragment
             * @跳转 Fragment
             */
            protected void startFragmentHome(Class<?> toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragmentHome(toFragment);
                }
            }

            /**
             * 使用首页模式启动Fragment
             *
             * @param fragmentId
             * @param toFragment
             */
            protected void startFragmentHome(int fragmentId, Fragment toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startMode(GT_Fragment.MODE_HOME);
                    GT_Fragment.gt_fragment.startFragment(fragmentId, toFragment);
                }
            }

            /**
             * 使用首页模式启动Fragment
             *
             * @param fragmentId
             * @param toFragment
             */
            protected void startFragmentHome(int fragmentId, Class<?> toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startMode(GT_Fragment.MODE_HOME);
                    GT_Fragment.gt_fragment.startFragment(fragmentId, toFragment);
                }
            }

            /**
             * 启动悬浮窗
             *
             * @param toFragment
             */
            protected void startFloatingWindow(Class<?> toFragment) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (!Settings.canDrawOverlays(this)) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent, 0);
                    } else {
                        startService(new Intent(this, toFragment));
                    }
                } else {
                    startService(new Intent(this, toFragment));
                }
            }

            protected GT_Fragment getGT_Fragment() {
                return GT_Fragment.gt_fragment;
            }

            //反馈数据
            @Override
            public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                onResultData(requestCode, resultCode, data);
            }

            /**
             * 反馈数据
             *
             * @param requestCode
             * @param resultCode
             * @param data
             */
            protected void onResultData(int requestCode, int resultCode, @Nullable Intent data) {

            }

            @Override
            public void finish() {
                super.finish();

                //切换动画
                switch (cutIndex) {
                    case 1:
                        //淡入淡出
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                    case 2:
                        //左右滑动
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    default:
                        //用户自定义
                        if (cutIndex == -1 && startAnim != 0 && closeAnim != 0) {
                            overridePendingTransition(startAnim, closeAnim);
                        }
                        break;
                }


            }

        }

        /**
         * 封装第二代 Activity
         * 封装特点：
         * 1.继承上代所有优点
         * 2.获取组件可以使用注解的方式进行获取
         */
        public abstract static class AnnotationActivity extends BaseActivity {

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                initDrawView();// 设置绘制前的数据
                initView(savedInstanceState);// 初始化 UI
                loadData();// 功能方法
            }

            @Override
            protected void initView(Bundle savedInstanceState) {
                build(this);
            }


        }

        /**
         * 封装第三代 Activity
         * 封装特点：
         * 1.继承上代所有优点
         * 2.获取组件可以直接无需获取，直接使用。
         *
         * @param <T>
         */
        public abstract static class DataBindingActivity extends AnnotationActivity {

        }


        /**
         * 获取Activity
         *
         * @return
         */
        public static Application getApplicationByReflect() {
            try {
                @SuppressLint("PrivateApi") Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
                Object thread = getActivityThread();
                Object app = activityThreadClass.getMethod("getApplication").invoke(thread);
                if (app == null) {
                    return null;
                }
                return (Application) app;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        private static Object getActivityThread() {
            Object activityThread = getActivityThreadInActivityThreadStaticField();
            if (activityThread != null) return activityThread;
            return getActivityThreadInActivityThreadStaticMethod();
        }

        private static Object getActivityThreadInActivityThreadStaticField() {
            try {
                @SuppressLint("PrivateApi") Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
                Field sCurrentActivityThreadField = activityThreadClass.getDeclaredField("sCurrentActivityThread");
                sCurrentActivityThreadField.setAccessible(true);
                return sCurrentActivityThreadField.get(null);
            } catch (Exception e) {
                Log.e("UtilsActivityLifecycle", "getActivityThreadInActivityThreadStaticField: " + e.getMessage());
                return null;
            }
        }

        private static Object getActivityThreadInActivityThreadStaticMethod() {
            try {
                @SuppressLint("PrivateApi") Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
                return activityThreadClass.getMethod("currentActivityThread").invoke(null);
            } catch (Exception e) {
                Log.e("UtilsActivityLifecycle", "getActivityThreadInActivityThreadStaticMethod: " + e.getMessage());
                return null;
            }
        }

    }

    /**
     * 封装 Fragment 管理器
     */
    public static class GT_Fragment implements SaveObject.SaveBean {

        //===================================================== 用于注解获取 GT_Fragment 实例对象 ====================================

        //注意：使用注解时如果没有指定加载Fragment容器的话很容易报未找到视图的异常，但添加 gt_fragment.setHomeFragmentId(R.id.frameLayout); 即可

        @Target(ElementType.FIELD)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface Build {
            int setLayoutHome() default 0;//设置HomeFragment容器

            int setLayoutMain() default 0;//设置MainFragment容器

            Class<?> setClass() default Build.class;//设置预先加载的Fragment

            int setStartMode() default GT_Fragment.MODE_STANDARD;//启动模式默认为默认模式
        }

        private GT_Fragment() {
            //单例
        }

        //===================================================== GT_Fragment 成员变量 ====================================

        private static GT_Fragment gt_fragment = new GT_Fragment();//GT_Fragment 实例化
        private static FragmentManager fragmentManager; //Fragment 管理器
        private static int homeFragmentId = 0;//Home首页页面FragmentID
        private static int mainFragmentId = 0;//Main主页面FragmentID
        private final static int FRAGMENT_ID = 0x1079;//设置静态Fragment 初始 ID
        private static String topFragmentName = "";//始终指向最顶端的 Fragment
        private static List<BackStackFragmentBean> fragmentBeanList;//存储 Fragment 栈中的 Fragment 信息
        private static List<String> fragmentNames;//存储 Fragment 栈中所有 Fragment 名字
        private String saveStackData = "[]";//保存当前栈中 Fragment 数据
        private Activity activity;//活动引用
        private static FragmentManager.OnBackStackChangedListener listener;//Fragment 栈中监听器

        //===================================================== GT_Fragment 成员变量GetSet方法 ====================================


        public static GT_Fragment getGt_fragment() {
            return gt_fragment;
        }

        public static void setGt_fragment(GT_Fragment gt_fragment) {
            GT_Fragment.gt_fragment = gt_fragment;
        }

        /**
         * 采取紧急措施，获取初始化的 Activity
         * 建议在 单 Activity 对 多 Fragment 的时候可以使用
         *
         * @return
         */
        public Activity getActivity() {

            //如果没有主动在 Fragment 初始化的时候初始化 Activity 那么就会导致这里为 null
            if (activity == null) {
                //采取第1号紧急措施，遍历整个 Fragment栈中还存在的 Fragment ，挨个去取一个不为 null 的 Activity ，有那就直接返回该 Activity
                for (String fragmentName : getBackStackFragmentNames()) {//遍历栈中的所有 Fragment
                    Fragment fragment = fragmentManager.findFragmentByTag(fragmentName);//通过设置的标识获取 Fragment
                    if (fragment != null) {
                        activity = fragment.getActivity();//获取 Activity
                        if (activity != null) {
                            return activity;
                        }
                    }
                }

                //采取第2号紧急措施，如果有绑定 Activity 那就直接使用绑定的 Activity
                if (activity == null && context != null && context != null) {
                    activity = (Activity) context;
                }

                //如果还是没有 Activity 那就没法了...

            }

            return activity;
        }

        /**
         * 获取 Fragment 管理器
         *
         * @return
         */
        public FragmentManager getFragmentManager() {
            return fragmentManager;
        }

        /**
         * 获取事务
         *
         * @return
         */
        public FragmentTransaction getTransaction() {
            if (fragmentManager != null) {
                return fragmentManager.beginTransaction();//获取事务
            }
            return null;
        }

        //设置与获取 Home页面的 fragment 容器ID
        public GT_Fragment setHomeFragmentId(int homeFragmentId) {
            this.homeFragmentId = homeFragmentId;
            return this;
        }

        public int getHomeFragmentId() {
            return homeFragmentId;
        }


        //设置与获取 主页面的 Fragment 容器ID
        public int getMainFragmentId() {
            return mainFragmentId;
        }

        public GT_Fragment setMainFragmentId(int mainFragmentId) {
            GT_Fragment.mainFragmentId = mainFragmentId;
            return this;
        }

        public List<BackStackFragmentBean> getBackStackFragmentStack() {
            return getBackStackFragmentList();
        }

        public List<String> getFragmentFragments() {
            return getBackStackFragmentNames();
        }


        //===================================================== 切换 Fragment 方式 ====================================

        /**
         * Fragment 启动方式说明
         * 注意事项： Fragment 的切换方式只要一次设置 就可以持久有效与启动模式相反
         * 注意事项： Fragment 切换方式中的 Activity 切换方式没有 HOME 启动模式。
         * 注意事项：不推荐将 Activity 与 Fragment 进行混用，不然无法保存原页面数据
         */

        /**
         * ACTIVITY:    Activity 切换方式   模仿 Activity 启动方式    切换 Fragment 使用 add 的方式
         */
        public final static int ACTIVITY = 0;

        /**
         * FRAGMENT:    Fragment 启动方式   模仿 Activity 启动方式    切换 Fragment 使用 replace 的方式
         */
        public final static int FRAGMENT = 1;

        /**
         * DIALOG:    Dialog 启动方式   模仿 Activity 启动方式    切换 Fragment 使用 hide/show 的方式
         */
        public final static int DIALOG = 2;

        /**
         * 切换方式 默认使用  Activity
         */
        public static int SWITCHING_MODE = ACTIVITY;//默认使用 Activity

        /**
         * 设置 Fragment 切换模式
         *
         * @param switchingMode 切换模式
         * @return
         */
        public GT_Fragment switchingMode(int switchingMode) {
            //如果启动模式不在正常的值内那就默认为 默认模式
            SWITCHING_MODE = switchingMode;
            return this;
        }

        /**
         * Fragment切换方式管理 （仅供内部调用）
         *
         * @param fragmentId
         * @param transaction
         * @param fragment
         * @param name
         */
        @SuppressLint("WrongConstant")
        private void fragmentSwitchingModeManagement(int fragmentId, FragmentTransaction transaction, Fragment fragment, Class<?> fragmentClass) {

            String fragmentName = fragmentClass.getName();
            //切换页面特效
            cutEffects(transaction);

            switch (SWITCHING_MODE) {
                case ACTIVITY:// Activity 切换方式
                {
//                    GT.log("Activity 切换方式");
                    transaction.add(fragmentId, fragment, fragmentName);
                    break;
                }

                case FRAGMENT:// Fragment 切换方式
                {
//                    GT.log("Fragment 切换方式");
                    transaction.replace(fragmentId, fragment, fragmentName);
                    break;
                }

                case DIALOG:// Dialog 切换方式
                {
//                    GT.log("Dialog 切换方式");
                    //判断当前 Fragment 栈中是否存在当前要显示的Fragment
                    List<String> stackFragmentNames = getStackFragmentNames();
//                    GT.log("获取栈中:" + stackFragmentNames);
                    if (stackFragmentNames != null && stackFragmentNames.contains(fragmentName)) {
                        //存在

//                        GT.log("存在");

                        List<String> backStackFragmentNames = getBackStackFragmentNames();
//                        GT.log("backStackFragmentNames:" + backStackFragmentNames);

                        for (String backStackfragmentName : backStackFragmentNames) {
                            if (backStackfragmentName.equals(fragmentName)) {
                                Fragment stackFragment = getStackFragment(fragmentName);
                                if (stackFragment != null) {
                                    transaction.show(stackFragment);
                                }
//                                GT.log("显示:" + stackFragment.getClass().getSimpleName());
                            } else {
                                Fragment stackFragment = getStackFragment(backStackfragmentName);
                                if (stackFragment != null) {
                                    transaction.hide(stackFragment);
                                }
//                                GT.log("隐藏:" + stackFragment.getClass().getSimpleName());
                            }
                        }
                    } else {
                        //不存在
//                        GT.log("不存在");
                        transaction.addToBackStack(fragmentName);//加入到回退栈
                        transaction.add(fragmentId, fragment, fragmentClass.getName());
                    }


                    break;
                }

                default:    //如果是非法参数 那就默认使用 Activity 的启动方式
                {

                    transaction.add(fragmentId, fragment, fragmentClass.getName());
                }

            }

            //提交事物
            try {
                activity = getActivity();
                if (activity != null && ApplicationUtils.isFrontDesk(activity)) {//判断是否在前台，如果是就正常提交事务
                    transaction.commit();
                } else {//负责使用不推荐的方式提交
                    transaction.commitAllowingStateLoss();
                }
            } catch (IllegalStateException e) {
                transaction.commitAllowingStateLoss();
            }

        }

        /**
         * 22种切换样式：
         * 0    默认切换、       transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);//当前默认使用这一个动画 淡入淡出
         * 1    X轴缩放、		transaction.setCustomAnimations(R.animator.scalex_enter, R.animator.scalex_exit, R.animator.scalex_enter, R.animator.scalex_exit);
         * 2    Y轴缩放、		transaction.setCustomAnimations(R.animator.scaley_enter, R.animator.scaley_exit, R.animator.scaley_enter, R.animator.scaley_exit);
         * 3    XY轴绽放、		transaction.setCustomAnimations(R.animator.scalexy_enter, R.animator.scalexy_exit, R.animator.scalexy_enter, R.animator.scalexy_exit);
         * 4    淡入淡出、		transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
         * 5    水平翻页、		transaction.setCustomAnimations(R.animator.card_flip_horizontal_right_in, R.animator.card_flip_horizontal_left_out, R.animator.card_flip_horizontal_left_in, R.animator.card_flip_horizontal_right_out);
         * 6    垂直翻页、		transaction.setCustomAnimations(R.animator.card_flip_vertical_right_in, R.animator.card_flip_vertical_left_out, R.animator.card_flip_vertical_left_in, R.animator.card_flip_vertical_right_out);
         * 7    垂直滑动、		transaction.setCustomAnimations(R.animator.slide_fragment_vertical_right_in, R.animator.slide_fragment_vertical_left_out, R.animator.slide_fragment_vertical_left_in, R.animator.slide_fragment_vertical_right_out);
         * 8    水平滑动、		transaction.setCustomAnimations(R.animator.slide_fragment_horizontal_right_in, R.animator.slide_fragment_horizontal_left_out, R.animator.slide_fragment_horizontal_left_in, R.animator.slide_fragment_horizontal_right_out);
         * 9    向上推的水平滑动、transaction.setCustomAnimations(R.animator.slide_fragment_horizontal_right_in, R.animator.slide_fragment_vertical_left_out, R.animator.slide_fragment_vertical_left_in, R.animator.slide_fragment_horizontal_right_out);
         * 10   向左推的垂直滑动、transaction.setCustomAnimations(R.animator.slide_fragment_vertical_right_in, R.animator.slide_fragment_horizontal_left_out, R.animator.slide_fragment_horizontal_left_in, R.animator.slide_fragment_vertical_right_out);
         * 11   交叉的滑动、		transaction.setCustomAnimations(R.animator.glide_fragment_horizontal_in, R.animator.glide_fragment_horizontal_out, R.animator.glide_fragment_horizontal_in, R.animator.glide_fragment_horizontal_out);
         * 12   会隐藏的滑动、    特殊
         * 13   弹出、			transaction.setCustomAnimations(R.animator.stack_right_in, R.animator.stack_left_out, R.animator.stack_left_in, R.animator.stack_right_out);
         * 14   魔方、 			transaction.setCustomAnimations(R.animator.cube_right_in, R.animator.cube_left_out, R.animator.cube_left_in, R.animator.cube_right_out);
         * 15   向下旋转、		transaction.setCustomAnimations(R.animator.rotatedown_right_in, R.animator.rotatedown_left_out, R.animator.rotatedown_left_in, R.animator.rotatedown_right_out);
         * 16   向上旋转、		transaction.setCustomAnimations(R.animator.rotateup_right_in, R.animator.rotateup_left_out, R.animator.rotateup_left_in, R.animator.rotateup_right_out);
         * 17   手风琴、			transaction.setCustomAnimations(R.animator.accordion_right_in, R.animator.accordion_left_out, R.animator.accordion_left_in, R.animator.accordion_right_out);
         * 18   水平翻转、 		transaction.setCustomAnimations(R.animator.table_horizontal_right_in, R.animator.table_horizontal_left_out, R.animator.table_horizontal_left_int, R.animator.table_horizontal_right_out);
         * 19   垂直翻转、		transaction.setCustomAnimations(R.animator.table_vertical_right_in, R.animator.table_vertical_left_out, R.animator.table_vertical_left_int, R.animator.table_vertical_right_out);
         * 20   左角落放大、		transaction.setCustomAnimations(R.animator.zoom_from_left_corner_right_in, R.animator.zoom_from_left_corner_left_out, R.animator.zoom_from_left_corner_left_in, R.animator.zoom_from_left_corner_right_out);
         * 21   右角落放大、		transaction.setCustomAnimations(R.animator.zoom_from_right_corner_right_in, R.animator.zoom_from_right_corner_left_out, R.animator.zoom_from_right_corner_left_in, R.animator.zoom_from_right_corner_right_out);
         */

        private static int cutEffectsIndex = 0;//默认为标准切换特效
        private static int enter = 0, exit = 0, popEnter = 0, popExit = 0;//设置自定义Fragment 切换

        /**
         * 设置自定义的 Fragment 切换特效
         *
         * @param enter
         * @param exit
         * @param popEnter
         * @param popExit
         */
        public void setFragmentCutEffects(int enter, int exit, int popEnter, int popExit) {
            cutEffectsIndex = -1;
            this.enter = enter;
            this.exit = exit;
            this.popEnter = popEnter;
            this.popExit = popExit;
        }


        public int getFragmentCutEffectsIndex() {
            return cutEffectsIndex;
        }

        /**
         * 设置切换特效
         * 8 种切换样式：
         * 0    默认切换、
         * 1    X轴缩放、
         * 2    Y轴缩放、
         * 3    XY轴绽放、
         * 4    淡入淡出、
         * 5    水平翻页、
         * 6    垂直翻页、
         * 7    垂直滑动、
         *
         * @param cutEffectsIndex
         */
        public void setFragmentCutEffectsIndex(int cutEffectsIndex) {
            GT_Fragment.cutEffectsIndex = cutEffectsIndex;
        }

        /**
         * 切换特效
         *
         * @param transaction
         */
        private void cutEffects(FragmentTransaction transaction) {


            switch (cutEffectsIndex) {

                case 0:
                    //Fragment内置的 淡入淡出
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    break;
                case 1:
                    //X轴缩放
                    transaction.setCustomAnimations(R.animator.scalex_enter, R.animator.scalex_exit, R.animator.scalex_enter, R.animator.scalex_exit);
                    break;
                case 2:
                    //Y轴缩放
                    transaction.setCustomAnimations(R.animator.scaley_enter, R.animator.scaley_exit, R.animator.scaley_enter, R.animator.scaley_exit);
                    break;
                case 3:
                    //XY轴绽放
                    transaction.setCustomAnimations(R.animator.scalexy_enter, R.animator.scalexy_exit, R.animator.scalexy_enter, R.animator.scalexy_exit);
                    break;
                case 4:
                    //淡入淡出
                    transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
                    break;
                case 5:
                    //水平翻页
                    transaction.setCustomAnimations(R.animator.card_flip_horizontal_right_in, R.animator.card_flip_horizontal_left_out, R.animator.card_flip_horizontal_left_in, R.animator.card_flip_horizontal_right_out);
                    break;
                case 6:
                    //垂直翻页
                    transaction.setCustomAnimations(R.animator.card_flip_vertical_right_in, R.animator.card_flip_vertical_left_out, R.animator.card_flip_vertical_left_in, R.animator.card_flip_vertical_right_out);
                    break;
                default:
                    //其它
                    if (cutEffectsIndex == -1) {
                        transaction.setCustomAnimations(enter, exit, popEnter, popExit);//使用自定义的 Fragment 切换特效
                    }
                    break;

            }


        }

        //===================================================== 启动 Fragment 模式 ====================================

        /**
         * Fragment 启动模式说明
         * 注意事项： Fragment 的启动模式在设置启动模式之后，只要启动后就会初始化到默认的启动模式，与Fragment切换方式相反
         * STANDARD:       默认模式     该启动模式为Android默认启动模式，每当启动一个 fragment 就会在任务栈中创建一个
         * HOME:           主界面模式   该启动模式不将 Fragment 加入退回栈,一般用于APP首页
         * SINGLE_TOP:     栈顶模式     该启动模式是在查看任务栈顶和你将要启动的 fragment 是否是同一个 fragment，是一个就直接复用，否则就新创一个实例
         * SINGLE_TASK:    栈内复用模式 该启动模式是在任务栈中看是否有和你一样的 fragment，有则直接把该 fragment 之上的 fragment 全部弹出使之置于栈顶,如果当前即最顶端那就复用。
         * ENTRANCE:       入口模式     如果当前新启动的Fragment处于栈底，那就弹出除栈底外所有 Fragment 如果当前栈中不存在，那就直接创建一个新的实例。常用于一个程序的入口处
         */

        /**
         * 默认模式
         */
        public final static int MODE_STANDARD = 0;
        /**
         * 主界面模式
         */
        public final static int MODE_HOME = 1;
        /**
         * 栈顶模式
         */
        public final static int MODE_SINGLE_TOP = 2;
        /**
         * 栈内复用模式
         */
        public final static int MODE_SINGLE_TASK = 3;
        /**
         * 入口模式
         */
        public final static int MODE_ENTRANCE = 4;
        /**
         * 启动模式
         */
        public static int START_MODE = MODE_STANDARD; //默认模式

        /**
         * 设置 Fragment 启动模式
         *
         * @param startMode 启动模式
         * @return
         */
        public GT_Fragment startMode(int startMode) {
            //如果启动模式不在正常的值内那就默认为 默认模式
            START_MODE = startMode;
            return this;
        }

        /**
         * 启动模式管理 （仅供内部调用）
         *
         * @param transaction 事件
         * @param fragment    当前启动的 Fragment
         * @return
         */
        private boolean modeManagement(FragmentTransaction transaction, Class<?> fragmentClass) {
//            GT.log("START_MODE2:" + START_MODE);
            String name = fragmentClass.getName();
            switch (START_MODE) {
                case MODE_STANDARD: //默认模式     该启动模式为Android默认启动模式，每当启动一个 fragment 就会在任务栈中创建一个
                {
                    START_MODE = MODE_STANDARD;//恢复默认模式
                    transaction.addToBackStack(name);//加入到回退栈
                    topFragmentName = name;//清空指向顶端的 Fragment
                    return true;
                }

                case MODE_HOME:  //主界面模式   该启动模式不将 Fragment 加入退回栈,一般用于APP首页
                {
                    START_MODE = MODE_STANDARD;//恢复默认模式

                    if (!isStackTop(fragmentClass)) {
                        return false;
                    }

                    if (SWITCHING_MODE == ACTIVITY) {//如果当前切换的方法是 Activity 那就需要加入回退栈，不给与Activity切换模式 Home 启动模式
//                        transaction.addToBackStack(name);//TODO Home 始终指向首页,故不加入回退栈  加入到回退栈 是否需要加入回退栈，待定
                    }

                    topFragmentName = name;//指向最顶端 Fragment
                    return true;
                }

                case MODE_SINGLE_TOP: //栈顶模式     该启动模式是在查看任务栈顶和你将要启动的 fragment 是否是同一个 fragment，是一个就直接复用，否则就新创一个实例
                {
                    START_MODE = MODE_STANDARD;//恢复默认模式

                    //当前栈中存在 Fragment 且 当前栈顶 Fragment 是当前要打开的 Fragment 就用直接复用
                    if (!isStackTop(fragmentClass)) {
                        return false;
                    }

                    transaction.addToBackStack(name);//加入到回退栈
                    topFragmentName = name;//清空指向顶端的 Fragment
                    return true;
                }

                case MODE_SINGLE_TASK: //栈内复用模式 该启动模式是在任务栈中看是否有和你一样的 fragment，有则直接把该 fragment 之上的 fragment 全部弹出使之置于栈顶,如果当前即最顶端那就复用。
                {
                    START_MODE = MODE_STANDARD;//恢复默认模式

                    //当前栈中存在 Fragment 且 当前栈顶 Fragment 是当前要打开的 Fragment 就用直接复用
                    if (!isStackTop(fragmentClass)) {
                        return false;
                    }

                    List<String> fragmentNames = getBackStackFragmentNames();
                    int lastIndex = fragmentNames.lastIndexOf(name);//查找当前集合中最后一个 Fragment 索引
                    if (lastIndex == -1) {
                        //如果回退栈中没找到新打开的 Fragment 就直接打开新的 Fragment
                        transaction.addToBackStack(name);//加入到回退栈
                        topFragmentName = name;//清空指向顶端的 Fragment
                        return true;
                    } else {
                        finish(name, false);//关闭到想要打开的 Fragment
                        return false;
                    }

                }

                case MODE_ENTRANCE: {//  入口模式     弹出除栈底外所有 Fragment 再创建一个新的实例。常用于一个程序的入口处

                    START_MODE = MODE_STANDARD;//恢复默认模式

                    //弹出所有栈
                    for (int i = 0; i < getBackStackFragmentNames().size(); i++) {
                        finish();
                    }
                    topFragmentName = name;//清空指向顶端的 Fragment
                    return true;


                }

                default://非正常的值
                    START_MODE = MODE_STANDARD;//恢复默认模式
                    err(getLineInfo(3) + "：在启动 Fragment 模式中，你输入了非正常的值,不给与启动操作。");
                    topFragmentName = name;//清空指向顶端的 Fragment
                    break;

            }


            return false;
        }


        //===================================================== Fragment 回退栈 与 栈内 工具类 ====================================

        /**
         * 判断当前 fragment 是否处于最顶端显示
         *
         * @param fragmentClass 当前需要判断的 FragmentClass
         * @return
         */
        public <T> boolean isStackTop(Class<T> fragmentClass) {

//            GT.log("当前是否在栈顶:" + fragmentClass.getSimpleName());

            //当前栈中存在 Fragment 且 当前栈顶 Fragment 是当前要打开的 Fragment 就用直接复用
            String name = fragmentClass.getName();
            List<String> stringList = getFragmentFragments();
//            GT.log("当前栈的顺序:" + stringList);
//            GT.log("name:" + name);
//            GT.log("topFragmentName:" + topFragmentName);

            if (name.equals(topFragmentName)) {//判断不加入回退栈的逻辑
                return false;
            } else {
                return true;
            }

            /*if (stringList.size() > 0) {
                String stackTop = stringList.get(stringList.size() - 1);//获取栈顶 Fragment
                GT.log("获取栈顶:" + stackTop);
                GT.log("name:" + name);
                GT.log("topFragmentName:" + topFragmentName);

                if (stackTop.equals(name)) {//如果回退栈栈顶是新打开的 Fragment
                    return false;
                } else if (name.equals(topFragmentName)) {//判断不加入回退栈的逻辑
                    return false;
                }
            } else if (name.equals(topFragmentName)) {//判断不加入回退栈的逻辑
                return false;
            }*/

//            return true;
        }

        /**
         * 获取当前 Fragment 回退栈中的信息
         *
         * @return
         */
        public List<BackStackFragmentBean> getBackStackFragmentList() {

            if (fragmentBeanList == null) {
                fragmentBeanList = new ArrayList<>();
            }

            fragmentBeanList.clear();//清空数据

            for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                String[] fragmentData = fragmentManager.getBackStackEntryAt(i).toString().split(" ");
                try {
                    String hashCode = fragmentData[0].substring(fragmentData[0].indexOf("{") + 1);//唯一标示
                    String stackIndex = fragmentData[1].substring(1);//栈索引
                    String fragmentName = fragmentData[2].substring(0, fragmentData[2].length() - 1);//fragment名称
                    fragmentBeanList.add(new BackStackFragmentBean(Integer.parseInt(stackIndex), hashCode, fragmentName));
                } catch (Exception e) {
                    errs(getLineInfo() + "报错:" + e);
                }
            }

            return fragmentBeanList;
        }

        /**
         * 获取当前 Fragment 回退栈中 Fragment 所有名称
         *
         * @return
         */
        public List<String> getBackStackFragmentNames() {

            if (fragmentNames == null) {
                fragmentNames = new ArrayList<>();
            }
            fragmentNames.clear();//清空数据

            for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                String[] fragmentDataArray = fragmentManager.getBackStackEntryAt(i).toString().split(" ");
                try {
                    String fragmentName = fragmentDataArray[2].substring(0, fragmentDataArray[2].length() - 1);//fragment名称
                    fragmentNames.add(fragmentName);
                } catch (Exception e) {
                    errs(getLineInfo() + "报错:" + e);
                }
            }

            //判断当前栈中的 Fragment 数量是否有变化，如果有变化就清空 指向栈顶的值 用于监听用户按下返回键 去掉 Home Fragment 但栈顶名称还是没变
            if (!saveStackData.equals(fragmentNames.toString())) {
//                topFragmentName = "";
                saveStackData = fragmentNames.toString();//保存本次栈信息
            }

            return fragmentNames;
        }

        /**
         * 获取当前 Fragment 回退栈中 Fragment 所有名称
         *
         * @return
         */
        public List<String> getBackStackFragmentSimpleNames() {
            List<String> fragmentNames = new ArrayList<>();

            String name = "";
            for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                String[] fragmentDataArray = fragmentManager.getBackStackEntryAt(i).toString().split(" ");
                try {
                    String fragmentName = fragmentDataArray[2].substring(0, fragmentDataArray[2].length() - 1);//fragment名称
                    int lastIndex = fragmentName.lastIndexOf(".");
                    if (lastIndex != -1) {
                        name = fragmentName.substring(lastIndex + 1);
                    } else {
                        name = "[null]";
                    }
                    fragmentNames.add(name);

                } catch (Exception e) {
                    errs(getLineInfo() + "报错:" + e);
                }
            }
            return fragmentNames;
        }

        /**
         * Fragment 属性实体类
         */
        public class BackStackFragmentBean {

            private int stackIndex;
            private String hashCode;
            private String fragmentName;

            public BackStackFragmentBean() {
            }

            public BackStackFragmentBean(int stackIndex, String hashCode, String fragmentName) {
                this.stackIndex = stackIndex;
                this.hashCode = hashCode;
                this.fragmentName = fragmentName;
            }

            public int getStackIndex() {
                return stackIndex;
            }

            public void setStackIndex(int stackIndex) {
                this.stackIndex = stackIndex;
            }

            public String getHashCode() {
                return hashCode;
            }

            public void setHashCode(String hashCode) {
                this.hashCode = hashCode;
            }

            public String getFragmentName() {
                return fragmentName;
            }

            public void setFragmentName(String fragmentName) {
                this.fragmentName = fragmentName;
            }

            @Override
            public String toString() {
                return "BackStackFragmentBean{" +
                        "stackIndex=" + stackIndex +
                        ", hashCode='" + hashCode + '\'' +
                        ", fragmentName='" + fragmentName + '\'' +
                        '}';
            }
        }

        /**
         * 获取Fragment栈中所有的 Fragment
         *
         * @return
         */
        public List<Fragment> getStackFragments() {
            if (fragmentManager != null) {
                return fragmentManager.getFragments();
            }
            return null;
        }

        /**
         * 获取Fragment栈中所有的 Fragment
         *
         * @return
         */
        public Fragment getStackFragment(String fragmentName) {
            if (fragmentManager != null) {
                List<Fragment> fragments = fragmentManager.getFragments();
                if (fragments != null) {
                    for (Fragment fragment : fragments) {
                        if (fragment.getClass().getName().equals(fragmentName)) {
                            return fragment;
                        }
                    }
                }
                return null;
            }
            return null;
        }

        /**
         * 获取栈中所有的Fragment全称
         *
         * @return
         */
        public List<String> getStackFragmentNames() {
            if (fragmentManager != null) {
                List<String> fragmentNames = new ArrayList<>();
                List<Fragment> fragments = fragmentManager.getFragments();

                if (fragments.size() == 0) {
                    return fragmentNames;
                }

                for (Fragment fragment : fragments) {
                    fragmentNames.add(fragment.getClass().getName());
                }

                return fragmentNames;
            }
            return null;
        }

        /**
         * 获取栈中所有的Fragment简称
         *
         * @return
         */
        public List<String> getStackFragmentSimpleNames() {
            if (fragmentManager != null) {
                List<Fragment> fragments = fragmentManager.getFragments();
                if (fragments.size() == 0) {
                    return null;
                }
                List<String> fragmentNames = new ArrayList<>();
                for (Fragment fragment : fragments) {
                    fragmentNames.add(fragment.getClass().getSimpleName());
                }
                return fragmentNames;
            }
            return null;
        }

        //===================================================== 构建 GT_Fragment 对象 ====================================

        /**
         * 初始化 Activity (不推荐)
         *
         * @param fragmentActivity
         * @return
         */
        public GT_Fragment BuildActivity(FragmentActivity fragmentActivity) {
            activity = fragmentActivity;
            return gt_fragment;
        }

        /**
         * 初始化 GT_Fragment 不指定 首页的容器
         *
         * @param fragmentActivity Activity 活动
         * @return
         */
        public static GT_Fragment Build(FragmentActivity fragmentActivity, Bundle bundle) {
            if (bundle == null || GT_Fragment.fragmentManager == null) {
                topFragmentName = "";//置空
                GT_Fragment.fragmentManager = fragmentActivity.getSupportFragmentManager();
                initFragment(fragmentActivity);
            }
            return gt_fragment;
        }

        /**
         * 初始化 GT_Fragment 不指定 首页的容器
         *
         * @param fragmentActivity 活动
         * @param fragmentClass    启动 Fragment 的 class
         * @param <T>
         * @return
         */
        public static <T> GT_Fragment Build(FragmentActivity fragmentActivity, Class<T> fragmentClass, Bundle bundle, int startModel) {
            if (bundle == null || GT_Fragment.fragmentManager == null) {
                topFragmentName = "";//置空
                GT_Fragment.fragmentManager = fragmentActivity.getSupportFragmentManager();
                initFragment(fragmentActivity);

                //启动一个指定为首页的 Fragment
                gt_fragment.switchingMode(FRAGMENT);
                gt_fragment.startMode(startModel).startFragment(fragmentClass);
                gt_fragment.switchingMode(SWITCHING_MODE);//回复当前切换方式
            }
            return gt_fragment;
        }

        /**
         * 初始化 GT_Fragment 不指定 首页的容器
         *
         * @param fragmentActivity 活动
         * @param fragment         启动 Fragment 的 class
         * @param <T>
         * @return
         */
        public static <T> GT_Fragment Build(FragmentActivity fragmentActivity, Fragment fragment, Bundle bundle, int startModel) {
            if (bundle == null || GT_Fragment.fragmentManager == null) {
                topFragmentName = "";//置空
                GT_Fragment.fragmentManager = fragmentActivity.getSupportFragmentManager();
                initFragment(fragmentActivity);

                //启动一个指定为首页的 Fragment
                gt_fragment.switchingMode(FRAGMENT);
                gt_fragment.startMode(startModel).startFragment(fragment);
                gt_fragment.switchingMode(SWITCHING_MODE);//回复当前切换方式
            }
            return gt_fragment;
        }

        /**
         * 初始化 GT_Fragment 指定首页 容器
         *
         * @param fragmentActivity Activity 活动
         * @param homeFragmentId   指定指定的 Fragment容器 Id
         * @return
         */
        public static GT_Fragment Build(FragmentActivity fragmentActivity, int homeFragmentId, Bundle bundle) {
            if (bundle == null || GT_Fragment.fragmentManager == null) {
                topFragmentName = "";//置空
                GT_Fragment.homeFragmentId = homeFragmentId;
                GT_Fragment.fragmentManager = fragmentActivity.getSupportFragmentManager();
                initFragment(fragmentActivity);
            }
            return gt_fragment;
        }

        /**
         * 初始化 GT_Fragment 指定首页 容器
         *
         * @param fragmentActivity 活动
         * @param fragmentClass    启动 Fragment 的 class
         * @param <T>
         * @return
         */
        public static <T> GT_Fragment Build(FragmentActivity fragmentActivity, int homeFragmentId, Class<T> fragmentClass, Bundle bundle, int startModel) {
            if (bundle == null || GT_Fragment.fragmentManager == null) {
                topFragmentName = "";//置空
                GT_Fragment.homeFragmentId = homeFragmentId;
                GT_Fragment.fragmentManager = fragmentActivity.getSupportFragmentManager();
                initFragment(fragmentActivity);

                //启动一个指定为首页的 Fragment
                gt_fragment.switchingMode(FRAGMENT);
                gt_fragment.startMode(startModel)
                        .startFragment(homeFragmentId, fragmentClass);
                gt_fragment.switchingMode(SWITCHING_MODE);//回复当前切换方式
            }
            return gt_fragment;
        }

        /**
         * 初始化 GT_Fragment 指定首页 容器
         *
         * @param fragmentActivity 活动
         * @param fragment         启动 Fragment 的 class
         * @param <T>
         * @return
         */
        public static <T> GT_Fragment Build(FragmentActivity fragmentActivity, int homeFragmentId, Fragment fragment, Bundle bundle, int startModel) {
            if (bundle == null || GT_Fragment.fragmentManager == null) {
                topFragmentName = "";//置空
                GT_Fragment.homeFragmentId = homeFragmentId;
                GT_Fragment.fragmentManager = fragmentActivity.getSupportFragmentManager();
                initFragment(fragmentActivity);

                //启动一个指定为首页的 Fragment
                gt_fragment.switchingMode(FRAGMENT);
                gt_fragment.startMode(startModel).startFragment(homeFragmentId, fragment);
                gt_fragment.switchingMode(SWITCHING_MODE);//回复当前切换方式
            }
            return gt_fragment;
        }

        /**
         * 给予 GT 内部使用的初始化 方法
         * 构建 GT_Fragment 对象的时候构建 Main 容器
         *
         * @param activity
         */
        private static void initFragment(Activity activity) {

            //实例化一个 Fragment 容器
            FrameLayout frameLayout = new FrameLayout(activity);

            //为 Fragment 容器设置 ID值
            frameLayout.setId(FRAGMENT_ID);

            //初始化 Fragment 容器 ID
            mainFragmentId = frameLayout.getId();

            //如果没有指定 首页 Fragment 容器Id 那就模式使用 Main 容器
            if (homeFragmentId == 0) {
                homeFragmentId = mainFragmentId;
            }

            //将 Fragment 容器设置屏幕大小
            /*DisplayMetrics outMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
            //将Fragment 容器添加到 视图中
            activity.addContentView(frameLayout, new ViewGroup.LayoutParams(outMetrics.widthPixels, outMetrics.heightPixels));
*/


            //监听回退栈 增加 fragment 与 减少 Fragment 都会进行触发 就算外部使用了监听覆盖了这里的监听，也不会对启动模式有影响
            listener = new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    topFragmentName = "";//清空指向顶端的 Fragment
                }
            };

            //注册监听
            fragmentManager.addOnBackStackChangedListener(listener);

        }

        //=========================================== 启动新的 Fragment ====================================


        /**
         * 启动新的 Fragment
         *
         * @param fragmentClass 新 Fragment 的 class
         * @param <T>
         * @return
         */
        public <T> GT_Fragment startFragment(Class<T> fragmentClass) {

            //判null 与 判断当前显示的Fragment是否为需要打开的Fragment
            if (fragmentClass == null) return this;

            T fragment = null;

            try {
                fragment = fragmentClass.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

            //启动 Fragment
            FragmentTransaction transaction = getTransaction();
            if (modeManagement(transaction, fragmentClass)) {
                fragmentSwitchingModeManagement(mainFragmentId, transaction, (Fragment) fragment, fragmentClass);
            }

            return this;
        }

        /**
         * 启动新的 Fragment
         *
         * @param resLayout     指定打开 Fragment 的容器
         * @param fragmentClass 启动新的 Fragment 的 class
         * @param <T>
         * @return
         */
        public <T> GT_Fragment startFragment(int resLayout, Class<T> fragmentClass) {
            //判null 与 判断当前显示的Fragment是否为需要打开的Fragment
            if (fragmentClass == null) return this;

            T fragment = null;

            try {
                fragment = fragmentClass.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

            //启动 Fragment
            FragmentTransaction transaction = getTransaction();
            if (modeManagement(transaction, fragmentClass)) {
                fragmentSwitchingModeManagement(resLayout, transaction, (Fragment) fragment, fragmentClass);
            }

            return this;
        }

        /**
         * 启动新的 Fragment
         *
         * @param fragment 启动新的 Fragment
         * @return
         */
        public GT_Fragment startFragment(Fragment fragment) {

            //判null 与 判断当前显示的Fragment是否为需要打开的Fragment
            if (fragment == null) return this;

            //启动 Fragment
            FragmentTransaction transaction = getTransaction();
            if (modeManagement(transaction, fragment.getClass())) {
                fragmentSwitchingModeManagement(mainFragmentId, transaction, fragment, fragment.getClass());
            }
            return this;
        }

        /**
         * 启动新的 Fragment
         *
         * @param resLayout 指定打开 Fragment 的容器
         * @param fragment  启动新的 Fragment
         * @return
         */
        public GT_Fragment startFragment(int resLayout, Fragment fragment) {

            //判null 与 判断当前显示的Fragment是否为需要打开的Fragment
            if (fragment == null) return this;

            //启动 Fragment
            FragmentTransaction transaction = getTransaction();
            if (modeManagement(transaction, fragment.getClass())) {
                fragmentSwitchingModeManagement(resLayout, transaction, fragment, fragment.getClass());
            }
            return this;
        }

        /**
         * 启动新的 Fragment 使用 Home 容器
         *
         * @param fragmentClass 新 Fragment 的 class
         * @param <T>
         * @return
         */
        public <T> GT_Fragment startFragmentHome(Class<T> fragmentClass) {

            //设置本次 Fragment 的启动模式
            startMode(MODE_HOME);

            //判null 与 判断当前显示的Fragment是否为需要打开的Fragment
            if (fragmentClass == null) return this;

            //执行 Activity 与 Fragment 切换模式的业务流程
            T fragment = null;
            try {
                fragment = fragmentClass.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

            //启动 Fragment
            FragmentTransaction transaction = getTransaction();

            if (modeManagement(transaction, fragmentClass)) {
                fragmentSwitchingModeManagement(homeFragmentId, transaction, (Fragment) fragment, fragmentClass);
            }

            return this;
        }

        /**
         * 启动新的 Fragment 使用 Home 容器
         *
         * @param fragment 启动新的 Fragment
         * @return
         */
        public GT_Fragment startFragmentHome(Fragment fragment) {

            //设置本次 Fragment 的启动模式
            startMode(MODE_HOME);

            //判null 与 判断当前显示的Fragment是否为需要打开的Fragment
            if (fragment == null) return this;

            //启动 Fragment
            FragmentTransaction transaction = getTransaction();
            if (modeManagement(transaction, fragment.getClass())) {
                fragmentSwitchingModeManagement(homeFragmentId, transaction, fragment, fragment.getClass());
            }
            return this;
        }


        //=========================================== GT_Fragment 功能性方法 ====================================

        /**
         * 为基类Fragment初始化必要的属性（推荐使用）
         *
         * @param view
         */
        public static void initBaseFragment(View view) {
            if (view == null) return;

            try {
                Drawable drawable = view.getBackground();//获取 Fragment 背景
                if (!(drawable instanceof BitmapDrawable)) { //如果背景没有设置背景图片
                    ColorDrawable colorDrawable = (ColorDrawable) view.getBackground();//获取 View 背景颜色
                    if (colorDrawable == null) {//如果背景颜色没有设置
                        view.setBackgroundColor(Color.WHITE);// 设置为 默认的 白色
                    }
                }
            } catch (Exception e) {
                err("Fragment BG Exception :" + e);
            }


            //解决 Fragment 点击事件穿透问题
            view.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }

        /**
         * 监听 Fragment 返回键
         *
         * @param view          Fragment 的 View
         * @param onKeyListener new  一个内部类
         *                      注意使用正确实例：
         *                      <p>
         *                      private long exitTime = 0;
         *                      //按两下返回键 弹出退出提示
         *                      private void onKeyDown(){
         *                      gt_fragment.onKeyDown(getView(), new View.OnKeyListener() {
         * @Override public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
         * if(keyCode == 4 && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
         * if((System.currentTimeMillis()-exitTime) > 2000){
         * exitTime = System.currentTimeMillis();
         * }else{
         * GT.toast_s("退出");
         * }
         * return true;//只将 返回按键进行监听
         * }
         * return false;//其余的如 音量小 音量大什么的，交给 Activity 管理
         * }
         * });
         * }
         */
        public static void onKeyDown(View view, View.OnKeyListener onKeyListener) {

            if (view == null || onKeyListener == null) {
                return;
            }

            /**
             * 判断用例：如   if(keyCode == 4 && keyEvent.getAction() == KeyEvent.ACTION_DOWN)
             *  返回键：
             *  【keyCode:4，KEYCODE_ENTER:66==getAction:0，ACTION_DOWN:0
             *  【keyCode:4，KEYCODE_ENTER:66==getAction:1，ACTION_DOWN:0
             *
             *  音量小：
             *  【keyCode:25，KEYCODE_ENTER:66==getAction:0，ACTION_DOWN:0
             *  【keyCode:25，KEYCODE_ENTER:66==getAction:1，ACTION_DOWN:0
             *
             *  音量大：
             *  【keyCode:24，KEYCODE_ENTER:66==getAction:0，ACTION_DOWN:0
             *  【keyCode:24，KEYCODE_ENTER:66==getAction:1，ACTION_DOWN:0
             */

            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setOnKeyListener(onKeyListener);
        }


        /**
         * 关闭最顶端的 Fragment (将栈顶的 Fragment 退出去)
         *
         * @return
         */
        public GT_Fragment finish() {
            if (fragmentManager != null) {
                fragmentManager.popBackStack();//将加入退回栈的最顶层 Fragment 进行退栈操作
            }
            return this;
        }

        /**
         * 在关闭 Fragment 同时传递数据回去
         *
         * @param intent
         * @param <T>
         * @return
         */
        public <T> GT_Fragment finish(Intent intent) {

            //抢救一下 activity 为空的情况
            if (activity == null) {
                activity = getActivity();//从当前Fragment 中获取Activity
                if (activity == null) {
                    activity = gt_fragment.getActivity();//紧急情况使用 gt 内部获取Activity
                }
            }

            //如果传递的值不为null 那就传递
            if (intent != null && activity != null) {
                ((GT_Activity.BaseActivity) activity).onActivityResult(1, 1, intent);
            }

            if (fragmentManager != null) {
                fragmentManager.popBackStack();//将加入退回栈的最顶层 Fragment 进行退栈操作
            }
            return this;
        }

        /**
         * 在关闭 Fragment 同时传递数据回去
         *
         * @param activity
         * @param intent
         * @param <T>
         * @return
         */
        public <T> GT_Fragment finish(Activity activity, Intent intent) {

            //如果传递的值不为null 那就传递
            if (intent != null && activity != null) {
                ((GT_Activity.BaseActivity) activity).onActivityResult(1, 1, intent);
            }

            if (fragmentManager != null) {
                fragmentManager.popBackStack();//将加入退回栈的最顶层 Fragment 进行退栈操作
            }
            return this;
        }

        /**
         * 在关闭 Fragment 同时传递数据回去
         *
         * @param intent
         * @param <T>
         * @return
         */
        public <T> GT_Fragment finish(Intent intent, int requestCode, int resultCode) {

            //抢救一下 activity 为空的情况
            if (activity == null) {
                activity = getActivity();//从当前Fragment 中获取Activity
                if (activity == null) {
                    activity = gt_fragment.getActivity();//紧急情况使用 gt 内部获取Activity
                }
            }

            //如果传递的值不为null 那就传递
            if (intent != null && activity != null) {
                ((GT_Activity.BaseActivity) activity).onActivityResult(requestCode, resultCode, intent);
            }

            if (fragmentManager != null) {
                fragmentManager.popBackStack();//将加入退回栈的最顶层 Fragment 进行退栈操作
            }
            return this;
        }

        /**
         * 在关闭 Fragment 同时传递数据回去
         *
         * @param activity
         * @param intent
         * @param <T>
         * @return
         */
        public <T> GT_Fragment finish(Activity activity, Intent intent, int requestCode, int resultCode) {

            //如果传递的值不为null 那就传递
            if (intent != null && activity != null) {
                ((GT_Activity.BaseActivity) activity).onActivityResult(requestCode, resultCode, intent);
            }

            if (fragmentManager != null) {
                fragmentManager.popBackStack();//将加入退回栈的最顶层 Fragment 进行退栈操作
            }
            return this;
        }


        /**
         * 指定 关闭 Fragment
         *
         * @param fragmentClass
         * @param <T>
         * @return
         */
        public <T> GT_Fragment finish(Class<T> fragmentClass) {
            if (fragmentManager != null) {
                fragmentManager.popBackStackImmediate(fragmentClass.getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);//将加入退回栈的最顶层 Fragment 进行退栈操作
            }
            return this;
        }

        /**
         * 指定 关闭 Fragment
         *
         * @param fragmentName
         * @param <T>
         * @return
         */
        public <T> GT_Fragment finish(String fragmentName) {
            if (fragmentManager != null) {
                fragmentManager.popBackStackImmediate(fragmentName, FragmentManager.POP_BACK_STACK_INCLUSIVE);//将加入退回栈的最顶层 Fragment 进行退栈操作
            }
            return this;
        }

        /**
         * 指定 关闭 Fragment
         *
         * @param fragmentClass  想要关闭的 FragmentClass
         * @param isCloseOneself 是否关闭自己
         * @param <T>
         * @return
         */
        public <T> GT_Fragment finish(Class<T> fragmentClass, boolean isCloseOneself) {
            if (fragmentManager != null) {
                if (isCloseOneself) {
                    fragmentManager.popBackStackImmediate(fragmentClass.getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);//将加入退回栈的最顶层 Fragment 进行退栈操作
                } else {
                    fragmentManager.popBackStackImmediate(fragmentClass.getName(), 0);//将加入退回栈的最顶层 Fragment 进行退栈操作
                }
            }
            return this;
        }

        /**
         * 指定 关闭 Fragment
         *
         * @param fragmentName   想要关闭的 Fragment 名称
         * @param isCloseOneself 是否关闭自己
         * @param <T>
         * @return
         */
        public <T> GT_Fragment finish(String fragmentName, boolean isCloseOneself) {
            if (fragmentManager != null) {
                if (isCloseOneself) {
                    fragmentManager.popBackStackImmediate(fragmentName, FragmentManager.POP_BACK_STACK_INCLUSIVE);//将加入退回栈的最顶层 Fragment 进行退栈操作 将自己也关闭
                } else {
                    fragmentManager.popBackStackImmediate(fragmentName, 0);//将加入退回栈的最顶层 Fragment 进行退栈操作 只关闭自己以上所有的 Fragment
                }
            }
            return this;
        }

        /**
         * 查找栈中 已经存在的 fragment
         *
         * @param fragmentClass
         * @param <T>
         * @return
         */
        public <T> Fragment queryFragmentByClass(Class<T> fragmentClass) {
            return fragmentManager.findFragmentByTag(fragmentClass.getName());
        }

        //=========================================== GT_Fragment 提供的公共方法 ====================================


        //=========================================== GT_Fragment 基类 ====================================

        /**
         * 封装第一代 Fragment
         */
        public abstract static class BaseFragment extends Fragment {

            // 是否恢复Fragment数据
            protected View view;// 用于存储 Fragment

            protected boolean isRecoverBundle() {
                return false;
            }

            // 定义 Activity
            public Activity activity;

            // 如果重写该方法了的话就需要自己写 接收 Activity
            @Override
            public void onAttach(Context context) {
                super.onAttach(context);
                // 使用官方推荐的方法持有 Activity
                activity = (Activity) context;

                // 如果因为异常原因持有失败就采取紧急措施，获取初始化的 Activity
                if (activity == null) {
                    activity = GT_Fragment.gt_fragment.getActivity();
                }

            }

            /**
             * 返回要加载的布局
             *
             * @return
             */
            protected int loadLayout() {
                return 0;
            }

            /**
             * 初始化 View 数据
             *
             * @param view
             * @param savedInstanceState
             */
            protected abstract void initView(View view, @Nullable Bundle savedInstanceState);

            /**
             * 用户在初始化布局前设置必要的参数 当前方法可不重写
             *
             * @param view
             */
            protected void createView(View view) {
            }

            protected View findViewById(int id) {
                if (view == null) return null;
                return view.findViewById(id);
            }

            /**
             * 退出当前 Fragment
             */
            protected void finish() {
                if (gt_fragment != null) {
                    gt_fragment.finish();
                }
            }

            /**
             * 退出当前 Fragment 并传递数据
             *
             * @param intent
             */
            protected void finish(Intent intent) {
                if (gt_fragment != null) {
                    gt_fragment.finish(intent);
                }
            }

            /**
             * 退出当前 Fragment 并传递数据
             *
             * @param activity
             * @param intent
             */
            protected void finish(Activity activity, Intent intent) {
                if (gt_fragment != null) {
                    gt_fragment.finish(activity, intent);
                }
            }

            /**
             * 退出当前 Fragment 并传递数据
             *
             * @param intent
             * @param requestCode
             * @param resultCode
             */
            protected void finish(Intent intent, int requestCode, int resultCode) {
                if (gt_fragment != null) {
                    gt_fragment.finish(intent, requestCode, resultCode);
                }
            }

            /**
             * 退出当前 Fragment 并传递数据
             *
             * @param activity
             * @param intent
             * @param requestCode
             * @param resultCode
             */
            protected void finish(Activity activity, Intent intent, int requestCode, int resultCode) {
                if (gt_fragment != null) {
                    gt_fragment.finish(activity, intent, requestCode, resultCode);
                }
            }

            /**
             * @param dialogFragment
             * @跳转其他的 DialogFragment
             */
            protected void startDialogFragment(DialogFragment dialogFragment) {
                dialogFragment.setTargetFragment(this, 1);
                dialogFragment.show(getFragmentManager(), dialogFragment.getClass().toString());// 弹出退出提示
            }

            /**
             * @param dialogFragment
             * @跳转其他的 DialogFragment
             */
            protected void startDialogFragment(Class<?> dialogFragmentClass) {
                DialogFragment dialogFragment = null;
                try {
                    dialogFragment = (DialogFragment) dialogFragmentClass.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }

                dialogFragment.setTargetFragment(this, 1);
                dialogFragment.show(getFragmentManager(), dialogFragment.getClass().toString());// 弹出退出提示
            }

            /**
             * @param supportFragmentManager
             * @param dialogFragment         跳转其他的
             */
            public void startDialogFragment(FragmentManager supportFragmentManager, DialogFragment dialogFragment) {
                dialogFragment.setTargetFragment(this, 1);
                dialogFragment.show(supportFragmentManager, dialogFragment.getClass().toString());// 弹出退出提示
            }

            /**
             * @param toFragment
             * @跳转 Fragment
             */
            protected void startFragment(Fragment toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragment(toFragment);
                }
            }

            /**
             * @param toFragment
             * @跳转 Fragment
             */
            protected void startFragment(Class<?> toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragment(toFragment);
                }
            }

            /**
             * 跳转Fragment
             *
             * @param fragmentId
             * @param toFragment
             */
            protected void startFragment(int fragmentId, Fragment toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragment(fragmentId, toFragment);
                }
            }

            /**
             * 跳转Fragment
             *
             * @param fragmentId
             * @param toFragment
             */
            protected void startFragment(int fragmentId, Class<?> toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragment(fragmentId, toFragment);
                }
            }


            /**
             * 跳转Fragment
             *
             * @param toFragment 跳转的Fragment
             * @param startMode  跳转该Fragment的启动模式
             */
            protected void startFragment(Fragment toFragment, int startMode) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startMode(startMode).startFragment(toFragment);
                }
            }

            /**
             * @param toFragment 跳转的Fragment
             * @param startMode  跳转该Fragment的启动模式
             */
            protected void startFragment(Class<?> toFragment, int startMode) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startMode(startMode).startFragment(toFragment);
                }
            }

            /**
             * 跳转的Fragment
             *
             * @param fragmentId 跳转的容器
             * @param toFragment 跳转的Fragment
             * @param startMode  跳转该Fragment的启动模式
             */
            protected void startFragment(int fragmentId, Fragment toFragment, int startMode) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startMode(startMode).startFragment(fragmentId, toFragment);
                }
            }

            /**
             * @param fragmentId 跳转的容器
             * @param toFragment 跳转的Fragment
             * @param startMode  跳转该Fragment的启动模式
             */
            protected void startFragment(int fragmentId, Class<?> toFragment, int startMode) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startMode(startMode).startFragment(fragmentId, toFragment);
                }
            }


            /**
             * @param toFragment
             * @跳转 Fragment
             */
            protected void startFragmentHome(Fragment toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragmentHome(toFragment);
                }
            }

            /**
             * @param toFragment
             * @跳转 Fragment
             */
            protected void startFragmentHome(Class<?> toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragmentHome(toFragment);
                }
            }

            /**
             * 使用首页模式启动Fragment
             *
             * @param fragmentId
             * @param toFragment
             */
            protected void startFragmentHome(int fragmentId, Fragment toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startMode(GT_Fragment.MODE_HOME);
                    GT_Fragment.gt_fragment.startFragment(fragmentId, toFragment);
                }
            }

            /**
             * 使用首页模式启动Fragment
             *
             * @param fragmentId
             * @param toFragment
             */
            protected void startFragmentHome(int fragmentId, Class<?> toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startMode(GT_Fragment.MODE_HOME);
                    GT_Fragment.gt_fragment.startFragment(fragmentId, toFragment);
                }
            }

            /**
             * 启动悬浮窗
             *
             * @param context
             * @param toFragment
             */
            protected void startFloatingWindow(Context context, Class<?> toFragment) {
                if (context == null) {
                    context = GT_Fragment.gt_fragment.getActivity();
                }
                if (context != null) {
                    if (android.os.Build.VERSION.SDK_INT >= 23) {
                        if (!Settings.canDrawOverlays(context)) {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + context.getPackageName()));
                            startActivityForResult(intent, 0);
                        } else {
                            context.startService(new Intent(context, toFragment));
                        }
                    } else {
                        context.startService(new Intent(context, toFragment));
                    }
                }

            }

            /**
             * 启动悬浮窗
             *
             * @param toFragment
             */
            protected void startFloatingWindow(Class<?> toFragment) {
                if (activity == null) {
                    activity = GT_Fragment.gt_fragment.getActivity();
                }

                if (activity != null) {
                    if (android.os.Build.VERSION.SDK_INT >= 23) {
                        if (!Settings.canDrawOverlays(activity)) {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + activity.getPackageName()));
                            startActivityForResult(intent, 0);
                        } else {
                            activity.startService(new Intent(activity, toFragment));
                        }
                    } else {
                        activity.startService(new Intent(activity, toFragment));
                    }
                }
            }

            protected GT_Fragment gt_fragment;

            public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View view = inflater.inflate(loadLayout(), container, false);
                createView(view);
                return view;
            }

            /**
             * 主要实现的功能 当页面可见的时候，才加载当前页面数据。 没有打开的页面，就不会预加载
             */
            protected void loadData() {
            }

            //是否解决EditText bug
            private boolean isSolveEditTextBug = true;

            public boolean isSolveEditTextBug() {
                return isSolveEditTextBug;
            }

            /**
             * 设置是否解决EditText bug
             *
             * @param solveEditTextBug
             */
            public void setSolveEditTextBug(boolean solveEditTextBug) {
                isSolveEditTextBug = solveEditTextBug;
            }

            @Override
            public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
                super.onViewCreated(view, savedInstanceState);

                //防止点击穿透
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                if (this.gt_fragment == null) {
                    this.gt_fragment = GT_Fragment.gt_fragment;
                }

                initView(view, savedInstanceState);// 主要方法
                loadData();
                // 如果切换方式是 Fragment 那就注册返回事件 如果是 Activity 请自行去注册 返回按钮事件
                GT_Fragment.onKeyDown(view, new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                            return onBackPressed();// 回调按下返回键
                        }
                        return false;
                    }
                });

                //是否解决EditText bug
                if (isSolveEditTextBug) {
                    //给EditText 组件设置返回事件
                    Thread.runJava(new Runnable() {
                        @Override
                        public void run() {
                            setViewBackListener(view);
                            setEditTextRequestFocus(view);
                        }
                    });
                }

            }


            /**
             * 给EditText 组件设置返回事件
             *
             * @param viewGroup
             */
            protected void setViewBackListener(View viewLayout) {

                ViewGroup viewGroup = null;

                //如果是Edit类型的那就监听返回事件
                if (viewLayout instanceof EditText) {

                    if (activity != null) {
                        Thread.runAndroid(activity, new Runnable() {
                            @Override
                            public void run() {
                                GT_Fragment.onKeyDown(viewLayout, new View.OnKeyListener() {
                                    @Override
                                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                                        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                                            return onBackPressed();// 回调按下返回键
                                        }
                                        return false;
                                    }
                                });
                            }
                        });
                    }
                    return;
                } else if (viewLayout instanceof ViewGroup) {

                    try {
                        viewGroup = (ViewGroup) viewLayout;
                    } catch (Exception e) {
//                        logs("异常返回 viewGroup:" + viewGroup.getClass());
                        return;
                    }

                    int childCount = viewGroup.getChildCount();
                    if (childCount == 0) return;

                    for (int i = 0; i < childCount; i++) {
                        View childAt = viewGroup.getChildAt(i);
                        setViewBackListener(childAt);
                    }
                }

            }

            /**
             * 设置最顶端的EditText为获取焦点
             *
             * @param viewLayout
             */
            protected boolean setEditTextRequestFocus(View viewLayout) {
                ViewGroup viewGroup = null;
                //如果是Edit类型的那就监听返回事件
                if (viewLayout instanceof EditText) {
                    if (activity != null) {
                        Thread.runAndroid(activity, new Runnable() {
                            @Override
                            public void run() {
                                viewLayout.requestFocus();
                            }
                        });
                    }
                    return true;
                } else if (viewLayout instanceof ViewGroup) {
                    try {
                        viewGroup = (ViewGroup) viewLayout;
                    } catch (Exception e) {
                        return false;
                    }
                    int childCount = viewGroup.getChildCount();
                    if (childCount == 0) return false;
                    for (int i = 0; i < childCount; i++) {
                        View childAt = viewGroup.getChildAt(i);
                        if (setEditTextRequestFocus(childAt)) {
                            return true;
                        }
                    }
                }
                return false;
            }

            /**
             * 返回 true 则劫持返回事件
             *
             * @return
             */
            protected boolean onBackPressed() {
                return false;
            }

            @Override
            public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                onResultData(requestCode, resultCode, data);
            }

            /**
             * 反馈数据
             *
             * @param requestCode
             * @param resultCode
             * @param data
             */
            protected void onResultData(int requestCode, int resultCode, Intent data) {

            }

        }

        /**
         * 封装第二代 Fragment
         */
        public abstract static class AnnotationFragment extends BaseFragment {

            //布局ID
            private int resLayout;

            public void setLayout(int resLayout) {
                this.resLayout = resLayout;
            }

            @Override
            public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                GT.build(this);// 注解赋值布局ID值
                View view = inflater.inflate(resLayout, container, false);
                createView(view);
                return view;
            }

            @Override
            protected void initView(View view, Bundle savedInstanceState) {
                GT.build(this);

            }
        }

        /**
         * 封装第三代 Fragment
         * 封装特点：
         * 1.继承上代所有优点
         * 2.获取组件可以直接无需获取，直接使用。
         *
         * @param <T>
         */
        public abstract static class DataBindingFragment extends AnnotationFragment {

        }

        //=========================================== GT_Fragment 释放资源方法 ====================================

        /**
         * 释放资源
         */
        public void close() {

            //移除 Fragment 栈中监听器
            if (fragmentManager != null && listener != null) {
                fragmentManager.removeOnBackStackChangedListener(listener);
            }

            if (activity != null) {
                activity = null;
            }

            if (fragmentNames != null) {
                fragmentNames.clear();
            }

            if (fragmentBeanList != null) {
                fragmentBeanList.clear();
            }

            if (gt_fragment != null) {
                gt_fragment = null;
            }

        }


    }

    /**
     * 对话框封装类
     */
    public static class GT_Dialog implements SaveObject.SaveBean {

        /**
         * AlertDialog.Builder 对话框类
         */
        public static class GT_AlertDialog extends AlertDialog.Builder {

            /**
             * 初始化 上下文
             *
             * @param context
             */
            public GT_AlertDialog(Context context) {
                super(context);
            }


            public interface OnGetDataListener {
                void getData(Object data);
            }

            /**
             * 设置按钮的对话框
             *
             * @param img             图标
             * @param title           标题
             * @param message         信息
             * @param btnName         第一个按钮名称
             * @param onClickListener 单击事件
             * @return 返回对话框操作对象
             */
            public AlertDialog.Builder dialogButton(int img, String title, String message, boolean isCancelable, String btnName, DialogInterface.OnClickListener onClickListener) {
                setTitle(title);
                setIcon(img);
                setMessage(message);   //设置 标题、图标、消息
                setCancelable(isCancelable);
                if (btnName != null && onClickListener != null) {
                    setNegativeButton(btnName, onClickListener);//设置单击事件
                }
                return this;
            }

            /**
             * 两个按钮对话框
             *
             * @param img              图标
             * @param title            标题
             * @param message          内容
             * @param buttonName1      左边按钮名称
             * @param onClickListener1 左边按钮单击事件
             * @param buttonName2      右边按钮名称
             * @param onClickListener2 右边按钮单击事件
             * @return
             */
            public AlertDialog.Builder dialogTwoButton(int img, String title, String message, boolean isCancelable, String buttonName1, DialogInterface.OnClickListener onClickListener1, String buttonName2, DialogInterface.OnClickListener onClickListener2) {
                setIcon(img);
                setTitle(title);
                setMessage(message);
                setCancelable(isCancelable);
                setPositiveButton(buttonName1, onClickListener1).
                        setNegativeButton(buttonName2, onClickListener2);
                return this;
            }

            /**
             * 设置列表选项对话框
             * @param img       图标
             * @param title     标题
             * @param texts     类表项
             * @return 返回对话框对象
             */
            /**
             * @param img             图标
             * @param title           标题
             * @param items           类表项
             * @param onClickListener 单击事件
             * @return
             */
            public AlertDialog.Builder dialogList(int img, String title, boolean isCancelable, final String[] items, DialogInterface.OnClickListener onClickListener) {
                setIcon(img);
                setTitle(title);
                setCancelable(isCancelable);
                if (items != null && onClickListener != null)
                    setItems(items, onClickListener); //添加列表项
                return this;
            }

            /**
             * 设置单选列表选项对话框
             *
             * @param img             图标
             * @param title           标题
             * @param item            列表项
             * @param onClickListener 单击事件
             * @return 返回对话框对象
             */
            private int checkedItemIndex = 0;

            public AlertDialog.Builder dialogSingleChoiceList(int img, String title, boolean isCancelable, String[] items, int checkedItem, String buttonName, OnGetDataListener onGetDataListener) {
                setIcon(img);
                setTitle(title);
                setCancelable(isCancelable);
                checkedItemIndex = checkedItem;
                setSingleChoiceItems(items, checkedItem,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                checkedItemIndex = which;
                            }
                        }).setPositiveButton(buttonName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onGetDataListener.getData(items[checkedItemIndex]);
                    }
                });
                return this;
            }

            /**
             * 设置多选列表对话框
             *
             * @param img             图标
             * @param title           标题
             * @param items           列表
             * @param onClickListener 单击事件
             * @return 返回对话框对象
             */
            private List<String> selectItemList;

            public AlertDialog.Builder dialogMultiChoice(int img, String title, boolean isCancelable, String[] items, boolean[] initChoiceSets, String buttonName, OnGetDataListener onGetDataListener) {
                if (selectItemList == null) {
                    selectItemList = new ArrayList<>();
                } else {
                    selectItemList.clear();
                }

                //初始化加入默认选项
                for (int i = 0; i < initChoiceSets.length; i++) {
                    if (initChoiceSets[i]) {
                        selectItemList.add(items[i]);
                    }
                }

                // 设置默认选中的选项，全为false默认均未选中
                setIcon(img);
                setTitle(title);
                setCancelable(isCancelable);
                setMultiChoiceItems(items, initChoiceSets,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (which != -1) {
                                    if (isChecked) {
                                        selectItemList.add(items[which]);
                                    } else {
                                        selectItemList.remove(items[which]);
                                    }
                                }
                            }
                        });
                setPositiveButton(buttonName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onGetDataListener.getData(selectItemList);
                    }
                });
                return this;
            }

            /**
             * 加载对话框
             *
             * @param title        加载对话框标题
             * @param message      加载对话框内容
             * @param isCancelable 单击是否可取消
             */
            public ProgressDialog progressDialog(int img, String title, String message, boolean isCancelable) {
                ProgressDialog waitingDialog = new ProgressDialog(getContext());
                waitingDialog.setIcon(img);
                waitingDialog.setTitle(title);
                waitingDialog.setMessage(message);
                waitingDialog.setIndeterminate(true);
                waitingDialog.setCancelable(isCancelable);
                return waitingDialog;
            }

            /**
             * 进度条加载框对话框
             *
             * @param title                 标题
             * @param messgae               内容
             * @param maxValue              最大值
             * @param progress              当前值/初始值
             * @param loadingSpeed          加载速度
             * @param isCancelable          中途是否可取消
             * @param onProgressBarListener 监听进度接口
             * @return
             */
            public ProgressDialog progressBarDialog(int img, String title, String messgae, int maxValue, int progress, boolean isCancelable, OnProgressBarListener onProgressBarListener) {
                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setIcon(img);
                progressDialog.setTitle(title);
                progressDialog.setMessage(messgae);
                progressDialog.setMax(maxValue);
                progressDialog.setProgress(progress);
                progressDialog.setCancelable(isCancelable);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

                Thread.runJava(new Runnable() {
                    @Override
                    public void run() {
                        boolean isStart = false;        //是否开始加载
                        int record = 0;
                        long startTime = 0;
                        long endTime = 0;
                        while (true) {
                            //开始加载
                            if (progressDialog.getProgress() != progress && !isStart) {
                                startTime = System.currentTimeMillis();
                                isStart = true;
                                onProgressBarListener.startLoad(progressDialog.getProgress(), progressDialog.getMax());
                            }
                            //加载中
                            if (isStart && record != progressDialog.getProgress()) {
                                record = progressDialog.getProgress();
                                onProgressBarListener.underwayLoad(progressDialog.getProgress());
                            }
                            //结束加载
                            if (progressDialog.getProgress() >= progressDialog.getMax()) {
                                endTime = System.currentTimeMillis();
                                onProgressBarListener.closeLoad(endTime - startTime);
                                break;
                            }
                            Thread.sleep(100);
                        }

                    }
                });


                return progressDialog;
            }

            /**
             * 进度条对话框进度监听接口
             */
            public interface OnProgressBarListener {
                /**
                 * 开始加载
                 *
                 * @param progress     当前初始值
                 * @param maxValue     目标最大值
                 * @param loadingSpeed 加载速度
                 */
                void startLoad(int progress, int maxValue);

                /**
                 * 加载中
                 *
                 * @param progress 当前进度值
                 */
                void underwayLoad(int progress);

                /**
                 * 加载完毕
                 *
                 * @param totalElapsedTime 加载总耗时间 (ms)
                 */
                void closeLoad(long totalElapsedTime);
            }


            /**
             * 输入对话框
             *
             * @param title                 标题
             * @param message               内容
             * @param isCancelable          是否可取消
             * @param buttonName            按钮名称
             * @param onGetEditTextListener 获取输入框的内容接口
             * @return
             */
            public AlertDialog.Builder inputDialog(int img, String title, String message, boolean isCancelable, String buttonName, OnGetDataListener onGetDataListener) {
                EditText editText = new EditText(getContext());
                setIcon(img);
                setTitle(title);
                setMessage(message);
                setCancelable(isCancelable);
                setView(editText);
                setPositiveButton(buttonName,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onGetDataListener.getData(editText.getText().toString());
                            }
                        });
                return this;
            }


            /**
             * 加载自定义的 对话框 自带去掉边框
             */
            public static abstract class Loading_view extends ProgressDialog {

                public Loading_view(Context context) {
                    super(context);
                }

                public Loading_view(Context context, int theme) {
                    super(context, theme);
                }

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    init(getContext());
                }

                /**
                 * 加载布局
                 *
                 * @param context
                 */
                protected abstract void loadLayout(Context context);

                /**
                 * 初始化布局
                 *
                 * @return
                 */
                public abstract int initLayout();

                private void init(Context context) {
                    setCancelable(true);
                    setCanceledOnTouchOutside(false);
                    setContentView(initLayout());//loading的xml文件

                    loadLayout(context);//加载布局

                    WindowManager.LayoutParams params = getWindow().getAttributes();
                    params.width = WindowManager.LayoutParams.WRAP_CONTENT;
                    params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    getWindow().setAttributes(params);
                }

                @Override
                public void show() {//开启
                    super.show();
                }

                @Override
                public void dismiss() {//关闭
                    super.dismiss();
                }

            }

            /**
             * 自定义的 Dialog
             */
            public static class ViewDialog {

                private Dialog dialog;
                private View view;

                public Dialog getDialog() {
                    return dialog;
                }

                public View getView() {
                    return view;
                }

                /**
                 * 设置 ViewDialog 全屏 该方法需要在 show() 方法之后调用
                 *
                 * @param activity
                 * @return 用法如下：
                 * GT.GT_AlertDialog.ViewDialog viewDialog = new GT.GT_AlertDialog.ViewDialog()
                 * .initLayout(activity, R.layout.item_load, R.style.dialogNoBg, true, -1, 0, 0);
                 * viewDialog.getDialog().show();
                 * viewDialog.allWindow(activity);//放到此处
                 */
                public ViewDialog allWindow(Activity activity) {
                    WindowManager WindowManager = activity.getWindowManager();
                    Display display = WindowManager.getDefaultDisplay();
                    WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
                    lp.width = display.getWidth(); //设置宽度
                    lp.height = display.getHeight(); //设置宽度
                    dialog.getWindow().setAttributes(lp);
                    return this;
                }

                /**
                 * @param context 上下文
                 * @param layout  布局
                 * @return
                 */
                public ViewDialog initLayout(Context context, int layout) {
                    dialog = new Dialog(context);
                    view = LayoutInflater.from(context).inflate(layout, null);
                    dialog.setContentView(view);
                    return this;
                }

                /**
                 * @param context 上下文
                 * @param layout  布局
                 * @param Style   设置样式
                 * @return
                 */
                public ViewDialog initLayout(Context context, int layout, int Style) {
                    dialog = new Dialog(context);
                    dialog = new Dialog(context, Style);
                    view = LayoutInflater.from(context).inflate(layout, null);
                    dialog.setContentView(view);
                    return this;
                }

                /**
                 * @param context       上下文
                 * @param Style         样式
                 * @param clickExternal 外部是否可点击
                 * @param layout        布局
                 * @param transparency  透明度
                 * @param X             显示的 X 轴位置
                 * @param Y             显示的 Y 轴位置
                 * @return 当前类的对象
                 * <p>
                 * style 样式 参考：
                 * <style name="dialogNoBg">
                 * <item name="android:background">#00000000</item>
                 * <item name="android:WindowUtilsBackground">@android:color/transparent</item>
                 * <item name="android:WindowUtilsNoTitle">true</item>
                 * <item name="android:WindowUtilsFullscreen">true</item>
                 * <item name="android:WindowUtilsIsFloating">true</item>
                 * </style>
                 */
                public ViewDialog initLayout(Context context, int layout, int Style, boolean clickExternal, int transparency, int X, int Y) {

                    if (Style != 0)
                        dialog = new Dialog(context, Style);
                    else
                        dialog = new Dialog(context);

                    dialog.setCancelable(clickExternal);       //设置点击外面不会消失 true为点击外面可以被取消，false 为点击外面不可被取消

                    view = LayoutInflater.from(context).inflate(layout, null);
                    dialog.setContentView(view);

                    if (transparency != -1)
                        dialog.getWindow().setDimAmount(transparency);//设置昏暗度为0 则表示为透明， 如果是 -1 则是默认

                    //修改 自定义对话框的显示位置
                    Window dialogWindowUtils = dialog.getWindow();//随意定义个Dialog
                    WindowManager.LayoutParams lp = dialogWindowUtils.getAttributes();//实例化WindowUtils
                    //实例化WindowUtils操作者
                    lp.x = X; // 新位置X坐标
                    lp.y = Y; // 新位置Y坐标
                    dialogWindowUtils.setAttributes(lp);

                    return this;
                }

            }

        }

        /**
         * 用于辅助 DialogFragment
         */
        public abstract static class BaseDialogFragment extends DialogFragment {

            public Activity activity;
            public GT_Fragment gt_fragment;
            public View view;

            @Override
            public void onAttach(Context context) {
                super.onAttach(context);
                activity = (Activity) context;
            }

            /**
             * 返回要加载的布局
             *
             * @return
             */
            protected int loadLayout() {
                return 0;
            }

            /**
             * 初始化 View 数据
             *
             * @param view
             * @param savedInstanceState
             */
            protected abstract void initView(View view, @Nullable Bundle savedInstanceState);

            /**
             * 主要实现的功能
             */
            protected void loadData() {
            }

            /**
             * 注意： 本方法建议在 onResume 方法中使用 设置充满全屏
             */
            protected void setFullScreen() {
                Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setLayout(-1, -2);
            }

            /**
             * 设置弹出对话框时是否隐藏虚拟按键
             */
            protected void setHideBottomNav() {
                Dialog dialog = getDialog();
                Window window = dialog.getWindow();
                if (window == null || window.getDecorView() == null) {
                    return;
                }
                //不加FLAG_NOT_FOCUSABLE，dialog显示时就会显示虚拟按键
                window.addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                dialog.setOnShowListener(d -> {
                    //dialog显示之后，要清除FLAG_NOT_FOCUSABLE，否则不会弹出软键盘
                    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                });
                //隐藏虚拟按键
                hideBottomNavInner(dialog);
                window.getDecorView().setOnSystemUiVisibilityChangeListener(visibility -> {
                    //从后台重新进入时，要再次隐藏虚拟按键
                    hideBottomNavInner(dialog);
                });
            }

            /**
             * 隐藏虚拟按钮的帮助方法
             *
             * @param dialog
             */
            private static void hideBottomNavInner(Dialog dialog) {
                View decorView = dialog.getWindow().getDecorView();
                int vis = decorView.getSystemUiVisibility();
                vis |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
                decorView.setSystemUiVisibility(vis);
            }

            /**
             * 设置隐藏背景
             */
            protected void setHideBackground() {
                getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// 隐藏背景
            }

            /**
             * 设置单击外部不隐藏对话框
             */
            protected void setClickExternalNoHideDialog() {
                // 设置点击外部不会取消当前对话框
                getDialog().setCanceledOnTouchOutside(false);
            }

            /**
             * 初始化 DialogFragment 必备配置
             *
             * @param isClickExternalNoHideDialog 设置单击外部不隐藏对话框 true:单击不消失 false:单击消失
             */
            protected void initDialogFragmentFunction(boolean isClickExternalNoHideDialog) {
                setHideBackground();//设置隐藏背景
                setHideBottomNav();//设置弹出对话框时是否隐藏虚拟按键
                if (isClickExternalNoHideDialog)
                    setClickExternalNoHideDialog();//设置单击外部不隐藏对话框
            }

            /**
             * 用户在初始化布局前设置必要的参数 当前方法可不重写
             *
             * @param view
             */
            public void createView(View view) {
            }

            protected View findViewById(int id) {
                if (view == null) return null;
                return view.findViewById(id);
            }

            /**
             * 关闭当前 对话框 Fragment
             */
            public void finish() {
                dismiss();
            }

            /**
             * 在关闭 Fragment 同时传递数据回去
             *
             * @param intent
             * @param <T>
             * @return
             */
            public void finish(Intent intent) {

                if (intent != null) {

                    Fragment targetFragment = getTargetFragment();//判断打开该页面的是 Activity 还是 Fragment
                    if (targetFragment != null) {
                        //向 Fragment 反馈数据
                        targetFragment.onActivityResult(1, 1, intent);
                    } else {
                        //向 Activity 反馈数据
                        //抢救一下 activity 为空的情况
                        if (activity == null) {
                            activity = getActivity();//从当前Fragment 中获取Activity
                            if (activity == null) {
                                activity = gt_fragment.getActivity();//紧急情况使用 gt 内部获取Activity
                            }
                        }

                        //如果传递的值不为null 那就传递
                        if (activity != null) {
                            ((GT_Activity.BaseActivity) activity).onActivityResult(1, 1, intent);
                        }
                    }
                }

                dismiss();

            }

            /**
             * 在关闭 Fragment 同时传递数据回去
             *
             * @param activity
             * @param intent
             * @param <T>
             * @return
             */
            public void finish(Activity activity, Intent intent) {

                if (intent != null) {

                    Fragment targetFragment = getTargetFragment();//判断打开该页面的是 Activity 还是 Fragment
                    if (targetFragment != null) {
                        //向 Fragment 反馈数据
                        targetFragment.onActivityResult(1, 1, intent);
                    } else {
                        //向 Activity 反馈数据
                        //抢救一下 activity 为空的情况
                        if (activity == null) {
                            activity = getActivity();//从当前Fragment 中获取Activity
                            if (activity == null) {
                                activity = gt_fragment.getActivity();//紧急情况使用 gt 内部获取Activity
                            }
                        }

                        //如果传递的值不为null 那就传递
                        if (activity != null) {
                            ((GT_Activity.BaseActivity) activity).onActivityResult(1, 1, intent);
                        }
                    }
                }

                dismiss();

            }

            /**
             * 在关闭 Fragment 同时传递数据回去
             *
             * @param intent
             * @param <T>
             * @return
             */
            public void finish(Intent intent, int requestCode, int resultCode) {

                if (intent != null) {

                    Fragment targetFragment = getTargetFragment();//判断打开该页面的是 Activity 还是 Fragment
                    if (targetFragment != null) {
                        //向 Fragment 反馈数据
                        targetFragment.onActivityResult(requestCode, resultCode, intent);
                    } else {
                        //向 Activity 反馈数据
                        //抢救一下 activity 为空的情况
                        if (activity == null) {
                            activity = getActivity();//从当前Fragment 中获取Activity
                            if (activity == null) {
                                activity = gt_fragment.getActivity();//紧急情况使用 gt 内部获取Activity
                            }
                        }

                        //如果传递的值不为null 那就传递
                        if (activity != null) {
                            ((GT_Activity.BaseActivity) activity).onActivityResult(requestCode, resultCode, intent);
                        }
                    }
                }

                dismiss();

            }

            /**
             * 在关闭 Fragment 同时传递数据回去
             *
             * @param activity
             * @param intent
             * @param <T>
             * @return
             */
            public void finish(Activity activity, Intent intent, int requestCode, int resultCode) {

                if (intent != null) {

                    Fragment targetFragment = getTargetFragment();//判断打开该页面的是 Activity 还是 Fragment
                    if (targetFragment != null) {
                        //向 Fragment 反馈数据
                        targetFragment.onActivityResult(requestCode, resultCode, intent);
                    } else {
                        //向 Activity 反馈数据
                        //抢救一下 activity 为空的情况
                        if (activity == null) {
                            activity = getActivity();//从当前Fragment 中获取Activity
                            if (activity == null) {
                                activity = gt_fragment.getActivity();//紧急情况使用 gt 内部获取Activity
                            }
                        }

                        //如果传递的值不为null 那就传递
                        if (activity != null) {
                            ((GT_Activity.BaseActivity) activity).onActivityResult(requestCode, resultCode, intent);
                        }
                    }
                }

                dismiss();

            }


            /**
             * @param dialogFragment
             * @跳转其他的 DialogFragment
             */
            protected void startDialogFragment(DialogFragment dialogFragment) {
                dialogFragment.show(getFragmentManager(), dialogFragment.getClass().toString());// 弹出退出提示
            }

            /**
             * @param dialogFragment
             * @跳转其他的 DialogFragment
             */
            protected void startDialogFragment(Class<?> dialogFragmentClass) {

                DialogFragment fragment = null;

                try {
                    fragment = (DialogFragment) dialogFragmentClass.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }

                fragment.show(getFragmentManager(), fragment.getClass().toString());// 弹出退出提示
            }

            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                view = inflater.inflate(loadLayout(), container, false);
                createView(view);
                return view;
            }

            /**
             * @param toFragment
             * @跳转 Fragment
             */
            protected void startFragment(Fragment toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragment(toFragment);
                }
            }

            /**
             * @param toFragment
             * @跳转 Fragment
             */
            protected void startFragment(Class<?> toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragment(toFragment);
                }
            }

            protected void startFragment(int fragmentId, Fragment toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragment(fragmentId, toFragment);
                }
            }

            protected void startFragment(int fragmentId, Class<?> toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragment(fragmentId, toFragment);
                }
            }

            /**
             * @param toFragment
             * @跳转 Fragment
             */
            protected void startFragmentHome(Fragment toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragmentHome(toFragment);
                }
            }

            /**
             * @param toFragment
             * @跳转 Fragment
             */
            protected void startFragmentHome(Class<?> toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startFragmentHome(toFragment);
                }
            }

            /**
             * 使用首页模式启动Fragment
             *
             * @param fragmentId
             * @param toFragment
             */
            protected void startFragmentHome(int fragmentId, Fragment toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startMode(GT_Fragment.MODE_HOME);
                    GT_Fragment.gt_fragment.startFragment(fragmentId, toFragment);
                }
            }

            /**
             * 使用首页模式启动Fragment
             *
             * @param fragmentId
             * @param toFragment
             */
            protected void startFragmentHome(int fragmentId, Class<?> toFragment) {
                if (GT_Fragment.gt_fragment != null) {
                    GT_Fragment.gt_fragment.startMode(GT_Fragment.MODE_HOME);
                    GT_Fragment.gt_fragment.startFragment(fragmentId, toFragment);
                }
            }

            /**
             * 启动悬浮窗
             *
             * @param context
             * @param toFragment
             */
            protected void startFloatingWindow(Context context, Class<?> toFragment) {
                if (context == null) {
                    context = GT_Fragment.gt_fragment.getActivity();
                }
                if (context != null) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (!Settings.canDrawOverlays(context)) {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + context.getPackageName()));
                            startActivityForResult(intent, 0);
                        } else {
                            context.startService(new Intent(context, toFragment));
                        }
                    } else {
                        context.startService(new Intent(context, toFragment));
                    }
                }

            }

            /**
             * 启动悬浮窗
             *
             * @param toFragment
             */
            protected void startFloatingWindow(Class<?> toFragment) {
                if (activity == null) {
                    activity = GT_Fragment.gt_fragment.getActivity();
                }

                if (activity != null) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (!Settings.canDrawOverlays(activity)) {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + activity.getPackageName()));
                            startActivityForResult(intent, 0);
                        } else {
                            activity.startService(new Intent(activity, toFragment));
                        }
                    } else {
                        activity.startService(new Intent(activity, toFragment));
                    }
                }
            }

            //是否解决EditText bug
            private boolean isSolveEditTextBug = true;

            public boolean isSolveEditTextBug() {
                return isSolveEditTextBug;
            }

            /**
             * 设置是否解决EditText bug
             *
             * @param solveEditTextBug
             */
            public void setSolveEditTextBug(boolean solveEditTextBug) {
                isSolveEditTextBug = solveEditTextBug;
            }

            @Override
            public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
                super.onViewCreated(view, savedInstanceState);
                initView(view, savedInstanceState);// 主要方法
                loadData();
                // 监听单击返回键无效
                getDialog().setOnKeyListener(new Dialog.OnKeyListener() {

                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            return onBackPressed();
                        }
                        return false;
                    }
                });
                this.gt_fragment = GT_Fragment.gt_fragment;

                //是否解决 EditText bug
                if (isSolveEditTextBug) {
                    //给EditText 组件设置返回事件
                    Thread.runJava(new Runnable() {
                        @Override
                        public void run() {
                            setViewBackListener(view);
                            setEditTextRequestFocus(view);
                        }
                    });
                }
            }


            /**
             * 给EditText 组件设置返回事件
             *
             * @param viewGroup
             */
            protected void setViewBackListener(View viewLayout) {

                ViewGroup viewGroup = null;

                //如果是Edit类型的那就监听返回事件
                if (viewLayout instanceof EditText) {
                    if (activity != null)
                        Thread.runAndroid(activity, new Runnable() {
                            @Override
                            public void run() {
                                GT_Fragment.onKeyDown(viewLayout, new View.OnKeyListener() {
                                    @Override
                                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                                        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                                            return onBackPressed();// 回调按下返回键
                                        }
                                        return false;
                                    }
                                });
                            }
                        });
                    return;
                } else if (viewLayout instanceof ViewGroup) {

                    try {
                        viewGroup = (ViewGroup) viewLayout;
                    } catch (Exception e) {
//                        logs("异常返回 viewGroup:" + viewGroup.getClass());
                        return;
                    }

                    int childCount = viewGroup.getChildCount();
                    if (childCount == 0) return;

                    for (int i = 0; i < childCount; i++) {
                        View childAt = viewGroup.getChildAt(i);
                        setViewBackListener(childAt);
                    }
                }

            }

            /**
             * 设置最顶端的EditText为获取焦点
             *
             * @param viewLayout
             */
            protected boolean setEditTextRequestFocus(View viewLayout) {
                ViewGroup viewGroup = null;
                //如果是Edit类型的那就监听返回事件
                if (viewLayout instanceof EditText) {
                    if (activity != null) {
                        Thread.runAndroid(activity, new Runnable() {
                            @Override
                            public void run() {
                                viewLayout.requestFocus();
                            }
                        });
                    }
                    return true;
                } else if (viewLayout instanceof ViewGroup) {
                    try {
                        viewGroup = (ViewGroup) viewLayout;
                    } catch (Exception e) {
                        return false;
                    }
                    int childCount = viewGroup.getChildCount();
                    if (childCount == 0) return false;
                    for (int i = 0; i < childCount; i++) {
                        View childAt = viewGroup.getChildAt(i);
                        if (setEditTextRequestFocus(childAt)) {
                            return true;
                        }
                    }
                }
                return false;
            }


            /**
             * 返回 true 则劫持返回事件
             *
             * @return
             */
            protected boolean onBackPressed() {
                return false;
            }

        }

        /**
         * 用于辅助 DialogFragment
         */
        public abstract static class AnnotationDialogFragment extends BaseDialogFragment {
            // 布局ID
            private int resLayout;

            public void setLayout(int resLayout) {
                this.resLayout = resLayout;
            }

            @Override
            public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                GT.build(this);// 注解赋值布局ID值
                View view = inflater.inflate(resLayout, container, false);
                createView(view);
                return view;
            }


            @Override
            protected void initView(View view, @Nullable Bundle savedInstanceState) {
                GT.build(this);
            }
        }

        /**
         * 封装第三代 DialogFragment
         * 封装特点：
         * 1.继承上代所有优点
         * 2.获取组件可以直接无需获取，直接使用。
         *
         * @param <T>
         */
        public abstract static class DataBindingDialogFragment extends AnnotationDialogFragment {

        }

    }

    /**
     * GT 适配器
     */
    public static class Adapters {

        /**
         * 用于旧版继承的 适配器基类
         *
         * @param <T>
         */
        public static abstract class BaseArrayAdapters<T> extends ArrayAdapter<T> implements BaseViewHolder<T> {

            private int position = 0;

            public int getPosition() {
                return position;
            }

            public BaseArrayAdapters(Context context, int resource, List<T> objects) {
                super(context, resource, objects);
            }

            /**
             * 加载布局
             *
             * @return
             */
            protected abstract int loadLayout();

            public View getView(int position, View convertView, ViewGroup parent) {
                this.position = position;
                View layout = LayoutInflater.from(getContext()).inflate(loadLayout(), parent, false);
                initView(layout, getItem(position));
                initView(layout, getItem(position), convertView, parent);
                return layout;
            }

            protected View initView(View view, T bean) {
                function(view, bean);
                return view;
            }

            protected View initView(View view, T bean, View convertView, ViewGroup parent) {
                function(view, bean);
                return view;
            }

        }

        private static abstract interface BaseViewHolder<T> {
            void function(final View view, final T bean);
        }

        /**
         * 用于继承新版的适配器
         */
        public abstract static class BaseAdapter<T, K extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<K> {

            protected Context context;
            private List<T> beanList;
            private LinearLayoutManager linearLayoutManager;
            private int layout = -1;//布局
            private View itemView;

            public BaseAdapter() {

            }

            public BaseAdapter(Context context) {
                this.context = context;
            }

            /**
             * @param context
             * @param beanList 展示内容
             */
            @SuppressLint("NotifyDataSetChanged")
            public BaseAdapter(Context context, List<T> beanList) {
                this.context = context;
                this.beanList = beanList;
                notifyDataSetChanged();
            }

            @SuppressLint("NotifyDataSetChanged")
            public BaseAdapter(Context context, RecyclerView rv, List<T> beanList, int layout_V_OR_H) {
                this.context = context;
                linearLayoutManager = new LinearLayoutManager(context, layout_V_OR_H, false);
                rv.setAdapter(this);
                rv.setLayoutManager(linearLayoutManager);
                this.beanList = beanList;
                notifyDataSetChanged();
            }

            /**
             * @param context
             * @param rv
             * @param beanList      展示内容
             * @param layout_V_OR_H LinearLayoutManager.VERTICAL OR LinearLayoutManager.HORIZONTAL OR 瀑布流
             * @param layout        加载的布局
             */
            public BaseAdapter(Context context, RecyclerView rv, List<T> beanList, int layout_V_OR_H, int layout) {
                this.context = context;
                if (layout != -1) {
                    this.layout = layout;
                }
                linearLayoutManager = new LinearLayoutManager(context, layout_V_OR_H, false);
                rv.setAdapter(this);
                rv.setLayoutManager(linearLayoutManager);
                this.beanList = beanList;
                notifyDataSetChanged();
            }

            public Context getContext() {
                return context;
            }

            public void setContext(Context context) {
                this.context = context;
            }

            public LinearLayoutManager getLinearLayoutManager() {
                return linearLayoutManager;
            }

            public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
                this.linearLayoutManager = linearLayoutManager;
            }

            public List<T> getBeanList() {
                return beanList;
            }

            /**
             * 用于一次性刷新加载数据
             *
             * @param beanList
             */
            public void setBeanList(List<T> beanList) {
                this.beanList = beanList;
                notifyDataSetChanged();
            }

            /**
             * 用于累计刷新加载数据 (常用)
             *
             * @param beanList
             */
            public void setBeanLists(List<T> beanList) {
                if (this.beanList == null) {
                    this.beanList = beanList;
                } else {
                    this.beanList.addAll(this.beanList.size(), beanList);
                }
                notifyDataSetChanged();
            }

            /**
             * @param rv
             * @return
             */
            public LinearLayoutManager setLinearLayoutManager_V(RecyclerView rv) {
                LinearLayoutManager llm = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                rv.setAdapter(this);
                rv.setLayoutManager(llm);
                return llm;
            }

            /**
             * @param rv
             * @return
             */
            public LinearLayoutManager setLinearLayoutManager_H(RecyclerView rv) {
                LinearLayoutManager llm = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                rv.setAdapter(this);
                rv.setLayoutManager(llm);
                return llm;
            }

            protected abstract K onCreateViewHolder(View itemView);

            @Override
            public K onCreateViewHolder(ViewGroup parent, int viewType) {
                if (layout == -1) {
                    layout = loadLayout();
                }
                return onCreateViewHolder(LayoutInflater.from(context).inflate(layout, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull K holder, int position) {
                T t = beanList.get(position);
                this.itemView = holder.itemView;
                initView(holder, holder.itemView, position, t);
                loadData(holder, holder.itemView, position, t);
            }


            protected int loadLayout() {
                return layout;
            }

            protected void loadData(K holder, View itemView, int position, T t) {
            }

            protected abstract void initView(K holder, View itemView, int position, T t);

            @Override
            public int getItemCount() {
                return beanList == null ? 0 : beanList.size();
            }

            public abstract static class BaseHolder extends RecyclerView.ViewHolder {
                protected View itemView;

                public BaseHolder(@NonNull View itemView) {
                    super(itemView);
                    this.itemView = itemView;
                    GT.build(this, itemView);
                }

                protected View findViewById(int id) {
                    if (itemView == null) return null;
                    return itemView.findViewById(id);
                }

            }

        }

        /**
         * 封装第二代 Adapter
         *
         * @param <T>
         */
        public static abstract class AnnotationAdapter<T, K extends RecyclerView.ViewHolder> extends BaseAdapter<T, K> {

            public AnnotationAdapter() {

            }

            public AnnotationAdapter(Context context) {
                super(context);
                GT.build(this);
            }

            public AnnotationAdapter(Context context, List<T> beanList) {
                super(context, beanList);
                GT.build(this);
            }

            public AnnotationAdapter(Context context, RecyclerView rv, List<T> beanList, int layout_V_OR_H) {
                super(context, rv, beanList, layout_V_OR_H);
                GT.build(this);
            }

            public AnnotationAdapter(Context context, RecyclerView rv, List<T> beanList, int layout_V_OR_H, int layout) {
                super(context, rv, beanList, layout_V_OR_H, layout);
                GT.build(this);
            }

            @Override
            protected void initView(K holder, View itemView, int position, T t) {

            }

            //布局ID
            private int resLayout = -1;

            public void setLayout(int resLayout) {
                this.resLayout = resLayout;
            }

            @Override
            protected int loadLayout() {
                return resLayout;
            }

        }

        /**
         * 封装第三代 Adapter
         * 封装特点：
         * 1.继承上代所有优点
         * 2.获取组件可以直接无需获取，直接使用。
         * <p>
         * 使用如下 ：注意，适配器暂不支持 ViewModel ，暂时感觉没必要支持
         *
         * @param <T>
         * @GT_DataBinding(setLayout = "item_title",setBindingType = GT_DataBinding.Adapter)
         * @GT.Annotations.GT_AnnotationAdapter(R.layout.item_title) public class DemoAdapter extends DemoAdapterBinding<DemoBean> {
         */
        public abstract static class DataBindingAdapter<T, K extends RecyclerView.ViewHolder> extends AnnotationAdapter<T, K> {

            public DataBindingAdapter() {
            }

            public DataBindingAdapter(Context context) {
                super(context);

            }

            public DataBindingAdapter(Context context, List<T> beanList) {
                super(context, beanList);
            }

            public DataBindingAdapter(Context context, RecyclerView rv, List<T> beanList, int layout_V_OR_H) {
                super(context, rv, beanList, layout_V_OR_H);
            }

            public DataBindingAdapter(Context context, RecyclerView rv, List<T> beanList, int layout_V_OR_H, int layout) {
                super(context, rv, beanList, layout_V_OR_H, layout);
            }

            @Override
            protected void initView(K holder, View itemView, int position, T t) {
                super.initView(holder, itemView, position, t);

            }
        }

    }

    /**
     * 封装了集合类
     */
    public static class GT_FloatingWindow implements SaveObject.SaveBean {

        /**
         * TODO 必看事项
         * 悬浮窗需要注意以下问题：
         * 1.申请悬浮窗权限：
         * <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
         *
         * 2.需要动态处理权限
         *  if (Build.VERSION.SDK_INT >= 23) {
         *                     if (!Settings.canDrawOverlays(activity)) {
         *                         Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
         *                         startActivityForResult(intent, 0);
         *                     } else {
         *                         startService(new Intent(activity, FloatingDemo.class));
         *                     }
         *                 } else {
         *                     startService(new Intent(activity, FloatingDemo.class));
         *                 }
         *
         * 3.静态注册 <service android:name=".service.test.FloatingDemo"  android:exported="true"/>
         *
         *
         */

        /**
         * 悬浮窗基类
         */
        public abstract static class BaseFloatingWindow extends Service {

            public Context context;                           //上下活动
            private WindowManager windowManager;
            private WindowManager.LayoutParams layoutParams;
            private int width = -1;                            //屏幕宽度
            private int height = -1;                           //屏幕高度
            private View view;                                 //当前加载的布局

            protected Timer timer;                                 //定时器来检测是否绑定APP
            private boolean isDrag = false;                      //是否可拖动
            private static boolean isShow = true;                //是否显示

            private static int type_screenType = -1079;          //创建屏幕的类型,默认是使用用户设置的Xml宽高
            private final static int type_self_adaption = -1;    //自适应
            private final static int type_portraitScreen = 0;    //竖屏
            private final static int type_landscape = 1;         //横屏
            private final static int type_screen = 2;            //自适应半屏
            private final static int type_fullScreen = 3;        //自适应全屏

            private static double screenSizeCoefficient = 2;     //屏幕大小系数,系数越大，创建屏幕越小 注意：该系数不能 <= 0

            public View findViewById(int id) {
                if (view == null) return null;
                return view.findViewById(id);
            }

            public WindowManager getWindowManager() {
                return windowManager;
            }

            public void setWindowManager(WindowManager windowManager) {
                this.windowManager = windowManager;
            }

            public WindowManager.LayoutParams getLayoutParams() {
                return layoutParams;
            }

            public void setLayoutParams(WindowManager.LayoutParams layoutParams) {
                this.layoutParams = layoutParams;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public View getView() {
                return view;
            }

            /**
             * 是否显示当前APP，需要与 setBindingApp 方法配合
             *
             * @return
             */
            public static boolean isIsShow() {
                return isShow;
            }

            /**
             * 设置是否显示,一般放在绑定的APP生命周期中
             * 注意：需要在绑定的悬浮窗中 setBindingApp 方法进行绑定才有效
             *
             * @param isShow
             */
            public static void setIsShow(boolean isShow) {
                BaseFloatingWindow.isShow = isShow;
            }

            public static double getScreenSizeCoefficient() {
                return screenSizeCoefficient;
            }

            /**
             * 屏幕大小系数,系数越大，创建屏幕越小 注意：该系数不能 <= 0
             *
             * @param screenSizeCoefficient
             */
            public static void setScreenSizeCoefficient(double screenSizeCoefficient) {
                BaseFloatingWindow.screenSizeCoefficient = screenSizeCoefficient;
            }

            public static int getType_screenType() {
                return type_screenType;
            }

            /**
             * 默认类型为用户设置的Xml布局：-1079
             * 自适应：-1
             * 竖屏：0
             * 横屏：1
             * 自适应半屏：2
             * 自适应全屏：3
             *
             * @param type_screenType 创建屏幕类型
             */
            public static void setType_screenType(int type_screenType) {
                BaseFloatingWindow.type_screenType = type_screenType;
            }

            public boolean isDrag() {
                return isDrag;
            }

            public void setView(View view) {
                this.view = view;
            }

            /**
             * 初始化 UI,可用于设置悬浮窗的 宽高显示的位置
             * 如：
             * layoutParams.width = width;
             * layoutParams.height = height / 3;
             * layoutParams.x = 300;
             * layoutParams.y = 300;
             */
            protected abstract void initView(View view);

            /**
             * 初始化 加载布局
             */
            protected int loadLayout() {
                return 0;
            }

            /**
             * 功能方法
             *
             * @param context
             * @param view    当前加载的View
             */
            protected void loadData(Context context, Intent intent, View view) {
            }


            /**
             * 是否获取焦点，获取焦点后才能进行弹出软键盘
             *
             * @param isGet
             */
            protected void setGetFocus(boolean isGet) {
                if (isGet) {
                    layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
                } else {
                    layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                }
                updateView();
            }

            /**
             * 更新View
             */
            protected void updateView() {
                windowManager.updateViewLayout(view, layoutParams);
            }

            /**
             * 更新宽高
             */
            protected void updateWidthHeight() {
                width = windowManager.getDefaultDisplay().getWidth();//获取当前屏幕宽度
                height = windowManager.getDefaultDisplay().getHeight();//获取当前屏幕高度
            }

            protected void show() {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }

            protected void hide() {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCreate() {
                super.onCreate();
                context = this;
                windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
                layoutParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                } else {
                    layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
                }
                layoutParams.format = PixelFormat.RGBA_8888;
                layoutParams.gravity = Gravity.TOP | Gravity.LEFT;
                layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

                //如果用户没有手动设置宽高，那就默认用系统宽高
                if (width == -1 && height == -1) {
                    updateWidthHeight();//更新当前屏幕宽高
                }

                //如果是继承自注解悬浮窗，那么View会不为null
                if (view == null) {
                    view = LayoutInflater.from(this).inflate(loadLayout(), null);
                }

                setScreenSize();//智能设置屏幕大小。

                //初始化View
                initView(view);
                windowManager.addView(view, layoutParams);

            }

            /**
             * TODO 注意：用户在xml布局中用“比重的方式”设置宽高可能会出现问题，
             * 如果使用悬浮窗自适应设置窗口大小的就不用担心bug问题
             * 推荐使用 以下方式来设置宽高
             * android:layout_width="300dp"
             * android:layout_height="500dp"
             * 或
             * layoutParams.width = 300;
             * layoutParams.height = 500;
             * 设置屏幕大小、方向
             */
            private void setScreenSize() {
                if (type_screenType != -1079) {
                    //自适应设置窗口大小，方向
                    switch (type_screenType) {
                        case type_portraitScreen://竖屏
                            if (width > height) {
                                layoutParams.width = (int) (width / (2 * screenSizeCoefficient));
                                layoutParams.height = (int) (height / screenSizeCoefficient);
                            } else {
                                layoutParams.width = (int) (width / screenSizeCoefficient);
                                layoutParams.height = (int) (height / screenSizeCoefficient);
                            }
                            break;

                        case type_landscape://横屏
                            if (width > height) {
                                layoutParams.width = (int) (width / screenSizeCoefficient);
                                layoutParams.height = (int) (height / screenSizeCoefficient);
                            } else {
                                layoutParams.width = (int) (width / screenSizeCoefficient);
                                layoutParams.height = (int) (height / (2 * screenSizeCoefficient));
                            }
                            break;

                        case type_screen://自适应半屏
                            if (width > height) {
                                layoutParams.width = (int) (width / screenSizeCoefficient);
                                layoutParams.height = height;
                            } else {
                                layoutParams.width = width;
                                layoutParams.height = (int) (height / screenSizeCoefficient);
                            }
                            break;

                        case type_fullScreen://自适应全屏
                            layoutParams.width = width;
                            layoutParams.height = height;
                            break;

                        case type_self_adaption://自适应
                            layoutParams.width = (int) (width / screenSizeCoefficient);
                            layoutParams.height = (int) (height / screenSizeCoefficient);
                            break;

                        default://自适应
                            layoutParams.width = (int) (width / screenSizeCoefficient);
                            layoutParams.height = (int) (height / screenSizeCoefficient);
                            break;
                    }
                } else {
                    //默认是占屏幕一半的大小
                    layoutParams.width = (int) (width / screenSizeCoefficient);
                    layoutParams.height = (int) (height / screenSizeCoefficient);
                    view.measure(0, 0);//设置标准宽高

                    int measuredWidth = view.getMeasuredWidth();
                    int measuredHeight = view.getMeasuredHeight();

                    int w = measuredWidth - (view.getPaddingLeft() + view.getPaddingRight());
                    int h = measuredHeight - (view.getPaddingTop() + view.getPaddingBottom());

                    //设置界面宽度
                    if (w == 121 || w == 122 || w == 155 || w == 154) {
                        layoutParams.width = width;
                    } else {
                        layoutParams.width = view.getMeasuredWidth();
                    }
                    //设置界面高度
                    if (h == 66 || h == 67 || h == 85 || h == 84) {
                        layoutParams.height = height;
                    } else {
                        layoutParams.height = view.getMeasuredHeight();
                    }


                }


            }

            @Override
            public IBinder onBind(Intent intent) {
                return null;
            }

            /**
             * 设置可拖动
             */
            protected void setDrag(boolean tf) {
                if (view != null) {
                    if (tf) {
                        isDrag = true;
                        view.setOnTouchListener(new FloatingOnTouchListener());
                    } else {
                        isDrag = false;
                        view.setOnTouchListener(null);
                    }

                }
            }

            /**
             * 注意:次方法不会销毁悬浮窗
             * 是否显示悬浮窗
             */
            protected void isShow(boolean tf) {
                Thread.runAndroid(new Runnable() {
                    @Override
                    public void run() {
                        if (tf) {
                            view.setVisibility(View.VISIBLE);
                        } else {
                            view.setVisibility(View.GONE);
                        }
                    }
                });
            }

            /**
             * 设置将绑定当前APP，如果APP不在前台那就隐藏悬浮窗
             * GT_FloatingService.isShow = true;
             *
             * @param monitoringTime 绑定检测间隔时间 毫秒
             */
            protected void setBindingApp(long monitoringTime) {
                if (timer == null) {
                    timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            if (isShow) {
                                isShow(true);
                            } else {
                                isShow(false);
                            }
                        }
                    }, 0, monitoringTime);
                }
            }

            @Override
            public int onStartCommand(Intent intent, int flags, int startId) {
                loadData(this, intent, view);
                return super.onStartCommand(intent, flags, startId);
            }

            /**
             * 启动悬浮窗
             *
             * @param context
             * @param toFragment
             */
            protected void startFloatingWindow(Context context, Class<?> toFragment) {
                startService(new Intent(context, toFragment));
            }

            /**
             * 启动悬浮窗
             *
             * @param toFragment
             */
            protected void startFloatingWindow(Class<?> toFragment) {
                startService(new Intent(this, toFragment));
            }


            /**
             * 退出当前 悬浮窗 并传递数据
             */
            public void finish() {
                if (windowManager != null) {
                    windowManager.removeView(view);
                    windowManager = null;
                }
                onDestroy();
                stopSelf();
            }

            /**
             * 退出当前 悬浮窗 并传递数据
             *
             * @param intent
             */
            protected void finish(Intent intent) {
                if (intent != null) {
                    onDestroy();
                }
            }

            protected boolean onBackPressed() {
                return false;
            }

            /**
             * 反馈数据
             *
             * @param requestCode
             * @param resultCode
             * @param data
             */
            protected void onResultData(int requestCode, int resultCode, @Nullable Intent data) {

            }

            @Override
            public void onDestroy() {
                super.onDestroy();
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
            }

            /**
             * 拖动事件
             */
            public class FloatingOnTouchListener implements View.OnTouchListener {
                private int x;
                private int y;

                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            x = (int) event.getRawX();
                            y = (int) event.getRawY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            int nowX = (int) event.getRawX();
                            int nowY = (int) event.getRawY();
                            int movedX = nowX - x;
                            int movedY = nowY - y;
                            x = nowX;
                            y = nowY;
                            layoutParams.x = layoutParams.x + movedX;
                            layoutParams.y = layoutParams.y + movedY;
                            windowManager.updateViewLayout(view, layoutParams);
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            }


        }

        /**
         * 悬浮窗注解类
         */
        public abstract static class AnnotationFloatingWindow extends BaseFloatingWindow {

            //布局ID
            protected int resLayout;

            public void setLayout(int resLayout) {
                this.resLayout = resLayout;
            }

            @Override
            public void onCreate() {
                GT.build(this);
                if (resLayout == -1) {
                    resLayout = loadLayout();
                }
                View view = LayoutInflater.from(this).inflate(resLayout, null);
                setView(view);
                super.onCreate();

            }

            @Override
            protected void initView(View view) {
                GT.build(this);
            }

        }

        /**
         * 封装第三代 FloatingWindow
         * 封装特点：
         * 1.继承上代所有优点
         * 2.获取组件可以直接无需获取，直接使用。
         *
         * @param <T>
         */
        public abstract static class DataBindingFloatingWindow extends AnnotationFloatingWindow {

        }

    }

    /**
     * 封装了 PopupWindow 集合类
     */
    public static class GT_PopupWindow implements SaveObject.SaveBean {

        //封装第一代 PopupWindow
        public static abstract class BasePopupWindow {

            private PopupWindow popWindow;
            private View view;
            protected int layout;
            public Context context;

            protected void buildData() {
            }

            ;

            protected View findViewById(int id) {
                if (view == null) return null;
                return view.findViewById(id);
            }

            public int loadLayout() {
                return layout;
            }

            public BasePopupWindow() {

            }

            public BasePopupWindow(Context context) {
                buildData();
                if (layout <= 0) {
                    layout = loadLayout();
                }
                this.context = context;
                view = LayoutInflater.from(context).inflate(layout, null, false);
                popWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                initView(view, popWindow);
                loadData(view, popWindow);
            }

            protected void initView(View view, PopupWindow popWindow) {
                //1.构造一个PopupWindow，参数依次是加载的View，宽高
//        popWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                popWindow.setOutsideTouchable(false);
                popWindow.setTouchable(true);
                popWindow.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return onBackPressed();//党纪外面会自动消失
                    }
                });

                popWindow.setFocusable(true);
                popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效
            }

            protected void loadData(View view, PopupWindow popWindow) {
            }

            //按下其他地方是否销毁
            public boolean onBackPressed() {
                return false;
            }

            /**
             * 显示在 参照View的下方
             *
             * @param v    参照View
             * @param xyga 最多四个参数: int xoff, int yoff, int gravity, int Animation
             */
            public void showBelow(View v, int... xyga) {

                if (popWindow != null && !popWindow.isShowing()) {
                    //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
                    switch (xyga.length) {
                        case 1:
                            popWindow.showAsDropDown(v, xyga[0], 0);//显示
                            break;
                        case 2:
                            popWindow.showAsDropDown(v, xyga[0], xyga[1]);//显示
                            break;
                        case 3:
                            popWindow.showAsDropDown(v, xyga[0], xyga[1], xyga[2]);//显示
                            break;
                        case 4:
                            popWindow.setAnimationStyle(xyga[3]);
                            popWindow.showAsDropDown(v, xyga[0], xyga[1], xyga[2]);//显示
                            break;
                        default:
                            popWindow.showAsDropDown(v);//显示
                            break;
                    }
                }
            }

            /**
             * 显示在 参照View的绝对位置
             *
             * @param v
             * @param xyga 最多四个参数: int xoff, int yoff, int gravity, int Animation
             */
            public void showAbsolute(View v, int... xyga) {
                if (popWindow != null && !popWindow.isShowing()) {
                    //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
                    switch (xyga.length) {
                        case 1:
                            popWindow.showAtLocation(v, xyga[0], 0, 0);
                            break;
                        case 2:
                            popWindow.showAtLocation(v, xyga[0], xyga[1], 0);//显示
                            break;
                        case 3:
                            popWindow.showAtLocation(v, xyga[0], xyga[1], xyga[2]);//显示
                            break;
                        case 4:
                            popWindow.setAnimationStyle(xyga[3]);
                            popWindow.showAtLocation(v, xyga[0], xyga[1], xyga[2]);//显示
                            break;
                        default:
                            popWindow.showAtLocation(v, 0, 0, 0);//显示
                            break;
                    }

                }
            }

            /**
             * 显示在 参照View的绝对位置
             *
             * @param v
             * @param xyga 最多四个参数: int xoff, int yoff, int gravity, int Animation
             */
            public void startPopupWindowBelow(View v, int... xyga) {
                showBelow(v, xyga);
            }

            /**
             * 显示在 参照View的绝对位置
             *
             * @param v
             * @param xyga 最多四个参数: int xoff, int yoff, int gravity, int Animation
             */
            public void startPopupWindowAbsolute(View v, int... xyga) {
                showAbsolute(v, xyga);
            }

            //销毁当前对象
            public void finish() {
                if (popWindow != null && popWindow.isShowing()) {
                    popWindow.dismiss();
                    popWindow = null;
                    view = null;
                }
            }

        }

        //封装第二代 PopupWindow
        public static abstract class AnnotationPopupWindow extends BasePopupWindow {

            @Override
            protected void buildData() {
                GT.build(this);
            }

            public AnnotationPopupWindow() {
            }

            public AnnotationPopupWindow(Context context) {
                super(context);
            }

            public void setLayout(int resLayout) {
                layout = resLayout;
            }

            @Override
            protected void initView(View view, PopupWindow popWindow) {
                super.initView(view, popWindow);
                GT.build(this, view);
            }

        }

        /**
         * 封装第三代 PopupWindow
         * 封装特点：
         * 1.继承上代所有优点
         * 2.获取组件可以直接无需获取，直接使用。
         *
         * @param <T>
         */
        public abstract static class DataBindingPopupWindow extends AnnotationPopupWindow {

            public DataBindingPopupWindow() {

            }

            public DataBindingPopupWindow(Context context) {
                super(context);
            }

        }

    }

    /**
     * 封装 GT_View
     */
    public static class GT_View implements SaveObject.SaveBean {

        /**
         * 封装第一代 View 实例View 有两种方法，2种构造方法
         */
        public static abstract class BaseView {

            private View view;
            protected int layout;
            public Context context;

            public View getView() {
                return view;
            }

            public void setView(View view) {
                this.view = view;
            }

            public void setLayout(int resLayout) {
                layout = resLayout;
            }

            protected int loadLayout() {
                return 0;
            }

            protected View findViewById(int id) {
                if (view == null) return null;
                return view.findViewById(id);
            }

            protected void bingData() {
            }

            public BaseView() {

            }

            public BaseView(Context context) {
                bingData();
                this.context = context;

                if (layout <= 0) {
                    layout = loadLayout();
                }
                view = LayoutInflater.from(context).inflate(layout, null, false);
                initView(view);
                loadData(view);
            }

            protected void initView(View view) {

            }

            protected void loadData(View view) {
            }

            //按下其他地方是否销毁
            public boolean onBackPressed() {
                return false;
            }

            //销毁当前对象
            public void finish() {
                try {
                    if (view != null) {
                        ((ViewGroup) view.getParent()).removeView(view);
                        view = null;
                    }
                } catch (Exception e) {

                }
            }

        }

        /**
         * 封装第二代 View 实现注解
         */
        public static abstract class AnnotationView extends BaseView {

            public AnnotationView() {
            }

            @Override
            protected void bingData() {
                super.bingData();
                GT.build(this);
            }

            /**
             * 构造方法
             *
             * @param context
             */
            public AnnotationView(Context context) {
                super(context);
            }

            @Override
            public void setLayout(int resLayout) {
                super.setLayout(resLayout);
            }

            @Override
            protected void initView(View view) {
                GT.build(this);
            }

        }

        /**
         * 封装第三代 View
         * 封装特点：
         * 1.继承上代所有优点
         * 2.获取组件可以直接无需获取，直接使用。
         *
         * @param <T>
         */
        public abstract static class DataBindingView extends AnnotationView {

            public DataBindingView() {
            }

            public DataBindingView(Context context) {
                super(context);
            }
        }

    }

    //DataBinding xml 布局解析并赋值
    public static class DataBindingUtil<T> {

        //DataBinding 实体类
        public static class ViewBean<T> {

            private Class<T> tClass;
            private String viewName;
            private int viewId;
            private View view;

            public ViewBean() {
                super();
            }

            public ViewBean(Class<T> tClass, String viewName, int viewId, View view) {
                this.tClass = tClass;
                this.viewName = viewName;
                this.viewId = viewId;
                this.view = view;
            }

            public Class<T> gettClass() {
                return tClass;
            }

            public void settClass(Class<T> tClass) {
                this.tClass = tClass;
            }

            public String getViewName() {
                return viewName;
            }

            public void setViewName(String viewName) {
                this.viewName = viewName;
            }

            public int getViewId() {
                return viewId;
            }

            public void setViewId(int viewId) {
                this.viewId = viewId;
            }

            public View getView() {
                return view;
            }

            public void setView(View view) {
                this.view = view;
            }

            @Override
            public String toString() {
                return "ViewBean{" +
                        "tClass=" + tClass +
                        ", viewName='" + viewName + '\'' +
                        ", viewId=" + viewId +
                        ", view=" + view +
                        '}';
            }
        }

        //解析 xml 布局后的信息
        private final static Map<String, ViewBean> viewBeanList = new HashMap<>();

        /**
         * 给 Activity 进行绑定的
         *
         * @param activity
         * @param <T>
         * @return
         */
        public static <T> T setContentView(Object bindingView) {
            if (bindingView == null) return null;
            //获取 Context 与 View
            Object[] objects = AnnotationAssist.returnActivityOrView(bindingView);
            Context context = (Context) objects[0];
            View view = (View) objects[1];
            if (context == null) context = getActivity();
            if (view == null) view = getActivityLayout((Activity) context);


            //清空 xml 布局信息
            viewBeanList.clear();
            //第一步：取布局中所有设置了ID的组件名与ID值
            analysisLayout(context, view);
            //第二部：获取代码自动生成的 DataBinding
            T bean = getDataBindingBean(bindingView.getClass().getName());//1
            //第三步：绑定类的数据
            bindClassData(bean, (Activity) context);
            return bean;
        }

        /**
         * 给 View 页面进行绑定的
         *
         * @param <T>
         * @param obj
         * @return
         */
        public static <T> T setContentView(Object bindingView, View view) {
            if (bindingView == null || view == null) return null;

            Context context = null;

            if (context == null) {

                context = (Context) AnnotationAssist.returnActivityOrView(bindingView)[0];

                if (context == null && bindingView instanceof Context) {
                    context = (Context) bindingView;
                }

                if (context == null) {
                    context = view.getContext();
                }

                if (context == null) {
                    context = GT.getActivity();
                }
            }

            //清空 xml 布局信息
            viewBeanList.clear();
            //第一步：取布局中所有设置了ID的组件名与ID值
            analysisLayout(context, view);
            //第二部：获取代码自动生成的 DataBinding
            T bean = getDataBindingBean(bindingView.getClass().getName());//2
            //第三步：绑定类的数据
            bindClassData(bean, (Activity) context);
            return bean;
        }

        /**
         * 设置 绑定 View
         *
         * @param bindingView
         * @param view
         * @param context
         * @param <T>
         * @return
         */
        public static <T> T setContentView(Object bindingView, View view, Context context) {
            if (bindingView == null || view == null) return null;

            if (context == null) {

                context = (Context) AnnotationAssist.returnActivityOrView(bindingView)[0];

                if (context == null && bindingView instanceof Context) {
                    context = (Context) bindingView;
                }

                if (context == null) {
                    context = view.getContext();
                }

                if (context == null) {
                    context = GT.getActivity();
                }
            }

            //清空 xml 布局信息
            viewBeanList.clear();
            //第一步：取布局中所有设置了ID的组件名与ID值
            analysisLayout(context, view);
            //第二部：获取代码自动生成的 DataBinding
            T bean = getDataBindingBean(bindingView.getClass().getName());//3
            //第三步：绑定类的数据
            bindClassData(bean, view);
            return bean;
        }

        //绑定 ViewModel
        public static <T> T dataBinding(Object thisObj) {
            if (thisObj == null) return null;
            T obj = null;
            try {
                //如果没有手动设置Model 那就从ViewModel类中智能取泛型model
                Type[] actualTypeArguments = AnnotationAssist.getClassGenericType(thisObj);
                if (actualTypeArguments == null || actualTypeArguments.length == 0) return null;
                Type actualTypeArgument = actualTypeArguments[actualTypeArguments.length - 1];

                String classStr = actualTypeArgument.toString();

                if (classStr.indexOf("<") != -1) {
                    classStr = classStr.substring(0, classStr.indexOf("<"));
                }

                //根据 class 反射出实体类
                obj = (T) new AnnotationAssist().classToObject(classStr);

                //将当前的 ViewModel 设置进入 Model中
                Object state = GT.AnnotationAssist.setReflectMethodValue(obj, "setBindingData", null, Object.class, thisObj);
                if ("false".equals(state)) {
                    GT.errs("View 与 ViewMode 自动绑定失败，请手动绑定");
                }
            } catch (Exception e) {
                GT.errs("View 与 ViewMode 自动绑定失败，请手动绑定:" + e);
            }
            return obj;
        }

        /**
         * 绑定类数据
         *
         * @param bean
         * @param activity
         * @param <T>
         * @return
         */
        private static <T> T bindClassData(T bean, Object mView1) {
            if (bean == null) return null;
            Field[] fields = bean.getClass().getDeclaredFields();
            for (Field field : fields) {
                View view = null;
                String name = field.getName();
                field.setAccessible(true);
                ViewBean viewBean = viewBeanList.get(name);
                if (viewBean != null) {

                    View mView2 = null;

                    if (mView1 instanceof Activity) {
                        view = ((Activity) mView1).findViewById(viewBean.getViewId());
                    }

                    if (mView1 instanceof View) {
                        view = ((View) mView1).findViewById(viewBean.getViewId());
                    }


                    try {
                        if (mView1 != null && view != null) field.set(bean, view);
                    } catch (Exception e) {
                        GT.log("e:" + e);
                    }
                }
            }
            return bean;
        }

        /**
         * 获取数据绑定的类
         *
         * @param activity
         * @param <T>
         * @return
         */
        private static <T> T getDataBindingBean(String className) {
            Class<T> cla = null;
            try {
                cla = (Class<T>) Class.forName(className + "Binding");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (cla == null) return null;
            //第二步:获取数据绑定的具体类
            T bean = null;//定义泛型
            try {
                bean = cla.newInstance();//实体化
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            return bean;
        }

        /**
         * 获取 Activity 布局
         *
         * @param activity
         * @return
         */
        private static View getActivityLayout(Activity activity) {
            View decorView = activity.getWindow().getDecorView();
            ViewGroup parent = decorView.findViewById(android.R.id.content);
            if (parent.getChildCount() > 0) {
                return parent.getChildAt(parent.getChildCount() - 1);
            }
            return null;
        }

        /**
         * 解析布局
         *
         * @param view
         */
        private static void analysisLayout(Context context, View view) {
            if (view == null) return;
            ViewBean viewBean = new ViewBean();
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                viewBean.setView(viewGroup);
                viewBean.settClass(viewGroup.getClass());
                if (viewGroup.getId() != -1) {
                    viewBean.setViewId(viewGroup.getId());
                    String idName = context.getResources().getResourceEntryName(viewGroup.getId());
                    viewBean.setViewName(idName);
                }
                viewBeanList.put(viewBean.getViewName(), viewBean);
                for (int index = 0; index < viewGroup.getChildCount(); index++) {
                    analysisLayout(context, viewGroup.getChildAt(index));//回调解析
                }
            } else {
                viewBean.setView(view);
                viewBean.settClass(view.getClass());
                if (view.getId() != -1) {
                    viewBean.setViewId(view.getId());
                    String idName = context.getResources().getResourceEntryName(view.getId());
                    viewBean.setViewName(idName);
                }
                viewBeanList.put(viewBean.getViewName(), viewBean);
            }
        }


    }

    //自定义组工具集合
    public static class ViewUtils {

        /**
         * 跑马灯
         */
        public static class MarqueeTextView extends TextView {

            public MarqueeTextView(Context context) {
                this(context, null);
            }

            public MarqueeTextView(Context context, AttributeSet attrs) {
                super(context, attrs);
                //设置单行
                setSingleLine();
                //设置Ellipsize
                setEllipsize(TextUtils.TruncateAt.MARQUEE);
                //获取焦点
                setFocusable(true);
                //走马灯的重复次数，-1代表无限重复
                setMarqueeRepeatLimit(-1);
                //强制获得焦点
                setFocusableInTouchMode(true);
            }

            /*
             *这个属性这个View得到焦点,在这里我们设置为true,这个View就永远是有焦点的
             */
            @Override
            public boolean isFocused() {
                return true;
            }

        }

        /**
         * 遥感控制
         * 使用教程请查看官网：https://github.com/1079374315/GT
         */
        public static class RockerView extends View {

            //返回字符串方向
            public static String getDirection(Direction direction) {
                String message = null;
                switch (direction) {
                    case DIRECTION_LEFT:
                        message = "左";
                        break;
                    case DIRECTION_RIGHT:
                        message = "右";
                        break;
                    case DIRECTION_UP:
                        message = "上";
                        break;
                    case DIRECTION_DOWN:
                        message = "下";
                        break;
                    case DIRECTION_UP_LEFT:
                        message = "左上";
                        break;
                    case DIRECTION_UP_RIGHT:
                        message = "右上";
                        break;
                    case DIRECTION_DOWN_LEFT:
                        message = "左下";
                        break;
                    case DIRECTION_DOWN_RIGHT:
                        message = "右下";
                        break;
                    default:
                        break;
                }
                return message;
            }

            private static final int DEFAULT_SIZE = 400;
            private static final int DEFAULT_ROCKER_RADIUS = 50;
            private Paint mAreaBackgroundPaint;
            private Paint mRockerPaint;
            private Point mRockerPosition;
            private Point mCenterPoint;
            private int mAreaRadius;
            private int mRockerRadius;
            private CallBackMode mCallBackMode;
            private OnAngleChangeListener mOnAngleChangeListener;
            private OnShakeListener mOnShakeListener;
            private DirectionMode mDirectionMode;
            private Direction tempDirection;
            private static final double ANGLE_0 = 0.0D;
            private static final double ANGLE_360 = 360.0D;
            private static final double ANGLE_HORIZONTAL_2D_OF_0P = 90.0D;
            private static final double ANGLE_HORIZONTAL_2D_OF_1P = 270.0D;
            private static final double ANGLE_VERTICAL_2D_OF_0P = 0.0D;
            private static final double ANGLE_VERTICAL_2D_OF_1P = 180.0D;
            private static final double ANGLE_4D_OF_0P = 0.0D;
            private static final double ANGLE_4D_OF_1P = 90.0D;
            private static final double ANGLE_4D_OF_2P = 180.0D;
            private static final double ANGLE_4D_OF_3P = 270.0D;
            private static final double ANGLE_ROTATE45_4D_OF_0P = 45.0D;
            private static final double ANGLE_ROTATE45_4D_OF_1P = 135.0D;
            private static final double ANGLE_ROTATE45_4D_OF_2P = 225.0D;
            private static final double ANGLE_ROTATE45_4D_OF_3P = 315.0D;
            private static final double ANGLE_8D_OF_0P = 22.5D;
            private static final double ANGLE_8D_OF_1P = 67.5D;
            private static final double ANGLE_8D_OF_2P = 112.5D;
            private static final double ANGLE_8D_OF_3P = 157.5D;
            private static final double ANGLE_8D_OF_4P = 202.5D;
            private static final double ANGLE_8D_OF_5P = 247.5D;
            private static final double ANGLE_8D_OF_6P = 292.5D;
            private static final double ANGLE_8D_OF_7P = 337.5D;
            private static final int AREA_BACKGROUND_MODE_PIC = 0;
            private static final int AREA_BACKGROUND_MODE_COLOR = 1;
            private static final int AREA_BACKGROUND_MODE_XML = 2;
            private static final int AREA_BACKGROUND_MODE_DEFAULT = 3;
            private int mAreaBackgroundMode;
            private Bitmap mAreaBitmap;
            private int mAreaColor;
            private static final int ROCKER_BACKGROUND_MODE_PIC = 4;
            private static final int ROCKER_BACKGROUND_MODE_COLOR = 5;
            private static final int ROCKER_BACKGROUND_MODE_XML = 6;
            private static final int ROCKER_BACKGROUND_MODE_DEFAULT = 7;
            private int mRockerBackgroundMode;
            private Bitmap mRockerBitmap;
            private int mRockerColor;

            public RockerView(Context context, AttributeSet attrs) {
                super(context, attrs);
                this.mCallBackMode = CallBackMode.CALL_BACK_MODE_MOVE;
                this.tempDirection = Direction.DIRECTION_CENTER;
                this.mAreaBackgroundMode = 3;
                this.mRockerBackgroundMode = 7;
                this.initAttribute(context, attrs);
                if (this.isInEditMode()) {
                    //            GT.log("RockerView", "RockerView: isInEditMode");
                }

                this.mAreaBackgroundPaint = new Paint();
                this.mAreaBackgroundPaint.setAntiAlias(true);
                this.mRockerPaint = new Paint();
                this.mRockerPaint.setAntiAlias(true);
                this.mCenterPoint = new Point();
                this.mRockerPosition = new Point();
            }

            private void initAttribute(Context context, AttributeSet attrs) {
                TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RockerView);
                Drawable areaBackground = typedArray.getDrawable(R.styleable.RockerView_areaBackground);
                if (null != areaBackground) {
                    if (areaBackground instanceof BitmapDrawable) {
                        this.mAreaBitmap = ((BitmapDrawable) areaBackground).getBitmap();
                        this.mAreaBackgroundMode = 0;
                    } else if (areaBackground instanceof GradientDrawable) {
                        this.mAreaBitmap = this.drawable2Bitmap(areaBackground);
                        this.mAreaBackgroundMode = 2;
                    } else if (areaBackground instanceof ColorDrawable) {
                        this.mAreaColor = ((ColorDrawable) areaBackground).getColor();
                        this.mAreaBackgroundMode = 1;
                    } else {
                        this.mAreaBackgroundMode = 3;
                    }
                } else {
                    this.mAreaBackgroundMode = 3;
                }

                Drawable rockerBackground = typedArray.getDrawable(R.styleable.RockerView_rockerBackground);
                if (null != rockerBackground) {
                    if (rockerBackground instanceof BitmapDrawable) {
                        this.mRockerBitmap = ((BitmapDrawable) rockerBackground).getBitmap();
                        this.mRockerBackgroundMode = 4;
                    } else if (rockerBackground instanceof GradientDrawable) {
                        this.mRockerBitmap = this.drawable2Bitmap(rockerBackground);
                        this.mRockerBackgroundMode = 6;
                    } else if (rockerBackground instanceof ColorDrawable) {
                        this.mRockerColor = ((ColorDrawable) rockerBackground).getColor();
                        this.mRockerBackgroundMode = 5;
                    } else {
                        this.mRockerBackgroundMode = 7;
                    }
                } else {
                    this.mRockerBackgroundMode = 7;
                }

                this.mRockerRadius = typedArray.getDimensionPixelOffset(R.styleable.RockerView_rockerRadius, 50);
                //        GT.log("RockerView", "initAttribute: mAreaBackground = " + areaBackground + "   mRockerBackground = " + rockerBackground + "  mRockerRadius = " + this.mRockerRadius);
                typedArray.recycle();
            }

            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int widthMode = MeasureSpec.getMode(widthMeasureSpec);
                int heightMode = MeasureSpec.getMode(heightMeasureSpec);
                int widthSize = MeasureSpec.getSize(widthMeasureSpec);
                int heightSize = MeasureSpec.getSize(heightMeasureSpec);
                int measureWidth;
                if (widthMode == 1073741824) {
                    measureWidth = widthSize;
                } else {
                    measureWidth = 400;
                }

                int measureHeight;
                if (heightMode == 1073741824) {
                    measureHeight = heightSize;
                } else {
                    measureHeight = 400;
                }

                this.setMeasuredDimension(measureWidth, measureHeight);
            }

            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                int measuredWidth = this.getMeasuredWidth();
                int measuredHeight = this.getMeasuredHeight();
                int cx = measuredWidth / 2;
                int cy = measuredHeight / 2;
                this.mCenterPoint.set(cx, cy);
                this.mAreaRadius = measuredWidth <= measuredHeight ? cx : cy;
                if (0 == this.mRockerPosition.x || 0 == this.mRockerPosition.y) {
                    this.mRockerPosition.set(this.mCenterPoint.x, this.mCenterPoint.y);
                }

                Rect src;
                Rect dst;
                if (0 != this.mAreaBackgroundMode && 2 != this.mAreaBackgroundMode) {
                    if (1 == this.mAreaBackgroundMode) {
                        this.mAreaBackgroundPaint.setColor(this.mAreaColor);
                        canvas.drawCircle((float) this.mCenterPoint.x, (float) this.mCenterPoint.y, (float) this.mAreaRadius, this.mAreaBackgroundPaint);
                    } else {
                        this.mAreaBackgroundPaint.setColor(-7829368);
                        canvas.drawCircle((float) this.mCenterPoint.x, (float) this.mCenterPoint.y, (float) this.mAreaRadius, this.mAreaBackgroundPaint);
                    }
                } else {
                    src = new Rect(0, 0, this.mAreaBitmap.getWidth(), this.mAreaBitmap.getHeight());
                    dst = new Rect(this.mCenterPoint.x - this.mAreaRadius, this.mCenterPoint.y - this.mAreaRadius, this.mCenterPoint.x + this.mAreaRadius, this.mCenterPoint.y + this.mAreaRadius);
                    canvas.drawBitmap(this.mAreaBitmap, src, dst, this.mAreaBackgroundPaint);
                }

                if (4 != this.mRockerBackgroundMode && 6 != this.mRockerBackgroundMode) {
                    if (5 == this.mRockerBackgroundMode) {
                        this.mRockerPaint.setColor(this.mRockerColor);
                        canvas.drawCircle((float) this.mRockerPosition.x, (float) this.mRockerPosition.y, (float) this.mRockerRadius, this.mRockerPaint);
                    } else {
                        this.mRockerPaint.setColor(-65536);
                        canvas.drawCircle((float) this.mRockerPosition.x, (float) this.mRockerPosition.y, (float) this.mRockerRadius, this.mRockerPaint);
                    }
                } else {
                    src = new Rect(0, 0, this.mRockerBitmap.getWidth(), this.mRockerBitmap.getHeight());
                    dst = new Rect(this.mRockerPosition.x - this.mRockerRadius, this.mRockerPosition.y - this.mRockerRadius, this.mRockerPosition.x + this.mRockerRadius, this.mRockerPosition.y + this.mRockerRadius);
                    canvas.drawBitmap(this.mRockerBitmap, src, dst, this.mRockerPaint);
                }

            }

            public boolean onTouchEvent(MotionEvent event) {
                switch (event.getAction()) {
                    case 0:
                        this.callBackStart();
                    case 2:
                        float moveX = event.getX();
                        float moveY = event.getY();
                        this.mRockerPosition = this.getRockerPositionPoint(this.mCenterPoint, new Point((int) moveX, (int) moveY), (float) this.mAreaRadius, (float) this.mRockerRadius);
                        this.moveRocker((float) this.mRockerPosition.x, (float) this.mRockerPosition.y);
                        break;
                    case 1:
                    case 3:
                        this.callBackFinish();
                        float upX = event.getX();
                        float upY = event.getY();
                        this.moveRocker((float) this.mCenterPoint.x, (float) this.mCenterPoint.y);
                        //                GT.log("RockerView", "onTouchEvent: 抬起位置 : x = " + upX + " y = " + upY);
                }

                return true;
            }

            private Point getRockerPositionPoint(Point centerPoint, Point touchPoint, float regionRadius, float rockerRadius) {
                float lenX = (float) (touchPoint.x - centerPoint.x);
                float lenY = (float) (touchPoint.y - centerPoint.y);
                float lenXY = (float) Math.sqrt((double) (lenX * lenX + lenY * lenY));
                double radian = Math.acos((double) (lenX / lenXY)) * (double) (touchPoint.y < centerPoint.y ? -1 : 1);
                double angle = this.radian2Angle(radian);
                this.callBack(angle);
                //        GT.log("RockerView", "getRockerPositionPoint: 角度 :" + angle);
                if (lenXY + rockerRadius <= regionRadius) {
                    return touchPoint;
                } else {
                    int showPointX = (int) ((double) centerPoint.x + (double) (regionRadius - rockerRadius) * Math.cos(radian));
                    int showPointY = (int) ((double) centerPoint.y + (double) (regionRadius - rockerRadius) * Math.sin(radian));
                    return new Point(showPointX, showPointY);
                }
            }

            private void moveRocker(float x, float y) {
                this.mRockerPosition.set((int) x, (int) y);
                //        GT.log("RockerView", "onTouchEvent: 移动位置 : x = " + this.mRockerPosition.x + " y = " + this.mRockerPosition.y);
                this.invalidate();
            }

            private double radian2Angle(double radian) {
                double tmp = (double) Math.round(radian / 3.141592653589793D * 180.0D);
                return tmp >= 0.0D ? tmp : 360.0D + tmp;
            }

            private Bitmap drawable2Bitmap(Drawable drawable) {
                int width = drawable.getIntrinsicWidth();
                int height = drawable.getIntrinsicHeight();
                @SuppressLint("WrongConstant") Bitmap.Config config = drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
                Bitmap bitmap = Bitmap.createBitmap(width, height, config);
                Canvas canvas = new Canvas(bitmap);
                drawable.setBounds(0, 0, width, height);
                drawable.draw(canvas);
                return bitmap;
            }

            private void callBackStart() {
                this.tempDirection = Direction.DIRECTION_CENTER;
                if (null != this.mOnAngleChangeListener) {
                    this.mOnAngleChangeListener.onStart();
                }

                if (null != this.mOnShakeListener) {
                    this.mOnShakeListener.onStart();
                }

            }

            private void callBack(double angle) {
                if (null != this.mOnAngleChangeListener) {
                    this.mOnAngleChangeListener.angle(angle);
                }

                if (null != this.mOnShakeListener) {
                    if (CallBackMode.CALL_BACK_MODE_MOVE == this.mCallBackMode) {
                        switch (this.mDirectionMode) {
                            case DIRECTION_2_HORIZONTAL:
                                if ((0.0D > angle || 90.0D <= angle) && (270.0D > angle || 360.0D <= angle)) {
                                    if (90.0D <= angle && 270.0D > angle) {
                                        this.mOnShakeListener.direction(Direction.DIRECTION_LEFT);
                                    }
                                } else {
                                    this.mOnShakeListener.direction(Direction.DIRECTION_RIGHT);
                                }
                                break;
                            case DIRECTION_2_VERTICAL:
                                if (0.0D <= angle && 180.0D > angle) {
                                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN);
                                } else if (180.0D <= angle && 360.0D > angle) {
                                    this.mOnShakeListener.direction(Direction.DIRECTION_UP);
                                }
                                break;
                            case DIRECTION_4_ROTATE_0:
                                if (0.0D <= angle && 90.0D > angle) {
                                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN_RIGHT);
                                } else if (90.0D <= angle && 180.0D > angle) {
                                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN_LEFT);
                                } else if (180.0D <= angle && 270.0D > angle) {
                                    this.mOnShakeListener.direction(Direction.DIRECTION_UP_LEFT);
                                } else if (270.0D <= angle && 360.0D > angle) {
                                    this.mOnShakeListener.direction(Direction.DIRECTION_UP_RIGHT);
                                }
                                break;
                            case DIRECTION_4_ROTATE_45:
                                if ((0.0D > angle || 45.0D <= angle) && (315.0D > angle || 360.0D <= angle)) {
                                    if (45.0D <= angle && 135.0D > angle) {
                                        this.mOnShakeListener.direction(Direction.DIRECTION_DOWN);
                                    } else if (135.0D <= angle && 225.0D > angle) {
                                        this.mOnShakeListener.direction(Direction.DIRECTION_LEFT);
                                    } else if (225.0D <= angle && 315.0D > angle) {
                                        this.mOnShakeListener.direction(Direction.DIRECTION_UP);
                                    }
                                } else {
                                    this.mOnShakeListener.direction(Direction.DIRECTION_RIGHT);
                                }
                                break;
                            case DIRECTION_8:
                                if ((0.0D > angle || 22.5D <= angle) && (337.5D > angle || 360.0D <= angle)) {
                                    if (22.5D <= angle && 67.5D > angle) {
                                        this.mOnShakeListener.direction(Direction.DIRECTION_DOWN_RIGHT);
                                    } else if (67.5D <= angle && 112.5D > angle) {
                                        this.mOnShakeListener.direction(Direction.DIRECTION_DOWN);
                                    } else if (112.5D <= angle && 157.5D > angle) {
                                        this.mOnShakeListener.direction(Direction.DIRECTION_DOWN_LEFT);
                                    } else if (157.5D <= angle && 202.5D > angle) {
                                        this.mOnShakeListener.direction(Direction.DIRECTION_LEFT);
                                    } else if (202.5D <= angle && 247.5D > angle) {
                                        this.mOnShakeListener.direction(Direction.DIRECTION_UP_LEFT);
                                    } else if (247.5D <= angle && 292.5D > angle) {
                                        this.mOnShakeListener.direction(Direction.DIRECTION_UP);
                                    } else if (292.5D <= angle && 337.5D > angle) {
                                        this.mOnShakeListener.direction(Direction.DIRECTION_UP_RIGHT);
                                    }
                                } else {
                                    this.mOnShakeListener.direction(Direction.DIRECTION_RIGHT);
                                }
                        }
                    } else if (CallBackMode.CALL_BACK_MODE_STATE_CHANGE == this.mCallBackMode) {
                        switch (this.mDirectionMode) {
                            case DIRECTION_2_HORIZONTAL:
                                if ((0.0D <= angle && 90.0D > angle || 270.0D <= angle && 360.0D > angle) && this.tempDirection != Direction.DIRECTION_RIGHT) {
                                    this.tempDirection = Direction.DIRECTION_RIGHT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_RIGHT);
                                } else if (90.0D <= angle && 270.0D > angle && this.tempDirection != Direction.DIRECTION_LEFT) {
                                    this.tempDirection = Direction.DIRECTION_LEFT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_LEFT);
                                }
                                break;
                            case DIRECTION_2_VERTICAL:
                                if (0.0D <= angle && 180.0D > angle && this.tempDirection != Direction.DIRECTION_DOWN) {
                                    this.tempDirection = Direction.DIRECTION_DOWN;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN);
                                } else if (180.0D <= angle && 360.0D > angle && this.tempDirection != Direction.DIRECTION_UP) {
                                    this.tempDirection = Direction.DIRECTION_UP;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_UP);
                                }
                                break;
                            case DIRECTION_4_ROTATE_0:
                                if (0.0D <= angle && 90.0D > angle && this.tempDirection != Direction.DIRECTION_DOWN_RIGHT) {
                                    this.tempDirection = Direction.DIRECTION_DOWN_RIGHT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN_RIGHT);
                                } else if (90.0D <= angle && 180.0D > angle && this.tempDirection != Direction.DIRECTION_DOWN_LEFT) {
                                    this.tempDirection = Direction.DIRECTION_DOWN_LEFT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN_LEFT);
                                } else if (180.0D <= angle && 270.0D > angle && this.tempDirection != Direction.DIRECTION_UP_LEFT) {
                                    this.tempDirection = Direction.DIRECTION_UP_LEFT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_UP_LEFT);
                                } else if (270.0D <= angle && 360.0D > angle && this.tempDirection != Direction.DIRECTION_UP_RIGHT) {
                                    this.tempDirection = Direction.DIRECTION_UP_RIGHT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_UP_RIGHT);
                                }
                                break;
                            case DIRECTION_4_ROTATE_45:
                                if ((0.0D <= angle && 45.0D > angle || 315.0D <= angle && 360.0D > angle) && this.tempDirection != Direction.DIRECTION_RIGHT) {
                                    this.tempDirection = Direction.DIRECTION_RIGHT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_RIGHT);
                                } else if (45.0D <= angle && 135.0D > angle && this.tempDirection != Direction.DIRECTION_DOWN) {
                                    this.tempDirection = Direction.DIRECTION_DOWN;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN);
                                } else if (135.0D <= angle && 225.0D > angle && this.tempDirection != Direction.DIRECTION_LEFT) {
                                    this.tempDirection = Direction.DIRECTION_LEFT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_LEFT);
                                } else if (225.0D <= angle && 315.0D > angle && this.tempDirection != Direction.DIRECTION_UP) {
                                    this.tempDirection = Direction.DIRECTION_UP;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_UP);
                                }
                                break;
                            case DIRECTION_8:
                                if ((0.0D <= angle && 22.5D > angle || 337.5D <= angle && 360.0D > angle) && this.tempDirection != Direction.DIRECTION_RIGHT) {
                                    this.tempDirection = Direction.DIRECTION_RIGHT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_RIGHT);
                                } else if (22.5D <= angle && 67.5D > angle && this.tempDirection != Direction.DIRECTION_DOWN_RIGHT) {
                                    this.tempDirection = Direction.DIRECTION_DOWN_RIGHT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN_RIGHT);
                                } else if (67.5D <= angle && 112.5D > angle && this.tempDirection != Direction.DIRECTION_DOWN) {
                                    this.tempDirection = Direction.DIRECTION_DOWN;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN);
                                } else if (112.5D <= angle && 157.5D > angle && this.tempDirection != Direction.DIRECTION_DOWN_LEFT) {
                                    this.tempDirection = Direction.DIRECTION_DOWN_LEFT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_DOWN_LEFT);
                                } else if (157.5D <= angle && 202.5D > angle && this.tempDirection != Direction.DIRECTION_LEFT) {
                                    this.tempDirection = Direction.DIRECTION_LEFT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_LEFT);
                                } else if (202.5D <= angle && 247.5D > angle && this.tempDirection != Direction.DIRECTION_UP_LEFT) {
                                    this.tempDirection = Direction.DIRECTION_UP_LEFT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_UP_LEFT);
                                } else if (247.5D <= angle && 292.5D > angle && this.tempDirection != Direction.DIRECTION_UP) {
                                    this.tempDirection = Direction.DIRECTION_UP;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_UP);
                                } else if (292.5D <= angle && 337.5D > angle && this.tempDirection != Direction.DIRECTION_UP_RIGHT) {
                                    this.tempDirection = Direction.DIRECTION_UP_RIGHT;
                                    this.mOnShakeListener.direction(Direction.DIRECTION_UP_RIGHT);
                                }
                        }
                    }
                }

            }

            private void callBackFinish() {
                this.tempDirection = Direction.DIRECTION_CENTER;
                if (null != this.mOnAngleChangeListener) {
                    this.mOnAngleChangeListener.onFinish();
                }

                if (null != this.mOnShakeListener) {
                    this.mOnShakeListener.onFinish();
                }

            }

            public void setCallBackMode(CallBackMode mode) {
                this.mCallBackMode = mode;
            }

            public void setOnAngleChangeListener(OnAngleChangeListener listener) {
                this.mOnAngleChangeListener = listener;
            }

            public void setOnShakeListener(DirectionMode directionMode, OnShakeListener listener) {
                this.mDirectionMode = directionMode;
                this.mOnShakeListener = listener;
            }

            public interface OnAngleChangeListener {
                void onStart();

                void angle(double var1);

                void onFinish();
            }

            public interface OnShakeListener {
                void onStart();

                void direction(Direction var1);

                void onFinish();
            }

            public static enum Direction {
                DIRECTION_LEFT,
                DIRECTION_RIGHT,
                DIRECTION_UP,
                DIRECTION_DOWN,
                DIRECTION_UP_LEFT,
                DIRECTION_UP_RIGHT,
                DIRECTION_DOWN_LEFT,
                DIRECTION_DOWN_RIGHT,
                DIRECTION_CENTER;

                private Direction() {
                }
            }

            public static enum DirectionMode {
                DIRECTION_2_HORIZONTAL,
                DIRECTION_2_VERTICAL,
                DIRECTION_4_ROTATE_0,
                DIRECTION_4_ROTATE_45,
                DIRECTION_8;

                private DirectionMode() {
                }
            }

            public static enum CallBackMode {
                CALL_BACK_MODE_MOVE,
                CALL_BACK_MODE_STATE_CHANGE;

                private CallBackMode() {
                }
            }
        }

        /**
         * 流动布局
         */
        public static class FlowLayout extends ViewGroup {


            /**
             * 当前行已用的宽度，由子View宽度加上横向间隔
             */
            private int mUsedWidth = 0;
            /**
             * 代表一行
             */
            private Line mLine = null;
            /**
             * 默认间隔
             */
            public static final int DEFAULT_SPACING = 20;
            /**
             * 横向间隔
             */
            private int mHorizontalSpacing = DEFAULT_SPACING;
            /**
             * 纵向间隔
             */
            private int mVerticalSpacing = DEFAULT_SPACING;
            /**
             * 代表行的集合
             */
            private final List<Line> mLines = new ArrayList<Line>();
            /**
             * 默认的最大的行数
             */
            private int mMaxLinesCount = Integer.MAX_VALUE;
            /**
             * 是否让子view充满该行
             */
            private boolean fillLine = false;

            public FlowLayout(Context context) {
                super(context);
            }

            public FlowLayout(Context context, AttributeSet attrs) {
                super(context, attrs);
            }

            /**
             * 是否填充满改行，如果是false，右边有空间
             */
            public void setFillLine(boolean fill) {
                fillLine = fill;
            }

            // 设置两个view水平间距
            public void setHorizontalSpacing(int spacing) {
                if (mHorizontalSpacing != spacing) {
                    mHorizontalSpacing = spacing;
                    requestLayout();
                }
            }

            /**
             * 设置纵向间距
             */
            public void setVerticalSpacing(int spacing) {
                if (mVerticalSpacing != spacing) {
                    mVerticalSpacing = spacing;
                    requestLayout();
                }
            }

            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                // 1获取with height 以及mode
                int widthMode = MeasureSpec.getMode(widthMeasureSpec);
                int heightMode = MeasureSpec.getMode(heightMeasureSpec);

                int heightSize = MeasureSpec.getSize(heightMeasureSpec)
                        - getPaddingBottom() - getPaddingTop();
                int widthSize = MeasureSpec.getSize(widthMeasureSpec)
                        - getPaddingLeft() - getPaddingRight();
                restoreLine();
                int count = getChildCount();
                // 2 测量子View
                for (int i = 0; i < count; i++) {
                    View child = getChildAt(i);

                    int widthSpec = MeasureSpec.makeMeasureSpec(widthSize,
                            widthMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST
                                    : widthMode);
                    int heightSpec = MeasureSpec.makeMeasureSpec(heightSize,
                            heightMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST
                                    : heightMode);

                    child.measure(widthSpec, heightSpec);
                    if (mLine == null) {
                        mLine = new Line();
                    }
                    // 将childview 添加到每一行中
                    int childWidth = child.getMeasuredWidth();
                    // 当前行已经占用的宽度
                    mUsedWidth += childWidth;
                    if (mUsedWidth < widthSize) {
                        // 当前行还没有达到上限，那么该child就添加进这一行
                        mLine.addView(child);
                        mUsedWidth += mHorizontalSpacing; // 添加上两个子View之间水平方向的间隔
                    } else {
                        // 说明长度超出了当前的最大宽度
                        if (mLine.getViewCount() == 0) {
                            // 表示当前行中还没有元素，添加的第一个元素 长度就超过了最大宽度，那么也要把该child 添加进去保证有数据
                            mLine.addView(child);
                            // 同时换行
                        } else {
                            // 表示当前行中已经有元素，那么换一行，添加进去
                            newLine();
                            mLine.addView(child);
                            // 改变已使用的宽度
                            mUsedWidth += mHorizontalSpacing + childWidth;
                        }

                    }
                }

                // 前面只有换行的时候才将Line 添加到lines 集合中，这里要判断一下最后一行，将最后一行也添加进去
                if (mLine != null && mLine.getViewCount() > 0
                        && !mLines.contains(mLine)) {
                    // 表示有数据
                    mLines.add(mLine);
                }

                // 设置测量的宽高setMeasuredDimension
                int totoalHeight = 0;
                for (int i = 0; i < mLines.size(); i++) {
                    totoalHeight += mLines.get(i).mHeight;// N行的高度
                }
                // 加上 行间距
                totoalHeight += (mLines.size() - 1) * mVerticalSpacing;
                // 加上padding
                totoalHeight += getPaddingBottom() + getPaddingTop();
                // 设置FlowLayout的宽度值 高度值 宽度就是默认的宽度，高度是总的高度
                int measuredHeight = resolveSize(totoalHeight, heightMeasureSpec);
                setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
                        measuredHeight);
            }

            @Override
            protected void onLayout(boolean changed, int l, int t, int r, int b) {
                // 主要是调用child.layout
                int count = mLines.size();
                int left = getPaddingLeft();
                int top = getPaddingTop();

                for (int i = 0; i < count; i++) {
                    Line line = mLines.get(i);
                    line.layout(left, top);
                    top += mVerticalSpacing + line.mHeight;
                }
            }


            /**
             * 还原所有数据
             */
            private void restoreLine() {
                mLines.clear();
                mLine = new Line();
                mUsedWidth = 0;
            }

            /**
             * 新增加一行
             */
            private boolean newLine() {
                mLines.add(mLine);
                if (mLines.size() < mMaxLinesCount) {
                    mLine = new Line();
                    mUsedWidth = 0;
                    return true;
                }
                return false;
            }

            /***
             * 代表着一行，封装了一行所占高度，该行子View的集合，以及所有View的宽度总和
             *
             * @author Administrator
             *
             */
            public class Line {

                int mWidth = 0;// 该行中所有的子View累加的宽度
                int mHeight = 0;// 该行中所有的子View中高度最高的那个子View的高度

                List<View> views = new ArrayList<View>();// 存放一行中的View

                public void addView(View child) {
                    views.add(child);
                    mWidth += child.getMeasuredWidth();
                    int childHeight = child.getMeasuredHeight();
                    mHeight = mHeight < childHeight ? childHeight : mHeight;// 高度等于一行中最高的View
                }

                /***
                 * layout 子view
                 * @param l
                 * @param t
                 */
                public void layout(int l, int t) {
                    int left = l;
                    int top = t;
                    // 父布局的宽度
                    int totoalWidth = getMeasuredWidth() - getPaddingLeft()
                            - getPaddingRight();
                    // 当前line 中view的个数
                    int count = getViewCount();
                    // 剩余空间平分给每个View
                    int spaceLast = totoalWidth - mWidth - (count - 1)
                            * mHorizontalSpacing;
                    int averageWidth = spaceLast / count;
                    // 平分的宽度
                    // int splitSpacing = (int) (spaceLast / count + 0.5);
                    for (int i = 0; i < count; i++) {
                        View child = views.get(i);
                        int childHeight = child.getMeasuredHeight();
                        int childWidth = child.getMeasuredWidth();

                        if (fillLine) {// 要充满该行
                            childWidth += averageWidth;
                            child.getLayoutParams().width = childWidth;
                            // 改变了原来的宽高，重新测量一次
                            int widthMeasureSpec = MeasureSpec.makeMeasureSpec(
                                    childWidth, MeasureSpec.EXACTLY);
                            int heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                                    childHeight, MeasureSpec.EXACTLY);
                            // 再次测量
                            child.measure(widthMeasureSpec, heightMeasureSpec);

                        }
                        // 布局View
                        child.layout(left, top, left + childWidth, top + childHeight);
                        left += childWidth + mHorizontalSpacing; // 为下一个View的left赋值
                    }
                }

                /**
                 * 该行中view的个数
                 *
                 * @return
                 */
                public int getViewCount() {
                    return views.size();
                }

            }
        }

        /**
         * 拖动View
         */
        public static class TouchView {

            /**
             * 设置View可拖动
             *
             * @param view
             * @param isDrag 是否可拖动
             */
            public static void setDrag(View view, boolean isDrag) {
                if (!isDrag) {
                    view.setOnTouchListener(null);
                    return;
                }

                final double[] lastX = new double[1];
                final double[] lastY = new double[1];
                view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        //此方法获取的是手指相对于view的左上角的x,y值
                        int x = (int) event.getX();
                        int y = (int) event.getY();
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                lastX[0] = x;
                                lastY[0] = y;
                                break;
                            case MotionEvent.ACTION_MOVE:
                                int offsetX = (int) (x - lastX[0]);
                                int offsetY = (int) (y - lastY[0]);
                                view.layout(view.getLeft() + offsetX, view.getTop() + offsetY, view.getRight() + offsetX, view.getBottom() + offsetY);
                                break;
                        }
                        return false;
                    }
                });

            }

        }

        /**
         * 设置触摸放大
         */
        public static class ViewTouchMagnify {
            private static int viewWidth;        //保存按钮宽度
            private static int viewHeight;       //保存按钮高度

            /**
             * 为按钮设置触摸事件
             *
             * @param view
             */
            public static void touchZoomInView(final View view) {
                //设置按钮触摸事件
                view.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View arg0, MotionEvent arg1) {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        if (arg1.getAction() == MotionEvent.ACTION_DOWN) {//如果用户手指触摸屏幕
                            viewWidth = view.getWidth();        //保存按钮的宽度
                            viewHeight = view.getHeight();        //保存按钮的高度
                            //                        view.setTextSize(18);								//设置按钮放大时字体大小
                            layoutParams.width = viewWidth + 20;                //设置按钮放大时的宽度
                            layoutParams.height = viewHeight + 10;            //设置按钮放大时的高度
                        } else if (arg1.getAction() == MotionEvent.ACTION_UP) {//如果用户手指离开屏幕
                            //                        button.setTextSize(15);							//设置按钮为原来字体大小
                            layoutParams.width = viewWidth;                //设置按钮为原来的宽度
                            layoutParams.height = viewHeight;                //设置按钮为原来的高度
                        }
                        view.setLayoutParams(layoutParams);    //提交事务
                        return false; //设置为未完成消耗掉的时间   如果将此返回为     true  那么按钮的  单击事件将会被屏蔽掉
                    }
                });

            }

        }

        /**
         * 嵌套滑动
         */
        public class NestScrollView extends ScrollView {
            public NestScrollView(Context context) {
                this(context, null);
            }

            public NestScrollView(Context context, AttributeSet attrs) {
                this(context, attrs, 0);
            }

            public NestScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
            }

            @Override
            public boolean onInterceptTouchEvent(MotionEvent ev) {
                //关键点在这
                getParent().requestDisallowInterceptTouchEvent(true);//不要拦截父事件
                return super.onInterceptTouchEvent(ev);
            }

        }

        /**
         * 延迟触发(如：EditText输入完后再触发)
         */
        public static class DelayTrigger {

            private OnTriggerValue onTriggerValue;
            private EditText et;

            public void EditText(EditText et, int sleep, OnTriggerValue onTriggerValue) {
                this.et = et;
                this.onTriggerValue = onTriggerValue;
                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        //在文本之前改变
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        //正在改变中
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        //文本之后改变了
                        if (delayRun != null) {
                            //每次editText有变化的时候，则移除上次发出的延迟线程
                            handler.removeCallbacks(delayRun);
                        }
                        //延迟800ms，如果不再输入字符，则执行该线程的run方法
                        handler.postDelayed(delayRun, sleep);
                    }
                });

            }

            private static Handler handler = new Handler();
            /**
             * 延迟线程，看是否还有下一个字符输入
             */
            private Runnable delayRun = new Runnable() {
                @Override
                public void run() {
                    //在这里调用服务器的接口，获取数据
                    if (onTriggerValue != null && et != null)
                        onTriggerValue.onTriggerValue(et.getText().toString());
                }
            };


            public interface OnTriggerValue {
                void onTriggerValue(String str);
            }
        }

        /**
         * 用于获取输入法高度
         */
        public static class KeyboardLayout extends FrameLayout {

            private KeyboardLayoutListener mListener;
            private int delayTime;
            private boolean mIsKeyboardActive = false; //　输入法是否激活
            private int mKeyboardHeight = 0; // 输入法高度
            private Context context;
            private boolean isActive;
            private int keyboardHeight;

            public KeyboardLayout(Context context) {
                this(context, null, 0);
            }

            public KeyboardLayout(Context context, AttributeSet attrs) {
                this(context, attrs, 0);
            }

            public KeyboardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);// 监听布局变化
                getViewTreeObserver().addOnGlobalLayoutListener(new KeyboardOnGlobalChangeListener());
            }

            private class KeyboardOnGlobalChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {
                private int mScreenHeight = 0;

                @Override
                public void onGlobalLayout() {
                    keyboardHeight = 0;
                    final Rect rect = new Rect();
                    // 获取当前页面窗口的显示范围
                    ((Activity) getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                    int screenHeight = getScreenHeight();
                    keyboardHeight = screenHeight - rect.bottom; // 输入法的高度
                    isActive = false;
                    if (Math.abs(keyboardHeight) > screenHeight / 5) {
                        isActive = true; // 超过屏幕五分之一则表示弹出了输入法
                        mKeyboardHeight = keyboardHeight;
                    }
                    mIsKeyboardActive = isActive;
                    if (mListener != null) {
                        if (delayRun != null) {
                            //每次editText有变化的时候，则移除上次发出的延迟线程
                            handler.removeCallbacks(delayRun);
                        }
                        //延迟触发，如果不再有反馈则触发，则执行该线程的run方法
                        handler.postDelayed(delayRun, delayTime);
                    }
                }

                private int getScreenHeight() {
                    if (mScreenHeight > 0) {
                        return mScreenHeight;
                    }
                    mScreenHeight = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE))
                            .getDefaultDisplay().getHeight();
                    return mScreenHeight;
                }
            }


            private Handler handler = new Handler();
            /**
             * 延迟线程，看是否还有下一个字符输入
             */
            private Runnable delayRun = new Runnable() {
                @Override
                public void run() {
                    //在这里调用服务器的接口，获取数据
                    mListener.onKeyboardStateChanged(isActive, keyboardHeight);
                }
            };

            /**
             * @param listener
             * @param delayTime 灵敏度，灵敏度越小，检测反馈时间越短，但有可能会出现反馈多次的情况,推荐值 100毫秒
             */
            public void setKeyboardListener(KeyboardLayoutListener listener, int delayTime) {
                mListener = listener;
                if (delayTime < 0) {
                    delayTime = 0;
                }
                this.delayTime = delayTime;
            }

            public KeyboardLayoutListener getKeyboardListener() {
                return mListener;
            }

            public boolean isKeyboardActive() {
                return mIsKeyboardActive;
            }

            /**
             * 获取输入法高度
             *
             * @return
             */
            public int getKeyboardHeight() {
                return mKeyboardHeight;
            }

            public interface KeyboardLayoutListener {
                /**
                 * @param isActive       输入法是否激活
                 * @param keyboardHeight 输入法面板高度
                 */
                void onKeyboardStateChanged(boolean isActive, int keyboardHeight);
            }


        }


    }

    //============================================= 编程思想辅助 ======================================

    //MVC MVP MVVM 框架类
    public static class Frame {

        /**
         * 作者推荐：根据项目模块的不同，选择相适应的框架进行实现
         */

        /**
         * MVC模式
         * Model:继承自 MVC_Model (模型/数据处理):是应用程序中用于处理应用程序数据逻辑的部分。通常模型对象负责在数据库中存取数据
         * View: xml布局文件 (视图/UI描述):是应用程序中处理数据显示的部分。通常视图是依据模型数据创建的
         * Controller: Activity/Fragment (控制器/用户交互):是应用程序中处理用户交互的部分。通常控制器负责从视图读取数据，控制用户输入，并向模型发送数据
         * 关系图：View -> Controller -> Model -> View
         *
         * @param <T>
         */
        public abstract static class MVC_Model<T> {
            protected T bindingView;//持有的 Controller/View 对象

            public MVC_Model() {

            }

            public void setBindingView(T bindingView) {
                this.bindingView = bindingView;
            }

            public MVC_Model(T t) {
                bindingView = t;
            }
        }

        /**
         * MVP模式
         * Model:继承自 MVP_Model (模型/数据处理):主要提供数据存取功能
         * View: Activity/Fragment (视图/UI事件):初始化View,处理用户事件和视图
         * Presenter:继承自 MVP_Presenter (展示层/衔接):负责通过Model存取数据，连接View和Model，从Model中取出数据交给View
         * 关系图：View ←→ Presenter , Presenter ←→ Model (View与Model完全隔离)
         *
         * @param <T>
         */
        public abstract static class MVP_Presenter<T> extends MVC_Model<T> {
            private Activity activity;
            private View view;

            public MVP_Presenter() {

            }

            public MVP_Presenter(T t) {
                super(t);
                Object[] objects = AnnotationAssist.returnActivityOrView(t);
                activity = (Activity) objects[0];
                view = (View) objects[1];
                initView();
            }


            //初始化View
            protected void initView() {
            }

            //加载数据
            protected void loadData() {
            }

            protected <K extends View> K findViewById(int id) {
                try {
                    if (activity != null) {
                        return activity.findViewById(id);
                    } else if (view != null) {
                        return view.findViewById(id);
                    } else {
                        errt("activity add view null");
                        return null;
                    }
                } catch (Exception e) {
                    return null;
                }
            }


        }

        public abstract static class MVP_Model<T> extends MVC_Model<T> {
            public MVP_Model(T t) {
                super(t);
            }

            public MVP_Model() {
            }
        }

        /**
         * MVVM模式
         * Model:继承自 MVVM_Model (模型/数据处理):主要提供数据存取功能
         * View: Activity/Fragment (视图/UI事件):初始化View,处理用户事件和视图
         * ViewModel:继承自 MVVM_ViewModel (展示层/衔接):负责通过Model存取数据，连接View和Model，从Model中取出数据交给View
         * 关系图：View ←→ ViewModel , ViewModel ←→ Model (View与Model完全隔离)
         *
         * @param <T>
         */
        public abstract static class MVVM_ViewModel<T> extends MVP_Presenter<T> {

            public MVVM_ViewModel() {
            }

            public MVVM_ViewModel(T bindingView) {
                super(bindingView);
            }

            @Override
            public void setBindingView(T bindingView) {
                super.setBindingView(bindingView);
            }
        }

        public abstract static class MVVM_Model<T> extends MVP_Model<T> {
            public MVVM_Model(T t) {
                super(t);
            }

            public MVVM_Model() {
            }
        }

        /**
         * GT模式
         * Model:继承自 GT_Model (模型/数据处理):主要提供数据存取功能
         * View: Activity/Fragment (视图/UI事件):初始化View,处理用户事件和视图
         * ViewModel:继承自 GT_ViewModel (展示层/衔接):负责通过Model存取数据，连接View和Model，从Model中取出数据交给View
         * 关系图：View ←→ GT_ViewModel , GT_ViewModel ←→ GT_Model (View与Model完全隔离)
         *
         * @param <T>
         */
        public abstract static class GT_ViewModel<V, VM, M> extends MVVM_ViewModel<VM> {

            protected V bindingData;
            protected M bindingModel;

            public void setBindingData(V bindingData) {
                this.bindingData = bindingData;
                setModel(bindingData);
                initView();
                loadData();
            }

            public GT_ViewModel() {
            }


            /**
             * 特别重要的回调
             * 设置 Model、View、ViewModel的值
             *
             * @param bindingData
             */
            public GT_ViewModel(V bindingData) {
                //如果是接受过 GT绑定 的就不进行再次绑定了
                super((
                        bindingData.getClass().getSuperclass().getSuperclass().toString().contains("class com.gsls.gt.GT$") &&
                                bindingData.getClass().getSuperclass().getSuperclass().toString().contains("$DataBinding") ?
                                (VM) bindingData : DataBindingUtil.setContentView(bindingData)
                ));
                bindingModel(bindingData);
            }

            protected void setModel(V bindingData) {
                bindingView = bindingData.getClass().getSuperclass().getSuperclass().toString().contains("class com.gsls.gt.GT$") &&
                        bindingData.getClass().getSuperclass().getSuperclass().toString().contains("$DataBinding") ?
                        (VM) bindingData : DataBindingUtil.setContentView(bindingData);
                bindingModel(bindingData);
            }

            private void bindingModel(V bindingData) {
                this.bindingData = bindingData;
                bindingModel = setBindingModel();//获取手动设置的Model
                if (bindingModel != null) return;//如果没有手动设置Model那就智能生成Model
                try {
                    //如果没有手动设置Model 那就从ViewModel类中智能取泛型model
                    Type[] actualTypeArguments = AnnotationAssist.getClassGenericType(this);
                    Type actualTypeArgument = actualTypeArguments[actualTypeArguments.length - 1];

                    String classStr = "";

                    //在 jdk 1.7 的情况，编译会报错 不支持 getTypeName 方法
                    /*try{
                        classStr = actualTypeArgument.getTypeName();
                    }catch (Exception e){
                        classStr = actualTypeArgument.toString();
                    }*/

                    classStr = actualTypeArgument.toString();

                    if (classStr.indexOf("<") != -1) {
                        classStr = classStr.substring(0, classStr.indexOf("<"));
                    }

                    //根据 class 反射出实体类
                    M obj = (M) new AnnotationAssist().classToObject(classStr);

                    //将当前的 ViewModel 设置进入 Model中
                    Object state = AnnotationAssist.setReflectMethodValue(obj, "setBindingView", null, Object.class, this);
                    if ("false".equals(state)) {
                        GT.err("ViewMode 与 Model 自动绑定失败，请手动绑定");
                    }
                    bindingModel = obj;

                } catch (Exception e) {
                    GT.err("ViewMode 与 Model 自动绑定失败，请手动绑定:" + e);
                }
            }

            protected M setBindingModel() {
                return bindingModel;
            }

        }

        public abstract static class GT_Model<T> extends MVVM_Model<T> {

            public GT_Model() {
            }

            public GT_Model(T t) {
                super(t);
            }


        }

        /**
         * GT_Binding模式
         * Model:继承自 GT_BindingModel (模型/数据处理):主要提供数据存取功能
         * View: Activity/Fragment (视图/UI事件):初始化View,处理用户事件和视图
         * ViewModel:继承自 GT_BindingViewModel (展示层/衔接):负责通过Model存取数据，连接View和Model，从Model中取出数据交给View
         * 关系图：View ←→ GT_BindingViewModel , GT_BindingViewModel ←→ GT_BindingModel (View与Model完全隔离)
         * <p>
         * //TODO 有两种方式 初始化 ViewModel:
         * 1.通过泛类进行 映射实例化 实例化的ViewModel 需要有无参构造方法 或 有参与无参构造方法可同在
         * 如：public class MainActivity extends MainActivityBinding<MainViewModel> {
         * <p>
         * 2.通过 new 来实例化，如： new MainViewModel(this);
         * 如果需要绑定 ViewModel 可使用 bindingViewModel(new MainViewModel(this));
         * 这样就可以在 View 中使用 viewModel 变量来进行操作
         *
         * @param <T>
         */
        public abstract static class GT_BindingViewModel<V, M> extends GT_ViewModel<V, V, M> {

            // 使用 ViewModel 泛类型绑定
            public GT_BindingViewModel() {
            }

            /**
             * 使用 new 实例化 时使用 设置 Model、View、ViewModel的值
             *
             * @param bindingData
             */
            public GT_BindingViewModel(V bindingData) {
                super(bindingData);
                bindingView = bindingData;//将普通页面类与对应的Binding类相对等
                loadData();
            }

        }


    }

//============================================= 设备监听类 ======================================

    public static class DeviceListening {

        //AudioManager 安卓手机音量类
        public static class GT_AudioManager {
            /**
             * 提示：再设置音量大小时，请先搞清楚，该音量的最大值
             */
            private AudioManager mAudioManager;//定义 AudioManager
            private Activity activity;//定义 Activity
            private int max;//最大值
            private int current;//当前值

            /**
             * 初始化
             *
             * @param activity
             */
            public GT_AudioManager(Activity activity) {
                this.activity = activity;
                mAudioManager = (AudioManager) activity.getSystemService(activity.AUDIO_SERVICE);
            }//初始化

            /**
             * 获取 通话声音 最大值 与 当前通过的声音值
             *
             * @return
             */
            public int getVoiceCall() {
                current = mAudioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
                return current;
            }

            /**
             * 获取 音量操作类对象
             *
             * @return
             */
            public int getVoiceCallMax() {
                max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
                return max;
            }

            /**
             * 设置音量大小
             *
             * @param value
             */
            public void setVoiceCallValue(int value) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, value, AudioManager.FLAG_PLAY_SOUND);//设置 通话声音 音量大小为 0 静音
            }//设置 通话声音 的音量

            /**
             * 获取当前手机的声音值
             *
             * @return
             */
            public int getVoiceSystem() {
                max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
                current = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
                return current;
            }

            /**
             * 获取 系统音量 最大值
             *
             * @return
             */
            public int getVoiceSystemMax() {
                max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
                return max;
            }

            /**
             * 设置系统音量值
             *
             * @param value
             */
            public void setVoiceSystemValue(int value) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, value, AudioManager.FLAG_PLAY_SOUND);//设置 通话声音 音量大小为 0 静音
            }//设置 系统音量 的音量

            /**
             * 获取 当前通过的声音值
             *
             * @return
             */
            public int getVoiceRing() {
                current = mAudioManager.getStreamVolume(AudioManager.STREAM_RING);
                return current;
            }

            /**
             * 铃声音量 最大值
             *
             * @return
             */
            public int getVoiceRingMax() {
                max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
                return max;
            }

            /**
             * 设置 铃声音量 的音量
             *
             * @param value
             */
            public void setVoiceRingValue(int value) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_RING, value, AudioManager.FLAG_PLAY_SOUND);//设置 铃声音量 音量大小为 0 静音
            }//设置 铃声音量 的音量

            /**
             * 获取 当前通过的声音值
             *
             * @return
             */
            public int getVoiceMusic() {
                current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                return current;
            }

            /**
             * 获取 音乐音量(多媒体) 最大值
             *
             * @return
             */
            public int getVoiceMusicMax() {
                max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                return max;
            }

            /**
             * 设置 多媒体 的音量
             *
             * @param value
             */
            public void setMusicValue(int value) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, value, AudioManager.FLAG_PLAY_SOUND);//设置多媒体音量大小为 0 静音
            }//设置 多媒体 的音量

            /**
             * 获取  当前通过的声音值
             *
             * @return
             */
            public int getVoiceAlarm() {
                current = mAudioManager.getStreamVolume(AudioManager.STREAM_ALARM);
                return current;
            }

            /**
             * 获取 提示声音 音量 最大值
             *
             * @return
             */
            public int getVoiceAlarmMax() {
                max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
                return max;
            }

            /**
             * 设置 提示声音 的音量
             *
             * @param value
             */
            public void setVoiceAlarmValue(int value) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_ALARM, value, AudioManager.FLAG_PLAY_SOUND);//设置 铃声音量 音量大小为 0 静音
            }//设置 提示声音 的音量

            /**
             * 游戏过程中只允许调整多媒体音量，而不允许调整通话音量。
             */
            public void gemgMusiceNoSet() {
                activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
            }//游戏过程中只允许调整多媒体音量，而不允许调整通话音量。

        }

        //ScreenListener 监听屏幕状态类
        public static class ScreenListener {
            /**
             * 监听
             * 手机屏幕点亮
             * 手机屏幕锁屏
             * 手机屏幕解锁
             * <p>
             * 使用实例:
             * GT.ScreenListener screenListener  = new GT.ScreenListener(this); //初始化 ScreenListener
             * screenListener.begin(new GT.ScreenListener.ScreenStateListener(){....} //new 一个匿名内部类 即可
             * 在销毁该 Activity 时一定要 调用该方法来注销广播
             * unregisterListener(); 方法来注销该广播
             */

            private Context context2;                                //联系上下文
            private ScreenBroadcastReceiver mScreenReceiver;        //定义一个广播
            private ScreenStateListener mScreenStateListener;       //定义个内部接口

            /**
             * 初始化
             */
            public ScreenListener(Context context) {
                this.context2 = context;
                mScreenReceiver = new ScreenBroadcastReceiver();//初始化广播
            }

            /**
             * 自定义接口
             */
            public interface ScreenStateListener {
                void onScreenOn();            //手机屏幕点亮

                void onScreenOff();            //手机屏幕锁屏

                void onUserPresent();        //手机屏幕解锁
            }

            /**
             * 获取screen的状态
             */
            private void getScreenState() {
                //初始化powerManager
                PowerManager manager = (PowerManager) context2.getSystemService(Context.POWER_SERVICE);
                if (manager.isScreenOn()) {   //如果监听已经开启
                    if (mScreenStateListener != null) {
                        mScreenStateListener.onScreenOn();
                    }
                } else {                      //如果监听没开启
                    if (mScreenStateListener != null) {
                        mScreenStateListener.onScreenOff();
                    }
                }
            }

            /**
             * 写一个内部的广播
             */
            private class ScreenBroadcastReceiver extends BroadcastReceiver {
                private String action = null;

                @Override
                public void onReceive(Context context, Intent intent) {
                    action = intent.getAction();
                    if (Intent.ACTION_SCREEN_ON.equals(action)) {        //屏幕亮时操作
                        mScreenStateListener.onScreenOn();
                    } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {   //屏幕关闭时操作
                        mScreenStateListener.onScreenOff();
                    } else if (Intent.ACTION_USER_PRESENT.equals(action)) {//解锁时操作
                        mScreenStateListener.onUserPresent();
                    }
                }
            }

            /**
             * 开始监听广播状态
             */
            public void begin(ScreenStateListener listener) {
                mScreenStateListener = listener;
                registerListener();                                //注册监听
                getScreenState();                                //获取监听
            }

            /**
             * 启动广播接收器
             */
            private void registerListener() {
                IntentFilter filter = new IntentFilter();
                filter.addAction(Intent.ACTION_SCREEN_ON);            //屏幕亮起时开启的广播
                filter.addAction(Intent.ACTION_SCREEN_OFF);            //屏幕关闭时开启的广播
                filter.addAction(Intent.ACTION_USER_PRESENT);        //屏幕解锁时开启的广播
                context2.registerReceiver(mScreenReceiver, filter);    //发送广播

            }

            /**
             * 解除广播
             */
            public void unregisterListener() {
                context2.unregisterReceiver(mScreenReceiver); //注销广播
            }
        }

        //HeadsetPlugReceiver 监听耳机是否插入
        public static class GT_HeadsetPlugReceiver {

            /**
             * 监听 耳机
             * <p>
             * 使用实例:
             * GT.GT_HeadsetPlugReceiver gt_headsetPlugReceiver = new GT.GT_HeadsetPlugReceiver(this); //初始化 GT_HeadsetPlugReceiver
             * gt_headsetPlugReceiver.isHeadset_TF();    //获取当前耳机的状态  插入则返回 true 否则返回 false
             * 在销毁该 Activity 时一定要 调用该方法来注销广播
             * unregisterListener(); 方法来注销该广播
             */

            private Activity activity;
            private static boolean headset_TF;//定义耳机是否插入
            private HeadsetPlugReceiver headsetPlugReceiver;//监听手机是否有耳机插入广播

            /**
             * 检测是否插入耳机
             *
             * @return true则插入 false则未插入
             */
            public boolean isHeadset_TF() {
                registerHeadsetPlugReceiver();
                return headset_TF;
            }

            /**
             * 实例化 耳机监听
             *
             * @param activity
             */
            public GT_HeadsetPlugReceiver(Activity activity) {
                this.activity = activity;
            }

            /**
             * /注销广播
             */
            public void unregisterListener() {
                activity.unregisterReceiver(headsetPlugReceiver); //注销广播
            }

            /**
             * 注册 广播
             */
            public void registerHeadsetPlugReceiver() {
                headsetPlugReceiver = new HeadsetPlugReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.HEADSET_PLUG");
                activity.registerReceiver(headsetPlugReceiver, intentFilter);
            }

            /**
             * 内部类
             */
            private static class HeadsetPlugReceiver extends BroadcastReceiver {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (intent.hasExtra("state")) {
                        if (intent.getIntExtra("state", 0) == 0) {
                            if (LOG.GT_LOG_TF)
                                headset_TF = false;
                        } else if (intent.getIntExtra("state", 0) == 1) {
                            if (LOG.GT_LOG_TF)
                                headset_TF = true;
                        }
                    }
                }
            }

        }

        //Spiritleve 屏幕旋转监听
        public abstract static class Spiritleve implements SensorEventListener {
            /**
             * 用法如下：
             * //屏幕旋转监听 内部类
             * class SV extends SpiritleveView{
             * *
             * public SV(Context context) {
             * super(context);
             * }
             * *
             *
             * @Override protected void getPosition(float xAngle, float yAngle) {
             * super.getPosition(xAngle, yAngle);
             * GT.log("X:" + (int)xAngle + "," + "Y:" + (int)yAngle);
             * }
             * }
             * *
             * 最后再在方法中初始化
             * new SV(activity);
             * *
             */
            float[] acceleromterValues = new float[3];//加速度传感器的值
            float[] magneticValues = new float[3];//磁场传感器的值

            //动作定义 常量
            public static final int LIE_LOW = 0;         //平躺
            public static final int SLEEPER = 1;         //卧铺
            public static final int STAND_RIGHT = 2;     //右立;
            public static final int LEFT_STANDING = 3;   //左立
            public static final int STAND = 4;           //站立
            public static final int HANDSTAND = 5;       //倒立

            public static final int ZHP = 1;            //正横屏
            public static final int FHP = -1;           //反横屏
            public static final int ZSP = 2;            //正竖屏
            public static final int FSP = -2;           //反竖屏

            private SensorManager sensorManager;       //定义取消屏幕监听

            public Spiritleve(Context context) {
                sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);//获取传感器管理器
                //为磁场传感器注册监听器
                sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_GAME);
                //为加速度传感器注册监听器
                sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
            }

            @Override
            public void onSensorChanged(SensorEvent event) {    //值改变触发

                if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                    acceleromterValues = event.values.clone();//获取加速度传感器的值
                } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                    magneticValues = event.values.clone();//获取磁场传感器的值
                }

                float[] R = new float[9];//保存旋转数据的数组
                float[] values = new float[3];//保存方向数据的数组

                SensorManager.getRotationMatrix(R, null, acceleromterValues, magneticValues);//获得一个包含旋转矩阵的数组
                SensorManager.getOrientation(R, values);//获取方向值

                float xAngle = (float) Math.toDegrees(values[1]);//x轴旋转角度
                float yAngle = (float) Math.toDegrees(values[2]);//y轴旋转角度

                getPosition(xAngle, yAngle);//获取小球的位置坐标

            }

            //根据X轴和Y轴的旋转角度确定小篮球的位置
            protected void getPosition(float xAngle, float yAngle) {
                            /*
                                这里会返回具体的手机位置信息
                                使用 getMobilePosition 或 getScreenPosition 方法判定 当前手机的位置
                             */
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                //精度改变触发
            }

            /**
             * 获取当前手机位置
             * 手机仰天躺(LIE_LOW)、手机卧铺躺(SLEEPER)、手机右立(STAND_RIGHT)、
             * 手机左立(LEFT_STANDING)、手机站起(STAND)、手机倒立(HANDSTAND)
             */
            public int getMobilePosition(float xAngle, float yAngle) {
                int X = (int) xAngle;
                int Y = (int) yAngle;
                if ((X >= -10 && X <= 10) && (Y >= -10 && Y <= 10)) {//手机仰天躺
                    return LIE_LOW;
                } else if ((X >= -10 && X <= 10) && (Y >= 170 && Y <= 179)) {//手机卧铺躺
                    return SLEEPER;
                } else if ((X >= 0 && X <= 2) && (Y >= 11 && Y <= 90)) {//手机右立
                    return STAND_RIGHT;
                } else if ((X >= 0 && X <= 2) && (Y <= -1 && Y >= -90)) {//手机左立
                    return LEFT_STANDING;
                } else if (X <= -50 && X >= -90) {//手机站起
                    return STAND;
                } else if ((X >= 50 && X <= 90)) {//手机倒立
                    return HANDSTAND;
                }
                return SLEEPER;//默认仰天躺
            }

            /**
             * 获取当前手机屏幕位置
             * 正横屏(ZHP.1)、反横屏(FHP.-1)、竖屏(SP.0)
             */
            public int getScreenPosition(float xAngle, float yAngle) {
                int X = (int) xAngle;
                int Y = (int) yAngle;

                if ((X >= -30 && X <= 0) && (Y <= -40 && Y >= -179)) {
                    return ZHP;
                } else if ((X >= -30 && X <= 0) && (Y >= 40 && Y <= 179)) {
                    return FHP;
                } else if ((X >= -90 && X <= -10) && (Y >= -39 && Y <= 179)) {
                    return ZSP;
                } else if ((X <= 90 && X >= 10) && (Y <= 39 && Y >= -179)) {
                    return FSP;
                }
                return -181;//默认正横屏
            }

            public void close() {
                sensorManager.unregisterListener(this);//取消注册的屏幕监听
            }

        }


    }

//============================================= 多媒体类 ========================================

    /**
     * 播放音乐 长音频
     */
    public static class GT_MediaPlayer {

        /**
         * 本类使用案列：
         * GT.GT_MediaPlayer mediaPlayer = new GT.GT_MediaPlayer(this);//实例化对象
         * mediaPlayer.loadMusic(R.raw.bg_music);  //加载 或 更新 将要播放的 音频， 此方法可用于 更新接下来要播放的音频
         * mediaPlayer.play_pause();//暂停 或 播放
         * mediaPlayer.stop();//停止播放
         * mediaPlayer.close();//释放资源
         * <p>
         * 相关属性设置
         * int getDuration()：获取流媒体的总播放时长，单位是毫秒。
         * int getCurrentPosition()：获取当前流媒体的播放的位置，单位是毫秒。
         * void seekTo(int msec)：设置当前MediaPlayer的播放位置，单位是毫秒。
         * void setLooping(boolean looping)：设置是否循环播放。
         * boolean isLooping()：判断是否循环播放。
         * boolean  isPlaying()：判断是否正在播放。
         * void prepare()：同步的方式装载流媒体文件。
         * void prepareAsync()：异步的方式装载流媒体文件。
         * void release ()：回收流媒体资源。
         * void setAudioStreamType(int streamtype)：设置播放流媒体类型。
         * void setWakeMode(Context context, int mode)：设置CPU唤醒的状态。
         * setNextMediaPlayer(MediaPlayer next)：设置当前流媒体播放完毕，下一个播放的MediaPlayer。
         */
        private boolean isPlay = true;    //定义是否为可播放状态
        private Activity activity;
        private MediaPlayer mediaPlayer;
        private int resid = 0;
        private String url = null;


        /**
         * 获取 mediaPlayer 组件
         *
         * @return
         */
        public MediaPlayer getMediaPlayer() {
            return mediaPlayer;
        }

        /**
         * 初始化音频播放的上下文
         *
         * @param activity
         */
        public GT_MediaPlayer(Activity activity) {
            this.activity = activity;
        }//初始化 上下文

        /**
         * 加载 res 目录下的资源文件
         *
         * @param resid
         * @return
         */
        public GT_MediaPlayer loadMusic(int resid) {
            this.resid = resid;
            if (!isPlay) {       //停止过播放
                if (mediaPlayer.isPlaying()) {//如果属于播放状态
                    mediaPlayer.stop();//停止播放
                    mediaPlayer.release();//释放资源
                }
            }
            mediaPlayer = null;//清空内存中对象
            mediaPlayer = MediaPlayer.create(activity, resid);    //初始化 MediaPlayer 对象
            isPlay = true;//恢复可播放状态
            return this;
        }//加载 res 目录下的资源文件

        /**
         * 获取 网络 或 SD 上的的音频资源
         *
         * @param url
         * @return
         */
        public GT_MediaPlayer loadMusic(String url) {
            this.url = url;
            if (!isPlay) {       //停止过播放
                if (mediaPlayer.isPlaying()) {//如果属于播放状态
                    mediaPlayer.stop();//停止播放
                }
                mediaPlayer.release();//释放资源
            }
            mediaPlayer = null;//清空内存中对象
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();//预加载音频
            } catch (IOException e) {
                //                e.printStackTrace();
                GT.err(getLineInfo(1), "你的音频资源可能 需要添加 网络或访问SD卡的读取权限，否则无法进行有效的获取资源 url:" + url);
            }
            isPlay = true;//恢复可播放状态
            return this;
        }//获取 网络 或 SD 上的的音频资源

        /**
         * 播放音频
         *
         * @return
         */
        public GT_MediaPlayer play() {
            if (mediaPlayer != null) {
                recover_play();//如果音频被停止了就恢复音频可播放，在进行 start
                mediaPlayer.start();
            }
            return this;
        }//播放

        /**
         * 暂停播放
         *
         * @return
         */
        public GT_MediaPlayer pause() {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
            }
            return this;
        }//暂停

        /**
         * 播放 与 暂停
         *
         * @return
         */
        public GT_MediaPlayer play_pause() {
            if (mediaPlayer != null) {
                recover_play();//如果音频被停止了就恢复音频可播放，在进行 start
                if (!mediaPlayer.isPlaying()) {        //如果当前的 mediaPlayer 处于暂停状态  且 播放状态为 false 没有在播放
                    mediaPlayer.start();//继续播放
                } else {  //当前处于音乐暂停状态
                    mediaPlayer.pause();//暂停音乐
                }
            }
            return this;
        }//播放 与 暂停

        /**
         * 停止音乐
         *
         * @return
         */
        public GT_MediaPlayer stop() {
            if (mediaPlayer != null && isPlay) {
                isPlay = false;//设置为暂停状态
                mediaPlayer.stop();
            }
            return this;
        }//停止音乐

        /**
         * 恢复可播放
         *
         * @return
         */
        private GT_MediaPlayer recover_play() {
            if (mediaPlayer != null) {
                if (!isPlay) {       //停止过播放
                    if (mediaPlayer.isPlaying()) {//如果属于播放状态
                        mediaPlayer.stop();//停止播放
                    }
                    mediaPlayer.release();//释放资源
                    mediaPlayer = null;//清空内存中对象
                    if (resid != 0) {
                        mediaPlayer = MediaPlayer.create(activity, resid);    //初始化 MediaPlayer 对象
                    } else if (url != null) {
                        mediaPlayer = new MediaPlayer();
                        try {
                            mediaPlayer.setDataSource(url);
                            mediaPlayer.prepare();//预加载音频
                        } catch (IOException e) {
                            GT.err(getLineInfo(1), "你的音频资源可能 需要添加 网络或访问SD卡的读取权限，否则无法进行有效的获取资源 url:" + url);
                        }
                    }
                    isPlay = true;//恢复可播放状态
                }
            }
            return this;
        }//恢复可播放

        /**
         * 释放资源
         */
        public void close() {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {//如果属于播放状态
                    mediaPlayer.stop();//停止播放
                }
                mediaPlayer.release();//释放资源
                mediaPlayer = null;
            }
        }//释放资源


    }

    /**
     * 播放音频 短音频
     */
    public static class GT_SoundPool {

        private Context context;
        private static SoundPool soundPool;
        private AudioAttributes attr = null;
        private static Map<String, Integer> map = new HashMap<>();      //初始化 map  用于存放 音频 key 与 值
        private static Map<String, Integer> mapMusic = new HashMap<>(); //初始化 mapMusic 用于存放待播放的音频

        /**
         * 初始化 Content
         *
         * @param context
         */
        public GT_SoundPool(Context context) {
            this.context = context;
        }

        /**
         * 设置音频属性
         *
         * @param attr
         * @return
         */
        public GT_SoundPool setAudioAttributes(AudioAttributes attr) {
            this.attr = attr;
            return this;
        }//设置音频属性


        /**
         * 加载音频
         *
         * @param map
         * @return
         */
        @SuppressLint("NewApi")
        public GT_SoundPool initMusic(Map<String, Integer> map) {
            if (map != null) {
                this.map = map;
                if (attr == null) {
                    //设置音效属性 如果为空,就设置默认的音频属性
                    attr = new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_GAME)//设置音效的使用场景 为游戏
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)//设置音效类型
                            .build();
                }

                //初始化
                soundPool = new SoundPool.Builder()
                        .setAudioAttributes(attr)//设置音效池的属性
                        .setMaxStreams(map.size())//最大容纳 动态添加最大值 个音频
                        .build();

                loadMusic();//初始化 音频流
            }
            return this;
        }

        /**
         * 添加音频
         *
         * @param key
         * @param rawId
         * @return
         */
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public GT_SoundPool addMusic(String key, int rawId) {
            if (map != null) {
                if (!map.containsKey(key)) { //如果当前 map 中没有此 key
                    map.put(key, rawId); //将值保存到 map 中

                    if (attr == null) {
                        //设置音效属性 如果为空,就设置默认的音频属性
                        attr = new AudioAttributes.Builder()
                                .setUsage(AudioAttributes.USAGE_GAME)//设置音效的使用场景 为游戏
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)//设置音效类型
                                .build();
                    }

                    //初始化
                    soundPool = new SoundPool.Builder()
                            .setAudioAttributes(attr)//设置音效池的属性
                            .setMaxStreams(map.size())//最大容纳 动态添加最大值 个音频
                            .build();

                    loadMusic();//初始化 音频流

                } else {
                    GT.log(getLineInfo(1), "添加音频无效，当前已经包含相同的 key，无法再进行装载相同的 key");//提示无效的添加
                }
            }
            return this;
        } //添加音频

        /**
         * 初始化音频
         */
        private void loadMusic() {
            if (map != null && mapMusic != null) {
                for (String key : map.keySet()) {
                    mapMusic.put(key, soundPool.load(context, map.get(key), 1));//初始化 待播放的音频
                }
            }
        }//初始化音频

        /**
         * 移除音频
         *
         * @param key
         * @return
         */
        public GT_SoundPool removalMusic(Integer key) {
            if (map != null && mapMusic != null) {
                if (map.containsKey(key)) {
                    map.remove(key);
                    mapMusic.remove(key);
                    loadMusic();//初始化音频
                } else {
                    log(getLineInfo(1), "移除音频失败，当前并不存在此 key:" + key);
                }
            }
            return this;
        }//移除音频

        /**
         * 清空音频
         *
         * @return
         */
        public GT_SoundPool clear() {
            if (map != null && mapMusic != null) {
                map.clear();
                mapMusic.clear();
            }
            return this;
        }//清空音频

        /**
         * 修改音频
         *
         * @param key
         * @param rawId
         * @return
         */
        public GT_SoundPool updateMusic(String key, Integer rawId) {
            if (map != null && mapMusic != null) {
                if (map.containsKey(key)) {
                    map.put(key, rawId);
                    mapMusic.put(key, rawId);
                    loadMusic();//初始化音频
                } else {
                    GT.log(getLineInfo(1), "修改音频无效，当前并不存在当前 key，无法进行更新操作");//提示无效的更新
                }
            }
            return this;
        }//修改音频

        /**
         * 播放音频
         *
         * @param key  指定播放的音频key
         * @param loop 是否循环 false为不循环, true 为循环
         * @param rate 速率 为正常速率 1  最低为 0.5，最高为 2
         * @return
         */
        public GT_SoundPool play(String key, boolean loop, float rate) {
            //播放所选音频
            if (soundPool != null) {
                soundPool.play(
                        mapMusic.get(key),           //指定播放的音频 key
                        1,              //左声道 为0.0 到 1.0
                        1,             //右声道 为0.0 到 1.0
                        0,                 //优先级 0
                        (loop == true ? -1 : 0),    //是否循环 0为不循环, -1 为循环
                        rate                        //速率 为正常速率 1  最低为 0.5，最高为 2
                );
            }
            return this;
        }
    }

    /**
     * 播放视频
     */
    public static class GT_Video implements SurfaceHolder.Callback {

        /**
         * 使用说明：
         * 第一步：在 xml 中 定义好 SurfaceView 组件
         * 第二步：video = new GT.GT_Video(this,R.raw.lmh,surfaceView);//初始化 GT_Video 视频播放器
         * 第三步：播放 video.play();、暂停 video.pause();、 停止 video.stop();、释放资源 video.close();
         */

        private MediaPlayer mediaPlayer = null;
        private SurfaceView surfaceView;
        private SurfaceHolder surfaceHolder;
        private Context context;
        private int resId;
        private static boolean isPlay = true;   //定义是否被停止播放过视频

        /**
         * 初始化视频 ()注意，需要单击才有效果 一个坑
         *
         * @param context     上下文
         * @param resId       资源id
         * @param surfaceView surfaceView
         */
        public GT_Video(Context context, int resId, SurfaceView surfaceView) {
            this.context = context;
            this.surfaceView = surfaceView;
            this.resId = resId;
            surfaceHolder = this.surfaceView.getHolder();
            surfaceHolder.addCallback(this);
        }

        /**
         * 播放 视频
         *
         * @return
         */
        public GT_Video play() {
            GT.err("mediaPlayer:" + mediaPlayer);
            if (mediaPlayer != null) {
                recover_play();
                GT.err("播放");
                mediaPlayer.start();
            }
            return this;
        }

        /**
         * 暂停视频
         *
         * @return
         */
        public GT_Video pause() {
            if (mediaPlayer != null) {
                recover_play();
                mediaPlayer.pause();
            }
            return this;
        }

        /**
         * 释放资源
         *
         * @return
         */
        public GT_Video stop() {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                isPlay = false;
            }
            return this;
        }

        /**
         * 恢复可播放
         *
         * @return
         */
        private MediaPlayer recover_play() {
            if (!isPlay) {       //停止过播放
                close();
                mediaPlayer = null;//清空内存中对象
                if (resId != 0) {
                    mediaPlayer = MediaPlayer.create(context, resId);//设置加载的视频资源
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDisplay(surfaceHolder);    //设置显示视频显示在SurfaceView上
                }
                isPlay = true;//恢复可播放状态
            }
            return mediaPlayer;
        }//恢复可播放


        /**
         * 释放资源
         */
        public void close() {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }//释放资源


        /**
         * 初始化 视频 数据
         *
         * @param holder
         */
        public void surfaceCreated(SurfaceHolder holder) {
            mediaPlayer = MediaPlayer.create(context, resId);//设置加载的视频资源
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDisplay(surfaceHolder);    //设置显示视频显示在SurfaceView上
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }

//======================================= Run GT 的内部注解 =====================================

    /**
     * 注解类
     */
    public static class Annotations {

        /**
         * 为给 Activity 类 标的注解 用法如下：
         *
         * @Activity(R.layout.activity_main) public class DemoActivity extends AppCompatActivity {....}
         */
        @Target(ElementType.TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface GT_AnnotationActivity {
            int value();
        }

        /**
         * 为给 Fragment 类 标的注解 用法如下：
         */
        @Target(ElementType.TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface GT_AnnotationFragment {
            int value();
        }

        /**
         * 为给 DialogFragment 类 标的注解 用法如下：
         */
        @Target(ElementType.TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface GT_AnnotationDialogFragment {
            int value();
        }

        /**
         * 为悬浮窗注解
         */
        @Target(ElementType.TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface GT_AnnotationFloatingWindow {
            int value();
        }

        /**
         * 为 PopupWindow 注解
         */
        @Target(ElementType.TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface GT_AnnotationPopupWindow {
            int value();
        }

        /**
         * 为 适配器 注解
         */
        @Target(ElementType.TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface GT_AnnotationAdapter {
            int value();
        }

        /**
         * 为 自定义 View 给需要 特别复杂的View 给与页面的操作方式
         */
        @Target(ElementType.TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface GT_AnnotationView {
            int value();
        }

        /**
         * 为给 View 组件标的注解
         * 用法如下：
         *
         * @GT_View(R.id.ioc_tv) private TextView tv;
         */
        @Target(ElementType.FIELD)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface GT_View {
            int value();
        }


        /**
         * 为给 GT_Window 类 标的注解 用法如下：
         */
        @Target(ElementType.TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface GT_AnnotationWindow {
            int value();
        }


        /**
         * 为给 单击方法 标的注解
         * 用法如下：切记 单击方法一定要是 public 的修饰符
         *
         * @Click({R.id.ioc_btn01,R.id.ioc_btn02,R.id.ioc_btn03}) public void setButtonOnClickListener(View view){
         * switch (view.getId()){
         * case R.id.ioc_btn01:
         * Log.e(TAG, "单击 1 号" );
         * break;
         * case R.id.ioc_btn02:
         * Log.e(TAG, "单击 2 号" );
         * break;
         * case R.id.ioc_btn03:
         * Log.e(TAG, "单击 3 号" );
         * break;
         * }
         * }
         */
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.RUNTIME)
        @OnClickEvent(listenerType = View.OnClickListener.class, listenerSetter = "setOnClickListener", methodName = "onClick")
        public @interface GT_Click {
            int[] value();
        }


        /**
         * 用于协助 单击方法的注解
         */
        @Target(ElementType.ANNOTATION_TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        private @interface OnClickEvent {
            Class<?> listenerType();//接口类型

            String listenerSetter();//设置的方法

            String methodName();//接口里面要实现的方法
        }


        /**
         * 用于注解 普通成员变量的注解 可携带 参数
         * 注意：如果是 对象 那么 构造函数一定不能设置为 单例（构造函数修饰符不能为 private 私有的）否则会注解注入失败！
         */
        @Target(ElementType.FIELD)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface GT_Object {

            /**
             * 定义当前注解 支持的参数类型
             */
            interface TYPE {
                String BYTE = "byte";
                String SHORT = "short";
                String INT = "int";
                String LONG = "long";
                String FLOAT = "float";
                String DOUBLE = "double";
                String BOOLEAN = "boolean";
                String CHAR = "char";
                String STRING = "String";

                String BYTES = "bytes";
                String SHORTS = "shorts";
                String INTS = "ints";
                String LONGS = "longs";
                String FLOATS = "floats";
                String DOUBLES = "doubles";
                String BOOLEANS = "booleans";
                String CHARS = "chars";
                String STRINGS = "Strings";
            }

            /**
             * 单参数的传递
             **/
            byte valueByte() default 0;

            short valueShort() default 0;

            int valueInt() default 0;

            long valueLong() default 0L;

            float valueFloat() default 0.0f;

            double valueDouble() default 0.0d;

            boolean valueBoolean() default false;

            char valueChar() default 0;

            String valueString() default "";

            /**
             * 多参数的传递
             **/
            byte[] valueBytes() default {};

            short[] valueShorts() default {};

            int[] valueInts() default {};

            long[] valueLongs() default {};

            float[] valueFloats() default {};

            double[] valueDoubles() default {};

            boolean[] valueBooleans() default {};

            char[] valueChars() default {};

            String[] valueStrings() default {};

            /**
             * 修改参数的类型
             **/
            String type() default "";

            String[] types() default {};

            /**
             * 要赋值的方法
             **/
            String function() default "";

            String[] functions() default {};

        }

        /**
         * 资源注解
         */
        public @interface GT_Res {

            /**
             * 字符串 注解
             */
            @Target(ElementType.FIELD)
            @Retention(RetentionPolicy.RUNTIME)
            @interface GT_String {
                int value();
            }

            /**
             * 颜色 注解
             */
            @Target(ElementType.FIELD)
            @Retention(RetentionPolicy.RUNTIME)
            @interface GT_Color {
                int value();
            }

            /**
             * 尺寸 注解
             */
            @Target(ElementType.FIELD)
            @Retention(RetentionPolicy.RUNTIME)
            @interface GT_Dimen {
                int value();
            }

            /**
             * 图片 注解
             */
            @Target(ElementType.FIELD)
            @Retention(RetentionPolicy.RUNTIME)
            @interface GT_Drawable {
                int value();
            }

            /**
             * 动画 注解
             */
            @Target(ElementType.FIELD)
            @Retention(RetentionPolicy.RUNTIME)
            @interface GT_Animation {
                int value();
            }

            /**
             * 字符串数组 注解
             */
            @Target(ElementType.FIELD)
            @Retention(RetentionPolicy.RUNTIME)
            @interface GT_StringArray {
                int value();
            }

            /**
             * 整数数组 注解
             */
            @Target(ElementType.FIELD)
            @Retention(RetentionPolicy.RUNTIME)
            @interface GT_IntArray {
                int value();
            }

            /**
             * 将 xml 文件解析成 View 注解
             */
            @Target(ElementType.FIELD)
            @Retention(RetentionPolicy.RUNTIME)
            @interface GT_Layout {
                int value();
            }

        }

        /**
         * 集合注解
         */
        public @interface GT_Collection {

            /**
             * List 注解
             */
            @Target(ElementType.FIELD)
            @Retention(RetentionPolicy.RUNTIME)
            @interface GT_List {

                String[] valueObject() default {};

                Class<?>[] valueClass() default {};

                String[] valueString() default {};

                byte[] valueByte() default {};

                short[] valueShort() default {};

                int[] valueInt() default {};

                long[] valueLong() default {};

                float[] valueFloat() default {};

                double[] valueDouble() default {};

                boolean[] valueBoolean() default {};

                char[] valueChar() default {};

            }


            /**
             * Map 注解
             */
            @Target(ElementType.FIELD)
            @Retention(RetentionPolicy.RUNTIME)
            @interface GT_Map {

                String[] valueKey() default {};

                String[] valueObject() default {};

                Class<?>[] valueClass() default {};

                String[] valueString() default {};

                byte[] valueByte() default {};

                short[] valueShort() default {};

                int[] valueInt() default {};

                long[] valueLong() default {};

                float[] valueFloat() default {};

                double[] valueDouble() default {};

                boolean[] valueBoolean() default {};

                char[] valueChar() default {};
            }


            /**
             * Set 注解
             */
            @Target(ElementType.FIELD)
            @Retention(RetentionPolicy.RUNTIME)
            @interface GT_Set {

                String[] valueObject() default {};

                Class<?>[] valueClass() default {};

                String[] valueString() default {};

                byte[] valueByte() default {};

                short[] valueShort() default {};

                int[] valueInt() default {};

                long[] valueLong() default {};

                float[] valueFloat() default {};

                double[] valueDouble() default {};

                boolean[] valueBoolean() default {};

                char[] valueChar() default {};

            }

        }

    }

    /**
     * 注解工具类
     */
    public static class AnnotationAssist {

        //================================   下面是 初始化 注解内容   ==============================
        //主要用于注解 Activity 与 Fragment 共有的
        public static void initAll(Object object) {
            if (context == null) {
                context = getActivity();
            }

            // SQL 注解
            initSQL(object);                         //为加载 List SQL 成员变量初始化

            //注解加载布局
            initAnnotationActivity(object);          //为加载 Activity 布局初始化
            initAnnotationFragment(object);          // 为加载 AnnotationFragment 布局初始化
            initGT_AnnotationDialogFragment(object); // 为加载 DialogFragment 布局初始化
            initGT_AnnotationAdapter(object);       // 为加载 Adapter 布局初始化

            //框架注解
            initGT_Fragment(object);                 //为加载 Fragment 框架
            initGT_SharedPreferences(object);        //SP框架注解
            initSerialPortUtils(object);             //串口框架注解
            initTCP(object);                         //TCP框架注解

            initAnnotationFloatingWindow(object);    //注解悬浮窗
            initAnnotationView(object);             //注解 View
            initAnnotationPopupWindow(object);      //注解弹出式窗 PopupWindow

            //这两个需要重点照顾需要
            initView(object);                        //为加载 组件 初始化
            initClick(object);                       //注解单击事件

            // Java 注解部分
            initObject(object);                    //为加载 Object 成员变量初始化
            initList(object);                      //为加载 List 成员变量初始化
            initMap(object);                       //为加载 Map 成员变量初始化
            initSet(object);                       //为加载 Set 成员变量初始化

            //资源 注解
            initAnimation(object);            //为加载 Animation 资源初始化
            initDimen(object);                //为加载 Dimen 资源初始化
            initDrawable(object);             //为加载 Style 资源初始化
            initColor(object);                //为加载 Color 资源初始化
            initString(object);               //为加载 String 资源初始化
            initIntArray(object);             //为加载 IntArray 资源初始化
            initStringArray(object);          //为加载 StringArray 资源初始化
            initLayout(object);               //为加载 Layout 资源初始化

        }

        //================================   下面是 SQL 的注解内容   ==============================

        /**
         * @param object
         * @param mClass
         * @注入 SQL 资源字符串
         */
        private static Object classObject = null;//最终注入的值

        private static void initSQL(Object object) {
            Field[] fields = object.getClass().getDeclaredFields();//获致当前 Activity 所有成员变更
            for (Field field : fields) {

                //获取识别注解
                Hibernate.Build initView_Hibernate = field.getAnnotation(Hibernate.Build.class);

                //初始化GT数据库
                if (initView_Hibernate != null) {

                    String sqlName = initView_Hibernate.setSqlName();//数据库名称
                    int sqlVersion = initView_Hibernate.setSqlVersion();//数据库版本号
                    Class<?>[] classes = initView_Hibernate.setSqlTables();//数据库映射的表
                    boolean IsThread = initView_Hibernate.setIsThread();//是否使用线程创建数据库
                    String passWord = initView_Hibernate.setPassWord();//获取加密密码

                    if (getActivity() == null) {
                        err(getLineInfo(LOG.lineInfoIndex), "注入数据库失败！请在 Activity 中绑定GT注解");
                        return;
                    }

                    if (IsThread) {
                        //使用线程的方式创建

                        Thread.runJava(new Runnable() {
                            @Override
                            public void run() {

                                //如果用户没有自己设置映射的表那就扫描标注Bean,Entity的类做表
                                if (classes.length == 0) {
                                    classObject = new Hibernate().
                                            initSQL(sqlName, sqlVersion, passWord, ApplicationUtils.getPackageName(getActivity()));
                                } else {
                                    classObject = new Hibernate().
                                            initSQL(sqlName, sqlVersion, passWord, classes);
                                }

                                //实例注入
                                try {
                                    field.setAccessible(true);
                                    field.set(object, classObject);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                    } else {
                        //不使用线程的方式创建

                        //如果用户没有自己设置映射的表那就扫描标注Bean,Entity的类做表
                        if (classes.length == 0) {
                            classObject = new Hibernate().
                                    initSQL(sqlName, sqlVersion, null, ApplicationUtils.getPackageName(getActivity()));
                        } else {
                            classObject = new Hibernate().
                                    initSQL(sqlName, sqlVersion, null, classes);
                        }
                        //实例注入
                        try {
                            field.setAccessible(true);
                            field.set(object, classObject);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }

            }
        }

        //================================   下面是 Java 的注解内容   ==============================

        /**
         * 参数版
         *
         * @param object
         */
        private static void initObject(Object object) {
            Field[] fields = object.getClass().getDeclaredFields();//获致所有成员变更
            for (Field field : fields) {
                Annotations.GT_Object initView = field.getAnnotation(Annotations.GT_Object.class);
                if (initView != null) {

                    //获取 完整的类路径
                    String classPage = field.toString();
                    String[] s = classPage.split(" ");
                    classPage = s[s.length - 2];


                    //实例化一个对象
                    Object object2 = null;
                    try {
                        object2 = Class.forName(classPage).newInstance();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    //获取参数的值类型
                    String type = initView.type();
                    String[] types = initView.types();

                    //创建保存 参数类型的容器
                    List<Object> valueList = new ArrayList<>();
                    if (type.length() != 0) {
                        valueType(type, valueList, initView, 0);//将当前的单个数据赋值到 listView 中
                    } else if (types.length != 0) {
                        for (int i = 0; i < types.length; i++) {
                            valueType(types[i], valueList, initView, i);//将当前的多个数据赋值到 listView 中
                        }
                    }

                    /** 获取注解传递过来的参数 **/
                    String function = initView.function();
                    String[] functions = initView.functions();

                    /**
                     * 获取当前方法所有方法
                     */
                    if (function.length() != 0 && valueList.size() != 0) {
                        functionValue(field, object2, function, valueList, 0);//对相应的方法进行赋值
                    } else if (functions.length != 0 && valueList.size() != 0) {
                        for (int i = 0; i < functions.length; i++) {
                            functionValue(field, object2, functions[i], valueList, i);//对相应的方法进行赋值
                        }
                    }

                    //给注解下面的 成员变量注入值
                    try {
                        field.setAccessible(true);
                        field.set(object, object2);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * 注入 List 资源字符串
         **/
        private static void initList(Object object) {

            Field[] fields = object.getClass().getDeclaredFields();//获致所有成员变更
            for (Field field : fields) {
                Annotations.GT_Collection.GT_List initView = field.getAnnotation(Annotations.GT_Collection.GT_List.class);
                if (initView != null) {

                    String[] valueObject = initView.valueObject();
                    Class<?>[] valueClass = initView.valueClass();
                    String[] valueString = initView.valueString();

                    byte[] valueByte = initView.valueByte();
                    short[] valueShort = initView.valueShort();
                    int[] valueInt = initView.valueInt();
                    long[] valueLong = initView.valueLong();
                    float[] valueFloat = initView.valueFloat();
                    double[] valueDouble = initView.valueDouble();
                    boolean[] valueBoolean = initView.valueBoolean();
                    char[] valueChar = initView.valueChar();

                    List<Object> objectList = new ArrayList<>();

                    if (valueObject.length != 0) {
                        for (Object value : valueObject) {
                            objectList.add(value);
                        }
                    } else if (valueClass.length != 0) {
                        for (Class cla : valueClass) {
                            String classPage = cla.toString();
                            String[] s = classPage.split(" ");
                            classPage = s[1];

                            //实例化一个对象
                            Object object2 = null;
                            try {
                                object2 = Class.forName(classPage).newInstance();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            objectList.add(object2);//添加每一个经过反射得到的 对象
                        }

                    } else if (valueString.length != 0) {
                        for (Object value : valueString) {
                            objectList.add(value);
                        }
                    } else if (valueByte.length != 0) {
                        for (Object value : valueByte) {
                            objectList.add(value);
                        }
                    } else if (valueShort.length != 0) {
                        for (Object value : valueShort) {
                            objectList.add(value);
                        }
                    } else if (valueInt.length != 0) {
                        for (Object value : valueInt) {
                            objectList.add(value);
                        }
                    } else if (valueLong.length != 0) {
                        for (Object value : valueLong) {
                            objectList.add(value);
                        }
                    } else if (valueFloat.length != 0) {
                        for (Object value : valueFloat) {
                            objectList.add(value);
                        }
                    } else if (valueDouble.length != 0) {
                        for (Object value : valueDouble) {
                            objectList.add(value);
                        }
                    } else if (valueBoolean.length != 0) {
                        for (Object value : valueBoolean) {
                            objectList.add(value);
                        }
                    } else if (valueChar.length != 0) {
                        for (Object value : valueChar) {
                            objectList.add(value);
                        }
                    }

                    //实例注入 List
                    try {
                        field.setAccessible(true);
                        field.set(object, objectList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

        }

        /**
         * 注入 Map 资源字符串
         **/
        private static void initMap(Object object) {

            Field[] fields = object.getClass().getDeclaredFields();//获致所有成员变更
            for (Field field : fields) {
                Annotations.GT_Collection.GT_Map initView = field.getAnnotation(Annotations.GT_Collection.GT_Map.class);
                if (initView != null) {

                    Map<Object, Object> objectMap = new HashMap<>();//创建一个 Map
                    String[] valueKey = initView.valueKey();
                    if (valueKey.length != 0) {    //如果不为 0 则创建 map 后并注入值 否则仅创建新的 Map 对象

                        String[] valueObject = initView.valueObject();
                        Class<?>[] valueClass = initView.valueClass();
                        String[] valueString = initView.valueString();

                        byte[] valueByte = initView.valueByte();
                        short[] valueShort = initView.valueShort();
                        int[] valueInt = initView.valueInt();
                        long[] valueLong = initView.valueLong();
                        float[] valueFloat = initView.valueFloat();
                        double[] valueDouble = initView.valueDouble();
                        boolean[] valueBoolean = initView.valueBoolean();
                        char[] valueChar = initView.valueChar();

                        if (valueObject.length != 0) {
                            for (int i = 0; i < valueKey.length; i++) {
                                objectMap.put(valueKey[i], valueObject[i]);
                            }
                        } else if (valueClass.length != 0) {
                            for (Class cla : valueClass) {

                                //截取 类的 class 路径属性
                                String classPage = cla.toString();
                                String[] s = classPage.split(" ");
                                classPage = s[1];

                                //实例化一个对象
                                Object object2 = null;
                                try {
                                    object2 = Class.forName(classPage).newInstance();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InstantiationException e) {
                                    e.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }

                                for (int i = 0; i < valueKey.length; i++) {
                                    objectMap.put(valueKey[i], object2);
                                }

                            }

                        } else if (valueString.length != 0) {
                            for (int i = 0; i < valueKey.length; i++) {
                                objectMap.put(valueKey[i], valueString[i]);
                            }
                        } else if (valueByte.length != 0) {
                            for (int i = 0; i < valueKey.length; i++) {
                                objectMap.put(valueKey[i], valueByte[i]);
                            }
                        } else if (valueShort.length != 0) {
                            for (int i = 0; i < valueKey.length; i++) {
                                objectMap.put(valueKey[i], valueShort[i]);
                            }
                        } else if (valueInt.length != 0) {
                            for (int i = 0; i < valueKey.length; i++) {
                                objectMap.put(valueKey[i], valueInt[i]);
                            }
                        } else if (valueLong.length != 0) {
                            for (int i = 0; i < valueKey.length; i++) {
                                objectMap.put(valueKey[i], valueLong[i]);
                            }
                        } else if (valueFloat.length != 0) {
                            for (int i = 0; i < valueKey.length; i++) {
                                objectMap.put(valueKey[i], valueFloat[i]);
                            }
                        } else if (valueDouble.length != 0) {
                            for (int i = 0; i < valueKey.length; i++) {
                                objectMap.put(valueKey[i], valueDouble[i]);
                            }
                        } else if (valueBoolean.length != 0) {
                            for (int i = 0; i < valueKey.length; i++) {
                                objectMap.put(valueKey[i], valueBoolean[i]);
                            }
                        } else if (valueChar.length != 0) {
                            for (int i = 0; i < valueKey.length; i++) {
                                objectMap.put(valueKey[i], valueChar[i]);
                            }
                        }

                    }

                    try {
                        field.setAccessible(true);
                        field.set(object, objectMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

        }

        /**
         * 注入 Set 资源字符串
         **/
        private static void initSet(Object object) {

            Field[] fields = object.getClass().getDeclaredFields();//获致所有成员变更
            for (Field field : fields) {
                Annotations.GT_Collection.GT_Set initView = field.getAnnotation(Annotations.GT_Collection.GT_Set.class);
                if (initView != null) {

                    String[] valueObject = initView.valueObject();
                    Class<?>[] valueClass = initView.valueClass();
                    String[] valueString = initView.valueString();

                    byte[] valueByte = initView.valueByte();
                    short[] valueShort = initView.valueShort();
                    int[] valueInt = initView.valueInt();
                    long[] valueLong = initView.valueLong();
                    float[] valueFloat = initView.valueFloat();
                    double[] valueDouble = initView.valueDouble();
                    boolean[] valueBoolean = initView.valueBoolean();
                    char[] valueChar = initView.valueChar();

                    Set<Object> objectSet = new HashSet<>();

                    if (valueObject.length != 0) {
                        for (Object value : valueObject) {
                            objectSet.add(value);
                        }
                    } else if (valueClass.length != 0) {
                        for (Class cla : valueClass) {
                            String classPage = cla.toString();
                            String[] s = classPage.split(" ");
                            classPage = s[1];

                            //实例化一个对象
                            Object object2 = null;
                            try {
                                object2 = Class.forName(classPage).newInstance();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            objectSet.add(object2);//添加每一个经过反射得到的 对象
                        }

                    } else if (valueString.length != 0) {
                        for (Object value : valueString) {
                            objectSet.add(value);
                        }
                    } else if (valueByte.length != 0) {
                        for (Object value : valueByte) {
                            objectSet.add(value);
                        }
                    } else if (valueShort.length != 0) {
                        for (Object value : valueShort) {
                            objectSet.add(value);
                        }
                    } else if (valueInt.length != 0) {
                        for (Object value : valueInt) {
                            objectSet.add(value);
                        }
                    } else if (valueLong.length != 0) {
                        for (Object value : valueLong) {
                            objectSet.add(value);
                        }
                    } else if (valueFloat.length != 0) {
                        for (Object value : valueFloat) {
                            objectSet.add(value);
                        }
                    } else if (valueDouble.length != 0) {
                        for (Object value : valueDouble) {
                            objectSet.add(value);
                        }
                    } else if (valueBoolean.length != 0) {
                        for (Object value : valueBoolean) {
                            objectSet.add(value);
                        }
                    } else if (valueChar.length != 0) {
                        for (Object value : valueChar) {
                            objectSet.add(value);
                        }
                    }

                    //实例注入 List
                    try {
                        field.setAccessible(true);
                        field.set(object, objectSet);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

        }

        //================================   下面是 通用 的注解内容   ==========================

        private static void initView(Object obj) {
            if (obj == null) return;
            Class<?> clazz = obj.getClass();//获取该类信息
            Field[] fields = clazz.getDeclaredFields();//获致所有成员变更
            for (Field field : fields) {
                Annotations.GT_View initView = field.getAnnotation(Annotations.GT_View.class);
                if (initView != null) {
                    int viewId = initView.value();
                    try {
                        field.setAccessible(true);
                        //匹配是 Activity 还是 View
                        Object[] objects = returnActivityOrView(obj);
                        Activity activity = (Activity) objects[0];
                        View view = (View) objects[1];
                        if (activity != null) {
                            field.set(obj, activity.findViewById(viewId));
                        } else if (view != null) {
                            field.set(obj, view.findViewById(viewId));
                        }
                    } catch (Exception e) {
                        GT.logs("e:" + e);
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * 注入控件
         *
         * @param object
         * @param mClass
         * @param view
         */
        private static void initView(Object object, View view) {
            Field[] fields = object.getClass().getDeclaredFields();//获致所有成员变更
            for (Field field : fields) {
                Annotations.GT_View initView = field.getAnnotation(Annotations.GT_View.class);
                if (initView != null) {
                    int viewId = initView.value();
                    try {
                        View viewById = view.findViewById(viewId);
                        field.setAccessible(true);
                        field.set(object, viewById);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * 注入点击事件
         */
        private static void initClick(Object object, View view) {
            Method[] methods = object.getClass().getMethods();//获取所有声明为公有的方法
            for (Method method : methods) {//遍历所有公有方法
                Annotation[] annotations = method.getAnnotations();//获取该公有方法的所有注解
                for (Annotation annotation : annotations) {//遍历所有注解
                    Class<? extends Annotation> annotationType = annotation.annotationType();//获取具体的注解类
                    Annotations.OnClickEvent onClickEvent = annotationType.getAnnotation(Annotations.OnClickEvent.class);//取出注解的onClickEvent注解
                    if (onClickEvent != null) {//如果不为空
                        try {
                            Method valueMethod = annotationType.getDeclaredMethod("value");//获取注解InjectOnClick的value方法
                            int[] viewIds = (int[]) valueMethod.invoke(annotation, (Object[]) null);//获取控件值
                            Class<?> listenerType = onClickEvent.listenerType();//获取接口类型
                            String listenerSetter = onClickEvent.listenerSetter();//获取set方法
                            String methodName = onClickEvent.methodName();//获取接口需要实现的方法
                            MyInvocationHandler handler = new MyInvocationHandler(object);//自己实现的代码，负责调用
                            handler.setMethodMap(methodName, method);//设置方法及设置方法
                            Object object2 = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class<?>[]{listenerType}, handler);//创建动态代理对象类
                            for (int viewId : viewIds) {//遍历要设置监听的控件
                                View view2 = view.findViewById(viewId);//获取该控件
                                Method m = view2.getClass().getMethod(listenerSetter, listenerType);//获取方法
                                m.invoke(view2, object2);//调用方法
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        /**
         * 注入点击事件
         */
        private static void initClick(Object obj) {
            Method[] methods = obj.getClass().getMethods();//获取所有声明为公有的方法
            for (Method method : methods) {//遍历所有公有方法
                Annotation[] annotations = method.getAnnotations();//获取该公有方法的所有注解
                for (Annotation annotation : annotations) {//遍历所有注解
                    Class<? extends Annotation> annotationType = annotation.annotationType();//获取具体的注解类
                    Annotations.OnClickEvent onClickEvent = annotationType.getAnnotation(Annotations.OnClickEvent.class);//取出注解的onClickEvent注解
                    if (onClickEvent != null) {//如果不为空
                        try {
                            Method valueMethod = annotationType.getDeclaredMethod("value");//获取注解InjectOnClick的value方法
                            int[] viewIds = (int[]) valueMethod.invoke(annotation, (Object[]) null);//获取控件值
                            Class<?> listenerType = onClickEvent.listenerType();//获取接口类型
                            String listenerSetter = onClickEvent.listenerSetter();//获取set方法
                            String methodName = onClickEvent.methodName();//获取接口需要实现的方法
                            MyInvocationHandler handler = new MyInvocationHandler(obj);//自己实现的代码，负责调用
                            handler.setMethodMap(methodName, method);//设置方法及设置方法
                            Object object2 = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class<?>[]{listenerType}, handler);//创建动态代理对象类

                            //匹配是 Activity 还是 View
                            Object[] objects = returnActivityOrView(obj);
                            Activity activity = (Activity) objects[0];
                            View view = (View) objects[1];

                            if (activity != null) {
                                for (int viewId : viewIds) {//遍历要设置监听的控件
                                    View view2 = activity.findViewById(viewId);//获取该控件
                                    Method m = view2.getClass().getMethod(listenerSetter, listenerType);//获取方法
                                    m.invoke(view2, object2);//调用方法
                                }
                            } else if (view != null) {
                                for (int viewId : viewIds) {//遍历要设置监听的控件
                                    View view2 = view.findViewById(viewId);//获取该控件
                                    Method m = view2.getClass().getMethod(listenerSetter, listenerType);//获取方法
                                    m.invoke(view2, object2);//调用方法
                                }
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        /**
         * 注入 Animation 资源字符串
         **/
        private static void initAnimation(Object object) {
            for (Field field : object.getClass().getDeclaredFields()) {
                Annotations.GT_Res.GT_Animation initView = field.getAnnotation(Annotations.GT_Res.GT_Animation.class);
                if (initView != null) {
                    int viewRes = initView.value();
                    try {
                        Context context = GT.context;
                        if (context != null) {
                            Animation animation = AnimationUtils.loadAnimation(context, viewRes);
                            field.setAccessible(true);
                            field.set(object, animation);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * 注入 Dimen 资源字符串
         **/
        private static void initDimen(Object object) {
            Field[] fields = object.getClass().getDeclaredFields();//获致所有成员变更
            for (Field field : fields) {
                Annotations.GT_Res.GT_Dimen initView = field.getAnnotation(Annotations.GT_Res.GT_Dimen.class);
                if (initView != null) {
                    int viewRes = initView.value();
                    try {
                        float dimension = GT.context.getResources().getDimension(viewRes);
                        field.setAccessible(true);
                        field.set(object, dimension);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * 注入 Drawable 资源字符串
         **/
        private static void initDrawable(Object object) {
            Field[] fields = object.getClass().getDeclaredFields();//获致所有成员变更
            for (Field field : fields) {
                Annotations.GT_Res.GT_Drawable initView = field.getAnnotation(Annotations.GT_Res.GT_Drawable.class);
                if (initView != null) {
                    int viewRes = initView.value();
                    try {
                        Drawable drawable = GT.context.getResources().getDrawable(viewRes);
                        field.setAccessible(true);
                        field.set(object, drawable);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * 注入 Color 资源字符串
         **/
        private static void initColor(Object object) {
            Field[] fields = object.getClass().getDeclaredFields();//获致所有成员变更
            for (Field field : fields) {
                Annotations.GT_Res.GT_Color initView = field.getAnnotation(Annotations.GT_Res.GT_Color.class);
                if (initView != null) {
                    int viewRes = initView.value();
                    try {
                        int color = GT.context.getResources().getColor(viewRes);
                        field.setAccessible(true);
                        field.set(object, color);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * 注入 String 资源字符串
         **/
        private static void initString(Object object) {
            Field[] fields = object.getClass().getDeclaredFields();//获致所有成员变更
            for (Field field : fields) {
                Annotations.GT_Res.GT_String initView = field.getAnnotation(Annotations.GT_Res.GT_String.class);
                if (initView != null) {
                    int viewRes = initView.value();
                    try {
                        String string = GT.context.getResources().getString(viewRes);
                        field.setAccessible(true);
                        field.set(object, string);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        /**
         * 注入 Int 资源字符串数组
         **/
        private static void initIntArray(Object object) {
            Field[] fields = object.getClass().getDeclaredFields();//获致所有成员变更
            for (Field field : fields) {
                Annotations.GT_Res.GT_IntArray initView = field.getAnnotation(Annotations.GT_Res.GT_IntArray.class);
                if (initView != null) {
                    int viewRes = initView.value();
                    try {
                        int[] intArray = GT.context.getResources().getIntArray(viewRes);
                        field.setAccessible(true);
                        field.set(object, intArray);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        /**
         * 注入 String 资源字符串数组
         **/
        private static void initStringArray(Object object) {
            Field[] fields = object.getClass().getDeclaredFields();//获致所有成员变更
            for (Field field : fields) {
                Annotations.GT_Res.GT_StringArray initView = field.getAnnotation(Annotations.GT_Res.GT_StringArray.class);
                if (initView != null) {
                    int viewRes = initView.value();
                    try {
                        String[] stringArray = GT.context.getResources().getStringArray(viewRes);
                        field.setAccessible(true);
                        field.set(object, stringArray);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        /**
         * 解析 Layout 资源文件成 View
         **/
        private static void initLayout(Object object) {
            Field[] fields = object.getClass().getDeclaredFields();//获致所有成员变更
            for (Field field : fields) {
                Annotations.GT_Res.GT_Layout initView = field.getAnnotation(Annotations.GT_Res.GT_Layout.class);
                if (initView != null) {
                    int viewRes = initView.value();
                    try {
                        Context context = GT.context;
                        if (context != null) {
                            View viewLayout = LayoutInflater.from(context).inflate(viewRes, null);
                            field.setAccessible(true);
                            field.set(object, viewLayout);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        //================================   下面是 Fragment 的注解内容   ==========================


        /**
         * 注入 ContextView
         *
         * @param activity
         */
        private static void initAnnotationActivity(Object obj) {
            Annotations.GT_AnnotationActivity contentView = obj.getClass().getAnnotation(Annotations.GT_AnnotationActivity.class);//获取该类 ContextView 的注解类
            //如果有注解
            if (contentView != null) {
                if (obj instanceof Activity) {
                    Activity activity = (Activity) obj;
                    int viewId = contentView.value();//获取注解类参数
                    activity.setContentView(viewId);
                }
            }
        }

        /**
         * 注解 Fragment
         *
         * @param object
         * @param mClass
         */
        private static void initAnnotationFragment(Object object) {
            Annotations.GT_AnnotationFragment contentView = object.getClass().getAnnotation(Annotations.GT_AnnotationFragment.class);// 获取该类 ContextView 的注解类
            // 如果有注解
            if (contentView != null) {
                int viewId = contentView.value();// 获取注解类参数
                try {
                    Method method = object.getClass().getMethod("setLayout", int.class);// 获取该方法的信息
                    method.setAccessible(true);// 获取该方法的访问权限
                    method.invoke(object, viewId);// 调用该方法的，并设置该方法参数
                } catch (NoSuchMethodException e) {
//					e.printStackTrace();
                    GT.errs("e:" + e);
                } catch (IllegalAccessException e) {
//					e.printStackTrace();
                    GT.errs("e:" + e);
                } catch (InvocationTargetException e) {
//					e.printStackTrace();
                    GT.errs("e:" + e);
                }
            }
        }

        /**
         * 注解 Fragment
         *
         * @param object
         * @param mClass
         */
        private static void initGT_AnnotationDialogFragment(Object object) {
            Annotations.GT_AnnotationDialogFragment contentView = object.getClass().getAnnotation(Annotations.GT_AnnotationDialogFragment.class);// 获取该类 ContextView 的注解类
            // 如果有注解
            if (contentView != null) {
                int viewId = contentView.value();// 获取注解类参数
                try {
                    Method method = object.getClass().getMethod("setLayout", int.class);// 获取该方法的信息
                    method.setAccessible(true);// 获取该方法的访问权限
                    method.invoke(object, viewId);// 调用该方法的，并设置该方法参数
                } catch (NoSuchMethodException e) {
//					e.printStackTrace();
                    GT.errs("e:" + e);
                } catch (IllegalAccessException e) {
//					e.printStackTrace();
                    GT.errs("e:" + e);
                } catch (InvocationTargetException e) {
//					e.printStackTrace();
                    GT.errs("e:" + e);
                }
            }
        }

        /**
         * 注解 Fragment
         *
         * @param object
         * @param mClass
         */
        private static void initGT_AnnotationAdapter(Object object) {
            Annotations.GT_AnnotationAdapter contentView = object.getClass().getAnnotation(Annotations.GT_AnnotationAdapter.class);// 获取该类 ContextView 的注解类
            // 如果有注解
            if (contentView != null) {
                int viewId = contentView.value();// 获取注解类参数
                try {
                    Method method = object.getClass().getMethod("setLayout", int.class);// 获取该方法的信息
                    method.setAccessible(true);// 获取该方法的访问权限
                    method.invoke(object, viewId);// 调用该方法的，并设置该方法参数
                } catch (NoSuchMethodException e) {
//					e.printStackTrace();
                    GT.errs("e:" + e);
                } catch (IllegalAccessException e) {
//					e.printStackTrace();
                    GT.errs("e:" + e);
                } catch (InvocationTargetException e) {
//					e.printStackTrace();
                    GT.errs("e:" + e);
                }
            }
        }

        /**
         * 注解Fragment构建
         *
         * @param object
         * @param mClass
         */
        private static void initGT_Fragment(Object object) {
            Field[] fields = object.getClass().getDeclaredFields();//获致当前 Activity 所有成员变更
            for (Field field : fields) {
                Object classObject = null;//最终注入的值

                //带参数的
                GT_Fragment.Build initView_GT_Fragments = field.getAnnotation(GT_Fragment.Build.class);

                //GT_Fragment 注解不为null
                if (initView_GT_Fragments != null) {
                    //如果注解的Activity不为null 那就直接构建注解的GT_Fragment并注入
                    Activity activity = (Activity) getActivity();
                    if (activity != null) {
                        //获取注解的值
                        int layoutHome = initView_GT_Fragments.setLayoutHome();
                        int layoutMain = initView_GT_Fragments.setLayoutMain();
                        Class<?> aClass = initView_GT_Fragments.setClass();
                        int startMode = initView_GT_Fragments.setStartMode();

                        //如果没有参数那就直接只构建
                        if (layoutHome == 0 && layoutMain == 0 && aClass == Build.class && startMode == GT_Fragment.MODE_STANDARD) {
                            GT_Fragment.gt_fragment = GT_Fragment.Build((FragmentActivity) activity, activity.getIntent().getExtras());
                        } else {

                            //如果有参数 主界面为 null
                            if (layoutHome == 0) {
                                layoutHome = layoutMain;
                            }

                            if (layoutHome != 0 && aClass != GT_Fragment.Build.class) {
                                GT_Fragment.gt_fragment = GT_Fragment.Build((FragmentActivity) activity, layoutHome, aClass, activity.getIntent().getExtras(), startMode);
                            } else if (layoutHome != 0) {
                                GT_Fragment.gt_fragment = GT_Fragment.Build((FragmentActivity) activity, layoutHome, activity.getIntent().getExtras());
                            } else if (aClass != GT_Fragment.Build.class) {
                                GT_Fragment.gt_fragment = GT_Fragment.Build((FragmentActivity) activity, aClass, activity.getIntent().getExtras(), startMode);
                            } else {
                                GT_Fragment.gt_fragment = GT_Fragment.Build((FragmentActivity) activity, activity.getIntent().getExtras());
                            }

                            //设置
                            GT_Fragment.gt_fragment.setMainFragmentId(layoutMain);
                        }
                    }

                    classObject = GT_Fragment.gt_fragment;
                    //实例注入
                    try {
                        field.setAccessible(true);
                        field.set(object, classObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        }

        /**
         * 注解 GT_SharedPreferences
         *
         * @param object
         * @param mClass
         */
        private static void initGT_SharedPreferences(Object object) {
            Field[] fields = object.getClass().getDeclaredFields();//获致当前 Activity 所有成员变更
            for (Field field : fields) {
                Object classObject = null;//最终注入的值

                //带参数的
                GT_SharedPreferences.Build initView_GT_SharedPreferences = field.getAnnotation(GT_SharedPreferences.Build.class);

                //GT_Fragment 注解不为null
                if (initView_GT_SharedPreferences != null) {
                    //如果注解的Activity不为null 那就直接构建注解的GT_Fragment并注入
                    Activity activity = (Activity) getActivity();
                    GT_SharedPreferences gt_sharedPreferences = null;
                    if (activity != null) {
                        //获取注解的值
                        String spName = initView_GT_SharedPreferences.setSpName();
                        boolean iSCommit = initView_GT_SharedPreferences.setISCommit();
                        gt_sharedPreferences = new GT_SharedPreferences(GT.context, spName, iSCommit);
                    }
                    //实例注入
                    try {
                        field.setAccessible(true);
                        field.set(object, gt_sharedPreferences);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        }

        /**
         * 注解 SerialPortUtils
         *
         * @param object
         * @param mClass
         */
        private static void initSerialPortUtils(Object object) {
            Field[] fields = object.getClass().getDeclaredFields();//获致当前 Activity 所有成员变更
            for (Field field : fields) {
                Object classObject = null;//最终注入的值

                //带参数的
                SerialPortUtils.Build initBuild = field.getAnnotation(SerialPortUtils.Build.class);

                //GT_Fragment 注解不为null
                if (initBuild != null) {
                    //如果注解的Activity不为null 那就直接构建注解的GT_Fragment并注入
                    Activity activity = (Activity) getActivity();
                    SerialPortUtils serialPortUtils = null;
                    if (activity != null) {
                        //获取注解的值
                        String comAll = initBuild.setComAll();
                        int baudRate = initBuild.setBaudRate();
                        boolean iSLog = initBuild.setISLog();
                        serialPortUtils = new SerialPortUtils(GT.context, comAll, baudRate, iSLog);
                    }
                    //实例注入
                    try {
                        field.setAccessible(true);
                        field.set(object, serialPortUtils);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        }

        /**
         * 注解 TCP
         *
         * @param object
         * @param mClass
         */
        private static void initTCP(Object object) {
            Field[] fields = object.getClass().getDeclaredFields();//获致当前 Activity 所有成员变更
            for (Field field : fields) {
                Object classObject = null;//最终注入的值

                //带参数的
                GT_Socket.TCP.Build initBuild = field.getAnnotation(GT_Socket.TCP.Build.class);

                //GT_Fragment 注解不为null
                if (initBuild != null) {
                    //如果注解的Activity不为null 那就直接构建注解的GT_Fragment并注入
                    Activity activity = (Activity) getActivity();
                    GT_Socket.TCP tcp = null;
                    if (activity != null) {
                        //获取注解的值
                        String iP = initBuild.setIP();
                        int port = initBuild.setPort();
                        GT_Socket.isLog = initBuild.isLog();
                        if (iP == null || "null".equals(iP)) {
                            tcp = new GT_Socket.TCP(port);
                        } else {
                            tcp = new GT_Socket.TCP(iP, port);
                        }
                    }
                    //实例注入
                    try {
                        field.setAccessible(true);
                        field.set(object, tcp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        }

        /**
         * 注入 悬浮窗
         *
         * @param object
         * @param mClass
         */
        private static void initAnnotationFloatingWindow(Object object) {
            Annotations.GT_AnnotationFloatingWindow contentView = object.getClass().getAnnotation(Annotations.GT_AnnotationFloatingWindow.class);// 获取该类 ContextView 的注解类
            // 如果有注解
            if (contentView != null) {
                int viewId = contentView.value();// 获取注解类参数
                try {
                    Method method = object.getClass().getMethod("setLayout", int.class);// 获取该方法的信息
                    method.setAccessible(true);// 获取该方法的访问权限
                    method.invoke(object, viewId);// 调用该方法的，并设置该方法参数
                } catch (NoSuchMethodException e) {
                    GT.errs("e:" + e);//e.printStackTrace();
                } catch (IllegalAccessException e) {
                    GT.errs("e:" + e);//e.printStackTrace();
                } catch (InvocationTargetException e) {
                    GT.errs("e:" + e);//e.printStackTrace();
                }
            }
        }

        /**
         * 注入 View 注解
         *
         * @param object
         */
        private static void initAnnotationView(Object object) {
            Annotations.GT_AnnotationView contentView = object.getClass().getAnnotation(Annotations.GT_AnnotationView.class);// 获取该类 ContextView 的注解类
            // 如果有注解
            if (contentView != null) {
                int viewId = contentView.value();// 获取注解类参数
                try {
                    Method method = object.getClass().getMethod("setLayout", int.class);// 获取该方法的信息
                    method.setAccessible(true);// 获取该方法的访问权限
                    method.invoke(object, viewId);// 调用该方法的，并设置该方法参数
                } catch (NoSuchMethodException e) {
                    GT.errs("e:" + e);//e.printStackTrace();
                } catch (IllegalAccessException e) {
                    GT.errs("e:" + e);//e.printStackTrace();
                } catch (InvocationTargetException e) {
                    GT.errs("e:" + e);//e.printStackTrace();
                }
            }
        }


        /**
         * 注入 PopupWindow
         *
         * @param object
         * @param mClass
         */
        private static void initAnnotationPopupWindow(Object object) {
            Annotations.GT_AnnotationPopupWindow contentView = object.getClass().getAnnotation(Annotations.GT_AnnotationPopupWindow.class);// 获取该类 ContextView 的注解类
            // 如果有注解
            if (contentView != null) {
                int viewId = contentView.value();// 获取注解类参数
                try {
                    Method method = object.getClass().getMethod("setLayout", int.class);// 获取该方法的信息
                    method.setAccessible(true);// 获取该方法的访问权限
                    method.invoke(object, viewId);// 调用该方法的，并设置该方法参数
                } catch (NoSuchMethodException e) {
                    GT.errs("e:" + e);//e.printStackTrace();
                } catch (IllegalAccessException e) {
                    GT.errs("e:" + e);//e.printStackTrace();
                } catch (InvocationTargetException e) {
                    GT.errs("e:" + e);//e.printStackTrace();
                }
            }
        }

        /**
         * 单击注解 帮助方法
         *
         * @param field
         * @param object
         * @param functionName
         * @param valueList
         * @param index
         */
        private static void functionValue(Field field, Object object, String functionName, List<Object> valueList, int index) {

            if (functionName.length() != 0 && valueList.size() != 0) {

                Class<?> aClass = object.getClass();
                Method[] methods = aClass.getMethods();

                //获取当前类中所有方法
                for (int i = methods.length - 1; i >= 0; i--) {
                    String name = methods[i].getName();
                    if (name.equals(functionName)) {
                        try {
                            Method method = getAllValueTypeMethod(valueList.get(index), functionName, aClass);
                            method.setAccessible(true);
                            field.setAccessible(true);
                            method.invoke(object, valueList.get(index));
                        } catch (Exception e) {
                            if (LOG.isGtLogTf()) {
                                GT.err(getLineInfo(1), "注解注入失败 ！");
                            }
                            //                            e.printStackTrace();
                        }
                    }
                }
            }

        }

        /**
         * 自获取当前传入数据的类型
         *
         * @param data
         * @param functionName
         * @param aClass
         * @return
         */
        private static Method getAllValueTypeMethod(Object data, String functionName, Class<?> aClass) {

            Method method = null;

            Class<?> aClass1 = data.getClass();
            switch (aClass1.toString()) {
                case "class java.lang.Byte":
                    try {
                        method = aClass.getMethod(functionName, byte.class);
                    } catch (NoSuchMethodException e) {
                        //                    e.printStackTrace();
                        if (LOG.isGtLogTf()) {
                            GT.err(getLineInfo(1), "注解 赋值 byte 类型数据 报错");
                        }
                    }
                    break;
                case "class java.lang.Short":
                    try {
                        method = aClass.getMethod(functionName, short.class);
                    } catch (NoSuchMethodException e) {
                        //                    e.printStackTrace();
                        if (LOG.isGtLogTf()) {
                            GT.err(getLineInfo(1), "注解 赋值 Short 类型数据 报错");
                        }
                    }
                    break;
                case "class java.lang.Integer":
                    try {
                        method = aClass.getMethod(functionName, int.class);
                    } catch (NoSuchMethodException e) {
                        //                    e.printStackTrace();
                        if (LOG.isGtLogTf()) {
                            GT.err(getLineInfo(1), "注解 赋值 int 类型数据 报错");
                        }
                    }
                    break;
                case "class java.lang.Long":
                    try {
                        method = aClass.getMethod(functionName, long.class);
                    } catch (NoSuchMethodException e) {
                        //                    e.printStackTrace();
                        if (LOG.isGtLogTf()) {
                            GT.err(getLineInfo(1), "注解 赋值 Long 类型数据 报错");
                        }
                    }
                    break;
                case "class java.lang.Float":
                    try {
                        method = aClass.getMethod(functionName, float.class);
                    } catch (NoSuchMethodException e) {
                        //                    e.printStackTrace();
                        if (LOG.isGtLogTf()) {
                            GT.err(getLineInfo(1), "注解 赋值 Float 类型数据 报错");
                        }
                    }
                    break;
                case "class java.lang.Double":
                    try {
                        method = aClass.getMethod(functionName, double.class);
                    } catch (NoSuchMethodException e) {
                        //                    e.printStackTrace();
                        if (LOG.isGtLogTf()) {
                            GT.err(getLineInfo(1), "注解 赋值 Double 类型数据 报错");
                        }
                    }
                    break;
                case "class java.lang.Boolean":
                    try {
                        method = aClass.getMethod(functionName, boolean.class);
                    } catch (NoSuchMethodException e) {
                        //                    e.printStackTrace();
                        if (LOG.isGtLogTf()) {
                            GT.err(getLineInfo(1), "注解 赋值 Boolean 类型数据 报错");
                        }
                    }
                    break;
                case "class java.lang.Character":
                    try {
                        method = aClass.getMethod(functionName, char.class);
                    } catch (NoSuchMethodException e) {
                        //                    e.printStackTrace();
                        if (LOG.isGtLogTf()) {
                            GT.err(getLineInfo(1), "注解 赋值 Character 类型数据 报错");
                        }
                    }
                    break;
                case "class java.lang.String":
                    try {
                        method = aClass.getMethod(functionName, String.class);
                    } catch (NoSuchMethodException e) {
                        //                    e.printStackTrace();
                        if (LOG.isGtLogTf()) {
                            GT.err(getLineInfo(1), "注解 赋值 String 类型数据 报错");
                        }
                    }
                    break;
            }
            return method;
        }

        /**
         * 给 listView 赋值
         *
         * @param type
         * @param list
         * @param values
         */
        private static void valueType(String type, List<Object> list, Annotations.GT_Object values, int index) {

            switch (type) {

                /** 单个参数的赋值 **/
                case "byte":
                    list.add(values.valueByte());
                    break;
                case "short":
                    list.add(values.valueShort());
                    break;
                case "int":
                    list.add(values.valueInt());
                    break;
                case "long":
                    list.add(values.valueLong());
                    break;
                case "float":
                    list.add(values.valueFloat());
                    break;
                case "double":
                    list.add(values.valueDouble());
                    break;
                case "boolean":
                    list.add(values.valueBoolean());
                    break;
                case "char":
                    list.add(values.valueChar());
                    break;
                case "String":
                    list.add(values.valueString());
                    break;

                /** 多个参数的赋值 **/
                case "bytes":
                    byte[] bytes = values.valueBytes();
                    for (byte value : bytes) {
                        list.add(value);
                    }
                    break;
                case "shorts":
                    short[] shorts = values.valueShorts();
                    list.add(shorts[index]);
                    break;
                case "ints":
                    int[] ints = values.valueInts();
                    list.add(ints[index]);
                    break;
                case "longs":
                    long[] longs = values.valueLongs();
                    list.add(longs[index]);
                    break;
                case "floats":
                    float[] floats = values.valueFloats();
                    list.add(floats[index]);
                    break;
                case "doubles":
                    double[] doubles = values.valueDoubles();
                    list.add(doubles[index]);
                    break;
                case "booleans":
                    boolean[] booleans = values.valueBooleans();
                    list.add(booleans[index]);
                    break;
                case "chars":
                    char[] chars = values.valueChars();
                    list.add(chars[index]);
                    break;
                case "Strings":
                    String[] strings = values.valueStrings();
                    list.add(strings[index]);
                    break;
            }

        }

        /**
         * 初始化注解帮助类
         */
        private static class MyInvocationHandler implements InvocationHandler {

            private Object object;
            private Map<String, Method> methodMap = new HashMap<>(1);

            public MyInvocationHandler(Object object) {
                this.object = object;
            }

            public void setMethodMap(String name, Method method) {
                this.methodMap.put(name, method);
            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (object != null) {
                    String name = method.getName();
                    method = this.methodMap.get(name);
                    if (method != null) {
                        return method.invoke(object, args);
                    }
                }
                return null;
            }

        }

        public AnnotationAssist() {
        }

        /**
         * 目的：用于判断 当前类是否被 当前的注解注解过
         * 用例：new GT.AnnotationAssist(LogActivity.class, GT.Annotations.Toast.class);
         * 第一个参数：任意对象
         * 第二个参数：被注解的类
         * 再打开 GT 内部的 Log 日志
         */
        public AnnotationAssist(Object obj, Object annotation) {

            /**
             * 获取所有注解
             */
            obj = classToObject(obj);
            Annotation[] annotations = obj.getClass().getAnnotations();
            for (Annotation annotation1 : annotations) if (LOG.GT_LOG_TF) log(annotation1);


            /**
             * 获取声明注解	[Ljava.lang.annotation.Annotation;@28c97a5
             */
            Annotation[] deAnnos = obj.getClass().getDeclaredAnnotations();


            if (annotation != null) {
                //获取被 SubAnnotation 注解过的类
                Annotation subAnnotation = obj.getClass().getAnnotation((Class<Annotation>) annotation);
            }

        }

        /**
         * 返回 Activity 或 View
         *
         * @param obj
         * @return
         */
        public static Object[] returnActivityOrView(Object obj) {

            Activity activity = null;
            View view = null;
            if (obj instanceof Activity) {
                activity = (Activity) obj;
            } else if (obj instanceof Fragment) {
                Fragment fragment = (Fragment) obj;
                view = fragment.getView();
            } else if (obj instanceof DialogFragment) {
                DialogFragment fragment = (DialogFragment) obj;
                view = fragment.getView();
            } else if (obj instanceof View) {
                view = (View) obj;
            } else if (obj instanceof GT_FloatingWindow.BaseFloatingWindow) {
                GT_FloatingWindow.BaseFloatingWindow fwb = (GT_FloatingWindow.BaseFloatingWindow) obj;
                view = fwb.getView();
            } else if (obj instanceof GT_View.BaseView) {
                GT_View.BaseView baseView = (GT_View.BaseView) obj;
                view = baseView.view;
            } else if (obj instanceof Adapters.BaseAdapter) {
                Adapters.BaseAdapter baseView = (Adapters.BaseAdapter) obj;
                view = baseView.itemView;
            } else if (obj instanceof GT_PopupWindow.BasePopupWindow) {
                GT_PopupWindow.BasePopupWindow baseView = (GT_PopupWindow.BasePopupWindow) obj;
                view = baseView.view;
            } else if (obj instanceof RecyclerView.ViewHolder) {
                RecyclerView.ViewHolder baseView = (RecyclerView.ViewHolder) obj;
                view = baseView.itemView;
            } else {
                GT.err("暂不支持该组件注解：" + obj);
            }

            Object[] objArray = new Object[2];
            objArray[0] = activity;
            objArray[1] = view;
            return objArray;
        }

        /**
         * 解析文件 反射 Object
         *
         * @param obj
         * @return
         */
        public Object classToObject(Object obj) {
            String[] strs = obj.toString().split(" ");
            String str = "";
            if (strs.length == 2) {
                str = strs[1];
            } else {
                str = obj.toString();
            }
            Class<?> clazz = getClass();
            try {
                clazz = Class.forName(str);
            } catch (ClassNotFoundException e) {
                GT.err("e:" + e);
                e.printStackTrace();
            }
            try {
                obj = clazz.newInstance();
            } catch (IllegalAccessException e) {
                GT.err("e:" + e);
                e.printStackTrace();
            } catch (InstantiationException e) {
                GT.err("e:" + e);
                e.printStackTrace();
            }
            return obj;
        }


        /**
         * 获取类的泛类型
         *
         * @param obj 需要获取的类
         * @return
         */
        public static Type[] getClassGenericType(Object obj) {
            Type[] actualTypeArguments = null;
            try {
                Class clazz = obj.getClass();
                Type type = clazz.getGenericSuperclass();
                ParameterizedType p = (ParameterizedType) type;
                actualTypeArguments = p.getActualTypeArguments();
            } catch (Exception e) {
//                GT.errs("e:" + e);
            }
            return actualTypeArguments;
        }

        /**
         * 利用反射给类中方法进行赋值
         *
         * @param setObj            被设置操作的类
         * @param setMethodName     被调用的方法名
         * @param returnType        该方法返回值的类型,若没有返回值填入null即可
         * @param setValueTypeClass 设置值的类型
         * @param setValue          设置的值
         * @param <T>               自适应返回值类型
         * @return 如果返回值类型为null则默认返回 true:设置值成功 false:设置值失败
         */
        public static <T> T setReflectMethodValue(Object setObj, String setMethodName, Class<T> returnType, Class<?> setValueTypeClass, Object... setValue) {
            Method m = null;
            try {
                m = setObj.getClass().getMethod(setMethodName, setValueTypeClass);
                m.setAccessible(true);
                T invoke = (T) m.invoke(setObj, setValue);//调用方法
                if (returnType != null) {
                    return invoke;
                } else {
                    return (T) "true";
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                GT.err("e:" + e);
                e.printStackTrace();
            }
            if (returnType != null) {
                return null;
            } else {
                return (T) "false";
            }
        }


        /**
         * 获取反射方法的返回值
         *
         * @param setObj        被设置操作的类
         * @param setMethodName 被调用的方法名
         * @param returnType    该方法返回值的类型
         * @param <T>           自适应返回值类型
         * @return
         */
        public static <T> T runMethod(Object setObj, String setMethodName, Class<T> returnType) {
            if (returnType == null) return null;
            Method m = null;
            try {
                m = setObj.getClass().getMethod(setMethodName);
                T invoke = (T) m.invoke(setObj);//调用方法
                return invoke;
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                GT.err("e:" + e);
                e.printStackTrace();
            }
            return null;
        }


        /**
         * 获取反射方法的返回值
         *
         * @param setObj        被设置操作的类
         * @param setMethodName 被调用的方法名
         * @param returnType    该方法返回值的类型
         * @param <T>           自适应返回值类型
         * @return
         */
        public static <T> T getReflectMethodValue(Object setObj, String setMethodName, Class<T> returnType) {
            if (returnType == null) return null;
            Method m = null;
            try {
                m = setObj.getClass().getMethod(setMethodName);
                T invoke = (T) m.invoke(setObj);//调用方法
                return invoke;
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                GT.err("e:" + e);
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 获取反射变量的值
         *
         * @param classz          反射的类(可接受类型 class、String(类包名)、实体类)
         * @param valueType       反射变量的类型
         * @param returnValueName 反射变量的变量名
         * @param <T>
         * @return
         */
        public static <T> T getReflectVariateValue(Object classz, Class<T> valueType, String returnValueName) {
            T t = null;
            Class aClass = null;
            if (classz instanceof Class) {//class 类
                aClass = (Class) classz;
            } else if (classz instanceof String) {//类包名
                aClass = stringToClass(classz.toString());
            } else {//实体类
                aClass = classz.getClass();
            }
            if (aClass == null) return null;
            Field[] f = aClass.getDeclaredFields();
            for (Field field : f) {
                field.setAccessible(true);//设置可以读取 private 值
                try {
                    if (field.getName().equals(returnValueName)) {//如果是自己想要的值，那就直接返回
                        t = (T) field.get(aClass.newInstance());
                        return t;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        /**
         * 利用反射给变量设置值
         *
         * @param obj       反射的类
         * @param valueName 赋值的变量名
         * @param value     值
         * @return 返回反射后的对象
         */
        public static <T> T setReflectVariateValue(T obj, String valueName, Object value) {
            Class aClass = obj.getClass();
            try {
                Field name = aClass.getDeclaredField(valueName);
                name.setAccessible(true);
                name.set(obj, value);
                return obj;
            } catch (NoSuchFieldException | IllegalAccessException e) {
                GT.err("e:" + e);
                e.printStackTrace();
            }
            return obj;
        }

        /**
         * 反射该类所有的变量值 (可接受类型 class、String(类包名)、实体类)
         *
         * @param classz
         * @return
         */
        public static Map<String, Object> getReflectVariateValueAll(Object classz) {
            Map<String, Object> map = new HashMap<>();
            Class aClass = null;
            if (classz instanceof Class) {//class 类
                aClass = (Class) classz;
            } else if (classz instanceof String) {//类包名
                aClass = stringToClass(classz.toString());
            } else {//实体类
                aClass = classz.getClass();
            }
            if (aClass == null) return null;
            Field[] f = aClass.getDeclaredFields();
            for (Field field : f) {
                field.setAccessible(true);//设置可以读取 private 值
                try {
                    map.put(field.getName(), field.get(aClass.newInstance()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
            return map;
        }

        /**
         * 字符串转Class
         *
         * @param className 包名 如："com.gsls.gt.Person"
         * @param T
         * @return
         */
        public static Class<?> stringToClass(String className) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

    }

//========================================== 线程 ==============================================

    //Thread 更新UI线程
    public static class Thread {

        private static final Handler UTIL_HANDLER = new Handler(Looper.getMainLooper());

        /**
         * 更新 主线程 UI
         *
         * @param runnable
         */
        public static void runAndroid(Runnable runnable) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                runnable.run();
            } else {
                Thread.UTIL_HANDLER.post(runnable);
            }
        }

        /**
         * 更新 主线程 UI
         *
         * @param runnable
         */
        public static void runAndroidAct(Runnable runnable) {
            if (getActivity() != null) {
                Activity activity = (Activity) getActivity();
                activity.runOnUiThread(runnable);
            } else {
                log(getLineInfo(), "当前未绑定 Activity 无法使用该方法创建 UI 线程");
            }
        }

        /**
         * @param runnable  run
         * @param sleepTime 延时时间
         * @主线程
         */
        public static void runAndroid(Runnable runnable, int delayMillis) {
            Thread.UTIL_HANDLER.postDelayed(runnable, delayMillis);
        }

        /**
         * 更新 主线程 UI
         *
         * @param runnable
         */
        public static void runAndroid(View view, Runnable runnable) {
            view.post(runnable);
        }

        /**
         * 更新 主线程 UI
         *
         * @param runnable
         */
        public static void runAndroid(Activity activity, Runnable runnable) {
            activity.runOnUiThread(runnable);
        }

        /**
         * 开启 Java 子线程
         *
         * @param runnable
         */
        public static void runJava(Runnable runnable) {
            //注意：如果你在引用这个线程里引用了主线程的 对象 请在 run 方法中 加入 Looper.prepare(); 否则会在开始或结束Activity活动时 报异常
            new java.lang.Thread(runnable).start();
        }

        /**
         * 睡眠
         *
         * @param millis
         */
        public static void sleep(long millis) {
            try {
                java.lang.Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        /**
         * 循环 计时器
         *
         * @param delay     多少秒过后进行 开始计时器
         * @param period    每隔多少毫秒进行一次计时
         * @param timerTask 匿名类 new TimerTask 即可 然后在 run 方法中写耗时操作
         * @return
         */
        public static Timer Timer(long delay, long period, TimerTask timerTask) {
            /**
             * 参数 delay : 待多少秒过后进行 开始计时器
             * 参数 period : 每隔多少毫秒进行一次计时
             * 参数 timerTask : 匿名类 new TimerTask 即可 然后在 run 方法中写耗时操作
             */
            Timer timer = new Timer();
            timer.schedule(timerTask, delay, period);
            return timer;
        }

        /**
         * 简易 循环计时器
         *
         * @param timerTask
         * @return
         */
        public static Timer Timer(TimerTask timerTask) {
            /**
             * 参数 timerTask : 匿名类 new TimerTask 即可 然后在 run 方法中写耗时操作
             */
            Timer timer = new Timer();
            timer.schedule(timerTask, 0, 1);
            return timer;
        }

        /**
         * Timer 整体封装
         */
        public static class GT_Timer {

            private Timer timer;
            private TimerTask timerTask;

            /**
             * 初始化
             *
             * @param timerTask
             */
            public GT_Timer(TimerTask timerTask) {
                this.timerTask = timerTask;
                timer = new Timer();
            }

            /**
             * 开启循环计时
             *
             * @param delay
             * @param period
             * @return
             */
            public GT_Timer start(long delay, long period) {
                if (timer != null && timerTask != null) {
                    timer.schedule(timerTask, delay, period);
                }
                return this;
            }

            /**
             * 启动
             *
             * @return
             */
            public GT_Timer start() {
                if (timer != null && timerTask != null) {
                    timer.schedule(timerTask, 0, 1);
                }
                return this;
            }

            /**
             * 结束循环计时
             */
            public void close() {
                if (timer != null) {
                    timer.cancel();//停止计时
                    timer = null;
                    timerTask = null;
                }
            }


        }


        /**
         * AsyncTask 封装
         *
         * @param gtAsyncTask
         * @return
         */
        public static GTAsyncTask asyncTask(GTAsyncTask gtAsyncTask) {
            return gtAsyncTask;
        }

        /**
         * 自动开启的 AsyncTask 封装
         *
         * @param start
         * @param gtAsyncTask
         * @return
         */
        public static GTAsyncTask asyncTask(boolean start, GTAsyncTask gtAsyncTask) {
            if (start) gtAsyncTask.execute();//如果设置为
            return gtAsyncTask;
        }

        /**
         * AsyncTask 整体封装
         */
        public static class AsyncTask {
            /**
             * 使用实列:    GT.Thread.AsyncTask asyncTask = new GT.Thread.AsyncTask(new GT.Thread.GTAsyncTask(){....}
             */
            private GTAsyncTask gtAsyncTask;    //定义 GTAsyncTask

            /**
             * 获取 GTAsyncTask 对象
             *
             * @return
             */
            public GTAsyncTask getGtAsyncTask() {
                return gtAsyncTask;
            }

            /**
             * 初始化 GTAsyncTask
             *
             * @param gtAsyncTask
             */
            public AsyncTask(GTAsyncTask gtAsyncTask) {
                this.gtAsyncTask = gtAsyncTask;
            }

            //启动 GTAsyncTask
            public void start() {
                if (gtAsyncTask != null) {
                    try {
                        gtAsyncTask.execute();
                    } catch (IllegalStateException e) {
                        if (LOG.GT_LOG_TF) {
                            GT.log(getLineInfo(1), "无法执行任务:任务已在运行。");
                        }
                    }
                }
            }

            /**
             * 关闭 GTAsyncTask 并释放内存
             */
            public void close() {
                if (gtAsyncTask != null) {
                    gtAsyncTask.cancel(true);//强制关闭
                    gtAsyncTask = null;//置空
                }
            }

        }

        /**
         * 定义继承后要实现的类
         */
        public abstract static class GTAsyncTask extends android.os.AsyncTask<Object, Object, Object> {

            /**
             * 用法：继承该类并重写，或者利用 AsyncTask 封装类进行便捷操作
             * onPreExecute 用于初始化
             * onProgressUpdate 用于更新 UI 界面
             * doInBackground 用于进行耗时操作如网络请求、、
             * onPostExecute 用于反馈耗时完成、或者进行资源释放
             */

            @Override
            protected void onPreExecute() {
                //初始化
                super.onPreExecute();
            }

            @Override
            protected void onProgressUpdate(Object... values) {
                //更新 UI
                super.onProgressUpdate(values);
            }

            @Override
            protected Object doInBackground(Object... objects) {
                //阻塞操作 该方法并不在 主线程中调用，不能用于更新 UI 操作
                return null;
            }

            @Override
            protected void onPostExecute(Object object) {
                //在主线程中调用该方法，可以对 UI 进行修改
                super.onPostExecute(object);

            }
        }


    }

//=============================================== 黑科技 ===================================================

    /**
     * 黑魔法类
     */
    public static class DarknessMagic {
        //空白...无障碍..

        //无障碍服务
        public abstract static class AccessibilityServiceBase extends AccessibilityService {

            /**
             * 无障碍服务须知：
             * <p>
             * 第一步：
             * <p>
             * 注意:需要在xml 目录中创建 accessible_service_describe.xml
             * 内容如下：
             * <p>
             * <p>
             * <?xml version="1.0" encoding="utf-8"?>
             * <accessibility-service xmlns:android="http://schemas.android.com/apk/res/android"
             * xmlns:tools="http://schemas.android.com/tools"
             * android:accessibilityEventTypes="typeAllMask"
             * android:accessibilityFeedbackType="feedbackGeneric"
             * android:accessibilityFlags="flagReportViewIds|flagRetrieveInteractiveWindows"
             * android:canPerformGestures="true"
             * android:canRetrieveWindowContent="true"
             * android:description="@string/AccessibleServiceDescribe"
             * android:notificationTimeout="100"
             * tools:ignore="UnusedAttribute" />
             * <p>
             * <!--
             * android:description ：辅助功能描述，描述该辅助功能用来干嘛的
             * android:packageNames ：指定辅助功能监听的包名，不指定表示监听所有应用
             * android:accessibilityEventTypes：辅助功能处理事件类型，一般配置为 typeAllMask 表示接收所有事件
             * android:accessibilityFlags：辅助功能查找截点方式，一般配置为 flagDefault 默认方式。
             * android:accessibilityFeedbackType：操作相应按钮以后辅助功能给用户的反馈类型，包括声音，震动等。
             * android:notificationTimeout：相应时间设置
             * android:canRetrieveWindowContent：是否允许辅助功能获得窗口的节点信息，为了能正常实用辅助功能，请务必保持该项配置为true
             * <p>
             * android:packageNames="com.tencent.mm,com.android.systemui"
             * <p>
             * -->
             * <p>
             * <p>
             * 第二步：在 AndroidManifest.xml 文件中注册该服务
             * <p>
             * <application
             * ...
             * <p>
             * <!-- 无障碍服务 -->
             * <service
             * android:name="创建的无障碍服务类"
             * android:enabled="true"
             * android:exported="true"
             * android:permission="android.permission.BIND_ACCESSIBILITY_SERVICE">
             * <intent-filter>
             * <action android:name="android.accessibilityservice.AccessibilityService" />
             * </intent-filter>
             * <meta-data
             * android:name="android.accessibilityservice"
             * android:resource="@xml/accessible_service_describe" />
             * </service>
             * <p>
             * ...
             * />
             * <p>
             * <p>
             * 第三步：申请无障碍服务并开启
             * <p>
             * //申请无障碍服务
             * if (!DemoService.isStart()) {
             * try {
             * this.startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
             * } catch (Exception e) {
             * this.startActivity(new Intent(Settings.ACTION_SETTINGS));
             * e.printStackTrace();
             * }
             * }
             */

            public static AccessibilityServiceBase mService;
            public static String RUN_ACTIVITY = "";//运行动作
            public static boolean TF_LOG = false;//是否打开日
            public static boolean RUN_STATE = false;//运行状态// 志

            //初始化
            @Override
            protected void onServiceConnected() {
                super.onServiceConnected();
                if (TF_LOG)
                    GT.log("无障碍服务——连接中");
                mService = this;
                RUN_STATE = true;


            }

            /**
             * 初始化View,会不停的循环此方法
             *
             * @param action
             * @param packageName
             * @param rootInfo
             * @param event
             */
            protected abstract void initView(int action, String packageName, AccessibilityNodeInfo rootInfo, AccessibilityEvent event);

            /**
             * 业务逻辑,当有正确的动作后才会相应此方法
             *
             * @param action
             * @param packageName
             * @param rootInfo
             * @param event
             * @param run_Activity 接受到信息后请立刻清空消息
             */
            protected void loadData(int action, String packageName, AccessibilityNodeInfo rootInfo, AccessibilityEvent event, String run_Activity) {

            }

            //实现辅助功能
            @Override
            public void onAccessibilityEvent(AccessibilityEvent event) {
                if (TF_LOG)
                    GT.log("无障碍服务——监听中");

                AccessibilityNodeInfo rootInfo = getRootInActiveWindow();
                if (event == null || rootInfo == null || !RUN_STATE) return;

                if (TF_LOG) {
                    GT.log(event.getAction());
                    GT.log(event.getPackageName());
                    GT.log(rootInfo);
                }

                int action = event.getAction();
                CharSequence packageNameCS = event.getPackageName();
                if (packageNameCS == null) return;

                String packageName = String.valueOf(packageNameCS);

                initView(action, packageName, rootInfo, event);

                if (RUN_ACTIVITY == null || RUN_ACTIVITY.length() == 0)
                    loadData(event.getAction(), packageName, rootInfo, event, RUN_ACTIVITY);

            }

            @Override
            public void onInterrupt() {
                if (TF_LOG)
                    GT.log("无障碍服务——停止中");
                mService = null;
                RUN_STATE = false;
            }

            @Override
            public void onDestroy() {
                super.onDestroy();
                if (TF_LOG) {
                    GT.log("无障碍服务——已关闭");
                }
                mService = null;
                RUN_STATE = false;
            }

            /**
             * 辅助功能是否启动
             */
            public static boolean isStart() {
                return mService != null;
            }

            public static void finish() {
                RUN_STATE = false;
            }

            public static void start() {
                RUN_STATE = true;
            }


            protected void MyGesture() {//仿滑动
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Path path = new Path();
                    path.moveTo(1000, 1000);//滑动起点
                    path.lineTo(2000, 1000);//滑动终点
                    GestureDescription.Builder builder = new GestureDescription.Builder();
                    GestureDescription description = builder.addStroke(new GestureDescription.StrokeDescription(path, 100L, 100L)).build();
                    //100L 第一个是开始的时间，第二个是持续时间
                    dispatchGesture(description, new AccessibilityServiceUtils.MyCallBack(), null);
                }
            }


            /**
             * 立即发送移动的手势
             * 注意7.0以上的手机才有此方法，请确保运行在7.0手机上
             *
             * @param path  移动路径
             * @param mills 持续总时间
             */
            @RequiresApi(24)
            protected void dispatchGestureMove(Path path, long mills) {
                dispatchGesture(new GestureDescription.Builder().addStroke(new GestureDescription.StrokeDescription
                        (path, 0, mills)).build(), null, null);
            }

            /**
             * 点击指定位置
             * 注意7.0以上的手机才有此方法，请确保运行在7.0手机上
             */
            @RequiresApi(24)
            protected void dispatchGestureClick(int x, int y) {
                Path path = new Path();
                path.moveTo(x - 1, y - 1);
                path.lineTo(x + 1, y + 1);
                dispatchGesture(new GestureDescription.Builder().addStroke(new GestureDescription.StrokeDescription
                        (path, 0, 100)).build(), null, null);
            }

            /**
             * 长按指定位置
             * 注意7.0以上的手机才有此方法，请确保运行在7.0手机上
             */
            @RequiresApi(24)
            protected void dispatchGestureLongClick(int x, int y) {
                Path path = new Path();
                path.moveTo(x - 1, y - 1);
                path.lineTo(x, y - 1);
                path.lineTo(x, y);
                path.lineTo(x - 1, y);
                dispatchGesture(new GestureDescription.Builder().addStroke(new GestureDescription.StrokeDescription
                        (path, 0, 1000)).build(), null, null);
            }


            /*  int childCount = rootInfo.getChildCount();
        GT.log("childCount:" + childCount);

        for (int a = 0; a < childCount; a++) {
            AccessibilityNodeInfo child = rootInfo.getChild(a);
            GT.log("child[" + a + "]：" + child);
            if (child == null) continue;

            int childCount1 = child.getChildCount();
            GT.log("childCount1:" + childCount1);

            for (int b = 0; b < childCount1; b++) {
                AccessibilityNodeInfo child1 = child.getChild(b);
                GT.log("child1[" + b + "]：" + child1);
                if (child1 == null) continue;

                if (child1.toString().contains("使用 WLAN")) {
                    GT.log("进入点击 WLAN 流程");
                    RUN_ACTIVITY = "";
                    RUN_STATE = true;
                    clickView(child1);//单击WIFI开关
                    RUN_STATE = false;
                }

                int childCount2 = child1.getChildCount();
                GT.log("childCount2:" + childCount2);

                for (int c = 0; c < childCount2; c++) {
                    AccessibilityNodeInfo child2 = child1.getChild(c);
                    GT.log("child2[" + b + "]：" + child2);
                    if (child2 == null) continue;


                    child2.recycle();
                }


                child1.recycle();//回收资源
                child1 = null;
            }


            child.recycle();//回收资源
            child = null;
        }

        rootInfo.recycle();
        rootInfo = null;


        switch (RUN_ACTIVITY) {
            case "打开WIFI":
                RUN_ACTIVITY = "";//清空动作


                break;

            default:
                MyGesture();//滑动屏幕
                break;
        }*/


        }

        public abstract static class AccessibilityServiceUtils<T> {

            /**
             * 是包含还必须相等;
             */
            protected final boolean mIsEquals;
            protected final T mCheckData;

            public AccessibilityServiceUtils(@NonNull T checkData, boolean isEquals) {
                mCheckData = checkData;
                mIsEquals = isEquals;
            }

            public abstract boolean checkOk(AccessibilityNodeInfo thisInfo);

            /**
             * 找id，就是findAccessibilityNodeInfosByViewId方法
             * 和找text一样效率最高，如果能找到，尽量使用这个
             */
            public static class IdTF extends AccessibilityServiceUtils<String> implements IdTextTF {
                public IdTF(@NonNull String idFullName) {
                    super(idFullName, true);
                }

                @Override
                public boolean checkOk(AccessibilityNodeInfo thisInfo) {
                    return true;//此处不需要实现
                }

                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)

                @Override
                public AccessibilityNodeInfo findFirst(AccessibilityNodeInfo root) {
                    List<AccessibilityNodeInfo> list = root.findAccessibilityNodeInfosByViewId(mCheckData);
                    if (isEmptyArray(list)) {
                        return null;
                    }
                    for (int i = 1; i < list.size(); i++) {//其他的均回收
                        list.get(i).recycle();
                    }
                    return list.get(0);
                }

                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)

                @Override
                public List<AccessibilityNodeInfo> findAll(AccessibilityNodeInfo root) {
                    return root.findAccessibilityNodeInfosByViewId(mCheckData);
                }
            }

            /**
             * 普通text，就是findAccessibilityNodeInfosByText方法
             * 和找id一样效率最高，如果能找到，尽量使用这个
             */
            public static class TextTF extends AccessibilityServiceUtils<String> implements IdTextTF {
                public TextTF(@NonNull String text, boolean isEquals) {
                    super(text, isEquals);
                }

                @Override
                public boolean checkOk(AccessibilityNodeInfo thisInfo) {
                    return true;//此处不需要实现
                }


                @Override
                public AccessibilityNodeInfo findFirst(AccessibilityNodeInfo root) {
                    List<AccessibilityNodeInfo> list = root.findAccessibilityNodeInfosByText(mCheckData);
                    if (isEmptyArray(list)) {
                        return null;
                    }
                    if (mIsEquals) {
                        AccessibilityNodeInfo returnInfo = null;
                        for (AccessibilityNodeInfo info : list) {
                            if (returnInfo == null && info.getText() != null && mCheckData.equals(info.getText().toString())) {
                                returnInfo = info;
                            } else {
                                info.recycle();
                            }
                        }
                        return returnInfo;
                    } else {
                        return list.get(0);
                    }
                }


                @Override
                public List<AccessibilityNodeInfo> findAll(AccessibilityNodeInfo root) {
                    List<AccessibilityNodeInfo> list = root.findAccessibilityNodeInfosByText(mCheckData);
                    if (isEmptyArray(list)) {
                        return null;
                    }
                    if (mIsEquals) {
                        ArrayList<AccessibilityNodeInfo> listNew = new ArrayList<>();
                        for (AccessibilityNodeInfo info : list) {
                            if (info.getText() != null && mCheckData.equals(info.getText().toString())) {
                                listNew.add(info);
                            } else {
                                info.recycle();
                            }
                        }
                        return listNew;
                    } else {
                        return list;
                    }
                }
            }

            /**
             * 类似uc浏览器，有text值但无法直接根据text来找到
             */
            public static class WebTextTF extends AccessibilityServiceUtils<String> {
                public WebTextTF(@NonNull String checkString, boolean isEquals) {
                    super(checkString, isEquals);
                }

                @Override
                public boolean checkOk(AccessibilityNodeInfo thisInfo) {
                    CharSequence text = thisInfo.getText();
                    if (mIsEquals) {
                        return text != null && text.toString().equals(mCheckData);
                    } else {
                        return text != null && text.toString().contains(mCheckData);
                    }
                }
            }

            /**
             * 找ContentDescription字段
             */
            public static class ContentDescriptionTF extends AccessibilityServiceUtils<String> {
                public ContentDescriptionTF(@NonNull String checkString, boolean isEquals) {
                    super(checkString, isEquals);
                }

                @Override
                public boolean checkOk(AccessibilityNodeInfo thisInfo) {
                    CharSequence text = thisInfo.getContentDescription();
                    if (mIsEquals) {
                        return text != null && text.toString().equals(mCheckData);
                    } else {
                        return text != null && text.toString().contains(mCheckData);
                    }
                }
            }

            /**
             * 找ClassName匹配
             */
            public static class ClassNameTF extends AccessibilityServiceUtils<String> {
                public ClassNameTF(@NonNull String checkString, boolean isEquals) {
                    super(checkString, isEquals);
                }

                @Override
                public boolean checkOk(AccessibilityNodeInfo thisInfo) {
                    if (mIsEquals) {
                        return thisInfo.getClassName().toString().equals(mCheckData);
                    } else {
                        return thisInfo.getClassName().toString().contains(mCheckData);
                    }
                }
            }

            /**
             * 在某个区域内的控件
             */
            public static class RectTF extends AccessibilityServiceUtils<Rect> {
                public RectTF(@NonNull Rect rect) {
                    super(rect, true);
                }

                @Override
                public boolean checkOk(AccessibilityNodeInfo thisInfo) {
                    thisInfo.getBoundsInScreen(mRecycleRect);
                    return mCheckData.contains(mRecycleRect);
                }
            }

            public interface IdTextTF {

                AccessibilityNodeInfo findFirst(AccessibilityNodeInfo root);


                List<AccessibilityNodeInfo> findAll(AccessibilityNodeInfo root);
            }

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // 创建方法
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            public static Rect mRecycleRect = new Rect();

            public static final String ST_VIEW = "android.view.View",
                    ST_TEXTVIEW = "android.widget.TextView",
                    ST_IMAGEVIEW = "android.widget.ImageView",
                    ST_BUTTON = "android.widget.Button",
                    ST_IMAGEBUTTON = "android.widget.ImageButton",
                    ST_EDITTEXT = "android.widget.EditText",
                    ST_LISTVIEW = "android.widget.ListView",
                    ST_LINEARLAYOUT = "android.widget.LinearLayout",
                    ST_VIEWGROUP = "android.view.ViewGroup",
                    ST_SYSTEMUI = "com.android.systemui";

            /**
             * 找id，就是findAccessibilityNodeInfosByViewId方法
             * 和找text一样效率最高，如果能找到，尽量使用这个
             *
             * @param pageName 被查找项目的包名:com.android.xxx
             * @param idName   id值:tv_main
             */
            public static AccessibilityServiceUtils newId(String pageName, String idName) {
                return newId(pageName + ":id/" + idName);
            }

            /**
             * @param idfullName id全称:com.android.xxx:id/tv_main
             */
            public static AccessibilityServiceUtils newId(@NonNull String idfullName) {
                return new IdTF(idfullName);
            }

            /**
             * 普通text，就是findAccessibilityNodeInfosByText方法
             * 和找id一样效率最高，如果能找到，尽量使用这个
             */
            public static AccessibilityServiceUtils newText(@NonNull String text, boolean isEquals) {
                return new TextTF(text, isEquals);
            }

            /**
             * 类似uc浏览器，有text值但无法直接根据text来找到
             */
            public static AccessibilityServiceUtils newWebText(@NonNull String webText, boolean isEquals) {
                return new WebTextTF(webText, isEquals);
            }

            /**
             * 找ContentDescription字段
             */
            public static AccessibilityServiceUtils newContentDescription(@NonNull String cd, boolean isEquals) {
                return new ContentDescriptionTF(cd, isEquals);
            }

            /**
             * 找ClassName匹配
             */
            public static AccessibilityServiceUtils newClassName(@NonNull String className) {
                return new ClassNameTF(className, true);
            }

            public static AccessibilityServiceUtils newClassName(@NonNull String className, boolean isEquals) {
                return new ClassNameTF(className, isEquals);
            }

            /**
             * 在某个区域内的控件
             */
            public static AccessibilityServiceUtils newRect(@NonNull Rect rect) {
                return new RectTF(rect);
            }


            //模拟手势的监听
            @SuppressLint("NewApi")
            public static class MyCallBack extends AccessibilityService.GestureResultCallback {

                @Override
                public void onCompleted(GestureDescription gestureDescription) {
                    super.onCompleted(gestureDescription);

                }

                @Override
                public void onCancelled(GestureDescription gestureDescription) {
                    super.onCancelled(gestureDescription);

                }
            }


            /**
             * 点击该控件
             *
             * @return true表示点击成功
             */
            public static boolean clickView(AccessibilityNodeInfo nodeInfo) {
                if (nodeInfo != null) {
                    if (nodeInfo.isClickable()) {
                        nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        return true;
                    } else {
                        AccessibilityNodeInfo parent = nodeInfo.getParent();
                        if (parent != null) {
                            boolean b = clickView(parent);
                            parent.recycle();
                            if (b) return true;
                        }
                    }
                }
                return false;
            }

            /**
             * 查找第一个匹配的控件
             *
             * @param tfs 匹配条件，多个AbstractTF是&&的关系，如：
             *            AbstractTF.newContentDescription("表情", true),AbstractTF.newClassName(AbstractTF.ST_IMAGEVIEW)
             *            表示描述内容是'表情'并且是imageview的控件
             */

            public AccessibilityNodeInfo findFirst(AccessibilityServiceUtils[] tfs, AccessibilityNodeInfo rootInfo) {
                if (tfs.length == 0) throw new InvalidParameterException("AbstractTF不允许传空");

                if (rootInfo == null) return null;

                int idTextTFCount = 0, idTextIndex = 0;
                for (int i = 0; i < tfs.length; i++) {
                    if (tfs[i] instanceof AccessibilityServiceUtils.IdTextTF) {
                        idTextTFCount++;
                        idTextIndex = i;
                    }
                }
                switch (idTextTFCount) {
                    case 0://id或text数量为0，直接循环查找
                        AccessibilityNodeInfo returnInfo = findFirstRecursive(rootInfo, tfs);
                        rootInfo.recycle();
                        return returnInfo;
                    case 1://id或text数量为1，先查出对应的id或text，然后再查其他条件
                        if (tfs.length == 1) {
                            AccessibilityNodeInfo returnInfo2 = ((IdTextTF) tfs[idTextIndex]).findFirst(rootInfo);
                            rootInfo.recycle();
                            return returnInfo2;
                        } else {
                            List<AccessibilityNodeInfo> listIdText = ((IdTextTF) tfs[idTextIndex]).findAll(rootInfo);
                            if (isEmptyArray(listIdText)) {
                                break;
                            }
                            AccessibilityNodeInfo returnInfo3 = null;
                            for (AccessibilityNodeInfo info : listIdText) {//遍历找到匹配的
                                if (returnInfo3 == null) {
                                    boolean isOk = true;
                                    for (AccessibilityServiceUtils tf : tfs) {
                                        if (!tf.checkOk(info)) {
                                            isOk = false;
                                            break;
                                        }
                                    }
                                    if (isOk) {
                                        returnInfo3 = info;
                                    } else {
                                        info.recycle();
                                    }
                                } else {
                                    info.recycle();
                                }
                            }
                            rootInfo.recycle();
                            return returnInfo3;
                        }
                    default:
                        throw new RuntimeException("由于时间有限，并且多了也没什么用，所以IdTF和TextTF只能有一个");
                }
                rootInfo.recycle();
                return null;
            }

            /**
             * @param tfs 由于是递归循环，会忽略IdTF和TextTF
             */
            public static AccessibilityNodeInfo findFirstRecursive(AccessibilityNodeInfo parent, @NonNull AccessibilityServiceUtils... tfs) {
                if (parent == null) return null;
                if (tfs.length == 0) throw new InvalidParameterException("AbstractTF不允许传空");

                for (int i = 0; i < parent.getChildCount(); i++) {
                    AccessibilityNodeInfo child = parent.getChild(i);
                    if (child == null) continue;
                    boolean isOk = true;
                    for (AccessibilityServiceUtils tf : tfs) {
                        if (!tf.checkOk(child)) {
                            isOk = false;
                            break;
                        }
                    }
                    if (isOk) {
                        return child;
                    } else {
                        AccessibilityNodeInfo childChild = findFirstRecursive(child, tfs);
                        child.recycle();
                        if (childChild != null) {
                            return childChild;
                        }
                    }
                }
                return null;
            }

            /**
             * 查找全部匹配的控件
             *
             * @param tfs 匹配条件，多个AbstractTF是&&的关系，如：
             *            AbstractTF.newContentDescription("表情", true),AbstractTF.newClassName(AbstractTF.ST_IMAGEVIEW)
             *            表示描述内容是'表情'并且是imageview的控件
             */
            @NonNull
            public List<AccessibilityNodeInfo> findAll(AccessibilityServiceUtils[] tfs, AccessibilityNodeInfo rootInfo) {
                if (tfs.length == 0) throw new InvalidParameterException("AbstractTF不允许传空");

                ArrayList<AccessibilityNodeInfo> list = new ArrayList<>();
                if (rootInfo == null) return list;

                int idTextTFCount = 0, idTextIndex = 0;
                for (int i = 0; i < tfs.length; i++) {
                    if (tfs[i] instanceof AccessibilityServiceUtils.IdTextTF) {
                        idTextTFCount++;
                        idTextIndex = i;
                    }
                }
                switch (idTextTFCount) {
                    case 0://id或text数量为0，直接循环查找
                        findAllRecursive(list, rootInfo, tfs);
                        break;
                    case 1://id或text数量为1，先查出对应的id或text，然后再循环
                        List<AccessibilityNodeInfo> listIdText = ((IdTextTF) tfs[idTextIndex]).findAll(rootInfo);
                        if (isEmptyArray(listIdText)) {
                            break;
                        }
                        if (tfs.length == 1) {
                            list.addAll(listIdText);
                        } else {
                            for (AccessibilityNodeInfo info : listIdText) {
                                boolean isOk = true;
                                for (AccessibilityServiceUtils tf : tfs) {
                                    if (!tf.checkOk(info)) {
                                        isOk = false;
                                        break;
                                    }
                                }
                                if (isOk) {
                                    list.add(info);
                                } else {
                                    info.recycle();
                                }
                            }
                        }
                        break;
                    default:
                        throw new RuntimeException("由于时间有限，并且多了也没什么用，所以IdTF和TextTF只能有一个");
                }
                rootInfo.recycle();
                return list;
            }

            /**
             * @param tfs 由于是递归循环，会忽略IdTF和TextTF
             */
            public static void findAllRecursive(List<AccessibilityNodeInfo> list, AccessibilityNodeInfo parent, @NonNull AccessibilityServiceUtils... tfs) {
                if (parent == null || list == null) return;
                if (tfs.length == 0) throw new InvalidParameterException("AbstractTF不允许传空");

                for (int i = 0; i < parent.getChildCount(); i++) {
                    AccessibilityNodeInfo child = parent.getChild(i);
                    if (child == null) continue;
                    boolean isOk = true;
                    for (AccessibilityServiceUtils tf : tfs) {
                        if (!tf.checkOk(child)) {
                            isOk = false;
                            break;
                        }
                    }
                    if (isOk) {
                        list.add(child);
                    } else {
                        findAllRecursive(list, child, tfs);
                        child.recycle();
                    }
                }
            }


            /**
             * 由于太多,最好回收这些AccessibilityNodeInfo
             */
            public static void recycleAccessibilityNodeInfo(List<AccessibilityNodeInfo> listInfo) {
                if (isEmptyArray(listInfo)) return;

                for (AccessibilityNodeInfo info : listInfo) {
                    info.recycle();
                }
            }

            //集合是否是空的
            public static boolean isEmptyArray(Collection list) {
                return list == null || list.size() == 0;
            }

            public static <T> boolean isEmptyArray(T[] list) {
                return list == null || list.length == 0;
            }


        }

    }

    /**
     * 第三方平台类
     */
    public static class TripartitePlatform {

    }

    // 定义 GT 包 简易使用教程
    public interface CMD {

    }

//=============================================== 接口模块 ===================================================


}
