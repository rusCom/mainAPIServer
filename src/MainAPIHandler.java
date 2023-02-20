import aTaxi.ATaxiApplication;
import aTaxi.main.MainAppServer;
import aTaxi.payments.PaymentsAppServer;
import aTaxi.taximeter.TaximeterAppServer;
import com.intersys.objects.CacheException;
import geo.GeoAppServer;
import geo.GeoApplication;
import geo.tools.GeoCode;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.json.JSONObject;
import tools.AppServerResponse;
import tools.CheckDataException;
import tools.MainUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.sql.SQLException;

public class MainAPIHandler extends AbstractHandler {
    ATaxiApplication aTaxiApplication;

    public MainAPIHandler() throws CacheException {
        MainUtils.getInstance().printLog("main", "create MainAPIHandler");
        aTaxiApplication = ATaxiApplication.getInstance();
    }

    @Override
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse) {
        long startTime = System.currentTimeMillis();
        AppServerResponse appServerResponse = new AppServerResponse(405);
        String serverType = "main";
        try {
            if (baseRequest.getMethod().equals("OPTIONS")) {
                httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
                httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
                httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, X-API-KEY");
                appServerResponse.setStatus(200);
            } else if (baseRequest.getMethod().equals("GET") || baseRequest.getMethod().equals("POST")) {
                if (target.equals("/")) {
                    serverType = "unavailable";
                    appServerResponse.setStatus(503);
                } else {
                    String[] targets = target.split("/");
                    if (targets.length != 0) {
                        serverType = targets[1];
                        switch (serverType) {
                            case "geo" ->
                                    appServerResponse = (new GeoAppServer(aTaxiApplication)).response(target, baseRequest);
                            case "ataxi" -> {
                                if (targets.length != 2) {
                                    serverType = "ataxi";
                                    switch (targets[2]) {
                                        case "taximeter" -> {
                                            serverType = "ataxi_taximeter";
                                            appServerResponse = (new TaximeterAppServer(aTaxiApplication)).response(target, baseRequest);
                                        }
                                        case "payments" -> {
                                            serverType = "ataxi_payments";
                                            appServerResponse = (new PaymentsAppServer()).response(target, baseRequest);
                                        }
                                        case "main" ->{
                                            serverType = "ataxi_main";
                                            appServerResponse = (new MainAppServer(aTaxiApplication)).response(target, baseRequest);
                                        }
                                    }
                                }
                            }
                            case "status" -> appServerResponse = status();
                            default -> {
                                serverType = "unavailable";
                                appServerResponse.setStatus(503);
                            }
                        }
                    } // Если target не пусто
                } // if (target.equals("/")) { ...} else {
            } else {
                serverType = "unavailable";
                appServerResponse.setStatus(503);
            }
        } catch (CheckDataException checkDataException) {
            appServerResponse.setStatus(400, checkDataException.getLocalizedMessage());
        } catch (CacheException cacheException) {
            MainUtils.getInstance().printLog("cacheException", ExceptionUtils.getStackTrace(cacheException));
            if (baseRequest.getRemoteAddr().equals("[0:0:0:0:0:0:0:1]")){
                System.out.println(ExceptionUtils.getStackTrace(cacheException));
            }
            appServerResponse.setStatus(500);
        } catch (SQLException sqlException) {
            MainUtils.getInstance().printLog("sqlException", ExceptionUtils.getStackTrace(sqlException));
            if (baseRequest.getRemoteAddr().equals("[0:0:0:0:0:0:0:1]")){
                System.out.println(ExceptionUtils.getStackTrace(sqlException));
            }
            appServerResponse.setStatus(500);
        } catch (RuntimeException runtimeExceptionException) {
            MainUtils.getInstance().printLog("runtimeExceptionException", ExceptionUtils.getStackTrace(runtimeExceptionException));
            if (baseRequest.getRemoteAddr().equals("[0:0:0:0:0:0:0:1]")){
                System.out.println(ExceptionUtils.getStackTrace(runtimeExceptionException));
            }
            appServerResponse.setStatus(500);
        } catch (Exception e) {
            MainUtils.getInstance().printLog("anotherExceptionException", ExceptionUtils.getStackTrace(e));
            if (baseRequest.getRemoteAddr().equals("[0:0:0:0:0:0:0:1]")){
                System.out.println(ExceptionUtils.getStackTrace(e));
            }
            appServerResponse.setStatus(500, e.getLocalizedMessage());
        }

        // Вывод лога
        StringBuilder logText = new StringBuilder();
        logText.append("date").append("\t").append("= ").append(MainUtils.getCurDateTime()).append("\n");
        logText.append("address").append("\t").append("= ").append(baseRequest.getRemoteAddr()).append("\n");
        logText.append("target").append("\t").append("= ").append(target).append("\n");
        if (!baseRequest.getMethod().equals("GET")) {
            logText.append("method").append("\t").append("= ").append(baseRequest.getMethod()).append("\n");
        }
        if (!appServerResponse.getAuthorization().toString().equals("{}"))
            logText.append("auth").append("\t").append("= ").append(appServerResponse.getAuthorization().toString()).append("\n");
        if (baseRequest.getMethod().equals("POST")) {
            if (appServerResponse.getPostBody() != null) {
                if (!appServerResponse.getPostBody().isEmpty()) {
                    logText.append("body").append("\t").append("= ").append(appServerResponse.getPostBody().toString()).append("\n");
                }
            }
        }

        try {
            if (!baseRequest.getParameterMap().toString().equals("{}")) {
                logText.append("params").append("\t").append("= ").append(baseRequest.getParameterMap().toString()).append("\n");
            }
        } catch (Exception ignored) {
        }

        if (!appServerResponse.getStatus().equals("OK")) {
            logText.append("status").append("\t").append("= ").append(appServerResponse.getStatus()).append("\n");
        }
        logText.append("time").append("\t").append("= ").append((System.currentTimeMillis() - startTime) / 1000.0).append("\n");

        logText.append("resp").append("\t").append("= ").append(appServerResponse.getBodyLog()).append("\n");

        if (target.endsWith("/data")) {
            if (MainUtils.getInstance().getDataLog()) {
                MainUtils.getInstance().printLog(serverType, logText.toString());
            }
        } else {
            MainUtils.getInstance().printLog(serverType, logText.toString());
        }

        if (target.endsWith("server_error")) {
            MainUtils.getInstance().printLog("server_error", logText.toString());
        }

        if ((System.currentTimeMillis() - startTime) > 2000.0) {
            logText.append("time").append("\t").append("= ").append((System.currentTimeMillis() - startTime) / 1000.0).append("\n");
            MainUtils.getInstance().printLog("long_request", logText.toString());
        }

        // Вывод ответа
        httpServletResponse.setHeader("Server", serverType);
        try {
            if (appServerResponse.getStatusCode().equals(503)) {
                httpServletResponse.getWriter().print("503 Service Unavailable");
                httpServletResponse.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            } else if (appServerResponse.getStatusCode().equals(1001)) { // Вывод файла
                File file = new File(appServerResponse.getFileName());
                if (file.exists()){
                    String mimeType = URLConnection.guessContentTypeFromName(file.getName());
                    InputStream fis = new FileInputStream(file);
                    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                    httpServletResponse.setContentType(mimeType != null ? mimeType : "application/octet-stream");
                    httpServletResponse.setContentLength((int) file.length());
                    httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
                    ServletOutputStream os = httpServletResponse.getOutputStream();
                    byte[] bufferData = new byte[1024];
                    int read;
                    while ((read = fis.read(bufferData)) != -1) {
                        os.write(bufferData, 0, read);
                    }
                    os.flush();
                    os.close();
                    fis.close();
                }
                else {
                    httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }

            } else { // выводим стандартный вывод с объектом JSON
                JSONObject result = new JSONObject();
                result.put("status", appServerResponse.getStatus());
                result.put("status_code", appServerResponse.getStatusCode());
                if (appServerResponse.getBodyType().equals("string")) {
                    result.put("result", appServerResponse.getBodyString());
                    result.put("result_type", "string");
                } else if (appServerResponse.getBodyType().equals("json")) {
                    result.put("result", appServerResponse.getBodyJSON());
                    result.put("result_type", "json");
                } else if (appServerResponse.getBodyType().equals("jsonArray")) {
                    result.put("result", appServerResponse.getBodyArrayJSON());
                    result.put("result_type", "json_array");
                }
                result.put("time", (System.currentTimeMillis() - startTime) / 1000.0);
                httpServletResponse.setContentType("application/json;charset=utf-8");
                httpServletResponse.getWriter().print(result);
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (Exception e) {
            MainUtils.getInstance().printLog("printAnswerException", ExceptionUtils.getStackTrace(e));
        }

        baseRequest.setHandled(true);
    }

    AppServerResponse status() {
        JSONObject result = new JSONObject();
        result.put("pid", ProcessHandle.current().pid());

        JSONObject cache = new JSONObject();
        cache.put("bts_streets", GeoApplication.getInstance().getGeoCode().getBTSStreetsCount());

        result.put("cache", cache);

        int mb = 1024 * 1024;
        // get Runtime instance
        Runtime instance = Runtime.getRuntime();
        JSONObject memory = new JSONObject();
        memory.put("total", instance.totalMemory() / mb);
        memory.put("free", instance.freeMemory() / mb);
        memory.put("used", (instance.totalMemory() - instance.freeMemory()) / mb);
        memory.put("max", instance.maxMemory() / mb);
        result.put("memory", memory);


        return new AppServerResponse(200, result);
    }
}
