package tools.profileLoginCode;

public class ProfileLoginCodeAttempt {
    String phone;
    String remoteAddress;
    long timeStamp;

    public ProfileLoginCodeAttempt(String phone, String remoteAddress) {
        this.phone = phone;
        this.remoteAddress = remoteAddress;
        this.timeStamp = System.currentTimeMillis();
    }

    Integer isPassedTime(Integer timeout){
        long absMillis = Math.abs(System.currentTimeMillis() - this.timeStamp);
        int absSek = Math.toIntExact(absMillis / 1000);

        if (absSek > timeout)return 0;
        return absSek;

    }

    Integer passedTime(){
        long absMillis = Math.abs(System.currentTimeMillis() - this.timeStamp);
        int absSek = Math.toIntExact(absMillis / 1000);
        return absSek;
    }

    Integer between(ProfileLoginCodeAttempt to){
        long absMillis = Math.abs(to.timeStamp - this.timeStamp);
        int absSek = Math.toIntExact(absMillis / 1000);
        return absSek;
    }
}
