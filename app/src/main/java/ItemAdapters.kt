import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.dude.myapplication.Entry
import com.dude.myapplication.R


class ItemAdapters(var context: Context, var arrayList: ArrayList<Entry>) : BaseAdapter() {

    override fun getView(position: Int, contextView: View?, parent: ViewGroup?): View {
        val view: View = View.inflate(context, R.layout.card_view_item_layout_list, null)

        var icons: ImageView = view.findViewById(R.id.icon_list)
        var title: TextView = view.findViewById(R.id.title_text_view)
        var description: TextView = view.findViewById(R.id.description_text_view)

        var items: Entry = arrayList.get(position)

        icons.setImageResource(items.image!!)
        title.text = items.title
        description.text = items.description



        return view!!;
    }

    override fun getItem(position: Int): Any {
        return arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayList.size
    }
}