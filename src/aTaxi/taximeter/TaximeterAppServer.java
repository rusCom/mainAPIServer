package aTaxi.taximeter;

import aTaxi.ATaxiApplication;
import aTaxi.data.Driver;
import aTaxi.data.DriverActions;
import aTaxi.data.DriverStatus;
import aTaxi.data.DriverTariffPlan;
import aTaxiAPI.Messages;
import aTaxiAPI.Taximeter;
import aTaxiAPI.TaximeterLast;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.eclipse.jetty.server.Request;
import org.json.JSONArray;
import org.json.JSONObject;
import tools.AppServer;
import tools.AppServerResponse;
import tools.CheckDataException;
import tools.DateTimeUtils;
import tools.profileLoginCode.ProfileLoginCode;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

public class TaximeterAppServer extends AppServer {
    private final ATaxiApplication aTaxiApplication;
    private TaximeterApplication taximeterApplication;
    Database database;
    Driver curDriver;

    public TaximeterAppServer(ATaxiApplication aTaxiApplication) {
        this.aTaxiApplication = aTaxiApplication;
    }

    @Override
    public AppServerResponse response(String target, Request baseRequest) throws CacheException, CheckDataException, SQLException, IOException {
        super.response(target, baseRequest);
        aTaxiApplication.loadApplicationPreferences();
        taximeterApplication = aTaxiApplication.getTaximeterApplication();
        database = aTaxiApplication.getDataBase();


        curDriver = new Driver(database,
                paramString("token", false),
                location
        );

        if (curDriver.isAuth()) {
            appServerResponse.addAuthorization("driver_id", curDriver.getID());
            appServerResponse.addAuthorization("call_sign", curDriver.getCallSign());
        }

        switch (target) {
            case "/ataxi/taximeter/profile/auth" -> profileAuth();
            case "/ataxi/taximeter/profile/login" -> profileLogin();
            case "/ataxi/taximeter/profile/registration" -> profileRegistration();
            default -> {
                if (curDriver.isAuth()) {
                    appServerResponse.setStatus(200);
                    switch (target) {
                        case "/ataxi/taximeter/push" ->
                                Taximeter.DriverSetItem(database, curDriver.getID(), "PushToken", paramString("push_token"));
                        case "/ataxi/taximeter/profile/document/accept" -> {
                            if (paramString("doc_type").equals("public_offer")) {
                                Taximeter.DriverActionsAdd(database, curDriver.getID(), 181);
                            } else if (paramString("doc_type").equals("privacy_policy")) {
                                Taximeter.DriverActionsAdd(database, curDriver.getID(), 182);
                            }
                            else if (paramString("doc_type").equals("license_agreement")) {
                                Taximeter.DriverActionsAdd(database, curDriver.getID(), DriverActions.DriverLicenseAgreementApply);
                            }
                        }
                        case "/ataxi/taximeter/profile/set" -> profileSet();
                        case "/ataxi/taximeter/messages/send" -> {
                            String resMessagesSend = Taximeter.MessagesSend(database, curDriver.getID(), paramString("message"));
                            if (resMessagesSend.equals("OK")) {
                                lastData();
                            } else {
                                appServerResponse.setStatus(400, resMessagesSend);
                            }
                        }
                        case "/ataxi/taximeter/last/data" -> lastData();
                        case "/ataxi/taximeter/last/orders/check" -> lastOrdersCheck();
                        case "/ataxi/taximeter/last/orders/deny" -> {
                            TaximeterLast.OrdersDeny(database, curDriver.getID());
                            lastData();
                        }
                        case "/ataxi/taximeter/last/orders/apply" -> {
                            TaximeterLast.OrdersApply(database, curDriver.getID());
                            lastData();
                        }
                        case "/ataxi/taximeter/last/orders/waiting" -> {
                            String resWaiting = TaximeterLast.OrdersWaiting(database, curDriver.getID());
                            if (resWaiting.equals("ok")) {
                                lastData();
                            } else {
                                appServerResponse.setStatus(400, resWaiting);
                            }
                        }
                        case "/ataxi/taximeter/last/orders/executed" -> {
                            String resExecuted = TaximeterLast.OrdersExecuted(database, curDriver.getID());
                            if (resExecuted.equals("ok")) {
                                lastData();
                            } else {
                                appServerResponse.setStatus(400, resExecuted);
                            }
                        }
                        case "/ataxi/taximeter/last/orders/done" -> {
                            String resDone = TaximeterLast.OrdersDone(database, curDriver.getID());
                            if (resDone.equals("ok")) {
                                lastData();
                            } else {
                                appServerResponse.setStatus(400, resDone);
                            }
                        }
                        case "/ataxi/taximeter/last/his/order/get" ->
                                appServerResponse.setStatus(200, new JSONObject(TaximeterLast.HisOrderGet(database, paramInt("order_id"))));
                        case "/ataxi/taximeter/last/his_orders" ->
                                appServerResponse.setStatus(200, new JSONArray(TaximeterLast.GetHisOrders(database, curDriver.getID(), paramInt("last_id", 0))));
                        case "/ataxi/taximeter/payments" ->
                                appServerResponse.setStatus(200, new JSONArray(Taximeter.GetPayments(database, curDriver.getID(), paramInt("last_id", 0))));
                        case "/ataxi/taximeter/payments/corporate" ->
                                appServerResponse.setStatus(200, new JSONArray(Taximeter.GetPaymentsCorporateTaxi(database, curDriver.getID(), paramInt("last_id", 0))));
                        case "/ataxi/taximeter/payments/order" -> paymentsOrder();
                        case "/ataxi/taximeter/payments/tinkoff" -> appServerResponse.setStatus(200, taximeterApplication.getTinkoffTerminalData());
                        case "/ataxi/taximeter/last/his_messages" ->
                                appServerResponse.setStatus(200, new JSONArray(TaximeterLast.GetHisMessages(database, curDriver.getID(), paramInt("last_id", 0))));
                        case "/ataxi/taximeter/last/driver/online", "/ataxi/taximeter/driver/free" -> {
                            Taximeter.DriverFree(database, curDriver.getID());
                            lastData();
                        }
                        case "/ataxi/taximeter/last/driver/offline", "/ataxi/taximeter/driver/busy" -> {
                            Taximeter.DriverBusy(database, curDriver.getID());
                            lastData();
                        }
                        case "/ataxi/taximeter/driver/offline" -> Taximeter.DriverOffline(database, curDriver.getID());
                        case "/ataxi/taximeter/last/messages/deliver", "/ataxi/taximeter/messages/delivered" ->
                                Messages.SetStatus(database, paramInt("message_id"), "delivered");
                        case "/ataxi/taximeter/last/messages/read", "/ataxi/taximeter/messages/read" ->
                                Messages.SetStatus(database, paramInt("message_id"), "read");
                        case "/ataxi/taximeter/last/messages/send" ->
                                TaximeterLast.MessagesSend(database, curDriver.getID(), paramString("text"));
                        case "/ataxi/taximeter/last/statistics/rating" ->
                                appServerResponse.setStatus(200, TaximeterLast.StatisticsRating(database, curDriver.getID()));
                        case "/ataxi/taximeter/last/statistics/orders" ->
                                appServerResponse.setStatus(200, TaximeterLast.StatisticsOrders(database, curDriver.getID()));
                        case "/ataxi/taximeter/last/statistics/share" ->
                                appServerResponse.setStatus(200, TaximeterLast.StatisticsShare(database, curDriver.getID()));
                        case "/ataxi/taximeter/last/unlimited_tariff/activate" ->
                                TaximeterLast.UnlimitedTariffActivate(database, curDriver.getID(), paramInt("tariff_id"));
                        case "/ataxi/taximeter/tariff/activate" -> tariffActivate();
                    }
                } else {
                    appServerResponse.setStatus(401);
                }
            }
        }
        return appServerResponse;
    }

