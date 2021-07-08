package com.moonlitdoor.amessage.connection

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.moonlitdoor.amessage.routes.NavigationFactory
import com.moonlitdoor.amessage.routes.Routes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import java.util.UUID
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ConnectionModule {

  @Singleton
  @Provides
  @IntoSet
  fun providesFactory(): NavigationFactory = object : NavigationFactory {
    override fun create(builder: NavGraphBuilder, navHostController: NavHostController, showBottomBar: (Boolean) -> Unit) {
      builder.composable(route = Routes.Connection.route, arguments = Routes.Connection.arguments) { backStackEntry ->
        val connectionId = backStackEntry.arguments?.getString(Routes.Connection.Arguments.CONNECTION_ID)
        connectionId ?: throw IllegalArgumentException("connectionId can not be null")
        val viewModel: ConnectionViewModel = hiltViewModel(backStackEntry)
        viewModel.setConnectionId(UUID.fromString(connectionId))
        Connection(
          navHostController = navHostController,
          viewModel = viewModel,
          connectionId = UUID.fromString(connectionId),
          showBottomBar = showBottomBar,
        )
      }
    }
  }
}
