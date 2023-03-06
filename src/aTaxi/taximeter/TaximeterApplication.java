package aTaxi.taximeter;

import aTaxi.data.DriverTariffPlan;
import aTaxi.data.QiwiNumber;
import org.json.JSONArray;
import org.json.JSONObject;
import tools.MainUtils;

import java.util.ArrayList;
import java.util.List;

import static tools.MainUtils.*;

public class TaximeterApplication {
    private JSONObject unauthorizedData;
    public JSONObject profileLoginData;
    public JSONObject applicationData;
    public String supportPhone;
    public Integer systemDataTimer;
    public Integer systemTemplateMessagesTimeout;
    public String paymentInstructionLink;
    public String instructionLink;
    public String vkGroupLink;
    JSONArray dispatcherTemplateMessages;
    JSONArray restHosts;
    JSONArray dataRestHosts;
    JSONObject corporateTaxi;
    public String checkPriorErrorText;
    public String driverInviteCaption;
    public String driverInviteText;
    public List<DriverTariffPlan> driverTariffPlans;
    private String licenseAgreementLink;
    private QiwiNumber curFormQiwiNumber;
    public Integer documentsTimeout; // Через какое время надо подписывать документы повторно водителям. Задается в днях

    private JSONObject tinkoffTerminalData;

    public void loadApplicationPreferences(JSONObject data) {
        this.supportPhone = JSONGetString(data, "support_phone");
        this.licenseAgreementLink = JSONGetString(data, "license_agreement_link");

        unauthorizedData = new JSONObject();
        unauthorizedData.put("support_phone", supportPhone);
        unauthorizedData.put("registration_link", JSONGetString(data, "registration_link"));
        unauthorizedData.put("registration_app", JSONGetString(data, "registration_app"));
        unauthorizedData.put("privacy_policy_link", JSONGetString(data, "privacy_policy_link"));
        unauthorizedData.put("license_agreement_link", licenseAgreementLink);

        profileLoginData = new JSONObject();
        profileLoginData.put("type", "call");
        profileLoginData.put("timeout", MainUtils.getInstance().getPropertyString("profileLoginCode.timeout"));

        applicationData = new JSONObject();
        applicationData.put("android_necessary_version", JSONGetString(data, "android_necessary_version"));
        applicationData.put("android_desirable_version", JSONGetString(data, "android_desirable_version"));

        this.systemDataTimer = JSONGetInteger(data, "system_data_timer");
        this.systemTemplateMessagesTimeout = JSONGetInteger(data, "system_template_messages_timeout");
        this.paymentInstructionLink = JSONGetString(data, "payment_instruction_link");
        this.instructionLink = JSONGetString(data, "instruction_link");
        this.vkGroupLink = JSONGetString(data, "vk_group_link");
        this.dispatcherTemplateMessages = data.getJSONArray("dispatcher_template_messages");

        this.checkPriorErrorText = JSONGetString(data, "check_prior_error_text");
        this.driverInviteCaption = JSONGetString(data, "driver_invite_caption");
        this.driverInviteText = JSONGetString(data, "driver_invite_text");
        this.documentsTimeout = JSONGetInteger(data, "documents_timeout");

        if (data.has("rest_hosts")) {
            this.restHosts = data.getJSONArray("rest_hosts");
        } else {
            this.restHosts = null;
        }
        if (data.has("data_rest_hosts")) {
            this.dataRestHosts = data.getJSONArray("data_rest_hosts");
        } else {
            this.dataRestHosts = null;
        }

        corporateTaxi = null;
        if (JSONGetBool(data, "corporate_taxi")) {
            corporateTaxi = new JSONObject();
            corporateTaxi.put("contact_phone", JSONGetString(data, "corporate_taxi_contact_phone"));
            corporateTaxi.put("balance_button_dialog", JSONGetString(data, "corporate_taxi_balance_button_dialog"));
            corporateTaxi.put("check_order_dialog", JSONGetString(data, "corporate_taxi_check_order_dialog"));

        }

        if (driverTariffPlans == null){
            driverTariffPlans = new ArrayList<>();
        }

        driverTariffPlans.clear();
        if (data.has("unlimited_driver_tariff_plans")) {
            JSONArray unlimited_driver_tariff_plans = data.getJSONArray("unlimited_driver_tariff_plans");
            for (int itemID = 0; itemID < unlimited_driver_tariff_plans.length(); itemID++) {
                driverTariffPlans.add(new DriverTariffPlan(unlimited_driver_tariff_plans.getJSONObject(itemID)));
            }
        }

        curFormQiwiNumber = QiwiNumber.fromJSON(data.getJSONObject("qiwi_number"));
    }

    public DriverTariffPlan getDriverTariffPlan(Integer driverTariffPlanID){
        DriverTariffPlan driverTariffPlanResult = null;
        for (DriverTariffPlan driverTariffPlan : driverTariffPlans) {
            if (driverTariffPlan.ID.equals(driverTariffPlanID)) {
                driverTariffPlanResult = driverTariffPlan;
            }
        }
        return driverTariffPlanResult;
    }

    public JSONObject getTinkoffTerminalData() {
        return tinkoffTerminalData;
    }

    public void setTinkoffTerminalData(JSONObject tinkoffTerminalData) {
        this.tinkoffTerminalData = tinkoffTerminalData;
    }

    public String getLicenseAgreementLink() {
        return licenseAgreementLink;
    }

    public JSONArray getUnlimitedDriverTariffPlans(Integer cityID) {
        JSONArray result = new JSONArray();
        for (DriverTariffPlan driverTariffPlan : driverTariffPlans) {
            if (driverTariffPlan.CityID.equals(cityID)) {
                result.put(driverTariffPlan.taximeterJSON());
            }
        }
        return result;
    }

    public QiwiNumber getQiwiNumber() {
        return curFormQiwiNumber;
    }

    public JSONObject getUnauthorizedData() {
        return unauthorizedData;
    }

}
