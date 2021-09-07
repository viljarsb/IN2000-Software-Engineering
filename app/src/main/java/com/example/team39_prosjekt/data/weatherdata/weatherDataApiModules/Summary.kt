
import com.google.gson.annotations.SerializedName



//Ignore this, just a data holder.
data class Summary (@SerializedName("symbol_code") private val symbol : String)
{
	fun getSymbol(): String {return symbol}
}