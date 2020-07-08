package p;

import android.util.Log;

import java.lang.ref.WeakReference;

import m.LoginM;
import v.Login;

public class LoginP {

    LoginM loginM;
    WeakReference<Login> weakReference;
    public LoginP(Login login){
        this.loginM=new LoginM();
        this.weakReference=new WeakReference<Login>(login);
    }
    public void loginP(){
        Login login=(Login) weakReference.get();
        String pass=login.getPassWord();
        String name=login.getUserName();
        login=null;
        loginM.login(name, pass, new LoginM.Login() {
            @Override
            public void getLogin(String s) {
                Log.e("BACK",s);
                weakReference.get().loginSucc();
            }
        });
    }

}