    private void tariffActivate() throws CheckDataException, CacheException {
        if (curDriver.driverTariffPlanID > 2) {
            appServerResponse.setStatus(400, "Вы уже активировали тариф. Повторная активация не доступна.");
        } else {
            DriverTariffPlan driverTariffPlan = taximeterApplication.getDriverTariffPlan(paramInt("tariff_id"));
            if (curDriver.balance < driverTariffPlan.Cost) {
                appServerResponse.setStatus(400, "Недостаточный баланс для активации тарифа. Необходимый баланс " + driverTariffPlan.Cost + " руб.");
            } else {
                Taximeter.TariffActivate(database, curDriver.getID(), driverTariffPlan.ID);
                lastData();
            }
        }
    }

    private void lastOrdersCheck() throws CheckDataException, CacheException {
        String dbAnswer = TaximeterLast.OrdersCheck(database, curDriver.getID(), paramString("order_id"));
        if (dbAnswer.equals("ok")) {
            lastData();
        } else {
            appServerResponse.setStatus(404, dbAnswer);
        }
    }

    private void lastData() throws CacheException {
        JSONObject result = new JSONObject(TaximeterLast.GetData(database, curDriver.getID()));
        appServerResponse.setStatus(200, result);
    }

    private void Auth() {
        if (curDriver.isAuth()) {
            if (curDriver.isBlocked) {
                JSONObject res = new JSONObject();
                res.put("support_phone", curDriver.organization.driverSupportPhone);
                res.put("callsign", curDriver.getCallSign());
                appServerResponse.setStatus(403, res);
            } else {
                appServerResponse.setStatus(200);
            }
        } else {
            appServerResponse.setStatus(401, taximeterApplication.getUnauthorizedData());
        }
    }

