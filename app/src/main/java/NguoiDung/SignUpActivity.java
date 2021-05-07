package NguoiDung;

import android.app.ProgressDialog;
import android.content.Intent;
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

        getSupportActionBar().hide();
        FacebookSdk.sdkInitialize(getApplicationContext());
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        firebaseAuth=FirebaseAuth.getInstance();

        //configure progress dialog
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Vui lòng đợi !");
        progressDialog.setMessage("Đang tạo tài khoản cho bạn !!!");
        progressDialog.setCanceledOnTouchOutside(false);

        Glide.with(getBaseContext()).load(R.drawable.google).circleCrop().into(binding.btnLoginGoogle);
        Glide.with(getBaseContext()).load(R.drawable.facebook).circleCrop().into(binding.ViewbtnLoginFacebook);
        Glide.with(getBaseContext()).load(R.drawable.logomovieapp).circleCrop().into(binding.logoApp);

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        binding.btnLoginFacebook.setReadPermissions(Arrays.asList("public_profile", "email","user_photos"));
        mcallbackManager = CallbackManager.Factory.create();
        binding.ViewbtnLoginFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.ViewbtnLoginFacebook)
                {
                    binding.btnLoginFacebook.performClick();
                }
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnLoginFacebook.registerCallback(mcallbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "on Errror" + error.getMessage());
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
            binding.txtPassword.setError("Nhập mật khẩu của bạn ");
        }
        else if(password.length()<8)
        {
            binding.txtEmail.setError("Mật khẩu phải lớn hơn 8 chữ số");
        }
        else if(password.compareTo(passwordAccuray)!=0)
        {
            binding.txtEmail.setError("Mật khẩu và xác nhận mật khẩu phải giống nhau");
        }
        else
        {
            firebaseSignUp();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mcallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Log.d(tag, "on Acti");
            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = accountTask.getResult(ApiException.class);
                firebaseAuthWithGoogleAccount(account);
            } catch (Exception e) {
                Log.d(tag, "on Acti for result");
            }
        }
    }

    private void firebaseAuthWithGoogleAccount(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        String uid = firebaseUser.getUid();
                        String email = firebaseUser.getEmail();

                        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                        finish();
                    }
                });
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
                            Toast.makeText(SignUpActivity.this,"Account created\n"+email,Toast.LENGTH_LONG).show();

                            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                            finish();
                        }
                    })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //sign up failed
                        Toast.makeText(SignUpActivity.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Log.d(TAG, "signInWithCredential:success"+user.getPhotoUrl());

                            openProfile();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void openProfile() {
        startActivity(new Intent(SignUpActivity.this,MainActivity.class));
        finish();
    }
}
