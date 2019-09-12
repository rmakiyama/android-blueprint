package com.rmakiyama.android.blueprint.ui.article.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.rmakiyama.android.blueprint.databinding.FragmentArticleDetailBinding
import io.noties.markwon.Markwon
import javax.inject.Inject

class ArticleDetailFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: ArticleDetailViewModel by viewModels { factory }
    private lateinit var binding: FragmentArticleDetailBinding

    private val args: ArticleDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentArticleDetailBinding.inflate(inflater, container, false).also { binding ->
            this.binding = binding
            binding.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val markwon = Markwon.create(view.context)
        markwon.setMarkdown(binding.body, args.body)
    }
}
