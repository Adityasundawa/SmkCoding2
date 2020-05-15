import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adityasundawa.lawancorona.DataIndonesiaItem
import com.adityasundawa.lawancorona.R
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.data_item.*
class HomeAdapter(private val context: Context, private val items:
List<DataIndonesiaItem>, private val listener: (DataIndonesiaItem)-> Unit) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(
            R.layout.data_item,
            parent, false))
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }
    class ViewHolder(val context: Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(item: DataIndonesiaItem, listener: (DataIndonesiaItem) -> Unit) {
            txtPositive.text = item.positif
            txtMeninggal.text = item.meninggal
            txtSembuh.text = item.sembuh

            containerView.setOnClickListener { listener(item) }
        }
    }
}