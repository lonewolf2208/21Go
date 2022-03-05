package com.example.a21go.Ui

import android.app.AlertDialog
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a21go.Adapters.RecyclerAdapterHabits
import com.example.a21go.R
import com.example.a21go.databinding.FragmentHomePageBinding
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.CountDownTimer
import android.os.Environment
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import com.example.a21go.Activity.HomePageActivity
import com.example.a21go.Activity.MainActivity
import com.example.a21go.Adapters.RecyclerAdapterWallapers
import com.example.a21go.Network.Response
import com.example.a21go.Repository.HomePageRepo
import com.example.a21go.Repository.RelapseRepo
import com.example.a21go.Repository.getWallpapersRepo
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class HomePageFragment : Fragment() {
    var downloadId:Long=0
    lateinit var binding:FragmentHomePageBinding
    private var layoutManager: RecyclerView.LayoutManager?=null
    lateinit var adapter: RecyclerAdapterHabits
    private var layoutManagerWallpapers: RecyclerView.LayoutManager?=null
    lateinit var adapterWallapers: RecyclerAdapterWallapers
    private val START_TIME_IN_MILLIS: Long = 21*24*60*60*1000

    private var mTextViewCountDown: TextView? = null
//    private var mButtonStartPause: Button? = null
    private var mButtonReset: ImageView? = null
    var achievmentTimer:String=""
    private var mCountDownTimer: CountDownTimer? = null
    var timerachievement:Long=0
    private var mTimerRunning = false
    var timerstatement=true
    private var mTimeLeftInMillis: Long = 0
    private var mEndTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home_page, container, false)
        layoutManager = LinearLayoutManager(
            container?.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )


        Splash_Screen.data.observe(viewLifecycleOwner,
            {

                binding.BestDays.text="${it.data?.best.toString()} Days"
                binding.Attempts.text=it.data?.attempts.toString()
                binding.NameHomePage.text="${it.data?.username.toString()}"
            })
        binding.RecyclerViewHabits.layoutManager = layoutManager
        adapter= RecyclerAdapterHabits()
        binding.RecyclerViewHabits.adapter = adapter
        binding.UrlPage.setOnClickListener {
            val uri: Uri =
                Uri.parse("https://youtu.be/Msx8fz9qvUM")

            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        mTextViewCountDown = binding.textViewCountdown


        mButtonReset = binding.buttonReset


        mButtonReset?.setOnClickListener(View.OnClickListener { val builder= AlertDialog.Builder(requireContext())
            builder.setTitle("Quit App")
                .setMessage("This will erase your progress so far , are you sure want to reset?")
                .setPositiveButton("Nah, just Kidding!"){dialog,id->dialog.cancel()}
                .setNegativeButton("Yes,I'll come back Stronger!"){dialog,id->resetTimer() }
            val alertDialog:AlertDialog=builder.create()

            builder.show()
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))})
        return binding.root
    }
    private fun startTimerAchievement5Mins() {
        achievmentTimer = "5 mins"
        binding.progressBar.max=5*60*1000

        binding.progressBar.progress= 5*60*1000-timerachievement.toInt()
        if(timerstatement==true) {
            var minutes =
                TimeUnit.MILLISECONDS.toMinutes(timerachievement) - TimeUnit.HOURS.toMinutes(
                    TimeUnit.MILLISECONDS.toHours(timerachievement)
                )
            var seconds =
                TimeUnit.MILLISECONDS.toSeconds(timerachievement) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(timerachievement)
                )
            var hours = TimeUnit.MILLISECONDS.toHours(timerachievement) - TimeUnit.DAYS.toHours(
                TimeUnit.MILLISECONDS.toDays(timerachievement)
            )
            var days = TimeUnit.MILLISECONDS.toDays(mTimeLeftInMillis)
            var text = "${hours}hrs:${minutes} min: ${seconds} sec To Go"
            binding.Achievement.text = text
            timerstatement=false
        }
        else
        {
            binding.Achievement.text = "Your next target is ${achievmentTimer}"
            timerstatement=true
        }
