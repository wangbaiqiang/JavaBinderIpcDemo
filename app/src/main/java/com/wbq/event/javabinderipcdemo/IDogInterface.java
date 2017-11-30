package com.wbq.event.javabinderipcdemo;

import android.os.Binder;
import android.os.IInterface;
import android.os.RemoteException;

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

public interface IDogInterface extends IInterface {
    public List<Dog> getDogList() throws RemoteException;
    public void addDog(Dog dog) throws RemoteException;
    static final String DESCRIPTOR="com.wbq.event.javabinderipcdemo.IDogInterface";
    static final int GET_DOG_LIST_TRANSACTION= Binder.FIRST_CALL_TRANSACTION;
    static final int ADD_DOG_TRANSACTION= Binder.FIRST_CALL_TRANSACTION+1;

}
