package com.openxc.openxcstarter;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.openxcplatform.openxcstarter.R;
import com.openxc.VehicleManager;
import com.openxc.measurements.Measurement;
import com.openxc.measurements.LongAccel;
import com.openxc.measurements.VehicleSpeed;
import com.openxc.measurements.Altitude;

public class StarterActivity extends Activity {
    private static final String TAG = "StarterActivity";

    private VehicleManager mVehicleManager;
    private TextView mGradeView;
    private TextView mAltView;
    
    private double grade = 0.0;
    private int altitude = 0;
    private double accel = 0.0;
    private double speed = 0.0;
    private double p_speed = 0.0;
    IIRFilter grade_filter = new IIRFilter(0.95);

    Timer timer;
    TimerTask timerTask;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);
        // grab a reference to the engine speed text object in the UI, so we can
        // manipulate its value later from Java code
        mGradeView = (TextView) findViewById(R.id.txt_grade);
        mAltView = (TextView) findViewById(R.id.txt_alt);
    }

    @Override
    public void onPause() {
        super.onPause();
        // When the activity goes into the background or exits, we want to make
        // sure to unbind from the service to avoid leaking memory
        if(mVehicleManager != null) {
            Log.i(TAG, "Unbinding from Vehicle Manager");
            // Remember to remove your listeners, in typical Android
            // fashion.
            mVehicleManager.removeListener(LongAccel.class,
                    mAccelListener);
            mVehicleManager.removeListener(VehicleSpeed.class,
                    mSpeedListener);
            mVehicleManager.removeListener(Altitude.class,
                    mAltitudeListener);
            unbindService(mConnection);
            mVehicleManager = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // When the activity starts up or returns from the background,
        // re-connect to the VehicleManager so we can receive updates.
        if(mVehicleManager == null) {
            Intent intent = new Intent(this, VehicleManager.class);
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        }
        startTimer();
    }
    
    public void startTimer() {
    	timer = new Timer();
    	initializeTimerTask();
    	timer.schedule(timerTask, 10, 100);
    }
    
    public void stopTimerTask(View v) {
    	if (timer != null) {
    		timer.cancel();
    		timer = null;
    	}
    }
    
    public void initializeTimerTask() {
    	
    	timerTask = new TimerTask() {
	    		public void run() {
	    			grade = grade_filter.update(
	    			     	(accel -
	    				    (speed - p_speed)/0.1/3.6))/9.8;
	    			 
	    			p_speed = speed;
	    			updateTV();
	    		}
    	};
    }
    
    public void updateTV() {
    	runOnUiThread(new Runnable() {
    		@Override
    		public void run() {
    			mGradeView.setText(String.format("%.4f", grade));
    			
    			mAltView.setText(String.format("%d", altitude));
    		}
    	});
    }
    
    

    /* This is an OpenXC measurement listener object - the type is recognized
     * by the VehicleManager as something that can receive measurement updates.
     * Later in the file, we'll ask the VehicleManager to call the receive()
     * function here whenever a new EngineSpeed value arrives.
     */
    LongAccel.Listener mAccelListener = new LongAccel.Listener() {
        @Override
        public void receive(Measurement measurement) {
        	accel = ((LongAccel) measurement).getValue().doubleValue();
        }
    };
    
    VehicleSpeed.Listener mSpeedListener = new VehicleSpeed.Listener() {
        @Override
        public void receive(Measurement measurement) {
        	speed = ((VehicleSpeed) measurement).getValue().doubleValue();
        }
    };

    Altitude.Listener mAltitudeListener = new Altitude.Listener() {
        @Override
        public void receive(Measurement measurement) {
        	altitude = (int) ((Altitude) measurement).getValue().doubleValue();
        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        // Called when the connection with the VehicleManager service is
        // established, i.e. bound.
        public void onServiceConnected(ComponentName className,
                IBinder service) {
            Log.i(TAG, "Bound to VehicleManager");
            // When the VehicleManager starts up, we store a reference to it
            // here in "mVehicleManager" so we can call functions on it
            // elsewhere in our code.
            mVehicleManager = ((VehicleManager.VehicleBinder) service)
                    .getService();

            // We want to receive updates whenever the EngineSpeed changes. We
            // have an EngineSpeed.Listener (see above, mSpeedListener) and here
            // we request that the VehicleManager call its receive() method
            // whenever the EngineSpeed changes
            mVehicleManager.addListener(LongAccel.class, mAccelListener);
            mVehicleManager.addListener(VehicleSpeed.class, mSpeedListener);
            mVehicleManager.addListener(Altitude.class, mAltitudeListener);
        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {
            Log.w(TAG, "VehicleManager Service  disconnected unexpectedly");
            mVehicleManager = null;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.starter, menu);
        return true;
    }
}
