import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adityasundawa.lawancorona.DataGlobalItem
import com.adityasundawa.lawancorona.R
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.global_item.*
import kotlinx.android.synthetic.main.global_item.view.*

class GlobalAdapter(private val context: Context, private val items:
List<DataGlobalItem>, private val listener: (DataGlobalItem)-> Unit) :
    RecyclerView.Adapter<GlobalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(
            R.layout.global_item,
            parent, false))
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }
    class ViewHolder(val context: Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(item: DataGlobalItem, listener: (DataGlobalItem) -> Unit) {
            itemView.txtNegara.text = item.attributes.countryRegion

            itemView.txtPositif.text = item.attributes.confirmed.toString()
            itemView.txtSembuh.text = item.attributes.recovered.toString()
            itemView.txtMeninggal.text = item.attributes.deaths.toString()


            containerView.setOnClickListener { listener(item) }
        }
    }
}