//            },4000
//        )
//        findViewById<TextView>(R.id.Achievement).text="Your next achievement is ${achievmentTimer}"
    }

    private fun startTimerAchievement1Hour() {
        achievmentTimer = "1 Hour"
        binding.progressBar.max=1*60*60*1000

        binding.progressBar.progress=1*60*60*1000-timerachievement.toInt()
        if(timerstatement==true) {
            var minutes =
                TimeUnit.MILLISECONDS.toMinutes(timerachievement) - TimeUnit.HOURS.toMinutes(
                    TimeUnit.MILLISECONDS.toHours(timerachievement)
                )
            var seconds =
                TimeUnit.MILLISECONDS.toSeconds(timerachievement) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(timerachievement)
                )
            var hours = TimeUnit.MILLISECONDS.toHours(timerachievement) - TimeUnit.DAYS.toHours(
                TimeUnit.MILLISECONDS.toDays(timerachievement)
            )
            var days = TimeUnit.MILLISECONDS.toDays(mTimeLeftInMillis)
            var text = "${hours}hrs:${minutes} min: ${seconds} sec To Go"
            binding.Achievement.text = text
            timerstatement=false
        }
        else
        {
            binding.Achievement.text = "Your next target is ${achievmentTimer}"
            timerstatement=true
        }
//            },4000
//        )
//        findViewById<TextView>(R.id.Achievement).text="Your next achievement is ${achievmentTimer}"
    }
    private fun startTimerAchievement20min() {
        achievmentTimer = "20min"
        binding.progressBar.max=20*60*1000

        binding.progressBar.progress=20*60*1000-timerachievement.toInt()
        if(timerstatement==true) {
            var minutes =
                TimeUnit.MILLISECONDS.toMinutes(timerachievement) - TimeUnit.HOURS.toMinutes(
                    TimeUnit.MILLISECONDS.toHours(timerachievement)
                )
            var seconds =
                TimeUnit.MILLISECONDS.toSeconds(timerachievement) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(timerachievement)
                )
            var hours = TimeUnit.MILLISECONDS.toHours(timerachievement) - TimeUnit.DAYS.toHours(
                TimeUnit.MILLISECONDS.toDays(timerachievement)
            )
            var days = TimeUnit.MILLISECONDS.toDays(mTimeLeftInMillis)
            var text = "${hours}hrs:${minutes} min: ${seconds} sec To Go"
            binding.Achievement.text = text
            timerstatement=false
        }
        else
        {
            binding.Achievement.text = "Your next target is ${achievmentTimer}"
            timerstatement=true
        }
//            },4000
//        )
//        findViewById<TextView>(R.id.Achievement).text="Your next achievement is ${achievmentTimer}"
    }
    private fun startTimerAchievement5Hours() {
        achievmentTimer = "5 Hours"
        binding.progressBar.max=5*60*60*1000

        binding.progressBar.progress=5*60*60*1000-timerachievement.toInt()
        if(timerstatement==true) {
            var minutes =
                TimeUnit.MILLISECONDS.toMinutes(timerachievement) - TimeUnit.HOURS.toMinutes(
                    TimeUnit.MILLISECONDS.toHours(timerachievement)
                )
            var seconds =
                TimeUnit.MILLISECONDS.toSeconds(timerachievement) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(timerachievement)
                )
            var hours = TimeUnit.MILLISECONDS.toHours(timerachievement) - TimeUnit.DAYS.toHours(
                TimeUnit.MILLISECONDS.toDays(timerachievement)
            )
            var days = TimeUnit.MILLISECONDS.toDays(mTimeLeftInMillis)
            var text = "${hours}hrs:${minutes} min: ${seconds} sec To Go"
            binding.Achievement.text = text
            timerstatement=false
        }
        else
        {
            binding.Achievement.text = "Your next target is ${achievmentTimer}"
            timerstatement=true
        }
