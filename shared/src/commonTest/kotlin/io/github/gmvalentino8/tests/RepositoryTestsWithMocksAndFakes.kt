package io.github.gmvalentino8.tests

import io.github.gmvalentino8.shared.models.ApiModel
import io.github.gmvalentino8.shared.implementations.RepositoryImpl
import io.github.gmvalentino8.shared.interfaces.Api
import io.github.gmvalentino8.shared.models.RepositoryModel
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import org.kodein.mock.Fake
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks

class RepositoryTestsWithMocksAndFakes : TestsWithMocks() {
    override fun setUpMocks() = injectMocks(mocker)

    @Mock lateinit var api: Api
    @Fake lateinit var apiModel: ApiModel
    @Fake lateinit var repositoryModel: RepositoryModel
    private val repository by withMocks { RepositoryImpl(api = api) }

    @BeforeTest
    fun setup() {
        every { api.getRequest() } returns apiModel
        every { api.postRequest(body = apiModel) } returns Unit
    }

    @Test
    fun testGetData() {
        val result = repository.getData()
        verify { api.getRequest() }
        assertEquals(result.data, apiModel.data.toInt())
    }

    @Test
    fun testPostData() {
        repository.postData(model = repositoryModel)
        verify { api.postRequest(body = apiModel) }
    }
}
