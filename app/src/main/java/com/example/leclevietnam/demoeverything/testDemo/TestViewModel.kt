package com.example.leclevietnam.demoeverything.testDemo

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leclevietnam.demoeverything.room.testDatabase.student.Student
import com.example.leclevietnam.demoeverything.room.testDatabase.student.StudentRepos
import com.example.leclevietnam.demoeverything.util.Constraint
import com.example.leclevietnam.demoeverything.util.DateUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class TestViewModel @Inject constructor(private val studentRepos: StudentRepos) : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    val obsTxtBirthDay = ObservableField("")

    val event: MutableLiveData<Event> = MutableLiveData()

    fun showDatePicker() {
        event.value = ShowDialogEvent()
    }

    fun insertStudent(
            name: String,
            email: String,
            birthday: String,
            major: String,
            classId: String) {
        compositeDisposable.add(
                studentRepos.insert(Student(
                        name = name,
                        email = email,
                        birthday = DateUtil.convertStringToDate(
                                string = birthday,
                                format = Constraint.birthDateFormat),
                        major = major,
                        classId = classId))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            if (it) {
                                Log.d("TestDemo", "insertStudent success")
                            } else {
                                Log.d("TestDemo", "insertStudent failed")
                            }
                        }, {
                            Log.d("TestDemo", "insertStudent error")
                        })
        )
    }

    fun insertListStudent(list: List<Student>) {
        compositeDisposable.add(
                studentRepos.insert(list)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            if (it) {
                                Log.d("TestDemo", "insertStudent success")
                            } else {
                                Log.d("TestDemo", "insertStudent failed")
                            }
                        }, {
                            Log.d("TestDemo", "insertStudent error")
                        })
        )
    }

    fun updateStudent(id: Long?, name: String, email: String, birthday: Date, major: String, classId: String) {

    }

    fun deleteStudent(id: Long?, name: String, email: String, birthday: Date, major: String, classId: String) {

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    inner class ShowDialogEvent : Event()
}