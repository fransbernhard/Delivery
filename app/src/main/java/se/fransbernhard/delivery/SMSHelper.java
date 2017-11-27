package se.fransbernhard.delivery;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by mrx on 2017-11-27.
 */

public class SMSHelper {
    private String phoneNumber;
    private String message;

    public SMSHelper(String phoneNumber, int orderNr) {
        this.phoneNumber = phoneNumber;
        message = "Order: "+orderNr+" har levererats.";
    }

    public Intent sendSMS() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + convertToNumber()));
        intent.putExtra("MESSAGE", message);
        return intent;
    }

    private int convertToNumber() {
        String[] noSpaces = phoneNumber.split(" ");
        String newNumber = "";
        for (String s:noSpaces)
            newNumber += s;
        return Integer.parseInt(newNumber);
    }

}
