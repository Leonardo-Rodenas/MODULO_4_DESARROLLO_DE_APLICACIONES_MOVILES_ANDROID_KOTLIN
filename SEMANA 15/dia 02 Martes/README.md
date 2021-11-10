<center>

# Evidencia dia 02 - Semana 15
#### Leonardo Rodenas Escobar

</center>

---

## **Reflexión:** 
El curso va bastante bien a pesar del millón de problemas que tuvimos hacias atrás (y que no vale la pena explicar ahora). Hoy fue un día más de reforzar los conocimientos y de consolidar todo hasta ahora en un sólo ejercicio. Por el momento no pude terminar todo pero voy progresando poco a poco sobre ello.

## **Ejercicio:** 
<center>

![ejercicio](https://i.imgur.com/iT0Qyri.png)

</center>

Para iniciar el ejercicio comencé importando todas las dependencias necesarias (retrofit, lyfecycle, corrutinas, room, picasso) e implemento el viewBinding en Gradle.

Luego creo los packages y clases/interfaces necesarias para implementar Room y la conexión a la API.

<center>

![img](https://i.imgur.com/lh2mSet.png)

</center>

Dejo acá el código escrito en cada una de ellas:

## **Entitys(modelo):** 

~~~Kotlin
@Entity(tableName = "tabla_terrenos")
data class TerrenosModelItem(

    @PrimaryKey(autoGenerate = false)
    val id: String,

    @SerializedName("imagen")
    val img_src: String,

    val price: Int, //Long en el ejemplo?

    val type: String
)
~~~
<br>

## **Dao:** 

~~~Kotlin
@Dao
interface TerrenosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTodosLosTerrenos(listaDeTerrenos: List<TerrenosModelItem>)

    @Query("SELECT * FROM tabla_terrenos")
    fun obtenerTodosLosTerrenosDeLaBD(): LiveData<List<TerrenosModelItem>>

}
~~~
<br>

## **Database:** 

~~~Kotlin
@Database(entities = [TerrenosModelItem::class], version = 1)
abstract class TerrenosDataBase : RoomDatabase() {

    abstract fun obtenTerrenosDelDao(): TerrenosDao

    companion object {

        @Volatile
        private var baseDeDatosCreada: TerrenosDataBase? = null

        fun crearDatabase(context: Context): TerrenosDataBase {

            if (baseDeDatosCreada == null) {
                synchronized(this)
                {
                    baseDeDatosCreada = Room.databaseBuilder(
                        context,
                        TerrenosDataBase::class.java,
                        "base_De_Datos_Terrenos"
                    ).build()
                }
            }
            return baseDeDatosCreada!!
        }

    }
}
~~~
<br>

## **Repositorio:** 

~~~Kotlin
class TerrenosRepositorio(private val terrenosDao: TerrenosDao) {

    private val service = ClienteDeRetrofit.obtenCliente()
    val miLiveData = terrenosDao.obtenerTodosLosTerrenosDeLaBD()

    fun obtenDataDelServer() {
        val call = service.obtenerTerrenos()
        call.enqueue(object : Callback<List<TerrenosModelItem>> {
            override fun onResponse(
                call: Call<List<TerrenosModelItem>>,
                response: Response<List<TerrenosModelItem>>
            ) {
                CoroutineScope(Dispatchers.IO).launch {
                    response.body()?.let {
                        terrenosDao.insertarTodosLosTerrenos(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<TerrenosModelItem>>, t: Throwable) {
                call.cancel()
            }

        })
    }
}
~~~
<br>

## **Service:** 

~~~Kotlin
interface TerrenosService {

    @GET("realstate")
    fun obtenerTerrenos(): Call<List<TerrenosModelItem>>

}
~~~
<br>

## **Cliente:** 

~~~Kotlin
class ClienteDeRetrofit {

    companion object{
        private val url = "https://android-kotlin-fun-mars-server.appspot.com/"

        fun obtenCliente(): TerrenosService{
            val retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(
                GsonConverterFactory.create()).build()
            return retrofit.create(TerrenosService::class.java)
        }
    }
}
~~~
<br>

## **ViewModel:** 

~~~Kotlin
class TerrenosViewModel(application: Application) : AndroidViewModel(application) {

    private var repositorio : TerrenosRepositorio

    init {
        //indica funcion que traera el repositorio
        val terrenosDao = TerrenosDataBase.crearDatabase(application).obtenTerrenosDelDao()
        repositorio = TerrenosRepositorio(terrenosDao)
    }

    fun exponeDatosDelServer():LiveData<List<TerrenosModelItem>> {
        return repositorio.miLiveData
    }
}
~~~
<br>

## **Adaptador del recyclerView:** 

~~~Kotlin

//OJO QUE PARA HACER ESTO, PREVIO SE CREO EL ITEM_RECYCLERVIEW.XML PARA PODER HACER EL BINDING E IMPLEMENTAR EL ADAPTADOR

class AdaptadorRV : RecyclerView.Adapter<AdaptadorRV.CustomViewHolder>() {

    private var lista: List<TerrenosModelItem> = ArrayList()

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemRecyclerviewBinding.bind(itemView)

        fun bindData(img: TerrenosModelItem) {
            Picasso.get().load(img.img_src).into(binding.ivTerreno)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindData(lista[position])
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun setTerrenos(frases: List<TerrenosModelItem>) {
        lista = frases as ArrayList<TerrenosModelItem>
        notifyDataSetChanged()
    }

}
~~~
<br>

Con esto listo, comienzo a hacer las vistas de la aplicación.

## **MainActivity**

![MAIN](https://i.imgur.com/YcRTiah.png)

## **ListaFragment:**  

![FRAGMENTLISTA](https://i.imgur.com/JpdeqFu.png)

## **DetalleFragment:** 

![DETALLEFRAGMENT](https://i.imgur.com/MjLl7aN.png)

Y bueno, hasta acá fue mi progreso durante la clase, a la tarde y mañana trataré de continuar y finalizar el ejercicio, muchas gracias.

---

Leo Rodenes Escobar :smile: