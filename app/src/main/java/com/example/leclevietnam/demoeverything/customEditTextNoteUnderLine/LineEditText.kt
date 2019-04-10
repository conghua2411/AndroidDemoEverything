package com.example.leclevietnam.demoeverything.customEditTextNoteUnderLine

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.Editable
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText

class LineEditText(context: Context?, attrs: AttributeSet?): EditText(context, attrs) {

    private var mRect: Rect = Rect()
    private var mPaint: Paint = Paint()

    init {
        mPaint.style = Paint.Style.FILL_AND_STROKE
        mPaint.color = Color.parseColor("#ffff00")

//        setOnKeyListener { v, keyCode, event ->
//            if(keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
//
//                val text = (v as EditText).text.toString()
//
//                for(i in text.indices) {
//                    Log.d("enter_click_text", "hell no $i : ${text[i].toInt()}")
//                }
//
//                val split = Character.toString(10.toChar())
//
//                val editTextRow = text.split(split).size
//
//                if(editTextRow >= 10) {
//                    val lastBreak = text.lastIndexOf("\n")
//
//                    val newText = text.substring(0, lastBreak)
//
//                    v.text = Editable.Factory.getInstance().newEditable("")
//                    v.text = Editable.Factory.getInstance().newEditable(newText)
//                    v.setSelection(newText.length)
//                }
//
//                Log.d("enter_click", "hello --- $editTextRow --- $text")
//            }
//            return@setOnKeyListener false
//        }
    }

    override fun onDraw(canvas: Canvas?) {
        val height = height
        val lineHeight = lineHeight

        var count = height / lineHeight

        if(lineCount > count) {
            count = lineCount
        }

        val r = mRect
        val paint = mPaint

        var baseLine = getLineBounds(0, r) + 50

        for (i in count downTo 0 step 1) {
            canvas!!.drawLine(r.left.toFloat(), (baseLine + 1).toFloat(), (r.right).toFloat(), (baseLine + 1).toFloat(), paint)
            baseLine += lineHeight
        }

        super.onDraw(canvas)
    }
}