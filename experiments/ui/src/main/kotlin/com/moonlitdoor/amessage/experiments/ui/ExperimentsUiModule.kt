package com.moonlitdoor.amessage.experiments.ui

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
internal object ExperimentsUiModule {

  @Singleton
  @Provides
  @IntoSet
  fun providesFactory(): NavigationFactory = object : NavigationFactory {
    override fun create(builder: NavGraphBuilder, navHostController: NavHostController, showBottomBar: (Boolean) -> Unit) {
      builder.composable(route = Routes.Experiments.route) {
        ExperimentsUi(
          navHostController = navHostController,
          showBottomBar = showBottomBar,
        )
      }
    }
  }
}
