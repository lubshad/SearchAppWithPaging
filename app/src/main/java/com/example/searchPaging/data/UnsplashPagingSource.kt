import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.searchPaging.api.UnsplashApi
import com.example.searchPaging.data.unsplash.PixabayPhoto
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class UnsplashPagingSource(
    private val unsplashApi: UnsplashApi,
    private val query: String,
) : PagingSource<Int, PixabayPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PixabayPhoto> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        return try {
            val response =
                unsplashApi.searchPhotos(searchKey = query, page = position, params.loadSize)
            val photos = response.hits

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, PixabayPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}