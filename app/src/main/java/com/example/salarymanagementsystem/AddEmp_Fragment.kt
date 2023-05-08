package com.example.salarymanagementsystem

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_MUTABLE
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Calendar


class AddEmp_Fragment : Fragment() {

    var isImagePicked:Boolean = false
    private lateinit var imgToStore: Bitmap
    var js:JobScheduler?=null
    lateinit var name:EditText
    lateinit var tp:TimePicker
    lateinit var dp:DatePicker
    lateinit var list:ArrayList<String>
    lateinit var desc:EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_emp_, container, false)


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn=view.findViewById<Button>(R.id.addbtn)

        name=view.findViewById(R.id.name)
        desc=view.findViewById(R.id.desc)

        tp=view.findViewById(R.id.tp)
        dp=view.findViewById(R.id.date_Picker)

        val sharedPreferences=activity?.getSharedPreferences("tasks",Context.MODE_PRIVATE)
        list=loadTasks()


        var item=""

        btn.setOnClickListener {


            item="Name : "+name.text.toString()+" \n\nDescription : "+desc.text.toString()+"\n"
            list.add(item)
            savedata(list)
            val calender:Calendar=Calendar.getInstance()
            if(Build.VERSION.SDK_INT>=23){
                calender.set(
                    dp.year,
                    dp.month,
                    dp.dayOfMonth,
                    tp.hour,
                    tp.minute,
                    0
                )
            }else{
                calender.set(
                    calender.get(Calendar.YEAR),
                    calender.get(Calendar.MONTH),
                    calender.get(Calendar.DAY_OF_MONTH),
                    tp.currentHour,
                    tp.currentMinute,
                    0
                )
            }
            setAlarm(calender.timeInMillis)

            js= context?.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            val componentName = ComponentName(
                requireContext(),
                NotificationJob::class.java)
            val builder = JobInfo.Builder(123, componentName)
            builder.setMinimumLatency(5000)
            //builder.setOverrideDeadline(10000)
            builder.setPersisted(true)
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            builder.setRequiresCharging(false)
            js!!.schedule(builder.build())
            Toast.makeText(context,"The task is scheduled", Toast.LENGTH_SHORT).show()
        }


    }

    private fun savedata(list: ArrayList<String>) {
        val sharedPreferences = activity?.getSharedPreferences("tasks", Context.MODE_PRIVATE)
        val editor=sharedPreferences?.edit()
        val gson=Gson()
        val jsonStr=gson.toJson(list)
        editor?.putString("tasklist",jsonStr)
        editor?.apply()
    }

    private fun setAlarm(timeInMillis: Long) {
        val alarmManager= activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent=Intent(context,MyAlarm::class.java)
        val pendingIntent= PendingIntent.getBroadcast(context,0,intent,FLAG_MUTABLE )
        alarmManager.set(AlarmManager.RTC,timeInMillis,pendingIntent)
        Toast.makeText(context, "Alarm is set for the task", Toast.LENGTH_SHORT).show()
    }
    class MyAlarm : BroadcastReceiver() {
        override fun onReceive(
            context: Context,
            intent: Intent)
        {
            var mp = MediaPlayer.create(context, R.raw.alarm)
            Log.d("Hello","repeating alarm")
            mp.start()
            Toast.makeText(context, "Alarm Ringing", Toast.LENGTH_LONG).show()
        }
    }

    private fun loadTasks(): ArrayList<String> {
        val sharedPreferences=activity?.getSharedPreferences("tasks",Context.MODE_PRIVATE)
        var jsonString=sharedPreferences?.getString("tasklist","")
        val gson=Gson()
        val type= object:TypeToken<ArrayList<String>>(){}.type
        var list=gson.fromJson<ArrayList<String>>(jsonString,type)
        if(list==null){
            list=ArrayList<String>()
        }
        return list
    }
}