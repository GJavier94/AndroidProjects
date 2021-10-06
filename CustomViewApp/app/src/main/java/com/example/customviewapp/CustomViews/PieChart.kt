package com.example.customviewapp.CustomViews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.GestureDetector
import android.view.GestureDetector.*
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import com.example.customviewapp.R

/**
 * Creating a Custom View
 * All of the view class inherit from View  ( even ViewGroups )
 * you can create a custom view by inheriting from View or some of its subclasses
 *
 *   View ---------> EditText  -------> CustomView
 *      |__________> CustomView
 *
 * So you can reuse the already Specific View like EditText e.g.
 *
 *
 * View Class needs two parameters for its construction ( the context, the set of attributes [which are in the xml] )
 *
 * How can we define new customizable attributes and also define its possible values ?
 *
 * 1.-By using a static resource (e.g. res/values/attrs.xml) -> This is a new Namespace
 *
 * 2.- Then the sdk android looks for the declare-styleable classes
 * 3.- you can declare a namespace with custom name <customNameNamespace> by using the xmlns tag and as a value res-auto or the fully qualified package name
 * 4.- you can call the attrs by the syntax <customNameNamespace>:attr = "value"
 * 5. then  the resource bundle pass the xml attrs to the java/kotlin class withing an object AttributSet
 * 6. there is a method which can help to parse the attrs ->     context.theme.obtainStyledAttributes
 * 7.- by the returned type of this method --->  you can call getBoolean,getInteger and so on to get the attrs in the customView class
 * 8. R.java  returns a typedArray and also a set of id's for each attr e.g
 * typedArray(
 *  attri:<dataType>
 *    ...:....
 *  attrN:<dataType>
 * )
 * set(attriId:Integer,..., attrNId:Integer)
 *


    XML                     java/kotlin
    <declare-styleable name =  <name> >    <===>  R.styleable.<name>
 */



class PieChart(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var diameter: Float = 0F
    private var hh: Float = 0F
    private var ww: Float = 0F
    private var mText: String
    private var mlabelPosition: Int
    private var mShowText: Boolean
    private var xpad = (paddingStart + paddingEnd)
    private var ypad = (paddingTop + paddingBottom)
    private var textWidth = width/2

    private var listenerMotions = object: OnGestureListener{
        override fun onDown(p0: MotionEvent?): Boolean {
            Log.i(TAG, "onDown")
            return true
        }

        override fun onShowPress(p0: MotionEvent?) {
            Log.i(TAG, "onShowPress")

        }

        override fun onSingleTapUp(p0: MotionEvent?): Boolean {
            Log.i(TAG, "onSingleTapUp")
            return true
        }

        override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
            Log.i(TAG, "onScroll")
            return true
        }

        override fun onLongPress(p0: MotionEvent?) {
            Log.i(TAG, "onLongPress")
        }

        override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
            Log.i(TAG, "onFling")
            return true
        }

    }
    private val detector = GestureDetector(context, listenerMotions)

    /**
     * canvas -> what to draw e.g. ---> drawline , color, textheight, textSize....
     * paint  -> how to draw  e.g. --->  drawCircle,....
     * We should create this objects while initializing the custom view so that we can re use in onDraw -=> because we redraw many times the view
     */
    private val paint = Paint().apply {
        this.color = Color.GREEN
        this.style = Paint.Style.STROKE
        this.textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65f, resources.displayMetrics)

    }

    private val piePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.BLUE
    }

    init {


        /**
         * here we can get the attrs declared in the xml
         * it asks for the AttributeSet
         * the method obtainStyledAttributes takes the Attributes set object an returns a TypedArray
         * then we can call get<DataType> to get some attr
         * finally we need to recycle  the TypedArray
         **/
        Log.i(TAG, "constructor: PieChart")
        context.theme.obtainStyledAttributes(attrs, R.styleable.PieChart,0,0 ).apply {
            try{

                mShowText = getBoolean(R.styleable.PieChart_showText, false)
                mlabelPosition = getInteger(R.styleable.PieChart_labelPosition, 0 )
                mText = getText(R.styleable.PieChart_android_text).toString()

            }finally {
                this.recycle()
            }
        }


        Log.i(TAG, "mShowText:$mShowText mlabelPosition:$mlabelPosition mText:$mText")
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawText(this@PieChart.mText, (width /2).toFloat(), 0F, paint)
            drawCircle(ww, hh, diameter/2, piePaint )
        }


        super.onDraw(canvas)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.i(TAG, "onMeasure")
    }


    /**
     * We then can create methods to modify some value programmatically ( dinamycally)
     *
     */

    fun isShowText():Boolean{
        return this.mShowText
    }

    fun setShowText(showText:Boolean){
        this.mShowText = showText
        this.invalidate() // to re draw the view
        this.requestLayout() // if the size is changed
    }

    fun setText(text:String){
        this.mText = text
        Log.i(TAG, "setText $text" )
        this.invalidate() // to re draw the view
        this.requestLayout() // if the size is changed
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        xpad = (paddingStart + paddingEnd)
        ypad = (paddingTop + paddingBottom)
        xpad += paint.textSize.toInt()
        ww = width.toFloat() - xpad
        hh = height.toFloat() - ypad
        diameter = Math.min(ww, hh)


        super.onSizeChanged(w, h, oldw, oldh)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i(TAG, "calling  onTouchEvent...")

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.i(TAG, "SDK_INT:${Build.VERSION.SDK_INT } greater than ... VERSION_CODES.M:${Build.VERSION_CODES.M}")
            return detector.onTouchEvent(event)
        }
        Log.i(TAG, "SDK not allowed")
        return false
    }
    companion object {
        const val TAG = "LOG:PieChart"
    }

}