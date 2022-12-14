package com.example.planpacifico.data.rest

import com.example.planpacifico.core.Constants
import com.example.planpacifico.data.models.entities.FeatureEntity
import com.example.planpacifico.data.models.entities.ProfileEntity
import com.example.planpacifico.data.models.entities.WaterTypesEntity
import com.example.planpacifico.data.models.web.AnalysisBody
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.UsersEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.*
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.analysis.AnalysisResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.authentication.AuthBody
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.authentication.AuthResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.population.PopulationBody
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.population.PopulationResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.sample.SampleBody
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.sample.SampleByAnalysisResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.sample.SampleResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface WebService {
    @GET("listar_departments")
    suspend fun getDepartments(): BaseResponse<DepartmentResponse>

    @GET("listar_municipios")
    suspend fun getAllMunicipalities():BaseResponse<MunicipalitiesResponse>

    @GET("listar_centros_poblados")
    suspend fun getAllPopulatedCenter():BaseResponse<PopulatedCentersResponse>

    @GET("listar_grupos_etnicos")
    suspend fun getEthnicGroups():BaseResponse<EthnicGroupResponse>

    @GET("listar_perfiles")
    suspend fun getProfiles():BaseResponse<ProfileEntity>

    @GET("listar_usuarios")
    suspend fun getUsers():BaseResponse<UsersEntity>

    @GET("listar_tipos_agua")
    suspend fun getWaterTypes():BaseResponse<WaterTypesEntity>

    @GET("listar_caracteristicas")
    suspend fun getFeatures():BaseResponse<FeatureEntity>

    @GET("listar_parametros")
    suspend fun getParameters():BaseResponse<ParameterResponse>

    @GET("listar_municipios/{id_department}")
    suspend fun getMunicipalities(@Path("id_department")id_department : Int):BaseResponse<MunicipalitiesResponse>

    @GET("listar_centros_poblados/{id_municipality}")
    suspend fun getPopulatedCenter(@Path("id_municipality")id_municipality : Int):BaseResponse<PopulatedCentersResponse>

    @POST("almacenar_poblaciones")
    suspend fun saveWebPopulation(@Body populationBody: PopulationBody):BaseResponse<PopulationResponse>

    @PUT("actualizar_poblacion/{id_population}")
    suspend fun updateWebPopulation(@Body populationBody: PopulationBody,@Path("id_population")idPopulation:Int):BaseResponse<PopulationResponse>

    @GET("listar_poblaciones")
    suspend fun getWebPopulations():BaseResponse<PopulationResponse>

    @DELETE("eliminar_poblacion/{id_population}")
    suspend fun deleteWebPopulation(@Path("id_population")id_population:Int):BaseResponse<String>

    @POST("iniciar_sesion")
    suspend fun signIn(@Body authBody: AuthBody):BaseResponse<AuthResponse>

    @GET("listar_analisis_poblacion/{id_population}")
    suspend fun getAnalysisByPopulation(@Path("id_population")id_population:Int):BaseResponse<AnalysisResponse>

    @POST("almacenar_analisis")
    suspend fun saveAnalysis(@Body analysisBody: AnalysisBody):BaseResponse<AnalysisResponse>

    @POST("almacenar_muestras")
    suspend fun saveSampleAndMeasure(@Body sampleBody: SampleBody):BaseResponse<SampleResponse>

    @GET("listar_muestras/{id_analysis}")
    suspend fun getAllSamplesByAnalysis(@Path("id_analysis")id_analysis:Int):BaseResponse<SampleByAnalysisResponse>

}
object RetrofitClient{
    val webService by lazy {

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }
}