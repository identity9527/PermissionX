package com.example.permissionlibrary

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 *Build time：2020/6/23
 *Author：Tang
 *Des:
 */
/**typealias:为任意类型指定别名*/
typealias PermissionCallback = (Boolean,List<String>) ->Unit

class InvisibleFragment : Fragment(){
    private var callback:PermissionCallback? = null

    fun requestNow(cb:PermissionCallback,vararg permissions:String){
        callback = cb
        /**
         * 无论是否授予权限，都会调用权限申请方法
         * */
       requestPermissions(permissions,1)
    }

    /**
     * 无论之前是否已授予权限，都会接收到回调
     * */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode==1){
            val deniedList = ArrayList<String>()
            for((index,result) in grantResults.withIndex()){
                if(result!=PackageManager.PERMISSION_GRANTED){
                    deniedList.add(permissions[index])
                }
            }
            /**调用回调方法*/
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted,deniedList) }
        }
    }
}