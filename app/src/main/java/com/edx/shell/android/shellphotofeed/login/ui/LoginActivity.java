package com.edx.shell.android.shellphotofeed.login.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.edx.shell.android.shellphotofeed.MainActivity;
import com.edx.shell.android.shellphotofeed.PhotoFeedApp;
import com.edx.shell.android.shellphotofeed.R;
import com.edx.shell.android.shellphotofeed.login.LoginPresenter;
import com.edx.shell.android.shellphotofeed.login.events.LoginEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    // Variables
    private PhotoFeedApp app;

    // Servicios
    private LoginPresenter loginPresenter;
    private SharedPreferences sharedPreferences;

    // Componentes
    @Bind(R.id.til_email)
    TextInputLayout tilEmail;
    @Bind(R.id.edt_email)
    EditText edtEmail;
    @Bind(R.id.til_pass)
    TextInputLayout tilPass;
    @Bind(R.id.edt_pass)
    EditText edtPass;
    @Bind(R.id.rel_container)
    RelativeLayout relMainContainer;
    @Bind(R.id.btn_signin)
    Button btnSignin;
    @Bind(R.id.btn_signup)
    Button btnSignup;
    @Bind(R.id.progressbar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        app = (PhotoFeedApp) getApplication();

//        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter = new LoginPresenter() {
            @Override
            public void onCreate() {

            }

            @Override
            public void onDestroy() {

            }

            @Override
            public void validateLogin(String email, String pass) {

            }

            @Override
            public void registerNewUser(String email, String pass) {

            }

            @Override
            public void onEventMainThread(LoginEvent loginEvent) {

            }
        };
        loginPresenter.onCreate();
        loginPresenter.validateLogin(null, null);
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.btn_signin)
    public void onClickBtnSignin() {
        String email = edtEmail.getText().toString().trim();
        Log.d("AndroidChat", email);
    }

    @OnClick(R.id.btn_signin)
    public void onClickBtnSignup() {
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_signin)
    @Override
    public void handleSignin() {
        tilPass.setError(null);
        loginPresenter.validateLogin(edtEmail.getText().toString(),
                edtPass.getText().toString());
    }

    @OnClick(R.id.btn_signup)
    @Override
    public void handleSignup() {
        loginPresenter.registerNewUser(edtEmail.getText().toString(), edtPass.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void loginError(String error) {
        edtPass.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin), error);
        tilPass.setError(msgError);
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(relMainContainer, R.string.login_notice_message_useradded, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void newUserError(String error) {
        edtPass.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signup), error);
        tilPass.setError(msgError);

    }

    @Override
    public void setUserEmail(String email) {
        if (email != null) {
            sharedPreferences.edit()
                    .putString(app.getEmailKey(), email)
                    .commit();
        }
    }

    private void setInputs(boolean enabled) {
        tilEmail.setEnabled(enabled);
        tilPass.setEnabled(enabled);
        btnSignin.setEnabled(enabled);
        btnSignup.setEnabled(enabled);
    }
}