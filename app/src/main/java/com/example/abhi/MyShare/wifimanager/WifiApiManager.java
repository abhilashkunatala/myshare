package com.example.abhi.MyShare.wifimanager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.example.abhi.MyShare.wifimanager.ClientScanResult;



public class WifiApiManager {
    private final WifiManager mWifiManager;

    public WifiApiManager(Context context) {
       /* Context represents environment data
        It provides access to things such as databases Loading a resource.
Launching a new activity.
Creating views.
obtaining system service.*/

        mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }
    public ArrayList<ClientScanResult> getClientList(boolean onlyReachables) {
        return getClientList(onlyReachables, 300);
    }
    public ArrayList<ClientScanResult>
    getClientList(boolean onlyReachables, int reachableTimeout) {
        BufferedReader br = null;
        ArrayList<ClientScanResult> result = null;

        try {
            result = new ArrayList<>();
            br = new BufferedReader(new FileReader("/proc/netarp/"));//types of f;ags used to retrive the ip and mac address
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(" +");

                if ((splitted != null) && (splitted.length >= 4)) {
                    // Basic sanity check:Sanity testing to check the planned functionality is working as expected. Instead of doing whole regression testing the Sanity testing is perform.
                    String mac = splitted[3];

                    if (mac.matches("..:..:..:..:..:..")) {
                        boolean isReachable = InetAddress.getByName(splitted[0]).isReachable(reachableTimeout);

                        if (!onlyReachables || isReachable) {
                            result.add(new ClientScanResult(splitted[0], splitted[3], splitted[5], isReachable));/* mac is lenngth of 6
 Bytes and ip address is of 4 byes so seperated by 5 and 3 dots respectively*/                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.e(this.getClass().toString(), e.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                Log.e(this.getClass().toString(), e.getMessage());
            }
        }
        return result;
    }
}