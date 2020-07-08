package http;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

public class HttpUtils {

    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            login.getLogin("账户密码正确");
        }
    };

    public Login login;

    interface Login{
        public void getLogin(String s);
    }

    public void login(String name,String pass,Login login){
        this.login=login;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();

                message.obj = "子线程发送的消息Hi~Hi";

                handler .sendMessage(message);

            }
        }).start();

    }
}
