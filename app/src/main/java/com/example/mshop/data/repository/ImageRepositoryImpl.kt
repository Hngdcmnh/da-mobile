package com.example.mshop.data.repository

import android.content.Context
import android.net.Uri
import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.ktx.stateOn
import com.example.mshop.data.api.image.ImageService
import com.example.mshop.data.ktx.data
import com.example.mshop.data.ktx.errorBody
import com.example.mshop.domain.repository.ImageRepository
import com.example.mshop.ui.util.FileUtils
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ImageRepositoryImpl @Inject constructor(
    private val imageService: ImageService,
    private val ioDispatcher: CoroutineDispatcher
) : ImageRepository {

    override suspend fun uploadImage(context: Context, uri: Uri): DataState<String, Exception> = stateOn(ioDispatcher) {
        val imageFile = FileUtils.getFileFromUri(context, uri)
        val requestBody = imageFile.asRequestBody(context.contentResolver.getType(uri)?.toMediaTypeOrNull())
        val filePart = MultipartBody.Part.createFormData("image", imageFile.name, requestBody)
        val response = handleResponse(imageService.uploadImage(filePart))
        if (response.isSuccess) {
            response.data().imageUrl
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun uploadImage(pathImage: String): DataState<String, Exception> = stateOn(ioDispatcher){
        val imageFile = File(pathImage)
        val typeImage = imageFile.name.substringAfterLast('.')
        val requestBody = imageFile.asRequestBody("image/$typeImage".toMediaTypeOrNull())
        val filePart = MultipartBody.Part.createFormData("image", imageFile.name, requestBody)
        val response = handleResponse(imageService.uploadImage(filePart))
        if (response.isSuccess) {
            response.data().imageUrl
        } else {
            throw response.error().errorBody()
        }
    }
}