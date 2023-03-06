package aTaxi.payments;

import aTaxi.ATaxiApplication;
import aTaxiAPI.Payments;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import org.eclipse.jetty.server.Request;
import org.json.JSONObject;
import tools.AppServer;
import tools.AppServerResponse;
import tools.CheckDataException;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static tools.MainUtils.JSONGetString;

public class PaymentsAppServer extends AppServer {
    Database database;
    Integer UserID;
    @Override
    public AppServerResponse response(String target, Request baseRequest) throws CacheException, CheckDataException, SQLException, IOException {
        super.response(target, baseRequest);
        database = ATaxiApplication.getInstance().getDataBase();
        UserID = Payments.TerminalAuth(database, paramString("token", false));
        if (target.startsWith("/ataxi/payments/terminal/")) {
            if (!UserID.equals(0)) {
                switch (target) {
                    case "/ataxi/payments/terminal/check" -> {
                        JSONObject driverJSON = new JSONObject(Payments.TerminalCheck(database, paramString("data")));
                        if (driverJSON.toString().equals("")) {
                            appServerResponse.setStatus(404);
                        } else {
                            appServerResponse.setStatus(200, driverJSON);
                        }
                    }
                    case "/ataxi/payments/terminal/pay" -> {
                        Integer Status = Payments.TerminalPay(database, UserID, paramString("guid"), paramInt("summa"));
                        if (Status > 0) {
                            JSONObject res = new JSONObject();
                            res.put("payment_id", Status);
                            res.put("payment_date_time", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
                            appServerResponse.setStatus(200, res);
                        } else if (Status == -1) {
                            appServerResponse.setStatus(400, "Водитель не найден.");
                        } else {
                            appServerResponse.setStatus(400, "Ошибка проведения платежа. Попробуйте попозже.");
                        }
                    }
                }
            } else {
                appServerResponse.setStatus(401);
            }
        } else if (target.equals("/ataxi/payments/tinkoff/notifications")) {
            if (UserID.equals(0)) {
                appServerResponse.setStatus(401);
            } else {
                paymentsTinkoffNotifications();
            }
        } else if (target.equals("/ataxi/payments/notifications")) {
            paymentsNotifications();
        }
        return appServerResponse;
    }

    private void paymentsTinkoffNotifications() throws CacheException {
        appServerResponse.setStatus(400);
        if (baseRequest.getMethod().equals("POST")) {
            JSONObject result = new JSONObject();
            String TerminalKey = JSONGetString(getBodyJSON(), "TerminalKey");
            String OrderId = JSONGetString(getBodyJSON(), "OrderId");
            String Status = JSONGetString(getBodyJSON(), "Status");
            if (TerminalKey.equals("1676272733874")){
                if (!OrderId.equals("")){
                    Payments.TinkoffPay(database, UserID, OrderId, Status);
                    appServerResponse.setStatus(200);
                }
            }
        }
    }

    private void paymentsNotifications() {
        if (baseRequest.getMethod().equals("POST")) {
            appServerResponse.setStatus(200, getBodyJSON());
        }
        appServerResponse.setStatus(200, getBodyJSON());
    }


}
