import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dude.myapplication.DatabaseManager
import com.dude.myapplication.Entry
import com.dude.myapplication.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.recyclerview_entrylist.view.*

class RecyclerViewAdapter(private val entryList: ArrayList<Entry>) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    var removedEntry: Entry? = null
    var removedPosition: Int? = null
    lateinit var databaseManager: DatabaseManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_entrylist, parent, false)

        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentEntry = entryList[position]

        currentEntry.image?.let { holder.imageView.setImageResource(it) }
        holder.titleTextView.text = currentEntry.title
        holder.descriptionTextView.text = currentEntry.description
    }

    override fun getItemCount(): Int {
        return entryList.size
    }

    fun removeItem(position: Int, viewHolder: RecyclerView.ViewHolder, database: DatabaseManager){
        removedEntry = entryList[position]
        removedPosition = position
        databaseManager = database

        entryList.removeAt(position)
        notifyItemRemoved(position)

        Snackbar.make(viewHolder.itemView, "Löschen rückgängig", Snackbar.LENGTH_LONG).setAction("UNDO"){
            entryList.add(removedPosition!!, removedEntry!!)
            notifyItemInserted(position)
        }.show()
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.image_view
        val titleTextView: TextView = itemView.text_view_1
        val descriptionTextView: TextView = itemView.text_view_2
    }
}