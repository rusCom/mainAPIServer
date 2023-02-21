package tools;

import okhttp3.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

public class MainUtils {
    private static volatile MainUtils mainUtilsInstance;
    private final String curDir;
    private final HashMap<String, PrintWriter> printWriterMap;
    private Boolean consoleLog = false, fileLog = true, onlyMainLog = false, dataLog = false;
    private Properties properties;
    private DateFormat cacheDateFormatter;
    private OkHttpClient okHttpClient;

    public static MainUtils getInstance() {
        MainUtils localInstance = mainUtilsInstance;
        if (localInstance == null) {
            synchronized (MainUtils.class) {
                localInstance = mainUtilsInstance;
                if (localInstance == null) {
                    mainUtilsInstance = localInstance = new MainUtils();
                }
            }
        }
        return localInstance;
    }

    public MainUtils() {
        printWriterMap = new HashMap<>();
        curDir = new File("").getAbsolutePath();

        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(curDir + "/server.properties");
            properties.load(fis);
            consoleLog = Boolean.valueOf(properties.getProperty("server.consoleLog", "false"));
            fileLog = Boolean.valueOf(properties.getProperty("server.fileLog", "true"));
            onlyMainLog = Boolean.valueOf(properties.getProperty("server.onlyMainLog", "false"));
            dataLog = Boolean.valueOf(properties.getProperty("server.dataLog", "false"));
        } catch (Exception ignored) {
        }

