package com.mahmoudmabrok.hadibadi.hady

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mahmoudmabrok.hadibadi.hady.adapter.CustomAdapter
import com.mahmoudmabrok.hadibadi.R
import com.mahmoudmabrok.hadibadi.util.Alert
import com.mahmoudmabrok.hadibadi.util.Operation
import kotlinx.android.synthetic.main.activity_hady.*

class HadyActivity : AppCompatActivity() {

    private val adapter: CustomAdapter by lazy { CustomAdapter() }
    private val listPlayer: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hady)
        initView()
    }

    private fun initView() {

        rvHadyList.adapter = adapter

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: ViewHolder, target: ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                listPlayer.removeAt(pos)
                adapter.removeItem(pos)
            }
        }).attachToRecyclerView(rvHadyList)
    }

    /**
     * select one player from list and draw it out
     * @param view
     */
    fun Select(view: View?) {
        val size = adapter.itemCount
        if (size > 1) {
            val rnd = Operation.getRandom(size)
            val playerName = adapter.drawOut(rnd)
            // display nam with dialog
            Alert.getDialog(this@HadyActivity, playerName).show()
        } else {
            edHadyName.error = "Add More Players"
        }
    }


    fun addPlayer(view: View?) {
        val s = edHadyName.text.toString()
        if (!TextUtils.isEmpty(s)) {
            listPlayer.add(s)
            adapter.addItem(s)
            edHadyName.setText("")
        } else {
            edHadyName.error = "please add player name"
        }
    }
}