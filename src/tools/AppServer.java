package tools;

import com.intersys.objects.CacheException;
import geo.data.Location;
import org.eclipse.jetty.server.Request;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.codec.binary.Base64.decodeBase64;


public class AppServer {
    protected AppServerResponse appServerResponse;
    protected JSONObject authorization;
    protected String[] targets;
    protected Request baseRequest;
    protected Integer version = 0;
    private final Map<String, String> params = new HashMap<>();
    protected Location location;
    private JSONObject bodyJSON;
    protected static final DecimalFormat amountDecimalFormat = new DecimalFormat("0.00");



    public AppServerResponse response(String target, Request baseRequest) throws CacheException, CheckDataException, SQLException, IOException {
        this.baseRequest = baseRequest;
        targets = target.split("/");
        authorization = new JSONObject();

        // Если параметры авторизации переданы в параметрах запроса
        try {
            if (baseRequest.getParameter("token") != null) {
                authorization.put("token", baseRequest.getParameter("token"));

            }
        } catch (Exception e) {
            // e.printStackTrace();
        }


        // Если параметры авторизации переданы в заголовке
        if (baseRequest.getHeader("token") != null) {
            authorization.put("token", baseRequest.getHeader("token"));
        }

        // Считываем паарметры авторизации, переданные в заголовке authorization с кодированием Base64
        if (baseRequest.getHeader("authorization") != null) {
            String[] authHeader = baseRequest.getHeader("authorization").split(" ");
            if (authHeader[0].equals("Bearer")) {
                byte[] result = decodeBase64(authHeader[1].getBytes());
                try {
                    authorization = new JSONObject(new String(result));
                    if (authorization.has("location")) {
                        if (authorization.getJSONObject("location").has("lt")
                                && (authorization.getJSONObject("location").has("ln"))) {
                            try {
                                location = new Location(authorization.getJSONObject("location"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    } // if (authorization.has("location")) {
                    if (authorization.has("version")) {
                        String ver = authorization.getString("version");
                        if (ver.contains(".")) {
                            ver = ver.split("\\.")[0];
                        }
                        try {
                            version = Integer.parseInt(ver);
                        } catch (Exception ignored) {
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        if (MainUtils.JSONGetString(authorization, "token").equals("null")) {
            authorization.put("token", "");
        }

        appServerResponse = new AppServerResponse(405);
        appServerResponse.setAuthorization(authorization);


        return null;
    }

    protected LocalDateTime paramDateTime(String paramName) throws CheckDataException {
        String value = param(paramName);

        if (value == null) {
            throw new CheckDataException("Параметр `" + paramName + "` не передан.");
        }
        if (value.equals("")) {
            throw new CheckDataException("Параметр `" + paramName + "` не передан.");
        }
        try {
            return LocalDateTime.parse(value);
        } catch (Exception e) {
            throw new CheckDataException("Параметр `" + paramName + "` не соотвествует ISO 8601.");
        }
    }

    protected String paramString(String paramName, boolean needed) throws CheckDataException {
        if (needed) {
            return paramString(paramName);
        }
        String result = param(paramName);
        return result;
    }

    protected String paramString(String paramName) throws CheckDataException {
        String result = param(paramName);
        if (result.equals("")) {
            throw new CheckDataException("Параметр " + paramName + " обязателен");
        }
        if (paramName.equals("phone")) {
            result = MainUtils.convertPhone(result);
            if (result.equals("")) {
                throw new CheckDataException("Параметр " + paramName + " не является мобильным номером телефона");
            }
        }
        return result;
    }

    protected Double paramDouble(String paramName) throws CheckDataException {
        String result = param(paramName);
        if (result.equals("")) {
            throw new CheckDataException("Параметр " + paramName + " обязателен");
        }
        double resultDouble;
        try {
            resultDouble = Double.parseDouble(result);
        } catch (Exception exception) {
            throw new CheckDataException("Параметр " + paramName + " должен быть числом");
        }
        return resultDouble;
    }

    protected Integer paramInt(String paramName, Integer defValue) throws CheckDataException {
        String result = param(paramName);
        if (result.equals("")) {
            return defValue;
        }
        int resultInteger;
        try {
            resultInteger = Integer.parseInt(result);
        } catch (Exception exception) {
            throw new CheckDataException("Параметр " + paramName + " должен быть числом");
        }
        return resultInteger;
    }

    protected Integer paramInt(String paramName) throws CheckDataException {
        String result = param(paramName);
        if (result.equals("")) {
            throw new CheckDataException("Параметр " + paramName + " обязателен");
        }
        int resultInteger;
        try {
            resultInteger = Integer.parseInt(result);
        } catch (Exception exception) {
            throw new CheckDataException("Параметр " + paramName + " должен быть числом");
        }
        return resultInteger;
    }

    public String param(String name) {
        String result = "";
        if (authorization.has(name)) {
            result = MainUtils.JSONGetString(authorization, name);
        } else if (baseRequest.getParameter(name) != null) {
            result = baseRequest.getParameter(name);
        } else if (params.containsKey(name)) {
            result = params.get(name);
        } else if (getBodyJSON().has(name)) { // проверяем, если в теле запроса простой JSON и там есть эти данные
            result = MainUtils.JSONGetString(getBodyJSON(), name);
        }
        return result;
    }

    protected Boolean paramBoolean(String paramName){
        String result = param(paramName);
        switch (result) {
            case "" : return null;
            case "true":
            case "1": return true;
            default : return false;
        }
    }

    protected JSONObject getBodyJSON() {
        if (this.bodyJSON == null) {
            this.bodyJSON = this.loadBodyJSON();
            appServerResponse.setPostBody(bodyJSON);
        }
        return this.bodyJSON;
    }

    private JSONObject loadBodyJSON() {
        String body;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = baseRequest.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            try {
                throw ex;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    try {
                        throw ex;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        body = stringBuilder.toString();
        if (body.equals("")) {
            body = "{}";
        }
        return new JSONObject(body);
    }
}
