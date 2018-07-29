package me.shuza.rxformvalidation;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 *
 * :=  created by:  Shuza
 * :=  create date:  27-Jun-17
 * :=  (C) CopyRight Shuza
 * :=  www.shuza.ninja
 * :=  shuza.sa@gmail.com
 * :=  Fun  :  Coffee  :  Code
 *
 **/

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.etFirstName)
    EditText etFirstName;

    @BindView(R.id.etLastName)
    EditText etLastName;

    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    private String TAG = this.getClass().getSimpleName();

    private DisposableSubscriber<Boolean> disposableSubscriber = null;
    private Flowable<CharSequence> firstNameChangeObservable;
    private Flowable<CharSequence> lastNameChangeObservable;
    private Flowable<CharSequence> emailChangeObservable;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        firstNameChangeObservable = RxTextView.textChanges(etFirstName).skip(1).toFlowable(BackpressureStrategy.LATEST);
        lastNameChangeObservable = RxTextView.textChanges(etLastName).skip(1).toFlowable(BackpressureStrategy.LATEST);
        emailChangeObservable = RxTextView.textChanges(etEmail).skip(1).toFlowable(BackpressureStrategy.LATEST);

        combineLatestEvents();
    }

    private void combineLatestEvents() {
        disposableSubscriber = new DisposableSubscriber<Boolean>() {
            @Override
            public void onNext(Boolean isValidForm) {
                if (isValidForm) {
                    btnSubmit.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.blue));
                } else {
                    btnSubmit.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.gray));
                }
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "onError       ==/   " + t.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete       ==/    called");
            }
        };

        Flowable.combineLatest(firstNameChangeObservable, lastNameChangeObservable, emailChangeObservable,
                (firstName, lastName, email) -> {
                    boolean isValidFirstName = firstName != null && firstName.length() > 3;
                    if (!isValidFirstName) {
                        etFirstName.setError("Invalid first name");
                    }

                    boolean isValidLastName = lastName != null && lastName.length() > 3;
                    if (!isValidLastName) {
                        etLastName.setError("Invalid last name");
                    }

                    boolean isValidEmail = email != null && Validator.isValidEmailAddress(String.valueOf(email));
                    if (!isValidEmail) {
                        etEmail.setError("Invalid email addess");
                    }

                    return isValidFirstName && isValidLastName && isValidEmail;
                })
                .subscribe(disposableSubscriber);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        disposableSubscriber.dispose();
    }
}
