package com.up.measurecopy.main.login

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.up.measurecopy.R
import com.up.measurecopy.manager.PreferenceManager
import com.up.measurecopy.manager.UserManager
import com.up.measurecopy.model.Response
import com.up.measurecopy.model.User
import com.up.measurecopy.util.CommonUtil
import com.up.measurecopy.util.DeviceUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.act_login.*

/**
 * Created by ywd on 2017/11/2.
 * 登录页面
 */
class LoginAct : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_login)

        setListener()
    }

    private fun setListener() {
        login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login -> {
                val fiedlMap: MutableMap<String, String> = mutableMapOf(Pair("timestamp", "" + System.currentTimeMillis()))
//                fiedlMap.put("username",userName.text.toString())
//                fiedlMap.put("userpass",password.text.toString())
                fiedlMap.put("username", "15110268600")
                fiedlMap.put("userpass", "123456")
                fiedlMap.put("api_version", "1")
                fiedlMap.put("device_type", "1")
                fiedlMap.put("device_id", DeviceUtil.getDeviceId(this))
                val sign = CommonUtil.makeSign(fiedlMap)
                fiedlMap.put("sign", sign)
                LoginPresenter().loginService.login(fiedlMap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Response<User>> {
                    override fun onNext(t: Response<User>) {
//                        Toast.makeText(this@LoginAct, t.data.avatar_url,Toast.LENGTH_SHORT).show()
                        t.data.login_state = true
                        UserManager.getInstance()?.saveUser(t.data)
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(this@LoginAct, "onError", Toast.LENGTH_SHORT).show()
                    }

                    override fun onComplete() {
                        Toast.makeText(this@LoginAct, "onComplete", Toast.LENGTH_SHORT).show()
                    }

                    override fun onSubscribe(d: Disposable) {
                        Toast.makeText(this@LoginAct, "onSubscribe", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }
    }
}