    private void profileAuth() throws CacheException {
        Auth();
        if (appServerResponse.getStatusCode().equals(200) && this.version >= 39) {
            if (curDriver.checkLicenseAgreementDate(taximeterApplication.documentsTimeout)) {
                if (!taximeterApplication.getLicenseAgreementLink().equals("")) {
                    JSONObject result = new JSONObject();
                    result.put("doc_name", "Лицензионное соглашение");
                    result.put("doc_link", taximeterApplication.getLicenseAgreementLink());
                    result.put("doc_type", "license_agreement");
                    appServerResponse.setStatus(451, result);
                }
            }
        }


        if (appServerResponse.getStatusCode().equals(200)) {

            String driverInviteCaption = taximeterApplication.driverInviteCaption + " Ваш код для приглашения: " + curDriver.inviteCode + ". Подробности по телефону: " + curDriver.organization.driverSupportPhone + ".";
            String driverInviteText = taximeterApplication.driverInviteText + " Подробности по телефону: " + curDriver.organization.driverSupportPhone + ". Не забудь про код: " + curDriver.inviteCode;

            JSONObject preferences = new JSONObject();
            preferences.put("support_phone", curDriver.organization.driverSupportPhone);
            if (!curDriver.city.driverDispatcherPhone.equals("")) {
                preferences.put("dispatcher_phone", curDriver.city.driverDispatcherPhone);
            }
            if (curDriver.city.driverDispatcherMessages) {
                preferences.put("dispatcher_messages", true);
            }


            preferences.put("driver_invite_caption", driverInviteCaption);
            preferences.put("driver_invite_text", driverInviteText);

            preferences.put("system_data_timer", taximeterApplication.systemDataTimer);
            preferences.put("system_template_messages_timeout", taximeterApplication.systemTemplateMessagesTimeout);
            preferences.put("dispatcher_template_messages", taximeterApplication.dispatcherTemplateMessages);
            preferences.put("check_prior_error_text", taximeterApplication.checkPriorErrorText);

            if (version < 40){
                // preferences.put("payment_instruction_link", taximeterApplication.paymentInstructionLink);
                preferences.put("payment_instruction_link", taximeterApplication.getQiwiNumber().getLink(curDriver.getCallSign()));
            }

            if (!taximeterApplication.instructionLink.equals("")) {
                preferences.put("instruction_link", taximeterApplication.instructionLink);
            }
            if (!taximeterApplication.vkGroupLink.equals("")) {
                preferences.put("vk_group_link", taximeterApplication.vkGroupLink);
            }

            // TODO использование рейтингов в зависимости от авто и города. Пока просто поставил что всегда используется если водитель не грузовой
            // Если водитель не грузовой, то показываем рейтинг и безлимит
            if (!curDriver.tariff.equals("cargo")) {
                preferences.put("use_rating", true);
                preferences.put("unlimited_tariff_plans", taximeterApplication.getUnlimitedDriverTariffPlans(curDriver.city.ID));
            }

            preferences.put("rest_hosts", taximeterApplication.restHosts);
            preferences.put("data_rest_hosts", taximeterApplication.dataRestHosts);
            /// ****************** Доступные типы платежей
            if (version >= 40){
                JSONObject availablePayments = new JSONObject();
                availablePayments.put("qiwi", true);
                availablePayments.put("qiwi_note", "Пополнение картой любого банка. Дополнительная комиссия при зачислении 4%. Общая комиссия около 6%, в зависимости от условий Вашего банка. Некоторые банки, например Сбер, берут так же дополнительную комиссию в размере не менее 30 рублей за один платеж. Зачисление в течение 10 минут.");
                availablePayments.put("sbp", false);
                availablePayments.put("sbp_note", "Требуется установленное приложение Вашего банка на данном телефоне. Комиссия 6%.");

                availablePayments.put("detail", "Комиссия при оплате через СПБ 6%. Комиссия по карте от 6% в зависимости от Вашего банка, т.к. некоторые банки берут дополнительную комиссию.");
                availablePayments.put("detail", availablePayments.getString("qiwi_note"));

                availablePayments.put("ckassa", "https://vk.com/ataxi10?w=wall-148683721_53");
                availablePayments.put("ckassa_note", "Требуется установка приложения. Общая комиссия около 11%.");
                availablePayments.put("qiwi_terminal", "https://vk.com/ataxi10?w=wall-148683721_275");
                availablePayments.put("qiwi_terminal_note", "Оплата наличными через Qiwi терминалы. Комиссия 4% + Комиссия терминала.");
                availablePayments.put("qiwi_wallet", "https://vk.com/ataxi10?w=wall-148683721_275");
                availablePayments.put("qiwi_wallet_note", "Оплата с зарегистрированного Qiwi кошелька. Комиссия 4%.");

                if (version >= 41){
                    availablePayments.put("sbp", true);
                    availablePayments.put("detail", "Комиссия при оплате через СПБ 6%. Комиссия по карте от 6% в зависимости от Вашего банка, т.к. некоторые банки берут дополнительную комиссию.");
                }
                preferences.put("available_payments", availablePayments);
            }


            /// ****************** На какие каналы надо подписать приложение для получения рассылки
            JSONArray pushTopics = new JSONArray();
            if (curDriver.pushNotificationActive) {
                if (curDriver.tariff.equals("cargo")) {
                    pushTopics.put("cargo_" + curDriver.city.ID);
                } else {
                    pushTopics.put("airport_notification_" + curDriver.city.ID);
                    pushTopics.put("transcity_notification_" + curDriver.city.ID);
                    pushTopics.put("increased_demand_notification_" + curDriver.city.ID);
                    // TODO автомобиль бизнес
                }
            }
            pushTopics.put("system_notification");

            if (curDriver.getID() == 121) {
                pushTopics.put("system");
            }

            if (pushTopics.length() > 0) {
                preferences.put("push_topics", pushTopics);
            }

            if (taximeterApplication.corporateTaxi != null) {
                preferences.put("corporate_taxi", taximeterApplication.corporateTaxi);
            }

            JSONObject profile = new JSONObject();
            if (!curDriver.pushNotificationActive) {
                profile.put("push_notification_active", false);
            }
            if (curDriver.taximeterFreeOrderCount != 20) {
                profile.put("free_order_count", curDriver.taximeterFreeOrderCount);
            }


            // Если водитель грузовой и статус занят, ставим что стаус Свободен с задержокй
            if (curDriver.tariff.equals("cargo") && curDriver.status == DriverStatus.Busy) {
                new Thread(() -> {
                    try {
                        Thread.sleep(5000);
                        TaximeterLast.DriverOnLine(database, curDriver.getID());
                    } catch (InterruptedException | CacheException ignored) {
                    }
                }).start();
            }

            JSONObject result = new JSONObject();
            result.put("preferences", preferences);
            result.put("profile", profile);

            result.put("last_his_messages", new JSONArray(TaximeterLast.GetHisMessages(database, curDriver.getID())));
            result.put("last_account", new JSONObject(TaximeterLast.GetAccount(database, curDriver.getID())));
            result.put("last_messages", new JSONArray(TaximeterLast.GetMessages(database, curDriver.getID())));
            result.put("application", taximeterApplication.applicationData);

            Taximeter.DriverActionsAdd(database, curDriver.getID(), DriverActions.StartApplication);
            if (!curDriver.taximeterVersion.equals(version)) {
                Taximeter.DriverSetItem(database, curDriver.getID(), "TaxiMeterVersion", version.toString());
            }

            appServerResponse.setStatus(200, result);
        }
    }

