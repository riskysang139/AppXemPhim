package NguoiDung;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.appxemphim.MainActivity;
import com.example.appxemphim.R;
import com.example.appxemphim.databinding.ActivitySignupBinding;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    private FirebaseAuth firebaseAuth;
    private String email="",password="",passwordAccuray="";
    GoogleSignInClient googleSignInClient;
    ProgressDialog progressDialog;
    static final int RC_SIGN_IN = 100;
    static final String tag = "GOOGLE_SIGN_IN_TAG";

    CallbackManager mcallbackManager;
    static final String TAG = "FACEBOOk_SIGN_IN_TAG";
    private static final String EMAIL = "email";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_signup);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        getSupportActionBar().hide();

        firebaseAuth=FirebaseAuth.getInstance();

        //configure progress dialog
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Vui lòng đợi !");
        progressDialog.setMessage("Đang tạo tài khoản cho bạn !!!");
        progressDialog.setCanceledOnTouchOutside(false);

        Glide.with(getBaseContext()).load(R.drawable.google).circleCrop().into(binding.btnLoginGoogle);
        Glide.with(getBaseContext()).load(R.drawable.facebook).circleCrop().into(binding.ViewbtnLoginFacebook);
        Glide.with(getBaseContext()).load(R.drawable.logomovieapp).circleCrop().into(binding.logoApp);
        mcallbackManager = CallbackManager.Factory.create();
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btndangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    private void validateData() {
        email=binding.txtEmail.getText().toString().trim();
        password=binding.txtPassword.getText().toString().trim();
        passwordAccuray=binding.txtPasswordAccuracy.getText().toString().trim();
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            binding.txtEmail.setError("Định dạng email không phù hợp");
        }
        else if(TextUtils.isEmpty(password))
        {
            binding.txtPassword.setError("Mật khẩu không được để trống");
        }
        else if(password.length()<8)
        {
            binding.txtEmail.setError("Mật khẩu phải lớn hơn 8 chữ số");
        }
        else if(password.compareTo(passwordAccuray)!=0)
        {
            binding.txtEmail.setError("Mật khẩu và xác nhận mật khẩu không khớp");
        }
        else
        {
            firebaseSignUp();
        }
    }



    private void firebaseSignUp() {
        //show progress
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            //signup success
                            progressDialog.dismiss();
                            //get usserr info
                            FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                            String email=firebaseUser.getEmail();
                            Toast.makeText(SignUpActivity.this,"Tạo tài khoản \n"+email +" thành công !!!",Toast.LENGTH_LONG).show();

                            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                            finish();
                        }
                    })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //sign up failed
                        progressDialog.dismiss();
                        Toast.makeText(SignUpActivity.this,"Tài khoản đã tồn tại ! Vui lòng thử lại",Toast.LENGTH_LONG).show();

                    }
                });
    }

}
