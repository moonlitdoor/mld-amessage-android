package com.moonlitdoor.amessage.experiments

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [ExperimentsDI.ExperimentsModule::class])
interface ExperimentsDI {

  fun resources(): Resources
  fun firebaseRemoteConfigWrapper(): FirebaseRemoteConfigWrapper

  fun inject(wrapper: Experiment.InjectableWrapper): Experiment.InjectableWrapper

  @Module
  open class ExperimentsModule(private val context: Context) {

    @Provides
    fun providesResources(): Resources = context.resources

    @Provides
    fun providesSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    open fun providesFirebaseRemoteConfigWrapper(): FirebaseRemoteConfigWrapper = FirebaseRemoteConfigLive.get()

  }

  @Component.Factory
  interface Factory {
    fun create(experimentsModule: ExperimentsModule): ExperimentsDI
  }

  companion object {

    private var component: ExperimentsDI? = null

    @Synchronized
    fun init(context: Context, module: ExperimentsModule = ExperimentsModule((context))): ExperimentsDI = component ?: DaggerExperimentsDI.factory().create(
      experimentsModule = module
    ).also {
      component = it
    }

    @Synchronized
    fun get(): ExperimentsDI = component ?: run { throw Exception("Not Initialized") }

    @Synchronized
    fun unload() {
      component = null
    }

  }

}
