package com.wbq.event.javabinderipcdemo;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;

/**
 * Created by wangbaiqiang on 2017/11/21.
 * email 1036607309@qq.com
 * ----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 */

public abstract class AppDogServiceStub extends Binder implements IDogInterface{

    public AppDogServiceStub() {
        Log.e("wbq","stub.onTransact"+"stub 构造方法执行");
        this.attachInterface(this,DESCRIPTOR);
    }
// aidl文件生成的stub存根 会有一个asInterface（）方法
// 我们自己实现的时候可以不用，因为我们就是跨进程的不用判断是否是跨进程的那一步操作

    @Override
    public IBinder asBinder() {
        Log.e("wbq","stub.onTransact"+"asbinder 执行了！！");
        return this;
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch(code){
             case INTERFACE_TRANSACTION:{
                 Log.e("wbq","stub.onTransact"+INTERFACE_TRANSACTION);
                reply.writeString(DESCRIPTOR);
                return true;
             }
             case GET_DOG_LIST_TRANSACTION:{
                 Log.e("wbq","stub.onTransact"+"get dog list execute");
                data.enforceInterface(DESCRIPTOR);
                 List<Dog> _result = this.getDogList();
                 reply.writeNoException();
                 reply.writeTypedList(_result);
                 return true;
             }
             case ADD_DOG_TRANSACTION:{
                 Log.e("wbq","stub.onTransact"+"add dog execute");
                 data.enforceInterface(DESCRIPTOR);
                com.wbq.event.javabinderipcdemo.Dog _arg0;
                if (0!=data.readInt()){
                    _arg0=Dog.CREATOR.createFromParcel(data);
                }else {
                    _arg0=null;
                }
                    this.addDog(_arg0);
                reply.writeNoException();
                return true;
             }
        }
        return super.onTransact(code, data, reply, flags);
    }
}
