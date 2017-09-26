package me.shuza.rxtimerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.LongConsumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvLog)
    TextView tvLog;

    private DisposableSubscriber<Long> subscriberInterval;
    private DisposableSubscriber<Long> subscriberDelayInterval;

    private int POLL_INTERVAL = 2;
    private int DELAY_TIME = 10;
    private int INTERVAL_COUNT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnInterval)
    public void startTimerInterval(View view) {
        if (subscriberInterval != null && !subscriberInterval.isDisposed()) {
            subscriberInterval.dispose();
        }
        subscriberInterval = new DisposableSubscriber<Long>() {
            @Override
            public void onNext(Long aLong) {
                addLogMessage("Timer interval:  " + TimeUtil.getCurrentTime());
            }

            @Override
            public void onError(Throwable t) {
                addLogMessage("ERROR Timer interval: " + t.getMessage());
            }

            @Override
            public void onComplete() {
                addLogMessage("Timer interval completed !!");
            }
        };
        addLogMessage("START 2s timer interval...");
        Flowable.interval(POLL_INTERVAL, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subscriberInterval);
    }

    @OnClick(R.id.btnDelayInterval)
    public void startDelayTimer(View view) {
        if (subscriberDelayInterval != null && !subscriberDelayInterval.isDisposed()) {
            subscriberDelayInterval.dispose();
        }
        subscriberDelayInterval = new DisposableSubscriber<Long>() {
            @Override
            public void onNext(Long aLong) {
                addLogMessage("Delay Timer interval: " + TimeUtil.getCurrentTime());
            }

            @Override
            public void onError(Throwable t) {
                addLogMessage("ERROR delay timer: " + t.getMessage());
            }

            @Override
            public void onComplete() {
                addLogMessage("Delay timer completed !!");
            }
        };

        addLogMessage("START timer interval after " + DELAY_TIME + "s !!");

        Flowable.interval(DELAY_TIME, POLL_INTERVAL, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subscriberDelayInterval);
    }

    @OnClick(R.id.btnClear)
    public void clearLogMessage(View view) {
        tvLog.setText("");
    }

    @OnClick(R.id.btnStop)
    public void stopAllTimer(View view) {
        if (subscriberInterval != null && !subscriberInterval.isDisposed()) {
            subscriberInterval.dispose();
            addLogMessage("STOP Timer interval!!");
        }
        if (subscriberDelayInterval != null && !subscriberDelayInterval.isDisposed()) {
            subscriberDelayInterval.dispose();
            addLogMessage("STOP delay timer!!");
        }
    }


    public void addLogMessage(String message) {
        tvLog.setText(tvLog.getText().toString() + "\n" + message);
    }
}