//            },4000
//        )
//        findViewById<TextView>(R.id.Achievement).text="Your next achievement is ${achievmentTimer}"
    }
    private fun startTimerAchievement1day() {
        achievmentTimer = "1 Day"
        binding.progressBar.max=1*24*60*60*1000

        binding.progressBar.progress=1*24*60*60*1000-timerachievement.toInt()
        if(timerstatement==true) {
            var minutes =
                TimeUnit.MILLISECONDS.toMinutes(timerachievement) - TimeUnit.HOURS.toMinutes(
                    TimeUnit.MILLISECONDS.toHours(timerachievement)
                )
            var seconds =
                TimeUnit.MILLISECONDS.toSeconds(timerachievement) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(timerachievement)
                )
            var hours = TimeUnit.MILLISECONDS.toHours(timerachievement) - TimeUnit.DAYS.toHours(
                TimeUnit.MILLISECONDS.toDays(timerachievement)
            )
            var days = TimeUnit.MILLISECONDS.toDays(mTimeLeftInMillis)
            var text = "${hours}hrs:${minutes} min: ${seconds} sec To Go"
            binding.Achievement.text = text
            timerstatement=false
        }
        else
        {
            binding.Achievement.text = "Your next target is ${achievmentTimer}"
            timerstatement=true
        }
//            },4000
//        )
//        findViewById<TextView>(R.id.Achievement).text="Your next achievement is ${achievmentTimer}"
    }
    private fun startTimerAchievement7day() {
        achievmentTimer = "7 Days"
        binding.progressBar.max=7*24*60*60*1000

        binding.progressBar.progress=7*24*60*60*1000-timerachievement.toInt()
        if(timerstatement==true) {
            var minutes =
                TimeUnit.MILLISECONDS.toMinutes(timerachievement) - TimeUnit.HOURS.toMinutes(
                    TimeUnit.MILLISECONDS.toHours(timerachievement)
                )
            var seconds =
                TimeUnit.MILLISECONDS.toSeconds(timerachievement) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(timerachievement)
                )
            var hours = TimeUnit.MILLISECONDS.toHours(timerachievement) - TimeUnit.DAYS.toHours(
                TimeUnit.MILLISECONDS.toDays(timerachievement)
            )
            var days = TimeUnit.MILLISECONDS.toDays(mTimeLeftInMillis)
            var text = "${hours}hrs:${minutes} min: ${seconds} sec To Go"
            binding.Achievement.text = text
            timerstatement=false
        }
        else
        {
            binding.Achievement.text = "Your next target is ${achievmentTimer}"
            timerstatement=true
        }
//            },4000
//        )
//        findViewById<TextView>(R.id.Achievement).text="Your next achievement is ${achievmentTimer}"
    }
    private fun startTimerAchievement21day() {
        achievmentTimer = "21 days"
        binding.progressBar.max=21*24*60*60*1000

        binding.progressBar.progress=21*24*60*60*1000-timerachievement.toInt()
        if(timerstatement==true) {
            var minutes =
                TimeUnit.MILLISECONDS.toMinutes(timerachievement) - TimeUnit.HOURS.toMinutes(
                    TimeUnit.MILLISECONDS.toHours(timerachievement)
                )
            var seconds =
                TimeUnit.MILLISECONDS.toSeconds(timerachievement) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(timerachievement)
                )
            var hours = TimeUnit.MILLISECONDS.toHours(timerachievement) - TimeUnit.DAYS.toHours(
                TimeUnit.MILLISECONDS.toDays(timerachievement)
            )
            var days = TimeUnit.MILLISECONDS.toDays(mTimeLeftInMillis)
            var text = "${hours}hrs:${minutes} min: ${seconds} sec To Go"
            binding.Achievement.text = text
            timerstatement=false
        }
        else
        {
            binding.Achievement.text = "Your next target is ${achievmentTimer}"
            timerstatement=true
        }
