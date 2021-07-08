package com.moonlitdoor.amessage.settings

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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SettingsModule {

  @Singleton
  @Provides
  @IntoSet
  fun providesFactory(): NavigationFactory = object : NavigationFactory {
    override fun create(builder: NavGraphBuilder, navHostController: NavHostController, showBottomBar: (Boolean) -> Unit) {
      builder.composable(route = Routes.Settings.route) {
        val viewModel: SettingsViewModel = hiltViewModel(it)
        Settings(
          navHostController = navHostController,
          viewModel = viewModel,
          showBottomBar = showBottomBar,
        )
      }
      builder.composable(route = Routes.EmployeeSettings.route) {
        val viewModel: SettingsViewModel = hiltViewModel(it)
        EmployeeSettingsScreen(
          navHostController = navHostController,
          viewModel = viewModel,
          showBottomBar = showBottomBar,
        )
      }
      builder.composable(route = Routes.DeveloperSettings.route) {
        val viewModel: SettingsViewModel = hiltViewModel(it)
        DeveloperSettingsScreen(
          navHostController = navHostController,
          viewModel = viewModel,
          showBottomBar = showBottomBar,
        )
      }
    }
  }
}
