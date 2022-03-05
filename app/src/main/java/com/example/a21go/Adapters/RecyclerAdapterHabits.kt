package com.example.a21go.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a21go.R
import com.example.a21go.databinding.CardViewHabitsBinding
import org.eazegraph.lib.models.PieModel

class RecyclerAdapterHabits: RecyclerView.Adapter<RecyclerAdapterHabits.ViewHolder>() {
    companion object {
        var countdownworkout = 0
        var countdownbooks = 0
        var countdownmeditation = 0
        var totalSessions = 0
    }
    inner class ViewHolder(val binding:CardViewHabitsBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val cardViewLecturesBinding:CardViewHabitsBinding=
            DataBindingUtil.inflate(layoutInflater,
                R.layout.card_view_habits,parent,false)

        return ViewHolder(cardViewLecturesBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when {
        position==0->
            {
                holder.binding.HabitTitle.text = "Workout"
                if(countdownworkout>0)
                {
                    holder.binding.PieChartHabits.addPieSlice(
                        PieModel(
                            "AS",100.toFloat(),
                            Color.CYAN
                        )
                    )
                }
                holder.binding.CounterText.text="${countdownworkout}/1"
                holder.binding.ImageHoldeHabits.setImageResource(R.drawable.ic_workout)
            }
            position==1->
            {
                holder.binding.HabitTitle.text = "Books"
                if(countdownbooks>0)
                {
                    holder.binding.PieChartHabits.addPieSlice(
                        PieModel(
                            "AS",100.toFloat(),
                            Color.CYAN
                        )
                    )
                }
                holder.binding.CounterText.text="${countdownbooks}/1"
                holder.binding.ImageHoldeHabits.setImageResource(R.drawable.ic_book)
            }
            position==2->
            {
                if(countdownmeditation>0)
                {
                    holder.binding.PieChartHabits.addPieSlice(
                        PieModel(
                            "AS",100.toFloat(),
                            Color.CYAN
                        )
                    )
                }
                holder.binding.HabitTitle.text = "Meditation"
                holder.binding.CounterText.text="${countdownmeditation}/1"
                holder.binding.ImageHoldeHabits.setImageResource(R.drawable.ic_meditation)
            }
        }


      holder.binding.HabitIncreaseCounter.setOnClickListener {
          when {
              position==0->
              {
                  countdownworkout++
                  if(countdownworkout!=0)
                  {
                      holder.binding.PieChartHabits.addPieSlice(
                          PieModel(
                              "AS",100.toFloat(),
                              Color.CYAN
                          )
                      )
                  }
                  holder.binding.CounterText.text=countdownworkout.toString()
              }
              position==1->
              {countdownbooks++
                  if(countdownbooks!=0)
                  {
                      holder.binding.PieChartHabits.visibility= View.VISIBLE
                      holder.binding.PieChartHabits.addPieSlice(
                          PieModel(
                              "AS",100.toFloat(),
                              Color.CYAN
                          )
                      )
                  }
                  holder.binding.CounterText.text=countdownbooks.toString()
              }
              position==2->
              {
                  holder.binding.PieChartHabits.visibility= View.VISIBLE
                  countdownmeditation++
                  if(countdownmeditation!=0)
                  {
                      holder.binding.PieChartHabits.visibility= View.VISIBLE
                      holder.binding.PieChartHabits.addPieSlice(
                          PieModel(
                              "AS",100.toFloat(),
                              Color.CYAN
                          )
                      )
                  }
                  holder.binding.CounterText.text=countdownmeditation.toString()
              }
          }

      }
        holder.binding.HabitsDecreaseOunter.setOnClickListener {

                when {

                    position == 0 -> {

                        if (!(countdownworkout <= 0)) {
                            countdownworkout--
                            if (countdownworkout == 0) {
                                holder.binding.PieChartHabits.visibility= View.INVISIBLE
                            }
                            holder.binding.CounterText.text = countdownworkout.toString()
                        }
                    }
                    position == 1 -> {

                        if (!(countdownbooks<= 0)) {
                            countdownbooks--
                            if (countdownbooks == 0) {
                                holder.binding.PieChartHabits.visibility= View.INVISIBLE
                            }
                            holder.binding.CounterText.text = countdownbooks.toString()
                        }
                    }
                    position == 2 -> {

                        if (!(countdownmeditation<= 0)) {
                            countdownmeditation--
                        if (countdownmeditation == 0) {
                            holder.binding.PieChartHabits.visibility= View.INVISIBLE
                        }
                        holder.binding.CounterText.text = countdownmeditation.toString()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
       return 3
    }
}