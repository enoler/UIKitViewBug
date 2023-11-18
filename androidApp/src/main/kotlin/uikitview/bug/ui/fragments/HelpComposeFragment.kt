package uikitview.bug.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import dev.icerock.moko.mvvm.livedata.compose.observeAsState
import org.koin.androidx.viewmodel.ext.android.viewModel
import uikitview.bug.shared.domain.models.ListItem
import uikitview.bug.shared.domain.models.enums.MenuItem
import uikitview.bug.shared.ui.screen.HelpScreen
import uikitview.bug.shared.viewmodel.HelpViewModel

class HelpComposeFragment : Fragment() {

    private val vm: HelpViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        vm.fetchData()

        return setUpView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        removeObservers()
    }

    private fun navigateToContact() {
        Log.d("Navigate", "Contact")
    }

    private fun onSectionClicked(listItem: ListItem) = when (listItem.code) {
        MenuItem.HELP_CONTACT.code -> navigateToContact()
        else -> throw Exception("Not possible case")
    }

    private fun removeObservers() {
        vm.sections.removeObserver { }
    }

    private fun setUpView() = ComposeView(requireActivity()).apply {
        setContent {
            val sections by vm.sections.observeAsState()
            HelpScreen(sections = sections, onClickSection = ::onSectionClicked)
        }
    }

    companion object {
        fun newInstance() = HelpComposeFragment()
    }
}
