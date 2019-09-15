package com.rmakiyama.android.blueprint.ui.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.rmakiyama.android.blueprint.databinding.FragmentTimelineBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TimelineFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: TimelineViewModel by viewModels { factory }
    private lateinit var binding: FragmentTimelineBinding
    private val timelinePagerAdapter by lazy { TimelinePagerAdapter(childFragmentManager) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentTimelineBinding.inflate(inflater, container, false).also { binding ->
            this.binding = binding
            binding.lifecycleOwner = viewLifecycleOwner
            binding.viewModel = viewModel
            setupViewPager()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWindowInsets(view)
        viewModel.queries.observe(viewLifecycleOwner) { queries ->
            timelinePagerAdapter.update(queries)
            timelinePagerAdapter.notifyDataSetChanged()
        }
    }

    private fun setupWindowInsets(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { _, insets ->
            binding.root.updateLayoutParams<FrameLayout.LayoutParams> {
                topMargin = insets.systemWindowInsetTop
            }
            insets
        }
    }

    /**
     * ViewPagerを初期化する
     */
    private fun setupViewPager() {
        binding.pager.apply {
            adapter = timelinePagerAdapter
            binding.tab.setupWithViewPager(this)
        }
    }
}