    private void profileSet() throws CacheException, CheckDataException {
        Boolean pushNotificationActive = paramBoolean("push_notification_active");
        if (pushNotificationActive != null) {
            if (!pushNotificationActive.equals(curDriver.pushNotificationActive)) {
                if (pushNotificationActive)
                    Taximeter.DriverSetItem(database, curDriver.getID(), "PushNotificationActive", "1");
                else Taximeter.DriverSetItem(database, curDriver.getID(), "PushNotificationActive", "0");
            }
        }
        Integer taximeterFreeOrderCount = paramInt("free_order_count", null);
        if (taximeterFreeOrderCount != null) {
            if (!taximeterFreeOrderCount.equals(curDriver.taximeterFreeOrderCount)) {
                Taximeter.DriverSetItem(database, curDriver.getID(), "TaximeterFreeOrderCount", String.valueOf(taximeterFreeOrderCount));
            }
        }
        appServerResponse.setStatus(200);
    }

    private void profileLogin() throws CheckDataException, CacheException {
        curDriver = Driver.byPhone(database, paramString("phone"));
        Auth();
        if (appServerResponse.getStatusCode() == 200) {
            Integer timeOut = ProfileLoginCode.getInstance().check(curDriver.getPhone(), baseRequest.getRemoteAddr());
            if (timeOut.equals(0)) {
                Messages.AddCallCode(database, curDriver.getPhone(), curDriver.getCode());
                appServerResponse.setStatus(200);
            } else {
                appServerResponse.setStatus(400, "Слишком частые запросы. Попробуйте через " + ProfileLoginCode.getInstance().nextAttempt(timeOut) + " секунд.");
            }
        }
    }

