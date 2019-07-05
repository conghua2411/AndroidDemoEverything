package com.example.leclevietnam.demoeverything.factory

import com.example.leclevietnam.demoeverything.room.testDatabase.student.Student
import java.util.*

class StudentFactory {
    companion object Factory {
        fun makeStudent(): Student {
            return Student(DataFactory.randomLong(),
                    DataFactory.randomUuid(),
                    DataFactory.randomUuid(),
                    Date(),
                    DataFactory.randomUuid(),
                    DataFactory.randomUuid())
        }

        fun makeListStudent(count: Int): List<Student> {
            val listStudent = mutableListOf<Student>()
            repeat(count) {
                listStudent.add(makeStudent())
            }
            return listStudent
        }
    }
}