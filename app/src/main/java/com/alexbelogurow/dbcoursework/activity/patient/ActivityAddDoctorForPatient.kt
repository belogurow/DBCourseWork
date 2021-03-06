package com.alexbelogurow.dbcoursework.activity.patient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.alexbelogurow.dbcoursework.adapter.doctor.DoctorAdapter
import com.alexbelogurow.dbcoursework.util.DBHandler
import com.alexbelogurow.dbcoursework.model.Doctor
import com.alexbelogurow.dbcoursework.R

class ActivityAddDoctorForPatient : AppCompatActivity() {

    private val TAG = "ActivityAddDoctorForPatient"
    private val ADD_DOCTOR_TO_PATIENT = 1;
    private var EXTRA_PATIENT_ID = "PATIENT_ID"


    private var mToolbar: Toolbar? = null
    private var mRecyclerView: RecyclerView? = null
    private var mFabAddDoctor: FloatingActionButton? = null

    private var dbHandler: DBHandler? = null
    private var mAdapter: DoctorAdapter? = null

    private var doctorsList: List<Doctor>? = null
    private var patientId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)

        initializeFields()
        initializeAdapter()
    }

    private fun initializeFields() {
        patientId = intent.getIntExtra(EXTRA_PATIENT_ID, -1)

        mToolbar = findViewById(R.id.toolbarDoctor)
        mRecyclerView = findViewById(R.id.recyclerViewDcotor)
        mFabAddDoctor = findViewById(R.id.fabAddDoctor)
        mFabAddDoctor?.hide()

        setSupportActionBar(mToolbar)
        supportActionBar?.title = getString(R.string.add_doctor_for_patient)

        dbHandler = DBHandler.getInstance(this)

        mRecyclerView?.layoutManager = LinearLayoutManager(this)
        mRecyclerView?.setHasFixedSize(true)
    }

    private fun initializeAdapter() {
        doctorsList = dbHandler?.allDoctors
        mAdapter = DoctorAdapter(doctorsList!!, this, ADD_DOCTOR_TO_PATIENT, patientId!!)
        mRecyclerView?.adapter = mAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Finish activity when press back button
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
