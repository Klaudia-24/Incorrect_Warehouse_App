package com.example.incorrect_warehouse_app.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.incorrect_warehouse_app.R
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat
import java.util.*

class ReservationAdapter(private var reservationList: List<Reservation>):
    RecyclerView.Adapter<ReservationAdapter.ViewHolder>()  {

    private lateinit var mListner: onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListner){
        mListner = listener
    }

    inner class ViewHolder(reservationView: View, listener: onItemClickListner): RecyclerView.ViewHolder(reservationView){
        val productName: MaterialTextView = reservationView.findViewById(R.id.resProductName)
        val productSize: MaterialTextView = reservationView.findViewById(R.id.resProductSize)
        val productAmount: MaterialTextView = reservationView.findViewById(R.id.resProductAmount)
        val productPrice: MaterialTextView = reservationView.findViewById(R.id.resProductTotal)
        val reservationDate: MaterialTextView = reservationView.findViewById(R.id.resDate)
        val employeeName: MaterialTextView = reservationView.findViewById(R.id.resEmployeeName)
        val employeeSurname: MaterialTextView = reservationView.findViewById(R.id.resEmployeeSurname)

        init {
            reservationView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reservation_list, parent, false)
        return ViewHolder(view, mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productName.text = reservationList[position].name
        holder.productSize.text = reservationList[position].sizeofproduct.toString()
        holder.productAmount.text = reservationList[position].amount.toString()
        holder.productPrice.text = reservationList[position].price.toString() + " " + java.util.Currency.getInstance("GBP").getSymbol(
            Locale.ENGLISH
        )

        val simpleDateFormat = SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss")
        //val date = reservationList[position].reservationdate
        //val date = LocalDateTime.parse(reservationList[position].reservationdate)
        //Log.d("TEST onBindViewH 1:",date.toString())
        //val newDate = simpleDateFormat.format(date)

//        val parser = SimpleDateFormat("EEE, dd MM yyyy 'T'HH:mm:ss z")
//        val formatter = SimpleDateFormat("dd-MM-yyyy")
//        Log.d("TEST onBindViewH date:",reservationList[position].resdate)
//        Log.d("TEST onBindViewH 1:","before")
        //val output = formatter.format(parser.parse("Wed, 08 Dec 2021 00:00:00 GMT"))

        //Log.d("TEST onBindViewH 2:",output.toString())


        //Log.d("TEST onBindViewH 2:",newDate.toString())

        //holder.reservationDate.text = simpleDateFormat.format(reservationList[position].reservationdate)


        holder.reservationDate.text = reservationList[position].resdate
        holder.employeeName.text = reservationList[position].employeename
        holder.employeeSurname.text = reservationList[position].employeesurname
    }

    override fun getItemCount(): Int {
        return reservationList.size
    }
}