    private void profileRegistration() throws CheckDataException, CacheException {
        String code = paramString("code");
        curDriver = Driver.byPhone(database, paramString("phone"));

        Auth();
        if (appServerResponse.getStatusCode() == 200) {
            if (curDriver.getCode().equals(code)) {
                if (this.version >= 39){
                    if (!taximeterApplication.getLicenseAgreementLink().equals("")){
                        Taximeter.DriverActionsAdd(database, curDriver.getID(), DriverActions.DriverLicenseAgreementApply);
                    }
                }
                appServerResponse.setStatus(200, curDriver.getToken());
            } else {
                appServerResponse.setStatus(400, "Введен не верный код");
            }
        }
    }

    private void paymentsOrder() throws CheckDataException, CacheException, IOException {
        String source = paramString("source");
        if (source.equals("sbp")){
            appServerResponse.setStatus(400, "Ошибка проведения платежа. Попробуйте попозже");
            Integer amount = paramInt("amount");
            JSONObject result = new JSONObject(Taximeter.PaymentOrder(database, curDriver.getID(), amount, source));
            LocalDateTime lastPaymentDate = DateTimeUtils.convertFromCache(result.getString("last_payment_date"), true);
            if (lastPaymentDate.isBefore(LocalDateTime.now().minusDays(30))){
                result.put("message", true);
            }
            result.put("link", taximeterApplication.paymentInstructionLink);
            result.put("terminal_key", taximeterApplication.getTinkoffTerminalData().getString("terminal_key"));
            result.put("public_key", taximeterApplication.getTinkoffTerminalData().getString("public_key"));
            appServerResponse.setStatus(200, result);
        }
        else if (source.equals("qiwi")) {
            appServerResponse.setStatus(400, "Ошибка проведения платежа. Попробуйте попозже");

            Integer amount = paramInt("amount");
            JSONObject result = new JSONObject(Taximeter.PaymentOrder(database, curDriver.getID(), amount, source));
            LocalDateTime lastPaymentDate = DateTimeUtils.convertFromCache(result.getString("last_payment_date"), true);
            if (lastPaymentDate.isBefore(LocalDateTime.now().minusDays(30))){
                result.put("message", true);
            }
            String number = result.getString("number");
            ZonedDateTime expirationDateTime = ZonedDateTime.now().plusHours(3);

            // result.put("link", taximeterApplication.paymentInstructionLink);
            appServerResponse.setStatus(200, result);


            String url = "https://api.qiwi.com/partner/bill/v1/bills/" + number;
            JSONObject putBody = new JSONObject();
            JSONObject amountBody = new JSONObject();
            amountBody.put("currency", "RUB");
            amountBody.put("value", amountDecimalFormat.format(amount / 100.0));
            putBody.put("amount", amountBody);
            putBody.put("expirationDateTime", expirationDateTime);
            putBody.put("comment", curDriver.getCallSign());
            JSONObject customFields = new JSONObject();
            customFields.put("paySourcesFilter", "card");
            customFields.put("themeCode", taximeterApplication.getQiwiNumber().getThemeCode());
            customFields.put("callsign", curDriver.getCallSign());
            customFields.put("order_number", number);
            putBody.put("customFields", customFields);
            JSONObject customer = new JSONObject();
            customer.put("phone", curDriver.getPhone());
            customer.put("account", curDriver.getCallSign());
            putBody.put("customer", customer);
            // System.out.println(url);
            // System.out.println(putBody);
            // System.out.println(taximeterApplication.getQiwiNumber().getSecretKey());

            OkHttpClient okHttpClient = new OkHttpClient();
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(url)
                    .addHeader("content-type", "application/json")
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer " + taximeterApplication.getQiwiNumber().getSecretKey())
                    .put(RequestBody.create(MediaType.parse("application/json"), putBody.toString()))
                    .build();

            Response responseData = okHttpClient.newCall(request).execute();
            // System.out.println(responseData.code());
            if (responseData.code() == 200) {
                JSONObject response = new JSONObject(Objects.requireNonNull(responseData.body()).string());
                // System.out.println(response);
                if (!response.has("errorCode")){
                    result.put("link", response.getString("payUrl"));
                    appServerResponse.setStatus(200, result);
                }
            }
            responseData.close();
        } else {
            appServerResponse.setStatus(400, "unavailable payment source type");
        }
    }


}
