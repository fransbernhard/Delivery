package se.fransbernhard.delivery;

import android.telephony.SmsManager;

import java.util.ArrayList;

/**
 * Created by mrx on 2017-11-27.
 */

public class SMSHelper {
    private String phoneNumber;
    private String message;
    private SmsManager smsManager;
    private ArrayList<String> msgArray;
    final String serverNumber = "5554";

    public SMSHelper(String phoneNumber, int orderNr) {
        this.phoneNumber = phoneNumber;
        message = "Order: "+orderNr+" har levererats.";
        smsManager = SmsManager.getDefault();
        msgArray = smsManager.divideMessage(message);
    }

    public SMSHelper(int orderNr) {
        phoneNumber = serverNumber;
        message = "Order: "+orderNr+" har levererats.";
        smsManager = SmsManager.getDefault();
        msgArray = smsManager.divideMessage(message);
    }

    public void sendSMS() {
        if (phoneNumber.equals(serverNumber))
            smsManager.sendMultipartTextMessage(phoneNumber,null, msgArray, null, null);
        else
            smsManager.sendMultipartTextMessage((eraseSpaces(phoneNumber)),null, msgArray, null, null);
    }

    private String eraseSpaces(String oldNumber) {
        String[] noSpaces = oldNumber.split(" ");
        String newNumber = "";
        for (String s:noSpaces)
            newNumber += s;
        return newNumber;
    }

}
