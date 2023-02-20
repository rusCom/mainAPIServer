package tools.profileLoginCode;

import tools.MainUtils;

import java.util.ArrayList;
import java.util.List;

public class ProfileLoginCode {
    private static volatile ProfileLoginCode profileLoginCode;
    List<ProfileLoginCodeAttempt> profileLoginCodeAttempts;
    Integer timeout;


    public static ProfileLoginCode getInstance() {
        ProfileLoginCode localInstance = profileLoginCode;
        if (localInstance == null) {
            synchronized (ProfileLoginCode.class) {
                localInstance = profileLoginCode;
                if (localInstance == null) {
                    profileLoginCode = localInstance = new ProfileLoginCode();
                }
            }
        }
        return localInstance;
    }

    public ProfileLoginCode() {
        this.profileLoginCodeAttempts = new ArrayList<>();
        this.timeout = Integer.valueOf(MainUtils.getInstance().getPropertyString("profileLoginCode.timeout"));
    }

    public Integer nextAttempt(Integer time){
        return Math.abs(this.timeout - time);
    }

    ProfileLoginCodeAttempt getLastByPhone(String phone){
        ProfileLoginCodeAttempt profileLoginCodeAttempt = null;
        for (ProfileLoginCodeAttempt profileLoginCodeAttemptElement : this.profileLoginCodeAttempts){
            if (profileLoginCodeAttemptElement.phone.equals(phone)){
                profileLoginCodeAttempt = profileLoginCodeAttemptElement;
            }

        }
        return profileLoginCodeAttempt;
    }

    /// Возвращает через какое время можно попробовать еще раз запросить код. Если возвращает 0, то код можно запрашивать
    public Integer check(String phone, String remoteAddress) {
        Integer result = 0;
        ProfileLoginCodeAttempt profileLoginCodeAttempt = getLastByPhone(phone);
        if (profileLoginCodeAttempt != null){
            result = profileLoginCodeAttempt.isPassedTime(this.timeout);
        }

        if (result.equals(0)){
            profileLoginCodeAttempts.add(new ProfileLoginCodeAttempt(phone, remoteAddress));
        }

        return result;
    }
}
