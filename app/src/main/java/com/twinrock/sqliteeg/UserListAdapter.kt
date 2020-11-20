import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.twinrock.sqliteeg.R
import com.twinrock.sqliteeg.User

class UserListAdapter(private val userList: MutableList<User>, private val db: SQLiteDatabase) : RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent,
            false
        )
        return MyViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user = userList!![position]
        holder.tvName.text = user.name
        holder.tvPhone.text = user.phone
        holder.tvAddress.text = user.address
    }

    override fun getItemCount(): Int {
        if (userList.isNullOrEmpty()) return 0;
        return userList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(
        itemView
    ) {
        val TAG = javaClass.name
        val  tvName: TextView = itemView.findViewById(R.id.tvName)
        val  tvPhone: TextView = itemView.findViewById(R.id.tvPhone)
        val  tvAddress: TextView = itemView.findViewById(R.id.tvAddress)
    }
}



