package PP;

import busInterface.IPP_Out;

import java.util.Timer;
import java.util.TimerTask;

public class Integration {
    public static final int BUS_SAMPLING_TIME_MS = 20;


    private final int _busReadPeriodMs;
    private final IPP_Out _ppOut;
    private final PP[] _pps;
    private final Timer _messagePump;

    private boolean _isConnected;

    public Integration(IPP_Out ppOut, int busReadPeriodMs, PP... pps) {
        _ppOut = ppOut;
        _pps = pps;
        _messagePump = new Timer();
        _busReadPeriodMs = busReadPeriodMs;
    }

    public void Connect(){
        if(_isConnected)
            return;

        _messagePump.schedule(new TimerTask() {
            @Override
            public void run() {
                Signal();
            }
        }, _busReadPeriodMs);
        _isConnected = true;
    }

    public void Disconnect(){
        _messagePump.cancel();
        _isConnected = false;
        _ppOut.setParkingFoundOnLeft(false);
        _ppOut.setParkingFoundOnRight(false);
    }

    private void Signal(){
        for(PP m : _pps)
            m.Signal();
    }

    public boolean IsConnected() {
        return _isConnected;
    }

    public int GetBusReadPeriodMs() {
        return _busReadPeriodMs;
    }
}
