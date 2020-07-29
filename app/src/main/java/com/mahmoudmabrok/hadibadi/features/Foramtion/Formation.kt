package com.mahmoudmabrok.hadibadi.features.Foramtion

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mahmoudmabrok.hadibadi.Datalayer.DataRepo
import com.mahmoudmabrok.hadibadi.features.Foramtion.Adapter.MatchGame
import com.mahmoudmabrok.hadibadi.features.Foramtion.Adapter.matchAdapter
import com.mahmoudmabrok.hadibadi.features.LeagueActivity.LeagueActivity
import com.mahmoudmabrok.hadibadi.R
import com.mahmoudmabrok.hadibadi.util.Operation
import kotlinx.android.synthetic.main.activity_formation.*
import java.util.*

class Formation : AppCompatActivity() {
    lateinit var progressDialog: ProgressDialog
    private lateinit var list :MutableList<MatchGame>
    private lateinit var adapter: matchAdapter
    private lateinit var dataRepo: DataRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formation)
        dataRepo = DataRepo(applicationContext)
        initView()
    }

    var totalTeams = 0
    private var stageNum = 0

    private fun initView() {
        initProgress()
        adapter = matchAdapter()
        rvMatch.adapter = adapter
        list = ArrayList()
        fillAdapter()
    }

    private fun fillAdapter() {
        val teams: List<String>
        teams = ArrayList(LeagueActivity.listPlayer)
        if (teams != null) {
            Collections.shuffle(teams)
            val loop = teams.size / 2
            var match: MatchGame
            var temp: String
            var pos: Int
            for (i in 0 until loop) {
                match = MatchGame()
                pos = Operation.getRandom(teams.size)
                temp = teams.removeAt(pos)
                match.firstTeam = temp
                pos = if (teams.size == 1) {
                    0
                } else {
                    Operation.getRandom(teams.size)
                }
                temp = teams.removeAt(pos)
                match.secondTeam = temp
                match.matchNum = ++totalTeams
                list.add(match)
            }
            if (teams.size == 1) {
                match = MatchGame()
                match.firstTeam = teams[0]
                match.matchNum = ++totalTeams
                list.add(match)
            }
            // Collections.shuffle(list);
            var vv: List<MatchGame> = ArrayList(list)
            //add first stage item header
            val match1 = MatchGame()
            match1.matchNum = ++stageNum
            list.add(0, match1)
            while (vv.size > 1) {
                vv = completeLeageu(vv)
            }
            adapter.setList(list)
            progressDialog.dismiss()
        } else {
            progressDialog.dismiss()
            super.onBackPressed()
        }
    }

    private fun completeLeageu(ll: List<MatchGame>): List<MatchGame> {
        val temp = ArrayList(ll)
        Collections.shuffle(temp)
        val tempToAdd: MutableList<MatchGame> = ArrayList()
        val xx = "الفائز من "
        val loops = temp.size / 2
        var match: MatchGame
        var oldMatch: MatchGame
        var pos: Int
        var tmp: String
        for (i in 0 until loops) {
            // holder to match game
            match = MatchGame()
            pos = Operation.getRandom(temp.size)
            oldMatch = temp.removeAt(pos)
            tmp = if (oldMatch.secondTeam != null) {
                xx + oldMatch.matchNum
            } else {
                oldMatch.firstTeam
            }
            match.firstTeam = tmp
            
            pos = Operation.getRandom(temp.size)
            oldMatch = temp.removeAt(pos)
            tmp = if (oldMatch.secondTeam != null) {
                xx + oldMatch.matchNum
            } else {
                oldMatch.firstTeam
            }
            match.secondTeam = tmp
            match.matchNum = ++totalTeams
            tempToAdd.add(match)
        }
        if (temp.size == 1) {
            match = MatchGame()
            oldMatch = temp[0]
            tmp = if (oldMatch.secondTeam != null) {
                xx + oldMatch.matchNum
            } else {
                oldMatch.firstTeam
            }
            match.firstTeam = tmp
            match.matchNum = ++totalTeams
            tempToAdd.add(match)
        }
        //// TODO: 9/12/2018
        //here a new stage to add
        if (tempToAdd.size > 0) {
            val stageMatch = MatchGame()
            stageMatch.matchNum = ++stageNum
            list.add(stageMatch)
        }
        for (match1 in tempToAdd) {
            if (tempToAdd.size == 1) { //      show("^^ " + match1.getFirstTeam());
            }
            list.add(match1)
            Log.d(TAG, "before size " + ll.size + "listToAdd: " + match1.firstTeam + " vs " + match1.matchNum)
        }
        //list.addAll(tempToAdd) ; // cause unOrderd List
        return tempToAdd
    }

    private fun displayTeams(teams: List<String>) {
        for (s in teams) {
            show(s)
        }
    }

    private fun show(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    private fun initProgress() {
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Load matched Wait")
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(true)
        progressDialog.show()
    }

    companion object {
        private const val TAG = "Formation"
    }
}