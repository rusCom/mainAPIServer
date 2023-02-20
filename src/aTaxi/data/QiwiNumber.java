package aTaxi.data;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static tools.MainUtils.JSONGetString;

public class QiwiNumber {
    String number;
    String publicKey;
    String secretKey;
    String themeCode;
    String paymentComment;

    public static QiwiNumber fromJSON(JSONObject data){
        QiwiNumber qiwiNumber = new QiwiNumber();
        qiwiNumber.number = JSONGetString(data, "number");
        qiwiNumber.publicKey = JSONGetString(data, "public_key");
        qiwiNumber.secretKey = JSONGetString(data, "secret_key");
        qiwiNumber.themeCode = JSONGetString(data, "theme_code");
        qiwiNumber.paymentComment = JSONGetString(data, "payment_comment");
        return qiwiNumber;
    }

    public String getLink(String callSign) {
        String comment = this.paymentComment + " " + callSign + ".";
        comment = callSign;
        return "https://oplata.qiwi.com/create?publicKey=" + this.publicKey
                + "&customFields[paySourcesFilter]=card&customFields[themeCode]=" + this.themeCode
                + "&noCache=true&comment=" + URLEncoder.encode(comment, StandardCharsets.UTF_8);
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getThemeCode() {
        return themeCode;
    }

    public String getPaymentComment() {
        return paymentComment;
    }
}
