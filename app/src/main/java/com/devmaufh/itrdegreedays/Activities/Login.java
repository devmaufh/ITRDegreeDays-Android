package com.devmaufh.itrdegreedays.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.devmaufh.itrdegreedays.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity {
    //Login activity
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth auth;
    SignInButton btn_signGoogle;
    Button btnsignGuest;
    int GOOGLE_SIGNIN=123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        updateUI(user);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindUI();
        btn_signGoogle.setOnClickListener(v->SignInGoogle()); //Set on click with a lambda expression
        btnsignGuest.setOnClickListener(v->GuestLogin());
    }
    private void bindUI(){
        btn_signGoogle=(SignInButton)findViewById(R.id.login_btnIniGoogle);
        btnsignGuest=(Button)findViewById(R.id.login_btnInvitado);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);
    }
    private void SignInGoogle(){
        Intent intent=mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent,GOOGLE_SIGNIN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GOOGLE_SIGNIN){
            Task<GoogleSignInAccount> task= GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account=task.getResult(ApiException.class);
                if(account!=null){
                    //Request to firebase
                    FirebaseAuthWithGoogle(account);
                }
            }catch(ApiException e){

            }
        }
    }
    private void FirebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d("Firebaselogin","FirebaseAuth with Google"+account.getId());
        AuthCredential authCredential= GoogleAuthProvider
                .getCredential(account.getIdToken(),null);
        auth.signInWithCredential(authCredential)
                .addOnCompleteListener(this, task->{
                    if(task.isSuccessful()){
                        Log.d("Firebaselogin","Signed successfull");
                        Toast.makeText(this, "Login correcto", Toast.LENGTH_SHORT).show();
                        FirebaseUser user=auth.getCurrentUser();
                        updateUI(user);
                    }else{
                        Log.d("Firebaselogin","Signed Error");
                        Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }

                });
    }
    private void GuestLogin(){
        auth.signInAnonymously()
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("Anonimo", "signInAnonymously:success");
                            FirebaseUser user=auth.getCurrentUser();
                            updateUI(user);
                        }else{
                            Log.d("Anonimo", "signInAnonymously:ERROR");
                            Toast.makeText(Login.this, "Error ingresando como invitado", Toast.LENGTH_SHORT).show();
                            updateUI(null);

                        }
                    }
                });
    }
    private void updateUI(FirebaseUser user){
        if(user!=null){
            Toast.makeText(this, "Updated ui"+user.getDisplayName(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Updated ui"+user.getProviderId(), Toast.LENGTH_SHORT).show();
            Intent main= new Intent(this, MainActivity.class);
            main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(main);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        //Check if login
    }
}
