import com.google.gson.annotations.SerializedName



//Ignore this, just a data holder.
data class Meta (
	@SerializedName("updated_at") private val updated_at : Number
)
{
	fun getUpdatedAt(): Number {return updated_at}
}