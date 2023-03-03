package com.evajj.ktnetwork.environment

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.Process
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.evajj.ktnetwork.R

/**
 * Author:wenjunjie
 * Date:2023/3/2
 * Time:下午2:13
 * Description:
 **/
class EnvironmentActivity :AppCompatActivity() {
    companion object {
        val INTERFACE_ENVIRONMENT_PREF_KEY = "interface_environment_type"
        val NETWORK_ENVIRONMENT_PREF_KEY = "network_environment_type"
        private var sCurrentInterfaceEnvironment :String? = ""
        private var sCurrentNetWorkEnvironment  :String? = ""
        fun getNetWorkType(application: Application):Int {
            val prefs = PreferenceManager.getDefaultSharedPreferences(application!!)
            val environment = prefs.getString(NETWORK_ENVIRONMENT_PREF_KEY, "0")
            return Integer.valueOf(environment!!)
        }
        fun isOfficialEnvironment(application: Application): Boolean {
            val prefs = PreferenceManager.getDefaultSharedPreferences(
                application!!
            )
            val environment = prefs.getString(INTERFACE_ENVIRONMENT_PREF_KEY, "1")
            return "1".equals(environment, ignoreCase = true)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_environment)
        //setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, MyPreferenceFragment())
            .commit()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        sCurrentInterfaceEnvironment =
            prefs.getString(INTERFACE_ENVIRONMENT_PREF_KEY, "1")
        sCurrentNetWorkEnvironment =
            prefs.getString(NETWORK_ENVIRONMENT_PREF_KEY, "0")
    }


    class MyPreferenceFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener{

        override fun onAttach(context: Context) {
            super.onAttach(context)
            requireActivity().onBackPressedDispatcher.addCallback(this) {
                val prefs = PreferenceManager.getDefaultSharedPreferences(context)
                val newInterfaceValue = prefs.getString(INTERFACE_ENVIRONMENT_PREF_KEY, "1")
                val newNetworkValue = prefs.getString(NETWORK_ENVIRONMENT_PREF_KEY, "0")
                if (!sCurrentInterfaceEnvironment.equals(
                        newInterfaceValue,
                        ignoreCase = true
                    )
                    || !EnvironmentActivity.sCurrentNetWorkEnvironment.equals(
                        newNetworkValue,
                        ignoreCase = true
                    )
                ) {
                    Process.killProcess(Process.myPid())
                } else {
                    requireActivity().finish()
                }
            }
        }
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            addPreferencesFromResource(R.xml.environment_preference)
            findPreference<Preference>(INTERFACE_ENVIRONMENT_PREF_KEY)!!.onPreferenceChangeListener =
                this
            findPreference<Preference>(NETWORK_ENVIRONMENT_PREF_KEY)!!.onPreferenceChangeListener =
                this
        }

        override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
            if (!INTERFACE_ENVIRONMENT_PREF_KEY.equals(newValue.toString(), ignoreCase = true)) {
                Toast.makeText(context, "您已经更改了接口环境，再您退出当前页面的时候APP将会重启切换环境！", Toast.LENGTH_SHORT)
                    .show()
            }
            if (!NETWORK_ENVIRONMENT_PREF_KEY.equals(newValue.toString(), ignoreCase = true)) {
                Toast.makeText(context, "您已经更改了网络环境，再您退出当前页面的时候APP将会重启切换环境！", Toast.LENGTH_SHORT)
                    .show()
            }
            return true
        }

    }



}