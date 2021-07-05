package NguoiDung;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.appxemphim.MainActivity;
import com.example.appxemphim.R;
import com.example.appxemphim.databinding.ActivityLoginBinding;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    private String email = "", password = "";
    CallbackManager mcallbackManager;
    private static final String EMAIL = "email";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_);
        getSupportActionBar().hide();


        firebaseAuth = FirebaseAuth.getInstance();
        mcallbackManager = CallbackManager.Factory.create();
        setUpProgress();

    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.btndangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }

    private void validateData() {
        email = binding.txtEmail.getText().toString().trim();
        password = binding.txtPassword.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.txtEmail.setError("email nhập không đúng định dạng ");
        } else if (TextUtils.isEmpty(password)) {
            binding.txtPassword.setError("nhập mật khẩu của bạn");
        } else {
            firebaseSignIn();
        }
    }
    private void firebaseSignIn() {
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //login success;
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        String email = firebaseUser.getEmail();
                        Toast.makeText(LoginActivity.this, "Đăng nhập!\n" + email +" thành công !", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_LONG).show();
                    }
                });
    }
    private void openProfile() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
    private void setUpProgress()
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Vui Lòng Đợi ! ");
        progressDialog.setMessage("Đang Đăng Nhập !!!");
        progressDialog.setCanceledOnTouchOutside(false);

    }
    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null) {
            openProfile();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));

        }
        Glide.with(getBaseContext()).load(R.drawable.google).circleCrop().into(binding.btnLoginGoogle);
        Glide.with(getBaseContext()).load(R.drawable.facebook).circleCrop().into(binding.ViewbtnLoginFacebook);
        Glide.with(getBaseContext()).load(R.drawable.logomovieapp).transform(new CenterCrop(),new RoundedCorners(25)).into(binding.logoApp);
    }

}
