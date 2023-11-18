package uikitview.bug.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentTransaction
import dev.icerock.moko.mvvm.livedata.compose.observeAsState
import org.koin.androidx.viewmodel.ext.android.viewModel
import uikitview.bug.shared.domain.utils.Constant
import uikitview.bug.shared.ui.components.NavigationDrawerScreen
import uikitview.bug.shared.viewmodel.MainViewModel
import uikitview.bug.ui.fragments.HelpComposeFragment

class MainComposeActivity : AppCompatActivity() {

    private val vm: MainViewModel by viewModel()
    private val fragment = HelpComposeFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        setUpView()
    }

    override fun onDestroy() {
        super.onDestroy()
        removeObservers()
    }

    private fun init() {
        vm.getMenuItems()
    }

    private fun removeObservers() {
        vm.menuItems.removeObserver { }
    }

    private fun setUpView() = setContent {
        val menuItems by vm.menuItems.observeAsState()

        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        val lastScreenAttached = remember {
            mutableStateOf(vm.getInitialScreen().code)
        }
        val selectedFragment: MutableState<HelpComposeFragment> = remember {
            mutableStateOf(HelpComposeFragment.newInstance())
        }
        val selectedItem = remember {
            mutableStateOf(vm.getInitialScreen())
        }

        Log.d(Constant.TAG_TRACING, "Rendering Main View")

        NavigationDrawerScreen(
            drawerState = drawerState,
            scope = scope,
            lastScreenAttached = lastScreenAttached,
            menuItems = menuItems,
            selectedItem = selectedItem,
            content = {
                Log.d(Constant.TAG_COMPOSE, "Rendering DrawerMenu content")
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        FrameLayout(context).apply {
                            id = ViewCompat.generateViewId()
                        }
                    },
                    update = {
                        selectedFragment.value = fragment
                        supportFragmentManager.beginTransaction().run {
                            Log.d(Constant.TAG_COMPOSE, "Inflating fragment")
                            replace(it.id, fragment)
                            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            commit()
                        }
                    },
                )
            },
        )
    }
}