        new File(curDir + "/log/").mkdir();
    }

    private OkHttpClient getOkHttpClient() {
        if (okHttpClient == null){
            okHttpClient = new OkHttpClient();
        }
        return okHttpClient;
    }

    public JSONObject httpPost(String url, JSONObject postBody){
        JSONObject result = null;
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(postBody.toString(), MediaType.parse("application/json")))
                .build();
        try (Response responseData = getOkHttpClient().newCall(request).execute()) {
            if (responseData.code() == 200) {
                result = new JSONObject(Objects.requireNonNull(responseData.body()).string());
                responseData.body().close();
            }
        } catch (Exception exception) {
            MainUtils.getInstance().printLog("runtimeExceptionException", ExceptionUtils.getStackTrace(exception));
        }
        return result;
    }

    public JSONObject httpGet(String url){
        JSONObject result = null;
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response responseData = getOkHttpClient().newCall(request).execute()) {
            if (responseData.code() == 200) {
                result = new JSONObject(Objects.requireNonNull(responseData.body()).string());
                responseData.body().close();
            }
        } catch (Exception exception) {
            MainUtils.getInstance().printLog("runtimeExceptionException", ExceptionUtils.getStackTrace(exception));
        }

        return result;
    }

    public String getPropertyString(String propertyName) {
        String result = null;
        try {
            result = properties.getProperty(propertyName);
        } catch (Exception ignored) {
        }
        return result;
    }

    public String getPropertyString(String propertyName, String def) {
        String result = getPropertyString(propertyName);
        if (result == null){return def;}
        if (result == ""){return def;}
        return result;
    }

    public Integer getPropertyInteger(String propertyName, Integer def) {
        Integer result = getPropertyInteger(propertyName);
        if (result == null){return def;}
        return result;
    }

    public Integer getPropertyInteger(String propertyName) {
        Integer result = null;
        try {
            result = Integer.parseInt(properties.getProperty(propertyName));
        } catch (Exception ignored) {
        }
        return result;
    }

    public Boolean getDataLog() {
        return dataLog;
    }

    public void printLog(String logString) {
        printLog("main", logString);
    }

    public void printLog(Object object, String logString){
        String className = object.getClass().getSimpleName();
        printLog(className, logString);
    }

    public void printLog(String logType, String logString) {

        if (logType.equals("main")) {
            logString = getCurDateTime() + " - " + logString;
        }
        if (logType.equals("unavailable")) {
            try {
                getLogPrintWriter(logType).println(logString);
                getLogPrintWriter(logType).flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return;
        }

        if (consoleLog) {
            if (!logType.equals("main")) {
                logString = "logType\t= " + logType + "\n" + logString;
            }

            if (!onlyMainLog) {
                if (logString.length() > 510) {
                    System.out.println(logString.substring(0, 500));
                } else {
                    System.out.println(logString);
                }
                System.out.println("********************************************************");

            } else if (logType.equals("main")) {
                if (logString.length() > 510) {
                    System.out.println(logString.substring(0, 500));
                } else {
                    System.out.println(logString);
                }
                System.out.println("********************************************************");
            }
        }
        if (fileLog) {
            try {
                getLogPrintWriter(logType).println(logString);
                getLogPrintWriter(logType).println("********************************************************");
                getLogPrintWriter(logType).flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private PrintWriter getLogPrintWriter(String logType) throws FileNotFoundException {
        if (printWriterMap.get(logType) == null) {
            String logFileName;
            if (logType.equals("main")) {
                logFileName = curDir + "/log/main.txt";
            } else {
                logFileName = curDir + "/log/" + logType + "_" + getCurDateTime().replace(" ", "_").replace(":", "_").replace("-", "_") + ".txt";
            }

            OutputStream outputStream = new FileOutputStream(logFileName, true);

            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            printWriterMap.put(logType, printWriter);
        }
        return printWriterMap.get(logType);
    }

    public static String getCurDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


    public static int boolToInt(boolean data){
        if (data)return 1;
        return 0;
    }

    public static Boolean parseBoolean(String data){
        return parseBoolean(data, false);
    }

    public static Boolean parseBoolean(String data, Boolean def){
        if (data == null)return def;
        if (data.equals(""))return def;
        if (data.equals("1"))return true;
        if (data.equals("true"))return true;
        return Boolean.parseBoolean(data);
    }

    public static Integer JSONGetInteger(JSONObject data, String field) {
        Integer result = null;
        if (data.has(field)) {
            String resString = JSONGetString(data, field);
            if (resString.contains(".")){
                resString = resString.split("\\.")[0];
            }
            result = Integer.parseInt(resString);
        }
        return result;
    }

    public static Integer JSONGetInteger(JSONObject data, String field, Integer def) {
        Integer result = def;
        if (data.has(field)) {
            String resString = JSONGetString(data, field);
            if (resString.contains(".")){
                resString = resString.split("\\.")[0];
            }
            if (!resString.equals("")){
                result = Integer.parseInt(resString);
            }
        }
        return result;
    }

    public static Float JSONGetFloat(JSONObject data, String field) {
        Float result = null;
        if (data.has(field)) {
            result = Float.parseFloat(JSONGetString(data, field));
        }
        return result;
    }

    public static Double JSONGetDouble(JSONObject data, String field) {
        Double result = null;
        if (data.has(field)) {
            result = Double.parseDouble(JSONGetString(data, field));
        }
        return result;
    }

    public static Boolean JSONGetBool(JSONObject data, String field) {
        Boolean result = null;
        if (data.has(field)) {
            try {
                result = data.getBoolean(field);
            } catch (JSONException ignored) {
            }
            if (result == null) {
                String fieldValue = JSONGetString(data, field);
                if (fieldValue.equals("true")) {
                    result = true;
                }
                if (fieldValue.equals("1")) {
                    result = true;
                }
            }
        }
        if (result == null) {
            result = false;
        }
        return result;
    }

    public static String JSONGetString(JSONObject data, String field) {
        String result = "";
        if (data.has(field)) {
            Object object = data.get(field);

            if (object instanceof Boolean) {
                if (((Boolean) object)) {
                    result = "1";
                } else {
                    result = "0";
                }
            } else {
                result = String.valueOf(object);
            }
            if (result.equals("true")) {
                result = "1";
            }
            if (result.equals("false")) {
                result = "0";
            }
        }

        return result;
    }

    public static String convertPhone(String phone) {
        if (phone == null) {
            return "";
        }
        if (phone.isEmpty()) {
            return "";
        }

        String result = phone.replaceAll("[^\\d]", "");
        if (result.length() == 10) {
            result = "8" + result;
        } else {
            result = "8" + result.substring(1);
        }

        if (!result.startsWith("89")) {
            return "";
        }

        if (result.length() != 11) {
            return "";
        }

        return result;
    }

    public static Integer passedTimeMin(long timeout) {
        if (timeout == 0) {
            return 2147483647;
        }
        long absMillis = Math.abs(System.currentTimeMillis() - timeout);
        int absSek = Math.toIntExact(absMillis / 1000);

        return Math.toIntExact(absSek / 60);
    }

    public static String[] split(String data) {
        String[] result = new String[0];
        if (!data.equals("")) {
            try {
                result = data.split("\\^");
            } catch (Exception ignored) {

            }
        }
        return result;
    }
}
