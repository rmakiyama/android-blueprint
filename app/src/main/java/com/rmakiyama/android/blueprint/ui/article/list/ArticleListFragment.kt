package com.rmakiyama.android.blueprint.ui.article.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rmakiyama.android.blueprint.R
import com.rmakiyama.android.blueprint.databinding.FragmentArticleListBinding
import com.rmakiyama.android.blueprint.ui.article.list.item.ArticleItem
import com.rmakiyama.android.blueprint.ui.timeline.TimelineFragmentDirections
import com.rmakiyama.android.shared.ui.PagingScrollListener
import com.rmakiyama.android.shared.util.orFalse
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ArticleListFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: ArticleListViewModel by viewModels { factory }
    private lateinit var binding: FragmentArticleListBinding
    private val articleListAdapter: ArticleListAdapter = ArticleListAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.query = arguments?.getString(ArticleListViewModel::query.name) ?: ""
        viewModel.getArticles()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentArticleListBinding.inflate(inflater, container, false).also { binding ->
            this.binding = binding
            binding.lifecycleOwner = viewLifecycleOwner
            binding.viewModel = viewModel
            setupArticleList()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWindowInsets(view)
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            articleListAdapter.update(articles)
        }
    }

    private fun setupWindowInsets(view: View) {
        val space = resources.getDimensionPixelOffset(R.dimen.spacing_normal)
        ViewCompat.setOnApplyWindowInsetsListener(view) { _, insets ->
            binding.articleList.updatePadding(
                bottom = space + insets.systemWindowInsetBottom
            )
            insets
        }
    }

    private fun setupArticleList() {
        articleListAdapter.setOnItemClickListener { item, view ->
            val body = (item as ArticleItem).article.body
            view.findNavController().navigate(
                TimelineFragmentDirections.actionNavigationTimelineToNavigationArticleDetail(body)
            )
        }
        binding.articleList.apply {
            adapter = articleListAdapter
            (layoutManager as? LinearLayoutManager)?.let { manager ->
                addOnScrollListener(object : PagingScrollListener(manager) {
                    override fun onLoadMore() = viewModel.getArticles()
                    override fun isLoading(): Boolean = viewModel.loading.orFalse()
                })
            }
        }
    }

    companion object {

        fun newIntent(query: String): ArticleListFragment {
            val bundle = Bundle().apply {
                putString(ArticleListViewModel::query.name, query)
            }
            return ArticleListFragment().apply { arguments = bundle }
        }
    }
}
