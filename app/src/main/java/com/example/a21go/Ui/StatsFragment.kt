package com.example.a21go.Ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.a21go.Network.Response
import com.example.a21go.R
import com.example.a21go.Ui.Splash_Screen.Companion.readInfo
import com.example.a21go.ViewModel.StatsViewModel
import com.example.a21go.databinding.StatsFragmentBinding
import com.example.a21go.model.StatsModel
import kotlinx.coroutines.launch
import org.naishadhparmar.zcustomcalendar.CustomCalendar
import org.naishadhparmar.zcustomcalendar.OnNavigationButtonClickedListener
import org.naishadhparmar.zcustomcalendar.Property
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class StatsFragment :Fragment(), OnNavigationButtonClickedListener {
    private lateinit var customCalendar: CustomCalendar
    private lateinit var calender: Calendar
    private var _binding: StatsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var statsViewModel: StatsViewModel
    lateinit var items:List<StatsModel>
    var userid:String?=null
    var Date:Int?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StatsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        customCalendar = binding.customCalender
        lifecycleScope.launch {
            userid = readInfo("USERID")
        }
        statsViewModel.User_id.setValue(userid?.toInt())
        val dateFormat: DateFormat = SimpleDateFormat("d")
        val date = Date()
        Log.d("Month", dateFormat.format(date))
        Date=dateFormat.format(date).toInt()
        val descHashMap: HashMap<Any, Property> = HashMap()

        val defaultProperty = Property()

        defaultProperty.layoutResource = R.layout.default_view

        defaultProperty.dateTextViewResource = R.id.text_view

        descHashMap["default"] = defaultProperty


        // for present date
        val presentProperty = Property()
        presentProperty.layoutResource = R.layout.present_view
        presentProperty.dateTextViewResource = R.id.text_view3
        descHashMap["present"] = presentProperty

        // For absent
        val absentProperty = Property()
        absentProperty.layoutResource = R.layout.absent_view
        absentProperty.dateTextViewResource = R.id.text_view4
        descHashMap["absent"] = absentProperty


        customCalendar.setMapDescToProp(descHashMap)



        customCalendar.setOnNavigationButtonClickedListener(CustomCalendar.PREVIOUS, this)
        customCalendar.setOnNavigationButtonClickedListener(CustomCalendar.NEXT, this)




        return view
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onNavigationButtonClicked(
        whichButton: Int,
        newMonth: Calendar
    ): Array<MutableMap<Int, Any>?> {
        Log.i("Month", "on result${Calendar.DECEMBER}")
        val arr: Array<MutableMap<Int, Any>?> = arrayOfNulls<MutableMap<Int, Any>?>(2)
        when (newMonth[Calendar.MONTH]) {

            Calendar.JANUARY -> {
                arr[0] = HashMap()
                if(Calendar.JANUARY<Calendar.MONTH){
                    for (i in 1..31)
                    {
                        arr[0]?.set(i, "present")
                    }
                }
                else if (Calendar.JANUARY==Calendar.MONTH){
                    for(i in 1..Date!!){

                        arr[0]?.set(i, "present")
                    }

                    for(i in items)
                    {
                        val k=i.date.toString()
                        Log.d("hello", "hello")
                        Log.d("hello", i.date.toString())

                        if (i.month==Calendar.MONTH+1)
                            arr[0]?.set(k.toInt(), "absent")
                    }
                }
                for(i in items)
                {
                    if(i.month==1)
                    {
                        val k=i.date.toString()
                        arr[0]?.set(k.toInt(), "absent")
                    }
                }
            }
            Calendar.FEBRUARY -> {
                arr[0] = HashMap()
                if(Calendar.FEBRUARY<Calendar.MONTH){
                    for (i in 1..customCalendar.allViews.size)
                    {
                        arr[0]?.set(i, "present")
                    }
                }
                else if (Calendar.FEBRUARY==Calendar.MONTH){
                    for(i in 1..Date!!){

                        arr[0]?.set(i, "present")
                    }

                    for(i in items)
                    {
                        val k=i.date.toString()
                        Log.d("hello", "hello")
                        Log.d("hello", i.date.toString())

                        if (i.month==Calendar.MONTH+1)
                            arr[0]?.set(k.toInt(), "absent")
                    }
                }
                for(i in items)
                {
                    if(i.month==2)
                    {
                        val k=i.date.toString()
                        arr[0]?.set(k.toInt(), "absent")
                    }
                }
             }
            Calendar.MARCH -> {
                arr[0] = HashMap()
                if(Calendar.MARCH<Calendar.MONTH){
                    for (i in 1..31)
                    {
                        arr[0]?.set(i, "present")
                    }
                }
                else if (Calendar.MARCH==Calendar.MONTH){
                    for(i in 1..Date!!){

                        arr[0]?.set(i, "present")
                    }

                    for(i in items)
                    {
                        val k=i.date.toString()
                        Log.d("hello", "hello")
                        Log.d("hello", i.date.toString())

                        if (i.month==Calendar.MONTH+1)
                            arr[0]?.set(k.toInt(), "absent")
                    }
                }
                for(i in items)
                {
                    if(i.month==3)
                    {
                        val k=i.date.toString()
                        arr[0]?.set(k.toInt(), "absent")
                    }
                }
            }
            Calendar.APRIL -> {
                arr[0] = HashMap()
                if(Calendar.APRIL<Calendar.MONTH){
                    for (i in 1..30)
                    {
                        arr[0]?.set(i, "present")
                    }
                }
                else if (Calendar.APRIL==Calendar.MONTH){
                    for(i in 1..Date!!){

                        arr[0]?.set(i, "present")
                    }

                    for(i in items)
                    {
                        val k=i.date.toString()
                        Log.d("hello", "hello")
                        Log.d("hello", i.date.toString())

                        if (i.month==Calendar.MONTH+1)
                            arr[0]?.set(k.toInt(), "absent")
                    }
                }
                for(i in items)
                {
                    if(i.month==4)
                    {
                        val k=i.date.toString()
                        arr[0]?.set(k.toInt(), "absent")
                    }
                }
            }
            Calendar.MAY -> {
                arr[0] = HashMap()
                if(Calendar.MAY<Calendar.MONTH){
                    for (i in 1..31)
                    {
                        arr[0]?.set(i, "present")
                    }
                }
                else if (Calendar.MAY==Calendar.MONTH){
                    for(i in 1..Date!!){

                        arr[0]?.set(i, "present")
                    }

                    for(i in items)
                    {
                        val k=i.date.toString()
                        Log.d("hello", "hello")
                        Log.d("hello", i.date.toString())

                        if (i.month==Calendar.MONTH+1)
                            arr[0]?.set(k.toInt(), "absent")
                    }
                }
                for(i in items)
                {
                    if(i.month==5)
                    {
                        val k=i.date.toString()
                        arr[0]?.set(k.toInt(), "absent")
                    }
                }
                    }
            Calendar.JUNE -> {
                arr[0] = HashMap()
                if(Calendar.JUNE<Calendar.MONTH){
                    for (i in 1..30)
                    {
                        arr[0]?.set(i, "present")
                    }
                }
                else if (Calendar.JUNE==Calendar.MONTH){
                    for(i in 1..Date!!){

                        arr[0]?.set(i, "present")
                    }

                    for(i in items)
                    {
                        val k=i.date.toString()
                        Log.d("hello", "hello")
                        Log.d("hello", i.date.toString())

                        if (i.month==Calendar.MONTH+1)
                            arr[0]?.set(k.toInt(), "absent")
                    }
                }
                for(i in items)
                {
                    if(i.month==6)
                    {
                        val k=i.date.toString()
                        arr[0]?.set(k.toInt(), "absent")
                    }
                }
            }
            Calendar.JULY -> {
                arr[0] = HashMap()
                if(Calendar.JULY<Calendar.MONTH){
                    for (i in 1..31)
                    {
                        arr[0]?.set(i, "present")
                    }
                }
                else if (Calendar.JULY==Calendar.MONTH){
                    for(i in 1..Date!!){

                        arr[0]?.set(i, "present")
                    }

                    for(i in items)
                    {
                        val k=i.date.toString()
                        Log.d("hello", "hello")
                        Log.d("hello", i.date.toString())

                        if (i.month==Calendar.MONTH+1)
                            arr[0]?.set(k.toInt(), "absent")
                    }
                }
                for(i in items)
                {
                    if(i.month==7)
                    {
                        val k=i.date.toString()
                        arr[0]?.set(k.toInt(), "absent")
                    }
                }
            }
            Calendar.AUGUST -> {
                arr[0] = HashMap()
                if(Calendar.AUGUST<Calendar.MONTH){
                    for (i in 1..31)
                    {
                        arr[0]?.set(i, "present")
                    }
                }
                else if (Calendar.AUGUST==Calendar.MONTH){
                    for(i in 1..Date!!){

                        arr[0]?.set(i, "present")
                    }

                    for(i in items)
                    {
                        val k=i.date.toString()
                        Log.d("hello", "hello")
                        Log.d("hello", i.date.toString())

                        if (i.month==Calendar.MONTH+1)
                            arr[0]?.set(k.toInt(), "absent")
                    }
                }
                for(i in items)
                {
                    if(i.month==8)
                    {
                        val k=i.date.toString()
                        arr[0]?.set(k.toInt(), "absent")
                    }
                }
            }
            Calendar.SEPTEMBER -> {
                arr[0] = HashMap()
                if(Calendar.SEPTEMBER<Calendar.MONTH){
                    for (i in 1..30)
                    {
                        arr[0]?.set(i, "present")
                    }
                }
                else if (Calendar.SEPTEMBER==Calendar.MONTH){
                    for(i in 1..Date!!){

                        arr[0]?.set(i, "present")
                    }

                    for(i in items)
                    {
                        val k=i.date.toString()
                        Log.d("hello", "hello")
                        Log.d("hello", i.date.toString())

                        if (i.month==Calendar.MONTH+1)
                            arr[0]?.set(k.toInt(), "absent")
                    }
                }
                for(i in items)
                {
                    if(i.month==9)
                    {
                        val k=i.date.toString()
                        arr[0]?.set(k.toInt(), "absent")
                    }
                }
                 }
            Calendar.OCTOBER -> {
                arr[0] = HashMap()
                if(Calendar.OCTOBER<Calendar.MONTH){
                    for (i in 1..31)
                    {
                        arr[0]?.set(i, "present")
                    }
                }
                else if (Calendar.OCTOBER==Calendar.MONTH){
                    for(i in 1..Date!!){

                        arr[0]?.set(i, "present")
                    }

                    for(i in items)
                    {
                        val k=i.date.toString()
                        Log.d("hello", "hello")
                        Log.d("hello", i.date.toString())

                        if (i.month==Calendar.MONTH+1)
                            arr[0]?.set(k.toInt(), "absent")
                    }
                }
                for(i in items)
                {
                    if(i.month==10)
                    {
                        val k=i.date.toString()
                        arr[0]?.set(k.toInt(), "absent")
                    }
                }
            }
            Calendar.NOVEMBER -> {
                arr[0] = HashMap()
                if(Calendar.NOVEMBER<Calendar.MONTH){
                    for (i in 1..30)
                    {
                        arr[0]?.set(i, "present")
                    }
                }
                else if (Calendar.NOVEMBER==Calendar.MONTH){
                    for(i in 1..Date!!){

                        arr[0]?.set(i, "present")
                    }

                    for(i in items)
                    {
                        val k=i.date.toString()
                        Log.d("hello", "hello")
                        Log.d("hello", i.date.toString())

                        if (i.month==Calendar.MONTH+1)
                            arr[0]?.set(k.toInt(), "absent")
                    }
                }
                for(i in items)
                {
                    if(i.month==11)
                    {
                        val k=i.date.toString()
                        arr[0]?.set(k.toInt(), "absent")
                    }
                }
            }
            Calendar.DECEMBER -> {
                arr[0] = HashMap()
                if(Calendar.DECEMBER<Calendar.MONTH){
                    for (i in 1..31)
                    {
                        arr[0]?.set(i, "present")
                    }
                }
                else if (Calendar.DECEMBER==Calendar.MONTH){
                    for(i in 1..Date!!){

                        arr[0]?.set(i, "present")
                    }

                    for(i in items)
                    {
                        val k=i.date.toString()
                        Log.d("hello", "hello")
                        Log.d("hello", i.date.toString())

                        if (i.month==Calendar.MONTH+1)
                            arr[0]?.set(k.toInt(), "absent")
                    }
                }
                for(i in items)
                {
                    if(i.month==12)
                    {
                        val k=i.date.toString()
                        arr[0]?.set(k.toInt(), "absent")
                    }
                }
            }


        }


        return arr
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        statsViewModel = ViewModelProvider((context as FragmentActivity?)!!)[StatsViewModel::class.java]

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statsViewModel.SubmitStats()
        statsViewModel.statsResult.observe(viewLifecycleOwner, {
            when (it) {
                is Response.Success -> {

                    items= it.data!!
                    val dateHashmap: HashMap<Int, Any> = HashMap()


                    calender = Calendar.getInstance()



                    for(i in 1..Date!!){


                        dateHashmap[i] = "present"
                    }

                    for (j in it.data){
                        val k=j.date.toString()
                        Log.d("hello", "hello")
                        Log.d("hello", j.date.toString())

                        if (j.month==Calendar.MONTH+1)
                         dateHashmap[k.toInt()] = "absent"

                    }
                    customCalendar.setDate(calender, dateHashmap)
                }
                is Response.Error -> {
                    Toast.makeText(
                        context,
                        it.errorMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }


            }
        })
    }
}