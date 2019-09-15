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
import io.noties.markwon.ext.tables.TablePlugin
import io.noties.markwon.image.glide.GlideImagesPlugin
import io.noties.markwon.linkify.LinkifyPlugin
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
        val context = view.context
        val markwon = Markwon.builder(context)
            .usePlugins(
                listOf(
                    TablePlugin.create(context),
                    GlideImagesPlugin.create(context),
                    LinkifyPlugin.create()
                )
            )
            .build()
        markwon.setMarkdown(binding.body, args.body)
    }
}
