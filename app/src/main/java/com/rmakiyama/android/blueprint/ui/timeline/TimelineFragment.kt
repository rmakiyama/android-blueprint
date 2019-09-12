package com.rmakiyama.android.blueprint.ui.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private val timelineAdapter: TimelineAdapter = TimelineAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentTimelineBinding.inflate(inflater, container, false).also { binding ->
            this.binding = binding
            binding.lifecycleOwner = viewLifecycleOwner
            binding.timeline.adapter = timelineAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            timelineAdapter.update(articles)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getArticle()
    }
}
