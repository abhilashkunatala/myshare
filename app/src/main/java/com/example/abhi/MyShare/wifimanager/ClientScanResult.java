package com.example.abhi.MyShare.wifimanager;


public class ClientScanResult {

    private String IpAddr;

    private String HWAddr;

    private String Device;

    private boolean isReachable;

    public ClientScanResult(String ipAddress, String hWAddress, String device, boolean isReachable)
    {
        super();
                                                                                                                                                                                                                                                                                                                                                                     HWAddr = hWAddress;/*it is refereed to mac address i.e ethernet hardwareaddress*/
        Device = device;
        this.setReachable(isReachable);
    }

    public String getIpAddress()
    {
        return IpAddr;
    }


    public void setReachable(boolean isReachable)
    {
        this.isReachable = isReachable;
    }

    public boolean isReachable()
    {
        return isReachable;
    }
}