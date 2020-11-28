import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.twinrock.sqliteeg.MainActivity
import com.twinrock.sqliteeg.R
import com.twinrock.sqliteeg.User

class UserListAdapter(private val userList: MutableList<User>, private val db: SQLiteDatabase) : RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent,
            false
        )
        return MyViewHolder(layoutView, parent.context)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user = userList!![position]
        holder.tvName.text = user.name
        holder.tvPhone.text = user.phone
        holder.tvAddress.text = user.address

        holder.row.tag = user
    }

    override fun getItemCount(): Int {
        if (userList.isNullOrEmpty()) return 0;
        return userList.size
    }

    class MyViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(
        itemView
    ), View.OnClickListener {
        val TAG = javaClass.name
        val  tvName: TextView = itemView.findViewById(R.id.tvName)
        val  tvPhone: TextView = itemView.findViewById(R.id.tvPhone)
        val  tvAddress: TextView = itemView.findViewById(R.id.tvAddress)
        val row: LinearLayout = itemView.findViewById(R.id.row)
        val context: Context

        init {
            row.setOnClickListener(this)
            this.context = context
        }

        override fun onClick(row: View?) {
            val user = row?.getTag() as User
            Log.i(TAG, "-------- onclick item = "+ user.name)
            var intent = Intent(context, MainActivity::class.java)
            this.context.startActivity(intent)
        }

    }
}