//            },4000
//        )
//        findViewById<TextView>(R.id.Achievement).text="Your next achievement is ${achievmentTimer}"
    }

    private fun startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                when
                { //For 5 mins
                    mTimeLeftInMillis>1814100000->
                    {
                        timerachievement=millisUntilFinished-1814100000
                        startTimerAchievement5Mins()
                    }
                    //For 20 Minutes
                    mTimeLeftInMillis>1813200000->
                    {
                        timerachievement=millisUntilFinished-1813200000
                        startTimerAchievement20min()
                    }
                    //FOr One HOur
                    mTimeLeftInMillis>1810800000->
                    {
                        timerachievement=millisUntilFinished-1810800000
                        startTimerAchievement1Hour()
                    }
                    //For Five Hour
                    mTimeLeftInMillis>1796400000->
                    {
                        timerachievement=millisUntilFinished-1796400000
                        startTimerAchievement5Hours()
                    }
                    //For One Day
                    mTimeLeftInMillis>172800000->
                    {
                        timerachievement=millisUntilFinished-172800000
                        startTimerAchievement1day()
                    }
                    //For Seven Day
                    mTimeLeftInMillis>1209600000->
                    {
                        timerachievement=millisUntilFinished-1209600000
                        startTimerAchievement7day()
                    }
                    else->
                    {
                        mTimeLeftInMillis=millisUntilFinished-1814400000
                        startTimerAchievement21day()
                    }

                }
                mTimeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                mTimerRunning = false
                updateButtons()
            }
        }.start()

        mTimerRunning = true
        updateButtons()
    }

    private fun pauseTimer() {
        mCountDownTimer!!.cancel();
        mTimerRunning = false;
        updateButtons();
    }

    private fun resetTimer() {
        var days = TimeUnit.MILLISECONDS.toDays(mTimeLeftInMillis)
        val prefs = MainActivity.prefs
        val editor = prefs.edit()
        mTimeLeftInMillis=START_TIME_IN_MILLIS
        mCountDownTimer?.cancel()
        startTimer()

        lifecycleScope.launch {
            var result=RelapseRepo().RelapseRepoApi(Splash_Screen.id.toInt(),"Mood",(21-days).toInt())
            result.observe(viewLifecycleOwner,
                {
                    when(it)
                    {
                        is Response.Success->{

                            RecyclerAdapterHabits.countdownmeditation=0
                            RecyclerAdapterHabits.countdownbooks=0
                            RecyclerAdapterHabits.countdownworkout=0
                            HomePageRepo().HomePageApi(Splash_Screen.id!!.toInt()).observe(viewLifecycleOwner,{
                                editor.putLong("millisLeft", mTimeLeftInMillis)
                                editor.putBoolean("timerRunning", mTimerRunning)
                                editor.putLong("endTime", mEndTime)
                                editor.apply()
                                Splash_Screen.data.postValue(it)
                                var intent=Intent(activity,HomePageActivity::class.java)
                                startActivity(intent)
                            })

                        }
                        is Response.Error->{Toast.makeText(requireContext(),it.errorMessage.toString(),Toast.LENGTH_LONG).show()}
                    }
                })
        }

    }

    private fun updateCountDownText() {
        var minutes= TimeUnit.MILLISECONDS.toMinutes(mTimeLeftInMillis) - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(mTimeLeftInMillis))
        var seconds= TimeUnit.MILLISECONDS.toSeconds(mTimeLeftInMillis) - TimeUnit.MINUTES.toSeconds(
            TimeUnit.MILLISECONDS.toMinutes(mTimeLeftInMillis))
        var hours= TimeUnit.MILLISECONDS.toHours(mTimeLeftInMillis)- TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(mTimeLeftInMillis))
        var days = TimeUnit.MILLISECONDS.toDays(mTimeLeftInMillis)
        var text =  " ${days}:${hours}:${minutes}:${seconds}"
        mTextViewCountDown!!.text = text
    }

    private fun updateButtons() {
//        if (mTimerRunning) {
//
////            mButtonStartPause!!.setText("Pause");
//        } else {
////            mButtonStartPause!!.setText("Start");
//
//            if (mTimeLeftInMillis < 1000) {
////                mButtonStartPause!!.setVisibility(View.INVISIBLE);
//            } else {
////                mButtonStartPause!!.setVisibility(View.VISIBLE);
//            }
//
//
//        }
    }

    override fun onStop() {
        super.onStop();

        val prefs = MainActivity.prefs
        val editor = prefs.edit()
        lifecycleScope.launch {
            Splash_Screen.saveInfo(
                "countdownworkout",
                RecyclerAdapterHabits.countdownworkout.toString()
            )
            Splash_Screen.saveInfo(
                "countdownbooks",
                RecyclerAdapterHabits.countdownbooks.toString()
            )
            Splash_Screen.saveInfo(
                "countdownmeditation",
                RecyclerAdapterHabits.countdownmeditation.toString()
            )
        }
        editor.putLong("millisLeft", mTimeLeftInMillis)
        editor.putBoolean("timerRunning", mTimerRunning)
        editor.putLong("endTime", mEndTime)

        editor.apply()

        if (mCountDownTimer != null) {
            mCountDownTimer!!.cancel()
        }
    }
    private fun getWallpapers() {

        lifecycleScope.launch {
            var days = 20 - TimeUnit.MILLISECONDS.toDays(mTimeLeftInMillis)
            var data = getWallpapersRepo().getWallapapersApi(days.toInt())
            data.observe(viewLifecycleOwner,
                {
                    when (it) {
                        is Response.Success -> {
                            layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            binding.recyclerViewWallapapers.layoutManager = layoutManager
                            adapterWallapers = RecyclerAdapterWallapers(it.data)
                            binding.recyclerViewWallapapers.adapter = adapterWallapers
                            adapterWallapers.onClickListeer(object :
                                RecyclerAdapterWallapers.ClickListener {
                                override fun OnClick(position: Int) {
                                    var dta = adapterWallapers.wallpapersModelItem!![position].image

                                    val request = DownloadManager.Request(dta.toUri())
                                    request.apply {
                                        setTitle("21Go")
                                        setDescription("Downloading...")
                                        setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                        setMimeType("image/jpeg")
                                        setDestinationInExternalPublicDir(
                                            Environment.DIRECTORY_DOWNLOADS,
                                            "Educool Downloads/Notes/Wallpapers.pdf"
                                        )
                                        setAllowedOverRoaming(true)
                                        setAllowedOverMetered(true)
                                    }
                                    val downloadManager =
                                        activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                                    downloadId = downloadManager.enqueue(request)
                                }

                                val broadcastReceiver = object : BroadcastReceiver() {
                                    override fun onReceive(context: Context?, intent: Intent?) {
                                        val id = intent?.getLongExtra(
                                            DownloadManager.EXTRA_DOWNLOAD_ID,
                                            -1
                                        )
                                        if (id == downloadId) {
                                            Toast.makeText(
                                                context,
                                                "Download complete",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }

                                    }

                                }


                            })
                        }
                    }
                })

        }
    }
    override fun onStart() {
        super.onStart();

        var prefs = MainActivity.prefs

        mTimeLeftInMillis = prefs.getLong("millisLeft", START_TIME_IN_MILLIS);
        mTimerRunning = prefs.getBoolean("timerRunning", false);
        getWallpapers()
        updateCountDownText();
        updateButtons()
        if (mTimerRunning) {
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();

            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                startTimer()
                mTimerRunning = true;
                updateCountDownText();
                updateButtons();
            } else {
                startTimer();
            }
        }

    }}