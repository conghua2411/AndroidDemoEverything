package com.example.leclevietnam.demoeverything

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.leclevietnam.demoeverything.factory.StudentFactory
import com.example.leclevietnam.demoeverything.room.testDatabase.TestDatabase
import com.example.leclevietnam.demoeverything.room.testDatabase.student.StudentRepos
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StudentDAOTest {

    private lateinit var testDatabase: TestDatabase

    private lateinit var studentRepos: StudentRepos

    @Before
    fun initDb() {
        testDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                TestDatabase::class.java)
                .allowMainThreadQueries()
                .build()

        studentRepos = StudentRepos(testDatabase.studentDao())
    }

    @After
    fun closeDb() {
        testDatabase.close()
    }

    @Test
    fun insertStudent() {
        studentRepos.insert(StudentFactory.makeStudent())
                .subscribe()

        val students = studentRepos.getAllStudent()
                .blockingFirst()

        assert(students.isNotEmpty())
    }

    @Test
    fun deleteStudent() {

        val student = StudentFactory.makeStudent()

        studentRepos.insert(student)
                .subscribe()

        studentRepos.deleteStudent(student)

        var haveStudent = false

        studentRepos.getStudentById(student.id as Long)
                .subscribe {
                    haveStudent = true
                }

        assert(!haveStudent)
    }


}