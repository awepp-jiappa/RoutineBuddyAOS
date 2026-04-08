package com.routinebuddy.parent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.routinebuddy.parent.data.db.RoutineBuddyDatabase
import com.routinebuddy.parent.databinding.ActivityMainBinding
import com.routinebuddy.parent.repository.ParentRepository
import com.routinebuddy.parent.ui.child.ChildProfileFragment
import com.routinebuddy.parent.ui.routine.RoutineEditorFragment
import com.routinebuddy.parent.ui.routine.RoutineListFragment

class MainActivity : AppCompatActivity(), ParentNavigator {

    private lateinit var binding: ActivityMainBinding
    private lateinit var repository: ParentRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = RoutineBuddyDatabase.getInstance(this)
        repository = ParentRepository(database.childDao(), database.routineDao(), database.taskDao())

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ChildProfileFragment.newInstance())
                .commit()
        }
    }

    override fun repository(): ParentRepository = repository

    override fun openRoutineList() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, RoutineListFragment.newInstance())
            .commit()
    }

    override fun openCreateRoutine() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, RoutineEditorFragment.newCreateInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun openEditRoutine(routineId: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, RoutineEditorFragment.newEditInstance(routineId))
            .addToBackStack(null)
            .commit()
    }

    override fun closeEditor() {
        supportFragmentManager.popBackStack()
    }
}
