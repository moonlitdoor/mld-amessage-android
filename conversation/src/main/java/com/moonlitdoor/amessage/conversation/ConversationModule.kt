package com.moonlitdoor.amessage.conversation

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
internal object ConversationModule {

  @Singleton
  @Provides
  @IntoSet
  fun providesFactory(): NavigationFactory = object : NavigationFactory {
    override fun create(builder: NavGraphBuilder, navHostController: NavHostController, showBottomBar: (Boolean) -> Unit) {
      builder.composable(route = Routes.Conversation.route, arguments = Routes.Conversation.arguments) { backStackEntry ->
        val conversationId = backStackEntry.arguments?.getString(Routes.Conversation.Arguments.CONVERSATION_ID)
        conversationId ?: throw IllegalArgumentException("conversationId can not be null")
        val viewModel: ConversationViewModel = hiltViewModel(backStackEntry)
        Conversation(
          navHostController = navHostController,
          viewModel = viewModel,
          conversationId = UUID.fromString(conversationId),
          showBottomBar = showBottomBar,
        )
      }
    }
  }
}
