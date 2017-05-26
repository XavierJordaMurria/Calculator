package cat.jorda.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    enum class Operations
    {
        ADD, SUB, MUL, DIV
    }

    var operatorA:String? = null
    var operatorB:String? = null
    var operation:Operations? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.resultLabel.text = ""

        //.setOnClickListener({ v -> toast("Hello") })
        this.sumBtn.setOnClickListener { operation = Operations.ADD }
        this.subBtn.setOnClickListener { operation = Operations.SUB}
        this.mulBtn.setOnClickListener { operation = Operations.MUL}
        this.divBtn.setOnClickListener { operation = Operations.DIV }

        this.btn0.setOnClickListener {setOperation(value = btn0.text as String)}
        this.btn1.setOnClickListener {setOperation(value = btn1.text as String)}
        this.btn2.setOnClickListener {setOperation(value = btn2.text as String)}
        this.btn3.setOnClickListener {setOperation(value = btn3.text as String)}
        this.btn4.setOnClickListener {setOperation(value = btn4.text as String)}
        this.btn5.setOnClickListener {setOperation(value = btn5.text as String)}
        this.btn6.setOnClickListener {setOperation(value = btn6.text as String)}
        this.btn7.setOnClickListener {setOperation(value = btn7.text as String)}
        this.btn8.setOnClickListener {setOperation(value = btn8.text as String)}
        this.btn9.setOnClickListener {setOperation(value = btn9.text as String)}

        this.equalsBtn.setOnClickListener({this.resultLabel.text = calculate()})

        this.acBtn.setOnClickListener({operatorA = null
            operatorB = null
            operation = null
            this.resultLabel.text = "0"})
    }

    fun calculate():String
    {
        var tmpRes:String
        if (operatorA != null)
        {
            if (operatorB != null)
                tmpRes = doOperation(operatorA!!.toInt(), operatorB!!.toInt()).toString()
            else
                tmpRes = operatorA as String
        }
        else
            tmpRes = "0"

        //After clicking = reset all the params for next iteration.
        operatorB = null
        operatorA = tmpRes
        operation = null
        return tmpRes
    }

    fun doOperation(operatorA:Int,operatorB:Int):Int
    {
        when (operation)
        {
            Operations.ADD -> return operatorA + operatorB
            Operations.SUB -> return operatorA - operatorB
            Operations.DIV -> return operatorA / operatorB
            Operations.MUL -> return operatorA * operatorB
            else -> return 0
        }
    }

    fun setOperation(value:String)
    {
        if( operation == null)
        {
            operatorA = if (operatorA != null) operatorA.plus(value) else value

            this.resultLabel.setText(operatorA as String)
        }
        else
        {
            operatorB = if (operatorB != null) operatorB.plus(value) else value
            this.resultLabel.text = operatorB as String
        }
    }
}
