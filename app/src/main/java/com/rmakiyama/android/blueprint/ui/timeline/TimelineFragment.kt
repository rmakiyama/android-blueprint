package com.rmakiyama.android.blueprint.ui.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rmakiyama.android.blueprint.databinding.FragmentTimelineBinding
import com.rmakiyama.android.blueprint.ui.timeline.item.TimelineItem
import com.rmakiyama.android.shared.ui.PagingScrollListener
import com.rmakiyama.android.shared.util.orFalse
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TimelineFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: TimelineViewModel by viewModels { factory }
    private lateinit var binding: FragmentTimelineBinding
    private val timelineAdapter: TimelineAdapter = TimelineAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentTimelineBinding.inflate(inflater, container, false).also { binding ->
            this.binding = binding
            binding.lifecycleOwner = viewLifecycleOwner
            binding.viewModel = viewModel
            setupTimeline()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            timelineAdapter.update(articles)
        }
    }

    private fun setupTimeline() {
        timelineAdapter.setOnItemClickListener { item, view ->
            val body = (item as TimelineItem).article.body
            view.findNavController().navigate(
                TimelineFragmentDirections.actionNavigationTimelineToNavigationArticleDetail(body)
            )
        }
        binding.timeline.apply {
            adapter = timelineAdapter
            (layoutManager as? LinearLayoutManager)?.let { manager ->
                addOnScrollListener(object : PagingScrollListener(manager) {
                    override fun onLoadMore() = viewModel.getArticles()
                    override fun isLoading(): Boolean = viewModel.loading.orFalse()
                })
            }
        }
    }
}
