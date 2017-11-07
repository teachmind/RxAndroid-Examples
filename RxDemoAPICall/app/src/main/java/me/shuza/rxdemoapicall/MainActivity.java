package me.shuza.rxdemoapicall;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * :=  created by:  Shuza
 * :=  create date:  11/5/2017
 * :=  (C) CopyRight Shuza
 * :=  www.shuza.me
 * :=  shuza.sa@gmail.com
 * :=  Fun  :  Coffee  :  Code
 **/

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvEmail)
    TextView tvEmail;

    @BindView(R.id.tvBlog)
    TextView tvBlog;

    @BindView(R.id.btnLoad)
    Button btnLoad;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading...");
    }

    @OnClick(R.id.btnLoad)
    public void loadStudentList(View view) {
        progressDialog.show();
        getDataFromAPI();
    }

    public void getDataFromAPI() {
        ApiService apiService = NetworkManager.createRetrofit().create(ApiService.class);
        apiService.getDataFromAPI()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponsePojo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponsePojo data) {
                        progressDialog.dismiss();
                        tvName.setText("Name : " + data.getName());
                        tvEmail.setText("Email : " + data.getEmail());
                        tvBlog.setText("Blog : " + data.getBlog());
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtil.printLogMessage("error response", t.getMessage());

                        progressDialog.dismiss();
                        tvName.setText("error :   " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
