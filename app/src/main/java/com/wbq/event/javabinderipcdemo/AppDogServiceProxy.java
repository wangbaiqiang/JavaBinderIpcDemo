package com.wbq.event.javabinderipcdemo;

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

public class AppDogServiceProxy implements IDogInterface{
    private IBinder remote;

    public AppDogServiceProxy(IBinder remote) {
        Log.e("wbq","Proxy."+"代理的构造方法执行 得到远程的引用对象");
        this.remote = remote;
    }

    /**
     * 这个方法其实没必要 我写是在看到老罗的android之旅硬解码转软解码的代码中实现的实际是没有的
     * @param obj
     * @return
     */
    public static IDogInterface asInterface(IBinder obj){
        if (obj==null){
            return null;
        }
        return new AppDogServiceProxy(obj);
    }
    public String getInterfaceDescriptor(){
        Log.e("wbq","Proxy."+"getInterfaceDescriptor 拿到descriptor");
        return DESCRIPTOR;
    }
    @Override
    public List<Dog> getDogList() throws RemoteException {
        Log.e("wbq","Proxy."+"getDogList");
        Parcel _data=Parcel.obtain();
        Parcel _reply=Parcel.obtain();
        List<Dog> _result;
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            remote.transact(GET_DOG_LIST_TRANSACTION,_data,_reply,0);
            _reply.readException();
            _result=_reply.createTypedArrayList(Dog.CREATOR);
        } finally {
            _data.recycle();
            _reply.recycle();
        }
        return _result;
    }

    @Override
    public void addDog(Dog dog) throws RemoteException {
        Log.e("wbq","Proxy."+"addDog");
        Parcel _data=Parcel.obtain();
        Parcel _reply=Parcel.obtain();

        try{
            _data.writeInterfaceToken(DESCRIPTOR);
            if(dog!=null){
                _data.writeInt(1);
                dog.writeToParcel(_data,0);
            }else {
                _data.writeInt(0);
            }
            remote.transact(ADD_DOG_TRANSACTION,_data,_reply,0);
            _reply.readException();
        }finally {
            _data.recycle();
            _reply.recycle();
        }
    }

    @Override
    public IBinder asBinder() {
        return remote;
    }
}
