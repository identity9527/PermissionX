package com.example.permissionlibrary

import android.app.Activity
import androidx.fragment.app.FragmentActivity

/**
 *Build time：2020/6/23
 *Author：Tang
 *Des:
 */
object PermissionX {
    private const val TAG = "InvisibleFragment"

    fun request(activity:FragmentActivity,vararg permissions:String,callback: PermissionCallback){
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if(existedFragment!=null){
            existedFragment as InvisibleFragment
        }else{
            val invisibleFragment = InvisibleFragment()
            /**
             * commit：不会立即执行事务
             * 无法保证下一行返回的invisibleFragment已经被实例化
             * */
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        /***permissions：将一个数组转换为可变长度参数传递*/
        fragment.requestNow(callback,*permissions)
    }
}