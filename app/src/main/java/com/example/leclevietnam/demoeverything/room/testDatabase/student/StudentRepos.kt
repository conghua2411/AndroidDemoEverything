package com.example.leclevietnam.demoeverything.room.testDatabase.student

import io.reactivex.Observable
import javax.inject.Inject

class StudentRepos @Inject constructor(private val studentDAO: StudentDAO) {
    fun insert(list: List<Student>): Observable<Boolean> =
            Observable.fromCallable {
                studentDAO.insert(list)
                true
            }

    fun insert(student: Student): Observable<Boolean> =
            Observable.fromCallable {
                studentDAO.insert(student)
                true
            }

    fun getAllStudent(): Observable<List<Student>> =
            Observable.fromCallable {
                studentDAO.selectAll()
            }

    fun getAllStudent(id: Long): Observable<Student> =
            Observable.fromCallable {
                studentDAO.selectById(id)
            }
}