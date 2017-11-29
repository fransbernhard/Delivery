package se.fransbernhard.delivery;

import android.telephony.SmsManager;

/**
 * Created by mrx on 2017-11-27.
 */

public class SMSHelper {
    private String phoneNumber;
    private String message;
    private SmsManager smsManager;
    final String serverNumber = "0723227250";

    public SMSHelper(String phoneNumber, int orderNr) {
        this.phoneNumber = phoneNumber;
        message = "Order: "+orderNr+" har levererats.";
        smsManager = SmsManager.getDefault();
    }

    public SMSHelper(int orderNr) {
        phoneNumber = serverNumber;
        message = "Order: "+orderNr+" har levererats.";
        smsManager = SmsManager.getDefault();
    }

    public void sendSMS() {
        if (phoneNumber.equals(serverNumber))
            smsManager.sendTextMessage(phoneNumber,null, message, null, null);
        else
            smsManager.sendTextMessage(eraseSpaces(phoneNumber),null, message, null, null);
    }

    private String eraseSpaces(String oldNumber) {
        String[] noSpaces = oldNumber.split(" ");
        String newNumber = "";
        for (String s:noSpaces)
            newNumber += s;
        return newNumber;
    }

}
